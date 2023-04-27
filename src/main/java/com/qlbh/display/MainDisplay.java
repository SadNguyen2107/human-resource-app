package com.qlbh.display;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;

import javax.imageio.ImageIO;

import com.qlbh.controller.MainDisplayController;
import com.qlbh.entity.ChucVu;
import com.qlbh.entity.NhanVien;

import javafx.embed.swing.SwingFXUtils;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextFormatter;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

public class MainDisplay extends Application{
    private Stage stage;
    private Scene scene;
    
    int eID;
    int pID;

    private Button BACK;
    private Button toNhanVienBtn;
    private Button toChucVuBtn;
    private Button addNhanVienBtn;
    private Button editNhanVienBtn;
    private Button deleteNhanVienBtn;
    private Button clearNhanVienBtn;
    private Button addChucVuBtn;
    private Button editChucVuBtn;
    private Button deleteChucVuBtn;
    private Button clearChucVuBtn;
    private Button filterNhanVienBtn;
    private Button refreshTableBtn;

    private Label TITLE;
    private Label title1Label;
    private Label employeeImageLabel;
    private Label eNameLabel;
    private Label genderLabel;
    private Label birthDateLabel;
    private Label addressLabel;
    private Label phoneNumberLabel;
    private Label positionLabel;
    private Label title2Label;
    private Label pNameLabel;
    private Label salaryMLabel;

    private TextField eNameField;
    private TextArea addressField;
    private TextField phoneNumberField;
    private TextField pNameField;
    private TextField salaryMField;
    private TextField filterField;

    private ComboBox<ChucVu> positionComboBox;
    private ComboBox<String> genderComboBox;

    private DateTimeFormatter displayFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    private DatePicker birthDateDP;

    private ImageView employeeImageView;
    private ImageView titleIcon;

    private BorderPane contentPane;
    private AnchorPane utilsPane1;
    private AnchorPane utilsPane2;

    @Override
    public void start(Stage stage) throws Exception {
        this.stage = stage;
        this.eID = -1;
        this.pID = -1;

        // Image
        titleIcon = new ImageView(new Image(getClass().getResourceAsStream("/image/title.png")));
        titleIcon.setFitHeight(300);
        titleIcon.setFitWidth(300);
        ImageView employeeIcon = new ImageView(new Image(getClass().getResourceAsStream("/image/employee.jpg")));
        employeeIcon.setFitHeight(60);
        employeeIcon.setFitWidth(60);
        ImageView positionIcon = new ImageView(new Image(getClass().getResourceAsStream("/image/position.png")));
        positionIcon.setFitHeight(60);
        positionIcon.setFitWidth(60);
        Image icon = new Image(getClass().getResourceAsStream("/image/icon.png"));
        
        // Button
        BACK = new Button("Back");
        BACK.setPrefSize(50, 30);
        BACK.setVisible(false);
        toChucVuBtn = new Button("Xem Chức Vụ");
        toChucVuBtn.setGraphic(positionIcon);
        toChucVuBtn.setContentDisplay(ContentDisplay.TOP);
        toNhanVienBtn = new Button("Xem Nhân Viên");
        toNhanVienBtn.setGraphic(employeeIcon);
        toNhanVienBtn.setContentDisplay(ContentDisplay.TOP);

        addNhanVienBtn = new Button("Thêm nhân viên");
        addNhanVienBtn.setPrefWidth(125);
        editNhanVienBtn = new Button("Sửa nhân viên");
        editNhanVienBtn.setPrefWidth(100);
        editNhanVienBtn.setDisable(true);
        deleteNhanVienBtn = new Button("Xoá nhân viên");
        deleteNhanVienBtn.setPrefWidth(100);
        deleteNhanVienBtn.setDisable(true);
        clearNhanVienBtn = new Button("Clear");
        clearNhanVienBtn.setPrefWidth(75);
        addChucVuBtn = new Button("Thêm chức vụ");
        addChucVuBtn.setPrefWidth(125);
        editChucVuBtn = new Button("Sửa chức vụ");
        editChucVuBtn.setPrefWidth(100);
        editChucVuBtn.setDisable(true);
        deleteChucVuBtn = new Button("Xoá chức vụ");
        deleteChucVuBtn.setPrefWidth(100);
        deleteChucVuBtn.setDisable(true);
        clearChucVuBtn = new Button("Clear");
        clearChucVuBtn.setPrefWidth(75);
        filterNhanVienBtn = new Button("Lọc nhân viên với hệ số lương lớn hơn:");
        filterNhanVienBtn.setVisible(false);
        refreshTableBtn = new Button("Refresh Table");
        refreshTableBtn.setVisible(false);

        // Label
        TITLE = new Label("Quản lý nhân sự");
        TITLE.setFont(Font.font("System", FontWeight.BOLD, 30));
        TITLE.setStyle("-fx-text-fill: blue;");
        title1Label = new Label("Thông tin nhân viên");
        title1Label.setFont(Font.font("System", FontWeight.BOLD, 18));
        employeeImageLabel = new Label("Choose a picture");
        eNameLabel = new Label("Tên nhân viên (*)");
        genderLabel = new Label("Giới tính (*)");
        birthDateLabel = new Label("Ngày sinh (*)");
        addressLabel = new Label("Địa chỉ");
        phoneNumberLabel = new Label("Số điện thoại (*)");
        positionLabel = new Label("Chức vụ (*)");

        title2Label = new Label("Thông tin chức vụ");
        title2Label.setFont(Font.font("System", FontWeight.BOLD, 18));
        pNameLabel = new Label("Tên chức vụ");
        salaryMLabel = new Label("Hệ số lương");

        // TextField
        eNameField = new TextField();
        eNameField.setPrefWidth(225);
        addressField = new TextArea();
        addressField.setPrefWidth(225);
        addressField.setPrefRowCount(5);
        addressField.setWrapText(true);
        phoneNumberField = new TextField();
        phoneNumberField.setPrefWidth(225);
        phoneNumberField.setTextFormatter(new TextFormatter<>(change -> {
            String newText = change.getControlNewText();
            if (newText.matches("\\d*")) {
                return change;
            }
            return null;
        }));
        pNameField = new TextField();
        pNameField.setPrefWidth(225);
        salaryMField = new TextField();
        salaryMField.setPrefWidth(225);
        filterField = new TextField();
        filterField.setPrefWidth(50);
        filterField.setVisible(false);

        // ComboBox
        ObservableList<String> items = FXCollections.observableArrayList("Nam", "Nữ");
        genderComboBox = new ComboBox<>(items);
        genderComboBox.setPrefWidth(225);
        positionComboBox = new ComboBox<>();
        positionComboBox.setPrefWidth(225);
        positionComboBox.setVisibleRowCount(5);
        
        // DatePicker
        birthDateDP = new DatePicker();
        birthDateDP.setPrefWidth(225);
        birthDateDP.getEditor().setStyle("-fx-alignment: center;");
        
        // Miscellaneous
        employeeImageView = new ImageView();
        employeeImageView.setFitWidth(150);
        employeeImageView.setFitHeight(150);

        // Container
        contentPane = new BorderPane();
        contentPane.setPrefSize(800, 680);
        contentPane.setVisible(false);
        utilsPane1 = new AnchorPane();
        utilsPane1.setPrefSize(245, 680);
        utilsPane1.setVisible(false);
        utilsPane1.getChildren().addAll(title1Label, eNameLabel, genderLabel, birthDateLabel, addressLabel, employeeImageLabel, 
                                        phoneNumberLabel, positionLabel, eNameField, genderComboBox, addressField,
                                        phoneNumberField, birthDateDP, positionComboBox, employeeImageView,
                                        addNhanVienBtn, editNhanVienBtn, deleteNhanVienBtn, clearNhanVienBtn);
        utilsPane2 = new AnchorPane();
        utilsPane2.setPrefSize(245, 680);
        utilsPane2.setVisible(false);
        utilsPane2.getChildren().addAll(title2Label, pNameLabel, salaryMLabel,
                                        pNameField, salaryMField,
                                        addChucVuBtn, editChucVuBtn, deleteChucVuBtn, clearChucVuBtn);

        AnchorPane panel = new AnchorPane();
        panel.getChildren().addAll(titleIcon ,TITLE, BACK, toChucVuBtn, toNhanVienBtn, filterNhanVienBtn, refreshTableBtn, filterField, contentPane, utilsPane1, utilsPane2);
        
        
        // Set position
        // panel
        AnchorPane.setTopAnchor(filterNhanVienBtn, 10.0);
        AnchorPane.setLeftAnchor(filterNhanVienBtn, 450.0);
        AnchorPane.setTopAnchor(filterField, 10.0);
        AnchorPane.setLeftAnchor(filterField, 680.0);
        AnchorPane.setTopAnchor(refreshTableBtn, 10.0);
        AnchorPane.setRightAnchor(refreshTableBtn, 20.0);
        AnchorPane.setTopAnchor(titleIcon, 50.0);
        AnchorPane.setLeftAnchor(titleIcon, 375.0);
        AnchorPane.setTopAnchor(TITLE, 370.0);
        AnchorPane.setLeftAnchor(TITLE, 410.0);
        AnchorPane.setTopAnchor(toNhanVienBtn, 450.0);
        AnchorPane.setLeftAnchor(toNhanVienBtn, 400.0);
        AnchorPane.setTopAnchor(toChucVuBtn, 450.0);
        AnchorPane.setRightAnchor(toChucVuBtn, 400.0);
        AnchorPane.setTopAnchor(contentPane, 50.0);
        AnchorPane.setLeftAnchor(contentPane, 250.0);
        AnchorPane.setTopAnchor(utilsPane1, 50.0);
        AnchorPane.setLeftAnchor(utilsPane1, 0.0);
        AnchorPane.setTopAnchor(utilsPane2, 50.0);
        AnchorPane.setLeftAnchor(utilsPane2, 0.0);
        
        // utilsPane1
        AnchorPane.setTopAnchor(title1Label, 0.0);
        AnchorPane.setLeftAnchor(title1Label,40.0);
        AnchorPane.setTopAnchor(employeeImageLabel, 90.0);
        AnchorPane.setLeftAnchor(employeeImageLabel,85.0);
        AnchorPane.setTopAnchor(eNameLabel, 200.0);
        AnchorPane.setLeftAnchor(eNameLabel, 10.0);
        AnchorPane.setTopAnchor(genderLabel, 250.0);
        AnchorPane.setLeftAnchor(genderLabel, 10.0);
        AnchorPane.setTopAnchor(birthDateLabel, 300.0);
        AnchorPane.setLeftAnchor(birthDateLabel, 10.0);
        AnchorPane.setTopAnchor(addressLabel, 350.0);
        AnchorPane.setLeftAnchor(addressLabel, 10.0);
        AnchorPane.setTopAnchor(phoneNumberLabel, 480.0);
        AnchorPane.setLeftAnchor(phoneNumberLabel, 10.0);
        AnchorPane.setTopAnchor(positionLabel, 530.0);
        AnchorPane.setLeftAnchor(positionLabel, 10.0);

        AnchorPane.setTopAnchor(employeeImageView, 40.0);
        AnchorPane.setLeftAnchor(employeeImageView, 40.0);
        AnchorPane.setTopAnchor(eNameField, 220.0);
        AnchorPane.setLeftAnchor(eNameField, 10.0);
        AnchorPane.setTopAnchor(genderComboBox, 270.0);
        AnchorPane.setLeftAnchor(genderComboBox, 10.0);
        AnchorPane.setTopAnchor(birthDateDP, 320.0);
        AnchorPane.setLeftAnchor(birthDateDP, 10.0);
        AnchorPane.setTopAnchor(addressField, 370.0);
        AnchorPane.setLeftAnchor(addressField, 10.0);
        AnchorPane.setTopAnchor(phoneNumberField, 500.0);
        AnchorPane.setLeftAnchor(phoneNumberField, 10.0);
        AnchorPane.setTopAnchor(positionComboBox, 550.0);
        AnchorPane.setLeftAnchor(positionComboBox, 10.0);

        AnchorPane.setTopAnchor(addNhanVienBtn, 595.0);
        AnchorPane.setLeftAnchor(addNhanVienBtn, 10.0);
        AnchorPane.setTopAnchor(clearNhanVienBtn, 595.0);
        AnchorPane.setLeftAnchor(clearNhanVienBtn, 150.0);
        AnchorPane.setTopAnchor(editNhanVienBtn, 630.0);
        AnchorPane.setLeftAnchor(editNhanVienBtn, 10.0);
        AnchorPane.setTopAnchor(deleteNhanVienBtn, 630.0);
        AnchorPane.setLeftAnchor(deleteNhanVienBtn, 125.0);

        // utilsPane2
        AnchorPane.setTopAnchor(title2Label, 0.0);
        AnchorPane.setLeftAnchor(title2Label, 40.0);
        AnchorPane.setTopAnchor(pNameLabel, 70.0);
        AnchorPane.setLeftAnchor(pNameLabel, 10.0);
        AnchorPane.setTopAnchor(salaryMLabel, 120.0);
        AnchorPane.setLeftAnchor(salaryMLabel, 10.0);

        AnchorPane.setTopAnchor(pNameField, 90.0);
        AnchorPane.setLeftAnchor(pNameField, 10.0);
        AnchorPane.setTopAnchor(salaryMField, 140.0);
        AnchorPane.setLeftAnchor(salaryMField, 10.0);

        AnchorPane.setTopAnchor(addChucVuBtn, 180.0);
        AnchorPane.setLeftAnchor(addChucVuBtn, 10.0);
        AnchorPane.setTopAnchor(clearChucVuBtn, 180.0);
        AnchorPane.setLeftAnchor(clearChucVuBtn, 150.0);
        AnchorPane.setTopAnchor(editChucVuBtn, 225.0);
        AnchorPane.setLeftAnchor(editChucVuBtn, 10.0);
        AnchorPane.setTopAnchor(deleteChucVuBtn, 225.0);
        AnchorPane.setLeftAnchor(deleteChucVuBtn, 125.0);
        //

        ScrollPane scrollpane = new ScrollPane(panel);
        scene = new Scene(scrollpane, 1060, 740);
        stage.getIcons().add(icon);
        stage.setTitle("Quản lý nhân sự");
        stage.setScene(scene);

        MainDisplayController controller = new MainDisplayController(this);
        controller.showMainDisplay();
    }

    public void SHOW(){
        this.stage.show();
    }

    public void HIDEControls(){
        this.utilsPane1.setVisible(false);
        this.utilsPane2.setVisible(false);
        this.contentPane.setVisible(false);
    }

    public void HIDETitle(){
        this.toNhanVienBtn.setVisible(false);
        this.toChucVuBtn.setVisible(false);
        this.TITLE.setVisible(false);
        this.titleIcon.setVisible(false);
    }

    public void SHOWTitle(){
        this.toNhanVienBtn.setVisible(true);
        this.toChucVuBtn.setVisible(true);
        this.TITLE.setVisible(true);
        this.titleIcon.setVisible(true);
    }

    public void SHOWFilter(){
        this.filterNhanVienBtn.setVisible(true);
        this.filterField.setVisible(true);
    }

    public void HIDEFilter(){
        this.filterNhanVienBtn.setVisible(false);
        this.filterField.setVisible(false);
    }

    public void showMessage(Alert alert, String message) {
        alert.setTitle("Dialog");
        alert.setContentText(message);
        alert.showAndWait();
    }

    public void showEmployee(NhanVien employee){
        eID = employee.getId();
        eNameField.setText(employee.getName());
        genderComboBox.setValue(employee.getGender());
        phoneNumberField.setText(employee.getPhoneNumber());
        birthDateDP.getEditor().setText(new String(employee.getBirthDate().format(displayFormatter)));
        addressField.setText(employee.getAddress());
        phoneNumberField.setText(employee.getPhoneNumber());
        positionComboBox.setValue(employee.getPosition());
        if(employee.getPictureData() != null){
            employeeImageView.setImage(new Image(new ByteArrayInputStream(employee.getPictureData())));
        }

        editNhanVienBtn.setDisable(false);
        deleteNhanVienBtn.setDisable(false);
        addNhanVienBtn.setDisable(true);
    }

    public void showPosition(ChucVu position){
        pID = position.getId();
        pNameField.setText(position.getName());
        salaryMField.setText("" + position.getSalaryM());

        editChucVuBtn.setDisable(false);
        deleteChucVuBtn.setDisable(false);
        addChucVuBtn.setDisable(true);
    }

    public void clearNhanVienInfo(){
        eID = -1;
        eNameField.setText("");
        genderComboBox.setValue("");
        birthDateDP.setValue(null);
        birthDateDP.getEditor().setText("");
        addressField.setText("");
        phoneNumberField.setText("");
        positionComboBox.setValue(null);
        employeeImageView.setImage(null);

        editNhanVienBtn.setDisable(true);
        deleteNhanVienBtn.setDisable(true);
        addNhanVienBtn.setDisable(false);
    }

    public void clearChucVuInfo(){
        pID = -1;
        pNameField.setText("");
        salaryMField.setText("");

        editChucVuBtn.setDisable(true);
        deleteChucVuBtn.setDisable(true);
        addChucVuBtn.setDisable(false);
    }

    public NhanVien getNhanVienInfo(){
        LocalDate date = validateBirthDate();
        if (!validateName() || !validateGender() || !(date != null) || !validatePhoneNumber() || !validatePosition()) {
            return null;
        }
        try {
            NhanVien employee = new NhanVien();
            if(eID != -1){
                employee.setId(eID);
            }
            employee.setName(eNameField.getText().trim());
            employee.setGender(genderComboBox.getSelectionModel().getSelectedIndex());
            employee.setBirthDate(date);
            employee.setAddress(addressField.getText().trim());
            employee.setPhoneNumber(phoneNumberField.getText().trim());
            employee.setPosition(positionComboBox.getSelectionModel().getSelectedItem());
            if(employeeImageView != null && employeeImageView.getImage() != null){
                employee.setPictureData(imageToBytes(employeeImageView.getImage()));
            }
            return employee;
        } 
        catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public ChucVu getChucVuInfo(){
        if (!validateName2() || !validateSalaryM()) {
            return null;
        }
        try {
            ChucVu position = new ChucVu();
            if(pID != -1){
                position.setId(pID);
            }
            position.setName(pNameField.getText().trim());
            position.setSalaryM(Double.parseDouble(salaryMField.getText().trim()));
            return position;
        } catch (Exception e) {
            showMessage(new Alert(Alert.AlertType.ERROR), e.getMessage());
        }
        return null;
    }

    private boolean validateName() {
        String name = eNameField.getText();
        if (name == null || "".equals(name.trim())) {
            eNameField.requestFocus();
            showMessage(new Alert(Alert.AlertType.WARNING), "Tên nhân viên không được trống");
            return false;
        }
        return true;
    }

    private boolean validateGender() {
        int gender = genderComboBox.getSelectionModel().getSelectedIndex();
        if (gender < 0) {
            genderComboBox.requestFocus();
            showMessage(new Alert(Alert.AlertType.WARNING), "Hãy chọn giới tính.");
            return false;
        }
        return true;
    }

    private LocalDate validateBirthDate(){
        if(birthDateDP.getEditor().getText() != null && !birthDateDP.getEditor().getText().trim().isEmpty()){
            String selectedDate = birthDateDP.getEditor().getText().trim();
            try{
                DateTimeFormatter formatter = new DateTimeFormatterBuilder()
                .appendPattern("[d/M/yyyy][dd/MM/yyyy][dd-MM-yyyy][d-M-yyyy][dd MM yyyy][d M yyyy]")
                .toFormatter();
                LocalDate date = LocalDate.parse(selectedDate, formatter);
                if(date != null){
                    return date;
                }
            }
            catch(Exception e){
                birthDateDP.requestFocus();
                showMessage(new Alert(Alert.AlertType.WARNING), "Hãy chọn ngày sinh hoặc viết vào ô trống theo dạng:"
                                                                + "\n\"dd/MM/yyyy\" hoặc \"d/M/yyyy\""
                                                                + "\n\"dd MM yyyy\" hoặc \"d M yyyy\""
                                                                + "\n\"dd-MM-yyyy\" hoặc \"d-M-yyyy\""
                                                                + "\n");                                                                
            }
        }
        else{
            birthDateDP.requestFocus();
            showMessage(new Alert(Alert.AlertType.WARNING), "Hãy chọn ngày sinh.");
        }
        return null;
    }

    private boolean validatePhoneNumber() {
        String phoneNumber = phoneNumberField.getText();
        if (phoneNumber == null || "".equals(phoneNumber.trim())) {
            phoneNumberField.requestFocus();
            showMessage(new Alert(Alert.AlertType.WARNING), "Số điện thoại không được trống");
            return false;
        }
        return true;
    }

    private boolean validatePosition() {
        ChucVu selectedPosition = positionComboBox.getValue();
        if (selectedPosition == null) {
            positionComboBox.requestFocus();
            showMessage(new Alert(Alert.AlertType.WARNING), "Chức vụ không được trống.");
            return false;
        }
        return true;
    }

    private boolean validateName2() {
        String name = pNameField.getText();
        if (name == null || "".equals(name.trim())) {
            pNameField.requestFocus();
            showMessage(new Alert(Alert.AlertType.WARNING), "Tên chức vụ không được trống");
            return false;
        }
        return true;
    }

    private boolean validateSalaryM() {
        try {
            Double salaryM = Double.parseDouble(salaryMField.getText().trim());
            if (salaryM < 0) {
                salaryMField.requestFocus();
                showMessage(new Alert(Alert.AlertType.WARNING), "Hệ số lương không hợp lệ, phải lớn hơn 0.");
                return false;
            }
        } catch (Exception e) {
            salaryMField.requestFocus();
            showMessage(new Alert(Alert.AlertType.WARNING), "Hệ số lương không hợp lệ!");
            return false;
        }
        return true;
    }

    public double validateFilter(){
        Double filter = -1.0;
        try {
            filter = Double.parseDouble(filterField.getText().trim());
            if (filter < 0) {
                filterField.requestFocus();
                showMessage(new Alert(Alert.AlertType.WARNING), "Hệ số lương cần so sánh không hợp lệ, phải lớn hơn 0.");
                return -1.0;
            }
        } catch (Exception e) {
            filterField.requestFocus();
            showMessage(new Alert(Alert.AlertType.WARNING), "Hệ số lương cần so sánh không hợp lệ!");
            return -1.0;
        }
        return filter;
    }

    private byte[] imageToBytes(Image image) throws IOException {
        BufferedImage bufferedImage = SwingFXUtils.fromFXImage(image, null);
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        try {
            ImageIO.write(bufferedImage, "png", baos);
        } catch (IOException e) {
            e.getStackTrace();
        }
        return baos.toByteArray();
    }

    public Stage getStage(){
        return this.stage;
    }

    public BorderPane getBorderPane(){
        return this.contentPane;
    }

    public AnchorPane getUtils1Pane(){
        return this.utilsPane1;
    }

    public AnchorPane getUtils2Pane(){
        return this.utilsPane2;
    }

    public Button getBACKBtn(){
        return this.BACK;
    }

    public Button getToNhanVienBtn(){
        return this.toNhanVienBtn;
    }

    public Button getToChucVuBtn(){
        return this.toChucVuBtn;
    }

    public Button getAddNhanVienBtn(){
        return this.addNhanVienBtn;
    }

    public Button getEditNhanVienBtn(){
        return this.editNhanVienBtn;
    }

    public Button getDeleteNhanVienBtn(){
        return this.deleteNhanVienBtn;
    }

    public Button getClearNhanVienBtn(){
        return this.clearNhanVienBtn;
    }

    public Button getAddChucVuBtn(){
        return this.addChucVuBtn;
    }

    public Button getEditChucVuBtn(){
        return this.editChucVuBtn;
    }

    public Button getDeleteChucVuBtn(){
        return this.deleteChucVuBtn;
    }

    public Button getClearChucVuBtn(){
        return this.clearChucVuBtn;
    }

    public Button getFilterNhanVienBtn(){
        return this.filterNhanVienBtn;
    }

    public Button getRefreshTableBtn(){
        return this.refreshTableBtn;
    }

    public ImageView getImageView(){
        return this.employeeImageView;
    }

    public Label getImageLabel(){
        return this.employeeImageLabel;
    }

    public ComboBox<ChucVu> getPositionComboBox(){
        return this.positionComboBox;
    }
}
