<?php
$id = $_GET["id"];

class khachhang{
    function khachhang($id, $ten, $email){
        $this->id = $id;
        $this->ten = $ten;
        $this->email = $email;
    }
}

$arr = array();

$connect = mysqli_connect("localhost","root","123456","shopquanao");
mysqli_set_charset($connect, "utf8");

$query =  "SELECT * FROM khachhang WHERE id=$id";
$data = mysqli_query($connect, $query);

$arr = array();

while($row = mysqli_fetch_assoc($data)){
    array_push($arr, new khachhang($row['id'], $row['ten'], $row['email']));
}

echo json_encode($arr);
?>