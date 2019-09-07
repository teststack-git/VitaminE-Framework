package Functions.PopUpHelpers;

import ExtentReport.ExtentReportBuilder;

import Core.FileReadWrite;
import Core.Configuration;

import Functions.UtilityHelpers.UtilityHelpers;
import org.junit.Assert;
import org.openqa.selenium.*;



/**
 * This class allows for the interaction with pop ups
 * Uses ObjectLibrary.xml for object references
 */


public class PopUpActionsHelpers extends ExtentReportBuilder {

    public static void acceptAlertPopup(WebDriver driver) {
        try {
            Alert alert = driver.switchTo().alert();
            alert.accept();
        }catch (UnhandledAlertException uae) {
            if (FileReadWrite.loadConfigurationProperties().getProperty("disableExtentReportActiveListener").equalsIgnoreCase("Yes")) {
                System.out.println("Could not find element alert");
                Assert.fail();
            } else {
                localTest.get().fail("Could not find element alert");
                UtilityHelpers.adhocScreenCapture(driver, "Could not find element alert", "Failure");
                Assert.fail();
            }
        }
        if (FileReadWrite.loadConfigurationProperties().getProperty("disableExtentReportActiveListener").equalsIgnoreCase("Yes")) {
        System.out.println("Accepted popup");
        } else {
            localTest.get().info("Accepted popup");
        }
    }



}
