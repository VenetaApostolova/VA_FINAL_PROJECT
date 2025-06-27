package org.va.POM;

import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class HomePage extends BasePage {
    public static final String HOME_PAGE_SUFIX = "/posts/all";

    @FindBy(id = "nav-link-home")
    private WebElement navBarHomeLink;

    @FindBy(id = "nav-link-login")
    private WebElement navBarLoginLink;

    @FindBy(id = "nav-link-profile")
    private WebElement navBarProfileLink;

    @FindBy(id = "nav-link-new-post")
    private WebElement navBarNewPostLink;

    @FindBy(id = "search-bar")
    private WebElement searchBarInput;

    @FindBy(xpath = "//i[contains(@class,'fas fa-sign-out-alt fa-lg')]")
    private WebElement logOutButton;

    public HomePage(WebDriver driver, Logger log) {
        super(driver, log);
        PageFactory.initElements(driver,this);
    }

   public void openHomePage(){
        navigateTo(HOME_PAGE_SUFIX);
    }

    public void clickOnHomeNavBar(){
        wait.until(ExpectedConditions.visibilityOf(navBarHomeLink));
        navBarHomeLink.click();
    }

    public void clickOnLogOutButton(){
        clickOn(logOutButton);
    }

    public void clickOnProfileNavBar(){
        clickOn(navBarProfileLink);
    }

    public void clickOnNewPostNavBar(){
        clickOn(navBarNewPostLink);
    }

    public void clickOnLoginNavBar(){
        wait.until(ExpectedConditions.visibilityOf(navBarLoginLink));
        navBarLoginLink.click();
    }

    public boolean isNavHomeShown() {
        return  isElementPresented(navBarHomeLink);
    }

    public boolean isNavLoginShown(){
        return isElementPresented(navBarLoginLink);
    }

    public boolean isNavBarProfileLinkShown() {
        return isElementPresented(navBarProfileLink);
    }

    public boolean isNavBarNewPostLinkShown() {
        return isElementPresented(navBarNewPostLink);
    }

    public boolean isSearchInputInputShown(){
        return isElementPresented(searchBarInput);
    }

    public boolean isLogOutButtonShown(){
        return isElementPresented(logOutButton);
    }
}
