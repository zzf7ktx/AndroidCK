SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+07:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

CREATE DATABASE IF NOT EXISTS Shopbanhang;
USE Shopbanhang;
-- --------------------------------------------------------
--
-- Cấu trúc bảng cho bảng `chitietdonhang`
--

CREATE TABLE IF NOT EXISTS chitietdonhang (
  	madonhang int(11) NOT NULL,
  	masanpham int(11) NOT NULL,
  	soluongsanpham int(11) NOT NULL,
	trangthai INT NOT NULL,
	PRIMARY KEY(madonhang, masanpham)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Cấu trúc bảng cho bảng `donhang`
--

CREATE TABLE IF NOT EXISTS donhang (
  	id INT(11) NOT NULL AUTO_INCREMENT,
  	diachi INT NOT NULL,
	khachhang INT NOT NULL,
	tongtien INT NOT NULL,
	trangthai INT NOT NULL,
	ngaytao VARCHAR(11) NOT NULL,
	PRIMARY KEY(id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Cấu trúc bảng cho bảng `khachhang`
--

CREATE TABLE IF NOT EXISTS khachhang (
  	id INT(11) NOT NULL AUTO_INCREMENT,
  	ten NVARCHAR(50) NOT NULL,
  	sodienthoai INT NOT NULL,
  	email VARCHAR(255),
  	matkhau VARCHAR(250) NOT NULL,
  	phanquyen INT(11),
	ngaytao VARCHAR(11),
	PRIMARY KEY(id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Cấu trúc bảng cho bảng `danhmuc`
--

CREATE TABLE IF NOT EXISTS danhmuc (
  	id INT(11) NOT NULL AUTO_INCREMENT,
  	tendanhmuc NVARCHAR(100) NOT NULL,
	danhmuccha INT,
	PRIMARY KEY(id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Cấu trúc bảng cho bảng `sanpham`
--

CREATE TABLE IF NOT EXISTS sanpham (
	id INT(11) NOT NULL AUTO_INCREMENT,
  	tensanpham NVARCHAR(200) NOT NULL,
  	nhanhang NVARCHAR(15) NOT NULL,
  	gia int(15) NOT NULL,
	gianhang INT(11) NOT NULL,
  	mota NVARCHAR(1000) NOT NULL,
  	danhmuc INT(3) NOT NULL,
	luotxem INT,
	giamgia INT,
	ngaytao VARCHAR(11),
	soluong INT,
	rate INT,
	PRIMARY KEY(id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


--
-- Cấu trúc bảng cho bảng `gianhang`
--

CREATE TABLE IF NOT EXISTS gianhang (
	id INT(11) NOT NULL AUTO_INCREMENT,
	luotxem INT,
	chusohuu INT,
	ngaytao VARCHAR(11),
	rate INT,
	PRIMARY KEY(id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Cấu trúc bảng cho bảng `diachi`
--

CREATE TABLE IF NOT EXISTS diachi (
	id INT(11) NOT NULL AUTO_INCREMENT,
	makhachhang INT(11),
	phuongxa NVARCHAR(10),
	quanhuyen NVARCHAR(10),
	tinhthanh NVARCHAR(10),
	diachicuthe NVARCHAR(50),
	PRIMARY KEY(id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Cấu trúc bảng cho bảng `giohang`
--

CREATE TABLE IF NOT EXISTS giohang (
	masanpham INT(11),
	makhachhang INT(11),
	soluong INT(11)
	PRIMARY KEY(masanpham,makhachhang)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Cấu trúc bảng cho bảng `banner`
--

CREATE TABLE IF NOT EXISTS banner (
	id INT(11) NOT NULL AUTO_INCREMENT,
	ten NVARCHAR(10),
	PRIMARY KEY(id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Cấu trúc bảng cho bảng `hinhanh`
--

CREATE TABLE IF NOT EXISTS hinhanh (
	url VARCHAR(200),
	ten NVARCHAR(50),
	thuocve INT(11),
	loai INT,
	PRIMARY KEY(url)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Cấu trúc bảng cho bảng `flashdeals`
--

CREATE TABLE IF NOT EXISTS flashdeals (
 	id int(11) NOT NULL,
  	idSanPham int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Khóa chính
--

--
-- Khóa ngoại
--
ALTER TABLE chitietdonhang
  	ADD CONSTRAINT fk_chitiet_donhang FOREIGN KEY (madonhang) REFERENCES donhang (id);
ALTER TABLE chitietdonhang
  	ADD CONSTRAINT fk_chitiet_sanpham FOREIGN KEY (masanpham) REFERENCES sanpham (id);

ALTER TABLE diachi 
	ADD CONSTRAINT fk_diachi_khachhang FOREIGN KEY (makhachhang) REFERENCES khachhang (id);
ALTER TABLE gianhang
  	ADD CONSTRAINT fk_gianhang_khachhang FOREIGN KEY (chusohuu) REFERENCES khachhang (id);
ALTER TABLE danhmuc
  	ADD CONSTRAINT fk_danhmuc_danhmuc FOREIGN KEY (danhmuccha) REFERENCES danhmuc (id);
ALTER TABLE sanpham
  	ADD CONSTRAINT fk_sanpham_danhmuc FOREIGN KEY (danhmuc) REFERENCES danhmuc (id);
ALTER TABLE sanpham
  	ADD CONSTRAINT fk_sanpham_gianhang FOREIGN KEY (gianhang) REFERENCES gianhang (id);

ALTER TABLE giohang
  	ADD CONSTRAINT fk_giohang_khachhang FOREIGN KEY (makhachhang) REFERENCES khachhang (id);
ALTER TABLE giohang
  	ADD CONSTRAINT fk_giohang_sanpham FOREIGN KEY (masanpham) REFERENCES sanpham (id);



INSERT INTO danhmuc VALUES
(NULL, 'Giày', NULL),
(NULL, 'Nón', NULL),
(NULL, 'Áo', NULL),
(NULL, 'Quần', NULL),
(NULL, 'Dép', NULL);

INSERT INTO khachhang VALUES
(NULL, 'admin', 1321231,'admin@gmail.com', '21232f297a57a5a743894a0e4a801fc3', 1, '12-12-2019'),
(NULL, 'Dương Trí Tuệ', 132113,'tue@gmail.com', '29034b2eefb2a581a7c7a11cfc307776', NULL, '12-12-2019');

INSERT INTO gianhang VALUES
(NULL, 1, 1, '13-12-2019', NULL);

INSERT INTO diachi VALUES
(NULL, 1, 'A', 'B', 'C', '1/1'),
(NULL, 2, 'X', 'Y', 'Z', '1/4');

INSERT INTO sanpham VALUES
(1, 'Giày thể thao nam sneaker màu trắng', 'A', 50000, 1, 'Mã sản phẩm : CDT\r\nThiết kế thời trang- Màu sắc: Trắng- Dễ phối đồ\r\nChất liệu cao cấp, khử mùi, thoáng khí\r\nĐường may tinh tế- Êm và ôm chân\r\nSản phẩm được đảm bảo chất lượng\r\nKiểu dáng phong cách, trẻ trung, ấn tượng\r\nĐế cao su non đúc, tạo cảm giác thoái mái khi đi.\r\nSize: 38, 39, 40, 41, 42, 43', 1, 1, NULL, '13-12-2019', 2, NULL),
(2, 'Giày Sneaker Nam Năng Động Màu Xám - Pettino P003', 'Pettino', 170000, 1, 'Thiết kế trẻ trung\r\nDễ dàng phối trang phục\r\nĐồng kiểm: xem hàng khi nhận hàng\r\nCOD Thanh toán khi nhận hàng\r\nGiao hàng nhanh\r\nGiao hàng tiết kiệm\r\nChất liệu cao cấp\r\nMũi giày tròn\r\nĐế bằng cao su tổng hợp; xẻ rãnh chống trơn trượt', 1, 1, NULL, '13-12-2019', 2, NULL),
(3, 'Giày Nam - Giày Nam - Giày Nam - Giày Nam - T0101', 'T0101', 229000, 1, 'Giày có 2 màu vàng và đen đỏ.\r\nFull size từ 39 đến 43.\r\nĐế giày là cao su non đúc nguyên khối, thân giày là lớp vải xốp kết hợp da PU.\r\nGiày có form chuẩn nên bình thường các bạn đi size nào thì cứ chọn size đấy thôi.', 1, 1, NULL, '13-12-2019', 2, NULL);


INSERT INTO donhang VALUES
(NULL, 1, 2, 150000, 4, '24-12-2019');

INSERT INTO chitietdonhang VALUES
(1, 1, 3, 4);





-- Đang đổ dữ liệu cho bảng `flashdeals`
--

INSERT INTO flashdeals VALUES
(NULL, 1),
(NULL, 2),
(NULL, 3);

INSERT INTO hinhanh VALUES
('https://photo-3-baomoi.zadn.vn/w1000_r1/18/03/01/105/25103888/1_86074.jpg', 'Dep1', 5, 1),
('https://4menshop.com/images/thumbs/2015/09/quan-tay-cong-so-den-qt20-4885-slide-1.jpg', 'Quan1', 4, 1),
('https://vn-test-11.slatic.net/p/7/ao-thun-nam-tron-co-gian-co-tron-new-fashion-xanh-den-8818-2623629-(9, bb08843728d6696d0016bae70dafe46-catalog.jpg_340x340q80.jpg_.webp', 'Ao1', 3, 1),
('https://product.hstatic.net/1000230642/product/dsc_0305_805166acb1664f42b529cf1a4e8b232b_1024x1024.jpg', 'Non1', 2, 1),
('https://i2.wp.com/hnbmg.com/wp-content/uploads/2016/03/Rick-Owens-By-Drkshdw-shoes-FW-14-Moody-Sneakers-Mens-Black-010606.jpg?resize=480%2C360', 'Giay1', 1, 1),
('https://vn-test-11.slatic.net/shop/b25043883b2b57917aa3c43002b106be.jpeg', 'Giay2', 1, 2),
('https://vn-live-01.slatic.net/original/77897ea9fe3b077de3e2de03f59a83d5.jpg', 'Giay3', 2, 2),
('https://media3.scdn.vn/img2/2017/12_25/E3otSP_simg_de2fe0_500x500_maxb.png', 'Giay4', 3, 2);



COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
