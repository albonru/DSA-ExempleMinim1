package edu.upc.dsa;

import edu.upc.dsa.models.Pedido;
import edu.upc.dsa.models.Product;
import edu.upc.dsa.models.User;

import java.util.*;
import java.util.logging.Logger;

public class ProductManagerImpl implements ProductManager{

    HashMap<String,User> hashUsuarios = new HashMap<String,User>(); //id con su usuario
    HashMap<String,Product> hashProd = new HashMap<String,Product>(); //nombre con su producto
    HashMap<Integer,Product> hashProductosP = new HashMap<Integer,Product>(); //producto con su precio
    HashMap<Integer,Product> hashProductosV = new HashMap<Integer,Product>(); //producto con su numero de ventas
    LinkedList<Pedido> listaPedidosPendientes= new LinkedList<Pedido>();

    static final Logger logger = Logger.getLogger(ProductManagerImpl.class.getName());
    private static ProductManagerImpl manager;

    //Singleton
    public static ProductManagerImpl getInstance(){
        if(manager==null){
            manager= new ProductManagerImpl();
        }
        return manager;
    }

    public ProductManagerImpl(){}

    @Override
    public List<Product> listaProductosPrecio() {
        List<Product> resultado = new LinkedList<>(hashProductosP.values());

        Collections.sort(resultado, new Comparator<Product>() {
            @Override
            public int compare(Product p1, Product p2) {
                return Integer.compare(p1.getPrecio(), p2.getPrecio());
            }
        });
        logger.info("Lista de productos ordenados por precio (ascendente): " +resultado.toString());
        return resultado;
    }

    @Override
    public List<Product> listaProductosVentas() {
        List<Product> resultado = new LinkedList<>(hashProductosV.values());

        Collections.sort(resultado, new Comparator<Product>() {
            @Override
            public int compare(Product p1, Product p2) {
                return Integer.compare(p1.getVentas(), p2.getVentas());
            }
        });
        Collections.reverse(resultado);
        logger.info("Lista de productos ordenados por cantidad de ventas (descendente): " + resultado.toString());
        return resultado;
    }

    @Override
    public void realizarPedido(String userId, String pedidoId) {
        Pedido pedido = new Pedido(userId, pedidoId);
        listaPedidosPendientes.add(pedido);
    }

    @Override
    public void servirPedido() {
        Pedido pedido = listaPedidosPendientes.getFirst();
        HashMap<Integer,String> p = pedido.getPedido();
        for (Integer i : p.keySet()) {
            Product product = getProductByName(p.get(i));
            product.addVentas(i);
        }

        User user = getUserById(pedido.getUserId());
        user.addPedido(pedido);
    }

    @Override
    public List<Pedido> listaPedidosUsuario(String userId) {
        User user = getUserById(userId);
        List<Pedido> listaPedidos = user.getListaPedidos();
        for (Pedido pedido: listaPedidos) {

        }
        return listaPedidos;
    }

    @Override
    public User getUserById(String userId) {
        User user = this.hashUsuarios.get(userId);
        return user;
    }

    @Override
    public Product getProductByName(String name) {
        Product p = this.hashProd.get(name);
        return p;
    }

    @Override
    public void clear() {
        this.listaPedidosPendientes.clear();
        this.hashProd.clear();
        this.hashProductosP.clear();
        this.hashProductosV.clear();
        this.hashUsuarios.clear();
    }
}
