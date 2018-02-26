package com.rossevine.pertemuan_6.entity;

/**
 * Created by Developer on 2/20/2018.
 */

public class UserWrapper {
    private  int status;
    private  String message;
    private  User user;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
