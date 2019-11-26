package com.example.shop.classoop;

public class UserInfo {
    private int ID;
    private String Ten;
    private String Email;

    public UserInfo(int id, String ten, String email) {
        ID = id;
        Ten = ten;
        Email = email;
    }

    public void setID(int id) {
        ID = id;
    }

    public void setTensanpham(String ten) {

        Ten = ten;
    }

    public void setHinhanhsanpham(String email) {
        Email = email;
    }


    public int getID() {
        return ID;
    }

    public String getTen() {
        return Ten;
    }

    public String getEmail() {
        return Email;
    }
}
