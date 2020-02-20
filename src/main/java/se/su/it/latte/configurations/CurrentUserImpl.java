package se.su.it.latte.configurations;

import se.su.it.courseservice.courseinformation.client.api.CurrentUser;

public class CurrentUserImpl implements CurrentUser {

    @Override
    public String getCurrentUser() {
        return "Grinch";
    }
}
