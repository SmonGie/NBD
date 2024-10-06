package org.project.model.clients;

import org.project.model.Account;

public class Client {

    private String firstName, lastName, phoneNumber;
    private int age;
    private Address address;
    private Account account;
    private ClientType clientType;
//      trzeba dodac jeszcze ID!

    private String personalID;

    public Client(String firstName, String lastName, String personalID, int age, String phoneNumber, Address address) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.age = age;
        this.address = address;

        if(age<18){
            clientType = new Child();
        }
        else clientType = new  Adult();

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
                //"\n ID: " + personalID +
                "\n Wiek: " + age +
                "\n Numer telefonu: " + phoneNumber + "\n " + clientType.getInfo();

    }

    //    @Override
//    public boolean equals(Object obj) {
//        if (this == obj) {
//            return true;
//        }
//
////        if (!(obj instanceof Client)) {
////            return false;
////        }
//
//        Client other = (Client) obj;
//
//        return this.personalID.equals(other.personalID);
//    }
    public int applyLimits() {
        return clientType.applyLimits();
    }

}
