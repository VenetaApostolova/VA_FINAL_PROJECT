package org.va.POM;

import org.apache.logging.log4j.Logger;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class PostModal extends BasePage {

    @FindBy(className = "post-modal")
    private WebElement modalElement;

    @FindBy(css = ".post-modal-img img")
    private WebElement modalImage;

    @FindBy(className = "post-user")
    private WebElement postUser;

    @FindBy(className = "post-caption")
    private WebElement postCaption;

    public PostModal(WebDriver driver, Logger log) {
        super(driver, log);
        PageFactory.initElements(driver, this);
    }

    public boolean isImageVisible() {
        try {
            return wait.until(ExpectedConditions.visibilityOf(modalImage)).isDisplayed();
        } catch (NoSuchElementException | TimeoutException e) {
            log.error("Image in post modal is not visible.");
            return false;
        }
    }

    public String getPostUser() {
        try {
            wait.until(ExpectedConditions.visibilityOf(postUser));
            return postUser.getText();
        } catch (NoSuchElementException | TimeoutException e) {
            log.error("Username in post modal is not visible.");
            return null;
        }
    }

    public String getPostCaption() {
        try {
            wait.until(ExpectedConditions.visibilityOf(postCaption));
            return postCaption.getText();
        } catch (NoSuchElementException | TimeoutException e) {
            log.error("Caption in post modal is not visible.");
            return null;
        }
    }
}
