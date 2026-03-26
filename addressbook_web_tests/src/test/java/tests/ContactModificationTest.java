package tests;

import model.ContactData;
import model.GroupData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Random;

public class ContactModificationTest extends TestBase {

    @Test
    public void canModifyContact() {
        if (app.contact().getList().size() == 0) {
            app.contact().createContact(new ContactData());
        }
        var oldContacts = app.contact().getList();
        var rnd = new Random();
        var index = rnd.nextInt(oldContacts.size());
        var testDate = new ContactData().withLastName(randomString(8));
        app.contact().modifyContact(oldContacts.get(index), testDate);
        var newContacts = app.contact().getList();
        var expectedList = new ArrayList<>(oldContacts);
        expectedList.set(index, testDate.withId(oldContacts.get(index).id()));
        Comparator<ContactData> compareById = (o1, o2) -> {
            return Integer.compare(Integer.parseInt(o1.id()), Integer.parseInt(o2.id()));
        };
        newContacts.sort(compareById);
        expectedList.sort(compareById);
        Assertions.assertEquals(expectedList, newContacts);
    }
}
