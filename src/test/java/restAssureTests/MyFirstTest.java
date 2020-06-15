package restAssureTests;

import config.EndPoint;
import config.TestConfig1;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.when;

public class MyFirstTest extends TestConfig1 {

    @Test
    public void myFirstTest() {
        given().
                log().
                ifValidationFails().
                when().get("videogames/1").
                then().
                log().
                all();
    }

    @Test
    public void deleteGame() {
        given().
                spec(videoGame_requestSpec).
                when().
                delete("/videogames/11").
                then().
                spec(responseSpec);
    }

    @Test
    public void getAllVideoGames() {
        when().
                get(EndPoint.END_POINT_VIDEOGAMES.getEndPoint()).
                then().
                log().all();
    }
}
