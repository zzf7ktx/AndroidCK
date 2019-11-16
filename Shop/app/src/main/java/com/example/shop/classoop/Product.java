package com.example.shop.classoop;

import java.io.Serializable;

public class Product implements Serializable {
    public int ID;
    public String Tensanpham;
    public Integer Giasanpham;
    public String Hinhanhsanpham;
    public String Motasanpham;
    public int IDSanpham;

    public Product(int ID, String tensanpham, Integer giasanpham, String hinhanhsanpham, String motasanpham, int IDSanpham) {
        this.ID = ID;
        Tensanpham = tensanpham;
        Giasanpham = giasanpham;
        Hinhanhsanpham = hinhanhsanpham;
        Motasanpham = motasanpham;
        this.IDSanpham = IDSanpham;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public void setTensanpham(String tensanpham) {
        Tensanpham = tensanpham;
    }

    public void setGiasanpham(Integer giasanpham) {
        Giasanpham = giasanpham;
    }

    public void setHinhanhsanpham(String hinhanhsanpham) {
        Hinhanhsanpham = hinhanhsanpham;
    }

    public void setMotasanpham(String motasanpham) {
        Motasanpham = motasanpham;
    }

    public void setIDSanpham(int IDSanpham) {
        this.IDSanpham = IDSanpham;
    }



    public int getID() {
        return ID;
    }

    public String getTensanpham() {
        return Tensanpham;
    }

    public Integer getGiasanpham() {
        return Giasanpham;
    }

    public String getHinhanhsanpham() {
        return Hinhanhsanpham;
    }

    public String getMotasanpham() {
        return Motasanpham;
    }

    public int getIDSanpham() {
        return IDSanpham;
    }

}
