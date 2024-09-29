package co.edu.uniquindio.marketpruebas.factory;

import co.edu.uniquindio.marketpruebas.model.Administrador;
import co.edu.uniquindio.marketpruebas.model.MarketPlace;
import co.edu.uniquindio.marketpruebas.model.Usuario;
import co.edu.uniquindio.marketpruebas.model.Vendedor;

import java.util.Arrays;
import java.util.List;

public class ModelFactory {
    private static ModelFactory instance;
    private static MarketPlace marketPlace;

    private ModelFactory() {
        inicializarDatos();
    }
    public static ModelFactory getInstance() {
        if (instance == null) {
            instance = new ModelFactory();
        }
        return instance;
    }
    public static Usuario login(String usuario, String password) {
        for(Usuario usuario1 : marketPlace.getListaUsuarios()){
            if (usuario1.getUsuario().equals(usuario) && usuario1.getPassword().equals(password)){
                return usuario1;
            }
        }
        return null;
    }
    /**
     * INICIALIZACION DE DATOS
     */
    private static void inicializarDatos() {
        MarketPlace marketPlace1 = new MarketPlace();
        Vendedor vendedor1 = new Vendedor("Pepe", "Bermuda", "00001", "Alli al lado", "pepito07", "1234", "01");
        Vendedor vendedor2 = new Vendedor("Gustava", "Santos", "00002", "Alli al otro lado", "Pollos08", "12345", "02");
        Vendedor vendedor3 = new Vendedor("Martin", "Santos", "00003", "Alli", "user01", "00001", "03");
        Vendedor vendedor4 = new Vendedor("Pedro", "Martinez", "00004", "Alli", "user02", "00002", "04");
        Vendedor vendedor5 = new Vendedor("vendedor5","apellido5","00005","Alli","user05","00005","05");
        Vendedor vendedor6 = new Vendedor("vendedor6","apellido6","00006","Alli","user06","00006","06");
        Vendedor vendedor7 = new Vendedor("vendedor7","apellido7","00007","Alli","user07","00007","07");
        Vendedor vendedor8 = new Vendedor("vendedor8", "apellido8","00008","Alli","user08","00008","08");
        Vendedor vendedor9 = new Vendedor("vendedor9","apellido9","00009","Alli","user09","00009","09");
        Vendedor vendedor10 = new Vendedor("vendedor10", "apellido10","000010","Alli","user10","000010","10");
        Vendedor vendedor11 = new Vendedor("vendedor11","apellido11","000011","Alli","user11","000011","11");
        Administrador admin = new Administrador("Admin", "admin", "00000", "desconocida", "admin", "admin","00");
        marketPlace1.agregarContactosEntreSi(vendedor1,vendedor2);
        marketPlace1.agregarContactosEntreSi(vendedor1,vendedor3);
        marketPlace1.agregarContactosEntreSi(vendedor1,vendedor4);
        marketPlace1.agregarContactosEntreSi(vendedor1,vendedor5);
        marketPlace1.agregarContactosEntreSi(vendedor1,vendedor6);
        marketPlace1.agregarContactosEntreSi(vendedor1,vendedor7);
        marketPlace1.agregarContactosEntreSi(vendedor1,vendedor8);
        marketPlace1.agregarContactosEntreSi(vendedor1,vendedor9);
        marketPlace1.agregarContactosEntreSi(vendedor1,vendedor10);
        marketPlace1.agregarContactosEntreSi(vendedor1,vendedor11);
        List<Object> parametros = Arrays.asList(vendedor1,vendedor2,admin);
        parametros.forEach(marketPlace1::agregarAutomatico);
        marketPlace = marketPlace1;
    }
}
