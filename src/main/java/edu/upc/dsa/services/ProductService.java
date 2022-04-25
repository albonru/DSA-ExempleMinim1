package edu.upc.dsa.services;

import edu.upc.dsa.ProductManager;
import edu.upc.dsa.models.Pedido;
import edu.upc.dsa.models.Product;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

import javax.ws.rs.*;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Api(value = "/Product", description = "Endpoint to Product Service")
@Path("/Pedidos")
public class ProductService {
    private ProductManager manager;

    // GET lista productos segun precio
    @GET
    @ApiOperation(value = "get products by price", notes = "ascendente")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful", response = Product.class, responseContainer="List"),
    })
    @Path("/listaProductosPorPrecio")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getProductsP() {

        List<Product> products = this.manager.listaProductosPrecio();
        GenericEntity<List<Product>> entity = new GenericEntity<List<Product>>(products) {};
        return Response.status(201).entity(entity).build();
    }

    // GET lista productos segun ventas
    @GET
    @ApiOperation(value = "get products by sold", notes = "descendente")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful", response = Product.class, responseContainer="List"),
    })
    @Path("/listaProductosPorVentas")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getProductsV() {

        List<Product> products = this.manager.listaProductosVentas();
        GenericEntity<List<Product>> entity = new GenericEntity<List<Product>>(products) {};
        return Response.status(201).entity(entity).build();
    }

    // GET lista pedidos de un usuario concreto
    @GET
    @ApiOperation(value = "get orders by user", notes = "no")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful", response = Product.class, responseContainer="List"),
    })
    @Path("/listaPedidosSegunUsuario")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getPedidos(String userId) {

        if (userId == null)
            return Response.status(500).entity(userId).build();

        List<Pedido> pedidos = this.manager.listaPedidosUsuario(userId);
        GenericEntity<List<Pedido>> entity = new GenericEntity<List<Pedido>>(pedidos) {};
        return Response.status(201).entity(entity).build();
    }

}
