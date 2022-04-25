package edu.upc.dsa.models;

import java.util.HashMap;

public class Pedido {
    private String id;
    private HashMap<Integer,String> listaProductos = null; //cada producto con su cantidad
    private String userId; //usuari que ha fet la comanda

    public Pedido() {}

    public Pedido(String id) {
        this.id = id;
        this.listaProductos = new HashMap<Integer,String>();
    }

    public Pedido(String id, String userId) {
        this.id = id;
        this.listaProductos = new HashMap<Integer,String>();
        this.userId = userId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserId() { return userId; }

    public void setUser(String userId) {this.userId = userId; }

    public HashMap<Integer,String> getPedido()  {return listaProductos; }

    public void setPedido(HashMap<Integer,String> pedido) { this.listaProductos = pedido; }

    public void addProduct(int cantidad, String nombre) {
        this.listaProductos.put(cantidad,nombre);
    }
}
