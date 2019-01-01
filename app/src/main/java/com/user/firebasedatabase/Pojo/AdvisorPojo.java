package com.user.firebasedatabase.Pojo;

import com.google.firebase.database.IgnoreExtraProperties;

@IgnoreExtraProperties
public class AdvisorPojo {
    String advisorname,mobileno,address;

    public AdvisorPojo() {
    }
    public AdvisorPojo(String advisorname, String mobileno, String address) {
        this.advisorname = advisorname;
        this.mobileno = mobileno;
        this.address = address;
    }


    public String getAdvisorname() {
        return advisorname;
    }

    public void setAdvisorname(String advisorname) {
        this.advisorname = advisorname;
    }

    public String getMobileno() {
        return mobileno;
    }

    public void setMobileno(String mobileno) {
        this.mobileno = mobileno;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
