package com.epam.reportportal.business.ui.pages;

import com.epam.reportportal.business.models.User;
import lombok.EqualsAndHashCode;
import org.openqa.selenium.WebDriver;


@EqualsAndHashCode(callSuper = true)
public class LoginPage extends AbstractPage {

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    public LoginPage openPage() {
        driver.get(getProperties(LOGIN_PAGE_URL));
        return this;
    }

    public DashboardsPage logIn(User user) {
        customActions.clickButton(getProperties(LOGIN_WITH_EPAM_BUTTON_LABEL));
        customActions.fillInputField(getProperties(LOGIN_WITH_EPAM_EMAIL_INPUT_LABEL), user.getLogin());
        customActions.clickButton(getProperties(LOGIN_WITH_EPAM_NEXT_BUTTON_LABEL));
        customActions.fillInputField(getProperties(LOGIN_WITH_EPAM_PASSWORD_INPUT_LABEL), user.getPassword());
        customActions.clickButton(getProperties(LOGIN_WITH_EPAM_SIGN_IN_BUTTON_LABEL));
        return new DashboardsPage(driver);
    }
}
