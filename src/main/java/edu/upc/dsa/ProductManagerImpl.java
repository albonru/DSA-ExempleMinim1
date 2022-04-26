package edu.upc.dsa;

import edu.upc.dsa.models.Pedido;
import edu.upc.dsa.models.Product;
import edu.upc.dsa.models.User;

import java.util.*;
import java.util.logging.Logger;

public class ProductManagerImpl implements ProductManager{

    HashMap<String,User> hashUsuarios = new HashMap<String,User>(); //id con su usuario
    LinkedList<Product> listaProductos = new LinkedList<Product>();
    Queue<Pedido> cuaPedidosPendientes= new LinkedList<Pedido>();

    static final Logger logger = Logger.getLogger(ProductManagerImpl.class.getName());
    private static ProductManagerImpl manager;

    //Singleton
    public static ProductManagerImpl getInstance(){
        if(manager==null){
            manager= new ProductManagerImpl();
        }
        return manager;
    }

    private ProductManagerImpl(){}

    @Override
    public List<Product> listaProductosPrecio() {
        List<Product> resultado = new LinkedList<>(this.listaProductos);

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
        List<Product> resultado = new LinkedList<>(this.listaProductos);

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
    public void realizarPedido(String userId, Pedido pedido) {
        pedido.setUser(userId);
        this.cuaPedidosPendientes.add(pedido);
    }

    @Override
    public Pedido servirPedido() {
        Pedido pedido = this.cuaPedidosPendientes.poll();
        HashMap<Integer,String> p = pedido.getPedido();
        for (Integer i : p.keySet()) {
            Product product = getProductByName(p.get(i));
            product.addVentas(i);
        }

        User user = getUserById(pedido.getUserId());
        user.addPedido(pedido);
        return pedido;
    }

    @Override
    public List<Pedido> listaPedidosUsuario(String userId) {
        User user = getUserById(userId);
        List<Pedido> listaPedidos = user.getListaPedidos();

        return listaPedidos;
    }

    @Override
    public User getUserById(String userId) {
        User user = this.hashUsuarios.get(userId);
        return user;
    }

    @Override
    public Product getProductByName(String name) {
        for (Product product: this.listaProductos) {
            if (product.getNombre().equals(name)) {
                return product;
            }
        }
        return null;
    }

    public void addUser(String id) {
        User user = new User(id);
        this.hashUsuarios.put(user.getId(), user);
    }

    public void addProductToList(Product p) {
        this.listaProductos.add(p);
    }

    public List<Product> getListaProductos() {
        return this.listaProductos;
    }

    @Override
    public void clear() {
        this.cuaPedidosPendientes.clear();
        this.listaProductos.clear();
        this.hashUsuarios.clear();
    }
}
