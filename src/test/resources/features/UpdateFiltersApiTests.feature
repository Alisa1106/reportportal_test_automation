@regression @createTestData @deleteTestData
Feature: User is able to update filter by id via PUT request

  Scenario: Verify possibility to update filter via PUT request

    Given I send GET request to get all filters for project
    Then I see request status code is 200
    When I get filters IDs from response
    And I send PUT request to update filter with index 1
    Then I see request status code is 200
    And I see response message is 'User filter with ID = '.*' successfully updated.'
