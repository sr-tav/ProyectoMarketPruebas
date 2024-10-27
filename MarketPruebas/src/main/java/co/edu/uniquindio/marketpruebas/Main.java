package co.edu.uniquindio.marketpruebas;

import co.edu.uniquindio.marketpruebas.viewcontroller.LoginViewController;
import co.edu.uniquindio.marketpruebas.factory.ModelFactory;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        try{

            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/co/edu/uniquindio/marketpruebas/login.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 468, 531);
            stage.setScene(scene);
            stage.show();

        } catch (RuntimeException e) {
            throw new RuntimeException(e);

        }

    }

    public static void main(String[] args) {
        launch(args);
    }
}
