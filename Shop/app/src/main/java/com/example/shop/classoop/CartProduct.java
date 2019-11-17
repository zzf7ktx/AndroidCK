package com.example.shop.classoop;

public class CartProduct {
    int idKhachHang;
    int idSanPham;
    int soLuong;

    public CartProduct(int idKhachHang, int idSanPham, int soLuong) {
        this.idKhachHang = idKhachHang;
        this.idSanPham = idSanPham;
        this.soLuong = soLuong;
    }

    public int getIdKhachHang() {
        return idKhachHang;
    }

    public void setIdKhachHang(int idKhachHang) {
        this.idKhachHang = idKhachHang;
    }

    public int getIdSanPham() {
        return idSanPham;
    }

    public void setIdSanPham(int idSanPham) {
        this.idSanPham = idSanPham;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }
}
