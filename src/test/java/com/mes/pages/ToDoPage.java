package com.mes.pages;

import com.mes.steps.PlaywrightManager;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.TimeoutError;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ToDoPage {

    private final Locator itemAddId;
    private final Locator itemEditId;
    private final Locator itemTestId;
    private final Locator itemCountLabelClass;
    private final Locator itemTextInput;
    private Page page;

    private static final Logger logger = LogManager.getLogger(ToDoPage.class);

    public ToDoPage () {
        this.page = PlaywrightManager.getPage();
        this.itemAddId =  page.getByTestId("text-input");
        this.itemEditId = page.getByTestId("todo-item-label");
        this.itemTestId = page.getByTestId("todo-item");
        this.itemTextInput = page.getByTestId("todo-item").getByTestId("text-input");
        this.itemCountLabelClass = page.locator(".todo-count");
    }


    public void addItem(String toDoItem) {
        itemAddId.click();
        itemAddId.fill(toDoItem);
        page.keyboard().press("Enter");
    }

    public void editItem(String toDoItem) {
        itemEditId.dblclick();
        itemTextInput.clear();
        itemTextInput.fill(toDoItem);
        page.keyboard().press("Enter");
    }

    public void editItem(String oldToDoItem, String newToDoItem) {
        page.getByText(oldToDoItem).dblclick();
        itemTextInput.clear();
        itemTextInput.fill(newToDoItem);
        page.keyboard().press("Enter");
    }
    public void deleteItem(String toDoItem) {
        if (toDoItem.equalsIgnoreCase("non-existing")) {
            try {
                page.getByText(toDoItem);
            } catch (TimeoutError e) {
                logger.warn("Item to delete does not exist", e);
            }
        }
       page.getByText(toDoItem).hover();
       page.getByText(toDoItem).locator("xpath=following-sibling::*[1]").click();
    }

    public void markItemAsComplete(String toDoItem) {

        itemTestId.getByText(toDoItem).locator("xpath=preceding-sibling::*[1]").check();
    }
    public int getItemCount() {
       return itemEditId.count();
    }
    public String getItem() {
        return itemEditId.textContent();
    }
    public void open() {
        page.navigate("https://todomvc.com/examples/react/dist/#/");
    }

}
