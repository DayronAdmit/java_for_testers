package ru.stqa.mantis.tests;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.Duration;

public class UserRegistrationTests extends TestBase {

    @Test
    void canRegisterUser() {
        String username = "user21";
        String password = "password";
        var email = String.format("%s@localhost", username);
        app.jamesApi().addUser(email, password);
        app.rest().initRegistration(username, email);
        var mailMessages = app.mail().receive(email, password, Duration.ofSeconds(100));
        var activationLink = app.mail().extractLink(mailMessages);
        app.openLink(activationLink);
        app.webHelper().finishRegistration(username, password);
        app.http().login(username, password);

        Assertions.assertTrue(app.http().isLoggedIn());
    }
}

