package controller;

import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import dao.NhanVienDao;
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
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.stage.Stage;

import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

public class DangNhapController implements Initializable {
    private String taiKhoan;
    private String matKhau;

    public DangNhapController() {
    }

    public String getTaiKhoan() {
        return taiKhoan;
    }

    public void setTaiKhoan(String taiKhoan) {
        this.taiKhoan = taiKhoan;
    }

    public String getMatKhau() {
        return matKhau;
    }

    public void setMatKhau(String matKhau) {
        this.matKhau = matKhau;
    }

    @FXML
    private JFXTextField txtTaiKhoan;

    @FXML
    private JFXPasswordField pwfMatKhau;

    NhanVienDao nhanVienDao = new NhanVienDao();
    ObservableList<NhanVien> listNV;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
    }

    @FXML
    public void setTextNhanVien(ActionEvent event) throws Exception {
        try {
            listNV = FXCollections.observableArrayList();
            taiKhoan = txtTaiKhoan.getText();
            matKhau = pwfMatKhau.getText();

            listNV = nhanVienDao.loadNhanVien(taiKhoan,matKhau);

            setTaiKhoan(txtTaiKhoan.getText());
            setMatKhau(pwfMatKhau.getText());

            Stage stage = (Stage)((Node) event.getSource()).getScene().getWindow();
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/ui/MainGUI.fxml"));
            Parent parent = loader.load();
            Scene scene = new Scene(parent);
            stage.setTitle("Quản lý mua bán linh kiện");
            MainController controller = loader.getController();
            controller.setTenTK(listNV.get(0).getMaNhanVien(),listNV.get(0).getTenNhanVien(),listNV.get(0).getChucVu());
            stage.hide();
            stage.setScene(scene);
            stage.show();
        }catch (Exception e){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Thông báo");
            alert.setHeaderText("Lỗi đăng nhập!");
            //alert.setContentText("Are you sure?");
            Optional<ButtonType> BT=alert.showAndWait();
        }
    }

    @FXML
    void thoat(ActionEvent event) throws Exception {
        windows("/ui/MainGUI.fxml", "Quản lý mua bán linh kiện", event);
    }

    public void windows(String path, String title, ActionEvent event) throws Exception {
        double width = ((Node) event.getSource()).getScene().getWidth();
        double height = ((Node) event.getSource()).getScene().getHeight();
        Parent root = FXMLLoader.load(getClass().getResource(path));
        Scene scene = new Scene(root);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setTitle(title);
        stage.hide();
//        stage.getIcons().add(new Image("/images/logo.png"));
        stage.setScene(scene);
        stage.show();
    }
}