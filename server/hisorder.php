<?php
$id = $_GET['id'];

class donhang{
    function donhang($id, $nguoimua, $tongtien, $hinhanh, $diachi){
        $this->id = $id;
        $this->nguoimua = $nguoimua;
        $this->tongtien = $tongtien;
	$this->hinhanh = $hinhanh;
        $this->diachi = $diachi;
    }
}

$arr = array();

$connect = mysqli_connect("localhost","root","123456","shopbanhang");
mysqli_set_charset($connect, "utf8");

$query =  "SELECT dh.id, dh.trangthai, dh.ngaytao, dh.khachhang, dh.tongtien, CONCAT(dc.diachicuthe, dc.phuongxa, dc.quanhuyen, dc.tinhthanh) as diachiT, ha.url FROM donhang AS dh LEFT JOIN hinhanh AS ha ON dh.id = ha.thuocve AND ha.loai = 5 INNER JOIN diachi as dc ON dh.diachi = dc.makhachhang WHERE khachhang = $id";
$data = mysqli_query($connect, $query);

$arr = array();

while($row = mysqli_fetch_assoc($data)){
    array_push($arr, new donhang($row['id'], $row['khachhang'], $row['tongtien'], $row['url'], $row['diachiT']));
}

echo json_encode($arr);
?>
