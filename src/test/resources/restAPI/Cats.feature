Feature: Testing api for catfact
  Description: The purpose of test is to check working of REST API on catfact.ninja

  Scenario: User is able to get random fact from catfact using get method
    Given A list of facts are available on the page
    When User get random fact
    Then Fact is displayed