package com.qlbh.display;

import java.util.List;

import com.qlbh.entity.ChucVu;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.util.Callback;

public class ChucVuDisplay extends ScrollPane {
    private TableView<ChucVu> table;
    List<ChucVu> posistionList;

    public ChucVuDisplay(List<ChucVu> posistionList){
        // Tạo bảng
        table = new TableView<>();
        this.posistionList = posistionList;
        
        TableColumn<ChucVu, String> nameColumn = new TableColumn<>("Tên nhân viên");
        nameColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<ChucVu, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<ChucVu, String> cellData) {
                ChucVu sp = cellData.getValue();
                String name = sp.getName();
                return new SimpleStringProperty(name);
            }
        });
        nameColumn.setPrefWidth(180);

        TableColumn<ChucVu, Double> salaryMColumn = new TableColumn<>("Hệ số lương");
        salaryMColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<ChucVu, Double>, ObservableValue<Double>>() {
            @Override
            public ObservableValue<Double> call(TableColumn.CellDataFeatures<ChucVu, Double> cellData) {
                ChucVu sp = cellData.getValue();
                Double salaryM = sp.getSalaryM();
                return new SimpleDoubleProperty(salaryM).asObject();
            }
        });
        salaryMColumn.setPrefWidth(90);
        
        table.getColumns().add(nameColumn);
        table.getColumns().add(salaryMColumn);

        table.setItems(null);
        table.setPrefSize(790, 670);

        this.setContent(table);
    }

    public void tableRefresh(){
        ObservableList<ChucVu> observableItemList = FXCollections.observableArrayList(posistionList);
        this.table.setItems(observableItemList);
        this.table.refresh();
    }

    public TableView<ChucVu> getTable(){
        return table;
    }
}
