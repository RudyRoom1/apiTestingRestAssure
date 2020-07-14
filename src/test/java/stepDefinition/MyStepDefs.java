package stepDefinition;

import config.EndPoint;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;

import static config.TestConfig.videoGame_requestSpec;
import static io.restassured.RestAssured.given;

public class MyStepDefs {

    private Response response;
    private ValidatableResponse json;
    private RequestSpecification request;

    @Given("User set specification")
    public void user_set_specification() {
        request = given().spec(videoGame_requestSpec);
    }

    @When("Endpoint {string} was set endpoint")
    public void endpoint_was_set_endpoint(String endPoint) {
        if (!EndPoint.contains(endPoint)) {
            System.out.println("Nooooo");
        } else
            response = request.get(EndPoint.END_POINT_VIDEOGAMES.getEndPoint());
        // Write code here that turns the phrase above into concrete actions
    }

    @Then("The status code is {int}")
    public void theStatusCodeIsExpectedStatus(Integer inter) {
        System.out.println(inter);
        json = response.then().statusCode(inter);
    }
}
