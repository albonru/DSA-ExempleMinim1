package edu.upc.dsa;

import edu.upc.dsa.models.Pedido;
import edu.upc.dsa.models.Product;
import edu.upc.dsa.models.User;

import java.util.List;

public interface ProductManager {
    public List<Product> listaProductosPrecio(); //precio ascendente
    public List<Product> listaProductosVentas(); //numero de ventas descendente
    public void realizarPedido(String userId, String pedidoId); //distintos productos, diferentes cantidades
    public void servirPedido(); //en orden de llegada
    public List<Pedido> listaPedidosUsuario(String userId); //pedidos ya realizados

    public User getUserById(String id);
    public Product getProductByName(String name);
    public void clear();
}
