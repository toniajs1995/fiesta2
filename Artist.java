package com.example.abcde.comment;

/**
 * Created by ABCDE on 14-03-2018.
 */

public class Artist {


    String artistid;
    String comments;

    public Artist(){

    }

    public Artist(String comments, String artistid) {

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
