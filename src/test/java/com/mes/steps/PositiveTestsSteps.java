package com.mes.steps;

import com.mes.pages.ToDoPage;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.jupiter.api.Assertions;

public class PositiveTestsSteps {

    ToDoPage toDoPage = new ToDoPage();

    @When("I add a new to-do item with text {string}")
    public void ıAddANewToDoItemWithText(String toDoItem) {
        toDoPage.addItem(toDoItem);
    }

    @Then("I should see {string} in the to-do list")
    public void ıShouldSeeInTheToDoList(String toDoItem) {
        Assertions.assertEquals(toDoItem,toDoPage.getItem());
    }

    @When("I add a new to-do item with empty text")
    public void ıAddANewToDoItemWithEmptyText() {
        toDoPage.addItem("    ");
    }

    @Then("I should not see {string} in the to-do list")
    public void ıShouldNotSeeInTheToDoList(String toDoItem) {
        Assertions.assertEquals(0, toDoPage.getItemCount());
    }

    @When("I edit the to-do item to {string}")
    public void ıEditTheToDoItemTo(String newToDoItem) {
        toDoPage.editItem(newToDoItem);
    }
}
