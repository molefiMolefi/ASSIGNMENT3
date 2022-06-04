module com.example.helicoptergame {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.helicoptergame to javafx.fxml;
    exports com.example.helicoptergame;
}