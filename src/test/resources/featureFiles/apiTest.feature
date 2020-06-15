Feature: Rest Assure test VideoGamesDB
  My first test using cucumber

  Scenario Outline: Get all game data
    Given User set specification
    When  Endpoint '<endpoint>' was set endpoint
    Then  The status code is "<expectedStatus>"
    Examples:
      | endpoint    | expectedStatus |
      | /videogames | 200            |
      | /videogames | 200            |
