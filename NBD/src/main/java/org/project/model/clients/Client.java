package org.project.model.clients;

import jakarta.persistence.*;
import org.hibernate.annotations.GenericGenerator;
import org.project.model.Account;

@Entity
@Table(name = "Client")
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "Id", unique = true, nullable = false)
    private Long Id;  // Change to String for UUID

    private String firstName, lastName, phoneNumber;
    private int age;
    @Embedded
    private Address address;
    @OneToOne
    @JoinColumn(name = "account_id", referencedColumnName = "Id")
    private Account account;
    @Transient
    private ClientType clientType;

    public Client() {
    }

    public Client(String firstName, String lastName, int age, String phoneNumber, Address address) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.age = age;
        this.address = address;

        if (age < 18) {
            clientType = new Child();
        } else {
            clientType = new Adult();
        }
    }


    //public String getPersonalID() {
      //  return personalID;
    //}

    public void setId(Long Id) {
        this.Id = Id;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public void setClientType(ClientType clientType) {
        this.clientType = clientType;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public int getAge() {
        return age;
    }

    public Address getAddress() {
        return address;
    }

    public Account getAccount() {
        return account;
    }

    public ClientType getClientType() {
        return clientType;
    }

    public String getInfo() {
        return "Klient: " + firstName + " " + lastName +
                "\n ID: " + Id +
                "\n Wiek: " + age +
                "\n Numer telefonu: " + phoneNumber + "\n " + clientType.getInfo();
    }

    public int applyLimits() {
        return clientType.applyLimits();
    }
}
