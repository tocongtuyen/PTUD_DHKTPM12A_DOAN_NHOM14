package entity;

public class ChiTietKhuyenMai {
    private String maKhuyenMai;
    private String maSanPham;
    private String ngayBatDau;
    private String ngayKetThuc;
    private int mucKhuyenMai;

    public ChiTietKhuyenMai(int mucKhuyenMai) {
        this.mucKhuyenMai = mucKhuyenMai;
    }

    public String getMaKhuyenMai() {
        return maKhuyenMai;
    }

    public void setMaKhuyenMai(String maKhuyenMai) {
        this.maKhuyenMai = maKhuyenMai;
    }

    public String getMaSanPham() {
        return maSanPham;
    }

    public void setMaSanPham(String maSanPham) {
        this.maSanPham = maSanPham;
    }

    public String getNgayBatDau() {
        return ngayBatDau;
    }

    public void setNgayBatDau(String ngayBatDau) {
        this.ngayBatDau = ngayBatDau;
    }

    public String getNgayKetThuc() {
        return ngayKetThuc;
    }

    public void setNgayKetThuc(String ngayKetThuc) {
        this.ngayKetThuc = ngayKetThuc;
    }

    public int getMucKhuyenMai() {
        return mucKhuyenMai;
    }

    public void setMucKhuyenMai(int mucKhuyenMai) {
        this.mucKhuyenMai = mucKhuyenMai;
    }
}
