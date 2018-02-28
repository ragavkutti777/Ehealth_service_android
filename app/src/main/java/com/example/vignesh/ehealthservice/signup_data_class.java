package com.example.vignesh.ehealthservice;

/**
 * Created by Vignesh B on 2/28/2018.
 */

public class signup_data_class {

    private String name,age,phone,email;

    signup_data_class(String name,String age,String phone,String email)
    {
        this.name=name;
        this.age=age;
        this.phone=phone;
        this.email=email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
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


}
