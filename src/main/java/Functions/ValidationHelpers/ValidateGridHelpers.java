package Functions.ValidationHelpers;

import ExtentReport.ExtentReportBuilder;

import Core.Configuration;
import Core.FileReadWrite;

import Functions.UtilityHelpers.UtilityHelpers;
import com.google.common.collect.Iterables;

import org.junit.Assert;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;


/**
 * This class provides the ability to get text from tables and compare it to expected results
 * Uses ObjectLibrary.xml for object references
 * Uses Datalibrary.xml for data references
 */


public class ValidateGridHelpers extends ExtentReportBuilder {

    static String configTimeout = FileReadWrite.loadConfigurationProperties().getProperty("timeout");
    static int timeout = Integer.parseInt(configTimeout);



    // Extracts data from specified table and validates specified user data
    public static void byXpathGridDataExtractCompare(WebDriver driver, String gridXpath, String expectedData) {
        try {
            // Finds grid to extract data
            WebElement grid = driver.findElement(By.xpath(gridXpath));

            // Identifies all data within the grid
            String results = grid.getText();

            // Verifies the expected data displays within the results
            System.out.println(expectedData + '\n');
            System.out.println(results + '\n');


            if (FileReadWrite.loadConfigurationProperties().getProperty("disableExtentReportActiveListener").equalsIgnoreCase("Yes")) {
                if (results.contains(expectedData)) {
                    assertThat(results.contains(expectedData));
                } else if (!results.contains(expectedData)) {
                    assertThat(results.contains(expectedData));
                }
            } else {

                if (results.contains(expectedData)) {
                    System.out.println("Test passed");
                    localTest.get().pass("The following data was found: " + expectedData);
                } else if (!results.contains(expectedData)) {
                    System.out.println("Test Warn");
                    localTest.get().warning("The following data was NOT found: " + expectedData);
                    UtilityHelpers.adhocScreenCapture(driver, "Data was not found.", "Warning");
                    Assert.fail();
                }
            }
        } catch (TimeoutException te) {
            te.printStackTrace();
            if (FileReadWrite.loadConfigurationProperties().getProperty("disableExtentReportActiveListener").equalsIgnoreCase("Yes")) {
                System.out.println("Could not find element: " + gridXpath);
                Assert.fail();
            } else {
                localTest.get().fail("Could not find element: " + gridXpath);
                UtilityHelpers.adhocScreenCapture(driver, "Element not found.", "Failure");
                Assert.fail();
            }
        }
    }

    // Verify the column values display in ascending order
    public static void byIdValidateSortOrderAscending(WebDriver driver, String targetId) {
        try {
            // Added to prevent racing condition failure
            new WebDriverWait(driver, timeout).until(ExpectedConditions.presenceOfElementLocated(By.id(targetId)));
        } catch (Exception e) {
            if (FileReadWrite.loadConfigurationProperties().getProperty("disableExtentReportActiveListener").equalsIgnoreCase("Yes")) {
                System.out.println("Could not find element: " + targetId);
                Assert.fail();
            } else {
                localTest.get().fail("Could not find element: " + targetId);
                Assert.fail();
            }
        }

        // Target the column
        WebElement targetTable = driver.findElement(By.id(targetId));

        ArrayList<String> columnValueList = new ArrayList<>();
        List<WebElement> sortList= targetTable.findElements(By.tagName("tr"));

        for(WebElement rowValues:sortList){
            columnValueList.add(rowValues.getText().trim());
        }

        ArrayList<String> sortedList = new ArrayList<>();

        for(String columnValues: Iterables.skip(columnValueList, 1)){
            sortedList.add(columnValues + '\n');
        }
        while (sortedList.remove(""));
        ArrayList<String> actualColumnValueList = new ArrayList<>();
        List<WebElement> actualSortedList = targetTable.findElements(By.tagName("tr"));

        for(WebElement rowValues:actualSortedList){
            actualColumnValueList.add(rowValues.getAttribute("textContent").trim());
        }

        ArrayList<String> actualList = new ArrayList<>();

        for(String actualColumnValues:Iterables.skip(actualColumnValueList, 1)){
            actualList.add(actualColumnValues + '\n');
        }
        while (actualList.remove(""));
        Collections.sort(sortedList);
        System.out.println("Sorted List" + sortedList);
        System.out.println("Column Value" + actualList);
        if (FileReadWrite.loadConfigurationProperties().getProperty("disableExtentReportActiveListener").equalsIgnoreCase("Yes")) {
            try {
                if (sortedList.equals(actualList)) {
                    assertThat(sortedList.equals(actualList));
                } else {
                    assertThat(sortedList.equals(actualList));
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            try {
                if (sortedList.equals(actualList)) {
                    localTest.get().pass("The column displays in ascending order.");
                } else {
                    localTest.get().warning("The column does not display in ascending order.");
                    UtilityHelpers.adhocScreenCapture(driver, "Column is not in ascending order.", "Warning");
                    Assert.fail();
                }
            } catch (Exception e) {
                if (FileReadWrite.loadConfigurationProperties().getProperty("disableExtentReportActiveListener").equalsIgnoreCase("Yes")) {
                    System.out.println("Columns not found: " + targetId);
                    Assert.fail();
                } else {
                    localTest.get().fail("Columns not found: " + targetId);
                    UtilityHelpers.adhocScreenCapture(driver, "Field not found.", "Failure");
                    Assert.fail();
                }
            }
        }

    }

    // Verify the column values display in ascending order
    public static void byXpathValidateSortOrderAscending(WebDriver driver, String targetXpath) {
        try {
            // Added to prevent racing condition failure
            new WebDriverWait(driver, timeout).until(ExpectedConditions.presenceOfElementLocated(By.xpath(targetXpath)));
        } catch (Exception e) {
            if (FileReadWrite.loadConfigurationProperties().getProperty("disableExtentReportActiveListener").equalsIgnoreCase("Yes")) {
                System.out.println("Could not find element: " + targetXpath);
                Assert.fail();
            } else {
                localTest.get().fail("Could not find element: " + targetXpath);
                Assert.fail();
            }
        }
        // Target the column
        WebElement targetTable = driver.findElement(By.xpath(targetXpath));

        ArrayList<String> columnValueList = new ArrayList<>();
        List<WebElement> sortList= targetTable.findElements(By.tagName("tr"));

        for(WebElement rowValues:sortList){
            columnValueList.add(rowValues.getText().trim());
        }

        ArrayList<String> sortedList = new ArrayList<>();

        for(String columnValues:Iterables.skip(columnValueList, 1)){
            sortedList.add(columnValues + '\n');
        }
        while (sortedList.remove(""));
        ArrayList<String> actualColumnValueList = new ArrayList<>();
        List<WebElement> actualSortedList = targetTable.findElements(By.tagName("tr"));

        for(WebElement rowValues:actualSortedList){
            actualColumnValueList.add(rowValues.getAttribute("textContent").trim());
        }

        ArrayList<String> actualList = new ArrayList<>();

        for(String actualColumnValues:Iterables.skip(actualColumnValueList, 1)){
            actualList.add(actualColumnValues + '\n');
        }
        while (actualList.remove(""));
        Collections.sort(sortedList);
        System.out.println("Sorted List" + sortedList);
        System.out.println("Column Value" + actualList);

        if (FileReadWrite.loadConfigurationProperties().getProperty("disableExtentReportActiveListener").equalsIgnoreCase("Yes")) {
            try {
                if (sortedList.equals(actualList)) {
                    assertThat(sortedList.equals(actualList));
                } else {
                    assertThat(sortedList.equals(actualList));
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            try {
                if (sortedList.equals(actualList)) {
                    localTest.get().pass("The column displays in ascending order.");
                } else {
                    localTest.get().warning("The column does not display in ascending order.");
                    UtilityHelpers.adhocScreenCapture(driver, "Column is not in ascending order.", "Warning");
                    Assert.fail();
                }
            } catch (Exception e) {
                if (FileReadWrite.loadConfigurationProperties().getProperty("disableExtentReportActiveListener").equalsIgnoreCase("Yes")) {
                    System.out.println("Columns not found: " + targetXpath);
                    Assert.fail();
                } else {
                    localTest.get().fail("Columns not found: " + targetXpath);
                    UtilityHelpers.adhocScreenCapture(driver, "Field not found.", "Failure");
                    Assert.fail();
                }
            }
        }
    }

    // Verify the column values display in descending order
    public static void byIdValidateSortOrderDescending(WebDriver driver, String targetId) {
        try {
            // Added to prevent racing condition failure
            new WebDriverWait(driver, timeout).until(ExpectedConditions.presenceOfElementLocated(By.id(targetId)));
        } catch (Exception e) {
            if (FileReadWrite.loadConfigurationProperties().getProperty("disableExtentReportActiveListener").equalsIgnoreCase("Yes")) {
                System.out.println("Could not find element: " + targetId);
                Assert.fail();
            } else {
                localTest.get().fail("Could not find element: " + targetId);
                Assert.fail();
            }
        }
        WebElement targetTable = driver.findElement(By.id(targetId));

        ArrayList<String> columnValueList = new ArrayList<>();
        List<WebElement> sortList= targetTable.findElements(By.tagName("tr"));

        for(WebElement rowValues:sortList){
            columnValueList.add(rowValues.getText().trim());
        }

        ArrayList<String> sortedList = new ArrayList<>();

        for(String columnValues:Iterables.skip(columnValueList, 1)){
            sortedList.add(columnValues + '\n');
        }
        while (sortedList.remove(""));
        ArrayList<String> actualColumnValueList = new ArrayList<>();
        List<WebElement> actualSortedList = targetTable.findElements(By.tagName("tr"));

        for(WebElement rowValues:actualSortedList){
            actualColumnValueList.add(rowValues.getAttribute("textContent").trim());
        }

        ArrayList<String> actualList = new ArrayList<>();

        for(String actualColumnValues:Iterables.skip(actualColumnValueList, 1)){
            actualList.add(actualColumnValues + '\n');
        }
        while (actualList.remove(""));
        Collections.sort(sortedList);
        Collections.reverse(sortedList);
        System.out.println("Sorted List" + sortedList);
        System.out.println("Column Value" + actualList);
        if (FileReadWrite.loadConfigurationProperties().getProperty("disableExtentReportActiveListener").equalsIgnoreCase("Yes")) {
            try {
                if (sortedList.equals(actualList)) {
                    assertThat(sortedList.equals(actualList));
                } else {
                    assertThat(sortedList.equals(actualList));
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            try {
                if (sortedList.equals(actualList)) {
                    localTest.get().pass("The column displays in descending order.");
                } else {
                    localTest.get().warning("The column does not display in descending order.");
                    UtilityHelpers.adhocScreenCapture(driver, "Column is not in descending order.", "Warning");
                    Assert.fail();
                }
            } catch (Exception e) {
                if (FileReadWrite.loadConfigurationProperties().getProperty("disableExtentReportActiveListener").equalsIgnoreCase("Yes")) {
                    System.out.println("Columns not found: " + targetId);
                    Assert.fail();
                } else {
                    localTest.get().fail("Columns not found: " + targetId);
                    UtilityHelpers.adhocScreenCapture(driver, "Field not found.", "Failure");
                    Assert.fail();
                }
            }
        }

    }

    // Verify the column values display in descending order
    public static void byXpathValidateSortOrderDescending(WebDriver driver, String targetXpath) {
        try {
            // Added to prevent racing condition failure
            new WebDriverWait(driver, timeout).until(ExpectedConditions.presenceOfElementLocated(By.xpath(targetXpath)));
        } catch (Exception e) {
            if (FileReadWrite.loadConfigurationProperties().getProperty("disableExtentReportActiveListener").equalsIgnoreCase("Yes")) {
                System.out.println("Could not find element: " + targetXpath);
                Assert.fail();
            } else {
                localTest.get().fail("Could not find element: " + targetXpath);
                Assert.fail();
            }
        }
        WebElement targetTable = driver.findElement(By.xpath(targetXpath));

        ArrayList<String> columnValueList = new ArrayList<>();
        List<WebElement> sortList= targetTable.findElements(By.tagName("tr"));

        for(WebElement rowValues:sortList){
            columnValueList.add(rowValues.getText().trim());
        }

        ArrayList<String> sortedList = new ArrayList<>();

        for(String columnValues:Iterables.skip(columnValueList, 1)){
            sortedList.add(columnValues + '\n');
        }
        while (sortedList.remove(""));
        ArrayList<String> actualColumnValueList = new ArrayList<>();
        List<WebElement> actualSortedList = targetTable.findElements(By.tagName("tr"));

        for(WebElement rowValues:actualSortedList){
            actualColumnValueList.add(rowValues.getAttribute("textContent").trim());
        }

        ArrayList<String> actualList = new ArrayList<>();

        for(String actualColumnValues:Iterables.skip(actualColumnValueList, 1)){
            actualList.add(actualColumnValues + '\n');
        }
        while (actualList.remove(""));
        Collections.sort(sortedList);
        Collections.reverse(sortedList);
        System.out.println("Sorted List" + sortedList);
        System.out.println("Column Value" + actualList);
        if (FileReadWrite.loadConfigurationProperties().getProperty("disableExtentReportActiveListener").equalsIgnoreCase("Yes")) {
            try {
                if (sortedList.equals(actualList)) {
                    assertThat(sortedList.equals(actualList));
                } else {
                    assertThat(sortedList.equals(actualList));
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            try {
                if (sortedList.equals(actualList)) {
                    localTest.get().pass("The column displays in descending order.");
                } else {
                    localTest.get().warning("The column does not display in descending order.");
                    UtilityHelpers.adhocScreenCapture(driver, "Column is not in descending order.", "Warning");
                    Assert.fail();
                }
            } catch (Exception e) {
                if (FileReadWrite.loadConfigurationProperties().getProperty("disableExtentReportActiveListener").equalsIgnoreCase("Yes")) {
                    System.out.println("Columns not found: " + targetXpath);
                    Assert.fail();
                } else {
                    localTest.get().fail("Columns not found: " + targetXpath);
                    UtilityHelpers.adhocScreenCapture(driver, "Field not found.", "Failure");
                    Assert.fail();
                }
            }
        }
    }
}
