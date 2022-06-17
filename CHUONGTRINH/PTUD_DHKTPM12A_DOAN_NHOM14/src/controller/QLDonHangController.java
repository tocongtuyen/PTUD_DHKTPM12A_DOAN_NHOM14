package controller;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import dao.ChiTietDonHangDao;
import dao.DonHangDao;
import dao.KhachHangDao;
import dao.NhanVienDao;
import entity.ChiTietDonHang;
import entity.DonHang;
import entity.NhanVien;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import org.controlsfx.control.textfield.TextFields;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

public class QLDonHangController implements Initializable {

    @FXML
    private JFXComboBox<?> cbbTrangThai;

    @FXML
    private JFXTextField txtDiaChi;

    @FXML
    private JFXTextField txtNgayDat;

    @FXML
    private JFXTextField txtMaDonHang;

    @FXML
    private JFXTextField txtMaKhachHang;

    @FXML
    private JFXTextField txtTenKhachHang;

    @FXML
    private JFXTextField txtMaNhanVien;

    @FXML
    private JFXTextField txtTenNhanVien;

    @FXML
    private TableColumn<?, ?> columnMaHD;

    @FXML
    private TableColumn<?, ?> columnMaKH;

    @FXML
    private TableColumn<?, ?> columnNV;

    @FXML
    private TableColumn<?, ?> columnDiaChi;

    @FXML
    private TableColumn<?, ?> columnNgayDat;

    @FXML
    private TableColumn<?, ?> columnTrangThai;

    @FXML
    private TableView<ChiTietDonHang> tableChiTietDH;

    @FXML
    private TableColumn<?, ?> columnMaSP;

    @FXML
    private TableColumn<?, ?> columnTenSP;

    @FXML
    private TableColumn<?, ?> columnSoLuong;

    @FXML
    private TableColumn<?, ?> columnDonGia;

    @FXML
    private TableColumn<?, ?> columnGiamGia;

    @FXML
    private TableColumn<?, ?> columnThanhTien;

    @FXML
    private JFXTextField txtTim;

    @FXML
    private TableView<DonHang> tableHoaDon;

    @FXML
    private Label lblTaiKhoan;

    @FXML
    private JFXTextField txtTrangThai;

    ObservableList<NhanVien> listNV;
    NhanVienDao nhanVienDao;
    ObservableList<DonHang> data;
    DonHangDao listDH = new DonHangDao();
    KhachHangDao khachHangDao = new KhachHangDao();
    DonHang donHang = new DonHang();
    ChiTietDonHangDao chiTietDonHangDao = new ChiTietDonHangDao();
    ObservableList<ChiTietDonHang> dataCT = FXCollections.observableArrayList();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        data = FXCollections.observableArrayList();
        setCellTable();
//        loadHoaDon();

        setCellTableCT();

        TextFields.bindAutoCompletion(txtTim, khachHangDao.loadSDT());

        txtTim.setOnKeyPressed((KeyEvent e) -> {
            if(e.getCode() == KeyCode.ENTER){
                String SDT = txtTim.getText();
                if(!SDT.isEmpty() && SDT.matches("[0-9]+")) {
                    try {
                        String maKH = khachHangDao.searchSDT(SDT).get(0).getMaKhachHang();
                        String tenKH = khachHangDao.searchSDT(SDT).get(0).getTenKhachHang();

                        txtMaKhachHang.setText(maKH);
                        txtTenKhachHang.setText(tenKH);
//                        txtError.setText("");
                        loadHoaDon(SDT);
                    }catch (Exception e1){
//                        txtMaKhachHang.setText("");
//                        txtTenKhachHang.setText("");
//                        txtError.setText("Không tìm thấy khách hàng!");
                        Alert alert = new Alert(Alert.AlertType.INFORMATION);
                        alert.setTitle("Thông báo");
                        alert.setHeaderText("Không tìm thông tin hóa đơn của khách hàng");
                        //alert.setContentText("");
                        Optional<ButtonType> BT=alert.showAndWait();
                    }
                }else {
//                    txtMaKhachHang.setText("");
//                    txtTenKhachHang.setText("");
//                    txtError.setText("Không tìm thấy khách hàng!");
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Thông báo");
                    alert.setHeaderText("Không tìm thông tin hóa đơn của khách hàng");
                    //alert.setContentText("");
                    Optional<ButtonType> BT=alert.showAndWait();
                }
            }
        });
    }

    @FXML
    public void setTaiKhoan(String maNV){
        nhanVienDao = new NhanVienDao();
        listNV = nhanVienDao.loadNhanVien(maNV);
        lblTaiKhoan.setText(listNV.get(0).getTaiKhoan());
    }

    public void setCellTable(){
        columnMaHD.setCellValueFactory(new PropertyValueFactory<>("maDonHang"));
        columnMaKH.setCellValueFactory(new PropertyValueFactory<>("maKhachHang"));
        columnNV.setCellValueFactory(new PropertyValueFactory<>("maNhanVien"));
        columnDiaChi.setCellValueFactory(new PropertyValueFactory<>("diaChiGiaoHang"));
        columnNgayDat.setCellValueFactory(new PropertyValueFactory<>("ngayDatHang"));
        columnTrangThai.setCellValueFactory(new PropertyValueFactory<>("trangThai"));
    }

    public void loadHoaDon(String sdt){
        data.clear();//xoa bang cu
        data = listDH.loadData(sdt);
        tableHoaDon.setItems(data);
    }

    public void setCellTableCT(){
        columnMaSP.setCellValueFactory(new PropertyValueFactory<>("maSanPham"));
        columnTenSP.setCellValueFactory(new PropertyValueFactory<>("tenSanPham"));
        columnSoLuong.setCellValueFactory(new PropertyValueFactory<>("soLuong"));
        columnDonGia.setCellValueFactory(new PropertyValueFactory<>("giaBan"));
        columnGiamGia.setCellValueFactory(new PropertyValueFactory<>("giamGia"));
        columnThanhTien.setCellValueFactory(new PropertyValueFactory<>("thanhTien"));
    }

    public void loadChiTiet(String maDonHang){
        dataCT.clear();//xoa bang cu
        dataCT = chiTietDonHangDao.loadData(maDonHang);
        tableChiTietDH.setItems(dataCT);
    }

    @FXML
    public void backMain(ActionEvent event) throws Exception{
        Stage stage = (Stage)((Node) event.getSource()).getScene().getWindow();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/ui/MainGUI.fxml"));
        Parent parent = loader.load();
        Scene scene = new Scene(parent);
        stage.setTitle("Quản lý mua bán linh kiện");
        MainController controller = loader.getController();
        controller.setTenTK(listNV.get(0).getMaNhanVien(),listNV.get(0).getTenNhanVien(),listNV.get(0).getChucVu());
        stage.setScene(scene);
    }

    @FXML
    void tableClick(MouseEvent e) {
        int row = 0;
        if (MouseButton.PRIMARY == e.getButton()) {
//            row = tableChiTietDH.getSelectionModel().getSelectedIndex();
            donHang = tableHoaDon.getSelectionModel().getSelectedItem();
            txtMaNhanVien.setText(String.valueOf(donHang.getMaNhanVien()));
            txtMaDonHang.setText(String.valueOf(donHang.getMaDonHang()));
            txtMaKhachHang.setText(String.valueOf(donHang.getMaKhachHang()));
            txtDiaChi.setText(donHang.getDiaChiGiaoHang());
            txtNgayDat.setText(donHang.getNgayDatHang());
            txtTrangThai.setText(donHang.getTrangThai());

        }
        if (e .getClickCount() == 2) {
            donHang = tableHoaDon.getSelectionModel().getSelectedItem();
            loadChiTiet(donHang.getMaDonHang());
        }
    }
}
