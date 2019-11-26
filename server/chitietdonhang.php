<?php
$idsp = $_GET['masanpham'];
$ten = $_GET['tensanpham'];
$gia = $_GET['giasanpham'];
$sl = $_GET['soluong'];

$connect = mysqli_connect("localhost","root","123456","shopquanao");
mysqli_set_charset($connect, "utf8");

$get_num_donhang = mysqli_num_rows(mysqli_query($connect, "SELECT * FROM donhang"));
$get_num_chitiet = mysqli_num_rows(mysqli_query($connect, "SELECT * FROM chitietdonmuahang")) + 1;

$query = "INSERT INTO chitietdonmuahang VALUES($get_num_chitiet, $get_num_donhang, $idsp, '$ten', $gia, $sl)";
mysqli_query($connect, $query);
?>


