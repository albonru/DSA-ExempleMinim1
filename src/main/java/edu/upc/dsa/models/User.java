package edu.upc.dsa.models;

import edu.upc.dsa.ProductManagerImpl;

import org.apache.log4j.Logger;

import java.util.LinkedList;
import java.util.List;

public class User {
    private String nombre;
    private String id;

    static final Logger logger = Logger.getLogger(ProductManagerImpl.class.getName());
    List<Pedido> listaPedidos = null;

    public User() {}

    public User(String nombre, String id) {
        this.nombre = nombre;
        this.id = id;
        this.listaPedidos = new LinkedList<>();
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<Pedido> getListaPedidos() {
        return listaPedidos;
    }

    public void setListaPedidos(List<Pedido> listaPedidos) {
        this.listaPedidos = listaPedidos;
    }

    public void addPedido(Pedido pedido) {
        listaPedidos.add(pedido);
    }
}
