package org.va.base;

import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import org.testng.ITestResult;
import org.testng.annotations.*;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.time.Duration;

public class BaseTest {

    public static final String TEST_RESOURCES_DIR = "src\\test\\resources\\";
    public static final String DOWNLOAD_DIR = TEST_RESOURCES_DIR.concat("download\\");
    public static final String SCREENSHOTS_DIR = TEST_RESOURCES_DIR.concat("screenshots\\");
    public static final String REPORTS_DIR = TEST_RESOURCES_DIR.concat("reports\\");
    public static final String UPLOAD_DIR = TEST_RESOURCES_DIR.concat("upload\\");

    protected WebDriver driver;
    protected Logger log;

    @BeforeSuite
    protected final void setupTestSuite() throws IOException {
        cleanDirectory(REPORTS_DIR);
        cleanDirectory(SCREENSHOTS_DIR);
    }

    @Parameters({ "browser" })
    @BeforeMethod(alwaysRun = true)
    public void setUp(@Optional("chrome") String browser,  Method method) {
        String tafName = "GUI TAF";

        log = LogManager.getLogger(tafName);
        log.info(" ==== TEST CASE NAME : "+ method.getName() +" ====");
        BrowserDriverFactory factory = new BrowserDriverFactory(browser, log);
        driver = factory.createDriver();

        driver.manage().window().maximize();

        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(15));
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(7));
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown(ITestResult testResult) throws IOException {
        takeScreenshot(testResult);
        log.info("Closing driver");
        driver.quit();
    }

    @AfterSuite
    public void cleanFiles() throws IOException {
        cleanDirectory(DOWNLOAD_DIR);
    };

    private void takeScreenshot(ITestResult testResult) throws IOException {
        if (ITestResult.FAILURE == testResult.getStatus()) {
            TakesScreenshot takesScreenshot = (TakesScreenshot) driver;
            File screenshot = takesScreenshot.getScreenshotAs(OutputType.FILE);
            String testName = testResult.getName();

            FileUtils.copyFile(screenshot, new File(SCREENSHOTS_DIR.concat(testName).concat(".jpg")));
        };
    };

    private void cleanDirectory(String directoryPath) throws IOException {
        System.out.println("____________________________________________________________");
        File directory = new File(directoryPath);

        //If directory is not shown after git clone of the repo this code will build the path
        if (!directory.exists()) {
            FileUtils.forceMkdir(directory);
            System.out.println("Created folder with path: "+ directoryPath);
        };

        System.out.println("Deleting not needed files from folder with path: "+ directoryPath);
        FileUtils.cleanDirectory(directory);
        String[] fileList = directory.list();
        if (fileList != null && fileList.length == 0) {
            System.out.printf("All file are deleted in Directory: %s%n", directoryPath);
        } else {
            System.out.printf("Unable to delete the files in Directory: %s%n", directoryPath);
        };
        System.out.println("____________________________________________________________");
    };
}
