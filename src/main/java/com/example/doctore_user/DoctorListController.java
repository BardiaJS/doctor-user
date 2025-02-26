package com.example.doctore_user;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class DoctorListController {


    @FXML
    private ListView<String> doctorListView;

    public void initialize() {
        fetchDoctors();
    }

    private void fetchDoctors() {

        Task<ObservableList<String>> task = new Task<>() {
            @Override
            protected ObservableList<String> call() throws Exception {
                URL url = new URL("http://127.0.0.1:8000/api/doctor-list");
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setRequestMethod("GET");

                BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                StringBuilder response = new StringBuilder();
                String inputLine;
                while ((inputLine = in.readLine()) != null) {
                    response.append(inputLine);
                }
                in.close();

                // Parse the response manually to extract post titles and content
                // Assuming the response is a JSON array of objects with 'title' and 'content'
                String jsonResponse = response.toString();
                return parseDoctors(jsonResponse);
            }

            private ObservableList<String> parseDoctors(String jsonResponse) {
                // Manually parse the JSON response here
                // This is a simplified version; you may want to add error handling and more complex parsing
                ObservableList<String> doctors = FXCollections.observableArrayList();
                jsonResponse = jsonResponse.substring(1, jsonResponse.length() - 1); // Remove brackets
                String[] doctorArray = jsonResponse.split("},\\{");
                for (String doctor : doctorArray) {
                    String title = doctor.split("\"title\":\"")[1].split("\"")[0]; // Extract title
                    doctors.add(title);
                }
                return doctors;
            }
        };

        task.setOnSucceeded(event -> doctorListView.setItems(task.getValue()));
        task.setOnFailed(event -> {
            // Handle failure (e.g., show an error message)

        });

        Thread thread = new Thread(task);
        thread.setDaemon(true);
        thread.start();
    }
}
