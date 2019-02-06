Feature: Log in on automationpractice.com page

Scenario:
    Given Selenium
    When I open login page
    And I enter email as "jan.kowalski1@gmail.com"
    And I enter password as "abcdef1" 
    Then Login should work

Scenario:
    Given Selenium
    When I open login page
    And I enter email as "fake@email.com"
    And I enter password as "fakepassword" 
    Then Login should fail
