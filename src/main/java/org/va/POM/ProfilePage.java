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

    @FindBy(xpath = "//label[contains(text(), 'All')]")
    private WebElement filterAllButton;

    @FindBy(xpath = "//label[contains(text(), 'Public')]")
    private WebElement filterPublicButton;

    @FindBy(xpath = "//label[contains(text(), 'Private')]")
    private WebElement filterPrivateButton;


    public boolean areFilterButtonsVisible() {
        return filterAllButton.isDisplayed()
                && filterPublicButton.isDisplayed()
                && filterPrivateButton.isDisplayed();
    }

    public void clickAllButton() {
        clickOn(filterAllButton);
    }

    public void clickPublicButton() {
        clickOn(filterPublicButton);
    }

    public void clickPrivateButton() {
        clickOn(filterPrivateButton);
    }

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
        By postImage = By.cssSelector(".post-img img");
        wait.until(ExpectedConditions.presenceOfElementLocated(postImage));
        List<WebElement> posts = driver.findElements(postImage);
        return posts.size();
    }

    public int getDisplayedPostCountFromText() {
        By postCountText = By.cssSelector(".profile-stats li:nth-child(1)");
        wait.until(ExpectedConditions.visibilityOfElementLocated(postCountText));
        String text = driver.findElement(postCountText).getText();  // напр. "10 posts"
        String numberOnly = text.replaceAll("[^0-9]", "");          // премахваме всичко освен цифри
        return Integer.parseInt(numberOnly);
    }

    public void goToProfilePage() {
        log.info("@ ACTION: Navigating to Profile Page via navbar.");
        WebElement profileLink = wait.until(ExpectedConditions.elementToBeClickable(By.id("nav-link-profile")));
        profileLink.click();
    }

    public void clickCancelDelete() {
        log.info("@ ACTION: Clicking on 'No' in delete confirmation popup.");
        WebElement noButton = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//button[contains(text(),'No')]")));
        noButton.click();
    }

    public void clickOnLastPost() {
        log.info("STEP: Clicking on the last created post.");
        wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(".post-feed .post:nth-child(1)"))).click();
    }

    public void clickOnFirstProfilePost() {
            log.info("@ ACTION: Clicking on the first post from Profile page.");
            By postSelector = By.cssSelector(".post-image");
            wait.until(ExpectedConditions.numberOfElementsToBeMoreThan(postSelector, 0));
            List<WebElement> profilePosts = driver.findElements(postSelector);
            profilePosts.get(0).click();
        }
    public boolean isPostModalDisplayed() {
        log.info("@ ASSERT: Checking if the post modal is displayed.");
        try {
            WebElement modal = wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("post-modal")));
            return modal.isDisplayed();
        } catch (TimeoutException e) {
            log.warn("Post modal was not displayed within timeout.");
            return false;
        }
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

            public void clickOnLikeButton() {
                clickOn(likeButton);
            }

            public void clickOnDislikeButton() {
                clickOn(dislikeButton);
            }

            public void clickOnDeleteButton() {
                clickOn(deletePostButton);
            }

            public void clickOnYesButton() {
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

                public void hoverOverProfilePicture() {
                    log.info("Hovering over profile picture.");
                    Actions actions = new Actions(driver);
                    WebElement profilePicture = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".profile-image")));
                    actions.moveToElement(profilePicture).perform();
                }

                public boolean isHoverOverlayVisible() {
                    log.info("Checking if hover overlay is visible.");
                    By overlay = By.cssSelector(".profile-image:hover .hover-icon");
                    try {
                        WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(overlay));
                        return element.isDisplayed();
                    } catch (TimeoutException e) {
                        return false;
                    }
                }

            public void closePostModal() {
                // Method reserved for future use if modal closing becomes necessary.
            }

        }
