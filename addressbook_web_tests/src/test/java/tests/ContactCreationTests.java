package tests;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import common.CommonFunction;
import model.ContactData;
import model.GroupData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class ContactCreationTests extends TestBase {

    @Test
    public void canCreateContactInGroup() {
        var contact = new ContactData()
                .withFirstName(CommonFunction.randomString(7))
                .withLastName(CommonFunction.randomString(12));
        if (app.hbm().getGroupCount() == 0) {
            app.hbm().createGroup(new GroupData("", "", "", ""));
        }
        var group = app.hbm().getGroupList().get(0);

        var oldRelated = app.hbm().getContactsInGroup(group);
        app.contact().createContact(contact, group);
        var newRelated = app.hbm().getContactsInGroup(group);
        Comparator<ContactData> compareById = (o1, o2) -> {
            return Integer.compare(Integer.parseInt(o1.id()), Integer.parseInt(o2.id()));
        };
        newRelated.sort(compareById);
        var expectedRelated = new ArrayList<>(oldRelated);
        expectedRelated.add(contact.withId(newRelated.get(newRelated.size() - 1).id()));
        expectedRelated.sort(compareById);
        Assertions.assertEquals(expectedRelated, newRelated);
    }


    @ParameterizedTest
    @MethodSource("contactProvider")
    public void canCreateSimpleContact(ContactData contact) {
        var oldContacts = app.hbm().getContactList();
        app.contact().createContact(contact);
        var newContacts = app.hbm().getContactList();
        Comparator<ContactData> compareById = (o1, o2) -> {
            return Integer.compare(Integer.parseInt(o1.id()), Integer.parseInt(o2.id()));
        };
        newContacts.sort(compareById);
        var expectedList = new ArrayList<>(oldContacts);
        expectedList.add(contact.withId(newContacts.get(newContacts.size() - 1).id()));
        expectedList.sort(compareById);
        Assertions.assertEquals(expectedList, newContacts);
    }

    public static List<ContactData> contactProvider() throws IOException {
        var contacts = new ArrayList<ContactData>();
//        for (int i = 0; i < 5; i++) {
//            contacts.add(new ContactData()
//                    .withLastName(ComanFunction.randomString(i * 5))
//                    .withFirstName(ComanFunction.randomString(i * 5))
//                    .withAddress(ComanFunction.randomString(i * 5))
//                    .withEmail(ComanFunction.randomString(i * 5))
//                    .withMobilePhone(ComanFunction.randomString(i * 5)));
//        }
        ObjectMapper mapper = new ObjectMapper();
        var value = mapper.readValue(new File("contacts.json"), new TypeReference<List<ContactData>>() {
        });
        contacts.addAll(value);
        return contacts;
    }
}
