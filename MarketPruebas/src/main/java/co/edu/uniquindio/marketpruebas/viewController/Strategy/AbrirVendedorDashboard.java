package co.edu.uniquindio.marketpruebas.viewController.Strategy;

import co.edu.uniquindio.marketpruebas.mapping.dto.VendedorDto;
import co.edu.uniquindio.marketpruebas.services.AbrirVentanasStrategy;
import co.edu.uniquindio.marketpruebas.viewController.VendedorDashboardViewController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class AbrirVendedorDashboard implements AbrirVentanasStrategy {
    VendedorDto admin;


    public static void abrirVentana(VendedorDto usuario) throws IOException {
        FXMLLoader loader = new FXMLLoader(AbrirVendedorDashboard.class.getResource("/co/edu/uniquindio/marketpruebas/vendedor-dashboard.fxml"));
        Scene scene = new Scene(loader.load(),1012,809);
        VendedorDashboardViewController controller = loader.getController();
        controller.inicializarDashboard(usuario);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();
    }

    @Override
    public void abrirVentana2(VendedorDto usuario) throws IOException {

    }
}
