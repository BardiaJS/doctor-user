module com.example.doctore_user {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.net.http;
    requires java.desktop;


    opens com.example.doctore_user to javafx.fxml;
    exports com.example.doctore_user;
}