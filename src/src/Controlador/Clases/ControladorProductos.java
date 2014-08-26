package Controlador.Clases;

import Controlador.DataTypes.DataCategoria;
import Controlador.DataTypes.DataEspecificacionProducto;
import Controlador.DataTypes.DataProducto;
import Controlador.DataTypes.DataProveedor;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class ControladorProductos implements IControladorProductos{
    
    private Integer id;
    private EspecificacionProducto espProductoModificada;
    private Proveedor proveedorElegido;
    private EspecificacionProducto espProdElegido;
    private ArrayList<String> especificaciones;
    private EspecificacionProducto nuevoProducto;
    private Categoria nuevaCategoria;
    private Map<String,Proveedor> prvList = Collections.synchronizedMap(new HashMap());
    
    //    - prvLst Set<Proveedor>
//    - espLst : map<string,string>
//    - prdList : Productos
//    - lstCategorias  : Set<Categoria>
    
    @Override
    public Integer getId(){
        return this.id;
    }
    
    @Override
    public void setId(Integer id){
        this.id = id;
    }
    
    @Override
    public ArrayList<DataProveedor> listarProveedores(){
        prvList = ManejadorUsuarios.getInstance().obtenerProveedores();
        ArrayList<DataProveedor> result = new ArrayList<DataProveedor>();
        prvList.keySet().stream().forEach((p) -> {
            result.add(new DataProveedor(prvList.get(p)));
        });
        return result;
    }
    
    @Override
    public void elegirProveedor(String nickname){
        proveedorElegido = prvList.get(nickname);
    }
    
    @Override
    public void ingresarDatosProductos(DataEspecificacionProducto espProducto){
        nuevoProducto = new EspecificacionProducto(espProducto,proveedorElegido);
    }
    
    @Override
    public Boolean elegirTipoProducto(){
        return true;
    }
    
    @Override
    public void agregarMultiplesProductosAutogenerados(Integer cantidad){
        
    }
    
    @Override
    public void ingresarDatosUnidad(DataProducto producto){
        
    }
    
    @Override
    public ArrayList<DataCategoria> listarCategorias(){
        return null;
    }
    
    @Override
    public void elegirCategoria(String categoria){
        
    }
    
    @Override
    public void listarImagenesDisco(){
        
    }
    
    @Override
    public void elegirImagen(String rutaImagen){
        
    }
    
    @Override
    public Boolean controlarErrores(){
        return true;
    }
    
    @Override
    public void guardarProducto(){
        
    }
    
    @Override
    public void ingresarDatosCategoria(DataCategoria categoria){
        
    }
    
    //@Override
//    public Boolean tienePadre(){
//        return true;
//    }
    
    @Override
    public void asociarCategoriaPadre(){
        
    }
    
    @Override
    public void guardarCategoria(){
        
    }
    
    @Override
    public void elegirProducto(Integer id){
        
    }
    
    @Override
    public DataProducto mostrarInformacionProducto(){
        return null;
    }
    
    @Override
    public DataEspecificacionProducto listarEspecificacionProductos(){
        return null;
    }
    
    @Override
    public void modificarDatosEspecificacionProducto(DataEspecificacionProducto espProducto){
        
    }
    
    @Override
    public void agregarImagen(String rutaImagen){
        
    }
    
    @Override
    public void agregarCategoria(DataCategoria categoria){
        
    }
    
    @Override
    public Boolean validarInfo(){
        return true;
    }
    
}
