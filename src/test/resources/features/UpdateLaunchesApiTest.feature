@regression @createTestData @deleteTestData
Feature: User is able to update launch via PUT request

  Scenario: Verify possibility to update launch via PUT request

    Given I send GET request to get Launches list
    Then I see request status code is 200
    When I get launches IDs from response
    And I send PUT request to update launch with index 1
    Then I see request status code is 200
    And I see response message is 'Launch with ID = '.*' successfully updated.'

  Scenario: Verify error when trying to update deleted launch

    Given I send GET request to get Launches list
    Then I see request status code is 200
    When I get launches IDs from response
    And I delete launch with index 1
    Then I see request status code is 200
    When I send PUT request to update launch by ID
    Then I see request status code is 404
    And I see response message is 'Launch '.*' not found. Did you use correct Launch ID?'

  Scenario: Verify error when trying to update existing launch with incorrect data

    Given I send GET request to get Launches list
    Then I see request status code is 200
    When I get launches IDs from response
    And I send PUT request to update launch with index 1 with invalid data
    Then I see request status code is 400
    And I see response message is 'Incorrect Request. JSON parse error: .*'
