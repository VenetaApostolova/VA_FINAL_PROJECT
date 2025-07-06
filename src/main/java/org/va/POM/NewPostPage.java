package org.va.POM;

import org.apache.logging.log4j.Logger;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.TimeoutException;
import java.io.File;
import java.time.Duration;
import java.util.List;

public class NewPostPage extends BasePage {

    private WebDriverWait wait;

    public NewPostPage(WebDriver driver, Logger log) {
        super(driver, log);
        PageFactory.initElements(driver, this);
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    @FindBy(css = "input[type='file']")
    private WebElement uploadPictureInput;

    @FindBy(css = "input[placeholder='Enter you post caption here']")
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

    @FindBy(css = ".post-title")
    private List<WebElement> postTitles;

    @FindBy(css = ".like-post")
    private WebElement likeButton;

    @FindBy(css = ".modal-body .post-title")
    private WebElement modalCaption;

    public String getModalCaptionText() {
        return modalCaption.getText().trim();
    }

    public void uploadPicture(File file) {
        log.info("@ ACTION: Uploading file without wait, directly sending path.");
        uploadPictureInput.sendKeys(file.getAbsolutePath());
        log.info("CONFIRMATION # The file was successfully uploaded.");
    }
    public void clickOnFirstPost() {
        log.info("@ ACTION: Clicking on the first post to open it in modal.");
        WebElement firstPost = wait.until(ExpectedConditions.elementToBeClickable(postImages.get(0)));
        firstPost.click();
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
        return postTitles.get(0).getText();
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
    public void clickDeletePostButton() {
        WebElement deleteButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//i[contains(@class,'fa-trash') or contains(text(),'Delete post')]")));
        deleteButton.click();
    }

    public void clickCancelDelete() {
        WebElement noButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[contains(text(),'No')]")));
        noButton.click();
    }

    public boolean isPostModalDisplayed() {
        try {
            WebElement modal = wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("post-modal")));
            return modal.isDisplayed();
        } catch (TimeoutException e) {
            return false;
        }
    }

    public String getPostCreationErrorMessage() {
        try {
            WebElement toastMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(
                    By.cssSelector("div.toast-message.ng-star-inserted")
            ));
            return toastMessage.getText().trim();
        } catch (TimeoutException e) {
            log.warn("Toast error message was not found in the expected time.");
            return "";
        }
    }

    public boolean isPostWithCaptionVisible(String expectedCaption) {
        log.info("@ ASSERT: Checking if post with caption \"" + expectedCaption + "\" is visible.");

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        boolean found = false;
        for (WebElement caption : postTitles) {
            String actualText = caption.getText().trim();
            log.info("Found caption: \"" + actualText + "\"");
            if (actualText.equals(expectedCaption)) {
                found = true;
            }
        }
        return found;
    }

    public boolean isImagePreviewVisible() {
        log.info("@ ASSERT: Checking if image preview is visible.");
        try {
            WebElement preview = driver.findElement(By.cssSelector("img[alt='Image preview']"));
            return preview.isDisplayed();
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    public boolean isCreatePostButtonEnabled() {
        log.info("@ ASSERT: Checking if 'Create Post' button is enabled.");
        return createPostButton.isEnabled();
    }

    public boolean isPostSuccessfullyCreated() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        return wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//div[contains(@class,'post-feed')]//img")
        )).isDisplayed();

    }

    public boolean isPostErrorMessageVisible() {
        try {
            return wait.until(ExpectedConditions.visibilityOfElementLocated(
                    By.cssSelector("div.toast-message.ng-star-inserted")
            )).isDisplayed();
        } catch (TimeoutException e) {
            return false;
        }
    }
}



