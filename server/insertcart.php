<?php
$idsanpham = $_POST['sanpham'];

$connect = mysqli_connect("localhost","root","123456","shopquanao");
mysqli_set_charset($connect, "utf8");

$get_num_row = mysqli_num_rows(mysqli_query($connect, "SELECT * FROM sanpham WHERE id = $idsanpham"));

?>