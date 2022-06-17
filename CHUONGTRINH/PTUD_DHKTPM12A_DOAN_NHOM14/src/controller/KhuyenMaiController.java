package controller;

import dao.NhanVienDao;
import entity.NhanVien;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class KhuyenMaiController {

    ObservableList<NhanVien> listNV;
    NhanVienDao nhanVienDao;

    @FXML
    private Label lblTaiKhoan;

    @FXML
    public void setTaiKhoan(String maNV){
        nhanVienDao = new NhanVienDao();
        listNV = nhanVienDao.loadNhanVien(maNV);
        lblTaiKhoan.setText(listNV.get(0).getTaiKhoan());
    }

    @FXML
    public void backMain(ActionEvent event) throws Exception{
        Stage stage = (Stage)((Node) event.getSource()).getScene().getWindow();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/ui/MainGUI.fxml"));
        Parent parent = loader.load();
        Scene scene = new Scene(parent);
        MainController controller = loader.getController();
        controller.setTenTK(listNV.get(0).getMaNhanVien(),listNV.get(0).getTenNhanVien(),listNV.get(0).getChucVu());
        stage.setScene(scene);
    }
}
