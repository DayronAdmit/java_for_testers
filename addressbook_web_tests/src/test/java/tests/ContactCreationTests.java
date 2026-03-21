package tests;

import model.ContactData;
import org.junit.jupiter.api.Test;

public class ContactCreationTests extends TestBase {

    @Test
    public void canCreateSimpleContact() {
        app.contact().createContact(new ContactData("Тестов", "Иван", "Москва, проспект Стивена Ли, д. 0", "test1@test1.rub", "71234567799"));
    }
}
