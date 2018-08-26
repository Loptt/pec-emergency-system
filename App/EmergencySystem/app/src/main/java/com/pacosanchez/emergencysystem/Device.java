package com.pacosanchez.emergencysystem;

public class Device {

    String sName;
    String sType;
    String sStatus;
    String sIp;

    public Device() {
        this.sName = "";
        this.sType="";
        this.sStatus="0";
        this.sIp="0";
    }

    public Device(String sName, String sType, String sStatus, String sIp) {
        this.sName = sName;
        this.sType = sType;
        this.sStatus = sStatus;
        this.sIp = sIp;
    }

    public String getsName() {
        return sName;
    }

    public void setsName(String sName) {
        this.sName = sName;
    }

    public String getsType() {
        return sType;
    }

    public void setsType(String sType) {
        this.sType = sType;
    }

    public String getsStatus() {
        return sStatus;
    }

    public void setsStatus(String sStatus) {
        this.sStatus = sStatus;
    }

    public String getsIp() {
        return sIp;
    }

    public void setsIp(String sIp) {
        this.sIp = sIp;
    }
}