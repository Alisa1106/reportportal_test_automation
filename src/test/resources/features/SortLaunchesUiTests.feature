@regression @createTestData @createDriver @removeDriver @deleteTestData
Feature: User is able to see launches list sorted by most recent by default, and is able to resort them

  Background:
    Given I Log in as the Default User
    When I select current project
    And I open launches page

  Scenario: Verify launches is sorted by most recent by default
    Given I see launches are sorted by most recent bu default

  Scenario: Verify user is able to resort launches
    Given I sort Launches by start time
    Then I see Launches are sorted in ascending order
