package tests;

import model.ContactData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Random;

public class RemoveContactFromGroupTests extends TestBase {

    @Test
    public void canRemoveContactFromGroup() {
        if (app.hbm().getGroupCount() == 0) {
            app.hbm().createGroup(app.groups().generateGroup());
        }
        var groupFromRemove = app.hbm().getRandomGroup();
        app.contact().chooseContactGroup(groupFromRemove);

        if (app.contact().getList().size() == 0) {
            app.contact().createContact(app.contact().generateContact(), groupFromRemove);
            app.contact().chooseContactGroup(groupFromRemove);
        }
        var oldContactListInGroup = app.hbm().getContactsInGroup(groupFromRemove);

        var contacts = app.contact().getList();
        var index = new Random().nextInt(contacts.size());
        app.contact().removeContactFromGroup(contacts.get(index));

        var newContactListInGroup = app.hbm().getContactsInGroup(groupFromRemove);
        Comparator<ContactData> compareById = (o1, o2) -> {
            return Integer.compare(Integer.parseInt(o1.id()), Integer.parseInt(o2.id()));
        };
        newContactListInGroup.sort(compareById);
        var expectedContactList = new ArrayList<>(oldContactListInGroup);
        expectedContactList.remove(contacts.get(index));
        expectedContactList.sort(compareById);
        Assertions.assertEquals(expectedContactList, newContactListInGroup);
    }
}
