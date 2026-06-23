Feature: Hotel API

  Scenario: Get all hotels
    When user requests all hotels
    Then response should not be empty

  Scenario: Create hotel
    When user creates a hotel
    Then hotel should be created successfully

  Scenario: Get hotel by id
    When user creates a hotel
    And user requests hotel by id
    Then correct hotel should be returned

  Scenario: Delete hotel
    When user creates a hotel
    And user deletes hotel
    Then hotel should be removed
