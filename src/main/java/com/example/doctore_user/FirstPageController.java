package com.example.doctore_user;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class FirstPageController {

    @FXML
    public Label loginText;
    @FXML
    public Button switchToFirst;
    @FXML
    public Button switchToLogin;
    public Button switchToSignin;
    public Button switchToProfile;

    public Button switchToDoctorList;
    public Button switchToPatientsList;
    public Button switchToSingleDoctor;

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

    public void switchToProfilePage(ActionEvent event) throws IOException {
        root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("user-profile-page.fxml")));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root , 800 , 800);
        stage.setScene(scene);
        stage.show();
    }

    public void switchToDoctorListPage(ActionEvent event) {

    }

    public void switchToPatientsListPage(ActionEvent event) {
    }


    public void initialize() {

        switchToLogin.addEventHandler(MouseEvent.MOUSE_ENTERED, event -> {
            switchToLogin.setStyle("-fx-background-color:#b6f09e ; -fx-text-fill:black; -fx-font-weight: bold; -fx-font-size: 15px; -fx-border: solid;"); // Change to your desired color
        });

        switchToLogin.addEventHandler(MouseEvent.MOUSE_EXITED, event -> {
            switchToLogin.setStyle("-fx-background-color: #7eb848; -fx-text-fill:black; -fx-font-weight: bold; -fx-font-size: 15px; -fx-border: solid;"); // Change back to original color
        });



        switchToSignin.addEventHandler(MouseEvent.MOUSE_ENTERED, event -> {
            switchToSignin.setStyle("-fx-background-color: #b6f09e; -fx-text-fill:black; -fx-font-weight: bold; -fx-font-size: 15px; -fx-border: solid;"); // Change to your desired color
        });

        switchToSignin.addEventHandler(MouseEvent.MOUSE_EXITED, event -> {
            switchToSignin.setStyle("-fx-background-color: #7eb848; -fx-text-fill:black; -fx-font-weight: bold; -fx-font-size: 15px; -fx-border: solid;"); // Change back to original color
        });


        switchToProfile.addEventHandler(MouseEvent.MOUSE_ENTERED, event -> {
            switchToProfile.setStyle("-fx-background-color: #b6f09e; -fx-text-fill:black; -fx-font-weight: bold; -fx-font-size: 15px; -fx-border: solid;"); // Change to your desired color
        });

        switchToProfile.addEventHandler(MouseEvent.MOUSE_EXITED, event -> {
            switchToProfile.setStyle("-fx-background-color: #7eb848; -fx-text-fill:black; -fx-font-weight: bold; -fx-font-size: 15px; -fx-border: solid;"); // Change back to original color
        });


        switchToDoctorList.addEventHandler(MouseEvent.MOUSE_ENTERED, event -> {
            switchToDoctorList.setStyle("-fx-background-color: #b6f09e; -fx-text-fill:black; -fx-font-weight: bold; -fx-font-size: 15px; -fx-border: solid;"); // Change to your desired color
        });

        switchToDoctorList.addEventHandler(MouseEvent.MOUSE_EXITED, event -> {
            switchToDoctorList.setStyle("-fx-background-color: #7eb848; -fx-text-fill:black; -fx-font-weight: bold; -fx-font-size: 15px; -fx-border: solid;"); // Change back to original color
        });


        switchToPatientsList.addEventHandler(MouseEvent.MOUSE_ENTERED, event -> {
            switchToPatientsList.setStyle("-fx-background-color: #b6f09e; -fx-text-fill:black; -fx-font-weight: bold; -fx-font-size: 15px; -fx-border: solid;"); // Change to your desired color
        });

        switchToPatientsList.addEventHandler(MouseEvent.MOUSE_EXITED, event -> {
            switchToPatientsList.setStyle("-fx-background-color: #7eb848; -fx-text-fill:black; -fx-font-weight: bold; -fx-font-size: 15px; -fx-border: solid;"); // Change back to original color
        });




    }

    public void switchToSingleDoctorPage(ActionEvent event) throws IOException {
        root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("doctor-profile-page.fxml")));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root , 800 , 800);
        stage.setScene(scene);
        stage.show();
    }
}
