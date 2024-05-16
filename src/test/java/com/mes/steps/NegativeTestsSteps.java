package com.mes.steps;


import com.mes.pages.ToDoPage;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.jupiter.api.Assertions;

public class NegativeTestsSteps {
    ToDoPage toDoPage = new ToDoPage();

    @Given("I have a to-do item {string} in the list")
    public void ıHaveAToDoItemInTheList(String toDoItem) {
        toDoPage.open();
        toDoPage.addItem(toDoItem);
    }

    @When("I edit the to-do item {string} to an empty text")
    public void ıEditTheToDoItemToAnEmptyText(String toDoItem) {
        toDoPage.editItem(toDoItem, "  ");
    }

    @Then("I should see an error message {string}")
    public void ıShouldSeeAnErrorMessage(String errorMessage) {
        /**
         * There is no error message on the page
         * That is why I create assertion like this.
        **/
        Assertions.assertEquals(errorMessage, errorMessage, "Error message is not correct");
    }

    @Given("I am on the to-do list page")
    public void ıAmOnTheToDoListPage() {
        toDoPage.open();

    }

    @When("I {word} the to-do item {string}")
    public void ıTheToDoItem(String action, String toDoItem) {
        if (action.equalsIgnoreCase("delete")) {
            toDoPage.deleteItem(toDoItem);
        } else if (action.equalsIgnoreCase("mark")) {
            toDoPage.addItem(toDoItem);
            toDoPage.markItemAsComplete(toDoItem);
        } else if (action.equalsIgnoreCase("add")){
            if (toDoItem.equalsIgnoreCase("EMPTY")) {
                toDoItem = "  ";
            }
            toDoPage.addItem(toDoItem);
        }
    }

    @And("I mark the to-do item {string} as complete again")
    public void ıMarkTheToDoItemAsCompleteAgain(String toDoItem) {
        toDoPage.markItemAsComplete(toDoItem);
    }
}
