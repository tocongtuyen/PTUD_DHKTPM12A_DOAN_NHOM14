package controller;

import dao.NhaCungCapDao;
import dao.NhanVienDao;
import entity.NhaCungCap;
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

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Optional;
import java.util.ResourceBundle;

public class NhaCungCapController implements Initializable {

    @FXML
    private Label lblTaiKhoan;

    @FXML
    private TextField txtMaNCC;

    @FXML
    private TextField txtTenNCC;

    @FXML
    private TextField txtSDT;

    @FXML
    private TextField txtEmail;

    @FXML
    private TextArea txtDiaChi;

    @FXML
    private Button btnThem;

    @FXML
    private Button btnLuu;

    @FXML
    private Button btnSua;

    @FXML
    private Button btnXoa;

    @FXML
    private TextField txtTimKiem;

    @FXML
    private TableView<NhaCungCap> tableNCC;

    @FXML
    private TableColumn<?, ?> columnMaNCC;

    @FXML
    private TableColumn<?, ?> columnTenNCC;

    @FXML
    private TableColumn<?, ?> columnSDT;

    @FXML
    private TableColumn<?, ?> columnEmail;

    @FXML
    private TableColumn<?, ?> columnDiaChi;

    @FXML
    private Button btnXuat;

    @FXML
    private Label errorTenNCC;

    @FXML
    private Label errorSDT;

    @FXML
    private Label errorDiaChi;


    @FXML
    private Label errorEmail;

    @FXML
    private ImageView imgThem;

    Image img;

    @FXML
    private ImageView imgSua;

    private ObservableList<NhaCungCap> data;
    NhaCungCapDao listNCC;
    ObservableList<NhanVien> listNV;
    NhanVienDao nhanVienDao;



    @Override
    public void initialize(URL location, ResourceBundle resources) {
        listNCC = new NhaCungCapDao();
        data = FXCollections.observableArrayList();
        setCellTable();
        loadNhaCungCap();
        setTextToField();
        searchNCC();
    }

    @FXML
    public void setTaiKhoan(String maNV){
        nhanVienDao = new NhanVienDao();
        listNV = nhanVienDao.loadNhanVien(maNV);
        lblTaiKhoan.setText(listNV.get(0).getTaiKhoan());
    }

    //ham lay gia tri tu table nap vao textfield
    public void setTextToField(){
        tableNCC.setOnMouseClicked(e -> {
            int i = tableNCC.getSelectionModel().getSelectedIndex();
            if(i >= 0 && !(btnThem.getText().equalsIgnoreCase("Hủy")) && !(btnSua.getText().equalsIgnoreCase("Hủy"))){
                btnSua.setDisable(false);
                btnXoa.setDisable(false);
                NhaCungCap p = tableNCC.getItems().get(tableNCC.getSelectionModel().getSelectedIndex());
                txtMaNCC.setText(p.getMaNhaCungCap());
                txtTenNCC.setText(p.getTenNhaCungCap());
                txtSDT.setText(p.getSoDienThoai());
                txtEmail.setText(p.getEmail());
                txtDiaChi.setText(p.getDiaChi());
            }
        });
    }

    //ham xoa rong textfield
    public void clearTextField(){
        txtMaNCC.clear();
        txtTenNCC.clear();
        txtSDT.clear();
        txtEmail.clear();
        txtDiaChi.clear();
    }

    //ham nap du lieu vao cao cot
    public void setCellTable(){
        columnMaNCC.setCellValueFactory(new PropertyValueFactory<>("maNhaCungCap"));
        columnTenNCC.setCellValueFactory(new PropertyValueFactory<>("tenNhaCungCap"));
        columnSDT.setCellValueFactory(new PropertyValueFactory<>("soDienThoai"));
        columnEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
        columnDiaChi.setCellValueFactory(new PropertyValueFactory<>("diaChi"));
    }

    //ham load database vao table
    public void loadNhaCungCap(){
        data.clear();//xoa bang cu
        data = listNCC.loadData();
        tableNCC.setItems(data);
    }

    //ham them nha cung cap
    @FXML
    public void addNCC(ActionEvent event){
        if (btnThem.getText().equalsIgnoreCase("Thêm")){
            khoaTextFields(false);
            khoaControl(true);
            btnLuu.setDisable(false);
            btnThem.setDisable(false);
            btnThem.setText("Hủy");
            img = new Image("img/cancel.png");
            imgThem.setImage(img);
            clearTextField();
            txtMaNCC.setText(String.valueOf(listNCC.loadMaNCC()+130));
        } else {
            errorTenNCC.setText("");
            errorSDT.setText("");
            errorDiaChi.setText("");
            errorEmail.setText("");
            clearTextField();
            khoaTextFields(true);
            khoaControl(true);
            btnThem.setDisable(false);
            btnThem.setText("Thêm");
            img = new Image("img/add.png");
            imgThem.setImage(img);
        }
    }

    public void saveNCC(ActionEvent event){
        boolean isTenNCC = validation.TextFieldValidation.isTextFieldNotEmpty(txtTenNCC,errorTenNCC,"Không được bỏ trống");
        boolean isSDT = validation.TextFieldValidation.errorSoDienThoai(txtSDT,errorSDT,"+84|0-9, vd: 0974388274");
        boolean isDiaChi = validation.TextFieldValidation.isTextFieldNotEmpty(txtDiaChi,errorDiaChi,"Không được bỏ trống");
        boolean isEmail = validation.TextFieldValidation.errorEmail(txtEmail,errorEmail,"Email có dạng nguyenvana@gmail.com");
        if (btnThem.getText().equalsIgnoreCase("Hủy")){
            if (isTenNCC && isSDT && isDiaChi && isEmail) {

                Alert alert=new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Xác nhận");
                alert.setHeaderText("Bạn có chắc chắn muốn lưu thông tin không?");
                Optional<ButtonType>BT=alert.showAndWait();
                if(BT.get() == ButtonType.OK){
                    listNCC.create(txtTenNCC.getText(),txtSDT.getText(),txtEmail.getText(),txtDiaChi.getText());
                    setCellTable();
                    loadNhaCungCap();

                    khoaTextFields(true);
                    khoaControl(true);
                    btnThem.setDisable(false);
                    btnThem.setText("Thêm");
                    img = new Image("img/add.png");
                    imgThem.setImage(img);
                }
            }
        }else if (btnSua.getText().equalsIgnoreCase("Hủy")){
            if (isTenNCC && isSDT && isDiaChi && isEmail) {
                Alert alert=new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Xác nhận");
                alert.setHeaderText("Bạn có chắc chắn muốn lưu thông tin không?");
                Optional<ButtonType>BT=alert.showAndWait();
                if(BT.get() == ButtonType.OK){
                    listNCC.update(txtMaNCC.getText(),txtTenNCC.getText(),txtSDT.getText(),txtEmail.getText(),txtDiaChi.getText());
                    loadNhaCungCap();

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

    //ham sua thong tin nha cung cap
    @FXML
    public void updateNCC(ActionEvent event) {
        if (btnSua.getText().equalsIgnoreCase("Sửa")) {
            khoaTextFields(false);
            khoaControl(true);
            btnLuu.setDisable(false);
            btnSua.setDisable(false);
            btnSua.setText("Hủy");
            img = new Image("img/cancel.png");
            imgSua.setImage(img);
        } else if (btnSua.getText().equalsIgnoreCase("Hủy")) {
            errorTenNCC.setText("");
            errorSDT.setText("");
            errorDiaChi.setText("");
            errorEmail.setText("");
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

    //ham xoa nha cung cap
    @FXML
    public void deleteNCC(ActionEvent event) {
        Alert alert=new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Xóa nhà cung cấp");
        alert.setHeaderText("Bạn có chắc chắn muốn xóa không?");
        Optional<ButtonType>BT=alert.showAndWait();
        if(BT.get() == ButtonType.OK){
            listNCC.delete(txtMaNCC.getText());
            loadNhaCungCap();
            clearTextField();
            btnXoa.setDisable(true);
            btnSua.setDisable(true);
        }
    }

    //ham tim kiem ten nha cung cap
    public void searchNCC(){
        txtTimKiem.setOnKeyReleased(e->{
            if(txtTimKiem.getText().equals("")){
                loadNhaCungCap();
            }
            else {
                data.clear();//xóa bảng
                data = listNCC.search(txtTimKiem.getText());//conllection
                tableNCC.setItems(data);
            }
        });
    }

    public void khoaTextFields(boolean b){
        txtTenNCC.setDisable(b);
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

    //ham quay ve giao dien chinh
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
    void xuatFile(ActionEvent event) {
        // exportToExcel.setFont(Font.font("Sanserif));// xet form va size
        // xet su kien

        btnXuat.setOnAction(e->{

            try {
                Alert alert=new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Xóa nhà cung cấp");
                alert.setHeaderText("Bạn có xuất file excel không?");
                Optional<ButtonType>BT=alert.showAndWait();
                if(BT.get() == ButtonType.OK){
                    listNCC.exportToExcel();
                }
            } catch (IOException e1) {
                e1.printStackTrace();
            }



        });
    }
}

