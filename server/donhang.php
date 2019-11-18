<?php
$id = $_GET['id'];
$diachi = $_GET['diachi'];

$connect = mysqli_connect("localhost","root","123456","shopquanao");
mysqli_set_charset($connect, "utf8");

$get_num_row = mysqli_num_rows(mysqli_query($connect, "SELECT * FROM donhang")) + 1;
$data_kh = mysqli_query($connect, "SELECT * FROM khachhang WHERE id = $id");

$query = "INSERT INTO donhang VALUES($get_num_row, '$data_kh['id']', '$data_kh['sodienthoai']', '$data_kh['email']', '$diachi')";
mysqli_query($connect, $query);
?>


