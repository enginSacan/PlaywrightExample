Feature: Positive scenarios for the to-do list application

  Scenario: Add a new to-do item
    Given I am on the to-do list page
    When I add a new to-do item with text "Buy groceries"
    Then I should see "Buy groceries" in the to-do list

  Scenario: Mark a to-do item as complete
    Given I am on the to-do list page
    When I add a new to-do item with empty text
    Then I should see an error message "To-Do item cannot be empty"

  Scenario: Delete a to-do item
    Given I have a to-do item "Buy groceries" in the list
    When I delete the to-do item "Buy groceries"
    Then I should not see "Buy groceries" in the to-do list

  Scenario: Edit an existing to-do item
    Given I have a to-do item "Go jogging" in the list
    When I edit the to-do item to "Buy fruits"
    Then I should see "Buy fruits" in the to-do list