package restAssureTests;

import io.restassured.RestAssured;
import org.junit.BeforeClass;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class AuthenticationTests {

    @BeforeClass
    public static void setup() {
        RestAssured.proxy("localhost", 8888);
    }

    @Test
    public void basicPreemptiveAuthTest(){
        given().
                auth().preemptive().basic("username","password")
                .when()
                .get("http://localhost:8080/someEndpoint");
    }

    @Test
    public void oauth1Test(){
        given()
                .auth().oauth("consumerKey","consumerSecret","consumerAccessToken","secretToken")
                .when()
                .get("http://localhost:8080/someEndpoint")
                .then().log().all();
    }

    @Test
    public void oauth2Test(){
        given()
                .auth().oauth2("accessToken")
                .when()
                .get("http://localhost:8080/someEndpoint")
                .then().log().all();
    }

    @Test
    public void relaxedHTTOSTest(){
        given().
                relaxedHTTPSValidation()
                .when()
                .get("http://localhost:8080/someEndpoint");
    }

    public void keyStoreTest(){
        given().
                keyStore("dfsswdf","password")
                .when()
                .get("http://localhost:8080/someEndpoint");
    }

    
}
