@regression @createTestData @deleteTestData
Feature: User is able to remove launch by id via DELETE request

  Scenario Outline: Verify possibility to delete existing launch via DELETE request

    Given I send GET request to get Launches list
    Then I see request status code is 200
    When I get launches IDs from response
    And I delete launch with index <launchIndex>
    Then I see request status code is 200
    And I see ID of deleted launch with index <launchIndex> in the response in successfully deleted section

    Examples:
      | launchIndex |
      | 2           |
      | 4           |
      | 5           |

  Scenario: Verify response body when trying to delete launch which is not exist

    Given I send GET request to get Launches list
    Then I see request status code is 200
    When I get launches IDs from response
    And I delete launch with ID 12345
    Then I see ID of deleted launch with ID 12345 in the response in not found section
