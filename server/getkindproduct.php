<?php

class loaisanpham{
    function loaisanpham($id, $ten, $hinhanh){
        $this->id = $id;
        $this->ten = $ten;
        $this->hinhanh = $hinhanh;
    }
}

$arr = array();

$connect = mysqli_connect("localhost","root","","shopbanhang");
mysqli_set_charset($connect, "utf8");

$query =  "SELECT * FROM danhmuc as dm LEFT JOIN hinhanh AS ha ON dm.id = ha.thuocve AND ha.loai = 1";
$data = mysqli_query($connect, $query);

$arr = array();

while($row = mysqli_fetch_assoc($data)){
    array_push($arr, new loaisanpham($row['id'], $row['tendanhmuc'], $row['url']));
}

echo json_encode($arr);
?>