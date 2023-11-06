@regression @createTestData @createDriver @removeDriver @deleteTestData
Feature: User is able to see different defect types

  Background:
    Given I Log in as the Default User
    When I select current project
    And I open launches page

  Scenario Outline: Verify the Donut Charts colors correspond related defect type
    Given I see color <color> corresponds defect type <defect type>

    Examples:
      | color   | defect type    |
      | #ec3900 | Product Bug    |
      | #f7d63e | Automation Bug |
      | #0274d1 | System Issue   |
      | ffb743  | To Investigate |
