package com.example.shop.classoop;

public class UserInfo {
    private int ID;
    private String Ten;
    private String Email;
    private String Sdt;

    public UserInfo(int id, String ten, String email, String sdt) {
        ID = id;
        Ten = ten;
        Email = email;
        Sdt = sdt;
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

    public void setTen(String ten) {
        Ten = ten;
    }

    public String getSdt() {
        return Sdt;
    }

    public void setSdt(String sdt) {
        Sdt = sdt;
    }
}
