package com.choi.minjoon.backinschool.common.domain.detail;

/**
 * Created by User on 2017-06-01.
 */

public class DetailItem {
    private int id;
    private String userId;
    private String userName;
    private String password;
    private long score;
    private String address;

    public DetailItem() {
    }

    public DetailItem(int id, String userId, String userName, String password, long score, String address) {
        this.id = id;
        this.userId = userId;
        this.userName = userName;
        this.password = password;
        this.score = score;
        this.address = address;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public long getScore() {
        return score;
    }

    public void setScore(long score) {
        this.score = score;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", userId='" + userId + '\'' +
                ", userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                ", score=" + score +
                ", address='" + address + '\'' +
                '}';
    }
}
