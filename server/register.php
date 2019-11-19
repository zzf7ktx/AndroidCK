<?php

$user = $_POST['user'];
$pass = md5($_POST['pass']);
$email = $_POST['email'];
$phone = $_POST['phone'];

$connect = mysqli_connect("localhost","root","123456","shopbanhang");
mysqli_query($connect,"SET NAME 'utf8");

$requery = "SELECT * FROM khachhang";
$data = mysqli_query($connect, $requery);

$no_of_rows = mysqli_num_rows($data) + 1;

$query = "INSERT INTO khachhang VALUES($no_of_rows,'$user', '$email', '$pass', 2, '$phone')";

if(mysqli_query($connect, $query)){
    echo "Success";
} else {
    echo "Fail: Tài khoản email đã tồn tại.";
}

?>