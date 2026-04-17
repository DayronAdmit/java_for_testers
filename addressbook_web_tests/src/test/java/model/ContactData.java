package model;

import java.util.Objects;

public record ContactData(String id,
                          String lastName,
                          String firstName,
                          String address,
                          String email,
                          String mobilePhone,
                          String home,
                          String work,
                          String email2,
                          String email3) {
    public ContactData() {
        this("", "", "", "", "", "", "", "", "", "");
    }

    public ContactData withId(String id) {
        return new ContactData(id, this.lastName, this.firstName, this.address, this.email, this.mobilePhone, this.home, this.work, this.email2, this.email3);
    }

    public ContactData withLastName(String lastName) {
        return new ContactData(this.id, lastName, this.firstName, this.address, this.email, this.mobilePhone, this.home, this.work, this.email2, this.email3);
    }

    public ContactData withFirstName(String firstName) {
        return new ContactData(this.id, this.lastName, firstName, this.address, this.email, this.mobilePhone, this.home, this.work, this.email2, this.email3);
    }

    public ContactData withAddress(String address) {
        return new ContactData(this.id, this.lastName, this.firstName, address, this.email, this.mobilePhone, this.home, this.work, this.email2, this.email3);
    }

    public ContactData withEmail(String email) {
        return new ContactData(this.id, this.lastName, this.firstName, this.address, email, this.mobilePhone, this.home, this.work, this.email2, this.email3);
    }

    public ContactData withHome(String home) {
        return new ContactData(this.id, this.lastName, this.firstName, this.address, this.email, this.mobilePhone, home, this.work, this.email2, this.email3);
    }

    public ContactData withWork(String work) {
        return new ContactData(this.id, this.lastName, this.firstName, this.address, this.email, this.mobilePhone, this.home, work, this.email2, this.email3);
    }

    public ContactData withEmail2(String email2) {
        return new ContactData(this.id, this.lastName, this.firstName, this.address, this.email, this.mobilePhone, this.home, this.work, email2, this.email3);
    }

    public ContactData withEmail3(String email3) {
        return new ContactData(this.id, this.lastName, this.firstName, this.address, this.email, this.mobilePhone, this.home, this.work, this.email2, email3);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ContactData that = (ContactData) o;
        return Objects.equals(id, that.id) && Objects.equals(lastName, that.lastName) && Objects.equals(firstName, that.firstName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, lastName, firstName);
    }

    public ContactData withMobilePhone(String mobilePhone) {
        return new ContactData(this.id, this.lastName, this.firstName, this.address, this.email, mobilePhone, "", "", "", "");
    }
}
