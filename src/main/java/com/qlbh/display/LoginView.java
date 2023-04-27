package com.qlbh.display;

import com.qlbh.controller.LoginController;
import com.qlbh.entity.User;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class LoginView extends Application {
    Stage stage;
    Scene scene;

    private Label userNameLabel;
    private Label passwordlabel;
    private PasswordField passwordField;
    private TextField userNameField;
    private Button loginBtn;

    @Override
    public void start(Stage stage) throws Exception {
        this.stage = stage;
        
        userNameLabel = new Label("UserName");
        passwordlabel = new Label("Password");
        userNameField = new TextField();
        userNameField.setPrefWidth(230);
        passwordField = new PasswordField();
        passwordField.setPrefWidth(230);

        loginBtn = new Button();
        loginBtn.setText("Login");
        loginBtn.setPrefWidth(100);

        AnchorPane panel = new AnchorPane();
        panel.setPrefSize(250, 150);
        panel.getChildren().addAll(userNameField, userNameLabel, passwordField, passwordlabel, loginBtn);

        AnchorPane.setTopAnchor(userNameLabel, 10.0);
        AnchorPane.setLeftAnchor(userNameLabel, 10.0);
        AnchorPane.setTopAnchor(userNameField, 30.0);
        AnchorPane.setLeftAnchor(userNameField, 10.0);
        AnchorPane.setTopAnchor(passwordlabel, 60.0);
        AnchorPane.setLeftAnchor(passwordlabel, 10.0);
        AnchorPane.setTopAnchor(passwordField, 80.0);
        AnchorPane.setLeftAnchor(passwordField, 10.0);
        AnchorPane.setTopAnchor(loginBtn, 110.0);
        AnchorPane.setLeftAnchor(loginBtn, 80.0);

        this.scene = new Scene(panel, 250, 150);
        Image icon = new Image(getClass().getResourceAsStream("/image/icon.png"));
        stage.getIcons().add(icon);
        stage.setTitle("Đăng nhập");
        stage.setScene(scene);

        LoginController controller = new LoginController(this);
        controller.showLoginView();
    }

    public void SHOW(){
        this.stage.show();
    }

    public void CLOSE(){
        this.stage.close();
    }

    public void showMessage(Alert alert, String message) {
        alert.setTitle("Dialog");
        alert.setContentText(message);
        alert.showAndWait();
    }

    public User getUser() {
        return new User(userNameField.getText(), passwordField.getText());
    }

    public Button getButton(){
        return this.loginBtn;
    }
}