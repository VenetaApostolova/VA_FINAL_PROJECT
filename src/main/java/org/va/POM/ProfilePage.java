package org.va.POM;

import org.apache.logging.log4j.Logger;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.ExpectedCondition;

import java.util.List;

public class ProfilePage extends BasePage {

    @FindBy(xpath = "//div[contains(@class,'edit-profile-pic ')]")
    private WebElement uploadImage;

    @FindBy(id = "upload-img")
    private WebElement hiddenUploadImage;

    @FindBy(xpath = "//i[contains(@class,'like far fa-heart fa-2x')]")
    private WebElement likeButton;

    @FindBy(xpath = "//i[contains(@class,'ml-4 far fa-thumbs-down fa-2x')]")
    private WebElement dislikeButton;

    @FindBy(xpath = "//label[contains(@class,'delete-ask')]")
    private WebElement deletePostButton;

    @FindBy(xpath = "//button[contains(@class,'btn btn-primary btn-sm')]")
    private WebElement areYouSureYesButton;

    @FindBy(xpath = "//div[contains(@aria-label,'Post Deleted!')]")
    private WebElement confirmDeletionMessage;

    @FindBy(xpath = "//div[contains(@aria-label,'Post liked')]")
    private WebElement postLikeMessage;

    @FindBy(xpath = "//div[contains(@aria-label,'Post disliked')]")
    private WebElement postDislikeMessage;

    @FindBy(tagName = "app-post")
    private List<WebElement> postsList;

    private final Actions action;

    public ProfilePage(WebDriver driver, Logger log) {
        super(driver, log);
        PageFactory.initElements(driver, this);
        action = new Actions(driver);
    }

    // ============= BASIC PROFILE ACTIONS =============
    public void HoverOverProfilePicture() {
        action.moveToElement(uploadImage).perform();
    }

    public String getUsername() {
        WebElement username = driver.findElement(By.tagName("h2"));
        return username.getText();
    }

    public int getPostCount() {
        List<WebElement> posts = driver.findElements(By.xpath("//app-profile-post//img"));
        return posts.size();
    }

    public void clickOnLastPost() {
        log.info("STEP: Clicking on the last created post.");
        wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(".post-feed .post:nth-child(1)"))).click();
    }

    public void clickPost(int postIndex) {
        if (postsList.isEmpty()) {
            log.error("There are no posts available to click.");
            throw new IllegalStateException("No posts available to click.");
        }

        postsList.get(postIndex).click();

        wait.until(new ExpectedCondition<Boolean>() {
            @Override
            public Boolean apply(WebDriver driver) {
                return ((JavascriptExecutor) driver)
                        .executeScript("return document.readyState").equals("complete");
            }
        });
    }

    public void ClickOnLikeButton() {
        clickOn(likeButton);
    }

    public void ClickOnDisikeButton() {
        clickOn(dislikeButton);
    }

    public void ClickOnDeleteButton() {
        clickOn(deletePostButton);
    }

    public void ClickOnYesButton() {
        clickOn(areYouSureYesButton);
    }

    public boolean isDeletedMessageVisible() {
        try {
            return wait.until(ExpectedConditions.visibilityOf(confirmDeletionMessage)).isDisplayed();
        } catch (NoSuchElementException e) {
            log.error("ERROR : The Post Deleted! message is not displayed!");
            return false;
        }
    }

    public boolean isLikeMessageVisible() {
        try {
            return wait.until(ExpectedConditions.visibilityOf(postLikeMessage)).isDisplayed();
        } catch (NoSuchElementException e) {
            log.error("ERROR : The Post liked message is not displayed!");
            return false;
        }
    }

    public boolean isDislikeMessageVisible() {
        try {
            return wait.until(ExpectedConditions.visibilityOf(postDislikeMessage)).isDisplayed();
        } catch (NoSuchElementException e) {
            log.error("ERROR : The Post disliked message is not displayed!");
            return false;
        }
    }

    public boolean isPostLiked(int postIndex) {
        try {
            WebElement post = postsList.get(postIndex);
            WebElement likeIcon = post.findElement(By.cssSelector("i.fa-heart"));
            wait.until(ExpectedConditions.visibilityOf(likeIcon));
            String iconClass = likeIcon.getAttribute("class");
            log.info("Like icon class of post[" + postIndex + "]: " + iconClass);
            return iconClass.contains("fas");
        } catch (Exception e) {
            log.error("Like icon not found or not visible for post[" + postIndex + "].");
            return false;
        }
    }

    public void closePostModal() {
        // Method reserved for future use if modal closing becomes necessary.
    }
}
