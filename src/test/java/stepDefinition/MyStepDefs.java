package stepDefinition;

import static config.TestConfigRandomUser.randoUser_requestSpec;
import static io.restassured.RestAssured.given;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.introspect.VisibilityChecker;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import java.io.IOException;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.SoftAssertions;
import org.junit.Assert;
import randomuser_models.RandomUser;
import steps.RestApiSteps;

@Slf4j
public class MyStepDefs {

  private Response response;
  private ValidatableResponse json;
  private RequestSpecification request;
  private List<RandomUser> users;
  private RestApiSteps restApiSteps = new RestApiSteps();


  @Given("User set specification")
  public void user_set_specification() {
    request = given().spec(randoUser_requestSpec);
  }

  @When("Endpoint {string} was set endpoint")
  public void endpoint_was_set_endpoint(String endPoint) {
    response = request.get(endPoint);
  }

  @Then("The status code is {int}")
  public void theStatusCodeIsExpectedStatus(Integer inter) {
    System.out.println(inter);
    json = response.then().statusCode(inter);
  }

  @Then("Convert json to POJO")
  public void convertJsonToPOJO() throws IOException {
    String body = response.getBody().asString()
        .substring(response.getBody().asString().indexOf("["), response.getBody().asString().indexOf("]") + 1);

    ObjectMapper mapper = new ObjectMapper();
    mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
    mapper.setVisibilityChecker(VisibilityChecker.Std.defaultInstance().withFieldVisibility(
        JsonAutoDetect.Visibility.ANY));
    String mochedBody = "[\n"
        + "  {\n"
        + "    \"gender\": \"female\",\n"
        + "    \"name\": {\n"
        + "      \"title\": \"Mrs\",\n"
        + "      \"first\": \"Molly\",\n"
        + "      \"last\": \"Patel\"\n"
        + "    },\n"
        + "    \"location\": {\n"
        + "      \"street\": {\n"
        + "        \"number\": 1862,\n"
        + "        \"name\": \"Nelson Quay\"\n"
        + "      },\n"
        + "      \"city\": \"Hastings\",\n"
        + "      \"state\": \"Otago\",\n"
        + "      \"country\": \"New Zealand\",\n"
        + "      \"postcode\": 71986,\n"
        + "      \"coordinates\": {\n"
        + "        \"latitude\": \"62.6202\",\n"
        + "        \"longitude\": \"79.5212\"\n"
        + "      },\n"
        + "      \"timezone\": {\n"
        + "        \"offset\": \"-4:00\",\n"
        + "        \"description\": \"Atlantic Time (Canada), Caracas, La Paz\"\n"
        + "      }\n"
        + "    },\n"
        + "    \"email\": \"molly.patel@example.com\",\n"
        + "    \"login\": {\n"
        + "      \"uuid\": \"b9442949-b084-4111-9bf6-9310d3ade288\",\n"
        + "      \"username\": \"greenmeercat301\",\n"
        + "      \"password\": \"Qres3ler\",\n"
        + "      \"salt\": \"dFqhhgfT\",\n"
        + "      \"md5\": \"b96fe04fea288f9757147ba39b67a51d\",\n"
        + "      \"sha1\": \"b2eacf2cab4be2de1c5808cd8bc3abaa0672956c\",\n"
        + "      \"sha256\": \"ea238f6440927f7a663906a87e73d63de1dd19d317d195355e0dac2da5bb3aed\"\n"
        + "    },\n"
        + "    \"dob\": {\n"
        + "      \"date\": \"1949-04-13T15:02:50.310Z\",\n"
        + "      \"age\": 40\n"
        + "    },\n"
        + "    \"registered\": {\n"
        + "      \"date\": \"2002-05-25T10:56:00.236Z\",\n"
        + "      \"age\": 18\n"
        + "    },\n"
        + "    \"phone\": \"(268)-815-5460\",\n"
        + "    \"cell\": \"(894)-659-0410\",\n"
        + "    \"id\": {\n"
        + "      \"name\": \"\",\n"
        + "      \"value\": null\n"
        + "    },\n"
        + "    \"picture\": {\n"
        + "      \"large\": \"https://randomuser.me/api/portraits/women/81.jpg\",\n"
        + "      \"medium\": \"https://randomuser.me/api/portraits/med/women/81.jpg\",\n"
        + "      \"thumbnail\": \"https://randomuser.me/api/portraits/thumb/women/81.jpg\"\n"
        + "    },\n"
        + "    \"nat\": \"NZ\"\n"
        + "  },\n"
        + "  {\n"
        + "    \"gender\": \"female\",\n"
        + "    \"name\": {\n"
        + "      \"title\": \"Ms\",\n"
        + "      \"first\": \"Silvia\",\n"
        + "      \"last\": \"Ortega\"\n"
        + "    },\n"
        + "    \"location\": {\n"
        + "      \"street\": {\n"
        + "        \"number\": 8387,\n"
        + "        \"name\": \"Calle de Toledo\"\n"
        + "      },\n"
        + "      \"city\": \"Santa Cruz de Tenerife\",\n"
        + "      \"state\": \"Comunidad de Madrid\",\n"
        + "      \"country\": \"Spain\",\n"
        + "      \"postcode\": 92833,\n"
        + "      \"coordinates\": {\n"
        + "        \"latitude\": \"-7.3453\",\n"
        + "        \"longitude\": \"61.4362\"\n"
        + "      },\n"
        + "      \"timezone\": {\n"
        + "        \"offset\": \"-3:00\",\n"
        + "        \"description\": \"Brazil, Buenos Aires, Georgetown\"\n"
        + "      }\n"
        + "    },\n"
        + "    \"email\": \"silvia.ortega@example.com\",\n"
        + "    \"login\": {\n"
        + "      \"uuid\": \"0cf798f1-7216-4cda-95da-c7efebab7a3b\",\n"
        + "      \"username\": \"ticklishmouse222\",\n"
        + "      \"password\": \"puppies\",\n"
        + "      \"salt\": \"93TQSENm\",\n"
        + "      \"md5\": \"9097d8902fd3c1220bf83ef11d088aea\",\n"
        + "      \"sha1\": \"bf4614eb0e65949dab4c1c85d3f1dca0ef1b3aff\",\n"
        + "      \"sha256\": \"01ee49fa7e9ac1693b4d9003a226725dfa678c50987b7fbd43f7d91e36fd7c28\"\n"
        + "    },\n"
        + "    \"dob\": {\n"
        + "      \"date\": \"1977-08-25T20:12:12.332Z\",\n"
        + "      \"age\": 43\n"
        + "    },\n"
        + "    \"registered\": {\n"
        + "      \"date\": \"2003-04-16T20:07:23.864Z\",\n"
        + "      \"age\": 17\n"
        + "    },\n"
        + "    \"phone\": \"964-375-833\",\n"
        + "    \"cell\": \"646-302-412\",\n"
        + "    \"id\": {\n"
        + "      \"name\": \"DNI\",\n"
        + "      \"value\": \"58931731-Q\"\n"
        + "    },\n"
        + "    \"picture\": {\n"
        + "      \"large\": \"https://randomuser.me/api/portraits/women/47.jpg\",\n"
        + "      \"medium\": \"https://randomuser.me/api/portraits/med/women/47.jpg\",\n"
        + "      \"thumbnail\": \"https://randomuser.me/api/portraits/thumb/women/47.jpg\"\n"
        + "    },\n"
        + "    \"nat\": \"ES\"\n"
        + "  },\n"
        + "  {\n"
        + "    \"gender\": \"female\",\n"
        + "    \"name\": {\n"
        + "      \"title\": \"Mr\",\n"
        + "      \"first\": \"Konsta\",\n"
        + "      \"last\": \"Maki\"\n"
        + "    },\n"
        + "    \"location\": {\n"
        + "      \"street\": {\n"
        + "        \"number\": 6341,\n"
        + "        \"name\": \"Suvantokatu\"\n"
        + "      },\n"
        + "      \"city\": \"Petäjävesi\",\n"
        + "      \"state\": \"Northern Ostrobothnia\",\n"
        + "      \"country\": \"Finland\",\n"
        + "      \"postcode\": 93550,\n"
        + "      \"coordinates\": {\n"
        + "        \"latitude\": \"62.1015\",\n"
        + "        \"longitude\": \"45.3274\"\n"
        + "      },\n"
        + "      \"timezone\": {\n"
        + "        \"offset\": \"+9:00\",\n"
        + "        \"description\": \"Tokyo, Seoul, Osaka, Sapporo, Yakutsk\"\n"
        + "      }\n"
        + "    },\n"
        + "    \"email\": \"konsta.maki@example.com\",\n"
        + "    \"login\": {\n"
        + "      \"uuid\": \"2907a76b-9d65-47a6-8472-38df51b53a75\",\n"
        + "      \"username\": \"lazywolf504\",\n"
        + "      \"password\": \"lkjh\",\n"
        + "      \"salt\": \"f1DqDyyK\",\n"
        + "      \"md5\": \"1798c60145a9985fef92ab6f50e038bc\",\n"
        + "      \"sha1\": \"d2efdf9b12d0550251e19387f87790da487a915f\",\n"
        + "      \"sha256\": \"96f6e04d0b02f4e9ce42bb79808a5547897fceda33a33509d6f0bc6cbcc3c1ed\"\n"
        + "    },\n"
        + "    \"dob\": {\n"
        + "      \"date\": \"1989-12-09T03:20:45.286Z\",\n"
        + "      \"age\": 31\n"
        + "    },\n"
        + "    \"registered\": {\n"
        + "      \"date\": \"2010-06-25T13:33:10.200Z\",\n"
        + "      \"age\": 10\n"
        + "    },\n"
        + "    \"phone\": \"04-060-921\",\n"
        + "    \"cell\": \"042-306-74-40\",\n"
        + "    \"id\": {\n"
        + "      \"name\": \"HETU\",\n"
        + "      \"value\": \"NaNNA325undefined\"\n"
        + "    },\n"
        + "    \"picture\": {\n"
        + "      \"large\": \"https://randomuser.me/api/portraits/men/63.jpg\",\n"
        + "      \"medium\": \"https://randomuser.me/api/portraits/med/men/63.jpg\",\n"
        + "      \"thumbnail\": \"https://randomuser.me/api/portraits/thumb/men/63.jpg\"\n"
        + "    },\n"
        + "    \"nat\": \"FI\"\n"
        + "  },\n"
        + "  {\n"
        + "    \"gender\": \"female\",\n"
        + "    \"name\": {\n"
        + "      \"title\": \"Ms\",\n"
        + "      \"first\": \"Ivy\",\n"
        + "      \"last\": \"Wilson\"\n"
        + "    },\n"
        + "    \"location\": {\n"
        + "      \"street\": {\n"
        + "        \"number\": 9674,\n"
        + "        \"name\": \"Tremaine Avenue\"\n"
        + "      },\n"
        + "      \"city\": \"Rotorua\",\n"
        + "      \"state\": \"Gisborne\",\n"
        + "      \"country\": \"New Zealand\",\n"
        + "      \"postcode\": 75497,\n"
        + "      \"coordinates\": {\n"
        + "        \"latitude\": \"-8.9691\",\n"
        + "        \"longitude\": \"17.6797\"\n"
        + "      },\n"
        + "      \"timezone\": {\n"
        + "        \"offset\": \"-3:30\",\n"
        + "        \"description\": \"Newfoundland\"\n"
        + "      }\n"
        + "    },\n"
        + "    \"email\": \"ivy.wilson@example.com\",\n"
        + "    \"login\": {\n"
        + "      \"uuid\": \"eada6e1d-7476-4698-be7d-7a50a0f41351\",\n"
        + "      \"username\": \"yellowmouse587\",\n"
        + "      \"password\": \"pumperyellowmouse587\",\n"
        + "      \"salt\": \"FHrGApLM\",\n"
        + "      \"md5\": \"c683be4d7e8cb9709e952cfed704a47e\",\n"
        + "      \"sha1\": \"cc3f943a0698ede8086b349476f0c9371b59faab\",\n"
        + "      \"sha256\": \"a38feb73bb9e1ce0f20c99f530e7435b3f4e324423024b3afb50d3372a62f25d\"\n"
        + "    },\n"
        + "    \"dob\": {\n"
        + "      \"date\": \"1949-08-07T10:43:12.513Z\",\n"
        + "      \"age\": 40\n"
        + "    },\n"
        + "    \"registered\": {\n"
        + "      \"date\": \"2018-08-17T23:37:06.824Z\",\n"
        + "      \"age\": 2\n"
        + "    },\n"
        + "    \"phone\": \"(383)-703-4077\",\n"
        + "    \"cell\": \"(935)-400-9827\",\n"
        + "    \"id\": {\n"
        + "      \"name\": \"\",\n"
        + "      \"value\": null\n"
        + "    },\n"
        + "    \"picture\": {\n"
        + "      \"large\": \"https://randomuser.me/api/portraits/women/85.jpg\",\n"
        + "      \"medium\": \"https://randomuser.me/api/portraits/med/women/85.jpg\",\n"
        + "      \"thumbnail\": \"https://randomuser.me/api/portraits/thumb/women/85.jpg\"\n"
        + "    },\n"
        + "    \"nat\": \"NZ\"\n"
        + "  }\n"
        + "]";
    users = mapper.readValue(mochedBody, new TypeReference<List<RandomUser>>() {
    });
  }

  @Then("All user have same gender")
  public void allUserHaveSameGender() {
    Assert.assertTrue("Users have different gender", restApiSteps.usersHaveSameGender(users));
  }

  @Then("Users title occurrence of Mrs, Ms and Mr")
  public void usersOccurrenceOfMrsMsAndMr() {
    restApiSteps.usersHaveTitle(users);
  }

  @Then("Users average age is lower than {string}")
  public void usersAverageAgeIsLowerThan(String averageAge) {
    SoftAssertions softAssertions = new SoftAssertions();
    softAssertions.assertThat(restApiSteps.usersAverageAge(users).getAsDouble()).isLessThan(Double.valueOf(averageAge));
    softAssertions.assertAll();
  }

  @Then("User password contains at least 1 digit and 1 capital, none of the passwords contains user name")
  public void userPasswordContainsAtLeastDigitAndCapitalNoneOfThePasswordsContainsUserName() {
    SoftAssertions softAssertions = new SoftAssertions();
    softAssertions.assertThat(restApiSteps.stringContainsCapital(users))
        .as(String.format("User password contains capital Letter: %s", restApiSteps.stringContainsCapital(users)))
        .isTrue();
    softAssertions.assertThat(restApiSteps.stringContainsDigit(users))
        .as(String.format("User password contains digit: %s", restApiSteps.stringContainsDigit(users)))
        .isTrue();
    softAssertions.assertThat(!restApiSteps.stringContainsUserName(users))
        .as(String.format("User password contains user name: %s", restApiSteps.stringContainsUserName(users)))
        .isFalse();
    softAssertions.assertAll();
  }
}
