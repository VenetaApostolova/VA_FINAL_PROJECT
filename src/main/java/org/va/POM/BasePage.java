package org.va.POM;

import org.apache.logging.log4j.Logger;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.NoSuchElementException;

public abstract class BasePage {

    public static final String BASE_URL = "http://training.skillo-bg.com:4300";

    protected WebDriver driver;
    protected WebDriverWait wait;
    protected Logger log;

    public BasePage(WebDriver driver, Logger log){
        this.driver = driver;
        this.log = log;
        wait = new WebDriverWait(driver, Duration.ofSeconds(7));
    }

    public void navigateTo(String pageURLSUFFIX) {
        String urlToBeLoaded = BASE_URL + pageURLSUFFIX;
        driver.get(urlToBeLoaded);
        log.info("# CONFIRM: The user has navigated to " + urlToBeLoaded);
    }

    public void clickOn(WebElement element) {
        wait.until(ExpectedConditions.visibilityOf(element));
        wait.until(ExpectedConditions.elementToBeClickable(element));
        element.click();
        log.info("The user has clicked on " + locatorInfo(element));
    }

    public void typeTextIn(WebElement elm, String txt){
        wait.until(ExpectedConditions.visibilityOf(elm));
        elm.clear();
        elm.sendKeys(txt);
        log.info("# CONFIRM: The user has provided in " + locatorInfo(elm) + " the text: " + txt);
    }

    public String getElementText(WebElement element){
        wait.until(ExpectedConditions.visibilityOf(element));
        String elementText = element.getText();
        log.info("# CONFIRM: The text is: " + elementText);
        return elementText;
    }

    public String getElementAttributeValue(WebElement element){
        wait.until(ExpectedConditions.visibilityOf(element));
        String attribute = element.getAttribute("value");
        log.info("# CONFIRM: Attribute 'value' is: " + attribute);
        return attribute;
    }

    public String getElementPlaceholderValue(WebElement element){
        wait.until(ExpectedConditions.visibilityOf(element));
        String placeholder = element.getAttribute("placeholder");
        log.info("# CONFIRM: Placeholder is: " + placeholder);
        return placeholder;
    }

    public boolean isElementPresented(WebElement element){
        boolean isShown = false;
        String locator = locatorInfo(element);
        log.info("@ ACTION: Checking visibility of element: " + locator);
        try {
            wait.until(ExpectedConditions.visibilityOf(element));
            isShown = true;
            log.info("# CONFIRM: The element is visible: " + locator);
        } catch (TimeoutException | NoSuchElementException e) {
            log.error("* ERROR: Element not visible: " + locator);
        }
        return isShown;
    }

    public boolean isElementClickable(WebElement element){
        boolean isClickable = false;
        String locator = locatorInfo(element);
        log.info("@ ACTION: Checking if element is clickable: " + locator);
        try {
            wait.until(ExpectedConditions.elementToBeClickable(element));
            isClickable = true;
            log.info("# CONFIRM: The element is clickable: " + locator);
        } catch (TimeoutException e) {
            log.error("! ERROR: Element not clickable: " + locator);
        }
        return isClickable;
    }

    public boolean isTextPresentInElement(WebElement element, String expectedText) {
        try {
            wait.until(ExpectedConditions.textToBePresentInElement(element, expectedText));
            log.info("# CONFIRM: Text '" + expectedText + "' is present in element: " + locatorInfo(element));
            return true;
        } catch (TimeoutException e) {
            log.error("* ERROR: Text not present in element: " + expectedText);
            return false;
        }
    }

    public void pause(int seconds) {
        try {
            Thread.sleep(seconds * 1000L);
            log.info("Paused for " + seconds + " seconds.");
        } catch (InterruptedException e) {
            log.error("Interrupted during pause.");
            Thread.currentThread().interrupt();
        }
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
