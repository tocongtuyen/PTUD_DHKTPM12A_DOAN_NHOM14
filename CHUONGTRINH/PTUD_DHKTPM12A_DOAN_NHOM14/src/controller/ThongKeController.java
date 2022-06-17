package controller;

import dao.KhachHangDao;
import dao.NhanVienDao;

import dao.SanPhamDao;
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
import javafx.scene.chart.*;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class ThongKeController implements Initializable {

    private ObservableList<NhanVienDao.TKNhanVien> listTKNV;
    private ObservableList<KhachHangDao.TKKH> listTKKH;
    KhachHangDao listKH;
    NhanVienDao listNV;
    SanPhamDao listTKSP;
    SanPhamDao listTKDT;
    ObservableList<NhanVien> listNV1;
    NhanVienDao nhanVienDao;

    @FXML
    private AnchorPane loadSP;

    @FXML
    private Tab tkSanPham;

    @FXML
    private Tab tkKhachHang;

    @FXML
    private Tab tkDoanhThu;

    @FXML
    private Tab tkNhanVien;

    @FXML
    private Label lblTaiKhoan;

    @FXML
    private BarChart<?, ?> guiTKNV;
    @FXML
    private CategoryAxis y;

    @FXML
    private NumberAxis x;
    @FXML
    private PieChart thongKeSP;
    @FXML
    private BarChart<?, ?> tKDoanhThu;
    @FXML
    private BarChart<?, ?> TKKH;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        listKH = new KhachHangDao();
        listNV = new NhanVienDao();
        listTKSP = new SanPhamDao();
        listTKDT = new SanPhamDao();

        // thong ke  doanh so nhan vien
        XYChart.Series set1=new XYChart.Series<>();
        listTKNV=listNV.thongKeNV();
        for(NhanVienDao.TKNhanVien NV:listTKNV ){
            set1.getData().add(new XYChart.Data(NV.getTenNhanVien(),NV.getDoanSo()));
        }
        guiTKNV.getData().addAll(set1);

        // thong ke san pham
        ObservableList<PieChart.Data>listSP= FXCollections.observableArrayList(
        );
        for (SanPham sp:listTKSP.TKSP()) {

            listSP.add( new PieChart.Data(sp.getTenSanPham(),sp.getSoLuong()));
        }
        thongKeSP.setData(listSP);

        XYChart.Series set3=new XYChart.Series<>();

        for(int i=1;i<=12;i++){
            Double  doanhSo=listTKDT.TKDoanhSo(i,2018);
            set3.getData().add(new XYChart.Data("Tháng"+(i)+"/2018",doanhSo+300));

        }
        tKDoanhThu.getData().addAll(set3);

        // thong ke  doanh so KhachHang
        XYChart.Series set2=new XYChart.Series<>();
        listTKKH=listKH.thongKeKH();
        for(KhachHangDao.TKKH KH:listTKKH ){
            set2.getData().add(new XYChart.Data(KH.getTenKH(),KH.getTongTienDaMua()));
        }
        TKKH.getData().addAll(set2);
    }

    @FXML
    public void setTaiKhoan(String maNV){
        nhanVienDao = new NhanVienDao();
        listNV1 = nhanVienDao.loadNhanVien(maNV);
        lblTaiKhoan.setText(listNV1.get(0).getTaiKhoan());
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
        controller.setTenTK(listNV1.get(0).getMaNhanVien(),listNV1.get(0).getTenNhanVien(),listNV1.get(0).getChucVu());
        stage.setScene(scene);
    }
}
