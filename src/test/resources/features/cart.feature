Feature: Cart

  @regression
  Scenario: Add product and proceed to checkout
    Given the user is on the login screen
    When the user logs in with valid credentials
    And the user adds Sauce Labs Backpack to the cart
    Then the checkout screen should be displayed