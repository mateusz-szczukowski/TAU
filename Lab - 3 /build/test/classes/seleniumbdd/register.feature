Feature: Register on automationpractice.com page

Scenario:
    Given Selenium
    When I open register page
    And I enter registration email as "jan.kowalski667@gmail.com"
    And I enter registration password as "abcdef1" 
    And I enter first name as "Jan" 
    And I enter last name as "Kowalski"
    And I enter address as "Dworcowa 47" 
    And I enter city as "Warszawa"
    And I select default state
    And I enter post code as "00001" 
    And I enter phone number as "111-222-333"
    Then Registration should work
