package ru.stqa.mantis.tests;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.Duration;
import java.util.regex.Pattern;

public class MailTests extends TestBase {

    @Test
    void canReceiveEmail() {
        var message = app.mail().receive("user1@localhost", "root", Duration.ofSeconds(10));
        Assertions.assertEquals(1, message.size());
        System.out.println(message);
    }

    @Test
    void canDrainInbox() {
        app.mail().drain("user1@localhost", "password");
    }

    @Test
    void canExtractUrl() {
        var message = app.mail().receive("user1@localhost", "root", Duration.ofSeconds(100));
        var text = message.get(0).content();
        var pattern = Pattern.compile("http://\\S*");
        var matcher = pattern.matcher(text);
        if (matcher.find()) {
            var url = text.substring(matcher.start(), matcher.end());
            System.out.println(url);
        }
    }
}
