package com.qlbh.controller;

import java.io.File;

import com.qlbh.display.ChucVuDisplay;
import com.qlbh.display.MainDisplay;
import com.qlbh.display.NhanVienDisplay;
import com.qlbh.entity.ChucVu;
import com.qlbh.entity.NhanVien;
import com.qlbh.func.ChucVuFunc;
import com.qlbh.func.NhanVienFunc;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;

public class MainDisplayController {
    private MainDisplay mainDisplay;
    private NhanVienDisplay nhanVienDisplay;
    private ChucVuDisplay chucVuDisplay;
    private NhanVienFunc nhanVienDao;
    private ChucVuFunc chucVuDao;

    public MainDisplayController(MainDisplay mainDisplay){
        nhanVienDao = new NhanVienFunc();
        chucVuDao = new ChucVuFunc();
        nhanVienDisplay = new NhanVienDisplay(nhanVienDao.getList());
        chucVuDisplay = new ChucVuDisplay(chucVuDao.getList());
        this.mainDisplay = mainDisplay;

        // Add EventHandler
        this.mainDisplay.getToNhanVienBtn().setOnAction(new ToNhanVienListener()::actionPerformed);
        this.mainDisplay.getToChucVuBtn().setOnAction(new ToChucVuListener()::actionPerformed);
        this.mainDisplay.getBACKBtn().setOnAction(new BACKListener()::actionPerformed);
        this.mainDisplay.getFilterNhanVienBtn().setOnAction(new FilterNhanVienListener()::actionPerformed);
        this.mainDisplay.getRefreshTableBtn().setOnAction(new RefreshTableListener()::actionPerformed);

        // Add EventHandler for UtilsPane1
        this.mainDisplay.getAddNhanVienBtn().setOnAction(new AddNhanVienListener()::actionPerformed);
        this.mainDisplay.getEditNhanVienBtn().setOnAction(new EditNhanVienListener()::actionPerformed);
        this.mainDisplay.getDeleteNhanVienBtn().setOnAction(new DeleteNhanVienListener()::actionPerformed);
        this.mainDisplay.getClearNhanVienBtn().setOnAction(new ClearNhanVienListener()::actionPerformed);
        this.mainDisplay.getImageView().setOnMouseClicked(new ImageFileChooserListener()::actionPerformed);
        this.mainDisplay.getImageLabel().setOnMouseClicked(new ImageFileChooserListener()::actionPerformed);
        this.nhanVienDisplay.getTable().setOnMouseClicked(new Table1SelectionListener()::actionPerformed);

        // Add EventHandler for UtilsPane2
        this.mainDisplay.getAddChucVuBtn().setOnAction(new AddChucVuListener()::actionPerformed);
        this.mainDisplay.getEditChucVuBtn().setOnAction(new EditChucVuListener()::actionPerformed);
        this.mainDisplay.getDeleteChucVuBtn().setOnAction(new DeleteChucVuListener()::actionPerformed);
        this.mainDisplay.getClearChucVuBtn().setOnAction(new ClearChucVuListener()::actionPerformed);
        this.chucVuDisplay.getTable().setOnMouseClicked(new Table2SelectionListener()::actionPerformed);
    }

    public void showMainDisplay(){
        mainDisplay.SHOW();
    }

    class BACKListener{
        public void actionPerformed(ActionEvent e){
            mainDisplay.HIDEControls();
            mainDisplay.HIDEFilter();
            mainDisplay.SHOWTitle();
            mainDisplay.getBACKBtn().setVisible(false);
        }
    }

    class Table1SelectionListener{
        public void actionPerformed(MouseEvent e) {
            NhanVien employee = nhanVienDisplay.getTable().getSelectionModel().getSelectedItem();
            if (employee != null) {
                mainDisplay.showEmployee(employee);
            }
        }
    }

    class Table2SelectionListener{
        public void actionPerformed(MouseEvent e) {
            ChucVu position = chucVuDisplay.getTable().getSelectionModel().getSelectedItem();
            if (position != null) {
                mainDisplay.showPosition(position);
            }
        }
    }

    class ToNhanVienListener {
        public void actionPerformed(ActionEvent e) {
            if(mainDisplay.getBorderPane().getChildren().size() > 0){
                mainDisplay.getBorderPane().getChildren().clear();
            }
            ObservableList<ChucVu> observableItemList = FXCollections.observableArrayList(chucVuDao.getList());
            mainDisplay.getPositionComboBox().setItems(observableItemList);
            mainDisplay.getBorderPane().setCenter(nhanVienDisplay);
            mainDisplay.getBorderPane().setVisible(true);
            mainDisplay.getBACKBtn().setVisible(true);
            mainDisplay.HIDETitle();
            mainDisplay.SHOWFilter();
            if(mainDisplay.getUtils2Pane().isVisible()){
                mainDisplay.getUtils2Pane().setVisible(false);
                mainDisplay.clearChucVuInfo();
            }
            if(!mainDisplay.getUtils1Pane().isVisible()){
                mainDisplay.getUtils1Pane().setVisible(true);
            }
            nhanVienDisplay.tableRefresh();
        }
    }

    class ToChucVuListener {
        public void actionPerformed(ActionEvent e) {
            if(mainDisplay.getBorderPane().getChildren().size() > 0){
                mainDisplay.getBorderPane().getChildren().clear();
            }
            mainDisplay.getBorderPane().setCenter(chucVuDisplay);
            mainDisplay.getBorderPane().setVisible(true);
            mainDisplay.getBACKBtn().setVisible(true);
            mainDisplay.HIDETitle();
            if(mainDisplay.getUtils1Pane().isVisible()){
                mainDisplay.getUtils1Pane().setVisible(false);
                mainDisplay.clearNhanVienInfo();
            }
            if(!mainDisplay.getUtils2Pane().isVisible()){
                mainDisplay.getUtils2Pane().setVisible(true);
            }
            chucVuDisplay.tableRefresh();
        }
    }
 
    class AddNhanVienListener {
        public void actionPerformed(ActionEvent e) {
            NhanVien employee = mainDisplay.getNhanVienInfo();
            if (employee != null) {
                nhanVienDao.add(employee);
                mainDisplay.clearNhanVienInfo();
                nhanVienDisplay.tableRefresh();
                mainDisplay.showMessage(new Alert(Alert.AlertType.INFORMATION), "Thêm thành công!");
            }
        }
    }

    class AddChucVuListener {
        public void actionPerformed(ActionEvent e) {
            ChucVu position = mainDisplay.getChucVuInfo();
            if (position != null) {
                chucVuDao.add(position);
                mainDisplay.clearChucVuInfo();
                chucVuDisplay.tableRefresh();
                mainDisplay.showMessage(new Alert(Alert.AlertType.INFORMATION), "Thêm thành công!");
            }
        }
    }

    class EditNhanVienListener {
        public void actionPerformed(ActionEvent e) {
            NhanVien employee = mainDisplay.getNhanVienInfo();
            if (employee != null) {
                nhanVienDao.edit(employee);
                mainDisplay.showEmployee(employee);
                nhanVienDisplay.tableRefresh();
                mainDisplay.showMessage(new Alert(Alert.AlertType.INFORMATION), "Cập nhật thành công!");
            }
        }
    }

    class EditChucVuListener {
        public void actionPerformed(ActionEvent e) {
            ChucVu position = mainDisplay.getChucVuInfo();
            if (position != null) {
                chucVuDao.edit(position);
                nhanVienDao.followEdit(position);
                mainDisplay.clearChucVuInfo();
                chucVuDisplay.tableRefresh();
                mainDisplay.showMessage(new Alert(Alert.AlertType.INFORMATION), "Cập nhật thành công!");
            }
        }
    }

    class DeleteNhanVienListener {
        public void actionPerformed(ActionEvent e) {
            NhanVien employee = mainDisplay.getNhanVienInfo();
            if (employee != null) {
                nhanVienDao.delete(employee);
                mainDisplay.clearNhanVienInfo();
                nhanVienDisplay.tableRefresh();
                mainDisplay.showMessage(new Alert(Alert.AlertType.INFORMATION), "Xóa thành công!");
            }
        }
    }

    class DeleteChucVuListener {
        public void actionPerformed(ActionEvent e) {
            ChucVu position = mainDisplay.getChucVuInfo();
            if (position != null) {
                chucVuDao.delete(position);
                nhanVienDao.followDelete(position.getId());
                mainDisplay.clearChucVuInfo();
                chucVuDisplay.tableRefresh();
                mainDisplay.showMessage(new Alert(Alert.AlertType.INFORMATION), "Xóa thành công!");
            }
        }
    }

    class ClearNhanVienListener {
        public void actionPerformed(ActionEvent e) {
            mainDisplay.clearNhanVienInfo();
            nhanVienDisplay.getTable().getSelectionModel().clearSelection();
            mainDisplay.showMessage(new Alert(Alert.AlertType.INFORMATION), "Clear!");
        }
    }

    class ClearChucVuListener {
        public void actionPerformed(ActionEvent e) {
            mainDisplay.clearChucVuInfo();
            chucVuDisplay.getTable().getSelectionModel().clearSelection();
        }
    }

    class FilterNhanVienListener {
        public void actionPerformed(ActionEvent e) {
            Double filter = mainDisplay.validateFilter();
            if(filter != -1.0){
                nhanVienDao.filter(filter);
                nhanVienDisplay.tableShow(nhanVienDao.getFilteredList());
                mainDisplay.getRefreshTableBtn().setVisible(true);
                mainDisplay.showMessage(new Alert(Alert.AlertType.INFORMATION), "Lọc thành công!");
            }
        }
    }

    class RefreshTableListener {
        public void actionPerformed(ActionEvent e) {
            ObservableList<ChucVu> observableItemList = FXCollections.observableArrayList(chucVuDao.getList());
            mainDisplay.getPositionComboBox().setItems(observableItemList);
            nhanVienDisplay.tableRefresh();
            mainDisplay.getRefreshTableBtn().setVisible(false);
            mainDisplay.showMessage(new Alert(Alert.AlertType.INFORMATION), "Refresh!");
        }
    }

    class ImageFileChooserListener {
        public void actionPerformed(MouseEvent e) {
            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("Select Image File");
            fileChooser.getExtensionFilters().addAll(
                    new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg")
            );

            File selectedFile = fileChooser.showOpenDialog(mainDisplay.getStage());
            if(selectedFile != null){
                mainDisplay.getImageView().setImage(new Image(selectedFile.toURI().toString()));
            }
        }
    }
}


