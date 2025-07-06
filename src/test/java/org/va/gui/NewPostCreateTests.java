package org.va.gui;

import org.testng.Assert;
import org.testng.annotations.Test;
import org.va.POM.*;
import org.va.base.BaseTest;
import java.io.File;

public class NewPostCreateTests extends BaseTest {

    @Test(priority = 1)
    public void confirmUserCanCreatePostWithValidData() {
        log.info("STEP 1: Navigate to Home Page.");
        HomePage homePage = new HomePage(driver, log);
        homePage.openHomePage();

        log.info("STEP 2: Log in with valid user.");
        LoginPage loginPage = new LoginPage(driver, log);
        loginPage.login("venetaQA2025", "Veneta123!");

        log.info("STEP 3: Navigate to New Post Page.");
        homePage.clickOnNewPostNavBar();

        log.info("STEP 4: Upload image and provide description.");
        NewPostPage newPostPage = new NewPostPage(driver, log);
        File image = new File("src/test/resources/upload/testUpload1.jpg");
        newPostPage.uploadPicture(image);

        String caption = "Test Post - created by Automation";
        newPostPage.providePostCaption(caption);

        log.info("STEP 5: Click 'Create Post' button.");
        newPostPage.clickCreatePostButton();

        log.info("STEP 6: Navigate back to Home Page.");
        homePage.openHomePage();

        log.info("STEP 7: Assert post with caption is visible on Home Page.");
        Assert.assertTrue(newPostPage.isPostWithCaptionVisible(caption),
                "The post with caption was not found on the Home page.");
    }

    @Test(priority = 2)
    public void userCanUploadPictureSuccessfully() {
        log.info("STEP 1: Login with valid user.");
        LoginPage loginPage = new LoginPage(driver, log);
        loginPage.login("venetaQA2025", "Veneta123!");

        log.info("STEP 2: Go to Home Page.");
        HomePage homePage = new HomePage(driver, log);
        homePage.openHomePage();

        log.info("STEP 3: Navigate to Create Post page.");
        homePage.clickOnNewPostNavBar();

        log.info("STEP 4: Upload image.");
        NewPostPage postPage = new NewPostPage(driver, log);
        File imageFile = new File("src/test/resources/upload/testUpload1.jpg");

        Assert.assertTrue(imageFile.exists(), "Image file not found: " + imageFile.getAbsolutePath());
        postPage.uploadPicture(imageFile);

        log.info("STEP 5: Confirm image preview appears.");
        Assert.assertTrue(postPage.isImagePreviewVisible(), "Image preview should be visible.");

        log.info("STEP 6: Confirm 'Create Post' button is enabled.");
        Assert.assertTrue(postPage.isCreatePostButtonEnabled(), "'Create Post' button should be enabled after upload.");
    }
    @Test(priority = 3)
    public void testUserCanLikePost() {
        log.info("STEP 1: Logging in and navigating to profile.");
        HomePage homePage = new HomePage(driver, log);
        LoginPage loginPage = new LoginPage(driver, log);
        ProfilePage profilePage = new ProfilePage(driver, log);

        homePage.openHomePage();
        loginPage.loginWithTestUser();
        homePage.clickOnProfileNavBar();

        log.info("STEP 2: Clicking on the first available post.");
        profilePage.clickPost(0);

        log.info("STEP 3: Clicking like button.");
        profilePage.clickOnLikeButton();

        log.info("ASSERT: Checking if like message is visible.");
        Assert.assertTrue(profilePage.isLikeMessageVisible(), "Like confirmation message not visible.");
    }

    @Test(priority = 4)
    public void testUserCanDislikePost() {
        log.info("STEP 1: Logging in and navigating to profile.");
        HomePage homePage = new HomePage(driver, log);
        LoginPage loginPage = new LoginPage(driver, log);
        ProfilePage profilePage = new ProfilePage(driver, log);

        homePage.openHomePage();
        loginPage.loginWithTestUser();
        homePage.clickOnProfileNavBar();

        log.info("STEP 2: Clicking on the first available post.");
        profilePage.clickPost(0);

        log.info("STEP 3: Clicking dislike button.");
        profilePage.clickOnDislikeButton();

        log.info("ASSERT: Checking if dislike message is visible.");
        Assert.assertTrue(profilePage.isDislikeMessageVisible(), "Dislike confirmation message not visible.");
    }

    @Test(priority = 5)
    public void testUserCanDeletePost() {
        log.info("STEP 1: Logging in and navigating to profile.");
        HomePage homePage = new HomePage(driver, log);
        LoginPage loginPage = new LoginPage(driver, log);
        ProfilePage profilePage = new ProfilePage(driver, log);

        homePage.openHomePage();
        loginPage.loginWithTestUser();
        homePage.clickOnProfileNavBar();

        log.info("STEP 2: Clicking on the last created post.");
        profilePage.clickOnLastPost();

        log.info("STEP 3: Clicking delete button.");
        profilePage.clickOnDeleteButton();

        log.info("STEP 4: Confirming deletion.");
        profilePage.clickOnYesButton();

        log.info("ASSERT: Checking if deletion confirmation message is visible.");
        Assert.assertTrue(profilePage.isDeletedMessageVisible(), "Post was not deleted successfully.");
    }

    @Test(priority =6)
    public void testUserCanLikeLastCreatedPost() {
        log.info("STEP 1: Logging in and navigating to profile.");
        HomePage homePage = new HomePage(driver, log);
        LoginPage loginPage = new LoginPage(driver, log);
        ProfilePage profilePage = new ProfilePage(driver, log);
        NewPostPage postPage = new NewPostPage(driver, log);

        homePage.openHomePage();
        loginPage.loginWithTestUser();
        homePage.clickOnProfileNavBar();

        log.info("STEP 2: Clicking on the last created post.");
        profilePage.clickOnLastPost();

        log.info("STEP 3: Liking the post.");
        postPage.clickLikeButton();

        log.info("STEP 4: Verifying the post is liked.");
        boolean isLiked = postPage.isLikeButtonActive();
        Assert.assertTrue(isLiked, "Post was not liked successfully.");
    }

    @Test(priority = 7)
    public void testUserCanDislikeLastCreatedPost() {
        log.info("STEP 1: Logging in and navigating to profile.");
        HomePage homePage = new HomePage(driver, log);
        LoginPage loginPage = new LoginPage(driver, log);
        ProfilePage profilePage = new ProfilePage(driver, log);
        NewPostPage postPage = new NewPostPage(driver, log);

        homePage.openHomePage();
        loginPage.loginWithTestUser();
        homePage.clickOnProfileNavBar();

        log.info("STEP 2: Clicking on the last created post.");
        profilePage.clickOnLastPost();

        log.info("STEP 3: Disliking the post.");
        postPage.clickDislikeButton();

        log.info("STEP 4: Verifying the post is disliked.");
        boolean isDisliked = postPage.isDislikeButtonActive();
        Assert.assertTrue(isDisliked, "Post was not disliked successfully.");
    }

    @Test(priority =8)
    public void testUserCanDeleteLastCreatedPost() {
        log.info("STEP 1: Logging in and navigating to profile.");
        HomePage homePage = new HomePage(driver, log);
        LoginPage loginPage = new LoginPage(driver, log);
        ProfilePage profilePage = new ProfilePage(driver, log);
        NewPostPage postPage = new NewPostPage(driver, log);

        homePage.openHomePage();
        loginPage.loginWithTestUser();
        homePage.clickOnProfileNavBar();

        log.info("STEP 2: Clicking on the last created post.");
        profilePage.clickOnLastPost();

        log.info("STEP 3: Deleting the post.");
        postPage.clickDeleteButton();
        postPage.clickConfirmDeleteButton();

        log.info("STEP 4: Verifying deletion message appears.");
        boolean isDeleted = profilePage.isDeletedMessageVisible();
        Assert.assertTrue(isDeleted, "Post was not deleted successfully.");
    }
}





