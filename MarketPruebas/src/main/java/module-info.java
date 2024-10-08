module co.edu.uniquindio.marketpruebas {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;

    opens co.edu.uniquindio.marketpruebas.viewcontroller to javafx.fxml;
    opens co.edu.uniquindio.marketpruebas to javafx.fxml;
    exports co.edu.uniquindio.marketpruebas;
}