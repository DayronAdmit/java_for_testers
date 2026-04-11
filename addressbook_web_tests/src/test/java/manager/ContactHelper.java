package manager;

import model.ContactData;
import model.GroupData;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.Select;

import java.util.ArrayList;
import java.util.List;

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

    public void createContact(ContactData contact, GroupData group) {
        initContactCreation();
        fillContactForm(contact);
        selectGroup(group);
        submitContactCreation();
        returnToContactPage();
    }

    private void selectGroup(GroupData group) {
       new Select(manager.driver.findElement(By.name("new_group"))).selectByValue(group.id());
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

    public void deleteContact(ContactData contact) {
        openContactPage();
        selectContact(contact);
        removeSelectedContact();
        returnToContactPage();

    }

    private void removeSelectedContact() {
        click(By.name("delete"));
    }

    private void selectContact(ContactData contact) {
        click(By.cssSelector(String.format("input[value='%s']", contact.id())));
    }

    public List<ContactData> getList() {
        openContactPage();
        var contacts = new ArrayList<ContactData>();
        var trs = manager.driver.findElements(By.cssSelector("tr[name=entry]"));
        for (var tr : trs) {
            var lastName = tr.findElement(By.cssSelector("td:nth-child(2)")).getText();
            var firstName = tr.findElement(By.cssSelector("td:nth-child(3)")).getText();
            var id = tr.findElement(By.cssSelector("input")).getAttribute("value");
            contacts.add(new ContactData().withId(id).withLastName(lastName).withFirstName(firstName));
        }
        return contacts;
    }

    public void modifyContact(ContactData contactData, ContactData testDate) {
        openContactPage();
        initContactModification(contactData);
        fillContactForm(testDate);
        submitChanges();
        returnToGroupsPage();
    }

    private void returnToGroupsPage() {
        click(By.linkText("home page"));
    }

    private void submitChanges() {
        click(By.name("update"));
    }

    private void initContactModification(ContactData contactData) {
        click(By.xpath(String.format("//input[@value='%s']/ancestor::tr//*[@title='Edit']", contactData.id())));
    }
}
