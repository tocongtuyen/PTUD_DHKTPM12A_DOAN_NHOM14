Create database PTUD_DHKTPM12A_DOAN_NHOM14
go
use PTUD_DHKTPM12A_DOAN_NHOM14
go
Create table NhanVien(
maNhanVien int identity(16019571,130) not null primary key,
tenNhanVien nvarchar(100) not null,
soDT varchar(100) unique not null,
email varchar(100) unique,
chucVu nvarchar(100),
ngayVaoLam date,
taiKhoan varchar(100) unique,
matKhau varchar(100),
diaChi nvarchar(200),
trangThai nvarchar(100),
)
go
Create table KhachHang(
maKhachHang int identity(16011911,130) not null primary key,
tenKhachHang nvarchar(100) not null,
soDienThoai varchar(100) unique not null,
email varchar(100) unique,
diaChi nvarchar(200) not null
)
go
Create table KhuyenMai(
maKhuyenMai int identity(16029921,130) not null primary key,
tenKhuyenMai nvarchar(100) not null
)
go
Create table NhaCungCap(
maNhaCungCap int identity(16029281,130) not null primary key,
tenNhaCungCap nvarchar(100) not null,
soDienThoai varchar(100) unique not null,
email varchar(100) unique,
diaChi nvarchar(200) not null,
)
go
Create table SanPham(
maSanPham int identity(16013681,130) not null primary key,
maNhaCungCap int not null,
tenSanPham nvarchar(100) not null,
thoiGianBaoHanh int,
soLuong int not null,
donGia money not null,
trangThai nvarchar(100),
CONSTRAINT fk_maNhaCungCap FOREIGN KEY (maNhaCungCap) REFERENCES NhaCungCap (maNhaCungCap),
)
go
Create table DonHang(
maDonHang int identity(16014091,130) not null primary key,
maKhachHang int not null,
maNhanVien int not null,
diaChiGiaoHang nvarchar(200),
ngayDatHang date,
trangThai nvarchar(100),
CONSTRAINT fk_maKhachHang FOREIGN KEY (maKhachHang) REFERENCES KhachHang (maKhachHang),
CONSTRAINT fk_maNhanVien1 FOREIGN KEY (maNhanVien) REFERENCES NhanVien (maNhanVien) 
)
go
Create table ChiTietDonHang(
maSanPham int not null,
maDonHang int not null,
soLuong int not null,
giaBan money not null,
CONSTRAINT pk_ChiTietDonHang PRIMARY KEY (maSanPham,maDonHang),
CONSTRAINT fk_maSanPham FOREIGN KEY (maSanPham) REFERENCES SanPham (maSanPham),
CONSTRAINT fk_maDonHang FOREIGN KEY (maDonHang) REFERENCES DonHang (maDonHang)
)
go
Create table ChiTietKhuyenMai(
maKhuyenMai int not null,
maSanPham int not null,
ngayBatDau date,
ngayKetThuc date,
mucKhuyenMai int not null,
CONSTRAINT pk_ChiTietKhuyenMai PRIMARY KEY (maKhuyenMai,maSanPham),
CONSTRAINT fk_maKhuyenMai FOREIGN KEY (maKhuyenMai) REFERENCES KhuyenMai (maKhuyenMai),
CONSTRAINT fk_maSanPham1 FOREIGN KEY (maSanPham) REFERENCES SanPham (maSanPham)  
)

go

INSERT INTO NhaCungCap (tenNhaCungCap, soDienThoai, email, diaChi) VALUES ( 'Phong Vu', 0862908777, 'phongvu@gmail.com', 'quan 1, tpHCM')
INSERT INTO NhaCungCap (tenNhaCungCap, soDienThoai, email, diaChi) VALUES ( 'Hoang Phat', 0988992518, 'hoangphat@yahoo.com.vn', 'quan 2, tpHCM')
INSERT INTO NhaCungCap (tenNhaCungCap, soDienThoai, email, diaChi) VALUES ( 'Kim Ngoc', 01688883627, 'kimngoc06@gmail.com', 'quan 3, tpHCM')
INSERT INTO NhaCungCap (tenNhaCungCap, soDienThoai, email, diaChi) VALUES ( 'Nhu Y', 0907000350, 'nhuy3501@yahoo.com.vn', 'quan 4, tpHCM')
INSERT INTO NhaCungCap (tenNhaCungCap, soDienThoai, email, diaChi) VALUES ( 'Thanh Long', 0989420655, 'thanhlong81@gmail.com', 'quan 5, tpHCM')
INSERT INTO NhaCungCap (tenNhaCungCap, soDienThoai, email, diaChi) VALUES ( 'Bach Ho', 01699118349, 'bachho79@yahoo.com.vn', 'quan 6, tpHCM')
INSERT INTO NhaCungCap (tenNhaCungCap, soDienThoai, email, diaChi) VALUES ( 'Chu Tuoc', 01689525898, 'chutuoc76@gmail.com', 'quan 7, tpHCM')
INSERT INTO NhaCungCap (tenNhaCungCap, soDienThoai, email, diaChi) VALUES ( 'Huyen Vu', 01235099415, 'huyenvu89@yahoo.com.vn', 'quan 8, tpHCM')



INSERT INTO SanPham (maNhaCungCap, tenSanPham, thoiGianBaoHanh, soLuong, donGia, trangThai) VALUES (16029281, N'O cung SSD Intel Optane 16GB', 12, 210, 950, N'Đang kinh doanh')
INSERT INTO SanPham (maNhaCungCap, tenSanPham, thoiGianBaoHanh, soLuong, donGia, trangThai) VALUES (16029281, N'Mainboard Asus Rog Strix Z370', 12, 112, 3940, N'Đang kinh doanh')
INSERT INTO SanPham (maNhaCungCap, tenSanPham, thoiGianBaoHanh, soLuong, donGia, trangThai) VALUES (16029411, N'Card man hinh Asus 4GB CERBERUS-GTX1050TI-O4G', 12, 120, 4890, N'Đang kinh doanh')
INSERT INTO SanPham (maNhaCungCap, tenSanPham, thoiGianBaoHanh, soLuong, donGia, trangThai) VALUES (16029411, N'O dia DVD WR Asus SDRW-08D2S-S', 6, 350, 690, N'Đang kinh doanh')
INSERT INTO SanPham (maNhaCungCap, tenSanPham, thoiGianBaoHanh, soLuong, donGia, trangThai) VALUES (16029541, N'CPU Core i3-7100 (3.9GHz)', 9, 281, 3425, N'Đang kinh doanh')
INSERT INTO SanPham (maNhaCungCap, tenSanPham, thoiGianBaoHanh, soLuong, donGia, trangThai) VALUES (16029541, N'O cung HDD WD 1TB WD10EZEX Sata 3', 12, 130, 1100, N'Đang kinh doanh')
INSERT INTO SanPham (maNhaCungCap, tenSanPham, thoiGianBaoHanh, soLuong, donGia, trangThai) VALUES (16029671, N'Bo nho DDR4 Kingmax 4GB', 6, 342, 1055, N'Đang kinh doanh')
INSERT INTO SanPham (maNhaCungCap, tenSanPham, thoiGianBaoHanh, soLuong, donGia, trangThai) VALUES (16029671, N'Quat CPU CMHyper 212 LED', 6, 212, 760, N'Đang kinh doanh')
INSERT INTO SanPham (maNhaCungCap, tenSanPham, thoiGianBaoHanh, soLuong, donGia, trangThai) VALUES (16029801, N'FAN CPU Deepcool Gammaxx 400', 6, 230, 570, N'Đang kinh doanh')
INSERT INTO SanPham (maNhaCungCap, tenSanPham, thoiGianBaoHanh, soLuong, donGia, trangThai) VALUES (16029801, N'FAN CPU CM T2 mini', 6, 400, 220, N'Đang kinh doanh')
INSERT INTO SanPham (maNhaCungCap, tenSanPham, thoiGianBaoHanh, soLuong, donGia, trangThai) VALUES (16029801, N'Quat CPU Corsair Hydro Cooler', 12, 190, 2190, N'Đang kinh doanh')
INSERT INTO SanPham (maNhaCungCap, tenSanPham, thoiGianBaoHanh, soLuong, donGia, trangThai) VALUES (16029931, N'Quat case 8cm', 0, 290, 14, N'Đang kinh doanh')
INSERT INTO SanPham (maNhaCungCap, tenSanPham, thoiGianBaoHanh, soLuong, donGia, trangThai) VALUES (16030061, N'Ban phim laptop Lenovo 110-14', 0, 520, 476, N'Đang kinh doanh')
INSERT INTO SanPham (maNhaCungCap, tenSanPham, thoiGianBaoHanh, soLuong, donGia, trangThai) VALUES (16030191, N'Thung may XA-10', 6, 280, 280, N'Đang kinh doanh')



INSERT INTO NhanVien (tenNhanVien, soDT, email, chucVu, ngayVaoLam, taiKhoan, matKhau, diaChi, trangThai) VALUES (N'To Cong Tuyen', '01255676424', 'tctuyen1998@gmail.com', N'Quản lý', getdate(), 'sa', '123', 'tphcm', 'Đang làm')
INSERT INTO NhanVien (tenNhanVien, soDT, email, chucVu, ngayVaoLam, taiKhoan, matKhau, diaChi, trangThai) VALUES (N'Tran Hong Le', '0977357823', 'tranhongle2201@gmail.com', N'Quản lý', getdate(), 'sb', '123', 'Ha Noi', 'Đang làm')
INSERT INTO NhanVien (tenNhanVien, soDT, email, chucVu, ngayVaoLam, taiKhoan, matKhau, diaChi, trangThai) VALUES (N'Phan Ngoc De', '0966179514', 'ngocdemr@gmail.com', N'Bán hàng', getdate(), 'ngocdemr', '123a', 'DakLak', 'Đang làm')
INSERT INTO NhanVien (tenNhanVien, soDT, email, chucVu, ngayVaoLam, taiKhoan, matKhau, diaChi, trangThai) VALUES (N'Doan Ky', '01656749911', 'doanky1998@gmail.com', N'Bán hàng', getdate(), 'doanky1998', '123b', 'Hai Phong', 'Đã nghỉ')
INSERT INTO NhanVien (tenNhanVien, soDT, email, chucVu, ngayVaoLam, taiKhoan, matKhau, diaChi, trangThai) VALUES (N'Le Ngoc Khanh', '0962203523', 'lengockhanh1602@gmail.com', N'Bán hàng', getdate(), 'lengockhanh', '123c', 'tphcm','Đã nghỉ')
INSERT INTO NhanVien (tenNhanVien, soDT, email, chucVu, ngayVaoLam, taiKhoan, matKhau, diaChi, trangThai) VALUES (N'Pham Thuy Linh', '01653194721', 'phamthuylinh_2704@gmail.com', N'Bán hàng', getdate(), 'phamthuylinh', N'123d', 'tphcm', 'Đã nghỉ')



INSERT INTO KhachHang ( tenKhachHang, soDienThoai, email, diaChi) VALUES ( N'Trần Văn Sang', '0939945565', 'tranvansang@gmail.com', N'p5, go vap, TPHCM')
INSERT INTO KhachHang ( tenKhachHang, soDienThoai, email, diaChi) VALUES ( N'Hà Văn Huyện', '0939166160', 'havanluyen@gmail.com', N'Thu Duc, TPHCM')
INSERT INTO KhachHang ( tenKhachHang, soDienThoai, email, diaChi) VALUES ( N'Trần Văn Cao', '0914409356', 'tranvancao@gmail.com', N'Ben Tre')
INSERT INTO KhachHang ( tenKhachHang, soDienThoai, email, diaChi) VALUES ( N'Đỗ Thị Tuyết Loan', '0979748494', 'dothituyetloan@gmail.com', N'Bac Lieu')
INSERT INTO KhachHang ( tenKhachHang, soDienThoai, email, diaChi) VALUES ( N'Lê Văn Bình', '0913199757', 'levanbinh@gmail.com', N'Soc Trang')
INSERT INTO KhachHang ( tenKhachHang, soDienThoai, email, diaChi) VALUES ( N'Nguyễn Nhiệm Toàn', '0918122588', 'nguyentoannhiem@gmail.com', N'Vung Tau')
INSERT INTO KhachHang ( tenKhachHang, soDienThoai, email, diaChi) VALUES ( N'Nguyễn Văn Diệu', '0918660420', 'nguyenvandieu@gmail.com', N'Phan Thiet')
INSERT INTO KhachHang ( tenKhachHang, soDienThoai, email, diaChi) VALUES ( N'Tạ Quốc Thiện', '0963939994', 'taquocthien@gmail.com', N'Can Tho')
INSERT INTO KhachHang ( tenKhachHang, soDienThoai, email, diaChi) VALUES ( N'Trần Thị Kim Em', '01295779916', 'trankimem@gmail.com', N'Ha Noi')
INSERT INTO KhachHang ( tenKhachHang, soDienThoai, email, diaChi) VALUES ( N'Lê Thị Kim Anh', '01693535884', 'lethikimanh@gmail.com', N'Vinh Phuc')
INSERT INTO KhachHang ( tenKhachHang, soDienThoai, email, diaChi) VALUES ( N'Trang Mỹ Loan', '0919444383', 'trangmyloan@gmail.com', N'Phu Yen')
INSERT INTO KhachHang ( tenKhachHang, soDienThoai, email, diaChi) VALUES ( N'Trịnh Thị Lài', '01697277498', 'trinhthilai@gmail.com', N'Hai Phong')
INSERT INTO KhachHang ( tenKhachHang, soDienThoai, email, diaChi) VALUES ( N'Nguyễn Thị Mỹ', '0986716397', 'nguyenthimy@gmail.com', N'Thanh Hoa')
INSERT INTO KhachHang ( tenKhachHang, soDienThoai, email, diaChi) VALUES ( N'Lý Hùng Dũng', '0837271516', 'lyhungdung@gmail.com', N'Da Nang')


INSERT INTO KhuyenMai(tenKhuyenMai) VALUES(N'Mừng ngày nhà giáo VN 20/11')
INSERT INTO KhuyenMai(tenKhuyenMai) VALUES(N'Quốc Khánh 2/9')
INSERT INTO KhuyenMai(tenKhuyenMai) VALUES(N'Tết Nguyên Đán')
INSERT INTO KhuyenMai(tenKhuyenMai) VALUES(N'Valentine')
INSERT INTO KhuyenMai(tenKhuyenMai) VALUES(N'Quốc tế phụ nữ')
INSERT INTO KhuyenMai(tenKhuyenMai) VALUES(N'Quốc tế thiếu nhi')


INSERT INTO ChiTietKhuyenMai (maKhuyenMai, maSanPham, ngayBatDau, ngayKetThuc, mucKhuyenMai) VALUES (16030571, 16013681,GETDATE(), GETDATE(), 3)
INSERT INTO ChiTietKhuyenMai (maKhuyenMai, maSanPham, ngayBatDau, ngayKetThuc, mucKhuyenMai) VALUES (16029921, 16013811,GETDATE(), GETDATE(), 4)
INSERT INTO ChiTietKhuyenMai (maKhuyenMai, maSanPham, ngayBatDau, ngayKetThuc, mucKhuyenMai) VALUES (16030311, 16013941,GETDATE(), GETDATE(), 5)
INSERT INTO ChiTietKhuyenMai (maKhuyenMai, maSanPham, ngayBatDau, ngayKetThuc, mucKhuyenMai) VALUES (16030311, 16014461,GETDATE(), GETDATE(), 5)
INSERT INTO ChiTietKhuyenMai (maKhuyenMai, maSanPham, ngayBatDau, ngayKetThuc, mucKhuyenMai) VALUES (16030311, 16014591,GETDATE(), GETDATE(), 5)
INSERT INTO ChiTietKhuyenMai (maKhuyenMai, maSanPham, ngayBatDau, ngayKetThuc, mucKhuyenMai) VALUES (16030311, 16014721,GETDATE(), GETDATE(), 5)
INSERT INTO ChiTietKhuyenMai (maKhuyenMai, maSanPham, ngayBatDau, ngayKetThuc, mucKhuyenMai) VALUES (16030311, 16014851,GETDATE(), GETDATE(), 5)
INSERT INTO ChiTietKhuyenMai (maKhuyenMai, maSanPham, ngayBatDau, ngayKetThuc, mucKhuyenMai) VALUES (16030311, 16014981,GETDATE(), GETDATE(), 5)
INSERT INTO ChiTietKhuyenMai (maKhuyenMai, maSanPham, ngayBatDau, ngayKetThuc, mucKhuyenMai) VALUES (16030311, 16015111,GETDATE(), GETDATE(), 5)
INSERT INTO ChiTietKhuyenMai (maKhuyenMai, maSanPham, ngayBatDau, ngayKetThuc, mucKhuyenMai) VALUES (16030311, 16015371,GETDATE(), GETDATE(), 5)
INSERT INTO ChiTietKhuyenMai (maKhuyenMai, maSanPham, ngayBatDau, ngayKetThuc, mucKhuyenMai) VALUES (16030441, 16014071,GETDATE(), GETDATE(), 6)
INSERT INTO ChiTietKhuyenMai (maKhuyenMai, maSanPham, ngayBatDau, ngayKetThuc, mucKhuyenMai) VALUES (16030051, 16014201,GETDATE(), GETDATE(), 7)
INSERT INTO ChiTietKhuyenMai (maKhuyenMai, maSanPham, ngayBatDau, ngayKetThuc, mucKhuyenMai) VALUES (16030181, 16014331,GETDATE(), GETDATE(), 8)


INSERT INTO DonHang(maKhachHang,maNhanVien,diaChiGiaoHang,ngayDatHang,trangThai) VALUES(16011911,16019571,'phuong 5, Go Vap, tpHCM',getdate(),'Đã thanh toán')

SELECT * FROM [dbo].[SanPham]
SELECT * FROM [dbo].[NhanVien]
SELECT * FROM [dbo].[NhaCungCap]
SELECT * FROM [dbo].[KhachHang]
SELECT * FROM [dbo].[DonHang]
SELECT * FROM [dbo].[ChiTietDonHang]
SELECT * FROM [dbo].[KhuyenMai]
SELECT * FROM [dbo].[ChiTietKhuyenMai]

--select sp.maSanPham, tenSanPham, ctdh.soLuong, giaBan, mucKhuyenMai, (ctdh.soLuong*giaBan)*(1 - mucKhuyenMai*0.01) as [Thanh tien]  
--from sanpham sp join ChiTietKhuyenMai km 
--on sp.maSanPham = km.maSanPham join ChiTietDonHang ctdh 
--on ctdh.maSanPham = sp.maSanPham join DonHang dh 
--on dh.maDonHang = ctdh.maDonHang 
--where dh.maDonHang = '16014481'

--SELECT * FROM SanPham Where tenSanPham = 'O cung SSD Intel Optane 16GB'

--select maDonHang, k.maKhachHang, maNhanVien, diaChiGiaoHang, ngayDatHang, trangThai from KhachHang k join DonHang d on k.maKhachHang = d.maKhachHang where k.soDienThoai = 0939945565

--select top 10 KH.tenKhachHang,tongTienDaMua=sum(soluong*giaBan)
--					from [dbo].[DonHang] DH join [dbo].[ChiTietDonHang] CTDH 
--                    on DH.maDonHang=CTDH.maDonHang join [dbo].[KhachHang] KH on KH.maKhachHang=DH.maKhachHang
--                    where trangThai=N'Đã thanh toán'
--                    group by KH.tenKhachHang
--                    order by tongTienDaMua DESC

