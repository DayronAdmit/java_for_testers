package tests;

import model.ContactData;
import model.GroupData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class ContactCreationTests extends TestBase {

    @ParameterizedTest
    @MethodSource("contactProvider")
    public void canCreateSimpleContact(ContactData contact) {
        var oldContacts = app.contact().getList();
        app.contact().createContact(contact);
        var newContacts = app.contact().getList();
        Comparator<ContactData> compareById = (o1, o2) -> {
            return Integer.compare(Integer.parseInt(o1.id()), Integer.parseInt(o2.id()));
        };
        newContacts.sort(compareById);
        var expectedList = new ArrayList<>(oldContacts);
        expectedList.add(contact.withId(newContacts.get(newContacts.size() - 1).id()));
        expectedList.sort(compareById);
        Assertions.assertEquals(expectedList, newContacts);
    }

    public static List<ContactData> contactProvider() {
        var contacts = new ArrayList<ContactData>();
        for (int i = 0; i < 5; i++) {
            contacts.add(new ContactData()
                    .withLastName(randomString(i * 5))
                    .withFirstName(randomString(i * 5))
                    .withAddress(randomString(i * 5))
                    .withEmail(randomString(i * 5))
                    .withMobilePhone(randomString(i * 5)));
        }
        return contacts;
    }
}
