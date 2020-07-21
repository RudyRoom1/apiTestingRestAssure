package steps;

import java.util.Arrays;
import java.util.List;
import java.util.OptionalDouble;
import java.util.stream.Collectors;
import lombok.extern.slf4j.Slf4j;
import randomuser_models.RandomUser;

@Slf4j
public class RestApiSteps {

  public boolean usersHaveSameGender(List<RandomUser> randomUsers) {
    int genderQuantity = 1;
    return (randomUsers.stream().map(RandomUser::getGender).distinct().count() == genderQuantity);
  }

  public boolean usersHaveTitle(List<RandomUser> randomUsers) {
    List<String> nameTitle = Arrays.asList("Mrs", "Mr", "Ms");
    return (randomUsers.stream().map(user -> user.getName().getTitle())).distinct().collect(Collectors.toList())
        .containsAll(nameTitle);
  }

  public OptionalDouble usersAverageAge(List<RandomUser> randomUsers) {
    return randomUsers.stream().mapToInt(user -> user.getDob().getAge()).average();
  }

  public boolean stringContainsDigit(List<RandomUser> randomUsers) {
    return randomUsers.stream().map(user -> user.getLogin().getPassword()).anyMatch(password ->
        password.matches("^(?=.*\\d).+$"));
  }

  public boolean stringContainsCapital(List<RandomUser> randomUsers) {
    return randomUsers.stream().map(user -> user.getLogin().getPassword())
        .anyMatch(password -> password.matches("^(?=.*[A-Z]).+$"));
  }

  public boolean stringContainsUserName(List<RandomUser> randomUsers) {
    List<String> userPasswords = randomUsers.stream().map(user -> user.getLogin().getPassword())
        .collect(Collectors.toList());
    List<String> userNames = randomUsers.stream().map(user -> user.getLogin().getUsername())
        .collect(Collectors.toList());
    boolean isContain = false;

    for (String password : userPasswords )
      if (userNames.stream().anyMatch(password::contains)) {
        isContain = true;
      }
    return isContain;
  }


}
