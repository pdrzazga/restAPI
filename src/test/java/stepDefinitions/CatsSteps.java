package stepDefinitions;

import org.junit.Assert;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class CatsSteps {
    private static final String BASE_URL = "https://catfact.ninja/";

    private static Response response;
    private static String jsonString;

    @Given("A list of facts are available on the page")
        public boolean factsExisitng () {
        return true;
    }

    @When("User get random fact")
        public void getRandomFact () {
        RestAssured.baseURI = BASE_URL;
        RequestSpecification request = RestAssured.given();
        response = request.get("/fact");
        Assert.assertEquals(200, response.getStatusCode());
        jsonString = response.asString();
    }

    @Then("Fact is displayed")
        public void displayResponse (){
        System.out.println(jsonString);
    }
}
