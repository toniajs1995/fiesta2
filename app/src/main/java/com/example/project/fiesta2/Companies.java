package com.example.project.fiesta2;

import com.google.firebase.database.IgnoreExtraProperties;

/**
 * Created by user on 04-03-2018.
 */
@IgnoreExtraProperties
public class Companies {
    public String key;
    public String name;
    public String address;
    public String location;
    public String email_id;
    public String min_budget;
    public String max_budget;
    public String image;
    public String license_no;
    public String phone;
    public String district;
    public String category;

    public Companies() {
    }

    public Companies(String key,String name, String address, String location, String email_id, String min_budget, String max_budget, String image, String license_no, String phone, String district, String category) {
        this.key=key;
        this.name = name;
        this.address = address;
        this.location = location;
        this.email_id = email_id;
        this.min_budget = min_budget;
        this.max_budget = max_budget;
        this.image = image;
        this.license_no = license_no;
        this.phone = phone;
        this.district = district;
        this.category = category;
    }

    public String getAddress() {
        return address;
    }

    public String getLocation() {
        return location;
    }

    public String getEmail_id() {
        return email_id;
    }

    public String getMin_budget() {
        return min_budget;
    }

    public String getMax_budget() {return max_budget;}

    public String getLicense_no() {
        return license_no;
    }

    public String getPhone() {
        return phone;
    }

    public String getDistrict() {
        return district;
    }

    public String getCategory() {
        return category;
    }
    public String getName() {
        return name;
    }

    public String getImage() {
        return image;
    }

    public String getKey() {return key;}
}







