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

$connect = mysqli_connect("localhost","root","123456","shopbanhang");
mysqli_set_charset($connect, "utf8");

$query =  "SELECT kh.id, kh.ten , kh.sodienthoai as sdt, kh.email, kh.phanquyen, kh.ngaytao, ha.url FROM khachhang AS kh LEFT JOIN hinhanh AS ha ON kh.id = ha.thuocve AND ha.loai = 4 WHERE kh.id=$id";
$data = mysqli_query($connect, $query);

$arr = array();

while($row = mysqli_fetch_assoc($data)){
    array_push($arr, new khachhang($row['id'], $row['ten'], $row['email']));
}

echo json_encode($arr);
?>