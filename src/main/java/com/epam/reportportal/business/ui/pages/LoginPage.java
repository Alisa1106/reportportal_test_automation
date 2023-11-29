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
        customActions.fillInputField(getProperties(LOGIN_INPUT_LABEL), user.getLogin());
        customActions.clickHint();
        customActions.fillInputField(getProperties(PASSWORD_INPUT_LABEL), user.getPassword());
        customActions.clickButton(getProperties(LOGIN_BUTTON_LABEL));
        return new DashboardsPage(driver);
    }
}
