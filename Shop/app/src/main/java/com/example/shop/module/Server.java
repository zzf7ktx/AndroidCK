package com.example.shop.module;


public class Server {
    public static String localhost ="192.168.1.112:8080";
    public static String LoaiSanPham ="http://"+ localhost +"/server/getloaisanpham.php";
    public static String SanPhamMoiNhat="http://"+ localhost+ "/server/getsanphammoinhat.php";
    public static String SanPham = "http://"+ localhost+ "/server/getsanpham.php";
    public static String SanPhamFlashSale = "http://"+ localhost+ "/server/getsanphamflashsale.php";
    public static String ThongTinKhachHang ="http://"+ localhost +"/server/thongtinkhachhang.php";
    public static String ChiTietDonHang ="http://"+ localhost +"/server/chitietdonhang.php";
    public static String signup ="http://"+ localhost +"/server/signup.php";
    public static String signin ="http://"+ localhost +"/server/signin.php";

    // add more link to use
    public static String getuser = "http://"+ localhost +"/server/getuser.php";
    public static String getdata = "http://"+ localhost +"/server/getdata.php";
    public static String register = "http://"+ localhost +"/server/register.php";
    public static String getSaleProduct = "http://"+ localhost +"/server/getsaleproduct.php";
    public static String getProduct = "http://"+ localhost +"/server/getproduct.php";
    public static String getKind = "http://"+ localhost +"/server/getkindproduct.php";


    // đường dẫn tự đặt, cái trên ví dụ, localHost điền vào

}
