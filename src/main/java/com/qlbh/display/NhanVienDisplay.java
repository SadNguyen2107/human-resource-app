package com.qlbh.display;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import com.qlbh.entity.NhanVien;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.util.Callback;

public class NhanVienDisplay extends ScrollPane {
    private TableView<NhanVien> table;
    private DateTimeFormatter displayFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    List<NhanVien> employeeList;

    public NhanVienDisplay(List<NhanVien> employeeList){
        // Tạo bảng
        table = new TableView<>();
        this.employeeList = employeeList;
        
        TableColumn<NhanVien, String> nameColumn = new TableColumn<>("Tên nhân viên");
        nameColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<NhanVien, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<NhanVien, String> cellData) {
                NhanVien sp = cellData.getValue();
                String name = sp.getName();
                return new SimpleStringProperty(name);
            }
        });
        nameColumn.setPrefWidth(200);

        TableColumn<NhanVien, String> genderColumn = new TableColumn<>("Giới tính");
        genderColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<NhanVien, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<NhanVien, String> cellData) {
                NhanVien sp = cellData.getValue();
                String gender = sp.getGender();
                return new SimpleStringProperty(gender);
            }
        });
        genderColumn.setPrefWidth(60);

        TableColumn<NhanVien, String> birthDateColumn = new TableColumn<>("Ngày sinh");
        birthDateColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<NhanVien, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<NhanVien, String> cellData) {
                NhanVien sp = cellData.getValue();
                LocalDate birthDate = sp.getBirthDate();
                String date = birthDate.format(displayFormatter);
                return new SimpleStringProperty(date);
            }
        });
        birthDateColumn.setPrefWidth(90);
        
        TableColumn<NhanVien, String> addressColumn = new TableColumn<>("Địa chỉ");
        addressColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<NhanVien, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<NhanVien, String> cellData) {
                NhanVien sp = cellData.getValue();
                String address = sp.getAddress();
                return new SimpleStringProperty(address);
            }
        });
        addressColumn.setCellFactory(column -> {
            TableCell<NhanVien, String> cell = new TableCell<NhanVien, String>() {
                @Override
                protected void updateItem(String item, boolean empty) {
                    super.updateItem(item, empty);
                    if (item == null || empty) {
                        setText(null);
                        setGraphic(null);
                    } else {
                        Label label = new Label(item);
                        label.setWrapText(true);
                        setGraphic(label);
                    }
                }
            };
            return cell;
        });
        addressColumn.setPrefWidth(160);

        TableColumn<NhanVien, String> phoneNumberColumn = new TableColumn<>("Số điện thoại");
        phoneNumberColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<NhanVien, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<NhanVien, String> cellData) {
                NhanVien sp = cellData.getValue();
                String phoneNumber = sp.getPhoneNumber();
                return new SimpleStringProperty(phoneNumber);
            }
        });
        phoneNumberColumn.setPrefWidth(100);

        TableColumn<NhanVien, String> positionColumn = new TableColumn<>("Chức vụ");
        positionColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<NhanVien, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<NhanVien, String> cellData) {
                NhanVien sp = cellData.getValue();
                if(sp.getPosition() == null){
                    return null;
                }
                String position = sp.getPosition().getName();
                return new SimpleStringProperty(position);
            }
        });
        positionColumn.setPrefWidth(100);

        TableColumn<NhanVien, Double> salaryMColumn = new TableColumn<>("Hệ số lương");
        salaryMColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<NhanVien, Double>, ObservableValue<Double>>() {
            @Override
            public ObservableValue<Double> call(TableColumn.CellDataFeatures<NhanVien, Double> cellData) {
                NhanVien sp = cellData.getValue();
                if(sp.getPosition() == null){
                    return null;
                }
                Double salaryM = sp.getPosition().getSalaryM();
                return new SimpleDoubleProperty(salaryM).asObject();
            }
        });
        salaryMColumn.setPrefWidth(80);
        
        table.getColumns().add(nameColumn);
        table.getColumns().add(genderColumn);
        table.getColumns().add(birthDateColumn);
        table.getColumns().add(addressColumn);
        table.getColumns().add(phoneNumberColumn);
        table.getColumns().add(positionColumn);
        table.getColumns().add(salaryMColumn);

        table.setItems(null);
        table.setPrefSize(790, 670);

        this.setContent(table);
    }

    public void tableRefresh(){
        ObservableList<NhanVien> observableItemList = FXCollections.observableArrayList(employeeList);
        this.table.setItems(observableItemList);
        this.table.refresh();
    }

    public void tableShow(List<NhanVien> somelist){
        ObservableList<NhanVien> observableItemList = FXCollections.observableArrayList(somelist);
        this.table.setItems(observableItemList);
        this.table.refresh();
    }

    public TableView<NhanVien> getTable(){
        return table;
    }
}
