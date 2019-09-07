package ExtentReport;

import Core.Configuration;
import Core.FileReadWrite;

import org.openqa.selenium.BuildInfo;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ExtentManager extends Configuration {
    
    static ExtentReports extent;

    // Gets system level information for ExtentReports
    private static String USER_OS = System.getProperty("os.name");
    private static String User_OS_Version = System.getProperty("os.version");
    private static String User_Name = System.getProperty("user.name");
    private static final String Java_Version = System.getProperty("java.version");


    public static ExtentReports getInstance() {
        return extent;
    }

    // Writes browser info to ExtentReports
    public static void browserInfo() {
        if(FileReadWrite.loadConfigurationProperties().getProperty("browserType").equalsIgnoreCase("firefox")) {
            extent.setSystemInfo("Browser Name", FileReadWrite.loadConfigurationProperties().getProperty("browserType"));
            extent.setSystemInfo("Browser Version", FileReadWrite.loadConfigurationProperties().getProperty("browserVersionFF"));

        } else if (FileReadWrite.loadConfigurationProperties().getProperty("browserType").equalsIgnoreCase("chrome")) {
            extent.setSystemInfo("Browser Name", FileReadWrite.loadConfigurationProperties().getProperty("browserType"));
            extent.setSystemInfo("Browser Version", FileReadWrite.loadConfigurationProperties().getProperty("browserVersionChrome"));
        }
        else if (FileReadWrite.loadConfigurationProperties().getProperty("browserType").equalsIgnoreCase("safari")) {
            extent.setSystemInfo("Browser Name", FileReadWrite.loadConfigurationProperties().getProperty("browserType"));
            extent.setSystemInfo("Browser Version", FileReadWrite.loadConfigurationProperties().getProperty("browserVersionSafari"));
        }
        else if (FileReadWrite.loadConfigurationProperties().getProperty("browserType").equalsIgnoreCase("edge")) {
            extent.setSystemInfo("Browser Name", FileReadWrite.loadConfigurationProperties().getProperty("browserType"));
            extent.setSystemInfo("Browser Version", FileReadWrite.loadConfigurationProperties().getProperty("browserVersionEdge"));
        }
        else if (FileReadWrite.loadConfigurationProperties().getProperty("browserType").equalsIgnoreCase("ie")){
            extent.setSystemInfo("Browser Name", FileReadWrite.loadConfigurationProperties().getProperty("browserType"));
            extent.setSystemInfo("Browser Version", FileReadWrite.loadConfigurationProperties().getProperty("browserVersionIE"));
        }
    }

    public static ExtentReports createInstance(String fileName) {

        // Define ExtentReport type
        ExtentHtmlReporter extentHtmlReporter = new ExtentHtmlReporter(fileName);

        // Sets ExtentReport themes
        extentHtmlReporter.config().setTheme(Theme.STANDARD);
        extentHtmlReporter.config().setDocumentTitle(FileReadWrite.loadConfigurationProperties().getProperty("aut") + " Results");
        extentHtmlReporter.config().setDocumentTitle(fileName);
        extentHtmlReporter.config().setEncoding("utf-8");
        extentHtmlReporter.config().setReportName(fileName);
        extentHtmlReporter.config().setTimeStampFormat("MM-dd-yyyy");

        // Write ExtentReport execution parameters
        extent = new ExtentReports();
        extent.attachReporter(extentHtmlReporter);
        extent.setSystemInfo("User", User_Name);
        extent.setSystemInfo("Test Environment", FileReadWrite.loadConfigurationProperties().getProperty("environment"));
        extent.setSystemInfo("OS", USER_OS);
        extent.setSystemInfo("OS Version", User_OS_Version);
        browserInfo();
        extent.setSystemInfo("Selenium Version", new BuildInfo().getReleaseLabel());
        extent.setSystemInfo("Java Version", Java_Version);
        return extent;

    }
}
