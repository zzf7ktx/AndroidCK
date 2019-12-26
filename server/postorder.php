<?php

$diachi = $_POST['diachi'];
$makhachhang = $_POST['makhachhang'];
$tongtien = $_POST['tongtien'];

$connect = mysqli_connect("localhost","root","","shopbanhang");
mysqli_query($connect,"SET NAME 'utf8");

$requery = "SELECT * FROM diachi";
$data = mysqli_query($connect, $requery);

$no_of_rows = mysqli_num_rows($data) + 1;

$requery2 = "SELECT * FROM donhang";
$data2 = mysqli_query($connect, $requery2);

$no_of_rows2 = mysqli_num_rows($data2) + 1;

$query = "INSERT INTO diachi VALUES(NULL ,$makhachhang, '1', '1', '1', '$diachi')";
$query2 = "INSERT INTO donhang VALUES(NULL , $no_of_rows, $makhachhang, $tongtien, 1, DATE_FORMAT(CURRENT_DATE(), '%d-%m-%Y'))";
 
mysqli_query($connect, $query);

if(mysqli_query($connect, $query2)){
    echo strval($no_of_rows2);
} else {
    echo "Fail";
}


?>