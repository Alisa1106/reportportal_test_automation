package com.epam.reportportal.business.factories;

import com.epam.reportportal.business.models.User;
import com.epam.reportportal.core.common.utils.PropertyReader;
import lombok.NoArgsConstructor;

import static com.epam.reportportal.business.ui.constants.CredentialsPropertiesConst.DEFAULT_USER_LOGIN;
import static com.epam.reportportal.business.ui.constants.CredentialsPropertiesConst.DEFAULT_USER_PASSWORD;

@NoArgsConstructor
public class UserFactory {

    public static User defaultUser() {
        User user = new User();
        user.setLogin(System.getenv().getOrDefault(DEFAULT_USER_LOGIN, PropertyReader.getProperties("credentials", DEFAULT_USER_LOGIN)));
        user.setPassword(System.getenv().getOrDefault(DEFAULT_USER_PASSWORD, PropertyReader.getProperties("credentials", DEFAULT_USER_PASSWORD)));
        return user;
    }
}
