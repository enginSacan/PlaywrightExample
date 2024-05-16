# To-Do List Webapp Test Automation

## Overview
This project contains automated test scenarios for verifying the functionalities of a lightweight to-do list webapp using Cucumber and Playwright.

## Test Scenarios
### Positive Scenarios:
1. Add a new to-do item
2. Mark a to-do item as complete
3. Delete a to-do item
4. Edit an existing to-do item

### Negative Scenarios:
1. Edit a to-do item to be empty
2. Mark a non-existent to-do item
3. Add a non-existent to-do item
4. Mark a to-do item as complete twice

## Project Structure
- `src/test/features`: Contains the Gherkin feature files
- `src/test/java/steps`: Contains the step definitions for the feature files
- `src/test/java`: Contains the test runner class

## Running the Tests
1. Clone the repository.
2. Navigate to the project directory.
3. Run the tests using Maven:
   ```bash
   mvn verify

You can check the results under folder `target/cucumber-reports`