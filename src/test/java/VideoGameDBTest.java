import config.EndPoint;
import config.TestConfig1;
import io.restassured.response.Response;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;


public class VideoGameDBTest extends TestConfig1 {
    @Test
    public void getAllGames() {
        given().spec(videoGame_requestSpec)
                .when().get(EndPoint.END_POINT_VIDEOGAMES.getEndPoint()).
                then().
                log().all();
    }

    @Test
    public void createNewGameByJson() {
        String gameBodyJson = "{\n" +
                "  \"id\": 17,\n" +
                "  \"name\": \"string\",\n" +
                "  \"releaseDate\": \"2019-11-19T12:20:09.280Z\",\n" +
                "  \"reviewScore\": 0,\n" +
                "  \"category\": \"string\",\n" +
                "  \"rating\": \"string\"\n" +
                "}";

        given().
                body(gameBodyJson).
                when().
                post(EndPoint.END_POINT_VIDEOGAMES.getEndPoint()).
                then().log().all();
    }

    //    @Test
    public void createNewGameByXML() {
        String gameBodyXML = "<videoGame category=\"Shooter\" rating=\"Universal\">\n" +
                "    <id>13</id>\n" +
                "    <name>Resident Evil 7</name>\n" +
                "    <releaseDate>2005-10-01T00:00:00+03:00</releaseDate>\n" +
                "    <reviewScore>835</reviewScore>\n" +
                "  </videoGame>";

        given().
                body(gameBodyXML).
                when().
                post(EndPoint.END_POINT_VIDEOGAMES.getEndPoint()).
                then().log().all();
    }

    @Test
    public void updateGame() {
        String gameBodyJson = "{\n" +
                "  \"id\": 18,\n" +
                "  \"name\": \"string\",\n" +
                "  \"releaseDate\": \"2019-11-19T12:20:09.280Z\",\n" +
                "  \"reviewScore\": 22222,\n" +
                "  \"category\": \"string\",\n" +
                "  \"rating\": \"string\"\n" +
                "}";
        given()

                .body(gameBodyJson)
                .pathParam("videoGameId", 17).
                when().
                put(EndPoint.END_POINT_SINGLE_VIDEOGAMES.getEndPoint()).
                then().
                log().all();
    }

    @Test
    public void deleteGame() {
        given().
                spec(videoGame_requestSpec).
                pathParam("videoGameId", 17)
                .when().
                delete(EndPoint.END_POINT_SINGLE_VIDEOGAMES.getEndPoint()).
                then().
                spec(responseSpec);
    }

    @Test
    public void getSingleGame() {
        given().
                pathParam("videoGameId", 5).
                when()
                .get(EndPoint.END_POINT_SINGLE_VIDEOGAMES.getEndPoint())
                .then()
                .log().all();
    }

    @Test
    public void testVideoGameSerialisationByJSON() {
        VideoGame videoGame = new VideoGame("17", "shooter", "2018-04-22", "Halo 5", "Mature", "89");

        given().
                spec(videoGame_requestSpec)
                .body(videoGame)
                .when()
                .post(EndPoint.END_POINT_VIDEOGAMES.getEndPoint())
                .then()
                .log().all();
    }

    @Test
    public void testVideoGameSchemaJSON() {
        given()
                .pathParam("videoGameId", 5)
                .when()
                .get(EndPoint.END_POINT_SINGLE_VIDEOGAMES.getEndPoint())
                .then()
                .body(matchesJsonSchemaInClasspath("VideoGameJsonSchema.json"));
    }

    @Test
    public void convertJSONToPojo() {
        Response response = given()
                .pathParam("videoGameId", 5)
                .when()
                .get(EndPoint.END_POINT_SINGLE_VIDEOGAMES.getEndPoint());

        VideoGame videoGame = response.getBody().as(VideoGame.class);
        System.out.println(videoGame.toString());
    }

    @Test
    public void convertJSONToPojoList() {
        Response response = given()
                .when()
                .get(EndPoint.END_POINT_VIDEOGAMES.getEndPoint());

        List<VideoGame> videoGame = Arrays.asList(response.getBody().as(VideoGame[].class));

//        List<VideoGame> videoGame = response.getList("", VideoGame.class);
        System.out.println(videoGame.toString());
    }

}
