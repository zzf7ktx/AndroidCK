<?php

$user = $_POST['user'];
$pass = md5($_POST['pass']);
$email = $_POST['email'];
$phone = $_POST['phone'];

$connect = mysqli_connect("localhost","root","","shopbanhang");
mysqli_query($connect,"SET NAME 'utf8");

$query = "INSERT INTO khachhang VALUES(NULL ,'$user', '$phone', '$email', '$pass', NULL, DATE_FORMAT(CURRENT_DATE(), '%d-%m-%Y'))";

if(mysqli_query($connect, $query)){
    echo "Success";
} else {
    echo "Fail: Tài khoản email đã tồn tại.";
}

?>