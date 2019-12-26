<?php

$madonhang = $_POST['madonhang'];
$masanpham = $_POST['masanpham'];
$soluong = $_POST['soluong'];

$connect = mysqli_connect("localhost","root","","shopbanhang");
mysqli_query($connect,"SET NAME 'utf8");

$query = "INSERT INTO chitietdonhang VALUES($madonhang ,$masanpham, $soluong, 1)";

if(mysqli_query($connect, $query)){
    echo "Success";
} else {
    echo "Fail";
}

?>