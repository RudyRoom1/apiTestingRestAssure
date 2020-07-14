package restAssureTests;

import config.EndPoint;
import config.TestConfig1;
import io.restassured.http.ContentType;
import io.restassured.http.Headers;
import io.restassured.response.Response;
import org.junit.Test;

import java.util.List;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;

public class FootballTests extends TestConfig1 {
    @Test
    public void getAllCompetitionsOneSeason() {
        given()
                .spec(football_requestSpec)
                .queryParam("", 2016)
                .when()
                .get(EndPoint.END_POINT_FOOTBALL_COMPETITIONS.getEndPoint())
                .then().log().all();
    }

    @Test
    public void getDetailsOfOneArea() {
        given()
                .spec(football_requestSpec)
                .when()
                .get("competitions/2021/teams")
                .then()
                .log().all()
                .body("count", equalTo(20));
    }

    @Test
    public void getFirstTeamName() {
        given()
                .spec(football_requestSpec)
                .when()
                .get("competitions/2021/teams")
                .then()
                .log().all()
                .body("teams.name[0]", equalTo("Arsenal FC"));
    }

    @Test
    public void getAllTeamData() {
        String responseBody = given().spec(football_requestSpec).when().get("teams/57").asString();
        System.out.println(responseBody);
    }

    @Test
    public void getAllTeamData_DoCheckFirst() {
        Response response = given()
                .spec(football_requestSpec)
                .when().get("teams/57")
                .then()
                .contentType(ContentType.JSON)
                .extract().response();

        String jsonResponseAsString = response.asString();
    }

    @Test
    public void extractHeaders() {
        Response response = given()
                .spec(football_requestSpec)
                .when().get("teams/57")
                .then()
                .contentType(ContentType.JSON)
                .extract().response();
        Headers headers = response.getHeaders();

        String contentType = response.getHeader("Content-Type");
        System.out.println(contentType);
    }

    @Test
    public void extractFirstTeamName() {
        String firstTeamName = given().spec(football_requestSpec)
                .when()
                .get("competitions/2021/teams")
                .jsonPath().getString("teams.name[0]");
        System.out.println(firstTeamName);
    }

    @Test
    public void extractAllTeamName() {
        Response response = given().spec(football_requestSpec)
                .when()
                .get("competitions/2021/teams")
                .then()
                .contentType(ContentType.JSON)
                .extract().response();

        List<String> teamNames = response.path("teams.name");
             for(String teamName: teamNames){
                 System.out.println(teamName);
             }
    }

}
