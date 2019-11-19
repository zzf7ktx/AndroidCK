<?php
$user = $_POST["user"];
$pass = md5($_POST["pass"]);

$connect = mysqli_connect("localhost","root","123456","shopbanhang");
mysqli_query($connect,"SET NAME 'utf8");


$query =  "SELECT * FROM khachhang WHERE email = '$user' and matkhau = '$pass'";
$data = mysqli_query($connect, $query);

if(mysqli_num_rows($data) > 0){
    while ($row=mysqli_fetch_row($data)) {
        echo $row[0];
    }
    mysqli_free_result($data);
} else {
    echo "emty";
}
?>