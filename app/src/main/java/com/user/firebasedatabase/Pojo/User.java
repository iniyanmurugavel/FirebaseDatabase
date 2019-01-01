package com.user.firebasedatabase.Pojo;


import com.google.firebase.database.IgnoreExtraProperties;

@IgnoreExtraProperties
public class User {

    public String mobileno;
    public String email;

    public String getTypeofuser() {
        return typeofuser;
    }

    public void setTypeofuser(String typeofuser) {
        this.typeofuser = typeofuser;
    }

    public String typeofuser;

    public String name;
    public User(String mobileno, String email,String name,String  typeofuser) {
        this.mobileno = mobileno;
        this.email = email;
        this.name = name;
        this.typeofuser = typeofuser;


    }

    public User() {
    }

    public String getMobileno() {

        return mobileno;
    }

    public void setMobileno(String mobileno) {
        this.mobileno = mobileno;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
