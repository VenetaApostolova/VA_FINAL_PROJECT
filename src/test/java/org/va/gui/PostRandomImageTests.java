package org.va.gui;

import org.testng.Assert;
import org.testng.annotations.Test;
import org.va.POM.*;
import org.va.base.BaseTest;


import java.io.File;
import java.util.Random;

public class PostRandomImageTests extends BaseTest {

    @Test(priority = 1)
    public void testUserCanCreatePostWithRandomImage() {
        log.info("STEP 1: Navigating to the Home page.");
        HomePage homePage = new HomePage(driver, log);
        LoginPage loginPage = new LoginPage(driver, log);
        NewPostPage postPage = new NewPostPage(driver, log);
        ProfilePage profilePage = new ProfilePage(driver, log);
        homePage.openHomePage();

        log.info("STEP 2: Logging in with test user credentials.");
        loginPage.loginWithTestUser();

        log.info("STEP 3: Navigating to Post creation page.");
        homePage.clickOnNewPostNavBar();

        log.info("STEP 4: Uploading a random image.");
        File file = getRandomImageFromResources();
        postPage.uploadPicture(file);

        log.info("STEP 5: Providing caption.");
        postPage.providePostCaption("AutoTest Photo - Random image upload");

        log.info("STEP 6: Submitting the post.");
        postPage.clickCreatePostButton();

        log.info("STEP 7: Navigating to Profile page to verify post count.");
        homePage.clickOnProfileNavBar();
        int postCount = profilePage.getPostCount();
        Assert.assertTrue(postCount > 0, "Expected at least 1 post created.");
    }
    private File getRandomImageFromResources() {
        String[] imagePaths = {
                "src/test/resources/upload/testUpload.jpg",
                "src/test/resources/upload/testUpload1.jpg",
                "src/test/resources/upload/testUpload2.jpg",
                "src/test/resources/upload/testUpload3.jpg"
        };
        int randomIndex = new Random().nextInt(imagePaths.length);
        return new File(imagePaths[randomIndex]);
    }

    @Test(priority = 2)
    public void testUserCanSeeRandomImagePostInAllPostsPage() {
        log.info("STEP 1: Navigating to All Posts page.");
        HomePage homePage = new HomePage(driver, log);
        NewPostPage postPage = new NewPostPage(driver, log);
        homePage.clickOnAllPostsNavBar();

        log.info("STEP 2: Verifying the presence of a post with the test caption.");
        boolean isPostVisible = postPage.isPostWithCaptionVisible("AutoTest Photo - Random image upload");

        Assert.assertTrue(isPostVisible, "Post with expected caption is not visible on All Posts page.");
    }

}




