package com.example.shop.classoop;

import java.io.Serializable;

public class Order implements Serializable {
    public int ID;
    public int Nguoimua;
    public Integer Tongtien;
    public String Hinhanh;
    public String Diachi;

    public Order(int ID, int nguoimua, Integer tongtien, String hinhanh, String diachi) {
        this.ID = ID;
        Nguoimua = nguoimua;
        Tongtien = tongtien;
        Hinhanh = hinhanh;
        Diachi = diachi;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public void setNguoimua(int nguoimua) {
        Nguoimua = nguoimua;
    }

    public void setTongtien(Integer tongtien) {
        Tongtien = tongtien;
    }

    public void setHinhanh(String hinhanh) {
        Hinhanh = hinhanh;
    }

    public void setDiachi(String diachi) {
        Diachi = diachi;
    }

    public int getID() {
        return ID;
    }

    public int getNguoimua() {
        return Nguoimua;
    }

    public Integer getTongtien() {
        return Tongtien;
    }

    public String getHinhanh() {
        return Hinhanh;
    }

    public String getDiachi() {
        return Diachi;
    }

}
