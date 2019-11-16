<?php
class SinhVien{
	function SinhVien($ten, $namsinh, $diachi){
		$this->ten = $ten;
		$this->namsinh = $namsinh;
		$this->diachi = $diachi;
	}
}

$mangSV = array();

array_push($mangSV, new SinhVien("Nguyen Van A", 1999, "Ha Noi"));
array_push($mangSV, new SinhVien("Nguyen Van B", 1999, "Ha Noi"));

echo json_encode($mangSV)
?>