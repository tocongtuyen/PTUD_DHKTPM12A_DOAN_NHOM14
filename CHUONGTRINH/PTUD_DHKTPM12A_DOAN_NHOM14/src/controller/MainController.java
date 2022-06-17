package controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import dao.NhanVienDao;
import entity.NhanVien;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

import static java.lang.System.exit;

public class MainController extends Application implements Initializable {

    @FXML
    private Label lblMaNV;

    @FXML
    private Label lblTenNV;

    @FXML
    private Label lblChucVu;

    @FXML
    private JFXButton btnQLKH;

    @FXML
    private JFXButton btnQLSP;

    @FXML
    private JFXButton btnQLNCC;

    @FXML
    private JFXButton btnQLDH;

    @FXML
    private JFXButton btnQLKM;

    @FXML
    private JFXButton btnLDH;

    @FXML
    private JFXButton btnDangNhap;

    @FXML
    private JFXButton btnQLNV;

    @FXML
    private JFXButton btnThongKe;

    @FXML
    private JFXButton btnThoat;

    NhanVienDao nhanVienDao = new NhanVienDao();
    ObservableList<NhanVien> listNV;

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("/ui/MainGUI.fxml"));
        primaryStage.setTitle("Quản lý mua bán linh kiện");
        primaryStage.setScene(new Scene(root));
        primaryStage.setResizable(false);
        primaryStage.getIcons().add(new Image("/img/shop-icon.png"));
        // xử lý khi nhấn vào close(X)
        primaryStage.setOnCloseRequest(new EventHandler<WindowEvent>() {

            @Override
            public void handle(WindowEvent t) {
                Alert alertDN=new Alert(Alert.AlertType.CONFIRMATION);// button thong tin
                alertDN.setTitle("Xác nhận");
                alertDN.setHeaderText("Bạn có chắc chắn muốn thoát khỏi ứng dụng không ? ");
                Optional<ButtonType> optionDN= alertDN.showAndWait();//
                t.consume();// khoá nút tắt
                // thoát khi chon ok
                if(optionDN.get()==ButtonType.OK){
                    exit(0);
                }
            }

        });
        primaryStage.hide();
        primaryStage.show();

    }

    public static void main(String[] args) {
        launch(args);
    }
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        khoaControl(true);
        btnDangNhap.setDisable(false );
    }

    public void setTenTK(String maNV, String tenNV, String chucVu){
        lblMaNV.setText(maNV);
        lblTenNV.setText(tenNV);
        lblChucVu.setText(chucVu);
        if(lblChucVu.getText().equalsIgnoreCase("Quản lý")) {
            khoaControl(false);
            btnDangNhap.setDisable(true);
        }
        if(lblChucVu.getText().equalsIgnoreCase("Bán hàng")){
            khoaControl(false);
            btnDangNhap.setDisable(true);
            btnQLNV.setDisable(true);
            btnQLKM.setDisable(true);
            btnThongKe.setDisable(true);
            btnDangNhap.setDisable(true);
        }
    }

    public void khoaControl(boolean b){
        btnLDH.setDisable(b);
        btnDangNhap.setDisable(b);
        btnQLDH.setDisable(b);
        btnQLKH.setDisable(b);
        btnQLNCC.setDisable(b);
        btnQLSP.setDisable(b);
        btnQLNV.setDisable(b);
        btnQLKM.setDisable(b);
        btnThongKe.setDisable(b);
        btnThoat.setDisable(b);
    }

    @FXML
    void thoat(ActionEvent event) throws Exception {
        windows("/ui/DangNhapGUI.fxml", "Đăng nhập", event);
    }

    @FXML
    private void quanLyNhanVien(ActionEvent event) throws Exception {
        Stage stage = (Stage)((Node) event.getSource()).getScene().getWindow();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/ui/QuanLyNhanVienGUI.fxml"));
        Parent parent = loader.load();
        Scene scene = new Scene(parent);
        stage.setTitle("Quản lý nhân viên");
        NhanVienController controller = loader.getController();
        controller.setTaiKhoan(lblMaNV.getText());
        stage.setScene(scene);
    }

    @FXML
    private void lapDonHang(ActionEvent event) throws Exception {
        Stage stage = (Stage)((Node) event.getSource()).getScene().getWindow();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/ui/NhapDonHangGUI.fxml"));
        Parent parent = loader.load();
        Scene scene = new Scene(parent);
        stage.setTitle("Lập đơn hàng");
        DonHangController controller = loader.getController();
        controller.setTaiKhoan(lblMaNV.getText(),lblTenNV.getText());
        stage.setScene(scene);
    }

    @FXML
    private void quanLySanPham(ActionEvent event) throws Exception {
        Stage stage = (Stage)((Node) event.getSource()).getScene().getWindow();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/ui/QuanLySanPhamGUI.fxml"));
        Parent parent = loader.load();
        Scene scene = new Scene(parent);
        stage.setTitle("Quản lý sản phẩm");
        SanPhamController controller = loader.getController();
        controller.setTaiKhoan(lblMaNV.getText());
        stage.setScene(scene);
    }

    @FXML
    void quanLyKhuyenMai(ActionEvent event) throws Exception {
        Stage stage = (Stage)((Node) event.getSource()).getScene().getWindow();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/ui/QuanLyKhuyenMaiGUI.fxml"));
        Parent parent = loader.load();
        Scene scene = new Scene(parent);
        stage.setTitle("Quản lý khuyến mãi");
        KhuyenMaiController controller = loader.getController();
        controller.setTaiKhoan(lblMaNV.getText());
        stage.setScene(scene);
    }

    @FXML
    private void quanLyDonHang(ActionEvent event) throws Exception {
//        windows("/ui/QuanLyDonHangGUI.fxml", "Đơn hàng", event);
        Stage stage = (Stage)((Node) event.getSource()).getScene().getWindow();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/ui/QuanLyDonHangGUI.fxml"));
        Parent parent = loader.load();
        Scene scene = new Scene(parent);
        stage.setTitle("Quản lý đơn hàng");
        QLDonHangController controller = loader.getController();
        controller.setTaiKhoan(lblMaNV.getText());
        stage.setScene(scene);
    }

    //hàm chạy giao diện quản lý khách hàng
    @FXML
    private void quanLyKhachHang(ActionEvent event) throws Exception {
        Stage stage = (Stage)((Node) event.getSource()).getScene().getWindow();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/ui/QuanLyKhachHangGUI.fxml"));
        Parent parent = loader.load();
        Scene scene = new Scene(parent);
        stage.setTitle("Quản lý khách hàng");
        KhachHangController controller = loader.getController();
        controller.setTaiKhoan(lblMaNV.getText());
        stage.setScene(scene);
    }

    //hàm chạy giao diện quản lý nhà cung cấp
    @FXML
    private void quanLyNhaCungCap(ActionEvent event) throws Exception {
        Stage stage = (Stage)((Node) event.getSource()).getScene().getWindow();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/ui/QuanLyNhaCungCapGUI.fxml"));
        Parent parent = loader.load();
        Scene scene = new Scene(parent);
        stage.setTitle("Nhà cung cấp");
        NhaCungCapController controller = loader.getController();
        controller.setTaiKhoan(lblMaNV.getText());
        stage.setScene(scene);
    }

    @FXML
    private void dangNhap(ActionEvent event) throws Exception {
        windows("/ui/DangNhapGUI.fxml", "Đăng nhập", event);
    }

    @FXML
    void thongKe(ActionEvent event) throws Exception {
        Stage stage = (Stage)((Node) event.getSource()).getScene().getWindow();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/ui/ThongKeGUI.fxml"));
        Parent parent = loader.load();
        Scene scene = new Scene(parent);
        stage.setTitle("Thống kê");
        ThongKeController controller = loader.getController();
        controller.setTaiKhoan(lblMaNV.getText());
        stage.setScene(scene);
    }

    public void windows(String path, String title, ActionEvent event) throws Exception {

        double width = ((Node) event.getSource()).getScene().getWidth();
        double height = ((Node) event.getSource()).getScene().getHeight();

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource(path));
        Parent parent = loader.load();

        Scene scene = new Scene(parent);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setTitle(title);
        stage.setResizable(false);
        stage.hide();

//        stage.getIcons().add(new Image("/img/shop-icon.png"));
        stage.setScene(scene);
        stage.show();
    }
}