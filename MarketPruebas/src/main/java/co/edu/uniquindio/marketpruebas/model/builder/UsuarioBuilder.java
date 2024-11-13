package co.edu.uniquindio.marketpruebas.model.builder;

import co.edu.uniquindio.marketpruebas.model.Usuario;

public class UsuarioBuilder <T extends UsuarioBuilder<T>>{
    protected String nombre;
    protected String apellido;
    protected String cedula;
    protected String direccion;
    protected String usuario;
    protected String password;

    public Usuario build(){
        return new Usuario(nombre, apellido, cedula, direccion, usuario, password);
    }
    @SuppressWarnings("unchecked")
    protected T self(){
        return (T) this;
    }
    public T nombre(String nombre){
        this.nombre = nombre;
        return self();
    }
    public T apellido(String apellido){
        this.apellido = apellido;
        return self();
    }
    public T cedula(String cedula){
        this.cedula = cedula;
        return self();
    }
    public T direccion(String direccion){
        this.direccion = direccion;
        return self();
    }
    public T usuario(String usuario){
        this.usuario = usuario;
        return self();
    }
    public T password(String password){
        this.password = password;
        return self();
    }

}
