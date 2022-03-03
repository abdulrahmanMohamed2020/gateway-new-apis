package datageneration;

import com.github.javafaker.Faker;
import models.users.UserData;

public class UserDataGeneration {

    private UserDataGeneration() {}

    private static final Faker faker = new Faker();

    public static UserData createFullUserData() {
        UserData userData = new UserData();
        userData.setFirstName(faker.bothify("?????????"));
        userData.setLastName(faker.bothify("?????????"));
        userData.setWalletName(faker.bothify("????????###.near"));
        userData.setEmail(faker.bothify("?????##@test.com"));
        userData.setPhone(faker.numerify("##########"));
        userData.setCountryCode("+1");

        return userData;
    }

    public static UserData createUserDataForUpdate() {
        UserData userData = new UserData();
        userData.setFirstName(faker.bothify("?????????"));
        userData.setLastName(faker.bothify("?????????"));
        userData.setEmail(faker.bothify("?????##@test.com"));
        userData.setPhone(faker.numerify("##########"));
        userData.setCountryCode("+2");

        return userData;
    }

    public static UserData createUserDataForLogin() {
        UserData userData = new UserData();
        userData.setFirstName(faker.bothify("?????????"));
        userData.setLastName(faker.bothify("?????????"));
        userData.setEmail(faker.bothify("?????##@test.com"));
        userData.setPhone(faker.numerify("##########"));
        userData.setCountryCode("+2");

        return userData;
    }

}
