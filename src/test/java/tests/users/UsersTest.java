package tests.users;

import controllers.UserService;
import datageneration.UserDataGeneration;
import io.restassured.response.Response;
import models.users.User;
import models.users.UserData;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.testng.Assert.*;

public class UsersTest {

    String userId,userToken;

    @Test
    public void verifySchemaOfResponseWhenCreatingValidUser() {
        Response response = createUser(UserDataGeneration.createFullUserData());

        response.then().assertThat().body(matchesJsonSchemaInClasspath("schema/createUser.json"));

        deleteUser();
    }

    @Test
    public void verifyThatTheUserDataIsReturnedSuccessfullyAndNotEmptyWhenICreateUser() {
        Response response = createUser(UserDataGeneration.createFullUserData());

        User actualUser = response.as(User.class);

        assertNotNull(actualUser, "The User Data data is empty");
        assertFalse(actualUser.getUserData().toString().isEmpty(),"The User Data is empty");

        //deleteUser();
    }

    @Test
    public void verifySchemaOfResponseWhenGettingUser() {
        createUser(UserDataGeneration.createFullUserData());

        Response response = UserService.getUser(userId,userToken);

        assertEquals(response.statusCode(), 200, "The status code should be 200");
        response.then().assertThat().body(matchesJsonSchemaInClasspath("schema/getUser.json"));

        //deleteUser();
    }

    @Test
    public void verifyThatTheUserDataIsReturnedSuccessfullyAndNotEmptyWhenIGetUser() {
        createUser(UserDataGeneration.createFullUserData());

        Response response = UserService.getUser(userId,userToken);
        User actualUser = response.as(User.class);

        assertEquals(response.statusCode(), 200, "The status code should be 200");
        assertNotNull(actualUser, "The User Data data is empty");
        assertFalse(actualUser.getUserData().toString().isEmpty(),"The User Data is empty");
    }

    @Test
    public void verifyLoginResponseSchema() {
        Response response = createUser(UserDataGeneration.createFullUserData());
        User actualUser = response.as(User.class);

        Map<String,String> loginBody = new HashMap<>();
        loginBody.put("walletID",actualUser.getUserData().getWalletName());
        loginBody.put("channelType","phone");

        response = UserService.login(loginBody);

        assertEquals(response.statusCode(), 200, "The status code should be 200");
        response.then().assertThat().body(matchesJsonSchemaInClasspath("schema/login.json"));

        //deleteUser();
    }

    @Test
    public void verifySchemaOfResponseWhenUpdatingUser() {
        createUser(UserDataGeneration.createFullUserData());
        Response response = UserService.updateUser(userId,userToken,UserDataGeneration.createUserDataForUpdate());

        assertEquals(response.statusCode(), 202, "The status code should be 202 as Accepted");
        response.then().assertThat().body(matchesJsonSchemaInClasspath("schema/updateUser.json"));

        //deleteUser();
    }

    @Test
    public void verifyDeleteUserResponseStatusCode() {
        createUser(UserDataGeneration.createFullUserData());
        Response response = deleteUser();

        assertEquals(response.statusCode(), 200, "The status code should be 200");
    }

    private Response createUser(UserData userData) {
        Response response = UserService.createUser(userData);

        assertEquals(response.statusCode(), 201, "The status code should be 201 created");

        userToken = response.as(User.class).getJwtAccessToken();
        userId = response.as(User.class).getUserData().getUserId();

        return response;
    }

    private Response deleteUser() {
        return UserService.deleteUser(userId,userToken);
    }
}