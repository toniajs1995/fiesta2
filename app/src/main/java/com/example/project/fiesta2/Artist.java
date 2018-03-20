package com.example.project.fiesta2;

/**
 * Created by ABCDE on 14-03-2018.
 */

public class Artist {


    String artistid;
    String comments;

    public Artist(){

    }

    public Artist( String artistid,String comments) {

        this.artistid = artistid;
        this.comments = comments;
    }



    public String getArtistid() {
        return artistid;
    }
    public String getComments() {
        return comments;
    }
}
