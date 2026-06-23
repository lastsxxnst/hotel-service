Feature: Guest API

  Scenario: create guest
    When user creates guest
    Then guest should be created


  Scenario: delete guest
    When user deletes guest
    Then guest should be removed