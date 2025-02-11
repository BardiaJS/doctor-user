package com.example.doctore_user;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class SwitchController {
    @FXML
    public Label helloText;
    @FXML
    public Label loginText;
    @FXML
    public Button switchToFirst;
    @FXML
    public Button switchToLogin;
    public Button switchToSignin;
    public Button switchToProfile;

    private Stage stage;
    private Scene scene;
    private Parent root;

    public void switchToFirstPage (ActionEvent event)  throws IOException  {

        root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("first-page.fxml")));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root , 800 , 800);
        stage.setScene(scene);
        stage.show();

    }

    public void switchToLoginPage(ActionEvent event) throws IOException {


        root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("login-page.fxml")));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root , 800 , 800);
        stage.setScene(scene);
        stage.show();

    }

    public void switchToSigninPage(ActionEvent event) throws IOException {
        root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("sign-in-page.fxml")));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root , 800 , 800);
        stage.setScene(scene);
        stage.show();
    }

    public void switchToProfilePage(ActionEvent event) {
    }
}
