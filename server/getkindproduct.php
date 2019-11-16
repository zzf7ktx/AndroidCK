<?php

class loaisanpham{
    function loaisanpham($id, $ten, $hinhanh){
        $this->id = $id;
        $this->ten = $ten;
        $this->hinhanh = $hinhanh;
    }
}

$arr = array();

$connect = mysqli_connect("localhost","root","123456","shopquanao");
mysqli_set_charset($connect, "utf8");

$query =  "SELECT * FROM loaisanpham";
$data = mysqli_query($connect, $query);

$arr = array();

while($row = mysqli_fetch_assoc($data)){
    array_push($arr, new loaisanpham($row['id'], $row['tenLoaiSanPham'], $row['hinhAnhLoaiSanPham']));
}

echo json_encode($arr);
?>