package co.edu.uniquindio.marketpruebas.controlador;

import co.edu.uniquindio.marketpruebas.factory.ModelFactory;
import co.edu.uniquindio.marketpruebas.model.Administrador;
import co.edu.uniquindio.marketpruebas.model.Usuario;
import co.edu.uniquindio.marketpruebas.model.Vendedor;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import javax.swing.*;
import java.io.IOException;

public class LoginController {
    private ModelFactory modelFactory;
    @FXML
    private Button btnIngresar;

    @FXML
    private Button btnRegistrarse;

    @FXML
    private TextField textContraseña;

    @FXML
    private TextField textUsuario;
    @FXML
    void clickIngresar(ActionEvent event) throws IOException {
        String usuario = textUsuario.getText();
        String contra = textContraseña.getText();
        Usuario user = modelFactory.login(usuario, contra);
        if (user instanceof Vendedor) {
            JOptionPane.showMessageDialog(null, "Bienvenido Vendedor "+ user.getNombre());
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/co/edu/uniquindio/marketpruebas/vendedor-dashboard.fxml"));
            Scene scene = new Scene(fxmlLoader.load(),723,525);
            Stage stage = new Stage();
            VendedorDashboardController controller = fxmlLoader.getController();
            controller.inicializarDashboard(modelFactory,(Vendedor)user);
            stage.setScene(scene);
            stage.show();

        } else if (user instanceof Administrador) {
            JOptionPane.showMessageDialog(null, "Bienvenido Administrador "+ user.getNombre());
        }else{
            JOptionPane.showMessageDialog(null, "Este usuario no existe");
            textUsuario.setText("");
            textContraseña.setText("");
        }
    }

    @FXML
    void clickRegistrarse(ActionEvent event) {
        JOptionPane.showMessageDialog(null, "Aun estamos trabajandon en esto");
    }
    @FXML
    void clickUsuario(ActionEvent event) {

    }
    @FXML
    void clickContra(ActionEvent event) {

    }

    public ModelFactory getModelFactory() {
        return modelFactory;
    }

    public void setModelFactory(ModelFactory modelFactory) {
        this.modelFactory = modelFactory;
    }
}

