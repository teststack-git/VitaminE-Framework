package ExtentReport;

import Functions.TimeDatesHelpers.TimeDateHelpers;
import Core.FileReadWrite;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.ExtentReports;

import com.aventstack.extentreports.Status;
import org.testng.ITestResult;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.Reporter;


public class ExtentReportBuilder implements ITestListener{

    public static ExtentTest test;
    public static ITestResult testResult;

    public static final String USER_DIRECTORY = System.getProperty("user.dir");
    public static final String USER_OS = System.getProperty("os.name");
    public static String AUT = FileReadWrite.loadConfigurationProperties().getProperty("aut");
    public static String testType = FileReadWrite.loadConfigurationProperties().getProperty("testType");

    public static ThreadLocal<ExtentTest> parentTest = new ThreadLocal<ExtentTest>();
    public static ThreadLocal<ExtentTest> localTest = new ThreadLocal<ExtentTest>();

    public static String ExtentOS() {
        if(FileReadWrite.loadConfigurationProperties().getProperty("appendToLastReport").equalsIgnoreCase("yes")) {
            String RESULTS_FOLDER = FileReadWrite.loadConfigurationProperties().getProperty("extentReportsResultsFolderLocation")+AUT+"-"+testType+"-"+ TimeDateHelpers.displayCurrentDateTime("MM-dd-yyyy")+".html";
            return RESULTS_FOLDER;
        } else {
            String RESULTS_FOLDER = FileReadWrite.loadConfigurationProperties().getProperty("extentReportsResultsFolderLocation")+AUT+"-"+testType+"-"+ TimeDateHelpers.displayCurrentDateTime("MM-dd-yyyy_HH-mm-ss")+".html";
            return RESULTS_FOLDER;
        }
    }

    public static ExtentReports extent = ExtentManager.createInstance(ExtentOS());

    @Override
    public synchronized void onStart(ITestContext context) {
        ExtentTest parent = extent.createTest(context.getName());
        parentTest.set(parent);


    }

    @Override
    public synchronized void onFinish(ITestContext context) {
        extent.flush();
    }

    @Override
    public synchronized void onTestStart(ITestResult result) {
        ExtentTest child = parentTest.get().createNode(result.getMethod().getMethodName());
        localTest.set(child);

    }

    @Override
    public synchronized void onTestSuccess(ITestResult result) {

        if (localTest.get().getStatus() == Status.PASS) {
            localTest.get().log(Status.PASS, "Test Passed.");
            result.setStatus(ITestResult.SUCCESS);
            Reporter.setCurrentTestResult(result);
        } else if (localTest.get().getStatus() == Status.FAIL) {
            localTest.get().log(Status.FAIL, "Test Failed.");
            result.setStatus(ITestResult.FAILURE);
            Reporter.setCurrentTestResult(result);
        } else {
            localTest.get().log(Status.WARNING, "Warning.");
            result.setStatus(ITestResult.FAILURE);
            Reporter.setCurrentTestResult(result);
        }

    }

    // Points to image within report
    @Override
    public void onTestFailure(ITestResult result) {

    }


    @Override
    public synchronized void onTestSkipped(ITestResult result) {
        localTest.get().skip(result.getThrowable());
    }

    @Override
    public synchronized void onTestFailedButWithinSuccessPercentage(ITestResult result) {

    }

}
