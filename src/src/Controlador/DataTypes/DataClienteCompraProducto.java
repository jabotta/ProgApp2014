package Controlador.DataTypes;

import Controlador.Clases.ClienteCompraProducto;

public class DataClienteCompraProducto {
    
    private DataCliente cliente;
    private DataProducto producto;
    private Float precio;
    
    public DataClienteCompraProducto(ClienteCompraProducto cp) {
        this.cliente = cp.getDataCliente();
        this.producto = cp.getDataProducto();
        this.precio = cp.getPrecio();
    }
    
    public DataClienteCompraProducto(DataCliente cliente, DataProducto producto, Float precio) {
        this.cliente = cliente;
        this.producto = producto;
        this.precio = precio;
    }

    public DataCliente getCliente() {
        return cliente;
    }
    
    public void setCliente(DataCliente cliente) {
        this.cliente = cliente;
    }
    
    public DataProducto getProducto() {
        return producto;
    }

    public void setProducto(DataProducto producto) {
        this.producto = producto;
    }
    
    public Float getPrecio() {
        return precio;
    }

    public void setPrecio(Float precio) {
        this.precio = precio;
    }
    
    @Override
    public String toString() {
        return this.getCliente() + "  --  " + this.getProducto() + "  --  " + this.getPrecio() ;
    }
    
}
