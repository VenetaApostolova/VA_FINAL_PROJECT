package org.va.gui;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.va.POM.HomePage;
import org.va.POM.LoginPage;
import org.va.POM.NewPostPage;
import org.va.POM.ProfilePage;
import org.va.base.BaseTest;
import org.apache.logging.log4j.Logger;
import org.va.base.BaseTest;

public class ProfileNegativeTests extends BaseTest {



    @Test(priority = 1)
    public void confirmUnauthenticatedUserCannotAccessProfilePage() {
        Logger log = this.log;
        WebDriver driver = this.driver;

        log.info("STEP 1: Navigating directly to Profile Page without authentication.");
        driver.get("http://training.skillo-bg.com:4300/users/9634");

        log.info("STEP 2: Validating that the URL is redirected to login.");
        String currentUrl = driver.getCurrentUrl();

        Assert.assertTrue(currentUrl.contains("/users/login"),
                "The user was not redirected to the login page. Actual URL: " + currentUrl);
    }

    @Test(priority = 2)
    public void confirmPostIsNotDeletedWhenClickingNoOnConfirmation() {
        log.info("STEP 1: Open Home Page.");
        HomePage homePage = new HomePage(driver, log);
        homePage.openHomePage();

        log.info("STEP 2: Login with test user.");
        LoginPage loginPage = new LoginPage(driver, log);
        loginPage.login("venetaQA2025", "Veneta123!");

        log.info("STEP 3: Open Profile Page.");
        ProfilePage profilePage = new ProfilePage(driver, log);
        profilePage.goToProfilePage();

        log.info("STEP 4: Open post modal.");
        profilePage.clickOnFirstProfilePost();  // Нов метод тук!

        log.info("STEP 5: Attempt to delete the post, but cancel the confirmation.");
        profilePage.clickOnDeleteButton();
        profilePage.clickCancelDelete(); // натискаме 'No'

        log.info("STEP 6: Confirm the post modal is still visible.");
        Assert.assertTrue(profilePage.isPostModalDisplayed(), "Post modal should still be visible after clicking 'No'");
    }
}
