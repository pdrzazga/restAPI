package stepDefinitions;

import org.junit.Assert;
import java.util.Map;
import java.util.List;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.path.json.JsonPath;

public class UsersSteps {
    private static final String BASE_URL = "https://gorest.co.in/";
    private static final String TOKEN = "cf5a46ab01ebdeb7fc41ad9197dbbc6db9443f6171917aa558c51823bfed1606";

    private static Response response;
    private static String jsonString;
    int id;

    @Given("A list of users are available on the page")
    public boolean usersExisitng () {
        return true;
    }

    @Given("New position is added to user list using post request with name: {string} and email: {string} and gender: {string} and status: {string}")
    public void addNewUser(String name, String email, String gender, String status){
        RestAssured.baseURI = BASE_URL;
        RequestSpecification request = RestAssured.given();
        request.header("Authorization", "Bearer " + TOKEN)
                .header("Content-Type", "application/json");
        response = request.body("{ \"name\": \"" + name + "\", " +
                "\"email\": \"" + email + "\", " + "\"gender\": \"" + gender + "\", " +
                "\"status\": \"" + status + "\"}")
                .post("/public/v1/users");
        Assert.assertEquals(201, response.getStatusCode());
        id = response.jsonPath().getInt("data.id");
        //System.out.println(id);
    }

    @When("Previously created user is delete from the list using delete request")
    public void deleteUser(){
        RestAssured.baseURI = BASE_URL;
        RequestSpecification request = RestAssured.given();
        request.header("Authorization", "Bearer " + TOKEN)
                .header("Content-Type", "application/json");
        //System.out.println(id);
        //System.out.println("/public/v1/users/" + id);
        response = request.delete("/public/v1/users/" + id);
        Assert.assertEquals(204, response.getStatusCode());
    }

    @When("Status for created user is changed to {string}")
    public void modifyUserStatus(String status){
        RestAssured.baseURI = BASE_URL;
        RequestSpecification request = RestAssured.given();
        request.header("Authorization", "Bearer " + TOKEN)
                .header("Content-Type", "application/json");
        //System.out.println(id);
        //System.out.println("/public/v1/users/" + id);
        response = request.body("{ \"status\": \"" + status + "\"}")
                .put("/public/v1/users/" + id);
        Assert.assertEquals(200, response.getStatusCode());
    }

    @When("User get list")
    public void getUsersList () {
        RestAssured.baseURI = BASE_URL;
        RequestSpecification request = RestAssured.given();
        response = request.get("/public/v1/users");
        Assert.assertEquals(200, response.getStatusCode());
        jsonString = response.asString();
    }

    @Then("Users list are displayed")
    public void displayUsersList (){
        System.out.println(jsonString);
    }

    @Then("User is successfully added")
    public void checkingUserExisting (){
        RestAssured.baseURI = BASE_URL;
        RequestSpecification request = RestAssured.given();
        response = request.get("/public/v1/users/"+ id);
        Assert.assertEquals(200, response.getStatusCode());
        jsonString = response.asString();
        System.out.println(jsonString);
    }

    @Then("User status is successfully modified")
    public void checkingUserStatus (){
        RestAssured.baseURI = BASE_URL;
        RequestSpecification request = RestAssured.given();
        response = request.get("/public/v1/users/"+ id);
        Assert.assertEquals("active", response.jsonPath().getString("data.status"));
        //System.out.println(response.jsonPath().getString("data.status"));
    }

    @Then("User is successfully deleted")
    public void checkingUserDeleted (){
        RestAssured.baseURI = BASE_URL;
        RequestSpecification request = RestAssured.given();
        response = request.get("/public/v1/users/"+ id);
        Assert.assertEquals(404, response.getStatusCode());
    }
}
