package org.va.profile;

import org.va.POM.LoginPage;
import org.va.base.BaseTest;
import org.testng.annotations.Test;

public class ProfileTests extends BaseTest {

    @Test
    public void updateProfileInfo(){
        LoginPage logPage = new LoginPage(super.driver,log);
        logPage.loginWithTestUser();


    }
}
