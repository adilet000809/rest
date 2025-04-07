package test;

import io.restassured.response.Response;
import model.User;
import org.apache.http.HttpStatus;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import steps.UserSteps;

public class UserTest {

    private final UserSteps userSteps = new UserSteps();
    private User user;

    @BeforeTest
    public void setUp() {
        System.out.println(System.getenv("SOME_ENV"));
        user = new User(1L, "testUser", "FirstNAme", "LastName", "test@email.com", "password", "1234567890", 123);
    }

    @Test(description = "Test to add a user and login")
    public void testAddUser() {
        System.out.println(System.getenv("SOME_ENV"));
        Response response = userSteps.addUser(user);
        userSteps.checkStatusCode(response, HttpStatus.SC_OK);
        userSteps.checkUserId(response, user.getId());
        Response loginResponse = userSteps.loginUser(user.getUsername(), user.getPassword());
        userSteps.checkStatusCode(loginResponse, HttpStatus.SC_OK);
        userSteps.checkparam(System.getenv("SOME_ENV"));
    }

}
