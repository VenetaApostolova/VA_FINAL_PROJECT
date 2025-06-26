package org.nnn.reg;

import org.nnn.POM.RegistrationPage;
import org.nnn.base.BaseTest;
import org.testng.annotations.Test;

public class RegistrationHappyPathsTests extends BaseTest {


    @Test
    public void verifyUserCanRegisterWithValidData() throws InterruptedException {

        RegistrationPage registrationPage = new RegistrationPage(super.driver,log);
        registrationPage.navigateToRegistrationPageByURL();

        registrationPage.provideUserName();
        registrationPage.provideEmail();
        registrationPage.provideBDayInfo();
        registrationPage.providePass();
        registrationPage.providePassConfirm();
        registrationPage.providePublicInfo();
        registrationPage.clickOnSubmitBtn();

        Thread.sleep(3444);
    }
}
