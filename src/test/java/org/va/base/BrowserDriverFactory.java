package org.va.base;

import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.va.base.BaseTest.DOWNLOAD_DIR;

public class BrowserDriverFactory {

    private ThreadLocal<WebDriver> driver = new ThreadLocal<WebDriver>();
    private String browser;
    private Logger log;

    public BrowserDriverFactory(String browser, Logger log) {
        this.browser = browser.toLowerCase();
        this.log = log;
    }

    public WebDriver createDriver() {
        // Create driver
        log.info("Create driver: " + browser);

        switch (browser) {
            case "chrome":
                driver.set(new ChromeDriver(configChromeOptions()));
                break;

            case "firefox":
                driver.set(new FirefoxDriver());
                break;

            default:
                System.out.println("Do not know how to start: " + browser + ", starting chrome.");
                driver.set(new ChromeDriver(configChromeOptions()));
                break;
        }

        return driver.get();
    }

    private ChromeOptions configChromeOptions() {
        //Create path and setting for download folder
        Map<String, Object> prefs = new HashMap<>();
        prefs.put("download.default_directory", System.getProperty("user.dir").concat("\\").concat(DOWNLOAD_DIR));
        ChromeOptions chromeOptions = new ChromeOptions();

        chromeOptions.setExperimentalOption("prefs", prefs);
        chromeOptions.setExperimentalOption("excludeSwitches", List.of("disable-popup-blocking"));

        chromeOptions.addArguments("incognito");
        chromeOptions.addArguments("disable-popup-blocking");
        chromeOptions.addArguments("--ignore-certificate-errors",
                                    "--allow-running-insecure-content",
                                    "--allow-insecure-localhost",
                                    "--no-sandbox",
                                    "--disable-gpu");
        chromeOptions.addArguments("--unsafely-treat-insecure-origin-as-secure=http://training.skillo-bg.com:4300/");
        chromeOptions.setAcceptInsecureCerts(true);

        return chromeOptions;
    }
}