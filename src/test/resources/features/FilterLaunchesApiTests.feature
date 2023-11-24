@regression @createTestData @deleteTestData
Feature: User is able to get all launches by filter via GET request

  Scenario Outline: Verify possibility to get launches by filter GET request

    When I send GET request to get Launches list by filtering by "<filter name>": "<filter value>"
    And I get launches IDs from response
    Then I see request status code is 200
    And I get list of Launches when every Launch contains ID

    Examples:
      | filter name | filter value   |
      | name        | Demo Api Tests |
      | status      | PASSED         |
      | user        | ahar           |
