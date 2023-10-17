package com.epam.reportportal.business.factories;

import com.epam.reportportal.business.models.User;
import lombok.NoArgsConstructor;

import static com.epam.reportportal.business.ui.constants.CredentialsPropertiesConst.DEFAULT_USER_LOGIN;
import static com.epam.reportportal.business.ui.constants.CredentialsPropertiesConst.DEFAULT_USER_PASSWORD;

@NoArgsConstructor
public class UserFactory {

    public static User defaultUser() {

        User user = new User();
        user.setLogin(System.getProperty(DEFAULT_USER_LOGIN));
        user.setPassword(System.getProperty(DEFAULT_USER_PASSWORD));
        return user;
    }
}
