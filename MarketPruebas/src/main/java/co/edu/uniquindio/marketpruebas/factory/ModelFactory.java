package co.edu.uniquindio.marketpruebas.factory;

import co.edu.uniquindio.marketpruebas.mapping.dto.UsuarioDto;
import co.edu.uniquindio.marketpruebas.mapping.mappers.MarketPlaceMappingImpl;
import co.edu.uniquindio.marketpruebas.model.*;
import co.edu.uniquindio.marketpruebas.services.IModelFactoryService;

import javax.swing.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Arrays;
import java.util.List;

public class ModelFactory implements IModelFactoryService {
    private static ModelFactory instance;
    private static MarketPlace marketPlace;
    private static MarketPlaceMappingImpl mapping;

    private ModelFactory() {
        inicializarDatos();
        mapping = new MarketPlaceMappingImpl();
    }
    public static ModelFactory getInstance() {
        if (instance == null) {
            instance = new ModelFactory();
        }
        return instance;
    }

    @Override
    public UsuarioDto getUsuario(UsuarioDto usuario) {
        if(validarLogin(usuario)){
            return mapping.usuarioToUsuarioDto(marketPlace.getUsuario(usuario.getUsuario(), usuario.getPassword()));
        }
        return null;
    }

    @Override
    public boolean validarLogin(UsuarioDto usuario) {
        if (marketPlace.verificarUsuario(usuario.getUsuario(), usuario.getPassword())) {
            return true;
        }
        return false;
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
        Producto producto1 = new Producto("Mazda carro", "/co/edu/uniquindio/marketpruebas/imagenes/Mazda.jpeg", "Vehiculos usados", Estado.PUBLICADO, 40000000);
        Producto producto2 = new Producto("Nintendo Switch", "/co/edu/uniquindio/marketpruebas/imagenes/Nintendo-Switch.jpg","Consolas de video",Estado.PUBLICADO, 500000);
        Producto producto3 = new Producto("Closet de dos puertas", "/co/edu/uniquindio/marketpruebas/imagenes/Closet-dos.png","Muebles para el hogar",Estado.PUBLICADO, 450000);
        Producto producto4 = new Producto("Iphone 25", "/co/edu/uniquindio/marketpruebas/imagenes/iphone 25.jpeg","Celulares",Estado.PUBLICADO, 450000);
        Publicacion publicacion = new Publicacion(LocalDate.now(), LocalTime.now(), producto1,"Flamante vehiculo mazda dos dias de uso, mas informacion al interno");
        Publicacion publicacion2 = new Publicacion(LocalDate.now().plusDays(1),LocalTime.now(),producto2,"Espectacular consola de video, nintendo porfavor no me demandes, mas informacion al interno");
        Publicacion publicacion3 = new Publicacion(LocalDate.now().plusDays(2),LocalTime.now(),producto3,"Closet de dos puertas, de 3 metros de alto y 1,5 de ancho, mdf, mas info al interno");
        Publicacion publicacion4 = new Publicacion(LocalDate.now().plusDays(3),LocalTime.now(),producto4,"Unico en el mundo, iphone 25, extraido de los laboratorios de apple, promosion del 10% al primer interesado, precio real: 1 billon de dolars");
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
        Comentario comentario = new Comentario(vendedor2,LocalDate.now(),LocalTime.now(),"JAJAJAJAJ el parcerito mas alucin");
        Comentario comentario2 = new Comentario(vendedor2,LocalDate.now().plusDays(2),LocalTime.now(),"Esta feo");
        publicacion4.agregarComentario(comentario);
        publicacion.agregarComentario(comentario2);
        Muro muro = new Muro();
        muro.agregarPublicacion(publicacion);
        muro.agregarPublicacion(publicacion2);
        muro.agregarPublicacion(publicacion3);
        muro.agregarPublicacion(publicacion4);
        vendedor1.setMuro(muro);
        vendedor1.agregarProducto(producto1);
        vendedor1.agregarProducto(producto2);
        vendedor1.agregarProducto(producto3);
        vendedor1.agregarProducto(producto4);
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
