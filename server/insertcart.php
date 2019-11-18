<?php
$idsanpham = $_POST['sanpham'];
//$idkhachhang = $_POST['khachhang'];
//$sl = $_POST['sl'];

// class sanpham{
//     function sanpham($id, $ten, $gia, $hinhanh, $mota, $idLoai, $soluong){
//         $this->id = $id;
//         $this->ten = $ten;
//         $this->gia = $gia;
//         $this->hinhanh = $hinhanh;
//         $this->mota = $mota;
//         $this->idLoai = $idLoai;
//         $this->soluong = $soluong;
//     }
// }
//$arr = array();

$connect = mysqli_connect("localhost","root","123456","shopquanao");
mysqli_set_charset($connect, "utf8");

$get_num_row = mysqli_num_rows(mysqli_query($connect, "SELECT * FROM sanpham WHERE id = $idsanpham"));

?>