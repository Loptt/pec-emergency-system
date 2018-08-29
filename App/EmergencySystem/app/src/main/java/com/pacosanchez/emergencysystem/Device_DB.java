package com.pacosanchez.emergencysystem;

public class Device_DB {

    String sName;
    String sPartial_Key;
    String sID;


    public Device_DB() {
        this.sPartial_Key = "0";
    }

    public Device_DB(String sName, String sPartial_Key, String sID) {
        this.sName = sName;
        this.sPartial_Key = sPartial_Key;
        this.sID = sID;
    }

    public String getsName() {
        return sName;
    }

    public void setsName(String sName) {
        this.sName = sName;
    }

    public String getsPartial_Key() {
        return sPartial_Key;
    }

    public void setsPartial_Key(String sPartial_Key) {
        this.sPartial_Key = sPartial_Key;
    }

    public String getsID() {
        return sID;
    }

    public void setsID(String sID) {
        this.sID = sID;
    }
}
