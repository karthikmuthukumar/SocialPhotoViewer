package com.codepath.socialphotoviewer;

/**
 * Created by kmuthu1 on 10/12/15.
 */
public class UserComment {

    private String userName;
    private String comment;

    public UserComment(String userName, String comment) {
        this.userName = userName;
        this.comment = comment;
    }

    public String getComment() {
        return comment;
    }

    public String getUserName() {
        return userName;
    }
}
