//package controller;
//
//import com.jfoenix.controls.JFXButton;
//import com.jfoenix.controls.JFXComboBox;
//import com.jfoenix.controls.JFXPasswordField;
//import com.jfoenix.controls.JFXTextField;
//import dao.*;
//import entity.ChiTietDonHang;
//import entity.NhanVien;
//import entity.SanPham;
//import javafx.collections.FXCollections;
//import javafx.collections.ObservableList;
//import javafx.event.ActionEvent;
//import javafx.fxml.FXML;
//import javafx.fxml.FXMLLoader;
//import javafx.fxml.Initializable;
//import javafx.scene.Node;
//import javafx.scene.Parent;
//import javafx.scene.Scene;
//import javafx.scene.control.*;
//import javafx.scene.control.cell.PropertyValueFactory;
//import javafx.scene.input.MouseButton;
//import javafx.scene.input.MouseEvent;
//import javafx.stage.Stage;
//import org.controlsfx.control.textfield.TextFields;
//
//import java.net.URL;
//import java.util.Optional;
//import java.util.ResourceBundle;
//
//public class DonHangController implements Initializable {
//
//    @FXML
//    private JFXComboBox<String> cbbTrangThai;
//
//    @FXML
//    private JFXTextField txtDiaChi;
//
//    @FXML
//    private Label lblTaiKhoan;
//
//    @FXML
//    private TextField txtTaiKhoan;
//
//    @FXML
//    private JFXTextField txtNgayDat;
//
//    @FXML
//    private JFXTextField txtMaDonHang;
//
//    @FXML
//    private JFXTextField txtMaKhachHang;
//
//    @FXML
//    private JFXTextField txtTenKhachHang;
//
//    @FXML
//    private JFXTextField txtMaNhanVien;
//
//    @FXML
//    private JFXTextField txtTenNhanVien;
//
//    @FXML
//    private JFXTextField txtTim;
//
//    @FXML
//    private JFXButton btnTim;
//
//    @FXML
//    private JFXComboBox<SanPham> cbbMaSP;
//
//    @FXML
//    private JFXTextField txtTenSP;
//
//    @FXML
//    private JFXTextField txtGiamGia;
//
//    @FXML
//    private JFXTextField txtDonGia;
//
//    @FXML
//    private JFXTextField txtThanhTien;
//
//    @FXML
//    private JFXComboBox<Integer> cbbSoLuong;
//
//    @FXML
//    private TableView<ChiTietDonHang> tableChiTietDH;
//
//    @FXML
//    private TableColumn<?, ?> columnMaSP;
//
//    @FXML
//    private TableColumn<?, ?> columnTenSP;
//
//    @FXML
//    private TableColumn<?, ?> columnSoLuong;
//
//    @FXML
//    private TableColumn<?, ?> columnDonGia;
//
//    @FXML
//    private TableColumn<?, ?> columnGiamGia;
//
//    @FXML
//    private TableColumn<?, ?> columnThanhTien;
//
//    @FXML
//    private Label txtError;
//
//    @FXML
//    private Button btnThem;
//
//    @FXML
//    private Button btnSua;
//
//    @FXML
//    private Button btnXoa;
//
//    @FXML
//    private Button btnLuu;
//
//    @FXML
//    private TextField txtTongTien;
//
//    ObservableList<ChiTietDonHang> data = FXCollections.observableArrayList();
//    SanPhamDao sp = new SanPhamDao();
//    ChiTietDonHang chiTietDonHang = new ChiTietDonHang();
//    ChiTietDonHangDao ctdh=new ChiTietDonHangDao();
//    ChiTietKhuyenMaiDao cTKM = new ChiTietKhuyenMaiDao();
//    KhachHangDao khachHangDao = new KhachHangDao();
//    DonHangDao donHangDao = new DonHangDao();
//    NhanVienDao nhanVienDao;
//    ObservableList<NhanVien> listNV;
//
//
//    int row = 0;
//    @Override
//    public void initialize(URL location, ResourceBundle resources) {
//        cbbMaSP = new JFXComboBox<SanPham>();
//        String [] possibleWords = {"hai","hello","how are you"};
////        khachHangDao.loadSDT();
//        TextFields.bindAutoCompletion(txtTim, khachHangDao.loadSDT());
//
////        System.out.println(mainController.setTextNhanVien());
//
//        cbbTrangThai.getItems().addAll("Đã thanh toán","Chưa thanh toán");
//        cbbTrangThai.getSelectionModel().select(0);
//        for(int i = 1 ; i <= 30 ; i++){
//            cbbSoLuong.getItems().addAll(i);
//        }
//        cbbSoLuong.getSelectionModel().select(0);
//        ObservableList<SanPham> list = sp.loadMaSP();
//        cbbMaSP.setItems(list);
//        txtNgayDat.setText(String.valueOf(java.time.LocalDate.now()));
//        setCellTable();
//    }
//
//    @FXML
//    public void setTaiKhoan(String maNV, String tenNV){
//        nhanVienDao = new NhanVienDao();
//        txtMaNhanVien.setText(maNV);
//        txtTenNhanVien.setText(tenNV);
//        listNV = nhanVienDao.loadNhanVien(maNV);
//        lblTaiKhoan.setText(listNV.get(0).getTaiKhoan());
//    }
//
//    // hàm tìm kiếm khách hàng load dữ liệu vào textfield
//    @FXML
//    public void searchKhachHang(ActionEvent event) {
//        String SDT = txtTim.getText();
//        try {
//            khachHangDao.searchSDT(SDT);
//            String maKH = khachHangDao.searchSDT(SDT).get(0).getMaKhachHang();
//            String tenKH = khachHangDao.searchSDT(SDT).get(0).getTenKhachHang();
//
//            txtMaKhachHang.setText(maKH);
//            txtTenKhachHang.setText(tenKH);
//            txtError.setText("");
//        }catch (Exception e){
//            txtMaKhachHang.setText("");
//            txtTenKhachHang.setText("");
//            txtError.setText("Không tìm thấy khách hàng!");
//        }
//    }
//
//
//
//    // hàm load dữ liệu vào từng cột của tableview
//    public void setCellTable(){
//        columnMaSP.setCellValueFactory(new PropertyValueFactory<>("maSanPham"));
//        columnSoLuong.setCellValueFactory(new PropertyValueFactory<>("soLuong"));
//        columnDonGia.setCellValueFactory(new PropertyValueFactory<>("giaBan"));
//        columnTenSP.setCellValueFactory(new PropertyValueFactory<>("tenSP"));
//        columnGiamGia.setCellValueFactory(new PropertyValueFactory<>("giamGia"));
//        columnThanhTien.setCellValueFactory(new PropertyValueFactory<>("thanhTien"));
//    }
//
//    // hàm set gia trị của chi tiết đơn hàng
//    @FXML
//    void addChiTiet(ActionEvent event) {
//        tinhTien();
//        chiTietDonHang  = new ChiTietDonHang();
//        chiTietDonHang.setMaSanPham(String.valueOf(Integer.parseInt(String.valueOf(cbbMaSP.getSelectionModel().getSelectedItem()))));
//        chiTietDonHang.setSoLuong(Integer.parseInt(String.valueOf(cbbSoLuong.getSelectionModel().getSelectedItem())));
//        chiTietDonHang.setGiaBan(Double.parseDouble(txtDonGia.getText()));
//        chiTietDonHang.setTenSP(txtTenSP.getText());
//        chiTietDonHang.setGiamGia(Integer.parseInt(txtGiamGia.getText()));
//        chiTietDonHang.setThanhTien(Double.parseDouble(txtThanhTien.getText()));
//
//        data.add(chiTietDonHang);
//        tableChiTietDH.setItems(data);
//        tongTien();
//
//    }
//
//    //hàm tính tiền
//    public void tinhTien(){
//        int soLuong = Integer.parseInt(String.valueOf(cbbSoLuong.getSelectionModel().getSelectedItem()));
//        Double giaBan = Double.parseDouble(txtDonGia.getText());
//        int giamGia = Integer.parseInt(txtGiamGia.getText());
//        txtThanhTien.setText(String.valueOf((soLuong*giaBan)*(1 - giamGia*0.01)));
//    }
//
//    //hàm tính tổng tiền
//    public void tongTien(){
//        int i = -1;
//        double tong = 0;
//        for(ChiTietDonHang ct:data){
//            i++;
//            tong += data.get(i).getThanhTien();
//        }
//        txtTongTien.setText(String.valueOf(tong));
//    }
//
//    // load dữ liệu vào textfield
//    @FXML
//    public void loadSanPhamCombobox(ActionEvent event) {
//        String maSP = String.valueOf(cbbMaSP.getSelectionModel().getSelectedItem());
//
//        String tenSP = String.valueOf(sp.loadSPTheoMa(maSP).get(0).getTenSanPham());
//        Double donGia = sp.loadSPTheoMa(maSP).get(0).getDonGia();
//        int mucKhuyenMai = cTKM.loadSPTheoMa(maSP).get(0).getMucKhuyenMai();
//
//        txtTenSP.setText(tenSP);
//        txtDonGia.setText(String.valueOf(donGia));
//        txtGiamGia.setText(String.valueOf(mucKhuyenMai));
//    }
//
//    // hàm xóa 1 chi tiết
//    @FXML
//    void deleteChiTiet(ActionEvent event) {
//        tableChiTietDH.getItems().removeAll(tableChiTietDH.getSelectionModel().getSelectedItem());
//        tongTien();
//    }
//
//    //hàm lưu đơn hàng và chi tiết
//    @FXML
//    void saveHoaDon(ActionEvent event) {
//        int i = -1;
//        donHangDao.create(txtMaKhachHang.getText(),txtMaNhanVien.getText(),txtDiaChi.getText(),txtNgayDat.getText(),cbbTrangThai.getSelectionModel().getSelectedItem());
//        String maDonHang = String.valueOf(donHangDao.loadMaDonHang());
//        for(ChiTietDonHang ct:data){
//            i++;
//            String maSP = data.get(i).getMaSanPham();
//            int soLuong = data.get(i).getSoLuong();
//            Double giaBan = data.get(i).getGiaBan();
//            ctdh.create(maSP,maDonHang,soLuong,giaBan);
//        }
//        Alert alert = new Alert(Alert.AlertType.INFORMATION);
//        alert.setTitle("Thông báo");
//        alert.setHeaderText("Lưu thành công");
//        //alert.setContentText("Are you sure?");
//        Optional<ButtonType> BT=alert.showAndWait();
//    }
//
//    //hàm cập nhật chi tiết
//    @FXML
//    void updateChiTiet(ActionEvent event) {
//        tinhTien();
//        chiTietDonHang  = new ChiTietDonHang();
//        chiTietDonHang.setMaSanPham(String.valueOf(Integer.parseInt(String.valueOf(cbbMaSP.getSelectionModel().getSelectedItem()))));
//        chiTietDonHang.setSoLuong(Integer.parseInt(String.valueOf(cbbSoLuong.getSelectionModel().getSelectedItem())));
//        chiTietDonHang.setGiaBan(Double.parseDouble(txtDonGia.getText()));
//        chiTietDonHang.setTenSP(txtTenSP.getText());
//        chiTietDonHang.setGiamGia(Integer.parseInt(txtGiamGia.getText()));
//        chiTietDonHang.setThanhTien(Double.parseDouble(txtThanhTien.getText()));
//
//        data.set(row,chiTietDonHang);
//        tableChiTietDH.setItems(data);
//        tongTien();
//    }
//
//    // hàm nạp data vào textfield khi click tableview
//    @FXML
//    public void tableClick(MouseEvent e) {
//        if (MouseButton.PRIMARY == e.getButton()) {
//            chiTietDonHang = tableChiTietDH.getSelectionModel().getSelectedItem();
//            txtThanhTien.setText(String.valueOf(chiTietDonHang.getThanhTien()));
//            txtTenSP.setText(chiTietDonHang.getTenSP());
//            txtGiamGia.setText(String.valueOf(chiTietDonHang.getGiamGia()));
//            txtDonGia.setText(String.valueOf(chiTietDonHang.getGiaBan()));
//
//            row = tableChiTietDH.getSelectionModel().getSelectedIndex();
//        }
//    }
//
//    @FXML
//    public void backMain(ActionEvent event) throws Exception{
//        Stage stage = (Stage)((Node) event.getSource()).getScene().getWindow();
//        FXMLLoader loader = new FXMLLoader();
//        loader.setLocation(getClass().getResource("/ui/MainGUI.fxml"));
//        Parent parent = loader.load();
//        Scene scene = new Scene(parent);
//        MainController controller = loader.getController();
//        controller.setTenTK(listNV.get(0).getMaNhanVien(),listNV.get(0).getTenNhanVien(),listNV.get(0).getChucVu());
//        stage.setScene(scene);
//    }
//}

package controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import dao.*;
import entity.ChiTietDonHang;
import entity.NhanVien;
import entity.SanPham;
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
import org.controlsfx.control.textfield.AutoCompletionBinding;
import org.controlsfx.control.textfield.TextFields;

import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

public class DonHangController implements Initializable {

    @FXML
    private JFXComboBox<String> cbbTrangThai;

    @FXML
    private JFXTextField txtDiaChi;

    @FXML
    private Label lblTaiKhoan;

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
    private JFXTextField txtTim;

    @FXML
    private JFXButton btnTim;

    @FXML
    private JFXComboBox<SanPham> cbbM = new JFXComboBox<SanPham>();

    @FXML
    private JFXTextField txtTenSP;

    @FXML
    private JFXTextField txtGiamGia;

    @FXML
    private JFXTextField txtDonGia;

    @FXML
    private JFXTextField txtThanhTien;

    @FXML
    private JFXComboBox<Integer> cbbSoLuong;

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
    private Label txtError;

    @FXML
    private Button btnThem;

    @FXML
    private Button btnSua;

    @FXML
    private Button btnXoa;

    @FXML
    private Button btnLuu;

    @FXML
    private TextField txtTongTien;

    @FXML
    private JFXTextField txtTimSP;

    ObservableList<ChiTietDonHang> data = FXCollections.observableArrayList();
    SanPhamDao sp = new SanPhamDao();
    ChiTietDonHang chiTietDonHang = new ChiTietDonHang();
    ChiTietDonHangDao ctdh=new ChiTietDonHangDao();
    ChiTietKhuyenMaiDao cTKM = new ChiTietKhuyenMaiDao();
    KhachHangDao khachHangDao = new KhachHangDao();
    DonHangDao donHangDao = new DonHangDao();
    NhanVienDao nhanVienDao;
    ObservableList<NhanVien> listNV;
    String maDonHang = String.valueOf(donHangDao.loadMaDonHang()+130);
    AutoCompletionBinding<String> autoCompletionBinding;

    int row = 0;
    @Override
    public void initialize(URL location, ResourceBundle resources) {

        txtMaDonHang.setText(maDonHang);

        TextFields.bindAutoCompletion(txtTim, khachHangDao.loadSDT());
//        TextFields.bindAutoCompletion(txtTimSP,sp.loadDSMaSP());
        TextFields.bindAutoCompletion(txtTenSP,sp.loadTenSP());

        txtTim.setOnKeyPressed((KeyEvent e) -> {
            if(e.getCode() == KeyCode.ENTER){
                String SDT = txtTim.getText();
                if(!SDT.isEmpty() && SDT.matches("[0-9]+")) {
                    try {
                        String maKH = khachHangDao.searchSDT(SDT).get(0).getMaKhachHang();
                        String tenKH = khachHangDao.searchSDT(SDT).get(0).getTenKhachHang();

                        txtMaKhachHang.setText(maKH);
                        txtTenKhachHang.setText(tenKH);
                        txtError.setText("");
                    }catch (Exception e1){
                        txtMaKhachHang.setText("");
                        txtTenKhachHang.setText("");
                        txtError.setText("Không tìm thấy khách hàng!");
                    }
                }else {
                    txtMaKhachHang.setText("");
                    txtTenKhachHang.setText("");
                    txtError.setText("Không tìm thấy khách hàng!");
                }
            }
        });

        timTenSanPham();

        cbbTrangThai.getItems().addAll("Đã thanh toán","Chưa thanh toán");
        cbbTrangThai.getSelectionModel().select(1);
        for(int i = 1 ; i <= 20 ; i++){
            cbbSoLuong.getItems().addAll(i);
        }
        cbbSoLuong.getSelectionModel().select(0);
        ObservableList<SanPham> list = sp.loadMaSP();
//        cbbMaSP.setItems(list);
        txtNgayDat.setText(String.valueOf(java.time.LocalDate.now()));
        setCellTable();
    }

    @FXML
    public void setTaiKhoan(String maNV, String tenNV){
        nhanVienDao = new NhanVienDao();
        txtMaNhanVien.setText(maNV);
        txtTenNhanVien.setText(tenNV);
        listNV = nhanVienDao.loadNhanVien(maNV);
        lblTaiKhoan.setText(listNV.get(0).getTaiKhoan());
    }

    public void timSanPhamTheoMa(){
        txtTimSP.setOnKeyPressed((KeyEvent e) -> {
            if(e.getCode() == KeyCode.ENTER){
                String maSP = txtTimSP.getText();
                if(!maSP.isEmpty() && maSP.matches("[0-9]+")) {
                    try {
                        String tenSP = String.valueOf(sp.loadSPTheoMa(maSP).get(0).getTenSanPham());
                        Double donGia = sp.loadSPTheoMa(maSP).get(0).getDonGia();
                        int mucKhuyenMai = cTKM.loadSPTheoMa(maSP).get(0).getMucKhuyenMai();

                        txtTenSP.setText(tenSP);
                        txtDonGia.setText(String.valueOf(donGia));
                        txtGiamGia.setText(String.valueOf(mucKhuyenMai));
                    }catch (Exception el){
                        try{
                            String tenSP = String.valueOf(sp.loadSPTheoMa(maSP).get(0).getTenSanPham());
                            Double donGia = sp.loadSPTheoMa(maSP).get(0).getDonGia();
                            int mucKhuyenMai = 0;

                            txtTenSP.setText(tenSP);
                            txtDonGia.setText(String.valueOf(donGia));
                            txtGiamGia.setText(String.valueOf(mucKhuyenMai));
                        }catch (Exception e1){
                            Alert alert = new Alert(Alert.AlertType.INFORMATION);
                            alert.setTitle("Thông báo");
                            alert.setHeaderText("Không tìm thấy sản phẩm");
                            //alert.setContentText("");
                            Optional<ButtonType> BT=alert.showAndWait();
                        }
                    }
                }else {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Thông báo");
                    alert.setHeaderText("Không tìm thấy sản phẩm");
                    //alert.setContentText("");
                    Optional<ButtonType> BT=alert.showAndWait();
                }
            }
        });
    }

    public void timTenSanPham(){
        txtTenSP.setOnKeyPressed((KeyEvent e) -> {
            if(e.getCode() == KeyCode.ENTER){
                String tenSP = txtTenSP.getText();
                if(!tenSP.isEmpty()) {
                    try {
                        String maSP = String.valueOf(sp.loadSPTheoTen(tenSP).get(0).getMaSanPham());
                        Double donGia = sp.loadSPTheoTen(tenSP).get(0).getDonGia();
                        int mucKhuyenMai = cTKM.loadSPTheoMa(maSP).get(0).getMucKhuyenMai();

                        txtTimSP.setText(maSP);
                        txtDonGia.setText(String.valueOf(donGia));
                        txtGiamGia.setText(String.valueOf(mucKhuyenMai));
                    }catch (Exception el){
                        try{
                            String maSP = String.valueOf(sp.loadSPTheoTen(tenSP).get(0).getMaSanPham());
                            Double donGia = sp.loadSPTheoTen(tenSP).get(0).getDonGia();
                            int mucKhuyenMai = 0;

                            txtTimSP.setText(maSP);
                            txtDonGia.setText(String.valueOf(donGia));
                            txtGiamGia.setText(String.valueOf(mucKhuyenMai));
                        }catch (Exception e1){
                            Alert alert = new Alert(Alert.AlertType.INFORMATION);
                            alert.setTitle("Thông báo");
                            alert.setHeaderText("Không tìm thấy sản phẩm");
                            //alert.setContentText("");
                            Optional<ButtonType> BT=alert.showAndWait();
                        }
                    }
                }else {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Thông báo");
                    alert.setHeaderText("Không tìm thấy sản phẩm");
                    //alert.setContentText("");
                    Optional<ButtonType> BT=alert.showAndWait();
                }
            }
        });
    }

    // hàm tìm kiếm khách hàng load dữ liệu vào textfield
    @FXML
    public void searchKhachHang(ActionEvent event) {
        String SDT = txtTim.getText();
        try {
            String maKH = khachHangDao.searchSDT(SDT).get(0).getMaKhachHang();
            String tenKH = khachHangDao.searchSDT(SDT).get(0).getTenKhachHang();

            txtMaKhachHang.setText(maKH);
            txtTenKhachHang.setText(tenKH);
            System.out.println(maKH);
            txtError.setText("");
        }catch (Exception e){
            txtMaKhachHang.setText("");
            txtTenKhachHang.setText("");
            txtError.setText("Không tìm thấy khách hàng!");
        }
    }

    // hàm load dữ liệu vào từng cột của tableview
    public void setCellTable(){
        columnMaSP.setCellValueFactory(new PropertyValueFactory<>("maSanPham"));
        columnSoLuong.setCellValueFactory(new PropertyValueFactory<>("soLuong"));
        columnDonGia.setCellValueFactory(new PropertyValueFactory<>("giaBan"));
        columnTenSP.setCellValueFactory(new PropertyValueFactory<>("tenSanPham"));
        columnGiamGia.setCellValueFactory(new PropertyValueFactory<>("giamGia"));
        columnThanhTien.setCellValueFactory(new PropertyValueFactory<>("thanhTien"));
    }

    void clearTextChiTiet(){
        txtTimSP.clear();
        txtTenSP.clear();
        txtGiamGia.clear();
        txtDonGia.clear();
        txtThanhTien.clear();
    }

    void clearTextHoaDon(){
        txtTim.clear();
        txtMaKhachHang.clear();
        txtTenKhachHang.clear();
        txtDiaChi.clear();
    }

    // hàm set gia trị của chi tiết đơn hàng
    @FXML
    void addChiTiet(ActionEvent event) {
        tinhTien();
        try {
            chiTietDonHang = new ChiTietDonHang();
            chiTietDonHang.setMaSanPham(txtTimSP.getText());
            chiTietDonHang.setSoLuong(Integer.parseInt(String.valueOf(cbbSoLuong.getSelectionModel().getSelectedItem())));
            chiTietDonHang.setGiaBan(Double.parseDouble(txtDonGia.getText()));
            chiTietDonHang.setTenSanPham(txtTenSP.getText());
            chiTietDonHang.setGiamGia(Integer.parseInt(txtGiamGia.getText()));
            chiTietDonHang.setThanhTien(Double.parseDouble(txtThanhTien.getText()));

            if (data.isEmpty()) {
                data.add(chiTietDonHang);
                tableChiTietDH.setItems(data);
                tongTien();
                clearTextChiTiet();
                cbbSoLuong.getSelectionModel().select(0);
            } else if (!data.isEmpty()) {
                int i = -1;
                boolean flag = false;
                for (ChiTietDonHang ct : data) {
                    i++;
                    if (data.get(i).getMaSanPham().equalsIgnoreCase(txtTimSP.getText())) {
                        flag = true;
                        break;
                    }
                }
                if (flag == true) {
                    int soLuong = data.get(i).getSoLuong() + Integer.parseInt(String.valueOf(cbbSoLuong.getSelectionModel().getSelectedItem()));
                    Double giaBan = Double.parseDouble(txtDonGia.getText());
                    int giamGia = Integer.parseInt(txtGiamGia.getText());
                    Double thanhTien = (soLuong * giaBan) * (1 - giamGia * 0.01);

                    txtThanhTien.setText(String.valueOf(thanhTien));
                    chiTietDonHang.setSoLuong(soLuong);
                    chiTietDonHang.setThanhTien(thanhTien);

                    data.set(i, chiTietDonHang);
                    tableChiTietDH.setItems(data);
                    tongTien();
                    clearTextChiTiet();
                    cbbSoLuong.getSelectionModel().select(0);
                } else {
                    data.add(chiTietDonHang);
                    tableChiTietDH.setItems(data);
                    tongTien();
                    clearTextChiTiet();
                    cbbSoLuong.getSelectionModel().select(0);
                }
            }
            if(!data.isEmpty()){
                btnLuu.setDisable(false);
            }
        }catch (Exception e){

        }
    }

    //hàm tính tiền
    public void tinhTien(){
        try {
            int soLuong = Integer.parseInt(String.valueOf(cbbSoLuong.getSelectionModel().getSelectedItem()));
            Double giaBan = Double.parseDouble(txtDonGia.getText());
            int giamGia = Integer.parseInt(txtGiamGia.getText());
            txtThanhTien.setText(String.valueOf((soLuong*giaBan)*(1 - giamGia*0.01)));
        }catch (Exception e){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Lỗi");
            alert.setHeaderText("Vui lòng chọn sản phẩm");
            //alert.setContentText("");
            Optional<ButtonType> BT=alert.showAndWait();
        }
    }

    //hàm tính tổng tiền
    public void tongTien(){
        int i = -1;
        double tong = 0;
        for(ChiTietDonHang ct:data){
            i++;
            tong += data.get(i).getThanhTien();
        }
        txtTongTien.setText(String.valueOf(tong));
    }

    // load dữ liệu vào textfield
    @FXML
    public void loadSanPhamCombobox(ActionEvent event) {
//        try {
//            String maSP = String.valueOf(cbbMaSP.getSelectionModel().getSelectedItem());
//
//            String tenSP = String.valueOf(sp.loadSPTheoMa(maSP).get(0).getTenSanPham());
//            Double donGia = sp.loadSPTheoMa(maSP).get(0).getDonGia();
//            int mucKhuyenMai = cTKM.loadSPTheoMa(maSP).get(0).getMucKhuyenMai();
//
//            txtTenSP.setText(tenSP);
//            txtDonGia.setText(String.valueOf(donGia));
//            txtGiamGia.setText(String.valueOf(mucKhuyenMai));
//        }catch (Exception e){
//            String maSP = String.valueOf(cbbMaSP.getSelectionModel().getSelectedItem());
//
//            String tenSP = String.valueOf(sp.loadSPTheoMa(maSP).get(0).getTenSanPham());
//            Double donGia = sp.loadSPTheoMa(maSP).get(0).getDonGia();
//            int mucKhuyenMai = 0;
//
//            txtTenSP.setText(tenSP);
//            txtDonGia.setText(String.valueOf(donGia));
//            txtGiamGia.setText(String.valueOf(mucKhuyenMai));
//        }
    }

    // hàm xóa 1 chi tiết
    @FXML
    void deleteChiTiet(ActionEvent event) {
        tableChiTietDH.getItems().removeAll(tableChiTietDH.getSelectionModel().getSelectedItem());
        tongTien();
        if(data.isEmpty()){
            btnXoa.setDisable(true);
            btnLuu.setDisable(true);
        }
    }

    //hàm lưu đơn hàng và chi tiết
    @FXML
    void saveHoaDon(ActionEvent event) {
        try {
            if(!txtMaKhachHang.getText().isEmpty()){

                if(data.isEmpty()){
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Lỗi");
                    alert.setHeaderText("Vui lòng chọn sản phẩm");
                    //alert.setContentText("");
                    Optional<ButtonType> BT=alert.showAndWait();
                }else {
                    Alert alert=new Alert(Alert.AlertType.CONFIRMATION);
                    alert.setTitle("Xác nhận");
                    alert.setHeaderText("Bạn có chắc chắn muốn lưu thông tin không?");
                    Optional<ButtonType>BT=alert.showAndWait();
                    if(BT.get() == ButtonType.OK) {
                        donHangDao.create(txtMaKhachHang.getText(),txtMaNhanVien.getText(),txtDiaChi.getText(),txtNgayDat.getText(),"Đã thanh toán");
                        boolean flag = false;
                        int i = -1;
                        maDonHang = String.valueOf(donHangDao.loadMaDonHang());
                        for(ChiTietDonHang ct:data){
                            i++;
                            String maSP = data.get(i).getMaSanPham();
                            int soLuong = data.get(i).getSoLuong();
                            Double giaBan = data.get(i).getGiaBan();
                            ctdh.create(maSP,maDonHang,soLuong,giaBan);
                            flag = true;
                        }
                        if(flag == true){
                            Alert alert1 = new Alert(Alert.AlertType.INFORMATION);
                            alert.setTitle("Thông báo");
                            alert.setHeaderText("Lưu thành công");
                            //alert.setContentText("");
                            Optional<ButtonType> BT1=alert.showAndWait();
                            data.clear();
                            clearTextHoaDon();
                            txtTongTien.clear();
                        }
                    }
                }
            }else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Lỗi");
                alert.setHeaderText("Vui lòng nhập đầy đủ thông tin hóa đơn");
                //alert.setContentText("");
                Optional<ButtonType> BT=alert.showAndWait();
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    //hàm cập nhật chi tiết
    @FXML
    void updateChiTiet(ActionEvent event) {
//        tinhTien();
//        chiTietDonHang  = new ChiTietDonHang();
//        chiTietDonHang.setMaSanPham(String.valueOf(Integer.parseInt(String.valueOf(cbbMaSP.getSelectionModel().getSelectedItem()))));
//        chiTietDonHang.setSoLuong(Integer.parseInt(String.valueOf(cbbSoLuong.getSelectionModel().getSelectedItem())));
//        chiTietDonHang.setGiaBan(Double.parseDouble(txtDonGia.getText()));
//        chiTietDonHang.setTenSP(txtTenSP.getText());
//        chiTietDonHang.setGiamGia(Integer.parseInt(txtGiamGia.getText()));
//        chiTietDonHang.setThanhTien(Double.parseDouble(txtThanhTien.getText()));
//
//        data.set(row,chiTietDonHang);
//        tableChiTietDH.setItems(data);
//        tongTien();
    }

    // hàm nạp data vào textfield khi click tableview
    @FXML
    public void tableClick(MouseEvent e) {
       try {
           if (MouseButton.PRIMARY == e.getButton()) {
               row = tableChiTietDH.getSelectionModel().getSelectedIndex();
//               chiTietDonHang = tableChiTietDH.getSelectionModel().getSelectedItem();
//               txtThanhTien.setText(String.valueOf(chiTietDonHang.getThanhTien()));
//               txtTenSP.setText(chiTietDonHang.getTenSanPham());
//               txtGiamGia.setText(String.valueOf(chiTietDonHang.getGiamGia()));
//               txtDonGia.setText(String.valueOf(chiTietDonHang.getGiaBan()));
               btnXoa.setDisable(false);
           }
       }catch (Exception e1){

       }
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
}
