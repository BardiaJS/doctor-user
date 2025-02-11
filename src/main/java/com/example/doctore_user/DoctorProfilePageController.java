package com.example.doctore_user;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class DoctorProfilePageController {

    public Button reserveThisDoctor;
    private Stage stage;
    private Scene scene;
    private Parent root;

    public void switchToFirstPage(ActionEvent event) throws IOException {
        root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("first-page.fxml")));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root , 800 , 800);
        stage.setScene(scene);
        stage.show();
    }

    public void reserveThisDoctorButtonClicked(ActionEvent event) {

    }

    @FXML
    public void initialize() {

        reserveThisDoctor.addEventHandler(MouseEvent.MOUSE_ENTERED, event -> {
            reserveThisDoctor.setStyle("-fx-background-color: #9c9c9a; -fx-text-fill:white; -fx-font-weight: bold; -fx-font-size: 10px; -fx-border: solid;"); // Change to your desired color
        });

        reserveThisDoctor.addEventHandler(MouseEvent.MOUSE_EXITED, event -> {
            reserveThisDoctor.setStyle("-fx-background-color: #636362; -fx-text-fill:white; -fx-font-weight: bold; -fx-font-size: 10px; -fx-border: solid;"); // Change back to original color
        });
    }
}
