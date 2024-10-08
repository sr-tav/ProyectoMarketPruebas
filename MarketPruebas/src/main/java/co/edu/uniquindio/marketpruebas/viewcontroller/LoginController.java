package co.edu.uniquindio.marketpruebas.viewcontroller;

import co.edu.uniquindio.marketpruebas.controller.UsuarioController;
import co.edu.uniquindio.marketpruebas.factory.ModelFactory;
import co.edu.uniquindio.marketpruebas.mapping.dto.AdministradorDto;
import co.edu.uniquindio.marketpruebas.mapping.dto.UsuarioDto;
import co.edu.uniquindio.marketpruebas.mapping.dto.VendedorDto;
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
    private UsuarioController usuarioController;

    @FXML
    private Button btnIngresar;

    @FXML
    private Button btnRegistrarse;

    @FXML
    private TextField textContraseña;

    @FXML
    private TextField textUsuario;

    @FXML
    void initialize(){
        usuarioController = new UsuarioController();
    }

    @FXML
    void clickIngresar(ActionEvent event) throws IOException {
        login();
    }

    public void login() throws IOException {
        UsuarioDto dto = buildUsuarioDto();
        if (usuarioController.validarUsuario(dto)){
            abrirVentana(usuarioController.getUsuario(dto));
        }
    }

    public void abrirVentana(UsuarioDto usuario) throws IOException {
        if (usuario instanceof VendedorDto){
            JOptionPane.showMessageDialog(null, "Bienvenido Vendedor "+ usuario.getNombre());
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/co/edu/uniquindio/marketpruebas/vendedor-dashboard.fxml"));
            Scene scene = new Scene(fxmlLoader.load(),723,525);
            Stage stage = new Stage();
            VendedorDashboardController controller = fxmlLoader.getController();
            controller.inicializarDashboard((VendedorDto)usuario);
            stage.setScene(scene);

            //Cerrar la ventana actual
            Stage stageCerrar = (Stage) btnIngresar.getScene().getWindow();
            stageCerrar.close();

            //Mostrar la nueva ventana
            stage.show();
        }else if (usuario instanceof AdministradorDto){
            JOptionPane.showMessageDialog(null, "Bienvenido Administrador "+ usuario.getNombre());
        }else{
            JOptionPane.showMessageDialog(null, "Este usuario no existe");
        }
    }

    public UsuarioDto buildUsuarioDto(){
        return new UsuarioDto(
                null,
                null,
                null,
                null,
                textUsuario.getText(),
                textContraseña.getText()
        );
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

