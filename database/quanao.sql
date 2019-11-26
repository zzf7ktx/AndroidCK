SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+07:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

CREATE DATABASE IF NOT EXISTS shopbanhang;
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
	soluong INT(11),
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
(NULL, 'Giày thể thao nam sneaker màu trắng', 'A', 50000, 1, 'Mã sản phẩm : CDT\r\nThiết kế thời trang- Màu sắc: Trắng- Dễ phối đồ\r\nChất liệu cao cấp, khử mùi, thoáng khí\r\nĐường may tinh tế- Êm và ôm chân\r\nSản phẩm được đảm bảo chất lượng\r\nKiểu dáng phong cách, trẻ trung, ấn tượng\r\nĐế cao su non đúc, tạo cảm giác thoái mái khi đi.\r\nSize: 38, 39, 40, 41, 42, 43', 1, 1, NULL, '13-12-2019', 2, NULL),
(NULL, 'Giày Sneaker Nam Năng Động Màu Xám - Pettino P003', 'A', 170000, 1, 'Thiết kế trẻ trung\r\nDễ dàng phối trang phục\r\nĐồng kiểm: xem hàng khi nhận hàng\r\nCOD Thanh toán khi nhận hàng\r\nGiao hàng nhanh\r\nGiao hàng tiết kiệm\r\nChất liệu cao cấp\r\nMũi giày tròn\r\nĐế bằng cao su tổng hợp; xẻ rãnh chống trơn trượt', 1, 1, NULL, '13-12-2019', 2, NULL),
(NULL, 'Giày Nam - Giày Nam - Giày Nam - Giày Nam - T0101', 'A', 229000, 1, 'Giày có 2 màu vàng và đen đỏ.\r\nFull size từ 39 đến 43.\r\nĐế giày là cao su non đúc nguyên khối, thân giày là lớp vải xốp kết hợp da PU.\r\nGiày có form chuẩn nên bình thường các bạn đi size nào thì cứ chọn size đấy thôi.', 1, 1, NULL, '13-12-2019', 2, NULL),
(NULL, 'Giày nam trắng thể thao sneaker- khuyến mãi 50% chào mừng Quốc Khánh', 'A', 103000, 1, 'Mã sản phẩm : GST09\r\nThiết kế thời trang- độn đế bí mật cao 6 cm\r\nMàu sắc: Trắng- Dễ phối đồ\r\nChất liệu cao cấp, khử mùi, thoáng khí\r\nĐường may tinh tế- Êm và ôm chân\r\nSản phẩm được đảm bảo chất lượng\r\nKiểu dáng phong cách, trẻ trung, ấn tượng\r\nĐế cao su non đúc, tạo cảm giác thoái mái khi đi.\r\nSize: 38, 39, 40, 41, 42, 43', 1, 1, NULL, '13-12-2019', 2, NULL),
(NULL, 'Giày thể thao nam Muidoi G145 (Đen)', 'A', 90000, 1, 'Mạnh mẽ nam tính cùng Giày thể thao nam đế trắng sạch sẽ không bám bẩn,Phần đế chất liệu cao su cao cấp mềm mại, siêu nhẹ không đau chân khi vận động mạnh,thân giày là sự kết hợp giữa vải lưới và logo hãng thoáng khí chống hôi chân hay hầm bí khi mang.Từng đường may được trau chuốt kỹ lưỡng nhằm tạo nên một đôi giày hoàn hảoForm giày mới, phù hợp với hầu hết kiểu chân.Đế và thân được dán chắc bằng máy áp keo đảm bảo độ bền trên 1 năm.', 1, 1, NULL, '13-12-2019', 2, NULL),

(NULL, 'Nón lưỡi trai nam thời trang 2018', 'A', 30000, 1, 'Mũ lưỡi trai trơn các màu.\r\nChất liệu kaki.\r\nKiểu dáng trẻ đẹp.\r\nHàng có sẵn\r\nNónbền đẹp, không tạo cảm giác hầm, bức bí khi đội nón.\r\nNón được thiết kế đẹp mắt, thời trang với những đường may tỉ mỉ sẽ giúp bạn tự tin hơn trong những ngày nắng\r\nPhía sau có phần điều chỉnh kích thước phù hợp với từng kích cỡ của mỗi người, rất tiện lợi khi sử dụng.\r\nNón nam nữ thể thaocó thiết kế nón lưỡi trai cổ điển nhưng cá tính, vành nón trẻ trung và phong cách, giúp bạn luôn nổi bật khi xuống phố\r\nCó thể kết hợp cùng những item như áo thun, quần jeans, quần short năng động hay những item nữ tính, trẻ trung để luôn tự tin trong nhiều hoàn cảnh', 2, 1, NULL, '13-12-2019', 2, NULL),
(NULL, 'Nón Bucket Chữ Nhật Cá Tính - Mũ Tai Bèo Vải Tốt', 'A', 50000, 1, 'Mẫu mũ tai bèo Bucket siêu Hot hè 2018 siêu ngộ nghĩnh với phần vành mềm và không quá rộng, loại mũ tai bèo đã thịnh hành từ những năm 90 còn gọi là “bucket hat” trong tiếng Anh. Hàng chục năm trôi qua chiếc mũ tai bèo có chăng chỉ là phong phú hơn về chất liệu cũng như màu sắc, họa tiết trên mũ.', 2, 1, NULL, '13-12-2019', 2, NULL),
(NULL, 'Nón - Nón kết', 'A', 150000, 1, 'Chất liệu KAKI bền đẹp. Thiết kế form nón ôm vừa vặn, bố cục họa tiết độc đáo sáng tạo, mang lại vẻ cá tính cho bạn trẻ. ĐIỂM NỔI BẬT Nón lưỡi trai nam nữ thiết kế logo được thêu nổi ở chính giữa nón cùng với họa tiết ở bên phải nón thật thời trang và phong cách Thiết kế theo kiểu lưỡi trai cổ điển, phần lưỡi trai cong tạo nên nét mạnh mẽ và thời trang cho sản phẩm Sau nón có nút điều chỉnh cho vừa với khuôn đầu mỗi người với form nón ôm, vừa vặn và đẹp mắt. Với chất liệu kaki bền đẹp, cho form nón đứng với đường may chắc chắn, đồng thời tạo độ thông thoáng cho người dùng Những ngày hè nắng nóng, khi ra ngoài, ai cũng phải trang bị cho mình những sản phẩm có khả năng chống nắng tốt. Từ áo khoác, khẩu trang tới găng tay, tất vớ...và chắc rằng, chúng ta không thể bỏ qua những chiếc nón. Hiện diện trong cuộc sống của chúng ta từ rất lâu đời, đến nay, hầu như ai cũng có cho mình một chiếc nón để diện ngày nắng. Bạn chọn nón parama sang trọng hay mũ lưỡi trai trẻ trung, bình dân? Cùng xem m', 2, 1, NULL, '13-12-2019', 2, NULL),
(NULL, 'NÓN KẾT NÓN KẾT - SNAPBACK DA TRƠN', 'A', 82000, 1, '[XẢ HÀNG] NÓN SNAPBACK DA TRƠN [GIÁ SỐC]\r\nNÓN KẾT\r\n\r\nNón snapback nam nữ Mister RGM: Chất liệu da PU bền đẹp tạo dáng nón cứng cáp, chắc chắn. Thiết kế đơn giản với kiểu dáng thời trang, sành điệu, mang đến phong cách trẻ trung, năng động ĐIỂM NỔI BẬT Nón snapback nam nữ được thiết kế đơn giản với kiểu dáng thời trang, sành điệu, mang đến phong cách trẻ trung, năng động Mặt trước nón in chữ hoa trên nền , nổi bật mà tinh tế, đẹp mắt, cho bạn thêm cá tính, phong cách Phía sau có nút bấm nhựa, giúp bạn thoải mái điều chỉnh độ rộng phù hợp với vòng đầu của mình Màu trắng đơn giản, trẻ trung, không lỗi mốt, rất dễ phối đồ, phù hợp với nam và nữ Chất liệu bền đẹp tạo dáng nón cứng cáp, chắc chắn mang tới cho bạn sự thích thú, an tâm khi sử dụng Nếu bạn là một người theo đuổi các xu hướng thì không có lí do gì để có thể bỏ qua chiếc nón snapback - một trong những item tiêu biểu đại diện cho phong cách swag trẻ trung, khỏe khoắn đang cực \"hot\" đối với giới trẻ hiện nay. Cho tới nay, nón snapb', 2, 1, NULL, '13-12-2019', 2, NULL),
(NULL, 'NÓN KẾT NÓN KẾT - NÓN JEAN 2', 'A', 75000, 1, 'Chất liệu KAKI bền đẹp. Thiết kế form nón ôm vừa vặn, bố cục họa tiết độc đáo sáng tạo, mang lại vẻ cá tính cho bạn trẻ. ĐIỂM NỔI BẬT Nón lưỡi trai nam nữ thiết kế logo được thêu nổi ở chính giữa nón cùng với họa tiết ở bên phải nón thật thời trang và phong cách Thiết kế theo kiểu lưỡi trai cổ điển, phần lưỡi trai cong tạo nên nét mạnh mẽ và thời trang cho sản phẩm Sau nón có nút điều chỉnh cho vừa với khuôn đầu mỗi người với form nón ôm, vừa vặn và đẹp mắt. Với chất liệu kaki bền đẹp, cho form nón đứng với đường may chắc chắn, đồng thời tạo độ thông thoáng cho người dùng Những ngày hè nắng nóng, khi ra ngoài, ai cũng phải trang bị cho mình những sản phẩm có khả năng chống nắng tốt. Từ áo khoác, khẩu trang tới găng tay, tất vớ...và chắc rằng, chúng ta không thể bỏ qua những chiếc nón. Hiện diện trong cuộc sống của chúng ta từ rất lâu đời, đến nay, hầu như ai cũng có cho mình một chiếc nón để diện ngày nắng. Bạn chọn nón parama sang trọng hay mũ lưỡi trai trẻ trung, bình dân? Cùng xem m', 2, 1, NULL, '13-12-2019', 2, NULL),
(NULL, 'Áo nữ ', 'A', 199000, 1, 'Sét bộ váy len hàng Quảng Châu xịn với Phong cách nước Anh hot hit của năm với màu sắc nhã nhặn, nhiều lựa chọn, phom dáng xinh đẹp, kiểu dáng thích hợp cho bạn gái  luôn nổi bật, thích hợp đến văn phòng, dạo phố, đi chơi, đi học…', 3, 1, NULL, '13-12-2019', 2, NULL),
(NULL, 'Áo nữ xanh', 'A', 110000, 1, 'Áo Quảng Châu có thiết kế trẻ trung, hiện đại, với form tôn dáng, thoải mái\r\nDễ mix kèm trang phục cho bạn gái nét ngoài năng động, khỏe khoắn.\r\nChất liệu: len mỏng thoáng mát, co giãn.\r\nXuất xứ: Quảng Châu.\r\nKích thước: (cm)', 3, 1, NULL, '13-12-2019', 2, NULL),
(NULL, 'Quần Jogger', 'A', 95000, 1, 'Free size: 65kg trở xuống mặc vừa\r\n\r\nQuần chất nỉ 100% siêu mịn đẹp.\r\nForm chuẩn, mặc thoải mái, co giãn tốt.\r\nChất liệu cao cấp, mát, thấm hút mồ hôi tốt.\r\nHàng cam kết như hình, ảnh chụp thật sản phẩm.\r\nHàng cam kết giá tốt.\r\nVới chất liệu tốt sẽ đem đến cho quý khách sự hài lòng.\r\nChất liệu: Vải Nỉ bông, co dãn, thấm hút mồ hôi tốt, không nóng bức khi vận động nhiều, không nhăn, không co rút sau khi giặt.\r\n\r\nChất liệu mềm sang trọng, tạo sự nổi bật, quyến rũ cho người mặc\r\n', 4, 1, NULL, '13-12-2019', 2, NULL),
(NULL, 'Quần Jogger - DT65LL552', 'A', 99000, 1, 'Mo ta ', 4, 1, NULL, '13-12-2019', 2, NULL),
(NULL, 'Dép - SPU-X4', 'A', 135000, 1, 'QUAI: là lớp da PU cao cấp mang vừa mềm và êm chân\r\nĐẾ : cao su thiên nhiên 100%, cao su có tính năng ma sát cao chống trơn trượt an toàn cho người sử dụng\r\nChiều cao: gót 1.5cm, mũi 1cm\r\nNặng từ 600gram đến 750 gram tùy thuộc size dép\r\nVới thiết kế Dép nam vừa đơn giản vừa hiện đại , phù hợp trong các hoạt động: đi chơi, đi làm, đi học,...\r\nViệc lựa chọn cho mình một đôi dép vừa thích hợp đi chơi, du lịch, dạo phố, hay gặp gỡ bạn bè vừa mang đến phong cách trẻ trung, năng động là điều mà phái mạnh luôn quan tâm, Với đôi dép quai bố đầy cá tính phối cùng những chiếc quần jean rách bụi bặm, cho đến những chiếc quần short sắc màu trẻ trung đem đến cho người mặc những phong cách thời trang riêng.', 5, 1, NULL, '13-12-2019', 2, NULL),
(NULL, 'Dép đi trong nhà ', 'A', 57000, 1, ' - Tên sản phẩm: Dép bông đi trong nhà cao cấp\r\n- Chất liệu: lông nhân tạo khi mang vào tạo cho người mang cảm giác mềm mại, êm ái với da\r\n\r\n- Dép bông đi trong nhà sử dụng vải bông cao cấp được thiết kế với 3 lớp: vải lông, lớp xốp đàn hồi và nhựa dẻo tổng hợp.\r\n\r\n- Đế gai cao su, bám nền gạch, chống trơn trượt, may bằng chỉ nylon, chắc chắn khi tiếp xúc với nước.\r\n\r\nMàu sắc : nhiều màu shop gửi ngẫu nhiên. Quý khách có thể lưu ý màu sắc.', 5, 1, NULL, '13-12-2019', 2, NULL),
(NULL, 'Giày Cao Gót', 'A', 1800000, 1, 'Da Thật Màu Trắng Mẫu Mới Màu Sắc Phù Hợp Với Giày Làm Việc Giày Cao Gót\r\nThanh toán khi nhận hàng\r\n7 ngày đổi trả dễ dàng', 1, 1, NULL, '13-12-2019', 2, NULL),
(NULL, 'Giày Bít Nhọn Thời Trang 5050BN0067 Sablanca', 'A', 400000, 1, 'Mã sản phẩm: 5050BN0067\r\nKiểu dáng: Giày Bít Nhọn\r\nChất liệu: Da tổng hợp\r\nĐộ cao: 3cm\r\nMàu sắc: Beige, Xanh Rêu, Đen\r\nKích cỡ: 35-36-37-38-39 Giày Bít Nhọn thời trang mang thương hiệu Sablanca với thiết kế trẻ trung , năng động mang lại cho bạn cảm giác thoải mái trong từng bước chân .\r\n', 1, 1, NULL, '13-12-2019', 2, NULL),
(NULL, 'Giày Thể Thao Nữ Hàn Quốc', 'A', 280000, 1, 'Giày thể thao nữ Hàn Quốc\r\nUpper material: Nhiều Chất liệuBên trong vật liệu: bông\r\nChiều cao đế: 3cm\r\nChất liệu đế: cao su\r\nMàu sắc: Xám, Nâu\r\nKích thước: 39, 40, 41, 42, 43', 1, 1, NULL, '13-12-2019', 2, NULL),
(NULL, 'Giày sneaker nữ CHẤT ĐẸP 3 lỗ thoáng khí HAPU', 'A', 250000, 1, 'Là mẫu giày đang được các bạn nữ rất ưa chuộng hiện nay bởi những đặc tính nổi trội:\r\nKiểu dáng thời trang, năng động, dễ phối đồ.\r\nChất liệu tổng hợp nhẹ, êm và thoáng khí; đế cao su tổng hợp nên mềm và rất êm chân.\r\nMàu sắc được kết hợp một cách tinh tế tạo điểm nhấn và sự nổi bật cho người dùng.\r\nPhù hợp với mọi hoạt động: đi du lịch, đi dạo, đi học, chơi thể thao, tập GYM tập YOGA...\r\nSản phẩm có 2 phiên bản màu: đen, hồng.', 1, 1, NULL, '13-12-2019', 2, NULL),
(NULL, 'GIÀY THỂ THAO NỮ ĐẸP', 'A', 360000, 1, 'Thiết kế trẻ trung.\r\nĐường may tỉ mỉ.\r\nDễ dàng phối trang phục.\r\nChất liệu cao cấp.\r\nMũi giày tròn.\r\nĐế bằng cao su tổng hợp; xẻ rãnh chống trơn trượt.', 1, 1, NULL, '13-12-2019', 2, NULL),
(NULL, 'GIÀY CONVERSE CLASSIC CỔ THẤP ĐEN - HÀNG VIỆT NAM XUẤT KHẨU', 'A', 350000, 1, 'Chất liệu: Vải\r\nGiới tính: Nam, Nữ\r\nMàu sắc: Trắng Đen\r\nKiểu dáng trẻ, phong cách hiện đại\r\nLogo thương hiệu được bố trí nổi bật ở phần gót giày\r\nGiày thể thao hàng VNXK- Giày đẹp chuẩn như hình\r\nKhách được kiểm tra hàng trước khi thanh toán -Giao hàng tại nhà, Thanh toán tại nhà (COD)\r\nVUI LÒNG INBOX SHOP ĐỂ BIẾT THÊM THÔNG TIN VỀ SẢN PHẨM\r\nCẢM ƠN CÁC BẠN ĐÃ DÀNH THỜI GIAN TÌM HIỂU VỀ SẢN PHẨM NHÉ', 1, 1, NULL, '13-12-2019', 2, NULL),
(NULL, 'Giày Thể Thao Nam Sports Sneaker Ssk698', 'A', 698000, 1, 'Kiểu dáng bền đẹp\r\nPhong cách và cá tính\r\nĐường may tỉ mỉ\r\nGiày Thể Thao Nam Sports Sneaker SSK698 với những phân khúc thị trường và phong cách khác nhau đang có mặt trên thế giới. Chắc Chắn bền đẹp  và phong cách có thể giúp bạn thể hiện được tuyên ngôn bản thân. Luôn tự tin và khôn ngoan thể hiện phong cách với những kiểu trang phục và hoàn cảnh phù hợp Hãy sở hữu ngay Giày Thể Thao Nam Sports Sneaker SSK698', 1, 1, NULL, '13-12-2019', 2, NULL),
(NULL, '[ FULL BOX + TẶNG VỚ ] GIÀY THỂ THAO FILA - HÀNG VIỆT NAM XUẤT KHẨU', 'A', 550000, 1, 'Giày thể thao FILA hàng VNXK- Giày đẹp chuẩn như hình.\r\nShop Hỗ trợ đổi trả size nếu không vừa ( Khách hàng lưu ý giữ sạch giày như lúc đầu - mang vớ khi thử giày để giữ sạch giày)\r\nShop tặng hộp.\r\nKhách được kiểm tra hàng trước khi thanh toán -Giao hàng tại nhà, Thanh toán tại nhà (COD)\r\nVUI LÒNG INBOX SHOP ĐỂ BIẾT THÊM THÔNG TIN VỀ SẢN PHẨM ( Biểu tượng icon chat gần chỗ tên nhà bán hàng các bạn nhé...)\r\nCÁM ƠN CÁC BẠN ĐÃ DÀNH THỜI GIAN TÌM HIỂU VỀ SẢN PHẨM NHÉ <3', 1, 1, NULL, '13-12-2019', 2, NULL),
(NULL, 'Giày Sneak Nam Sodoha SMG65890W -White', 'A', 295000, 1, 'Chất liệu Cao Cấp , Đế Cao su ,phong cách thời trang; khó bám bẩn\r\nKiểu giày thể thao thắt dây năng động\r\nCổ giày có lót mút êm chân\r\nGiày được may đắp những miếng da PU ở mũi và gót giày để trang trí\r\nMũi giày ôm theo dáng bàn chân; không gây khó chịu trong lúc di chuyển\r\nĐế giày cao 3 cm; chất liệu cao su; được khắc họa tiết để tăng độ ma sát; chống trơn trượt', 1, 1, NULL, '13-12-2019', 2, NULL),
(NULL, 'Giày Boots Đi Mưa Rando OBPS-04 Size L (Đen)', 'A', 120000, 1, 'Giày Boots đi mưa RANDO với chất liệu PVC chính phẩm bền bỉ, giúp che mưa an toàn và tiện lợi cho giày của bạn.\r\nLàm bằng chất liệu nhựa cao cấp\r\nKiểu dáng thiết kế thời trang, độc đáo\r\nThương hiệu: Rando\r\nXuất xứ: Việt Nam', 1, 1, NULL, '13-12-2019', 2, NULL),
(NULL, 'Giày sneaker nam thể thao - A123 (trắng pha đen) ', 'A', 550000, 1, 'Giày vải lưới thoáng mát\r\nĐế tổng hợp nhẹ nhàng, êm ái, dẻo dai\r\nLên chân êm ái, di chuyển nhẹ nhàng\r\nGiày nhập Quảng Châu chất đẹp, chuẩn hình\r\nThanh toán khi nhận hàng\r\nXem hàng trước khi nhận hàng\r\nGiao hàng nhanh, phí giao hàng rẻ', 1, 1, NULL, '13-12-2019', 2, NULL),
(NULL, 'GIÀY CẦU LÔNG CHÍ PHÈO CAO CẤP CL015', 'A', 520000, 1, 'GIÀY CẦU LÔNG NAM NỮ TRẮNG XANH\r\nĐặc điểm nổi bật\r\nChất liệu từ cao su thiên nhiênSản phẩm như hìnhSize từ 38 đến 43Bảo hành 1 tháng từ ngày mua hàngĐế giày thiết kế chuyên dụng cho sân cỏ nhân tạoĐộ ma sát tốt - hỗ trợ xử lý bóng bằng đế giàySắc màu thời trang, họa tiết ấn tượngPU Cao Cấp cùng lớp vải lưới giúp thoáng khí . Sole: chất liệu chính là Phylon ưu điểm nhẹ và rất đàn hồi kết hợp mặt tiếp đất cao su thiên nhiên chống trơn trượt.Insole : cải tiến với chất liệu dày, cao cấp được xem như một phần của hệ thống chống sốc , hỗ trợ khi tập luyện.', 1, 1, NULL, '13-12-2019', 2, NULL),
(NULL, 'Giày Đá Bóng Prowin Cao Cấp Mũi Tên Size Người Lớn - Nhà Phân Phối Chính Từ Hãng', 'A', 396000, 1, 'Đặc điểm của Giày bóng đá Prowin- Giày bóng đá cao cấp Prowin được thiết kế và sản xuất bởi Công Ty TNHH Nam Bình tại Việt Nam. Prowin là mẫu giày bóng đá chính hãng và được ưa chuộng nhất đối với các sản phẩm giày bóng đá chính hãng giá rẻ tại Việt Nam.\r\n- Kiểu dáng : Ôm\r\n- Mùa : 4 mùa\r\n- Điểm nhấn : Mũ giày được làm băng PU \"\" poly Urethane \"\" nhẹ và đặc biệt rất mềm mại, không gây hôi chân, thân thiện với môi trường.Size trẻ em: 33,34,35,36,37\r\nSize người lớn: 38,39,40,41,42,43,44Mục đích sử dụng : Đá banh- Shop đảm bảo sản phẩm giày đá banh bán ra đúng với chất lượng như cam kết.\r\n- Tặng 1 đôi vớ cao cổ bóng đá khi mua sản phẩm\r\n- Bảo Hành: 365 ngàyTại hệ thống cửa hàng chúng tôi có đủ các chất liệu với từng mức giá tiền phù hợp với các bạn lựa chọn.#prowin #bongda #giaydabong #giay #giayprowin #prowinFX #dabong', 1, 1, NULL, '13-12-2019', 2, NULL),
(NULL, 'Giày Đá Bóng Prowin S50 - Nhà Phân Phối Chính Từ Hãng', 'A', 396000, 1, 'Kiểu dáng : Ôm\r\n- Mùa : 4 mùa\r\n- Điểm nhấn : Mũ giày được làm băng PU \"\" poly Urethane \"\" nhẹ và đặc biệt rất mềm mại, không gây hôi chân, thân thiện với môi trường.Size trẻ em: 33,34,35,36,37\r\nSize n', 1, 1, NULL, '13-12-2019', 2, NULL),
(NULL, 'Muasdjk', 'A', 178000, 1, 'Chất liệu: Polyester\r\nMàu sắc: Đen\r\nMũ Deepth: 12 cm\r\nMũ Chu Vi: 55 cm-60 cm\r\nDịp sử dụng phù hợp:: Thường Ngày\r\nKiểu: Mũ nồi\r\nPatter: Chắc Chắn', 2, 1, NULL, '13-12-2019', 2, NULL),
(NULL, 'Unisex Men Women Soft Leather Baseball Cap Biker Adjustable Outdoor Sports Hats White - intl', 'A', 193000, 1, 'Size:Adjustable\r\nGender: Unisex\r\nMaterial: Faux Leather\r\nCrowns: Flattened\r\nCanopy Shape: Curved\r\nHead Circumference: About 57-64cm\r\nHat Brim Width: About 7cm\r\nHat Depth: About 11cm', 2, 1, NULL, '13-12-2019', 2, NULL),
(NULL, 'Unisex Men Women Soft Leather Baseball Cap Biker Adjustable Outdoor Sports Hats Black - intl', 'A', 178000, 1, 'Size:Adjustable\r\nGender: Unisex\r\nMaterial: Faux Leather\r\nCrowns: Flattened\r\nCanopy Shape: Curved\r\nHead Circumference: About 57-64cm\r\nHat Brim Width: About 7cm\r\nHat Depth: About 11cm', 2, 1, NULL, '13-12-2019', 2, NULL),
(NULL, 'Nón kết,nón lưỡi trai,mũ nón HOCKEY', 'A', 140000, 1, 'SẢN PHẨM:\r\n- MÀU SẮC: Như hình\r\n- CHẤT LIỆU: Kaki Khóa Đồng Rút\r\n- PHÂN LOẠI: Nón Lưỡi Trai/ Nón Kết\r\n- DÀNH CHO: Nam/Nữ\r\n- PHONG CÁCH: Bụi bặm - Cá tính - Độc Đáo. Nón CUCI CON ONG chắc chắn là một phụ kiện thời trang đi kèm không thể thiếu cho bạn trẻ năng động, mạnh mẽ.\r\n*Sản phẩm đẹp như hình. Giá thành cạnh tranh nhất thị trường.\r\n---\r\nCÁCH BẢO QUẢN:\r\n- Các bạn KHÔNG cho sản phẩm vào máy giặt, khi chà rửa chỉ nên dùng bàn chải nhỏ chà nhẹ nhàng. (Sử dụng bột giặt, nước giặt)\r\n- Không ngâm nước xả, không dùng thuốc tẩy!\r\n#nonluoitrai #nonnam #nondep #nonnu #muluoitrai #musnapback #nonsnapback #snapback #hiphop #mudep #non', 2, 1, NULL, '13-12-2019', 2, NULL),
(NULL, 'Nón nam ATE Racing Team cá tính Thời Trang K & T', 'A', 79000, 1, 'Thương hiệu OEM SKU OE680FAAAD1FZ3VNAMZ-26840555 Dòng sản phẩm 2018 Loại bảo hành Bằng Hóa đơn mua hàng Các phong cách vành nón Vành cong Phân Loại Thời Trang Cơ Bản Tất Yếu Hat Style Mũ lưỡi trai kín & hở đầu Thời gian bảo hành 6 tháng\r\nBộ sản phẩm bao gồm	1 nón', 2, 1, NULL, '13-12-2019', 2, NULL),
(NULL, 'NÓN VẢI DÙ- NÓN KẾT VẢI DÙ - NÓN LƯỠI TRAI VẢI DÙ NAM NỮ H412 EVEREST(Nhiều màu)', 'A', 101000, 1, 'Nónlưỡi trai đượcthiết kế dạng lưỡi trai cổ điển, cá tính, phù họp với mọi đối tượng và xu hướng thời trang hiện đại đang được ưa chuộng\r\nHọa tiết chữ thêu nổi cá tính, màu sắc tươi trẻ, phù hợp với các bạn trẻ thích thể hiện bản thân\r\nPhía sau có phần điều chỉnh kích thước phù hợp cới từng kích cỡ của mỗi người, không cần lo lắng sẽ quá rộng hay quá chật\r\nĐường may và thêu cẩn thận, tỉ mỉ, đẹp mắt. Chất liệu kaki thoáng mát, không tạo cảm giác hầm, bức bí khi đội nón\r\nCó 7 màu cho bạn lựa chọn theo sở thích như đỏ, đen trắng,... Có thể kết hợp với quần short, túi đeo chéo, vòng tay,... khi đi chơi với bạn bè, gia đình', 2, 1, NULL, '13-12-2019', 2, NULL),
(NULL, 'Nón bucket tai bèo,nón vành,mũ vành OFF WHITE nam nữ', 'A', 27000, 1, 'SẢN PHẨM:\r\n- MÀU SẮC: Như hình\r\n- CHẤT LIỆU: Kaki Khóa Đồng Rút\r\n- PHÂN LOẠI: Nón Lưỡi Trai/ Nón Kết\r\n- DÀNH CHO: Nam/Nữ\r\n- PHONG CÁCH: Bụi bặm - Cá tính - Độc Đáo. Nón KẾT BALEN hắc chắn là một phụ kiện thời trang đi kèm không thể thiếu cho bạn trẻ năng động, mạnh mẽ.\r\n*Sản phẩm đẹp như hình. Giá thành cạnh tranh nhất thị trường.\r\n---\r\nCÁCH BẢO QUẢN:\r\n- Các bạn KHÔNG cho sản phẩm vào máy giặt, khi chà rửa chỉ nên dùng bàn chải nhỏ chà nhẹ nhàng. (Sử dụng bột giặt, nước giặt)\r\n- Không ngâm nước xả, không dùng thuốc tẩy!', 2, 1, NULL, '13-12-2019', 2, NULL),
(NULL, 'Nón Kết Màu Xanh Rêu Trơn', 'A', 100000, 1, 'Màu sắc: Xanh Rêu\r\nChất liệu:80% cotton, 20% nylon. Thoáng mát, chống nắng nóng cực tốt.\r\nGồm các size: L/ XL/ XXL\r\nThiết kế nón có phần che nắng phía sau gáy. Có thể mở ra hoặc gập lại tùy thích.', 2, 1, NULL, '13-12-2019', 2, NULL),
(NULL, 'Nón Kết Màu Camouflage', 'A', 100000, 1, 'Màu sắc, họa Tiết: Rằn Ri\r\nChất liệu: Thoáng mát, chống nắng nóng cực tốt.\r\nGồm các size: L/ XL/ XXL\r\nThiết kế nón có phần che nắng phía sau gáy. Có thể mở ra hoặc gập lại tùy thích.', 2, 1, NULL, '13-12-2019', 2, NULL),
(NULL, 'Nón nam thể thao KEIHIN Nón Trơn Store 440', 'A', 149000, 1, 'Giới Thiệu Sản Phẩm : Nón thể thao KEIHIN Thời Trang Nón Trơn Store (Đen TT440) – Hãng phân phối chính thức\r\n\r\nThông tin cơ bản của sản phẩm\r\n\r\nThương Hiệu : Nón Trơn Store\r\n\r\nChất liệu : Kaki tốt\r\n\r\nMẫu mã : Nón lưỡi trai\r\n\r\nMàu : Đen\r\n\r\nHình dáng : Thể thao\r\n\r\nTrọng lượng : 150 Gram\r\n\r\nNơi Sản Xuất : Việt Nam\r\n\r\nGiới Thiệu Sản Phẩm\r\n\r\nTrẻ trung, năng động, cá tính cùng Sản phẩm nón thể thao của thương hiệu Nón Trơn Store. Tông màu sang\r\n\r\ntrọng, đẳng cấp cùng chất liệu cao cấp, mềm mại, không mang đến cảm giác thoải mái , tự tin cho người sử dụng', 2, 1, NULL, '13-12-2019', 2, NULL),
(NULL, 'Bộ 3 áo thun trơn nam form rộng phong cách hàn quốc vải dày mịn AOTHUN102 (Trắng - Đen - Xám)', 'A', 120000, 1, 'Chất liệu thun mềm mại co giãn tốt , thoáng mát\r\nThiết kế thời trang phù hợp xu hướng hiện nay\r\nKiểu dáng đa phong cách, Form rộng Nam nữ đều mặc được\r\nĐường may tinh tế sắc sảo\r\nÁo thun được thiết kế vể đẹp trẻ trung năng động nhưng không kém phần cá tính.\r\nÁo được thiết kế đẹp, chuẩn form, đường may sắc xảo, vải cotton dày, mịn, thấm hút mồ hôi tạo sự thoải mái khi mặc!\r\nDễ dàng phối trang phục , thích hợp đi chơi đi làm đi dạo phố\r\nThích hợp cho sự kết hợp vứi quần jean, sọt,thể thao!\r\nCó đủ các size XS, S, M, L, XL, 2XL,3XL đáp ứng cho mọi khách hàng\r\nMàu sắc: Có đủ các màu Trắng, Xanh Ngọc, Xanh Chuối, Hồng, Cam, Vàng...', 3, 1, NULL, '13-12-2019', 2, NULL),
(NULL, ' Dài Tay nam ', 'A', 235000, 1, 'Loại sản phẩm: Áo Sơ Mi\r\nGiới tính: Nam\r\nChiều Dài tay: Dài tay\r\nÁo Sơ Mi Kiểu: Áo Sơ Mi thông thường\r\nCổ áo: Cổ Bẻ\r\nLoại vải: Vải Rộng\r\nChất liệu: Cotton\r\nPhong cách: Đô Thị thời trang\r\nLoại khóa: Một Hàng Khuy\r\nPhong Cách tay áo: Thông Thường\r\nKiểu Họa tiết: Kẻ Sọc\r\nKích thước: M L XL XXL XXXL 4XL 5XL\r\nCơ Bản Phong Cách: Thanh Niên thời trang\r\nBản mẫu: Mỏng\r\nLoại đơn vị: Cái\r\nTrọng Lượng gói hàng: 0.350 kg (0.77lb.)\r\nKích Thước đóng gói: 40 cm x 30 cm x 4 cm (15.75in x 11.81in x 1.57in)\r\n\r\nKhách hàng thân mến, nếu bạn có bất kỳ câu hỏi nào về sản phẩm của chúng tôi, xin được tự do liên hệ với chúng tôi. chúng tôi sẽ giúp bạn sớm nhất có thể', 3, 1, NULL, '13-12-2019', 2, NULL),
(NULL, 'Áo thun unisex tay lỡ nam nữ', 'A', 99000, 1, 'Áo Thun Tay Lỡ GMK\r\nĐối Tượng : Nam, Nữ .\r\nChất liệu từ Vải Thun Da Cá : Dày, Bền, Chắc chắn.\r\nMàu Sắc 2 màu: Trắng . Đen\r\nKích thước : Áo FREE SIZE dưới 65 kg ( Ngang 50cm, Dài 68 cm).\r\nCao từ 1m50 nặng 40 kg đến 1m75 Nặng 65 kg mặc vừa ( l ĐÂY CHỈ LÀ THÔNG TIN THAM KHỎA TÙY VÀO TỪNG DÁNG NGƯỜI ).', 3, 1, NULL, '13-12-2019', 2, NULL),
(NULL, 'YANO Áo thun nam nữ in hình cờ đỏ sao vàng vải dày mềm mịn ', 'A', 87000, 1, 'Chất liệu thun mềm mại co giãn tốt , thoáng mát\r\nThiết kế thời trang phù hợp xu hướng hiện nay\r\nKiểu dáng đa phong cách, Form rộng Nam nữ đều mặc được\r\nĐường may tinh tế sắc sảo\r\nÁo thun được thiết kế vể đẹp trẻ trung năng động nhưng không kém phần cá tính.\r\nÁo được thiết kế đẹp, chuẩn form, đường may sắc xảo, vải cotton dày, mịn, thấm hút mồ hôi tạo sự thoải mái khi mặc!\r\nDễ dàng phối trang phục , thích hợp đi chơi đi làm đi dạo phố\r\nThích hợp cho sự kết hợp vứi quần jean, sọt,thể thao!\r\nCó đủ các size XS, S, M, L, XL, 2XL,3XL đáp ứng cho mọi khách hàng', 3, 1, NULL, '13-12-2019', 2, NULL),
(NULL, 'Áo thun trơn nữ form rộng phong cách hàn quốc vải dày mịn Thời Trang Kabuto (Trắng)', 'A', 38000, 1, 'Chất liệu thun mềm mại co giãn tốt , thoáng mát\r\nThiết kế thời trang phù hợp xu hướng hiện nay\r\nKiểu dáng đa phong cách\r\nĐường may tinh tế sắc sảo\r\nÁo thun được thiết kế vể đẹp trẻ trung năng động nhưng không kém phần duyên dáng.\r\nÁo được thiết kế đẹp, chuẩn form, đường may sắc xảo, vải cotton dày, mịn, thấm hút mồ hôi tạo sự thoải mái khi mặc!\r\nDễ dàng phối trang phục , thích hợp đi chơi đi làm đi dạo phố\r\nThích hợp cho sự kết hợp vứi quần jean, sọt,legging!', 3, 1, NULL, '13-12-2019', 2, NULL),
(NULL, 'Áo thun nữ loại in hình 3D cực đẹp vải cực mịn, thoáng mát mẫu AIMA789', 'A', 59000, 1, 'Chất liệu vải dày mịn thun mềm mại co giãn tốt , thoáng mát\r\nKiểu dáng đa phong cách,Thiết kế thời trang phù hợp xu hướng hiện nay\r\nĐường may tinh tế sắc sảo,chuẩn form, vải cotton dày, mịn, thấm hút mồ hôi tạo sự thoải mái khi mặc!\r\nÁo thun được thiết kế vể đẹp trẻ trung năng động nhưng không kém phần cá tính.\r\nThiết kế thời trang phù hợp xu hướng hiện nay,Dễ dàng phối trang phục , thích hợp đi chơi đi làm đi dạo phố.\r\nThích hợp cho sự kết hợp vứi quần jean, sọt,thể thao!', 3, 1, NULL, '13-12-2019', 2, NULL),
(NULL, 'Áo thun trơn nữ form rộng phong cách hàn quốc vải dày mịn Thời Trang Everest (Nhiều màu)', 'A', 16000, 1, 'Chất liệu thun mềm mại co giãn tốt , thoáng mát\r\nThiết kế thời trang phù hợp xu hướng hiện nay\r\nKiểu dáng đa phong cách-Đường may tinh tế sắc sảo\r\nSize XS cho người 25-35kg (XS,S,M,L,XL,XXL,XXXL)\r\nĐược kiểm tra hàng trước khi nhận ưng ý mới thanh toán nhận hàng .\r\nÁo thun được thiết kế vể đẹp trẻ trung năng động nhưng không kém phần duyên dáng.\r\nÁo được thiết kế đẹp, chuẩn form, đường may sắc xảo, vải cotton dày, mịn, thấm hút mồ hôi tạo sự thoải mái khi mặc!\r\nDễ dàng phối trang phục , thích hợp đi chơi đi làm đi dạo phố\r\nThích hợp cho sự kết hợp vứi quần jean, sọt,legging!', 3, 1, NULL, '13-12-2019', 2, NULL),
(NULL, 'Áo thun nữ tôi yêu Việt Nam vải dày mịn AoK1577', 'A', 36000, 1, 'Chất liệu thun mềm mại co giãn tốt , thoáng mát\r\nThiết kế thời trang phù hợp xu hướng hiện nay\r\nKiểu dáng đa phong cách-Đường may tinh tế sắc sảo\r\nSize XS cho người 25-35kg (XS,S,M,L,XL,XXL,XXL)\r\nĐược kiểm tra hàng trước khi nhận ưng ý mới thanh toán nhận hàng .\r\nÁo thun được thiết kế vể đẹp trẻ trung năng động nhưng không kém phần duyên dáng.\r\nÁo được thiết kế đẹp, chuẩn form, đường may sắc xảo, vải cotton dày, mịn, thấm hút mồ hôi tạo sự thoải mái khi mặc!\r\nDễ dàng phối trang phục , thích hợp đi chơi đi làm đi dạo phố\r\nThích hợp cho sự kết hợp vứi quần jean, sọt,legging!', 3, 1, NULL, '13-12-2019', 2, NULL),
(NULL, 'INCERUN Men Round Neck Sport Gym Muscle Knitting Jumper Pullover Sweater Tee Top Red - intl', 'A', 660000, 1, 'Color:Black,White,Dark Grey,Light Grey,Navy,Red\r\nSize:S M L XL 2XL 3XL\r\nMaterial:Knitted\r\nSleeve Style:Long Sleeve\r\nOccasion:Casual\r\nSeason:Spring,Autumn,Winter\r\nProduct Description:Fashion Men\'s Slim Fit Knitted Sweater V-Neck Long Sleeve Casual T-Shirt Tee Tops\r\nPackage Included:1 Tops\r\nHighlights:\r\n1.V-Neck\r\n2.Slim Fit\r\n\r\n3.Knitted Sweater', 3, 1, NULL, '13-12-2019', 2, NULL),
(NULL, 'Bộ 2 áo thun nam body kẻ ngang cao cấp Hàn Quốc ( đen, trắng )', 'A', 70000, 1, 'Chất liệu: vải thun co giãn\r\nMềm mịn; mặc mát\r\nMàu sắc: nhiều màu lựa chọn\r\nThiết kế thời trang\r\nPhù hợp đi chơi; dạo phố', 3, 1, NULL, '13-12-2019', 2, NULL),
(NULL, 'ÁO THUN NAM TAY NGẮN THỜI TRANG NEW FASHION92 CAO CẤP 27', 'A', 130000, 1, 'Sản phẩm: Áo Thun Phông Nam NgắnTay Họa Tiết thời Trang\r\nChất liệu: Vải mềm mại, thoáng mát dễ thấm hút mồ hôi\r\nPhong cách: Áo thun ngắn tay, cổ tròn, phong cách đơn giản hiện đại, dễ phối đồ\r\nPhù hợp khi đi chơi, dạo phố cùng bạn bè …có thể mặc theo nhóm…\r\nKích Thước: Size M, L ,XL', 3, 1, NULL, '13-12-2019', 2, NULL),
(NULL, 'ÁO THUN NAM TAY DÀI PHONG CÁCH SEOUL FASHION TD07', 'A', 120000, 1, 'Áo thun nam dài tay\r\nChất liệu: thun\r\nChất vải mềm, thoáng mát, bền màu\r\nKiểu dáng thời trang, hiện đại; dễ kết hợp và phối đồ phụ kiện\r\nCó 3 size S M L', 3, 1, NULL, '13-12-2019', 2, NULL),
(NULL, 'Quần Jogger, quần jogger thời trang Cho Cả Nam Và Nữ Nỉ Trơn Đen Cá Tính', 'A', 100000, 1, 'Chất liệu cao cấp\r\nĐường may tỉ mỉ chắc chắn và tinh tế\r\nThiết kế theo phong cách Hàn Quốc cực chất\r\nMàu sắc : đen\r\nSize: M, L , XL.', 4, 1, NULL, '13-12-2019', 2, NULL),
(NULL, 'Quần Jogger, quần jogger thời trang Cho Cả Nam Và Nữ chất xịn năng động QB4', 'A', 110000, 1, 'Kích thước: M-2XL\r\nM: cho người từ 45- 54kg\r\nL: cho người từ 55 - 60kg\r\nXL cho người từ 60 - 65kg\r\n2XL cho người từ : 66-77kg', 4, 1, NULL, '13-12-2019', 2, NULL),
(NULL, 'Quần short jean nam phối rách cá tính 6467', 'A', 190000, 1, 'Cam kết luôn mang đến khách hàng những sản phẩm tốt với giá hợp lý\r\nQUẦN SHORT JEAN NAM 6467 MẪU MỚI CỰC CHẤT\r\nTRANG PHỤC CƠ BẢN KHÔNG THỂ THIẾU CỦA PHÁI MẠNH\r\nMẪU MỚI NHẤT THỂ HIỆN SỰ CÁ TÍNH VÀ NĂNG ĐỘNG\r\nTHIẾT KẾ TRẺ TRUNG HIỆN ĐẠI - ĐƠN GIẢN NHƯNG KHÔNG KÉM PHẦN MẠNH MẼ\r\nCHẤT VẢI JEAN DÀY DẶN - MỀM MỊN - THOẢI MÁI VẬN ĐỘNG\r\nĐƯỜNG MAY KỸ CHẮC CHẮN - DÁNG CHUẨN\r\nCAM KẾT KHÔNG RA MÀU\r\nCAM kẾT HÀNG ĐẸP CHUẨN 100%\r\n1 SẢN PHẨM TỐT VỚI GIÁ CỰC KỲ HỢP LÝ VÀ ĐÁNG TIỀN\r\nHỖ TRỢ ĐỔI SIZE KHI MẶC KHÔNG VỪA ( tham khảo bảng chọn size phía dưới )\r\nBảng chọn size tham khảo\r\n28 dưới 50kg\r\n29 50_55kg\r\n30 55_60kg\r\n31 60_65kg\r\n32 65_70kg\r\nTÙY THỂ TRẠNG VÀ CHIỀU CAO CHỌN SIZE TƯƠNG ỨNG\r\nMẶC ÔM GIẢM 1 SIZE\r\nCÓ BỤNG TẮNG 1 SIZE\r\nTất cả đơn hàng đều được xử lý giao đi trong vòng 24h, shop chỉ gọi xác nhận khi cần xác nhận lại thông tin\r\nSản phẩm bán ra không bao gồm phụ kiện', 4, 1, NULL, '13-12-2019', 2, NULL),
(NULL, 'SORT JEAN NAM CAO CẤP 267', 'A', 180000, 1, 'ĐẸP CÁ TÍNH , ĐẸP NỔI BẬTKHÔNG PHAI MÀU , KHÔNG XÙ VẢITHOẢI MÁI MÁT MẺ MÙA HÈ', 4, 1, NULL, '13-12-2019', 2, NULL),
(NULL, 'Bộ 5 quần lót nam cao cấp EVEREST công nghệ Nhật Bản QH68208 ', 'A', 149000, 1, 'Quần lót nam thời trang Everest thiết kế đơn giản, phối màu đẹp mắt, làm tăng sự nam tính, hấp dẫn và lôi cuốn cho phái mạnh\r\nĐược kiểm hàng trước khi thanh toán , quý khách tham khảo đúng bảng size trước khi đặt hàng\r\nThiết kế lưng thun bản rộng với logo thương hiệu được in nổi trên lưng quần tạo nên sự mạnh mẽ cho người mặc\r\nKiểu dáng ấn tượng, vừa vặn, ôm trọn vòng 3 tôn lên vẻ nam tính và lôi cuốn của bạn nam, mang đến cho bạn nam sự tự tin, quyến rũ\r\nCombo in tên thương hiệu EVEREST nổi bật, thời trang, mang tới sự mạnh mẽ, nam tính, có nhiều màu sắc kiểu dáng để bạn lựa chọn, thay đổi\r\nĐường may chắc chắn, thiết kế vừa vặn theo công nghệ tiêu chuẩn Nhật Bản làm tôn lên vẻ nam tính, quyến rũ và cực tinh xảo chất lượng .\r\nChất liệu cotton mềm mại cực kì thoáng mát , thấm hút tốt , khử mùi , bền đẹp tạo cảm giác thoải mái, dễ chịu cho người mặc, đặc biệt không gây kích ứng cho da suốt 24h .\r\nCó thể kết hợp cùng mọi trang phục hàng ngày, từ đi học, đi chơi, đi làm đến tiệc tùng ...', 4, 1, NULL, '13-12-2019', 2, NULL),
(NULL, 'Bộ 5 Quần Lót Đùi Nhật Cho Nam\r\n9 đánh giá26 câu hỏi đã trả lời\r\n', 'A', 99000, 1, ' Quần được làm từ cotton với độ co giãn vừa phải, tạo cảm giác thoải mái cho người mặc.\r\n- Quần còn có độ thấm hút mồ hôi cao, tạo sự thoáng mát và sẽ không gây ra các vết hằn khó chịu trên da\r\n- Có nhiều size : M, L, XL, XXL\r\n- Xuất xứ: Việt Nam xuất Nhật\r\n- Quần ôm trọn vòng ba, tạo cảm giác thoải mái khi vận động', 4, 1, NULL, '13-12-2019', 2, NULL),
(NULL, 'Quần Dài Nữ Thun Phom Rộng Jogger Thể Thao Thời Trang Hàn Quốc - BT Fashion (JO02-ADVANCE)', 'A', 39000, 1, 'Vải thun coton co dãn, thấm hút mồ hôi tốt.\r\nRất hợp phối với Áo thun, ba lỗ, hai dây, croptop (hở eo) có bán tại Shop\r\nQuần dài 88cm: Size S từ 40 - 42kg, Size M từ 42- 50 kg (từ 50-55kg mặc phom ôm)\r\nCó dây rút để điều chỉnh vòng eo.', 4, 1, NULL, '13-12-2019', 2, NULL),
(NULL, 'Quần dài nữ Baggy Jogger sọc caro hàn quốc', 'A', 75000, 1, 'Quần baggy nữ ca rô lật laicó thiết kế lưng thun, phối dây rút cá tính.\r\nNhấn nhá vải caroấn tượng phù hợp với xu hướng thời trang nổi bật của năm.\r\nThiết kế dạng quần baggy, lật lai, túi ẩncá tính cho các nàng thêm nét thu hút, thời trang.\r\nChất liệu vải bốkhông co giãn, thấm hút tốt.\r\nCó thể phối cùng áo croptop hoặc áo sơ mikiểu thời trang khi đi chơi, du lịch hoặc đi làm.\r\nphù hợp với các bạn nữ từ 1m45-1m7', 4, 1, NULL, '13-12-2019', 2, NULL),
(NULL, 'Quần Dài Nữ Thun Ôm Legging 2 Sọc Thể Thao Thời Trang Hàn Quốc - BT Fashion (Viền 3M- 03)', 'A', 39000, 1, 'Vải thun coton co dãn, thấmhút mồ hôi tốt.\r\nRất hợp phối với Áo thun, ba lỗ, hai dây, croptop (hở eo) có bán tại Shop\r\nQuần dài 58cm: Size S từ 40 - 45kg, Size M từ 45- 55 kg', 4, 1, NULL, '13-12-2019', 2, NULL),
(NULL, 'Quần dài Jogger thể thao nữ 18208 - Cửa hàng phân phối KIT Sport - Hàng nội địa Trung', 'A', 260000, 1, 'Vui lòng vào gian hàng để xem nhiều mẫu hơn.\r\nXem thêm chi tiết sản phẩm vui lòng chọn \"XEM TẤT CẢ\"\r\nXem hàng trước khi thanh toán, MIỄN PHÍ đổi hàng trong 7 ngày.\r\nHoàn tiền 100% nếu sản phẩm không như hình.\r\nTư vấn thêm, vui lòng nhắn tin cho chúng tôi.\r\nTìm kiếm nhanh chúng tôi với từ khóa \" kit sport\"', 4, 1, NULL, '13-12-2019', 2, NULL),
(NULL, 'Quần dài Thể Thao Nữ', 'A', 170000, 1, 'Quần Thể Thao Nữ RUN - QL1551 (Quần Legging Nữ)\r\nPhù hợp với hoạt động tập gym, vận động thể thao trong nhà hoặc ngoài trời...\r\nHàng nội địa\r\nThiết kế đơn giản nhưng trẻ trung, năng động và sexy\r\nChất liệu mang lại cảm giác thoải mái, thấm hút mồ hôi tốt, khô nhanh.\r\nChất liệu co dãn 4 chiều, thấm hút mồ hôi, giữ dáng lâu dài.\r\nChất liệu Cotton Spandex co dãn tốt.\r\nKích thước: Size M, Size L', 4, 1, NULL, '13-12-2019', 2, NULL),
(NULL, 'Quần Âu Nam Đỏ Đô Quần tây nam dáng côn hàn quốc cực chất', 'A', 118000, 1, 'Quần Âu nam Hàn Quốc\r\nSize : 28-29-30-31-32-33\r\nỐng: Côn\r\nChất vải: Co giãn tốt, không bay màu, giữ dáng quần không bị bai\r\nQuần âu nam hàn quốc chất lượng vải cao cấp, co giãn tạo cảm giác thoải mái cho người mặc. Kiểu dáng trẻ trung nhưng cũng không kém phần lịch lãm được thiết kế riêng cho người Việt.\r\nVới kinh nghiệm hơn 30 năm trong nghề may chúng tôi tin tưởng sẽ đáp ứng được những yêu cầu của các khách hàng khó tính .\r\nCam kết giá cả làm hài lòng quý khách', 4, 1, NULL, '13-12-2019', 2, NULL),
(NULL, 'Quần Baggy nữ caro A001 siêu hot', 'A', 310000, 1, 'Quần baggy nữ ca rô lật lai có thiết kế lưng thun, phối dây rút cá tính.\r\nNhấn nhá vải caro ấn tượng phù hợp với xu hướng thời trang nổi bật của năm.\r\nThiết kế dạng quần baggy, lật lai, túi ẩncá tính cho các nàng thêm nét thu hút, thời trang.\r\nChất liệu kaki Tây co giãn thoải mái, nhẹ, thấm hút tốt.\r\nCó thể phối cùng áo croptop hoặc áo sơ mikiểu thời trang khi đi chơi, du lịch hoặc đi làm.\r\nphù hợp với các bạn nữ từ 1m45-1m7\r\nSize S: 40 - 47 kg\r\nSize M: 47-53kg\r\nSize L: 53-60kg', 4, 1, NULL, '13-12-2019', 2, NULL),
(NULL, 'QUẦN NỈ THỂ THAO NAM NỮ THỜI TRANG CAO CẤP -N03S', 'A', 80000, 1, 'Thiết kế thời trang\r\nChất liệu: Nỉ dày dặn\r\nĐường may sắc sảo tinh tế\r\nDể dàng phối trang phục.\r\nĐổi,trả dễ dàng nếu khách hàng mặc không vừa hoặc không ưng ý.', 4, 1, NULL, '13-12-2019', 2, NULL),
(NULL, 'QUẦN BÒ, JEAN NỮ ỐNG RỘNG THỜI TRANG, LAZASHION', 'A', 230000, 1, ' bagy bò hàng CAO CẤP chất bán shop cực cực đẹp đủ size\r\n size :S M L \r\nThương hiệu OEM SKU 239656786_VNAMZ-306103192 Loại bảo hành No Warranty\r\n', 4, 1, NULL, '13-12-2019', 2, NULL),
(NULL, 'Quần short jean nữ ống rộng cá tính phong cách Hàn Quốc', 'A', 300000, 1, 'Quần short ống rộng\r\nLên form siêu đẹp\r\nKhó mòn , khó nhăn\r\nChất liệu thun jean co giản tốt\r\nSize M (eo 66-78; hông 102; mặt trước 29; mặt sau 34; 45-52kg)\r\nSize L (eo 68-80; hông 104; mặt trước 30; mặt sau 34; 50-58kg)', 4, 1, NULL, '13-12-2019', 2, NULL),
(NULL, 'Quần baggy đai tròn vải tuyết mưa loại 1', 'A', 89000, 1, 'Chất liệu cao cấp\r\nThiết kế thời trang\r\nĐường may chi tiết, tỉ mỉ\r\nẢnh thật trải sàn (3 ảnh cuối) khách nhé. Bao chất đẹp', 4, 1, NULL, '13-12-2019', 2, NULL),
(NULL, 'Quần Tây Âu Công Sở Nữ Ống Đứng Kéo Dài Chân, Định Hình Eo Thon ', 'A', 299000, 1, 'Chất liệu cao cấp mềm mịn làm cho dáng quần rất đứng và thanh lịch, đặc biệt với dáng quần này sẽ làm tôn chiều cao lên rất nhiều. Shop có hình ảnh hướng dẫn cách tôn dáng lên tới hơn 10cmcho các chị em công sở.\r\nShop cam kết sản phẩm y hình, chất liệu đẹp. Quý khách hàng được kiểm tra sản phẩm trước khi thanh toán\r\nVới chiếc quần đẹp và sang trọng này, chị em nào mặc lên cũng đảm bảo chuẩn form như hình, phối đồ gì cũng sang\r\nCác chịchọn size dựa theo bảng size bên dưới phần chi tiết sản phẩm để có được chiếc quần chuẩn người mẫu nhé các chị, có thể chat hỏi shop nếu cần', 4, 1, NULL, '13-12-2019', 2, NULL),
(NULL, 'Dép da nam DR-2016 nâu sần', 'A', 550000, 1, 'Là hàng VN chất lượng caocực kỳ hot, nổi tiếng trên toàn thế giới.Phá cách và hết sức độc đáo.\r\nƯu đãi giảm giá: Với chất liệu 100% da bò thật siêu phẩm này cực xứng đáng với giá #850k, nay giảm chỉ còn 550k.\r\nMix đồ: Đây là một trong những siêu phẩm phối với quần jeans hết sức tuyệt vời.', 5, 1, NULL, '13-12-2019', 2, NULL),
(NULL, 'Dép tông lụa thời trang mới 2018', 'A', 99000, 1, 'Điệu đà, duyên dáng, trẻ trung, thời trang\r\nChất liệu êm chân\r\nPhom dễ đi, chắc chắn, thoải mái\r\nĐế cao nâng dáng', 5, 1, NULL, '13-12-2019', 2, NULL),
(NULL, 'ZOQI Giày Sandal Nữ Đế Xuồng Giày Slip On Trơn Bohemia Dép Giày-quốc tế', 'A', 333000, 1, 'Khóa: Slip on\r\nGót: gót bằng\r\nDịp: Hàng Ngày\r\nChất liệu mặt trên: da Giả\r\nChất liệu lót: DA PU\r\nChất Liệu đế: Cao Su\r\nĐối tượng áp dụng: Thanh Niên\r\nPhong cách: Giải Trí\r\nChức năng: chống Mài Mòn & Thoáng Khí\r\nMàu sắc: màu Đen, Màu Be, Đỏ\r\nKích thước: 35-40\r\n', 1, 1, NULL, '13-12-2019', 2, NULL),
(NULL, 'Dép bánh mì nơ ú 4p MYS', 'A', 149000, 1, 'Điệu đà, duyên dáng, trẻ trung, thời trang\r\nChất liệu êm chân, quai giày được làm bằng da mềm mang lại cảm giác êm ái\r\nPhom giày dễ đi, chắc chắn, thoải mái , ôm chân vừa vặn giúp che khuyết điểm đôi bàn chân\r\nĐế giày bằng nhựa dẻo mang lại cảm giác nhẹ nhàng\r\nMột sản phẩm đẹp là khi phô diễn được hết vẻ đẹp của người sử dụng, là giày ôm gọn đôi chân thon thả, là chân xinh trắng nỏn nà nổi bật.\r\nKhông phải nhưng đôi giày hàng hiệu mới làm được điều đó, bởi ngay trong từng thiết kế bình dân cũng có thể thể hiện được.', 5, 1, NULL, '13-12-2019', 2, NULL),
(NULL, 'DÉP DA QUAI NGANG NAM CAO CẤP', 'A', 169000, 1, 'Chất liệu da cao cấp, Siêu mềm mại\r\nĐế công ty chất lượng cao\r\nDa được may xuống đế cẩn thận, chắc chắn\r\nThiết kế thời trang, độ bền cao\r\nCó đủ size từ 38-44\r\nKhi mua hàng quý khách sẽ được tặng 1 thẻ card Shop, Nếu gom đủ 3 card hoặc tổng hóa đơn mua hàng trên 500 ngàn. quý khách hàng sẽ được VOUCHER giảm 10% giá trị thanh toán cho đơn hàng bất kỳ tiếp theo. hãy nhanh tay nắm bắt cơ hội cực kỳ dễ dàng này nào', 5, 1, NULL, '13-12-2019', 2, NULL),
(NULL, 'Dép nam xỏ ngón quai kẹp thời trang cao cấp ', 'A', 49000, 1, 'Kích thước: 39-40-41-42\r\nChất liệu quai: cao su tổng hợp, mềm dẻo\r\nChất liệu đế: nhựa định hình\r\nChiều cao đế: 1.5cm\r\nMô tả chi tiết sản phẩm: trên phầm quai kẹ pcó logo kim loại tăng độ sang trọng cho sản phẩm;phần quai kẹp tiếp xúc với kẽ ngón chân mềm, nhỏ, QK yên tâm khi mang sẽ thoải mái, không bị đau chân như một số sản phẩm nhựa cứng trên thị trường.\r\nDép xỏ ngón quai kẹp Duwa được làm từ chất liệu cao su cao cấp, mềm dẻo tạo sự thoải mái cho người mang, thời trang và bền chắc.\r\nLưu ý khi sử dụng sản phẩm: để sản phẩm được bền như mới, quý khách không nên dùng chất tẩy rửa mạnh để vệ sinh sản phẩm và không nên phơi sẩn phẩm trực tiếp ngoài nắng.', 5, 1, NULL, '13-12-2019', 2, NULL),
(NULL, 'DÉP ULZZANG NỮ QUAI HẬU KHỦNG BỐ THỜI TRANG SANG CHẢNH DÉP SANDAL CÁ TÍNH HOT TREND 2018', 'A', 380000, 1, 'Sản phẩm chất lượng\r\nGiá cả hợp lý\r\nThiết kế phong cách', 5, 1, NULL, '13-12-2019', 2, NULL),
(NULL, 'Dép Trái Cây Đẹp Du Lịch Biển Nam Nữ LALA Premium ', 'A', 99000, 1, 'Dép thời trang cực xinhMàu sắc vui tươi, độc đáoChất liệu: quai vải - đế xốp cứng cực êm', 5, 1, NULL, '13-12-2019', 2, NULL),
(NULL, 'Dép quai hậu nam thời trang', 'A', 433000, 1, 'Kiểu dáng đẹp, kết hợp hài hòa giữa thời trang và trend 2018', 5, 1, NULL, '13-12-2019', 2, NULL),
(NULL, 'Đế Mềm Nam Chống Trượt Mùa Hè Giày Đi Biển Dép Lê', 'A', 165000, 1, 'Style:Màu Xám, Option:Châu Âu Mã 21 Mã Bên Trong Dài 13.5 Cm', 5, 1, NULL, '13-12-2019', 2, NULL),
(NULL, 'Giày Sandal xỏ ngón nam thời trang cao cấp ', 'A', 119000, 1, 'Đế cao su nguyên chất chống trơn trợt.\r\nChắc chắn, nhanh khô.\r\nThích hợp mang đi mưa, đi biển, đi dã ngoại, đi học, ...\r\nquai dán xé chắc chắn, dễ dàng điều chỉnh phù hợp với kích cỡ bàn chân.\r\nDễ dàng lau chùi và vệ sinh.\r\nKiểu dáng thời trang, hợp phong cách giới trẻ.\r\nĐường may tinh tế, chắc chắn.', 5, 1, NULL, '13-12-2019', 2, NULL),
(NULL, 'Dép Nam Quai Hậu Da Bò Sandal Thời Trang Màu Nâu', 'A', 309000, 1, '1.Sản phẩm : Dép Nam Quai Hậu Da Bò QN0211 - Màu Nâu\r\n2.Xuất xứ : Việt Nam.\r\n3.Kiểu dáng : Dép quai hậu như hình.\r\n4.Chất liệu : Da bò thật 100%.\r\n5.Màu sắc : Như hình\r\n6.Size : 38 - 43', 5, 1, NULL, '13-12-2019', 2, NULL),
(NULL, 'Dép bông đi trong nhà hình heo (Nâu)', 'A', 39000, 1, 'Dép đi trong nhà\r\nĐế gai cao su; bám nền gạch\r\nChất liệu vải lông; lớp xốp đàn hồi và nhựa dẻo tổng hợp', 5, 1, NULL, '13-12-2019', 2, NULL),
(NULL, 'Dép đi trong nhà', 'A', 40000, 1, 'Dép đi trong nhà êm ái, họa tiết chấm bi đơn giản, thích hợp cho cả gia đình\r\nChất liệu vải bông mềm mại, ấm áp bảo vệ đôi chân của bạn trong những ngày mùa đông lạnh giá.\r\nĐế dép in nổi chống trơn trượt, đảm bảo an toàn khi di chuyển.\r\n', 5, 1, NULL, '13-12-2019', 2, NULL),
(NULL, 'Dép sục đi trong nhà siêu nhẹ êm ái ', 'A', 85000, 1, 'Chất liệu xốp tổng hợp nhẹ, không thấm nước\r\nDép không gây mùi khi ra mồ hôi chân và không gây hại cho sức khỏe người sử dụng\r\nThiết kế bít mũi, đục lỗ thông thoáng\r\nCó quai đeo gót sành điệu\r\nSize dép từ 40 đến 44 cho bạn nhiều sự lựa chọn với nhiều màu sắc bắt mắt trẻ trung', 5, 1, NULL, '13-12-2019', 2, NULL),
(NULL, 'DÉP TỔ ONG MÀU ĐỎ KEITHSHOP', 'A', 42000, 1, 'DÉP TỔ ONG MÀU ĐỎ+ DÉP ĐI ÊM+ BỀN MÀU, DẺO MỀM', 5, 1, NULL, '13-12-2019', 2, NULL),
(NULL, 'Dép xỏ ngón quai kẹp nam cao cấp sản xuất tại Thái Lan ', 'A', 99000, 1, 'Xuất xứ: dép sản xuất tạiThái Lan\r\nChất liệu: xốp EVA cao cấp, đế có thiết kế dạng gợn sóng bằng cao su tăng độ ma sát\r\nDép rất chắc chắn, bền đẹp và êm chân\r\nMàu sắc: xám phối cam\r\nKích thước: 39-40-41-42\r\nCam kết y hình, được mở sản phẩm kiểm tra trước khi thanh toán nên quý khách yên tâm về chất lượng sản phẩm', 5, 1, NULL, '13-12-2019', 2, NULL);

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
('https://vn-test-11.slatic.net/shop/b25043883b2b57917aa3c43002b106be.jpeg', 'Giày thể thao nam sneaker màu trắng', 1, 2),
('https://vn-live-01.slatic.net/original/77897ea9fe3b077de3e2de03f59a83d5.jpg', 'Giày Sneaker Nam Năng Động Màu Xám - Pettino P003', 2, 2),
('https://media3.scdn.vn/img2/2017/12_25/E3otSP_simg_de2fe0_500x500_maxb.png', 'Giày Nam - Giày Nam - Giày Nam - Giày Nam - T0101', 3, 2),
('https://vn-test-11.slatic.net/shop/5b53d7e299a046aa7e856ecd2c2af151.jpeg', 'Giày nam trắng thể thao sneaker- khuyến mãi 50% chào mừng Quốc Khánh', 4, 2),
('https://vn-test-11.slatic.net/shop/d0107ab7c579146eae6ecc94391dbfc3.jpeg', 'Giày thể thao nam Muidoi G145 (Đen)', 5, 2),
('https://vn-test-11.slatic.net/shop/bf609f1db1ecc017ff1993e426351d62.jpeg', 'Nón lưỡi trai nam thời trang 2018', 6, 2),
('https://vn-test-11.slatic.net/shop/d715e51651dae66b98bbaa02c17acd80.jpeg', 'Nón Bucket Chữ Nhật Cá Tính - Mũ Tai Bèo Vải Tốt', 7, 2),
('https://media3.scdn.vn/img2/2018/5_10/HXHjKN_simg_de2fe0_500x500_maxb.jpg', 'Nón - Nón kết', 8, 2),
('https://media3.scdn.vn/img2/2018/5_29/HdLqA8_simg_de2fe0_500x500_maxb.jpg', 'NÓN KẾT NÓN KẾT - SNAPBACK DA TRƠN', 9, 2),
('https://media3.scdn.vn/img2/2018/9_1/99Vscz_simg_de2fe0_500x500_maxb.jpg', 'NÓN KẾT NÓN KẾT - NÓN JEAN 2', 10, 2),
('https://media3.scdn.vn/img2/2018/9_11/roPxZq_simg_de2fe0_500x500_maxb.jpg', 'Áo nữ ', 11, 2),
('https://media3.scdn.vn/img2/2018/10_9/Wvzt1D_simg_de2fe0_500x500_maxb.jpg', 'Áo nữ xanh', 12, 2),
('https://media3.scdn.vn/img2/2018/6_26/hv0z12_simg_de2fe0_500x500_maxb.jpg', 'Quần Jogger', 13, 2),
('https://media3.scdn.vn/img2/2018/4_14/C5tEvV_simg_de2fe0_500x500_maxb.jpg', 'Quần Jogger - DT65LL552', 14, 2),
('https://media3.scdn.vn/img2/2018/7_28/IN5Yt9_simg_de2fe0_500x500_maxb.jpg', 'Dép - SPU-X4', 15, 2),
('https://media3.scdn.vn/img2/2018/11_22/fSpR5v_simg_de2fe0_500x500_maxb.jpg', 'Dép đi trong nhà ', 16, 2),
('https://img.alicdn.com/imgextra/i1/2262938008/TB2.tX9qIIrBKNjSZK9XXagoVXa_!!2262938008.jpg', 'Giày Cao Gót', 17, 2),
('https://vn-test-11.slatic.net/shop/bafcdc4189c5554d6b4075e72200af36.jpeg', 'Giày Bít Nhọn Thời Trang 5050BN0067 Sablanca', 18, 2),
('https://vn-test-11.slatic.net/shop/9b9f565c0c5668ce22f3857359a28f32.jpeg', 'Giày Thể Thao Nữ Hàn Quốc', 19, 2),
('https://vn-test-11.slatic.net/shop/fd93c9931891cce9a444d996473c938c.png', 'Giày sneaker nữ CHẤT ĐẸP 3 lỗ thoáng khí HAPU', 20, 2),
('https://vn-test-11.slatic.net/shop/c5889e9010cdc7514507b7c43c4a5615.png', 'GIÀY THỂ THAO NỮ ĐẸP', 21, 2),
('https://vn-test-11.slatic.net/shop/8f5d38b93e68f603978662d5e9addf1d.jpeg', 'GIÀY CONVERSE CLASSIC CỔ THẤP ĐEN - HÀNG VIỆT NAM XUẤT KHẨU', 22, 2),
('https://vn-live-01.slatic.net/original/b14541a69353eb6debf689d2b3d152e5.jpg', 'Giày Thể Thao Nam Sports Sneaker Ssk698', 23, 2),
('https://vn-test-11.slatic.net/shop/cb34e7ef03d06f11bfb8b7a1b328c6b4.jpeg', '[ FULL BOX + TẶNG VỚ ] GIÀY THỂ THAO FILA - HÀNG VIỆT NAM XUẤT KHẨU', 24, 2),
('https://vn-live-01.slatic.net/original/5bb88ceec315b9c95af5519988608b29.jpg', 'Giày Sneak Nam Sodoha SMG65890W -White', 25, 2),
('https://vn-test-11.slatic.net/shop/48a4e59e7544c2b375b4ce64d58a0cb5.jpeg', 'Giày Boots Đi Mưa Rando OBPS-04 Size L (Đen)', 26, 2),
('https://vn-live.slatic.net/original/0ea2eb1b3e91b82b35a562a9d07c1743.jpg', 'Giày sneaker nam thể thao - A123 (trắng pha đen) ', 27, 2),
('https://vn-test-11.slatic.net/shop/f4859fc581dd3d06053c99c32e4b9028.jpeg', 'GIÀY CẦU LÔNG CHÍ PHÈO CAO CẤP CL015', 28, 2),
('https://vn-test-11.slatic.net/shop/77d57b8676040c6baccf5c7c8463b9d5.png', 'Giày Đá Bóng Prowin Cao Cấp Mũi Tên Size Người Lớn - Nhà Phân Phối Chính Từ Hãng', 29, 2),
('https://vn-test-11.slatic.net/shop/7f3ecd434bd09041434a966bd1df4ce9.png', 'Giày Đá Bóng Prowin S50 - Nhà Phân Phối Chính Từ Hãng', 30,2),
('https://vn-live-02.slatic.net/original/43eadc32fd6244a7e698bcedd309eea1.jpg', 'A', 31, 2),
('https://vn-live-02.slatic.net/original/ca9856a7fac7108362ef2040a544fc19.jpg', 'Unisex Men Women Soft Leather Baseball Cap Biker Adjustable Outdoor Sports Hats White - intl', 32, 2),
('https://vn-live-02.slatic.net/original/ca9856a7fac7108362ef2040a544fc18.jpg', 'Unisex Men Women Soft Leather Baseball Cap Biker Adjustable Outdoor Sports Hats Black - intl', 33, 2),
('https://vn-test-11.slatic.net/shop/dcddd134927ccc1fe9f8ff1fbd620912.jpeg', 'Nón kết,nón lưỡi trai,mũ nón HOCKEY', 34, 2),
('https://vn-test-11.slatic.net/shop/e0e6b772614b64f418982e5e8b582aad.jpeg', 'Nón nam ATE Racing Team cá tính Thời Trang K & T', 35, 2),
('https://vn-test-11.slatic.net/shop/4ded8a83b063ac9a7ae72c417e343048.jpeg', 'NÓN VẢI DÙ- NÓN KẾT VẢI DÙ - NÓN LƯỠI TRAI VẢI DÙ NAM NỮ H412 EVEREST(Nhiều màu)', 36, 2),
('https://vn-test-11.slatic.net/shop/74de8afd4b27b9a971517379a33a65c0.jpeg', 'Nón bucket tai bèo,nón vành,mũ vành OFF WHITE nam nữ', 37, 2),
('https://vn-live.slatic.net/original/cb0ff53c229c1c3b2b851cede48ad1c6.jpg', 'Nón Kết Màu Xanh Rêu Trơn', 38, 2),
('https://vn-live.slatic.net/original/b074e17fb2efc1e66e3edb6ccbc5154e.jpg', 'Nón Kết Màu Camouflage', 39, 2),
('https://vn-live-01.slatic.net/original/7f411387c5be9a40315fe28c52dd0416.jpg', 'Nón nam thể thao KEIHIN Nón Trơn Store 440', 40, 2),
('https://vn-live-02.slatic.net/original/d3ba82b01163ad5d70d744de0e76e83f.jpg', 'Bộ 3 áo thun trơn nam form rộng phong cách hàn quốc vải dày mịn AOTHUN102 (Trắng - Đen - Xám)', 41, 2),
('https://vn-live-02.slatic.net/original/2af9eded8172aa203865b9648f88c0be.jpg', 'Dài Tay nam ', 42, 2),
('https://vn-live.slatic.net/original/8c69b71159381826a288fe7b96936949.jpg', 'Áo thun unisex tay lỡ nam nữ', 43, 2),
('https://vn-test-11.slatic.net/shop/e986e90938e7aa1dd955515417fc2a52.jpeg', 'YANO Áo thun nam nữ in hình cờ đỏ sao vàng vải dày mềm mịn ', 44, 2),
('https://vn-live.slatic.net/original/abb0e754adf8cf5889fd2ecce0cce70c.jpg', 'Áo thun trơn nữ form rộng phong cách hàn quốc vải dày mịn Thời Trang Kabuto (Trắng)', 45, 2),
('https://vn-test-11.slatic.net/shop/8f6605530d6223c6d2f10fbadeee802a.jpeg', 'Áo thun nữ loại in hình 3D cực đẹp vải cực mịn, thoáng mát mẫu AIMA789', 46, 2),
('https://vn-live-02.slatic.net/original/9b4ed3c0d66fc3d752e7c302002550c9.jpg', 'Áo thun trơn nữ form rộng phong cách hàn quốc vải dày mịn Thời Trang Everest (Nhiều màu)', 47, 2),
('https://vn-live.slatic.net/v2/resize/products/S-20274-25a1ce81792bafa51ac4460e8195ba39.jpg', 'Áo thun nữ tôi yêu Việt Nam vải dày mịn AoK1577', 48, 2),
('https://vn-live-02.slatic.net/original/8ab9c34caa6aceb7d2be96ff296e71cc.jpg', 'INCERUN Men Round Neck Sport Gym Muscle Knitting Jumper Pullover Sweater Tee Top Red - intl', 49, 2),
('https://vn-live.slatic.net/v2/resize/products/102197014-ec073ca73fa70132ee0c5bdffe928d77.jpg', 'Bộ 2 áo thun nam body kẻ ngang cao cấp Hàn Quốc ( đen, trắng )', 50, 2),
('https://vn-test-11.slatic.net/shop/89756f0c072f45762ee7eae787576a18.jpeg', 'ÁO THUN NAM TAY NGẮN THỜI TRANG NEW FASHION92 CAO CẤP 27', 51, 2),
('https://vn-test-11.slatic.net/shop/db02bfc5fbfbba6dcb799091ee028f52.jpeg', 'ÁO THUN NAM TAY DÀI PHONG CÁCH SEOUL FASHION TD07', 52, 2),
('https://vn-test-11.slatic.net/shop/d39522abab9a0e93f081ed543a1784ea.jpeg', 'Quần Jogger, quần jogger thời trang Cho Cả Nam Và Nữ Nỉ Trơn Đen Cá Tính', 53, 2),
('https://vn-test-11.slatic.net/shop/50e363b266d6f4bfd8bd275cd2231113.jpeg', 'Quần Jogger, quần jogger thời trang Cho Cả Nam Và Nữ chất xịn năng động QB4', 54, 2),
('https://vn-test-11.slatic.net/original/cf3346d86a992688ec97fffc7f2bf0a1.jpg_200x200q80.jpg_.webp', 'Quần short jean nam phối rách cá tính 6467', 55, 2),
('https://vn-test-11.slatic.net/original/a504da379b2475734758c33755f0d526.jpg_200x200q80.jpg_.webp', 'SORT JEAN NAM CAO CẤP 267', 56, 2),
('https://vn-live-01.slatic.net/original/b8c49e9c11fcd16be6696bd91634f3c2.jpg', 'Bộ 5 quần lót nam cao cấp EVEREST công nghệ Nhật Bản QH68208 ', 57, 2),
('https://vn-test-11.slatic.net/p/7/bo-5-quan-lot-dui-nhat-cho-nam-4019-3486036-2d6ce218527b52fdbe7c6c6aaf21bbd6-catalog_233.jpg', 'Bộ 5 Quần Lót Đùi Nhật Cho Nam\r\n9 đánh giá26 câu hỏi đã trả lời\r\n', 58, 2),
('https://vn-live-01.slatic.net/original/ef30f050916d534ecc9d1e0ff99bb556.jpg', 'Quần Dài Nữ Thun Phom Rộng Jogger Thể Thao Thời Trang Hàn Quốc - BT Fashion (JO02-ADVANCE)', 59, 2),
('https://vn-live-01.slatic.net/original/f3c7ece81b55da64c13099ce1a4907f8.jpg', 'Quần dài nữ Baggy Jogger sọc caro hàn quốc', 60, 2),
('https://vn-live-01.slatic.net/original/2a2bee0f6118bcf8cfc0a219b989b496.jpg', 'Quần Dài Nữ Thun Ôm Legging 2 Sọc Thể Thao Thời Trang Hàn Quốc - BT Fashion (Viền 3M- 03)', 61, 2),
('https://vn-live-01.slatic.net/original/9c1b10a3026f7e61dcad1417a26bfcde.jpg', 'Quần dài Jogger thể thao nữ 18208 - Cửa hàng phân phối KIT Sport - Hàng nội địa Trung', 62, 2),
('https://vn-live-01.slatic.net/original/fc89e69322999324f20cea9b41bd7f24.jpg', 'Quần dài Thể Thao Nữ', 63, 2),
('https://vn-live-02.slatic.net/original/31bcae5ceef9948540bec8cd1663be58.jpg', 'Quần Âu Nam Đỏ Đô Quần tây nam dáng côn hàn quốc cực chất', 64, 2),
('https://vn-live-01.slatic.net/original/9783f4a469de49688237ad0b6b39b613.jpg', 'Quần Baggy nữ caro A001 siêu hot', 65, 2),
('https://vn-live-01.slatic.net/original/7639a23d7ccaf7c72d829245b2f793ca.jpg', 'QUẦN NỈ THỂ THAO NAM NỮ THỜI TRANG CAO CẤP -N03S', 66, 2),
('https://vn-live-01.slatic.net/original/138ac07f74eff08f63bfaf79e33ebbdf.jpg', 'QUẦN BÒ JEAN NỮ ỐNG RỘNG THỜI TRANG LAZASHION', 67, 2),
('https://vn-live-01.slatic.net/original/b9faf9bb7e0fa3c73d87e9c05b21cc99.jpg', 'Quần short jean nữ ống rộng cá tính phong cách Hàn Quốc', 68, 2),
('https://vn-live-02.slatic.net/original/8a16693c2a28d63b856cb7c803a5922b.jpg', 'Quần baggy đai tròn vải tuyết mưa loại 1', 69, 2),
('https://vn-live-01.slatic.net/original/e87f93f7dba91b7057ec4c1ecaef61c6.jpg', 'Quần Tây Âu Công Sở Nữ Ống Đứng Kéo Dài Chân, Định Hình Eo Thon ', 70, 2),
('https://vn-test-11.slatic.net/p/7/dep-da-nam-dr-2016-nau-san-3776-66258661-e1cb67691dfc209f1afed212175291e0-catalog_233.jpg', 'Dép da nam DR-2016 nâu sần', 71, 2),
('https://vn-live-02.slatic.net/original/6db0fbad66a94db5e531ddf5da7684b2.jpg', 'Dép tông lụa thời trang mới 2018', 72, 2),
('https://laz-img-sg.alicdn.com/original/927a7af628ec970637f045315904fdf6.jpg_200x200q80.jpg_.webp', 'ZOQI Giày Sandal Nữ Đế Xuồng Giày Slip On Trơn Bohemia Dép Giày-quốc tế', 73, 2),
('https://vn-test-11.slatic.net/original/f265caf1d4f91257543d8e1b01017da6.jpg_200x200q80.jpg_.webp', 'Dép bánh mì nơ ú 4p MYS', 74, 2),
('https://vn-live-01.slatic.net/original/576416194435657acb26d71e22f6620c.jpg', 'DÉP DA QUAI NGANG NAM CAO CẤP', 75, 2),
('https://vn-live-01.slatic.net/original/5bffbc4db9b7572a9ada942c92eef145.jpg', 'Dép nam xỏ ngón quai kẹp thời trang cao cấp ', 76, 2),
('https://vn-live-01.slatic.net/original/115b7edf34bc41143af1b3c9da26de02.jpg', 'DÉP ULZZANG NỮ QUAI HẬU KHỦNG BỐ THỜI TRANG SANG CHẢNH DÉP SANDAL CÁ TÍNH HOT TREND 2018', 77, 2),
('https://vn-live-01.slatic.net/original/319d28de8c864f8a4c63110ab15f2a9e.jpg', 'Dép Trái Cây Đẹp Du Lịch Biển Nam Nữ LALA Premium ', 78, 2),
('https://ph-live-02.slatic.net/original/e2196f6e29d321dbf43c5412e3ed6cd9.jpg', 'Dép quai hậu nam thời trang', 79, 2),
('https://vn-test-11.slatic.net/original/cd4a4ffbc624c1d480e49f9352b9e7ff.jpg_200x200q80.jpg_.webp', 'Đế Mềm Nam Chống Trượt Mùa Hè Giày Đi Biển Dép Lê', 80, 2),
('https://vn-live-01.slatic.net/original/c342d72d8761cce3d4c5c7cc549bd64d.jpg', 'Giày Sandal xỏ ngón nam thời trang cao cấp ', 81, 2),
('https://vn-live-01.slatic.net/original/c23ee85e814ffa1d4934a353d6ef92ee.jpg', 'Dép Nam Quai Hậu Da Bò Sandal Thời Trang Màu Nâu', 82, 2),
('https://vn-test-11.slatic.net/p/dep-bong-di-trong-nha-hinh-heo-nau-3373-6937542-646c2fc8950a9e028fcd910a824bacc8-catalog_233.jpg', 'C' , 83, 2),
('https://vn-live-01.slatic.net/original/d2767e544087303e6162437a2e27756e.jpg', 'Dép bông đi trong nhà hình heo (Nâu)', 84, 2),
('https://vn-live-01.slatic.net/original/b74cbc9e28db7ee4ae5976d32d802f3d.jpg', 'Dép sục đi trong nhà siêu nhẹ êm ái ', 85, 2),
('https://vn-live-01.slatic.net/original/f60830a592180cf148477a1e14af3af9.jpg', 'DÉP TỔ ONG MÀU ĐỎ KEITHSHOP', 86, 2),
('https://vn-live-01.slatic.net/original/fa505c3e80386269e92da2219bf25c37.jpg', 'Dép xỏ ngón quai kẹp nam cao cấp sản xuất tại Thái Lan ', 87, 2),
('https://vn-test-11.slatic.net/p/7/dep-quai-ngang-3-soc-bam-san-rat-tot-do-1016-17191271-fd54a61d642e2a031436ca455e80e464-catalog_233.jpg', 'Dép Quai Ngang 3 Sọc- Bám Sàn Rất Tốt ', 88, 2);





COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
