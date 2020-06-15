package restAssureTests;

import config.TestConfig1;
import io.restassured.response.Response;
import org.junit.Test;

import java.util.Map;

import static io.restassured.RestAssured.get;

public class GPathJSONTests extends TestConfig1 {

    @Test
    public void extractMapOfElementsWithFind() {
        Response response = get("competitions/2021/teams");
        Map<String, ?> allTeamDataForSingleTeam = response.path("teams.find { it.name == 'Leicester City FC' }");
        System.out.println(allTeamDataForSingleTeam);
    }

    @Test
    public void extractSingleValueWuthFind() {
        Response response = get("teams/57");
        String certainPlayer = response.path("squad.find { it.shirtNumber == 20}.name");
        System.out.println(certainPlayer);
    }
}
