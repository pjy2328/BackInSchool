package com.choi.minjoon.backinschool.common.domain.home;

/**
 * Created by User on 2017-06-01.
 */

public class FirstItem {
    public String id;
    public String title;
    public String body;

    public FirstItem(String id, String title, String body) {
        this.id = id;
        this.title = title;
        this.body = body;
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

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }
}
