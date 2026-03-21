package manager;

import model.ContactData;
import org.openqa.selenium.By;

public class ContactHelper extends BaseHelper {
    public ContactHelper(ApplicationManager manager) {
        super(manager);
    }

    public void createContact(ContactData contact) {
        initContactCreation();
        fillContactForm(contact);
        submitContactCreation();
        returnToContactPage();
    }

    public void fillContactForm(ContactData contact) {
        type(By.name("firstname"), contact.firstName());
        type(By.name("lastname"), contact.lastName());
        type(By.name("address"), contact.address());
        type(By.name("email"), contact.email());
        type(By.name("mobile"), contact.mobilePhone());
    }

    private void returnToContactPage() {
        click(By.linkText("home page"));
    }

    private void submitContactCreation() {
        click(By.name("submit"));
    }

    public void initContactCreation() {
        click(By.linkText("add new"));
    }

    public void openContactPage() {
        if (!manager.isElementPresent(By.name("searchstring"))) {
            click(By.linkText("home"));
        }
    }

    public boolean isContactPresent() {
        openContactPage();
        return manager.isElementPresent(By.name("selected[]"));
    }

    public void deleteContact() {
        openContactPage();
        selectContact();
        removeSelectedContact();
        returnToContactPage();

    }

    private void removeSelectedContact() {
        click(By.name("delete"));
    }

    private void selectContact() {
        click(By.name("selected[]"));
    }
}
