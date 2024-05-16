Feature: Negative scenarios for the to-do list application

  Scenario: Edit a to-do item to be empty
    Given I have a to-do item "Buy groceries" in the list
    When I edit the to-do item "Buy groceries" to an empty text
    Then I should see an error message "To-Do item does not exist"

  Scenario Outline: <action> a non-existent to-do item
    Given I am on the to-do list page
    When I <action> the to-do item "<toDoItem>"
    Then I should see an error message "<errorMessage>"
  Examples:
    | action | toDoItem      | errorMessage              |
    | Mark   | Buy Groceries | To-Do item does not exist |
    | Add    | EMPTY         | To-Do item does not exist |

  Scenario: Mark a to-do item as complete twice
    Given I am on the to-do list page
    When I mark the to-do item "Go to Jogging"
    And I mark the to-do item "Go to Jogging" as complete again
    Then I should see an error message "To-Do item is already marked as complete"