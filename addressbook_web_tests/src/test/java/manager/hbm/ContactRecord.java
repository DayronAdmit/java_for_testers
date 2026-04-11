package manager.hbm;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "addressbook")
public class ContactRecord {
    @Id
    public int id;
    public String lastName;
    public String firstName;
    @Column(name = "middlename")
    public String middleName = "";
    @Column(name = "nickname")
    public String nikName= "";
    public String company= "";
    public String title= "";
    public String home= "";
    public String work= "";
    public String fax= "";
    public String address;
    public String email;
    public String email2= "";
    public String email3= "";
    public String homepage= "";
    @Column(name = "mobile")
    public String mobilePhone;

    public ContactRecord() {

    }

    public ContactRecord(int id, String lastName, String firstName, String address, String email, String mobilePhone) {
        this.id = id;
        this.lastName = lastName;
        this.firstName = firstName;
        this.address = address;
        this.email = email;
        this.mobilePhone = mobilePhone;
    }
}
