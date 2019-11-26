<?php
$id = $_GET['id'];

class donhang{
    function donhang($madonhang, $masanpham, $ten, $gia, $hinhanh, $soluong){
        $this->madonhang = $madonhang;
	$this->masanpham = $masanpham;
	$this->ten = $ten;
        $this->gia = $gia;
	$this->hinhanh = $hinhanh;
        $this->soluong = $soluong;
    }
}

$arr = array();

$connect = mysqli_connect("localhost","root","","shopbanhang");
mysqli_set_charset($connect, "utf8");

$query =  "SELECT ctdh.madonhang, ctdh.masanpham, ctdh.soluongsanpham*sp.gia AS tonggia, sp.tensanpham, ha.url, ctdh.soluongsanpham FROM donhang AS dh INNER JOIN chitietdonhang AS ctdh INNER JOIN sanpham AS sp ON dh.id = ctdh.madonhang AND ctdh.masanpham = sp.id AND dh.id = $id LEFT JOIN hinhanh AS ha ON ctdh.masanpham = ha.thuocve AND ha.loai = 2";
$data = mysqli_query($connect, $query);

$arr = array();

while($row = mysqli_fetch_assoc($data)){
    array_push($arr, new donhang($row['madonhang'], $row['masanpham'], $row['tensanpham'], $row['tonggia'], $row['url'], $row['soluongsanpham']));
}

echo json_encode($arr);
?>
