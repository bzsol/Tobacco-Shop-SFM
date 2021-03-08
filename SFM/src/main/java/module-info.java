module hu.sfmproject {
    requires javafx.controls;
    requires javafx.fxml;

    opens hu.sfmproject to javafx.fxml;
    exports hu.sfmproject;
}