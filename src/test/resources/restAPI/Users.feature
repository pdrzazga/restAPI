Feature: Tests for API testing https://gorest.co.in/
  Description: The purpose of test is to check working of REST API on https://gorest.co.in/

    Scenario: User is able to get all users list using get method
        Given A list of users are available on the page
        When User get list
        Then Users list are displayed

    Scenario: User is able to add new user to list, modify status and then delete it
        Given New position is added to user list using post request with name: "pelson" and email: "pelsonpelsoniasty10@gmail.com" and gender: "female" and status: "inactive"
        Then User is successfully added
        When Status for created user is changed to "active"
        Then User status is successfully modified
        When Previously created user is delete from the list using delete request
        Then User is successfully deleted