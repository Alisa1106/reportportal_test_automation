@regression @createTestData @deleteTestData
Feature: User is able to compare launches via GET request

  Scenario Outline: Verify possibility to compare launches by GET request

    Given I send GET request to get Launches list
    Then I see request status code is 200
    When I get launches IDs from response
    And I send GET request to compare launches <launch1> and <launch2>
    Then I see request status code is 200

    Examples:
      | launch1 | launch2 |
      | 1       | 2       |
      | 2       | 3       |
      | 1       | 4       |
