Feature: Room API

  Scenario: create room
    When user creates room
    Then room should be created


  Scenario: delete room
    When user deletes room
    Then room should be removed