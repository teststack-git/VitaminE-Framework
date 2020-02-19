# VitaminE Framework

Welcome to the VitaminE framework!  VitaminE is a robust test automation framework that utilizes Selenium to rapidly build out test scripts.  VitaminE provides all necessary libraries, configurations, and test helpers allowing you to focus more time on deliverying your software.

## The Dependency

### Maven:
```
<dependencies>
    <dependency>
        <groupId>com.vitamineframework</groupId>
        <artifactId>CommunityEdition</artifactId>
        <version>1.0.0</version>
    </dependency>
</dependencies>
```

### Gradle
```
dependencies {
    compile group: 'com.vitamineframework', name: 'CommunityEdition', version: '1.0.0'
}
```

## The Setup

### Download your drivers
  * [ChromeDriver](https://chromedriver.chromium.org/) - Driver used to interact with Chrome browser
  * [GeckoDriver](https://github.com/mozilla/geckodriver/releases) - Driver used to interact with Firefox browser
  * [Microsoft Edge](https://developer.microsoft.com/en-us/microsoft-edge/tools/webdriver/#downloads) - Driver used to interact with Microsoft Edge browser

### Libraries
Data and Object libraries allow your tests to remain readable and provide reuse.
  * The object library houses page objects and provides the ability to centralize your objects.
  * The data library can hold various data sets.  It is recommended that you use one library per test environment.  Tests can also use more dynamic data such as POJOs, hash maps, Java Beans, etc.
These libraries are optional, however highly recommended!

### Configuration file
The configuration file controls the execution of the test.  This file contains numerous options that allows you set differing executions.  The following options are available:
 * url - Sets the URL that your tests will run against.
 * browserType - Sets the browser (Chrome, Firefox, IE, Edge, Safari).
 * environment - Set the environment for your data library.
 * platform - Set the operating system that you are running on.
 * aut (Application Under Test) - This is used with reporting to set a project title.
 * timeout - Sets the timeout limit for individual actions.
 * chromeMaximized - Maximizes the Chrome browser.
 * chromeFullScreen - Fullscreen the Chrome browser.
 * turnOffExtentReport - Turns the reporting feature on/off.  Set this to 'No' to enable report and 'Yes' to turn off.  YOU MUST TURN OFF TO RUN SCRIPTS INDIVIDUALLY!
 * extentReportsResultsFolderLocation - Sets the location where your report will be placed.
 * fileDownloadFolderLocation - Sets the location where your files will be downloaded.
 * testType - Set the type of test your are executing.  This is for reporting purposes.
 * appendToLastReport - This option appends your results to the last generated report.
 * enableHeadlessMode - Runs your tests in headless (browserless) mode.

### Helpers
Helpers are the interactions used to create scripts.

### Reporting
Your test execution results will be uploaded to a results folder if you have defined a results folder within the 'extentReportsResultsFolderLocation' property and
'disableExtentReportActiveListener' set to "No".