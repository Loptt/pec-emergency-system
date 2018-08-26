package com.pacosanchez.emergencysystem;

public class familyMember {

    private String name;
    private String phoneNumber;
    private String image;
    private String address;

    public familyMember(String name, String phoneNumber, String image, String address){
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.image = image;
        this.address = address;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getAddress() {
        return address;
    }

    public void setAdress(String address) {
        this.address = address;
    }
}
