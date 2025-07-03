package org.va.POM;

import org.apache.logging.log4j.Logger;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class LoginPage extends BasePage {
    private static final String LOGIN_PAGE_SUFIX = "/users/login";

    private static final String USER = "venetaQA2025";
    private static final String PASS = "Veneta123!";

    @FindBy(css = "p.h4")
    private WebElement loginFormTitle;

    @FindBy(id = "defaultLoginFormUsername")
    private WebElement usernameInputField;

    @FindBy(id = "defaultLoginFormPassword")
    private WebElement passwordInputField;

    @FindBy(id = "sign-in-button")
    private WebElement loginSubmitButton;

    @FindBy(xpath = "//div[contains(@aria-label,\"Successful login!\")]")
    private WebElement signInToastMessage;

    @FindBy(css = "a[href='/users/register']")
    private WebElement registerLink;

    @FindBy(css = "input[type='checkbox']")
    private WebElement rememberMeCheckbox;

    @FindBy(id = "defaultLoginFormUsername")
    private WebElement usernameInput;

    @FindBy(id = "defaultLoginFormPassword")
    private WebElement passwordInput;

    @FindBy(id = "sign-in-button")
    private WebElement loginFormSubmitBtn;

    @FindBy(id = "homeIcon")
    private WebElement loginPageLogo;

    public LoginPage(WebDriver driver, Logger log) {
        super(driver, log);
        PageFactory.initElements(driver,this);
    }

    public void navigateToLoginPage(){
        navigateTo(LOGIN_PAGE_SUFIX);
    }

    public void provideUser(String userNameText){
        wait.until(ExpectedConditions.visibilityOf(usernameInputField));
        usernameInputField.clear();
        usernameInputField.sendKeys(userNameText);
    }

    public void providePass(String pass){
        wait.until(ExpectedConditions.visibilityOf(passwordInputField));
        passwordInputField.clear();
        passwordInputField.sendKeys(pass);
    }

    public void clickOnLoginFormSubmitButton(){
        wait.until(ExpectedConditions.visibilityOf(loginSubmitButton));
        loginSubmitButton.click();
    }

    public void loginWithTestUser(){
        navigateToLoginPage();
        provideUser(USER);
        providePass(PASS);
        clickOnLoginFormSubmitButton();
    }
    public void clickOnRegisterLink() {
        wait.until(ExpectedConditions.visibilityOf(registerLink));
        registerLink.click();
    }

    public void selectRememberMeCheckbox() {
        wait.until(ExpectedConditions.elementToBeClickable(rememberMeCheckbox));
        if (!rememberMeCheckbox.isSelected()) {
            rememberMeCheckbox.click();
            log.info("Checkbox 'Remember me' was selected.");
        }
    }

    public String getLoginFormHeaderText(){
        return getElementText(loginFormTitle);
    }

    public String getUsernamePlaceHolderText(){
        return getElementPlaceholderValue(usernameInputField);
    }

    public String getPassPlaceHolderText(){
        return getElementPlaceholderValue(passwordInputField);
    }

    public String getLoginFormSubmitButtonlabel(){
        return getElementText(loginSubmitButton);
    }

    public String getLoginPageToastSuccessfullMsg(){
        return getElementText(signInToastMessage);
    }

    public boolean isLoginFormHeaderTextShown(){
        return isElementPresented(loginFormTitle);
    }

    public void login(String username, String password) {
    navigateToLoginPage();
    provideUser(username);
    providePass(password);
    clickOnLoginFormSubmitButton();
    log.info("User logged in with username: " + username);}

    public boolean isElementVisible(String elementName) {
           switch (elementName.toLowerCase()) {
                case "username":
                    return isElementPresented(usernameInput);
                case "password":
                    return isElementPresented(passwordInput);
                case "submit":
                    return isElementPresented(loginFormSubmitBtn);
                case "register":
                    return isElementPresented(registerLink);
                default:
                    throw new IllegalArgumentException("Unknown element: " + elementName);
            }
        }

            public boolean isLoginLogoVisible() {
                try {
                    wait.until(ExpectedConditions.visibilityOf(loginPageLogo));
                    return loginPageLogo.isDisplayed();
                } catch (TimeoutException e) {
                    log.error("Login logo not visible.");
                    return false;
                }
            }

        }



