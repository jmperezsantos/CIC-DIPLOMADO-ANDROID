package cic.ipn.mx.agendarestexample.model;


import java.io.Serializable;

public class ContactModel implements Serializable {

    private String id;

    private String name;

    private String lastname;

    private String phone;

    private String mail;

    public ContactModel() {
    }

    public ContactModel(String id, String name, String lastname, String phone, String mail) {
        this(name, lastname, phone, mail);

        this.id = id;
    }

    public ContactModel(String name, String lastname, String phone, String mail) {
        this.name = name;
        this.lastname = lastname;
        this.phone = phone;
        this.mail = mail;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    @Override
    public String toString() {
        return "ContactModel{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", lastname='" + lastname + '\'' +
                ", phone='" + phone + '\'' +
                ", mail='" + mail + '\'' +
                '}';
    }
}
