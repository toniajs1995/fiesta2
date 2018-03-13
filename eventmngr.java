package com.example.abcde.myapp;

/**
 * Created by ABCDE on 01-02-2018.
 */

public class eventmngr {
    String eid;
    String ename;
    String ephn;
    String eloc;
    Long budget;

    public eventmngr() {
    }

    public eventmngr(String eid, String ename, String ephn, String eloc, Long budget) {
        this.eid = eid;
        this.ename = ename;
        this.ephn = ephn;
        this.eloc = eloc;
        this.budget=budget;
    }

    public String getEid() {
        return eid;
    }

    public String getEname() {
        return ename;
    }

    public String getEphn() {
        return ephn;
    }

    public String getEloc() {
        return eloc;
    }

    public Long getBudget() {
        return budget;
    }
}
