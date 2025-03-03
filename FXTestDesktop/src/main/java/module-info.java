module com.nhm {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;

    opens com.nhm to javafx.fxml;
    exports com.nhm;
}
