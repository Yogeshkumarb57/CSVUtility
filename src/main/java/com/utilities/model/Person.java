package com.utilities.model;

import com.opencsv.bean.CsvBindByName;

public class Person {

    @CsvBindByName(column = PersonConstant.NAME)
    private String name;
    @CsvBindByName(column = PersonConstant.ADDRESS)
    private String address;
    @CsvBindByName(column = PersonConstant.EMAIL_ID)
    private String emailId;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmailId() {
        return emailId;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", emailId='" + emailId + '\'' +
                '}';
    }
}
