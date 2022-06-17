package controller;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import dao.NhaCungCapDao;
import dao.NhanVienDao;
import dao.SanPhamDao;
import entity.NhaCungCap;
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
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import org.controlsfx.control.textfield.TextFields;

import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

public class SanPhamController implements Initializable {

    ObservableList<NhanVien> listNV;
    NhanVienDao nhanVienDao;
    private ObservableList<SanPham> data;
    SanPhamDao listSP;
    NhaCungCapDao nhaCungCapDao = new NhaCungCapDao();

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
    private TableView<SanPham> tableSanPham;

    @FXML
    private TableColumn<?, ?> columnMaNCC;

    @FXML
    private TableColumn<?, ?> columnMaSP;

    @FXML
    private TableColumn<?, ?> columnTenSP;

    @FXML
    private TableColumn<?, ?> columnTG;

    @FXML
    private TableColumn<?, ?> columnSoLuong;

    @FXML
    private TableColumn<?, ?> columnDonGia;

    @FXML
    private TableColumn<?, ?> columnTrangThai;

    @FXML
    private JFXTextField txtMaSP;

    @FXML
    private JFXTextField txtTenSP;

    @FXML
    private JFXTextField txtSoLuongTon;

    @FXML
    private JFXTextField txtDonGia;

    @FXML
    private JFXTextField txtMaNCC;

    @FXML
    private JFXTextField txtTenNCC;

    @FXML
    private Label errorTenSP;

    @FXML
    private Label errorSLT;

    @FXML
    private Label errorDonGia;

    @FXML
    private Label errorMaNCC;

    @FXML
    private Label lblTaiKhoan;

    Image img;

    @FXML
    private JFXComboBox<String> cbbTrangThai;

    @FXML
    private JFXComboBox<Integer> cbbTGBH;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        TextFields.bindAutoCompletion(txtMaNCC,nhaCungCapDao.loadDSMaNCC());
        txtMaNCC.setOnKeyPressed((KeyEvent e) -> {
            if(e.getCode() == KeyCode.ENTER){
                String maNCC = txtMaNCC.getText();
                if(!maNCC.isEmpty() && maNCC.matches("[0-9]+") && !nhaCungCapDao.loadTenNCC(maNCC).isEmpty()) {

                    String tenNCC = nhaCungCapDao.loadTenNCC(maNCC);
                    txtTenNCC.setText(tenNCC);

                }else {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Thông báo");
                    alert.setHeaderText("Không tìm thấy nhà cung cấp");
                    //alert.setContentText("");
                    Optional<ButtonType> BT=alert.showAndWait();
                }
            }
        });
        cbbTrangThai.getItems().addAll("Đang kinh doanh","Ngừng kinh doanh");
        cbbTrangThai.getSelectionModel().select(0);
        cbbTGBH.getItems().addAll(0,3,6,9,12,24);
        cbbTGBH.getSelectionModel().select(0);
        listSP = new SanPhamDao();
        data = FXCollections.observableArrayList();


        setCellTable();
        load();
        setTextToField();
        search();
    }

//    public  void learnWord(String text){
//        if(nhaCungCapDao.loadDSMaNCC().isEmpty())
//    }

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
        stage.setTitle("Quản lý mua bán linh kiện");
        MainController controller = loader.getController();
        controller.setTenTK(listNV.get(0).getMaNhanVien(),listNV.get(0).getTenNhanVien(),listNV.get(0).getChucVu());
        stage.setScene(scene);
    }

    //ham nap du lieu vao cao cot
    public void setCellTable(){
        columnMaSP.setCellValueFactory(new PropertyValueFactory<>("maSanPham"));
        columnMaNCC.setCellValueFactory(new PropertyValueFactory<>("maNhaCungCap"));
        columnTenSP.setCellValueFactory(new PropertyValueFactory<>("tenSanPham"));
        columnTG.setCellValueFactory(new PropertyValueFactory<>("thoiGianBaoHanh"));
        columnSoLuong.setCellValueFactory(new PropertyValueFactory<>("soLuong"));
        columnDonGia.setCellValueFactory(new PropertyValueFactory<>("donGia"));
        columnTrangThai.setCellValueFactory(new PropertyValueFactory<>("trangThai"));
    }

    public void load() {
        data.clear();//xoa bang cu
        data = listSP.loadData();
        tableSanPham.setItems(data);
    }

    public void khoaTextFields(boolean b){
        txtMaNCC.setDisable(b);
        txtTenSP.setDisable(b);
        txtSoLuongTon.setDisable(b);
        txtDonGia.setDisable(b);
        cbbTGBH.setDisable(b);
        cbbTrangThai.setDisable(b);
    }

    public void khoaControl(boolean b){
        btnThem.setDisable(b);
        btnLuu.setDisable(b);
        btnSua.setDisable(b);
        btnXoa.setDisable(b);
    }

    //ham xoa rong textfield
    public void clearTextField(){
        txtMaSP.clear();
        txtMaNCC.clear();
        txtTenSP.clear();
        txtSoLuongTon.clear();
        txtDonGia.clear();
    }

    @FXML
    void addSanPham(ActionEvent event) {
        if (btnThem.getText().equalsIgnoreCase("Thêm")){
            khoaTextFields(false);
            cbbTrangThai.setDisable(true);
            khoaControl(true);
            btnLuu.setDisable(false);
            btnThem.setDisable(false);
            btnThem.setText("Hủy");
            img = new Image("img/cancel.png");
            imgThem.setImage(img);
            clearTextField();
            txtMaSP.setText(String.valueOf(listSP.loadMaSanPham()+130));
        } else {
            errorMaNCC.setText("");
            errorDonGia.setText("");
            errorSLT.setText("");
            errorTenSP.setText("");
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
    void deleteSanPham(ActionEvent event) {
        Alert alert=new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Xác nhận");
        alert.setHeaderText("Bạn có chắc chắn muốn xóa không?");
        Optional<ButtonType>BT=alert.showAndWait();
        if(BT.get() == ButtonType.OK){
            listSP.delete(txtMaSP.getText());
            load();
            clearTextField();
            btnXoa.setDisable(true);
            btnSua.setDisable(true);
        }
    }

    @FXML
    void saveSanPham(ActionEvent event) {
        boolean isTen = validation.TextFieldValidation.errorTen(txtTenSP,errorTenSP,"Không chứ ký tự số");
        boolean isSoLuongTon = validation.TextFieldValidation.errorSoLuongTon(txtSoLuongTon,errorSLT,"Số lượng lớn hơn 0 và nhỏ hơn 500");
        boolean isDonGia = validation.TextFieldValidation.errorDonGia(txtDonGia,errorDonGia,"Lớn hơn hoặc bằng 10 và nhở hơn 50000");
        boolean isMaNCC = validation.TextFieldValidation.isTextFieldTypeNumber(txtMaNCC,errorMaNCC,"Là ký tự số");
        if (btnThem.getText().equalsIgnoreCase("Hủy")){
            if (isTen && isSoLuongTon && isDonGia && isMaNCC) {

                Alert alert=new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Xác nhận");
                alert.setHeaderText("Bạn có chắc chắn muốn lưu thông tin không?");
                Optional<ButtonType>BT=alert.showAndWait();
                if(BT.get() == ButtonType.OK){
                    listSP.create(txtMaNCC.getText(),txtTenSP.getText(),cbbTGBH.getSelectionModel().getSelectedItem(),txtSoLuongTon.getText(),txtDonGia.getText(), cbbTrangThai.getSelectionModel().getSelectedItem());
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
        }else if (btnSua.getText().equalsIgnoreCase("Hủy")){
            if (isTen && isSoLuongTon && isDonGia && isMaNCC) {

                Alert alert=new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Xác nhận");
                alert.setHeaderText("Bạn có chắc chắn muốn lưu thông tin không?");
                Optional<ButtonType>BT=alert.showAndWait();
                if(BT.get() == ButtonType.OK){
                    listSP.update(txtTenSP.getText(),cbbTGBH.getSelectionModel().getSelectedItem(),txtSoLuongTon.getText(),txtDonGia.getText(), cbbTrangThai.getSelectionModel().getSelectedItem(),txtMaSP.getText());
                    load();

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

    @FXML
    void updateSanPham(ActionEvent event) {
        if (btnSua.getText().equalsIgnoreCase("Sửa")) {
            khoaTextFields(false);
            khoaControl(true);
            btnLuu.setDisable(false);
            btnSua.setDisable(false);
            btnSua.setText("Hủy");
            img = new Image("img/cancel.png");
            imgSua.setImage(img);
        } else if (btnSua.getText().equalsIgnoreCase("Hủy")) {
            errorMaNCC.setText("");
            errorDonGia.setText("");
            errorSLT.setText("");
            errorTenSP.setText("");
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

    //ham lay gia tri tu table nap vao textfield
    public void setTextToField(){
        tableSanPham.setOnMouseClicked(e -> {
            int i = tableSanPham.getSelectionModel().getSelectedIndex();
            if(i >= 0 && !(btnThem.getText().equalsIgnoreCase("Hủy")) && !(btnSua.getText().equalsIgnoreCase("Hủy"))){
                btnSua.setDisable(false);
                btnXoa.setDisable(false);
                SanPham p = tableSanPham.getItems().get(tableSanPham.getSelectionModel().getSelectedIndex());
                txtMaSP.setText(p.getMaSanPham());
                txtMaNCC.setText(p.getMaNhaCungCap());
                txtTenSP.setText(p.getTenSanPham());
                txtSoLuongTon.setText(String.valueOf(p.getSoLuong()));
                txtDonGia.setText(String.valueOf(p.getDonGia()));
            }
        });
    }

    public void search(){
        txtTimKiem.setOnKeyReleased(e->{
            if(txtTimKiem.getText().equals("")){
                load();
            }
            else {
                data.clear();//xóa bảng
                data = listSP.search(txtTimKiem.getText());//conllection
                tableSanPham.setItems(data);
            }
        });
    }
}
