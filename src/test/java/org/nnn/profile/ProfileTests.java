package org.nnn.profile;

import org.nnn.POM.LoginPage;
import org.nnn.base.BaseTest;
import org.testng.annotations.Test;

public class ProfileTests extends BaseTest {

    @Test
    public void updateProfileInfo(){
        LoginPage logPage = new LoginPage(super.driver,log);
        logPage.loginWithTestUser();


    }
}
