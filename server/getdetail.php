<?php

$id = $_GET['id'];

class sanpham{
    function sanpham($id, $ten, $gia, $hinhanh, $mota, $idLoai){
        $this->id = $id;
        $this->ten = $ten;
        $this->gia = $gia;
        $this->hinhanh = $hinhanh;
        $this->mota = $mota;
        $this->idLoai = $idLoai;
    }
}

$arr = array();

$connect = mysqli_connect("localhost","root","","shopbanhang");
mysqli_set_charset($connect, "utf8");

$query =  "SELECT sp.id, sp.tensanpham, sp.nhanhang, sp.gia, sp.mota, sp.danhmuc, sp.luotxem, sp.giamgia, sp.ngaytao, sp.soluong, sp.rate, ha.url FROM sanpham AS sp INNER JOIN hinhanh AS ha ON sp.id = ha.thuocve AND ha.loai = 2 AND sp.id = $id";
$data = mysqli_query($connect, $query);

$arr = array();

while($row = mysqli_fetch_assoc($data)){
    array_push($arr, new sanpham($row['id'], $row['tensanpham'], $row['gia'], $row['url'], $row['mota'], $row['danhmuc']));
}

echo json_encode($arr);
?>