@regression @createTestData @createDriver @removeDriver @deleteTestData
Feature: User is able to select launches and perform different actions with them

  Background:
    Given I Log in as the Default User
    When I select current project
    And I open launches page

  Scenario Outline: Verify possibility to compare launches

    Given I select launches checkboxes <checkbox1> and <checkbox2> and select Compare
    Then I see Compare modal is opened
    And I see Launches names number is equal to number of selected launches: <number of launches>

    Examples:
      | checkbox1 | checkbox2 | number of launches |
      | 1         | 2         | 2                  |
      | 2         | 3         | 2                  |
      | 3         | 4         | 2                  |
      | 4         | 5         | 2                  |
      | 0         | 1         | 4                  |

  Scenario Outline: Verify clicking the option after selecting several launches lead to corresponding modals

    Given I select launches checkboxes 2 and 3 and select <option>
    Then I see <option> modal is opened
    But I am able to close <option> modal by Cancel button

    Examples:
      | option        |
      | Edit          |
      | Merge         |
      | Compare       |
      | Move to debug |
      | Delete        |
