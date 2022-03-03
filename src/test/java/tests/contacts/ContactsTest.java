package tests.contacts;

import controllers.ContactsService;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class ContactsTest {
    @BeforeMethod
    public void setUp() {
        ContactsService.createContact("{}","");
    }

    @Test
    public void testTest() {
        System.out.println("abc");
    }
}
