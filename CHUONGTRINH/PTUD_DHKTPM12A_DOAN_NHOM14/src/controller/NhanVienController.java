package controller;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextArea;
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
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

public class NhanVienController implements Initializable {

    @FXML
    private Label lblTaiKhoan1;

    @FXML
    private Label lblMaNhanVien;

    @FXML
    private Label lblTenNhanVien;

    @FXML
    private Label lblSDT;

    @FXML
    private Label lblNgayVaoLam;

    @FXML
    private Label lblTrangThai;

    @FXML
    private Label lblTaiKhoan;

    @FXML
    private Label lblMatKhau;

    @FXML
    private Label lblDiaChi;

    @FXML
    private Label lblChuVu;

    @FXML
    private Label lblEmail;

    @FXML
    private JFXTextField txtMaNhanVien;

    @FXML
    private JFXTextField txtTenNhanVien;

    @FXML
    private JFXTextField txtSDT;

    @FXML
    private JFXComboBox<String> cbbChucVu;

    @FXML
    private JFXTextField txtEmail;

    @FXML
    private JFXTextField txtTaiKhoan;

    @FXML
    private JFXComboBox<String> cbbTrangThai;

    @FXML
    private JFXTextArea txtDiaChi;

    @FXML
    private JFXTextField txtNgayVaoLam;

    @FXML
    private TableView<NhanVien> tableQLNV;

    @FXML
    private TableColumn<?, ?> columnMaNV;

    @FXML
    private TableColumn<?, ?> columnTenNV;

    @FXML
    private TableColumn<?, ?> columnSDT;

    @FXML
    private TableColumn<?, ?> columnEmail;

    @FXML
    private TableColumn<?, ?> columnChucVu;

    @FXML
    private TableColumn<?, ?> columnNgayVaoLam;

    @FXML
    private TableColumn<?, ?> columnTrangThai;

    @FXML
    private TableColumn<?, ?> columnDiaChi;

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

    @FXML
    private Button btnXoa;

    @FXML
    private JFXPasswordField pwfMatKhau;

    @FXML
    private Label errorTenNV;

    @FXML
    private Label errorSDT;

    @FXML
    private Label errorEmail;

    @FXML
    private Label errorTaiKhoan;

    @FXML
    private Label errorMatKhau;

    @FXML
    private Label errorDiaChi;


    ObservableList<NhanVien> data;
    NhanVienDao listNV;

    Image img;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        txtNgayVaoLam.setText(String.valueOf(java.time.LocalDate.now()));
        cbbChucVu.getItems().addAll("Bán hàng","Quản lý");
        cbbChucVu.getSelectionModel().select(0);
        cbbTrangThai.getItems().addAll("Đang làm","Đã nghỉ");
        cbbTrangThai.getSelectionModel().select(0);
        listNV = new NhanVienDao();
        data = FXCollections.observableArrayList();
        setCellTable();
        load();
        setTextToField();
        search();
    }

    @FXML
    public void setTaiKhoan(String maNV){
        listNV = new NhanVienDao();
        data = listNV.loadNhanVien(maNV);
        lblTaiKhoan1.setText(data.get(0).getTaiKhoan());
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
        controller.setTenTK(data.get(0).getMaNhanVien(),data.get(0).getTenNhanVien(),data.get(0).getChucVu());
        stage.setScene(scene);
    }

    //ham nap du lieu vao cao cot
    public void setCellTable(){
        columnChucVu.setCellValueFactory(new PropertyValueFactory<>("chucVu"));
        columnDiaChi.setCellValueFactory(new PropertyValueFactory<>("diaChi"));
        columnEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
        columnMaNV.setCellValueFactory(new PropertyValueFactory<>("maNhanVien"));
        columnNgayVaoLam.setCellValueFactory(new PropertyValueFactory<>("ngayVaoLam"));
        columnSDT.setCellValueFactory(new PropertyValueFactory<>("soDT"));
        columnTrangThai.setCellValueFactory(new PropertyValueFactory<>("trangThai"));
        columnTenNV.setCellValueFactory(new PropertyValueFactory<>("tenNhanVien"));
    }

    public void load() {
        data.clear();//xoa bang cu
        data = listNV.loadData();
        tableQLNV.setItems(data);
    }

    public void khoaTextFields(boolean b){
        txtTenNhanVien.setDisable(b);
        txtSDT.setDisable(b);
        cbbChucVu.setDisable(b);
        txtEmail.setDisable(b);
        txtTaiKhoan.setDisable(b);
        pwfMatKhau.setDisable(b);
        cbbTrangThai.setDisable(b);
        txtDiaChi.setDisable(b);
    }

    public void khoaControl(boolean b){
        btnThem.setDisable(b);
        btnLuu.setDisable(b);
        btnSua.setDisable(b);
        btnXoa.setDisable(b);
    }

    //ham xoa rong textfield
    public void clearTextField() {
        txtTenNhanVien.clear();
        txtSDT.clear();
        txtEmail.clear();
        txtTaiKhoan.clear();
        pwfMatKhau.clear();
        txtDiaChi.clear();
    }

    @FXML
    void addNV(ActionEvent event) {
        if (btnThem.getText().equalsIgnoreCase("Thêm")){
            khoaTextFields(false);
            khoaControl(true);
            btnLuu.setDisable(false);
            btnThem.setDisable(false);
            btnThem.setText("Hủy");
            img = new Image("img/cancel.png");
            imgThem.setImage(img);
            clearTextField();
            cbbTrangThai.setDisable(true);
            txtMaNhanVien.setText(String.valueOf(listNV.loadMaNV()+130));
        } else {
            errorDiaChi.setText("");
            errorEmail.setText("");
            errorMatKhau.setText("");
            errorSDT.setText("");
            errorTaiKhoan.setText("");
            errorTenNV.setText("");

            clearTextField();
            khoaTextFields(true);
            khoaControl(true);
            btnThem.setDisable(false);
            btnThem.setText("Thêm");
            img = new Image("img/add.png");
            imgThem.setImage(img);
        }
    }

    //ham lay gia tri tu table nap vao textfield
    public void setTextToField(){
        tableQLNV.setOnMouseClicked(e -> {
            int i = tableQLNV.getSelectionModel().getSelectedIndex();
            if(i >= 0 && !(btnThem.getText().equalsIgnoreCase("Hủy")) && !(btnSua.getText().equalsIgnoreCase("Hủy"))){
                btnSua.setDisable(false);
                btnXoa.setDisable(false);
                NhanVien p = tableQLNV.getItems().get(tableQLNV.getSelectionModel().getSelectedIndex());

                txtMaNhanVien.setText(p.getMaNhanVien());
                txtTenNhanVien.setText(p.getTenNhanVien());
                txtSDT.setText(p.getSoDT());
                txtEmail.setText(p.getEmail());
                txtDiaChi.setText(p.getDiaChi());
                txtTaiKhoan.setText(p.getTaiKhoan());
                pwfMatKhau.setText(p.getMatKhau());
            }
        });
    }

    @FXML
    void deleteNV(ActionEvent event) {
        Alert alert=new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Xác nhận");
        alert.setHeaderText("Bạn có chắc chắn muốn xóa không?");
        Optional<ButtonType>BT=alert.showAndWait();
        if(BT.get() == ButtonType.OK){
            listNV.delete(txtMaNhanVien.getText());
            load();
            clearTextField();
            btnXoa.setDisable(true);
            btnSua.setDisable(true);
        }
    }

    @FXML
    void saveNV(ActionEvent event) {
        boolean isTen = validation.TextFieldValidation.errorTen(txtTenNhanVien,errorTenNV,"Không chứ ký tự số");
        boolean isDiaChi = validation.TextFieldValidation.isTextFieldNotEmpty(txtDiaChi,errorDiaChi,"not null");
        boolean isEmail = validation.TextFieldValidation.isTextFieldNotEmpty(txtEmail,errorEmail,"not null");
        boolean isSDT = validation.TextFieldValidation.isTextFieldNotEmpty(txtSDT,errorSDT,"not null");
        boolean isTaiKhoan = validation.TextFieldValidation.isTextFieldNotEmpty(txtTaiKhoan,errorTaiKhoan,"not null");
        boolean isMatKhau = validation.TextFieldValidation.errorMatKhau(pwfMatKhau,errorMatKhau,"not null");

        if (btnThem.getText().equalsIgnoreCase("Hủy")){
            if (isTen && isDiaChi && isEmail && isSDT && isTaiKhoan && isMatKhau) {
                Alert alert=new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Xác nhận");
                alert.setHeaderText("Bạn có chắc chắn muốn lưu thông tin không?");
                Optional<ButtonType>BT=alert.showAndWait();
                if(BT.get() == ButtonType.OK){
                    listNV.create(txtTenNhanVien.getText(),txtSDT.getText(),txtEmail.getText(),cbbChucVu.getSelectionModel().getSelectedItem(),txtNgayVaoLam.getText(),txtTaiKhoan.getText(),pwfMatKhau.getText(),txtDiaChi.getText(),cbbTrangThai.getSelectionModel().getSelectedItem());
                    setCellTable();
                    load();

                    khoaTextFields(true);
                    khoaControl(true);
                    btnThem.setDisable(false);
                    btnThem.setText("Thêm");
                    img = new Image("img/add.png");
                    imgThem.setImage(img);
                }
            }
//        }else if (btnSua.getText().equalsIgnoreCase("Hủy")){
//            if (isTen) {
//                listNV.update(txtMaNhanVien.getText(), txtTenNhanVien.getText(),txtSDT.getText(),txtEmail.getText(),cbbChucVu.getSelectionModel().getSelectedItem(),txtNgayVaoLam.getText(),txtTaiKhoan.getText(),pwfMatKhau.getText(),txtDiaChi.getText(),cbbTrangThai.getSelectionModel().getSelectedItem());
//                load();
//
//                khoaTextFields(true);
//                khoaControl(true);
//                btnThem.setDisable(false);
//                btnSua.setText("Sửa");
//                img = new Image("img/update.png");
//                imgSua.setImage(img);
//
//                Alert alert = new Alert(Alert.AlertType.INFORMATION);
//                alert.setTitle("Thông báo");
//                alert.setHeaderText("Lưu thành công");
//                Optional<ButtonType>BT=alert.showAndWait();
//
//            }
        }
    }

    @FXML
    void updateNV(ActionEvent event) {
//        if (btnSua.getText().equalsIgnoreCase("Sửa")) {
//            khoaTextFields(false);
//            khoaControl(true);
//            btnLuu.setDisable(false);
//            btnSua.setDisable(false);
//            btnSua.setText("Hủy");
//            img = new Image("img/cancel.png");
//            imgSua.setImage(img);
//        } else if (btnSua.getText().equalsIgnoreCase("Hủy")) {
//            errorDiaChi.setText("");
//            errorEmail.setText("");
//            errorMatKhau.setText("");
//            errorSDT.setText("");
//            errorTaiKhoan.setText("");
//            errorTenNV.setText("");
//            clearTextField();
//            khoaTextFields(true);
//            khoaControl(true);
//            btnThem.setDisable(false);
//            setTextToField();
//            btnSua.setText("Sửa");
//            img = new Image("img/update.png");
//            imgSua.setImage(img);
//        }
    }

    public void search(){
        txtTimKiem.setOnKeyReleased(e->{
            if(txtTimKiem.getText().equals("")){
                load();
            }
            else {
                data.clear();//xóa bảng
                data = listNV.search(txtTimKiem.getText());//conllection
                tableQLNV.setItems(data);
            }
        });
    }


}
