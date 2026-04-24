package ru.stqa.mantis.manager;


import org.openqa.selenium.By;

public class WebRegistrationHelper extends HelperBase {

    public WebRegistrationHelper(ApplicationManager manager) {
        super(manager);
    }

    public void initRegistration(String username, String email) {
        initRegistration();
        fillRegistrationForm(username, email);
        submitRegistration();
    }

    public void finishRegistration(String username, String password) {
        fillEditAccountForm(username, password);
        submitEditForm();
    }

    private void fillEditAccountForm(String username, String password) {
        type(By.id("realname"), username);
        type(By.id("password"), password);
        type(By.id("password-confirm"), password);
    }

    private void submitRegistration() {
        click(By.cssSelector("input[type='submit']"));
    }
    private void submitEditForm() {
        click(By.cssSelector("button[type='submit']"));
    }

    private void fillRegistrationForm(String username, String email) {
        type(By.id("username"), username);
        type(By.id("email-field"), email);
    }

    private void initRegistration() {
        click(By.cssSelector("a[href='signup_page.php']"));
    }

}
