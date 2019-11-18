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

$connect = mysqli_connect("localhost","root","123456","shopquanao");
mysqli_set_charset($connect, "utf8");

$query =  "SELECT * FROM donhang WHERE nguoimua = $id";
$data = mysqli_query($connect, $query);

$arr = array();

while($row = mysqli_fetch_assoc($data)){
    array_push($arr, new donhang($row['id'], $row['nguoimua'], $row['tongtien'], $row['hinhanh'], $row['diachi']));
}

echo json_encode($arr);
?>
