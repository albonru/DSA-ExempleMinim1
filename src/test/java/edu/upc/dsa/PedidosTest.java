package edu.upc.dsa;

import edu.upc.dsa.models.Pedido;
import edu.upc.dsa.models.Product;
import edu.upc.dsa.models.User;
import org.apache.log4j.Logger;

public class PedidosTest {
    private static Logger logger = Logger.getLogger(PedidosTest.class);
    ProductManager pm = ProductManagerImpl.getInstance();

    public void setUp() {
        Product p1 = new Product("Manzana", "1", 75);
        Product p2 = new Product("Ternera", "2", 700);
        Product p3 = new Product("Noodles", "3", 467);

        User u1 = new User("Alba", "1");
    }

    public void tearDown() { pm.clear(); }

    public void testRealizarPedido() {
        logger.info("Pedidos usuario al INICIO: " + pm.listaPedidosUsuario("1"));
        logger.info("Lista de productos segun cantidad de ventas al INICIO: " + pm.listaProductosVentas());

        Pedido pedido = new Pedido("1");
        pedido.addProduct(4,"Manzana");
        pedido.addProduct(1, "Ternera");
        pedido.addProduct(2,"Noodles");

        pm.realizarPedido("1","1");
    }

    public void testServirPedido() {
        pm.servirPedido();

        logger.info("Pedidos usuario al FINAL: " + pm.listaPedidosUsuario("1"));
        logger.info("Lista de productos segun cantidad de ventas al FINAL: " + pm.listaProductosVentas());
    }
}
