package com.example.doctore_user;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.util.Callback;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class DoctorListController {



    @FXML
    private ListView<String> doctorListView;

    public void initialize() {
        List<Doctor> doctors = fetchDoctors();

        int i = 0;
            if(doctors.isEmpty()){
                doctorListView.getItems().add("No Doctor Registered!");
            }else{
                for (Doctor doctor : doctors) {
                    i++;
                    doctorListView.getItems().add("number: " + i);
                    doctorListView.getItems().add("Name: " + doctor.getFirstName());
                    doctorListView.getItems().add("Last Name: " + doctor.getLastName());
                    doctorListView.getItems().add("Category: " + doctor.getCategory());


                }


                doctorListView.setCellFactory(new Callback<ListView<String>, ListCell<String>>() {
                    @Override
                    public ListCell<String> call(ListView<String> param) {
                        return new ListCell<String>() {
                            private final VBox vBox = new VBox(); // Use VBox instead of HBox
                            private final Button button = new Button("Reserve");
                            private final Label nameLabel = new Label();
                            private final Label lastNameLabel = new Label();
                            private final Label categoryLabel = new Label();

                            {
                                button.setOnAction(event -> {
                                    String item = nameLabel.getText(); // Get the name from the label
                                });

                                vBox.getChildren().addAll(nameLabel, lastNameLabel, categoryLabel, button); // Add labels and button to VBox
                                vBox.setSpacing(10); // Set spacing between labels and button
                            }

                            @Override
                            protected void updateItem(String item, boolean empty) {
                                super.updateItem(item, empty);
                                if (empty || item == null) {
                                    setGraphic(null);
                                } else {
                                    if (item.startsWith("Name: ")) {
                                        // Set the name label
                                        nameLabel.setText(item.substring(6)); // Extract the first name

                                        // Clear previous text from lastNameLabel and categoryLabel
                                        lastNameLabel.setText("");
                                        categoryLabel.setText("");

                                        // Now we also need to handle the other details (last name, category)
                                        int index = getIndex();
                                        if (index + 1 < doctorListView.getItems().size() && doctorListView.getItems().get(index + 1).startsWith("Last Name: ")) {
                                            lastNameLabel.setText(doctorListView.getItems().get(index + 1).substring(11)); // Extract last name
                                        }
                                        if (index + 2 < doctorListView.getItems().size() && doctorListView.getItems().get(index + 2).startsWith("Category: ")) {
                                            categoryLabel.setText(doctorListView.getItems().get(index + 2).substring(10)); // Extract category
                                        }

                                        setGraphic(vBox);
                                    } else {
                                        setGraphic(null); // Hide for other items
                                    }
                                }
                            }
                        };
                    }
                });
            }



    }

    private List<Doctor> fetchDoctors(){
        final String API_URL = "http://127.0.0.1:8000/api/doctor-list";
        List<Doctor> doctors = new ArrayList<>();
        try {
            URL url = new URL(API_URL);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setRequestProperty("Accept", "application/json");
            connection.setRequestProperty("Authorization", "Bearer " + LoginController.token);

            if (connection.getResponseCode() == HttpURLConnection.HTTP_OK) {
                BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                String inputLine;
                StringBuilder response = new StringBuilder();
                while ((inputLine = in.readLine()) != null) {
                    response.append(inputLine);
                }
                in.close();

                // Parse the response (assuming it's a simple JSON array)
                String jsonResponse = response.toString();
                jsonResponse = jsonResponse.substring(1, jsonResponse.length() - 1); // Remove brackets
                String[] doctorStrings = jsonResponse.split("},");

                for (String doctorString : doctorStrings) {
                    // Ensure to handle the closing brace for the last item correctly
                    doctorString = doctorString.replace("}", ""); // Remove the closing brace if it exists
                    String[] fields = doctorString.split(","); // Split fields

                    int id = Integer.parseInt(fields[0].split(":")[1]);
                    String firstName = fields[1].split(":")[1].replace("\"", "");
                    String lastName = fields[2].split(":")[1].replace("\"", "");
                    String category = fields[3].split(":")[1].replace("\"", "");

                    doctors.add(new Doctor(id, firstName , lastName ,category ));
                }
            } else {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setHeaderText(null);
                alert.setContentText("Unauthorized User");
                alert.showAndWait();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return doctors;
    }


    public void reserveButtonClicked(ActionEvent event) {
        
    }
}
