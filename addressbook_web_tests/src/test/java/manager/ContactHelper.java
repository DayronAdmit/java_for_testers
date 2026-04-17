package manager;

import common.CommonFunction;
import model.ContactData;
import model.GroupData;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
        returnToHomePage();
    }

    private void returnToHomePage() {
        click(By.linkText("home page"));
    }

    private void submitChanges() {
        click(By.name("update"));
    }

    private void initContactModification(ContactData contactData) {
        click(By.xpath(String.format("//input[@value='%s']/ancestor::tr//*[@title='Edit']", contactData.id())));
    }

    public void checkGroupById(String id) {
        new Select(manager.driver.findElement(By.name("group"))).selectByValue(id);
    }

    public void chooseContactsWithoutGroup() {
        new Select(manager.driver.findElement(By.name("group"))).selectByValue("[none]");
    }

    public void addCotactToGroup(ContactData contact, GroupData group) {
        selectContact(contact);
        chooseGroupToAdding(group);
        click(By.name("add"));
        openContactPage();
    }

    public void removeContactFromGroup(ContactData contact) {
        selectContact(contact);
        click(By.name("remove"));
        openContactPage();
    }

    private void chooseGroupToAdding(GroupData group) {
        new Select(manager.driver.findElement(By.name("to_group"))).selectByValue(group.id());
    }

    public void chooseContactGroup(GroupData group) {
        new Select(manager.driver.findElement(By.name("group"))).selectByValue(group.id());
    }

    public ContactData generateContact() {
        return new ContactData()
                .withId("")
                .withLastName(CommonFunction.randomString(10))
                .withFirstName(CommonFunction.randomString(8))
                .withAddress(CommonFunction.randomString(20))
                .withEmail(CommonFunction.randomString(15))
                .withMobilePhone(CommonFunction.randomString(10));
    }

    public String getPhones(ContactData contact) {
        return manager.driver.findElement(By.xpath(
                        String.format("//input[@id='%s']/../../td[6]", contact.id())))
                .getText();
    }

    public Map<String, String> getPhones() {
        var result = new HashMap<String, String>();
        var rows = manager.driver.findElements(By.name("entry"));
        for (WebElement row : rows) {
            var id = row.findElement(By.tagName("input")).getAttribute("id");
            var phones = row.findElements(By.tagName("td")).get(5).getText();
            result.put(id, phones);
        }
        return result;
    }

    public Map<String, String> getEmails() {
        var result = new HashMap<String, String>();
        var rows = manager.driver.findElements(By.name("entry"));
        for (WebElement row : rows) {
            var id = row.findElement(By.tagName("input")).getAttribute("id");
            var phones = row.findElements(By.tagName("td")).get(4).getText();
            result.put(id, phones);
        }
        return result;
    }

    public Map<String, String> getAddresses() {
        var result = new HashMap<String, String>();
        var rows = manager.driver.findElements(By.name("entry"));
        for (WebElement row : rows) {
            var id = row.findElement(By.tagName("input")).getAttribute("id");
            var phones = row.findElements(By.tagName("td")).get(3).getText();
            result.put(id, phones);
        }
        return result;
    }
}
