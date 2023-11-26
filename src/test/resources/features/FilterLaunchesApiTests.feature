@regression @createTestData @deleteTestData
Feature: User is able to get all launches by filter via GET request

  Scenario Outline: Verify possibility to get launches by filter GET request

    Given I send GET request to get Launches list by filtering by "<filter name>": "<filter value>"
    Then I see request status code is 200
    When I get launches IDs from response
    Then I get list of Launches when every Launch contains ID

    Examples:
      | filter name | filter value   |
      | name        | Demo Api Tests |
      | status      | PASSED         |
      | user        | ahar           |

  Scenario: Verify status code when trying to get launches by filter GET request with invalid filter values

    Given I send GET request to get Launches list by filtering by "status": "xzsawq"
    Then I see request status code is 400
