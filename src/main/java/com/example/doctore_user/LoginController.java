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

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LoginController {


    public Label displayErrorLabel;
    private String token;
    public Label userNationalIdLabel;
    public TextField inputUserNationalIdTextField;
    public Label userPasswordLabel;
    public PasswordField inputUserPasswordPasswordField;
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
        String userNationalId = inputUserNationalIdTextField.getText();
        String userPassword = inputUserPasswordPasswordField.getText();


        try {
            URL url = new URL("http://127.0.0.1:8000/api/login");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Type", "application/json");
            conn.setDoOutput(true);

            String jsonInputString = String.format("{\"national_id\":\"%s\", \"password\":\"%s\"}", userNationalId, userPassword);

            try (OutputStream os = conn.getOutputStream()) {
                byte[] input = jsonInputString.getBytes(StandardCharsets.UTF_8);
                os.write(input, 0, input.length);
            }

            int code = conn.getResponseCode();
            if (code == 200) {
                InputStream is = conn.getInputStream();
                showAlert("You have successfully logged in!");
            } else {
                BufferedReader br = new BufferedReader(new InputStreamReader(conn.getErrorStream(), StandardCharsets.UTF_8));
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
                String allMessages = alertMessages.toString();
                String[]lines = allMessages.split("\n");
                String nationalError = lines.length > 0 ? lines[0] : "";
                displayErrorLabel.setText(nationalError);

            }

        } catch (IOException e) {
            System.out.println("Error happened: " + e.toString());
        }
    }




    private void showAlert(String jsonResponse) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText(null);
        alert.setContentText(jsonResponse);
        alert.showAndWait();
    }

}
