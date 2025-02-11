package com.example.doctore_user;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class ChangeUserPasswordController {

    public Button savePasswordChange;
    public Label userOldPasswordLabel;
    public PasswordField userOldPasswordField;
    public Label userNewPasswordLabel;
    public PasswordField userNewPasswordField;
    private Stage stage;
    private Scene scene;
    private Parent root;

    public void switchToFirstPage(ActionEvent event) throws IOException {
        root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("user-profile-page.fxml")));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root , 800 , 800);
        stage.setScene(scene);
        stage.show();
    }

    public void savePasswordChangeButtonClicked(ActionEvent event) {
    }
}
