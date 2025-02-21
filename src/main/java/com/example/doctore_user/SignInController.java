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

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class SignInController {
    public Label signInText;
    public Label userNationalIdLabel;
    public TextField userNationalIdTextField;
    public Label userPasswordLabel;
    public PasswordField userPasswordPasswordfield;
    public Label userFirstNameLabel;
    public TextField userFirstNameTextField;
    public Label userLastNameLabel;
    public TextField userLastNameTextField;
    public Label userIllnessCaseLabel;
    public TextField userIllnessCaseTextField;
    public Button signInButton;
    public Label messageLabel;
    private Stage stage;
    private Scene scene;
    private Parent root;
    @FXML
    public void initialize() {

        signInButton.addEventHandler(MouseEvent.MOUSE_ENTERED, event -> {
            signInButton.setStyle("-fx-background-color: #7eb848; -fx-text-fill:black; -fx-font-weight: bold; -fx-font-size: 15px; -fx-border: solid;"); // Change to your desired color
        });

        signInButton.addEventHandler(MouseEvent.MOUSE_EXITED, event -> {
            signInButton.setStyle("-fx-background-color: #5D8736; -fx-text-fill:black; -fx-font-weight: bold; -fx-font-size: 15px; -fx-border: solid;"); // Change back to original color
        });
    }
    public void switchToFirstPage(ActionEvent event) throws IOException {
        root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("first-page.fxml")));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root , 800 , 800);
        stage.setScene(scene);
        stage.show();
    }



    public void signInButtonClicked(ActionEvent event) {
        String national_id = userNationalIdTextField.getText();
        String password = userPasswordPasswordfield.getText();
        String first_name = userFirstNameTextField.getText();
        String last_name = userLastNameTextField.getText();


        try {
            URL url = new URL("http://127.0.0.1:8000/api/signin-user");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Type", "application/json");
            conn.setDoOutput(true);

            String jsonInputString = String.format(
                    "{\"first_name\":\"%s\", \"last_name\":\"%s\", \"national_id\":\"%s\", \"password\":\"%s\"}",
                    first_name, last_name,national_id , password);

            try (OutputStream os = conn.getOutputStream()) {
                byte[] input = jsonInputString.getBytes(StandardCharsets.UTF_8);
                os.write(input, 0, input.length);
            }
            // Read the response
            if (conn.getResponseCode() == 201) {
                showAlert("Successfully Registration");
            }else{ // Success
                BufferedReader br = new BufferedReader(new InputStreamReader(conn.getErrorStream(), "utf-8"));
                StringBuilder response = new StringBuilder();
                String responseLine;
                while ((responseLine = br.readLine()) != null) {
                    response.append(responseLine.trim());
                }
                // Parse and display errors
                response = new StringBuilder(response.toString());
                Pattern pattern = Pattern.compile("\"([^\"]*)\"");
                Matcher matcher = pattern.matcher(response);
                StringBuilder alertMessages = new StringBuilder();
                while (matcher.find()) {
                    alertMessages.append(matcher.group(1)).append("\n"); // Collect sentences
                }
                showAlert(alertMessages.toString());

            }

        } catch (Exception e) {
            System.out.println("NO Service");
        }
    }


    private void showAlert(String jsonResponse) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText(null);
        alert.setContentText(jsonResponse);
        alert.showAndWait();
    }


}
