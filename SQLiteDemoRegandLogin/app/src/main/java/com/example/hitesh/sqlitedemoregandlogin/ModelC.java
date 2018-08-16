package com.example.hitesh.sqlitedemoregandlogin;

public class ModelC {

    int id;
    String name;
    String phone;
    String email;
    String password;
    String gender;
    String hobby;

    public ModelC(){}

    public ModelC(String na, String mob, String em, String pass, String gen, String hb) {

        this.name =na;
        this.phone = mob;
        this.email = em;
        this.password = pass;
        this.gender = gen;
        this.hobby = hb;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getHobby() {
        return hobby;
    }

    public void setHobby(String hobby) {
        this.hobby = hobby;
    }
}
