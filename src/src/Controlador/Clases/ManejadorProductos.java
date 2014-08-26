package Controlador.Clases;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class ManejadorProductos {
    
    private static ManejadorProductos instance = null;
    Map<Integer,Producto> productos = Collections.synchronizedMap(new HashMap());
    
    public static ManejadorProductos getInstance(){
        if(ManejadorProductos.instance == null){
            
            ManejadorProductos.instance = new ManejadorProductos();
        }
        return ManejadorProductos.instance;
    }
    
    private ManejadorProductos(){
    
    }
    
    public void agregarProducto(Integer key, Producto producto){
        productos.put(key, producto);
    }
    
    public Map<Integer,Producto> obtenerProductos(){
        return productos;
    }
    
}