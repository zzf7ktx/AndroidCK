package com.example.shop.classoop;

import java.io.Serializable;

public class OrderProduct implements Serializable {
    private  int Madonhang;
    private int Masanpham;
    private String Tensanpham;
    private Integer Giasanpham;
    private String Hinhanhsanpham;
    private int Soluong;

    public OrderProduct(int madonhang, int masanpham, String tensanpham, Integer giasanpham, String hinhanhsanpham, int soluong) {
        Madonhang = madonhang;
        Masanpham = masanpham;
        Tensanpham = tensanpham;
        Giasanpham = giasanpham;
        Hinhanhsanpham = hinhanhsanpham;
        Soluong = soluong;
    }

    public void setMadonhang(int madonhang) {
        Madonhang = madonhang;
    }

    public void setMasanpham(int masanpham) {
        Masanpham = masanpham;
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

    public void setSoluong(int soluong) {
        Soluong = soluong;
    }

    public int getMadonhang() {
        return Madonhang;
    }

    public int getMasanpham() {
        return Masanpham;
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

    public int getSoluong() {
        return Soluong;
    }

}
