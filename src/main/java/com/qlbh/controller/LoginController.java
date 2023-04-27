package com.qlbh.controller;

import com.qlbh.display.LoginView;
import com.qlbh.display.MainDisplay;
import com.qlbh.entity.User;
import com.qlbh.func.UserFunc;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;

public class LoginController {
    private UserFunc userDao;
    private LoginView loginView;
    
    public LoginController(LoginView view) {
        this.loginView = view;
        this.userDao = new UserFunc();

        this.loginView.getButton().setOnAction(new LoginListener()::actionPerformed);
    }
    
    public void showLoginView() {
        loginView.SHOW();
    }
    
    class LoginListener{
        public void actionPerformed(ActionEvent e) {
            User user = loginView.getUser();
            if (userDao.checkUser(user)) {
                Platform.runLater(() -> {
                    MainDisplay mainDisplay = new MainDisplay();
                    Stage stage = new Stage();
                    try {
                        mainDisplay.start(stage);
                    } catch (Exception e1) {
                        e1.printStackTrace();
                    }
                });
                loginView.CLOSE();
            } else {
                loginView.showMessage(new Alert(AlertType.WARNING), "username hoặc password không đúng.");
            }
        }
    }
}
