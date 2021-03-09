module hu.sfm {
    requires javafx.controls;
    requires javafx.fxml;

    opens hu.sfm to javafx.fxml;
    exports hu.sfm;
}