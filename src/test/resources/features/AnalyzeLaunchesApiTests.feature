@regression @createTestData @deleteTestData
Feature: User is able to Start launch analysis via POST request

  Scenario Outline: Verify possibility to start launch analysis by POST request

    Given I send GET request to get Launches list
    Then I see request status code is 200
    When I get launches IDs from response
    And I send POST request with valid body to analyze launch <launchIndex>
    Then I see request status code is 200
    And I see response message is 'autoAnalyzer analysis for launch with ID='.*' started.'

    Examples:
      | launchIndex |
      | 1           |
      | 2           |
      | 5           |

  Scenario: Verify error when trying to start launch analysis By POST request with invalid launch ID

    Given I send GET request to get Launches list
    When I send POST request to analyze launch by ID 1234567
    Then I see request status code is 404
    And I see response message is 'Launch '1234567' not found. Did you use correct Launch ID?'

  Scenario: Verify error when trying to send POST request with incorrect body to start launch analysis

    Given I send GET request to get Launches list
    Then I see request status code is 200
    When I get launches IDs from response
    And I send POST request with invalid body to analyze launch 2
    Then I see request status code is 400
    And I see response message is 'Incorrect Request. [Value is not allowed for field '.*'.] [Value is not allowed for field '.*'.] [Value is not allowed for field '.*'.]'
