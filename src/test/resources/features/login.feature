Feature: Login

  @smoke
  Scenario: Successful login
    Given the user is on the login screen
    When the user logs in with valid credentials
    Then the home screen should be displayed

  @regression
  Scenario: Unsuccessful login
    Given the user is on the login screen
    When the user logs in with invalid credentials
    Then an error message should be displayed

  @smoke
  Scenario: Successful logout
    Given the user is on the login screen
    When the user logs in with valid credentials
    And the user logs out
    Then the login screen should be displayed