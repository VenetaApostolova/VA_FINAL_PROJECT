package org.va.gui;

import org.testng.Assert;
import org.testng.annotations.Test;
import org.va.POM.*;
import org.va.base.BaseTest;


import java.io.File;

public class NewPostCreateTests extends BaseTest {


    @Test(priority = 1)
    public void ensureUserCanCreatePost() throws InterruptedException {
        log.info("STEP 1: Navigating to Home page.");
        HomePage homePage = new HomePage(driver, log);
        homePage.openHomePage();

        log.info("STEP 2: Logging in with test user.");
        LoginPage loginPage = new LoginPage(driver, log);
        loginPage.login("venetaQA2025", "Veneta123!");

        log.info("STEP 3: Clicking on 'Create Post'.");
        homePage.clickOnNewPostNavBar();

        log.info("STEP 4: Creating and submitting a new post.");
        PostPage postPage = new PostPage(driver, log);

        File imageFile = new File("src/test/resources/upload/testUpload1.jpg");
        Assert.assertTrue(imageFile.exists(), "Image file not found: " + imageFile.getAbsolutePath());

        postPage.uploadPicture(imageFile);

        postPage.providePostCaption("AutoTest Photo 001 - Do not delete");

        Thread.sleep(2000);

        postPage.clickCreatePostButton();

        log.info("STEP 5: Navigating to user profile to verify post was created.");
        homePage.clickOnProfileNavBar();

        ProfilePage profilePage = new ProfilePage(driver, log);
        int postCount = profilePage.getPostCount();

        log.info("ASSERT: Checking that post count is more than 0.");
        Assert.assertTrue(postCount > 0, "Post count did not increase – post may not have been created.");
    }

    @Test(priority = 2)
    public void userCanUploadPictureSuccessfully() {
        log.info("STEP 1: Login with valid user.");
        LoginPage loginPage = new LoginPage(driver, log);
        loginPage.login("venetaQA2025", "Veneta123!");

        log.info("STEP 2: Go to Home Page.");
        HomePage homePage = new HomePage(driver, log);
        homePage.openHomePage(); // опционално, ако не се пренасочва автоматично след login

        log.info("STEP 3: Navigate to Create Post page.");
        homePage.clickOnNewPostNavBar();

        log.info("STEP 4: Upload image.");
        PostPage postPage = new PostPage(driver, log);
        File imageFile = new File("src/test/resources/upload/testUpload1.jpg");

        Assert.assertTrue(imageFile.exists(), "Image file not found: " + imageFile.getAbsolutePath());
        postPage.uploadPicture(imageFile);

        log.info("STEP 5: Confirm image preview appears.");
        Assert.assertTrue(postPage.isImagePreviewVisible(), "Image preview should be visible.");

        log.info("STEP 6: Confirm 'Create Post' button is enabled.");
        Assert.assertTrue(postPage.isCreatePostButtonEnabled(), "'Create Post' button should be enabled after upload.");
    }

}





