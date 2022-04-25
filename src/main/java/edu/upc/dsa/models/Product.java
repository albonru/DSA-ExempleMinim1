package edu.upc.dsa.models;

public class Product {
    private String nombre;
    private String id;
    private int precio; //en centimos
    private int ventas; //numero de veces que se ha vendido

    public Product() {}

    public Product(String nombre, String id, int precio) {
        this.nombre = nombre;
        this.id = id;
        this.precio = precio;
        this.ventas = 0;
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

    public int getPrecio() { return precio; }

    public void setPrecio(int precio) {
        this.precio = precio;
    }

    public int getVentas() { return ventas; }

    public void setVentas(int ventas) { this.ventas = ventas; }

    public void addVentas(int num) {
        ventas += num;
    }
}
