package Controlador.DataTypes;

import Controlador.Clases.Usuario;
import java.util.Date;

public class DataProovedor extends DataUsuario{
    
    private String nombreCompania;
    private String linkSitio;
    
    public DataProovedor(Usuario u) {
        super(u);
    }
    
    public DataProovedor(String nombre_compania, String link_sitio, String nickname, String nombre, String apellido, String email, Date fechaNacimiento) {
        super(nickname, nombre, apellido, email, fechaNacimiento);
        this.nombreCompania = nombre_compania;
        this.linkSitio = link_sitio;
    }

    public String getNombreCompania() {
        return nombreCompania;
    }

    public void setNombreCompania(String nombreCompania) {
        this.nombreCompania = nombreCompania;
    }

    public String getLinkSitio() {
        return linkSitio;
    }

    public void setLinkSitio(String linkSitio) {
        this.linkSitio = linkSitio;
    }
    
}
