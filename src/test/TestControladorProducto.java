
import Controlador.Clases.Fabrica;
import Controlador.Clases.IControladorProductos;
import Controlador.Clases.IControladorUsuarios;
import Controlador.Clases.ManejadorCategorias;
import Controlador.Clases.ManejadorEspProductos;
import Controlador.Clases.ManejadorProductos;
import Controlador.Clases.ManejadorUsuarios;
import Controlador.DataTypes.DataCategoria;
import Controlador.DataTypes.DataCliente;
import Controlador.DataTypes.DataEspecificacionProducto;
import Controlador.DataTypes.DataProducto;
import Controlador.DataTypes.DataProveedor;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import static java.util.Objects.isNull;
import static org.junit.Assert.*;
import org.junit.Test;

/**
 *
 * @author mauro
 */
public class TestControladorProducto {

    @Test
    public void AltaDeCategoriaTest() {
        Integer idProductosControlador = Fabrica.getInstance().getControladorProductos(null).getId();
        IControladorProductos controlarProducto = Fabrica.getInstance().getControladorProductos(idProductosControlador);

        //Crear categoria sin padre
        DataCategoria categoria = new DataCategoria("cat1", null);
        controlarProducto.ingresarDatosCategoria(categoria);

        controlarProducto.guardarCategoria();
        assertTrue(!isNull(ManejadorCategorias.getInstance().obtenerCategorias().get("cat1")));

        //Crear con padre cat1
        DataCategoria categoria2 = new DataCategoria("cat2", categoria);
        controlarProducto.ingresarDatosCategoria(categoria2);

        controlarProducto.guardarCategoria();

        assertTrue(!isNull(ManejadorCategorias.getInstance().obtenerCategorias().get("cat2")));
        assertEquals(ManejadorCategorias.getInstance().obtenerCategorias().get("cat2").getPadre().getNombre(), "cat1");

        //agrego otro padre por las dudas
        DataCategoria categoria3 = new DataCategoria("cat3", categoria2);
        controlarProducto.ingresarDatosCategoria(categoria3);

        controlarProducto.guardarCategoria();

        assertTrue(!isNull(ManejadorCategorias.getInstance().obtenerCategorias().get("cat3")));
        assertEquals(ManejadorCategorias.getInstance().obtenerCategorias().get("cat3").getPadre().getNombre(), "cat2");

    }

    @Test
    public void TestRegistrarProducto() {

        Integer idProductosControlador = Fabrica.getInstance().getControladorProductos(null).getId();
        IControladorProductos controlarProducto = Fabrica.getInstance().getControladorProductos(idProductosControlador);
        Integer idUsuariosControlador = Fabrica.getInstance().getControladorUsuarios(null).getId();
        IControladorUsuarios controlarUsuario = Fabrica.getInstance().getControladorUsuarios(idUsuariosControlador);

        Calendar cal = Calendar.getInstance();
        cal.set(1960, 11, 1);
        DataProveedor proveedor1 = new DataProveedor("pperez", "Pedro", "Perez", "perez@gmail.com", cal, "Pcel", "www.pcel.com");
        controlarUsuario.ingresarDatosProveedor(proveedor1);
        controlarUsuario.guardarUsuario();
        DataEspecificacionProducto espProducto = new DataEspecificacionProducto("nroref1", "prod1", "nuevo", new HashMap(), (float) 2500.0, proveedor1, new ArrayList<>(), new ArrayList<>(), new ArrayList<>());

        //Crear producto id=1 y especificacion espProducto
        DataProducto producto1 = new DataProducto(1, "idSpec", espProducto);

        controlarProducto.ingresarDatosProductos(espProducto);
        controlarProducto.elegirProveedor("pperez");
        controlarProducto.ingresarEspecificacion("estado", "nuevo");
        controlarProducto.ingresarEspecificacion("color", "gris");
        controlarProducto.ingresarEspecificacion("altura", "1 metro");
        DataCategoria categoria = new DataCategoria("cat1", null);
        controlarProducto.ingresarDatosCategoria(categoria);
        controlarProducto.guardarCategoria();
        controlarProducto.agregarCategoriaAEspecificacion("cat1");
        controlarProducto.agregarImagen("img1");
        controlarProducto.ingresarDatosUnidad(producto1);
        if (controlarProducto.controlarErrores()) {
            controlarProducto.guardarProducto();
        }

        assertTrue(!isNull(controlarProducto.getId()));
        assertTrue(!isNull(controlarProducto.controlarErrores()));
        assertTrue(!isNull(controlarProducto.elegirTipoProducto()));
        assertEquals (ManejadorEspProductos.getInstance().getEspecificacionProducto("nroref1").getListaProductos().get(1).getIdEspecifico(), "idSpec");
        assertEquals (ManejadorEspProductos.getInstance().getEspecificacionProducto("nroref1").getListaProductos().get(1).getEspecificacionProducto().getDescripcion(), "nuevo");
        assertEquals (ManejadorEspProductos.getInstance().getEspecificacionProducto("nroref1").getListaProductos().get(1).getEspecificacionProducto().getNombre(), "prod1");
        assertEquals (ManejadorEspProductos.getInstance().getEspecificacionProducto("nroref1").getListaProductos().get(1).getEspecificacionProducto().getNroReferencia(), "nroref1");
        assertTrue ((ManejadorEspProductos.getInstance().getEspecificacionProducto("nroref1").getListaProductos().get(1).getEspecificacionProducto().getPrecio() == (float) 2500.0) );
        assertEquals (ManejadorEspProductos.getInstance().getEspecificacionProducto("nroref1").getListaProductos().get(1).getEspecificacionProducto().getDataProveedor().getLinkSitio(), "www.pcel.com");
        assertEquals (ManejadorEspProductos.getInstance().getEspecificacionProducto("nroref1").getListaProductos().get(1).getEspecificacionProducto().getDataProveedor().getNombreCompania(), "Pcel");
        assertEquals (ManejadorEspProductos.getInstance().getEspecificacionProducto("nroref1").getListaProductos().get(1).getEspecificacionProducto().getDataProveedor().getNombre(), "Pedro");
        assertEquals (ManejadorEspProductos.getInstance().getEspecificacionProducto("nroref1").getListaProductos().get(1).getEspecificacionProducto().getDataProveedor().getApellido(), "Perez");
        assertEquals (ManejadorEspProductos.getInstance().getEspecificacionProducto("nroref1").getListaProductos().get(1).getEspecificacionProducto().getDataProveedor().getNickname(), "pperez");
        Date fecha = new Date(1990, 03, 02);
        assertEquals (ManejadorEspProductos.getInstance().getEspecificacionProducto("nroref1").getListaProductos().get(1).getEspecificacionProducto().getDataProveedor().getFechaNacimiento(), fecha);
        
                
    }   

    @Test
    public void TestVerInformaciondeProducto() {

        Integer idUsuariosControlador = Fabrica.getInstance().getControladorUsuarios(null).getId();
        IControladorUsuarios controlarUsuario = Fabrica.getInstance().getControladorUsuarios(idUsuariosControlador);
        Integer idProductosControlador = Fabrica.getInstance().getControladorProductos(null).getId();
        IControladorProductos controlarProducto = Fabrica.getInstance().getControladorProductos(idProductosControlador);
        
        Calendar cal = Calendar.getInstance();
        cal.set(1960, 11, 1);
        controlarUsuario.ingresarDatosProveedor(new DataProveedor("gclaud", "Gallo", "Claudio", "gclaudio@mail.com", cal, "Alpiste", "www.alpiste.com"));
        controlarUsuario.guardarUsuario();

        DataCategoria cat1 = new DataCategoria("cat1", null);
        controlarProducto.ingresarDatosCategoria(cat1);
        controlarProducto.guardarCategoria();

        controlarProducto.elegirProveedor("gclaud");
        DataProveedor proveedor = new DataProveedor(ManejadorUsuarios.getInstance().getProveedor("gclaud"));
        DataEspecificacionProducto espProducto = new DataEspecificacionProducto("prod1", "Producto1", "descripcion 1", new HashMap(), (float) 12.0, proveedor, new ArrayList<>(), new ArrayList<>(), new ArrayList<>());
        controlarProducto.ingresarDatosProductos(espProducto);
        controlarProducto.ingresarEspecificacion("estado", "nuevo");
        controlarProducto.ingresarEspecificacion("color", "gris");
        DataProducto prodUnidad = new DataProducto(11, "idesp1", espProducto);
        controlarProducto.ingresarDatosUnidad(prodUnidad);
        controlarProducto.agregarCategoriaAEspecificacion("cat1");
        controlarProducto.agregarImagen("img");
        controlarProducto.guardarProducto();
        
        assertTrue(!isNull(controlarProducto.controlarErrores()));
        assertTrue (!isNull (ManejadorProductos.getInstance().obtenerProductos())); 
         assertEquals (ManejadorEspProductos.getInstance().getEspecificacionProducto("prod1").getListaProductos().get(11).getIdEspecifico(), "idesp1");
        assertEquals (ManejadorEspProductos.getInstance().getEspecificacionProducto("prod1").getListaProductos().get(11).getEspecificacionProducto().getDescripcion(), "descripcion 1");
        assertEquals (ManejadorEspProductos.getInstance().getEspecificacionProducto("prod1").getListaProductos().get(11).getEspecificacionProducto().getNombre(), "Producto1");
        assertEquals (ManejadorEspProductos.getInstance().getEspecificacionProducto("prod1").getListaProductos().get(11).getEspecificacionProducto().getNroReferencia(), "prod1");
        assertTrue ((ManejadorEspProductos.getInstance().getEspecificacionProducto("prod1").getListaProductos().get(11).getEspecificacionProducto().getPrecio() == (float) 12.0) );
        assertEquals (ManejadorEspProductos.getInstance().getEspecificacionProducto("prod1").getListaProductos().get(11).getEspecificacionProducto().getDataProveedor().getLinkSitio(), "www.alpiste.com");
        assertEquals (ManejadorEspProductos.getInstance().getEspecificacionProducto("prod1").getListaProductos().get(11).getEspecificacionProducto().getDataProveedor().getNombreCompania(), "Alpiste");
        assertEquals (ManejadorEspProductos.getInstance().getEspecificacionProducto("prod1").getListaProductos().get(11).getEspecificacionProducto().getDataProveedor().getNombre(), "Gallo");
        assertEquals (ManejadorEspProductos.getInstance().getEspecificacionProducto("prod1").getListaProductos().get(11).getEspecificacionProducto().getDataProveedor().getApellido(), "Claudio");
        assertEquals (ManejadorEspProductos.getInstance().getEspecificacionProducto("prod1").getListaProductos().get(11).getEspecificacionProducto().getDataProveedor().getNickname(), "gclaud");
        Date fecha = new Date(1981, 06, 07);
        assertEquals (ManejadorEspProductos.getInstance().getEspecificacionProducto("prod1").getListaProductos().get(11).getEspecificacionProducto().getDataProveedor().getFechaNacimiento(), fecha);
    }
    
    public void TestModificarProducto() {
        
        Integer idUsuariosControlador = Fabrica.getInstance().getControladorUsuarios(null).getId();
        IControladorUsuarios controlarUsuario = Fabrica.getInstance().getControladorUsuarios(idUsuariosControlador);
        Integer idProductosControlador = Fabrica.getInstance().getControladorProductos(null).getId();
        IControladorProductos controlarProducto = Fabrica.getInstance().getControladorProductos(idProductosControlador);
        
        Calendar cal = Calendar.getInstance();
        cal.set(1960, 11, 1);
        controlarUsuario.ingresarDatosProveedor(new DataProveedor("gclaud", "Gallo", "Claudio", "gclaudio@mail.com", cal, "Alpiste", "www.alpiste.com"));
        controlarUsuario.guardarUsuario();

        DataCategoria cat1 = new DataCategoria("cat1", null);
        controlarProducto.ingresarDatosCategoria(cat1);
        controlarProducto.guardarCategoria();

        controlarProducto.elegirProveedor("gclaud");
        DataProveedor proveedor = new DataProveedor(ManejadorUsuarios.getInstance().getProveedor("gclaud"));
        DataEspecificacionProducto espProducto = new DataEspecificacionProducto("prod1", "Producto1", "descripcion 1", new HashMap(), (float) 12.0, proveedor, new ArrayList<>(), new ArrayList<>(), new ArrayList<>());
        controlarProducto.ingresarDatosProductos(espProducto);
        controlarProducto.ingresarEspecificacion("estado", "nuevo");
        controlarProducto.ingresarEspecificacion("color", "gris");
        DataProducto prodUnidad = new DataProducto(5, "idesp1", espProducto);
        controlarProducto.ingresarDatosUnidad(prodUnidad);
        controlarProducto.agregarCategoriaAEspecificacion("cat1");
        controlarProducto.agregarImagen("img");
        controlarProducto.guardarProducto();        
        
        //Modificar Producto

        DataEspecificacionProducto espProducto2 = new DataEspecificacionProducto("prod1", "Cambio", "nuevo", new HashMap(), (float) 2500.0, proveedor, new ArrayList<String>(), new ArrayList<DataCategoria>(), new ArrayList<>());

        controlarProducto.modificarDatosEspecificacionProducto(espProducto2);       

        if(controlarProducto.validarInfo())
            controlarProducto.guardarEspProductoModificado();
        
        assertTrue (ManejadorEspProductos.getInstance().getEspecificacionProducto("prod1").getListaProductos().get(5).getEspecificacionProducto().getPrecio() == (float) 2500); 
        assertEquals (ManejadorEspProductos.getInstance().getEspecificacionProducto("prod1").getListaProductos().get(5).getEspecificacionProducto().getDescripcion(), "nuevo");
        assertEquals (ManejadorEspProductos.getInstance().getEspecificacionProducto("prod1").getListaProductos().get(5).getEspecificacionProducto().getNombre(), "Cambio");
        assertEquals (ManejadorEspProductos.getInstance().getEspecificacionProducto("prod1").getListaProductos().get(5).getEspecificacionProducto().getNroReferencia(), "prod1");
    }

}
