package com.example.project.fiesta2;

/**
 * Created by user on 24-03-2018.
 */

public class rate {
    private String Rate;
    private String email;

    public rate() {
    }

    public rate(String rate) {
        Rate = rate;
    }

    public String getRate() {
        return Rate;
    }

    public void setRate(String rate) {
        Rate = rate;
    }

    public String getEmail() {
        return email;
    }

    public rate( String rate,String email) {

        this.Rate = rate;
        this.email = email;
    }
}
