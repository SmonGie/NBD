package org.project.model.clients;

public class Address {

    private String city;
    private String street;
    private String number;

    public Address(String city, String street, String number) {
        this.city = city;
        this.street = street;
        this.number = number;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        if (city.isEmpty()) {
            System.out.println("Zmiana nie udala sie - pusty argument");
        } else
            this.city = city;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        if (street.isEmpty()) {
            System.out.println("Zmiana nie udala sie - pusty argument");
        } else
            this.street = street;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        if (number.isEmpty()) {
            System.out.println("Zmiana nie udala sie - pusty argument");
        } else
            this.number = number;
    }

    public String getInfo(){

        return getCity() + ", " + getStreet() + ", "+ getNumber();
    }
}
