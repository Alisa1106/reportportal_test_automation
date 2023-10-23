package com.epam.reportportal.business.ui.steps;

import com.epam.reportportal.business.models.User;
import com.epam.reportportal.business.ui.pages.LoginPage;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;

public class LoginSteps {

    private LoginPage loginPage;

    public LoginSteps(WebDriver driver) {
        loginPage = new LoginPage(driver);
    }

    @Step("Sign in as the user '{user}'")
    public LoginSteps signIn(User user) {
        loginPage.openPage()
                .logIn(user);
        return this;
    }
}
