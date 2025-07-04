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

}





