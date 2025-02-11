package com.example.doctore_user;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class LoginController {
    public Label doctorIdentityCodeLabel;
    public TextField doctorIdentityCodeTextField;
    public Label doctorNationalIdLabel;
    public TextField doctorNationalIdTextField;
    public Label userNationalIdLabel;
    public TextField userNationalIdTextField;
    public Label userPasswordLabel;
    public PasswordField userPasswordPasswordfield;
    public Button loginButton;
    private Stage stage;
    private Scene scene;
    private Parent root;
    public RadioButton doctorRadioButton;
    public RadioButton userRadioButton;

    @FXML
    public void initialize() {

        loginButton.addEventHandler(MouseEvent.MOUSE_ENTERED, event -> {
            loginButton.setStyle("-fx-background-color: #7eb848; -fx-text-fill:black; -fx-font-weight: bold; -fx-font-size: 15px; -fx-border: solid;"); // Change to your desired color
        });

        loginButton.addEventHandler(MouseEvent.MOUSE_EXITED, event -> {
            loginButton.setStyle("-fx-background-color: #5D8736; -fx-text-fill:black; -fx-font-weight: bold; -fx-font-size: 15px; -fx-border: solid;"); // Change back to original color
        });
    }




    public void switchToFirstPage(ActionEvent event) throws IOException {
        root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("first-page.fxml")));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root , 800 , 800);
        stage.setScene(scene);
        stage.show();
    }

    public void loginButtonClicked(ActionEvent event) {

    }

}
