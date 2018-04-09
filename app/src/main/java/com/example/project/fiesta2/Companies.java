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
    public String licence_no;
    public String phone;
    public String district;
    public String category;
    public String bkey;
    public String event_1;

    public Companies() {
    }

    public Companies(String key,String name, String address, String location, String email_id, String min_budget, String max_budget, String image, String licence_no, String phone, String district, String category,String bkey,String event_1) {
        this.key=key;
        this.name = name;
        this.address = address;
        this.location = location;
        this.email_id = email_id;
        this.min_budget = min_budget;
        this.max_budget = max_budget;
        this.image = image;
        this.licence_no = licence_no;
        this.phone = phone;
        this.district = district;
        this.category = category;
        this.bkey=bkey;
        this.event_1=event_1;
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

    public String getLicence_no() {
        return licence_no;
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

    public String getBkey(){return bkey;}

    public String getEvent_1() {
        return event_1;
    }
}







