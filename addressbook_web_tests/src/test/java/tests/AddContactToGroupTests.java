package tests;

import model.ContactData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Random;

public class AddContactToGroupTests extends TestBase {

    @Test
    public void canAddContactToGroup() {
        if (app.hbm().getGroupCount() == 0) {
            app.hbm().createGroup(app.groups().generateGroup());
        }
        var groupToAdd = app.hbm().getRandomGroup();
        var oldContactListInGroup = app.hbm().getContactsInGroup(groupToAdd);

        app.contact().chooseContactsWithoutGroup();
        if (app.contact().getList().size() == 0) {
            app.hbm().createContact(app.contact().generateContact());
            app.reloadPage();
        }
        var contacts = app.contact().getList();
        var index = new Random().nextInt(contacts.size());
        app.contact().addCotactToGroup(contacts.get(index), groupToAdd);
        var newContactListInGroup = app.hbm().getContactsInGroup(groupToAdd);
        Comparator<ContactData> compareById = (o1, o2) -> {
            return Integer.compare(Integer.parseInt(o1.id()), Integer.parseInt(o2.id()));
        };
        newContactListInGroup.sort(compareById);
        var expectedContactList = new ArrayList<>(oldContactListInGroup);
        expectedContactList.add(contacts.get(index));
        expectedContactList.sort(compareById);
        Assertions.assertEquals(expectedContactList, newContactListInGroup);
    }
}
