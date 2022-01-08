Feature: Tests for API testing https://gorest.co.in/
  Description: The purpose of test is to check working of REST API on https://gorest.co.in/

    Scenario: User is able to get all users list using get method
        Given A list of users are available on the page
        When User get list
        Then Users list are displayed

    Scenario: User is able to add new user to list using post method
        Given New position is added to user list using post request with name: "pelson" and email: "pelsonpelsoniasty6@gmail.com" and gender: "female" and status: "inactive"
        Then Users is successfully added