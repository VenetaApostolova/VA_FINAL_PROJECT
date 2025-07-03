package org.va.POM;

import org.apache.logging.log4j.Logger;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.time.Duration;
import java.util.List;

public class PostPage extends BasePage {

    private WebDriverWait wait;

    public PostPage(WebDriver driver, Logger log) {
        super(driver, log);
        PageFactory.initElements(driver, this);
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    // ========== LOCATORS ==========

    @FindBy(css = ".file[type='file']")
    private WebElement uploadPictureInput;

    @FindBy(css = "input[class='form-control ng-pristine ng-valid ng-touched']")
    private WebElement captionInput;

    @FindBy(id = "create-post")
    private WebElement createPostButton;

    @FindBy(css = ".post-feed .post")
    private List<WebElement> posts;

    @FindBy(css = ".fa-heart")
    private List<WebElement> likeIcons;

    @FindBy(css = ".fa-thumbs-down")
    private List<WebElement> dislikeIcons;

    @FindBy(css = ".delete-post")
    private List<WebElement> deleteIcons;

    @FindBy(css = ".post-image")
    private List<WebElement> postImages;

    @FindBy(css = ".post-description")
    private List<WebElement> postDescriptions;

    @FindBy(css = ".like-post")
    private WebElement likeButton;

    // ========== ACTIONS ==========

    public void uploadPicture(File file) {
        log.info("@ ACTION: Checking visibility of element: LOCATOR STRATEGY: CSS SELECTOR | VALUE: .file[type='file']");
        wait.until(ExpectedConditions.visibilityOf(uploadPictureInput));
        uploadPictureInput.sendKeys(file.getAbsolutePath());
        log.info("CONFIRMATION # The file was successfully uploaded.");
    }

    public void providePostCaption(String captionText) {
        captionInput.sendKeys(captionText);
        log.info("CONFIRMATION # The user has provided caption text: " + captionText);
    }

    public void clickCreatePostButton() {
        createPostButton.click();
        log.info("CONFIRMATION # The user has clicked on the submit post button.");
    }

    public int getNumberOfPosts() {
        return posts.size();
    }

    public void likeFirstPost() {
        likeIcons.get(0).click();
        log.info("ACTION # The user liked the first post.");
    }

    public void dislikeFirstPost() {
        dislikeIcons.get(0).click();
        log.info("ACTION # The user disliked the first post.");
    }

    public void deleteFirstPost() {
        deleteIcons.get(0).click();
        log.info("ACTION # The user deleted the first post.");
    }

    public String getFirstPostImageSrc() {
        return postImages.get(0).getAttribute("src");
    }

    public String getFirstPostCaption() {
        return postDescriptions.get(0).getText();
    }

    public void clickLikeButton() {
        log.info("@ ACTION: Clicking the Like button.");
        wait.until(ExpectedConditions.elementToBeClickable(likeButton));
        likeButton.click();
    }

    public boolean isLikeButtonActive() {
        log.info("@ ASSERT: Checking if Like button is active.");
        return likeButton.getAttribute("class").contains("text-danger");
    }
    public void clickDislikeButton() {
        log.info("@ ACTION: Clicking the Dislike button.");
        wait.until(ExpectedConditions.elementToBeClickable(dislikeIcons.get(0)));
        dislikeIcons.get(0).click();
    }

    public boolean isDislikeButtonActive() {
        log.info("@ ASSERT: Checking if Dislike button is active.");
        return dislikeIcons.get(0).getAttribute("class").contains("text-primary");
    }

    public void clickDeleteButton() {
        log.info("@ ACTION: Clicking Delete button.");
        wait.until(ExpectedConditions.elementToBeClickable(deleteIcons.get(0)));
        deleteIcons.get(0).click();
    }

    public void clickConfirmDeleteButton() {
        log.info("@ ACTION: Confirming delete action in popup.");
        Alert alert = driver.switchTo().alert();
        alert.accept();
    }
    public boolean isPostWithCaptionVisible(String expectedCaption) {
        log.info("@ ASSERT: Checking if post with caption \"" + expectedCaption + "\" is visible.");
        for (WebElement caption : postDescriptions) {
            if (caption.getText().trim().equals(expectedCaption)) {
                return true;
            }
        }
        return false;
    }
}
