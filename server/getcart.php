<?php

$idkhachhang = $_GET['khachhang'];


class sanpham{
    function sanpham($id, $ten, $gia, $hinhanh, $mota, $idLoai, $soluong){
        $this->id = $id;
        $this->ten = $ten;
        $this->gia = $gia;
        $this->hinhanh = $hinhanh;
        $this->mota = $mota;
        $this->idLoai = $idLoai;
        $this->soluong = $soluong
    }
}
$arr = array();

$connect = mysqli_connect("localhost","root","123456","shopquanao");
mysqli_set_charset($connect, "utf8");

$query =  "SELECT * FROM giohang AS gh WHERE gh.idkhachhang = $idkhachhang";
$data = mysqli_query($connect, $query);

$arr = array();

while($row = mysqli_fetch_assoc($data)){
    array_push($arr, new sanpham($row['id'], $row['tenSanPham'], $row['giaSanPham'], $row['hinhAnhSanPham'], $row['moTaSanPham'], $row['idLoaiSanPham']));
}

echo json_encode($arr);
?>