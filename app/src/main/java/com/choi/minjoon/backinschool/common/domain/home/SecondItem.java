package com.choi.minjoon.backinschool.common.domain.home;

/**
 * Created by User on 2017-06-01.
 */

public class SecondItem {
    public String userId;
    public String id;
    public String title;

    public SecondItem(String userId, String id, String title) {
        this.userId = userId;
        this.id = id;
        this.title = title;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
