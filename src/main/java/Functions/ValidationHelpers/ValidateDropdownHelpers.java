package Functions.ValidationHelpers;


import ExtentReport.ExtentReportBuilder;

import Core.Configuration;
import Core.FileReadWrite;

import Functions.UtilityHelpers.UtilityHelpers;
import com.aventstack.extentreports.Status;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.testng.Assert.assertTrue;

/**
 * This class allows for the validation of Dropdown objects
 * Uses ObjectLibrary.xml for object references
 * Uses Datalibrary.xml for data references
 */

///////////// Document Object Model ActionHelpers ////////////////

// These functions allow of the validation of the applications DOM

///////////////////////////////////////////////////////////


public class ValidateDropdownHelpers extends ExtentReportBuilder {

    static String configTimeout = FileReadWrite.loadConfigurationProperties().getProperty("timeout");
    static int timeout = Integer.parseInt(configTimeout);


    // Verify the minimum amount of characters before the dropdown is populated with values
    public static void byXpathVerifyMinimumCharacterDropdownPopulation(WebDriver driver, int minimumCharacterValue, String textValue, String dropdownTextFieldXpath) {
        try {
            // Added to prevent racing condition failure
            new WebDriverWait(driver, timeout).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(dropdownTextFieldXpath)));
        } catch (Exception e) {
            if (FileReadWrite.loadConfigurationProperties().getProperty("disableExtentReportActiveListener").equalsIgnoreCase("Yes")) {
                System.out.println("Could not find element: " + dropdownTextFieldXpath);
                Assert.fail();
            } else {
                localTest.get().fail("Could not find element: " + dropdownTextFieldXpath);
                UtilityHelpers.adhocScreenCapture(driver, "Could not find element: " + dropdownTextFieldXpath, "Failure");
                Assert.fail();
            }
        }
        try {
            // TextHelpers value to dropdown text field
            WebElement textField = driver.findElement(By.xpath(dropdownTextFieldXpath));
            // Identify text value length and determines minimum value
            // Identify text value length and determines minimum value
            for (int textValueCount = 0; textValueCount < textValue.length(); textValueCount++) {

                // Place output value into an array for each character
                char individualCharacterTextValue = textValue.charAt(textValueCount);
                // Builds the individual characters into a full string
                String appendedTextValue = new StringBuilder().append(individualCharacterTextValue).toString();
                // Sends the appended text value string to the text field
                textField.sendKeys(appendedTextValue);
                // Sleep is used to allow for processing of dropdown options
                Thread.sleep(2000);
                // Identifies the text value currently within the text field
                String textFieldValue = textField.getAttribute("value");
                // Iterates through the dropdown and determines if the expected values should display
                try{
                    WebElement dropdownList = driver.findElement(By.tagName("twa-autocomplete-items"));
                    if (textValueCount < minimumCharacterValue - 1) {
                        // Identifies the dropdown values
                        String output = dropdownList.getAttribute("textContent");
                        // Verify the text field value does not display within the dropdown values
                        assertThat(output).doesNotContain(textFieldValue);
                        System.out.println(textFieldValue + " does meet the minimum character count.");

                    } else {
                        // Identifies the dropdown values
                        String output = dropdownList.getAttribute("textContent");
                        // Verify the text field values are displayed within the dropdown values
                        assertThat(output).contains(textFieldValue);
                        System.out.println("Dropdown values found");
                    }
                }catch (Exception e) {
                    System.out.println("No options found");

                }

            }
        } catch (InterruptedException ie) {
            ie.printStackTrace();
            Assert.fail();
        }

        if (FileReadWrite.loadConfigurationProperties().getProperty("disableExtentReportActiveListener").equalsIgnoreCase("Yes")) {
        } else {
            if (localTest.get().getStatus() == Status.PASS) {
                localTest.get().pass("Validate the following element '" + dropdownTextFieldXpath + "' has minimum value of '" + minimumCharacterValue + "'");
            } else {
                localTest.get().fail("Validate the following element '" + dropdownTextFieldXpath + "' has minimum value of '" + minimumCharacterValue + "'");
                Assert.fail();
            }
        }
    }

    // Verify the minimum amount of characters before the dropdown is populated with values
    public static void byIdVerifyMinimumCharacterDropdownPopulation(WebDriver driver, int minimumCharacterValue, String textValue, String dropdownTextFieldId) {
        try {
            // Added to prevent racing condition failure
            new WebDriverWait(driver, timeout).until(ExpectedConditions.visibilityOfElementLocated(By.id(dropdownTextFieldId)));
        } catch (Exception e) {
            if (FileReadWrite.loadConfigurationProperties().getProperty("disableExtentReportActiveListener").equalsIgnoreCase("Yes")) {
                System.out.println("Could not find element: " + dropdownTextFieldId);
                Assert.fail();
            } else {
                localTest.get().fail("Could not find element: " + dropdownTextFieldId);
                UtilityHelpers.adhocScreenCapture(driver, "Could not find element: " + dropdownTextFieldId, "Failure");
                Assert.fail();
            }
        }
        try {
            // TextHelpers value to dropdown text field
            WebElement textField = driver.findElement(By.id(dropdownTextFieldId));
            // Identify text value length and determines minimum value
            // Identify text value length and determines minimum value
            for (int textValueCount = 0; textValueCount < textValue.length(); textValueCount++) {

                // Place output value into an array for each character
                char individualCharacterTextValue = textValue.charAt(textValueCount);
                // Builds the individual characters into a full string
                String appendedTextValue = new StringBuilder().append(individualCharacterTextValue).toString();
                // Sends the appended text value string to the text field
                textField.sendKeys(appendedTextValue);
                // Sleep is used to allow for processing of dropdown options
                Thread.sleep(2000);
                // Identifies the text value currently within the text field
                String textFieldValue = textField.getAttribute("value");
                // Iterates through the dropdown and determines if the expected values should display
                try{
                    WebElement dropdownList = driver.findElement(By.tagName("twa-autocomplete-items"));
                    if (textValueCount < minimumCharacterValue - 1) {
                        // Identifies the dropdown values
                        String output = dropdownList.getAttribute("textContent");
                        // Verify the text field value does not display within the dropdown values
                        assertThat(output).doesNotContain(textFieldValue);
                        System.out.println(textFieldValue + " does meet the minimum character count.");

                    } else {
                        // Identifies the dropdown values
                        String output = dropdownList.getAttribute("textContent");
                        // Verify the text field values are displayed within the dropdown values
                        assertThat(output).contains(textFieldValue);
                        System.out.println("Dropdown values found");
                    }
                }catch (Exception e) {
                    System.out.println("No options found");
                    Assert.fail();
                }

            }
        } catch (InterruptedException ie) {
            ie.printStackTrace();
            Assert.fail();
        }

        if (FileReadWrite.loadConfigurationProperties().getProperty("disableExtentReportActiveListener").equalsIgnoreCase("Yes")) {
        } else {
            if (localTest.get().getStatus() == Status.PASS) {
                localTest.get().pass("Validate the following element '" + dropdownTextFieldId + "' has minimum value of '" + minimumCharacterValue + "'");
            } else {
                localTest.get().fail("Validate the following element '" + dropdownTextFieldId + "' has minimum value of '" + minimumCharacterValue + "'");
                Assert.fail();
            }
        }
    }


    // Verify the maximum amount of characters before the dropdown is populated with values
    public static void byXpathVerifyMaximumCharacterDropdownPopulation(WebDriver driver, int maximumCharacterValue, String textValue, String dropdownTextFieldXpath) {
        try {
            // Added to prevent racing condition failure
            new WebDriverWait(driver, timeout).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(dropdownTextFieldXpath)));
        } catch (Exception e) {
            if (FileReadWrite.loadConfigurationProperties().getProperty("disableExtentReportActiveListener").equalsIgnoreCase("Yes")) {
                System.out.println("Could not find element: " + dropdownTextFieldXpath);
                Assert.fail();
            } else {
                localTest.get().fail("Could not find element: " + dropdownTextFieldXpath);
                UtilityHelpers.adhocScreenCapture(driver, "Could not find element: " + dropdownTextFieldXpath, "Failure");
                Assert.fail();
            }
        }
        try {
            // TextHelpers value to dropdown text field
            WebElement textField = driver.findElement(By.xpath(dropdownTextFieldXpath));
            // Identify text value length and determines maximum value
            for (int textValueCount = 0; textValueCount < textValue.length(); textValueCount++) {

                // Place output value into an array for each character
                char individualCharacterTextValue = textValue.charAt(textValueCount);
                // Builds the individual characters into a full string
                String appendedTextValue = new StringBuilder().append(individualCharacterTextValue).toString();
                // Sends the appended text value string to the text field
                textField.sendKeys(appendedTextValue);
                // Sleep is used to allow for processing of dropdown options
                Thread.sleep(2000);
                // Identifies the text value currently within the text field
                String textFieldValue = textField.getAttribute("value");
                // Iterates through the dropdown and determines if the expected values should display
                try{
                    WebElement dropdownList = driver.findElement(By.tagName("twa-autocomplete-items"));
                    if (textValueCount > maximumCharacterValue - 1) {
                        // Identifies the dropdown values
                        String output = dropdownList.getAttribute("textContent");
                        // Verify the text field value does not display within the dropdown values
                        System.out.println(textFieldValue + " does NOT meet the maximum character count.");
                        assertThat(output).doesNotContain(textFieldValue);
                    } else {
                        // Identifies the dropdown values
                        String output = dropdownList.getAttribute("textContent");
                        // Verify the text field values are displayed within the dropdown values
                        assertThat(output).contains(textFieldValue);
                        System.out.println("Dropdown values found");
                    }
                }catch (Exception e) {
                    System.out.println("No options found");
                    Assert.fail();
                }

            }
        } catch (InterruptedException ie) {
            ie.printStackTrace();
            Assert.fail();
        }

        if (FileReadWrite.loadConfigurationProperties().getProperty("disableExtentReportActiveListener").equalsIgnoreCase("Yes")) {
        } else {
            if (localTest.get().getStatus() == Status.PASS) {
                localTest.get().pass("Validate the following element '" + dropdownTextFieldXpath + "' has maximum value of '" + maximumCharacterValue + "'");
            } else {
                localTest.get().fail("Validate the following element '" + dropdownTextFieldXpath + "' has maximum value of '" + maximumCharacterValue + "'");
                Assert.fail();
            }
        }
    }

    // Verify the maximum amount of characters before the dropdown is populated with values
    public static void byIdVerifyMaximumCharacterDropdownPopulation(WebDriver driver, int maximumCharacterValue, String textValue, String dropdownTextFieldId) {
        try {
            // Added to prevent racing condition failure
            new WebDriverWait(driver, timeout).until(ExpectedConditions.visibilityOfElementLocated(By.id(dropdownTextFieldId)));
        } catch (Exception e) {
            if (FileReadWrite.loadConfigurationProperties().getProperty("disableExtentReportActiveListener").equalsIgnoreCase("Yes")) {
                System.out.println("Could not find element: " + dropdownTextFieldId);
                Assert.fail();
            } else {
                localTest.get().fail("Could not find element: " + dropdownTextFieldId);
                UtilityHelpers.adhocScreenCapture(driver, "Could not find element: " + dropdownTextFieldId, "Failure");
                Assert.fail();
            }
        }
        try {
            // TextHelpers value to dropdown text field
            WebElement textField = driver.findElement(By.id(dropdownTextFieldId));
            // Identify text value length and determines maximum value
            for (int textValueCount = 0; textValueCount < textValue.length(); textValueCount++) {

                // Place output value into an array for each character
                char individualCharacterTextValue = textValue.charAt(textValueCount);
                // Builds the individual characters into a full string
                String appendedTextValue = new StringBuilder().append(individualCharacterTextValue).toString();
                // Sends the appended text value string to the text field
                textField.sendKeys(appendedTextValue);
                // Sleep is used to allow for processing of dropdown options
                Thread.sleep(2000);
                // Identifies the text value currently within the text field
                String textFieldValue = textField.getAttribute("value");
                // Iterates through the dropdown and determines if the expected values should display
                try{
                    WebElement dropdownList = driver.findElement(By.tagName("twa-autocomplete-items"));
                    if (textValueCount > maximumCharacterValue - 1) {
                        // Identifies the dropdown values
                        String output = dropdownList.getAttribute("textContent");
                        // Verify the text field value does not display within the dropdown values
                        System.out.println(textFieldValue + " does NOT meet the maximum character count.");
                        assertThat(output).doesNotContain(textFieldValue);
                    } else {
                        // Identifies the dropdown values
                        String output = dropdownList.getAttribute("textContent");
                        // Verify the text field values are displayed within the dropdown values
                        assertThat(output).contains(textFieldValue);
                        System.out.println("Dropdown values found");
                    }
                }catch (Exception e) {
                    System.out.println("No options found");
                    Assert.fail();
                }

            }
        } catch (InterruptedException ie) {
            ie.printStackTrace();
            Assert.fail();
        }

        if (FileReadWrite.loadConfigurationProperties().getProperty("disableExtentReportActiveListener").equalsIgnoreCase("Yes")) {
        } else {
            if (localTest.get().getStatus() == Status.PASS) {
                localTest.get().pass("Validate the following element '" + dropdownTextFieldId + "' has maximum value of '" + maximumCharacterValue + "'");
            } else {
                localTest.get().fail("Validate the following element '" + dropdownTextFieldId + "' has maximum value of '" + maximumCharacterValue + "'");
                Assert.fail();
            }
        }
    }

    // Verify the maximum amount of characters before the dropdown is populated with values
    public static void byClassNameVerifyMaximumCharacterDropdownPopulation(WebDriver driver, int maximumCharacterValue, String textValue, String dropdownTextFieldClassName) {
        try {
            // Added to prevent racing condition failure
            new WebDriverWait(driver, timeout).until(ExpectedConditions.visibilityOfElementLocated(By.className(dropdownTextFieldClassName)));
        } catch (Exception e) {
            if (FileReadWrite.loadConfigurationProperties().getProperty("disableExtentReportActiveListener").equalsIgnoreCase("Yes")) {
                System.out.println("Could not find element: " + dropdownTextFieldClassName);
                Assert.fail();
            } else {
                localTest.get().fail("Could not find element: " + dropdownTextFieldClassName);
                UtilityHelpers.adhocScreenCapture(driver, "Could not find element: " + dropdownTextFieldClassName, "Failure");
                Assert.fail();
            }
        }
        try {
            // TextHelpers value to dropdown text field
            WebElement textField = driver.findElement(By.className(dropdownTextFieldClassName));
            // Identify text value length and determines maximum value
            for (int textValueCount = 0; textValueCount < textValue.length(); textValueCount++) {

                // Place output value into an array for each character
                char individualCharacterTextValue = textValue.charAt(textValueCount);
                // Builds the individual characters into a full string
                String appendedTextValue = new StringBuilder().append(individualCharacterTextValue).toString();
                // Sends the appended text value string to the text field
                textField.sendKeys(appendedTextValue);
                // Sleep is used to allow for processing of dropdown options
                Thread.sleep(2000);
                // Identifies the text value currently within the text field
                String textFieldValue = textField.getAttribute("value");
                // Iterates through the dropdown and determines if the expected values should display
                try{
                    WebElement dropdownList = driver.findElement(By.tagName("twa-autocomplete-items"));
                    if (textValueCount > maximumCharacterValue - 1) {
                        // Identifies the dropdown values
                        String output = dropdownList.getAttribute("textContent");
                        // Verify the text field value does not display within the dropdown values
                        System.out.println(textFieldValue + " does NOT meet the maximum character count.");
                        assertThat(output).doesNotContain(textFieldValue);
                    } else {
                        // Identifies the dropdown values
                        String output = dropdownList.getAttribute("textContent");
                        // Verify the text field values are displayed within the dropdown values
                        assertThat(output).contains(textFieldValue);
                        System.out.println("Dropdown values found");
                    }
                }catch (Exception e) {
                    System.out.println("No options found");
                    Assert.fail();
                }

            }
        } catch (InterruptedException ie) {
            ie.printStackTrace();
            Assert.fail();
        }

        if (FileReadWrite.loadConfigurationProperties().getProperty("disableExtentReportActiveListener").equalsIgnoreCase("Yes")) {
        } else {
            if (localTest.get().getStatus() == Status.PASS) {
                localTest.get().pass("Validate the following element '" + dropdownTextFieldClassName + "' has maximum value of '" + maximumCharacterValue + "'");
            } else {
                localTest.get().fail("Validate the following element '" + dropdownTextFieldClassName + "' has maximum value of '" + maximumCharacterValue + "'");
                Assert.fail();
            }
        }
    }

    // Assert the dropdown Starts with a specific value
    public static void byXpathDropdownOptionsStartWith(WebDriver driver, String optionValue, String dropdownTargetXpath ) {
        try {
            // Added to prevent racing condition failure
            new WebDriverWait(driver, timeout).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(dropdownTargetXpath)));
        } catch (Exception e) {
            if (FileReadWrite.loadConfigurationProperties().getProperty("disableExtentReportActiveListener").equalsIgnoreCase("Yes")) {
                System.out.println("Could not find element: " + dropdownTargetXpath);
                Assert.fail();
            } else {
                localTest.get().fail("Could not find element: " + dropdownTargetXpath);
                UtilityHelpers.adhocScreenCapture(driver, "Could not find element: " + dropdownTargetXpath, "Failure");
                Assert.fail();
            }
        }
        // Find the targeted text field that will contain input
        WebElement targetTextField = driver.findElement(By.xpath(dropdownTargetXpath));

        try {

            // Sends the XML data to the targeted text field
            targetTextField.sendKeys(optionValue);

            // Stores the dropdown values
            List<WebElement> dropdown = driver.findElements(By.tagName("material-select-dropdown-item"));

            // Loops through dropdown to record values and stores them in dropdownResults
            for (int i = 0; i < dropdown.size(); i++) {
                //Print the text
                String dropdownResults = dropdown.get(i).getText();
                System.out.println(dropdownResults);

                System.out.println(optionValue);
                // Assert the dropdownResults values start with the expected characters
                assertTrue(dropdownResults.startsWith(optionValue));
            }
        } catch (Exception e) {
            System.out.println("Unable to verify");
            Assert.fail();
        }

        if (FileReadWrite.loadConfigurationProperties().getProperty("disableExtentReportActiveListener").equalsIgnoreCase("Yes")) {
        } else {
            if (localTest.get().getStatus() == Status.PASS) {
                localTest.get().pass("Validating the dropdown values start with: " + optionValue);
            } else {
                localTest.get().fail("Validating the dropdown values start with: " + optionValue);
                Assert.fail();
            }
        }
    }

    // Assert the dropdown Starts with a specific value
    public static void byIdDropdownOptionsStartWith(WebDriver driver, String optionValue, String dropdownTargetId) {
        try {
            // Added to prevent racing condition failure
            new WebDriverWait(driver, timeout).until(ExpectedConditions.visibilityOfElementLocated(By.id(dropdownTargetId)));
        } catch (Exception e) {
            if (FileReadWrite.loadConfigurationProperties().getProperty("disableExtentReportActiveListener").equalsIgnoreCase("Yes")) {
                System.out.println("Could not find element: " + dropdownTargetId);
                Assert.fail();
            } else {
                localTest.get().fail("Could not find element: " + dropdownTargetId);
                UtilityHelpers.adhocScreenCapture(driver, "Could not find element: " + dropdownTargetId, "Failure");
                Assert.fail();
            }
        }
        // Find the targeted text field that will contain input
        WebElement targetTextField = driver.findElement(By.id(dropdownTargetId));

        try {
            // Clear the text field
            targetTextField.clear();
            // Sends the XML data to the targeted text field
            targetTextField.sendKeys(optionValue);

            // Stores the dropdown values
            List<WebElement> dropdown = driver.findElements(By.tagName("material-select-dropdown-item"));

            // Loops through dropdown to record values and stores them in dropdownResults
            for (int i = 0; i < dropdown.size(); i++) {
                //Print the text
                String dropdownResults = dropdown.get(i).getText();
                System.out.println(dropdownResults);

                System.out.println(optionValue);
                // Assert the dropdownResults values start with the expected characters
                assertTrue(dropdownResults.startsWith(optionValue));
            }
        } catch (Exception e) {
            System.out.println("Unable to verify");
            Assert.fail();
        }

        if (FileReadWrite.loadConfigurationProperties().getProperty("disableExtentReportActiveListener").equalsIgnoreCase("Yes")) {
        } else {
            if (localTest.get().getStatus() == Status.PASS) {
                localTest.get().pass("Validating the dropdown values start with: " + optionValue);
            } else {
                localTest.get().fail("Validating the dropdown values start with: " + optionValue);
                Assert.fail();
            }
        }

    }

    // Assert the dropdown options contain a specific value
    public static void byIdDropdownOptionsContain(WebDriver driver, String optionValue, String dropdownTargetId) {
        try {
            // Added to prevent racing condition failure
            new WebDriverWait(driver, timeout).until(ExpectedConditions.visibilityOfElementLocated(By.id(dropdownTargetId)));
        } catch (Exception e) {
            if (FileReadWrite.loadConfigurationProperties().getProperty("disableExtentReportActiveListener").equalsIgnoreCase("Yes")) {
                System.out.println("Could not find element: " + dropdownTargetId);
                Assert.fail();
            } else {
                localTest.get().fail("Could not find element: " + dropdownTargetId);
                UtilityHelpers.adhocScreenCapture(driver, "Could not find element: " + dropdownTargetId, "Failure");
                Assert.fail();
            }
        }
        // Define the selection list (items within the target text field)
        WebElement targetList = driver.findElement(By.id(dropdownTargetId));

        try {
            // Define the selection list (items within the target text field)

            List<WebElement> allTargetItems = targetList.findElements(By.tagName("material-select-dropdown-item"));
            for (int fieldItems = 0; fieldItems < allTargetItems.size(); fieldItems++) {
                //Store the text field text
                String allItemResults = allTargetItems.get(fieldItems).getText();

                // Asserts the Results contains Values (case insensitive)
                assertThat(allItemResults.toLowerCase().contains(optionValue.toLowerCase()));
                System.out.println("The expected value '" + optionValue + "' is within the dropdown option: " + allItemResults);
            }
        } catch (Exception e) {
            Assert.fail();
        }

        try {
            // Define the selection list (items within the target text field)
            List<WebElement> allTargetItems = targetList.findElements(By.tagName("material-select-item"));
            for (int fieldItems = 0; fieldItems < allTargetItems.size(); fieldItems++) {
                //Store the text field text
                String allItemResults = allTargetItems.get(fieldItems).getText();

                // Asserts the Results contains Values (case insensitive)
                assertThat(allItemResults.toLowerCase().contains(optionValue.toLowerCase()));
                System.out.println("The expected value '" + optionValue + "' is within the dropdown option: " + allItemResults);
            }
        } catch (Exception e) {
            Assert.fail();
        }

        if (FileReadWrite.loadConfigurationProperties().getProperty("disableExtentReportActiveListener").equalsIgnoreCase("Yes")) {
        } else {
            if (localTest.get().getStatus() == Status.PASS) {
                localTest.get().pass("Validating the dropdown values contains: " + optionValue);
            } else {
                localTest.get().fail("Validating the dropdown values contains: " + optionValue);
                Assert.fail();
            }
        }

    }

    // Assert the dropdown options contain a specific value
    public static void byXpathDropdownOptionsContain(WebDriver driver, String optionValue, String dropdownTargetXpath) {
        try {
            // Added to prevent racing condition failure
            new WebDriverWait(driver, timeout).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(dropdownTargetXpath)));
        } catch (Exception e) {
            if (FileReadWrite.loadConfigurationProperties().getProperty("disableExtentReportActiveListener").equalsIgnoreCase("Yes")) {
                System.out.println("Could not find element: " + dropdownTargetXpath);
                Assert.fail();
            } else {
                localTest.get().fail("Could not find element: " + dropdownTargetXpath);
                UtilityHelpers.adhocScreenCapture(driver, "Could not find element: " + dropdownTargetXpath, "Failure");
                Assert.fail();
            }
        }
        // Define the selection list (items within the target text field)
        WebElement targetList = driver.findElement(By.xpath(dropdownTargetXpath));

        try {
            // Define the selection list (items within the target text field)

            List<WebElement> allTargetItems = targetList.findElements(By.tagName("material-select-dropdown-item"));
            for (int fieldItems = 0; fieldItems < allTargetItems.size(); fieldItems++) {
                //Store the text field text
                String allItemResults = allTargetItems.get(fieldItems).getText();

                // Asserts the Results contains Values (case insensitive)
                assertThat(allItemResults.toLowerCase().contains(optionValue.toLowerCase()));
                System.out.println("The expected value '" + optionValue + "' is within the dropdown option: " + allItemResults);
            }
        } catch (Exception e) {
            Assert.fail();
        }

        try {
            // Define the selection list (items within the target text field)
            List<WebElement> allTargetItems = targetList.findElements(By.tagName("material-select-item"));
            for (int fieldItems = 0; fieldItems < allTargetItems.size(); fieldItems++) {
                //Store the text field text
                String allItemResults = allTargetItems.get(fieldItems).getText();

                // Asserts the Results contains Values (case insensitive)
                assertThat(allItemResults.toLowerCase().contains(optionValue.toLowerCase()));
                System.out.println("The expected value '" + optionValue + "' is within the dropdown option: " + allItemResults);
            }
        } catch (Exception e) {
            Assert.fail();
        }

        if (FileReadWrite.loadConfigurationProperties().getProperty("disableExtentReportActiveListener").equalsIgnoreCase("Yes")) {
        } else {
            if (localTest.get().getStatus() == Status.PASS) {
                localTest.get().pass("Validating the dropdown values contains: " + optionValue);
            } else {
                localTest.get().fail("Validating the dropdown values contains: " + optionValue);
                Assert.fail();
            }
        }

    }

    // Assert the dropdown options contain a specific value
    public static void dropdownOptionsContain(WebDriver driver, String optionValue) {
        try {
            // Added to prevent racing condition failure
            new WebDriverWait(driver, timeout).until(ExpectedConditions.visibilityOfElementLocated(By.tagName("twa-autocomplete-items")));
        } catch (Exception e) {
            if (FileReadWrite.loadConfigurationProperties().getProperty("disableExtentReportActiveListener").equalsIgnoreCase("Yes")) {
                System.out.println("Could not find dropdown options.");
                Assert.fail();
            } else {
                localTest.get().fail("Could not find dropdown options.");
                UtilityHelpers.adhocScreenCapture(driver, "Could not find dropdown options.", "Failure");
                Assert.fail();
            }
        }
        try {
            // Define the selection list (items within the target text field)
            WebElement targetList = driver.findElement(By.tagName("twa-autocomplete-items"));

            List<WebElement> allTargetItems = targetList.findElements(By.tagName("material-select-dropdown-item"));
            for (int fieldItems = 0; fieldItems < allTargetItems.size(); fieldItems++) {
                //Store the text field text
                String allItemResults = allTargetItems.get(fieldItems).getText();

                // Asserts the Results contains Values (case insensitive)
                assertThat(allItemResults.toLowerCase().contains(optionValue.toLowerCase()));
                System.out.println("The expected value '" + optionValue + "' is within the dropdown option: " + allItemResults);
            }
        } catch (Exception e) {
            Assert.fail();
        }

        try {
            // Define the selection list (items within the target text field)
            WebElement targetList = driver.findElement(By.tagName("material-list"));
            List<WebElement> allTargetItems = targetList.findElements(By.tagName("material-select-item"));
            for (int fieldItems = 0; fieldItems < allTargetItems.size(); fieldItems++) {
                //Store the text field text
                String allItemResults = allTargetItems.get(fieldItems).getText();

                // Asserts the Results contains Values (case insensitive)
                assertThat(allItemResults.toLowerCase().contains(optionValue.toLowerCase()));
                System.out.println("The expected value '" + optionValue + "' is within the dropdown option: " + allItemResults);
            }
        } catch (Exception e) {
            Assert.fail();
        }

        if (FileReadWrite.loadConfigurationProperties().getProperty("disableExtentReportActiveListener").equalsIgnoreCase("Yes")) {
        } else {
            if (localTest.get().getStatus() == Status.PASS) {
                localTest.get().pass("Validating the dropdown values contains: " + optionValue);
            } else {
                localTest.get().fail("Validating the dropdown values contains: " + optionValue);
                Assert.fail();
            }
        }
    }

    // Used in assertions to validate dropdown options match expected values
    public static String byXpathDropdownOptionAssertion(WebDriver driver, String dropdownXpath) {
        // WaitHelpers for data grid to display
        try {
            // Added to prevent racing condition failure
            new WebDriverWait(driver, timeout).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(dropdownXpath)));
        } catch (Exception e) {
            if (FileReadWrite.loadConfigurationProperties().getProperty("disableExtentReportActiveListener").equalsIgnoreCase("Yes")) {
                System.out.println("Could not find element: " + dropdownXpath);
                Assert.fail();
            } else {
                localTest.get().fail("Could not find element: " + dropdownXpath);
                UtilityHelpers.adhocScreenCapture(driver, "Could not find element: " + dropdownXpath, "Failure");
                Assert.fail();
            }
        }
        // Finds grid to extract data
        WebElement mytable = driver.findElement(By.xpath(dropdownXpath));
        //To locate rows of table.
        List<WebElement> rows_table = mytable.findElements(By.tagName("tw-menuitem"));
        System.out.println("Number of rows: " + rows_table.size());
        //Loop will execute till the last row of table.
        String allItemResults = "";
        for (int fieldItems = 0; fieldItems < rows_table.size(); fieldItems++) {
            //Store the results
            String tempItemResults = allItemResults + '\n';
            allItemResults = tempItemResults + (rows_table.get(fieldItems).getAttribute("label"));
        }
        // Get all text within the table
        String results = mytable.getText();
        System.out.println("Table results" + results);
        System.out.println("Results" + allItemResults);
        // Make text public so it can be compared against
        return allItemResults;

    }
}
