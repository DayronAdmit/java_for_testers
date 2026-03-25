package tests;

import model.ContactData;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class ContactCreationTests extends TestBase {

    @ParameterizedTest
    @MethodSource("contactProvider")
    public void canCreateSimpleContact(ContactData contact) {
        app.contact().createContact(contact);
    }

    public static List<ContactData> contactProvider() {
        var contacts = new ArrayList<ContactData>();
        for (int i = 0; i < 5; i++) {
            contacts.add(new ContactData(randomString(i * 5), randomString(i * 5), randomString(i * 5), randomString(i * 5), randomString(i * 5)));
        }
        return contacts;
    }
}
