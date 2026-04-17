package tests;

import model.ContactData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ContactInfoTests extends TestBase {

    @Test
    void testPhones() {
        if (app.hbm().getContactCount() == 0) {
            app.hbm().createContact(new ContactData());
            app.reloadPage();
        }
        var contacts = app.hbm().getContactList();
        var contact = contacts.get(0);
        var phones = app.contact().getPhones();
        var expected = Stream.of(contact.home(), contact.mobilePhone(), contact.work())
                .filter(s -> s != null && !"".equals(s))
                .collect(Collectors.joining("\n"));
        Assertions.assertEquals(expected, phones.get(contact.id()));
    }

    @Test
    void testEmails() {
        if (app.hbm().getContactCount() == 0) {
            app.hbm().createContact(new ContactData());
            app.reloadPage();
        }
        var contacts = app.hbm().getContactList();
        var contact = contacts.get(0);
        var emails = app.contact().getEmails();
        var expected = Stream.of(contact.email(), contact.email2(), contact.email3())
                .filter(s -> s != null && !"".equals(s))
                .collect(Collectors.joining("\n"));
        Assertions.assertEquals(expected, emails.get(contact.id()));
    }

    @Test
    void testAddress() {
        if (app.hbm().getContactCount() == 0) {
            app.hbm().createContact(new ContactData());
            app.reloadPage();
        }
        var addresses = app.contact().getAddresses();
        var contacts = app.hbm().getContactList();
        var contact = contacts.get(0);
        var expectedAddresses = contact.address();
        Assertions.assertEquals(expectedAddresses, addresses.get(contact.id()));
    }
}
