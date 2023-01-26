module com.example.bookstoreapplication {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.bookstoreapplication to javafx.fxml;
    exports com.example.bookstoreapplication;
}