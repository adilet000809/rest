package steps;

import io.qameta.allure.Step;
import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import model.ApiResponse;
import model.User;
import org.testng.Assert;

import static io.restassured.RestAssured.given;

public class UserSteps {
    private static final String BASE_URL = "https://petstore.swagger.io/v2/";
    private static final String USER = "/user";
    private static final String LOGIN = String.format("%s/login", USER);

    @Step("Add user")
    public Response addUser(User user) {
        return given().filter(new AllureRestAssured()).baseUri(BASE_URL).contentType(ContentType.JSON).body(user).post(USER);
    }

    @Step("Login user")
    public Response loginUser(String username, String password) {
        return given().filter(new AllureRestAssured()).baseUri(BASE_URL).queryParam("username", username).queryParam("password", password)
                .get(LOGIN);
    }

    @Step("Check that status code is {expectedStatus}")
    public void checkStatusCode(Response response, int expectedStatus) {
        response.then().assertThat().statusCode(expectedStatus);
    }

    @Step("Check that user id is {expectedId}")
    public void checkUserId(Response response, Long expectedId) {
        ApiResponse user = response.as(ApiResponse.class);
        Assert.assertEquals(Long.parseLong(user.getMessage()), expectedId, "User id");
    }

    @Step("Check param is {param}")
    public void checkparam(String param) {
        Assert.assertEquals(param, "my custom variable", "param");
    }
}
