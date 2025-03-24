module com.nhm {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires java.base;

    opens com.nhm to javafx.fxml;
    opens com.nhm.pojo to javafx.base;
    exports com.nhm;
}
