<?php

$idkhachhang = $_GET['khachhang'];


class sanpham{
    function sanpham($id, $ten, $gia, $hinhanh, $soluong){
        $this->id = $id;
        $this->ten = $ten;
        $this->gia = $gia;
        $this->hinhanh = $hinhanh;
        $this->soluong = $soluong;
    }
}
$arr = array();

$connect = mysqli_connect("localhost","root","123456","shopbanhang");
mysqli_set_charset($connect, "utf8");

$query =  "SELECT sp.id, sp.tensanpham, sp.nhanhang, sp.gia, sp.mota, sp.danhmuc, sp.luotxem, sp.giamgia, sp.ngaytao, sp.soluong, sp.rate, ha.url FROM giohang AS gh INNER JOIN sanpham AS sp INNER JOIN hinhanh AS ha ON gh.masanpham = sp.id AND gh.makhachhang = $idkhachhang AND sp.id = ha.thuocve AND ha.loai = 2";
$data = mysqli_query($connect, $query);

$arr = array();

while($row = mysqli_fetch_assoc($data)){
    array_push($arr, new sanpham($row['id'], $row['tensanpham'], $row['gia'], $row['url'], $row['soluong']));
}

echo json_encode($arr);
?>