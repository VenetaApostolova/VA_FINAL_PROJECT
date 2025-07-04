package org.va.POM;

import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.NoSuchElementException;

public abstract class BasePage {

    public static final String BASE_URL = "http://training.skillo-bg.com:4300";

    WebDriver driver;
    WebDriverWait wait;
    Logger log;

    public BasePage(WebDriver driver, Logger log){
        this.driver = driver;
        this.log = log;
        wait = new WebDriverWait(driver, Duration.ofSeconds(7));
    }

    public void navigateTo(String pageURLSUFIX) {
        String urlToBeLoaded = BASE_URL + pageURLSUFIX;
        driver.get(urlToBeLoaded);
        log.info("# CONFIRM: The user has navigated to " + urlToBeLoaded);
    }

    public void clickOn(WebElement element) {
        wait.until(ExpectedConditions.visibilityOf(element));
        wait.until(ExpectedConditions.elementToBeClickable(element));
        element.click();
        log.info("The user has clicked on " + element);
    }

    public void typeTextIn(WebElement elm, String txt){
        wait.until(ExpectedConditions.visibilityOf(elm));
        elm.clear();
        elm.sendKeys(txt);
        log.info("# CONFIRM: The user has provided in " + elm + " the text: " + txt);
    }
    public void waitForVisibility(By locator) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    public String getElementText(WebElement element){
        wait.until(ExpectedConditions.visibilityOf(element));
        String elementText = element.getText();
        log.info("# CONFIRM THE WEB ELEMENT TEXT IS: " + elementText);
        return elementText;
    }

    public String getElementAttributeValue(WebElement element){
        wait.until(ExpectedConditions.visibilityOf(element));
        String elementText = element.getAttribute("value");
        log.info("# CONFIRM THE WEB ELEMENT ATTRIBUTE VALUE IS: " + elementText);
        return elementText;
    }

    public String getElementPlaceholderValue(WebElement element){
        wait.until(ExpectedConditions.visibilityOf(element));
        String elementText = element.getAttribute("placeholder");
        log.info("# CONFIRM THE WEB ELEMENT PLACEHOLDER VALUE IS: " + elementText);
        return elementText;
    }

    // VERIFICATIONS - BOOLEANS

    public boolean isElementPresented(WebElement element){
        boolean isShown = false;
        String locatorInfo = locatorInfo(element);
        log.info("@ ACTION: Checking visibility of element: " + locatorInfo);
        try {
            wait.until(ExpectedConditions.visibilityOf(element));
            isShown = true;
            log.info("# CONFIRM: The element is visible: " + locatorInfo);
        } catch (TimeoutException | NoSuchElementException e) {
            log.error("* ERROR: Element not visible: " + locatorInfo);
        }
        return isShown;
    }

    public boolean isElementClickable(WebElement element){
        boolean isClickable = false;
        String locatorInfo = locatorInfo(element);
        log.info("@ ACTION: Checking if element is clickable: " + locatorInfo);
        try {
            wait.until(ExpectedConditions.elementToBeClickable(element));
            isClickable = true;
            log.info("# CONFIRM: The element is clickable: " + locatorInfo);
        } catch (TimeoutException e) {
            log.error("! ERROR: Element not clickable: " + locatorInfo);
        }
        return isClickable;
    }

    private String locatorInfo(WebElement elm){
        try {
            String[] rawInfo = elm.toString().split("->");
            if (rawInfo.length < 2) return elm.toString();
            String[] locatorParts = rawInfo[1].split(":");
            String strategy = locatorParts[0].trim();
            String value = locatorParts[1].replace("]", "").trim();
            return "LOCATOR STRATEGY: " + strategy.toUpperCase() + " | VALUE: " + value;
        } catch (Exception e) {
            return "Unable to parse element info.";
        }
    }
}
