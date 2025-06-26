package org.nnn.POM;

import org.apache.logging.log4j.Logger;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

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
        String urlToBeLoaded = BASE_URL+pageURLSUFIX;
        driver.get(urlToBeLoaded);
        log.info("# CONFIRM: The user has naivated to "+ urlToBeLoaded);
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
        log.info("# CONFIRM: The user has provided in" + elm + " the text "+txt);

        //JS excectuer - DOM completed function that will give us info if the page JS is ready
        // waitPageToBeFullyLoaded();
    }

    //Support methods for interacting with ELM text and attributes
    //TEXT Interactions
    public String getElementText(WebElement element){
        wait.until(ExpectedConditions.visibilityOf(element));
        String elementText = element.getText();

        log.info("# CONFIRM THE WEB ELEMENT TEXT IS " + elementText);
        return elementText;
    }

    public String getElementAttributeValue(WebElement element){
        wait.until(ExpectedConditions.visibilityOf(element));
        String elementText = element.getAttribute("value");

        log.info("# CONFIRM THE WEB ELEMENT ATTRIBUTE VALUE IS " + elementText);
        return elementText;
    }

    public String getElementPlaceholderValue(WebElement element){
        wait.until(ExpectedConditions.visibilityOf(element));
        String elementText = element.getAttribute("placeholder");

        log.info("# CONFIRM THE WEB ELEMENT PLACEHOLDER VALUE IS " + elementText);
        return elementText;
    }

    // VERIFICATIONS BOOLEANS
    public boolean isElementPresented(WebElement element){
        boolean isWebElementShown = false;
        String locatorInfo = locatorInfo(element);

        log.info("@ ACTION The user is verifying if web element is shown with locator: info " + locatorInfo);
        try {
            wait.until(ExpectedConditions.visibilityOf(element));
            isWebElementShown = true;
            log.info("# CONFIRM The web element is shown with info: "+ locatorInfo);
        } catch (TimeoutException e) {
           log.error("* ERROR NOT SHOWN Web element with "+locatorInfo);
        }
        return isWebElementShown;
    }

    public boolean isElementClickable(WebElement element){
        boolean isElementClickable = false;
        String locatorInfo = locatorInfo(element);
        log.info("@ ACTION The user is verifying if web element is shown with locator: info ", locatorInfo);
        try {
            wait.until(ExpectedConditions.elementToBeClickable(element));
            isElementClickable = true;
            log.info("# CONFIRM The web element is shown with info: "+ locatorInfo);
        } catch (TimeoutException e) {
            log.error("! ERROR NOT CLICKABLE Web element with "+locatorInfo);
        }
        return isElementClickable;
    }

    private String locatorInfo(WebElement elm ){
        String[] rawWebElmInfo = elm.toString().split("->");
        String[] webElmInfo = rawWebElmInfo[1].split(":");
        String locatorStrategy = webElmInfo[0];
        String locatorExpression = webElmInfo[1];
        String info = "LOCATOR STRATEGY BY : "+ locatorStrategy.toUpperCase() + " WITH LOCATOR EXPRESSION"+ locatorExpression;
        return info;
    }
}
