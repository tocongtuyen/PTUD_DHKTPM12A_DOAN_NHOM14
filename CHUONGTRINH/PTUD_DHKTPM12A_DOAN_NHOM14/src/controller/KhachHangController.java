//package controller;
//
//import entity.KhachHang;
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
//import javafx.stage.Stage;
//
//import java.net.URL;
//import java.sql.Connection;
//import java.sql.PreparedStatement;
//import java.sql.ResultSet;
//import java.sql.SQLException;
//import java.util.ResourceBundle;
//
//public class KhachHangController implements Initializable {
//
//    @FXML
//    private TextField txtMaKhachHang;
//
//    @FXML
//    private TextField txtTenKhachHang;
//
//    @FXML
//    private TextField txtSDT;
//
//    @FXML
//    private TextField txtEmail;
//
//    @FXML
//    private TextArea txtDiaChi;
//
//    private Connection con = null;
//    private PreparedStatement pst = null;
//    private ResultSet rs = null;
//    private ObservableList<KhachHang> data;
//
//    @FXML
//    private TableView<KhachHang> tableKhachHang;
//
//    @FXML
//    private TableColumn<?, ?> columnMaKhachHang;
//
//    @FXML
//    private TableColumn<?, ?> columnTenKhachHang;
//
//    @FXML
//    private TableColumn<?, ?> columnSDT;
//
//    @FXML
//    private TableColumn<?, ?> columnEmail;
//
//    @FXML
//    private TableColumn<?, ?> columnDiaChi;
//
//    @FXML
//    private Button btnThem;
//
//    @FXML
//    private TextField txtTimKiem;
//
//    @FXML
//    private Button btnSua;
//
//    @FXML
//    private Button btnXoa;
//
//    private void setCellTable(){
//        columnMaKhachHang.setCellValueFactory(new PropertyValueFactory<>("maKhachHang"));
//        columnTenKhachHang.setCellValueFactory(new PropertyValueFactory<>("tenKhachHang"));
//        columnSDT.setCellValueFactory(new PropertyValueFactory<>("soDienThoai"));
//        columnEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
//        columnDiaChi.setCellValueFactory(new PropertyValueFactory<>("diaChi"));
//    }
//    //ham load database vao table
//    private void loadDataFromDatabase(){
////        data.clear();//xoa bang cu
//        try {
//            pst = con.prepareStatement("Select * from KhachHang");
//            rs = pst.executeQuery();
//            while (rs.next()){
//                data.add(new KhachHang(""+rs.getInt(1), rs.getString(2), rs.getString(3),rs.getString(4),rs.getString(5)));
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        tableKhachHang.setItems(data);
//    }
//
//    @FXML
//    void addKhachHang(ActionEvent event) {
//
//    }
//
//    @FXML
//    void deleteKhachHang(ActionEvent event) {
//
//    }
//
//    @FXML
//    void updateKhachHang(ActionEvent event) {
//
//    }
//
//    @Override
//    public void initialize(URL location, ResourceBundle resources) {
//        con = dao.DBConnection.getConnection();
//        data = FXCollections.observableArrayList();
//        setCellTable();
//        loadDataFromDatabase();
//    }
//
//    @FXML
//    public void backMain(ActionEvent event) throws Exception{
//        windows("/ui/MainGUI.fxml", "Giao diện chính", event);
//    }
//
//    public void windows(String path, String title, ActionEvent event) throws Exception {
//        double width = ((Node) event.getSource()).getScene().getWidth();
//        double height = ((Node) event.getSource()).getScene().getHeight();
//        Parent root = FXMLLoader.load(getClass().getResource(path));
//        Scene scene = new Scene(root, width, height);
//        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
//        stage.setTitle(title);
////        stage.getIcons().add(new Image("/images/logo.png"));
//        stage.setScene(scene);
//        stage.show();
//    }
//}

package controller;

import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import dao.KhachHangDao;
import dao.NhanVienDao;
import entity.KhachHang;
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
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

public class KhachHangController implements Initializable {

    ObservableList<NhanVien> listNV;
    NhanVienDao nhanVienDao;
    private ObservableList<KhachHang> data;
    KhachHangDao listKH;

    @FXML
    private Label lblTaiKhoan;

    @FXML
    private TableView<KhachHang> tableKhachHang;

    @FXML
    private TableColumn<?, ?> columnMaKhachHang;

    @FXML
    private TableColumn<?, ?> columnTenKhachHang;

    @FXML
    private TableColumn<?, ?> columnSDT;

    @FXML
    private TableColumn<?, ?> columnEmail;

    @FXML
    private TableColumn<?, ?> columnDiaChi;

    @FXML
    private JFXTextField txtMaKH;

    @FXML
    private JFXTextField txtTenKH;

    @FXML
    private JFXTextField txtSDT;

    @FXML
    private JFXTextField txtEmail;

    @FXML
    private JFXTextArea txtDiaChi;

    @FXML
    private JFXTextField txtTimKiem;

    @FXML
    private Button btnThem;

    @FXML
    private ImageView imgThem;

    @FXML
    private Button btnLuu;

    @FXML
    private Button btnSua;

    @FXML
    private ImageView imgSua;

    Image img;

    @FXML
    private Label errorTen;

    @FXML
    private Label errorSDT;

    @FXML
    private Label errorEmail;

    @FXML
    private Label errorDiaChi;

    @FXML
    private Button btnXoa;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        listKH = new KhachHangDao();
        data = FXCollections.observableArrayList();
        setCellTable();
        loadKhachHang();
        setTextToField();
        search();
    }

    @FXML
    public void setTaiKhoan(String maNV){
        nhanVienDao = new NhanVienDao();
        listNV = nhanVienDao.loadNhanVien(maNV);
        lblTaiKhoan.setText(listNV.get(0).getTaiKhoan());
    }

    //ham nap du lieu vao cao cot
    public void setCellTable(){
        columnMaKhachHang.setCellValueFactory(new PropertyValueFactory<>("maKhachHang"));
        columnTenKhachHang.setCellValueFactory(new PropertyValueFactory<>("tenKhachHang"));
        columnSDT.setCellValueFactory(new PropertyValueFactory<>("soDienThoai"));
        columnEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
        columnDiaChi.setCellValueFactory(new PropertyValueFactory<>("diaChi"));
    }

    public void loadKhachHang(){
        data.clear();//xoa bang cu
        data = listKH.loadData();
        tableKhachHang.setItems(data);
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

    public void khoaTextFields(boolean b){
        txtTenKH.setDisable(b);
        txtSDT.setDisable(b);
        txtEmail.setDisable(b);
        txtDiaChi.setDisable(b);
    }

    public void khoaControl(boolean b){
        btnThem.setDisable(b);
        btnLuu.setDisable(b);
        btnSua.setDisable(b);
        btnXoa.setDisable(b);
    }

    //ham xoa rong textfield
    public void clearTextField(){
        txtMaKH.clear();
        txtTenKH.clear();
        txtSDT.clear();
        txtEmail.clear();
        txtDiaChi.clear();
    }

    @FXML
    void addKhachHang(ActionEvent event) {
        if (btnThem.getText().equalsIgnoreCase("Thêm")){
            khoaTextFields(false);
            khoaControl(true);
            btnLuu.setDisable(false);
            btnThem.setDisable(false);
            btnThem.setText("Hủy");
            img = new Image("img/cancel.png");
            imgThem.setImage(img);
            clearTextField();
            txtMaKH.setText(String.valueOf(listKH.loadMaKhachHang()+130));
        } else {
            errorTen.setText("");
            errorSDT.setText("");
            errorEmail.setText("");
            errorDiaChi.setText("");
            clearTextField();
            khoaTextFields(true);
            khoaControl(true);
            btnThem.setDisable(false);
            btnThem.setText("Thêm");
            img = new Image("img/add.png");
            imgThem.setImage(img);
        }
    }

    @FXML
    void saveKhachHang(ActionEvent event) {
        boolean isTen = validation.TextFieldValidation.errorTen(txtTenKH,errorTen,"Không chứ ký tự số");
        boolean isSDT = validation.TextFieldValidation.errorSoDienThoai(txtSDT,errorSDT,"10-11 chữ số");
        boolean isEmail = validation.TextFieldValidation.errorEmail(txtEmail,errorEmail,"Email không hợp lệ! VD: nguyenvana@gmail.com");
        boolean isDiaChi = validation.TextFieldValidation.isTextFieldNotEmpty(txtDiaChi,errorDiaChi,"Không được bỏ trống");
        if (btnThem.getText().equalsIgnoreCase("Hủy")){
            if (isTen && isSDT && isEmail && isDiaChi) {

                Alert alert=new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Xác nhận");
                alert.setHeaderText("Bạn có chắc chắn muốn lưu thông tin không?");
                Optional<ButtonType>BT=alert.showAndWait();
                if(BT.get() == ButtonType.OK){
                    listKH.create(txtTenKH.getText(),txtSDT.getText(),txtEmail.getText(),txtDiaChi.getText());
                    setCellTable();
                    loadKhachHang();

                    khoaTextFields(true);
                    khoaControl(true);
                    btnThem.setDisable(false);
                    btnThem.setText("Thêm");
                    img = new Image("img/add.png");
                    imgThem.setImage(img);
                }

            }
        }else if (btnSua.getText().equalsIgnoreCase("Hủy")){
            if (isTen && isSDT && isEmail && isDiaChi) {

                Alert alert=new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Xác nhận");
                alert.setHeaderText("Bạn có chắc chắn muốn lưu thông tin không?");
                Optional<ButtonType>BT=alert.showAndWait();
                if(BT.get() == ButtonType.OK){
                    listKH.update(txtMaKH.getText(),txtTenKH.getText(),txtSDT.getText(),txtEmail.getText(),txtDiaChi.getText());
                    loadKhachHang();

                    khoaTextFields(true);
                    khoaControl(true);
                    btnThem.setDisable(false);
                    btnSua.setText("Sửa");
                    img = new Image("img/update.png");
                    imgSua.setImage(img);
                }

            }
        }
    }

    //ham lay gia tri tu table nap vao textfield
    public void setTextToField(){
        tableKhachHang.setOnMouseClicked(e -> {
            int i = tableKhachHang.getSelectionModel().getSelectedIndex();
            if(i >= 0 && !(btnThem.getText().equalsIgnoreCase("Hủy")) && !(btnSua.getText().equalsIgnoreCase("Hủy"))){
                btnSua.setDisable(false);
                btnXoa.setDisable(false);
                KhachHang p = tableKhachHang.getItems().get(tableKhachHang.getSelectionModel().getSelectedIndex());
                txtMaKH.setText(p.getMaKhachHang());
                txtTenKH.setText(p.getTenKhachHang());
                txtSDT.setText(p.getSoDienThoai());
                txtEmail.setText(p.getEmail());
                txtDiaChi.setText(p.getDiaChi());
            }
        });
    }

    @FXML
    void updateKhachHang(ActionEvent event) {
        if (btnSua.getText().equalsIgnoreCase("Sửa")) {
            khoaTextFields(false);
            khoaControl(true);
            btnLuu.setDisable(false);
            btnSua.setDisable(false);
            btnSua.setText("Hủy");
            img = new Image("img/cancel.png");
            imgSua.setImage(img);
        } else if (btnSua.getText().equalsIgnoreCase("Hủy")) {
            errorTen.setText("");
            errorSDT.setText("");
            errorDiaChi.setText("");
            clearTextField();
            khoaTextFields(true);
            khoaControl(true);
            btnThem.setDisable(false);
            setTextToField();
            btnSua.setText("Sửa");
            img = new Image("img/update.png");
            imgSua.setImage(img);
        }
    }

    //ham xoa khach hang
    @FXML
    void deleteKhachHang(ActionEvent event) {
        Alert alert=new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Xác nhận");
        alert.setHeaderText("Bạn có chắc chắn muốn xóa thông tin không?");
        Optional<ButtonType>BT=alert.showAndWait();
        if(BT.get() == ButtonType.OK){
            listKH.delete(txtMaKH.getText());
            loadKhachHang();
            clearTextField();
            btnXoa.setDisable(true);
            btnSua.setDisable(true);
        }
    }

    public void search(){
        txtTimKiem.setOnKeyReleased(e->{
            if(txtTimKiem.getText().equals("")){
                loadKhachHang();
            }
            else {
                data.clear();//xóa bảng
                data = listKH.search(txtTimKiem.getText());//conllection
                tableKhachHang.setItems(data);
            }
        });
    }
}
