module co.edu.uniquindio.marketpruebas {
    requires javafx.controls;
    requires javafx.fxml;


    opens co.edu.uniquindio.marketpruebas to javafx.fxml;
    exports co.edu.uniquindio.marketpruebas;
}