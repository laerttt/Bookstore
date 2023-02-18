module com.example.bookstoreapplication {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.bookstoreapplication to javafx.fxml;
    exports com.example.bookstoreapplication;
    exports com.example.bookstoreapplication.Models;
    opens com.example.bookstoreapplication.Models to javafx.fxml;
    exports com.example.bookstoreapplication.Views;
    opens com.example.bookstoreapplication.Views to javafx.fxml;
    exports com.example.bookstoreapplication.Controls;
    opens com.example.bookstoreapplication.Controls to javafx.fxml;
    exports com.example.bookstoreapplication.NoHeader;
    opens com.example.bookstoreapplication.NoHeader to javafx.fxml;
}