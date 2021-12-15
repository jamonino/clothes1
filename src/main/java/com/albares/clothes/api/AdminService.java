
package com.albares.clothes.api;

import com.albares.clothes.db.Product;
import com.albares.clothes.utils.Db;
import com.albares.clothes.utils.Response;
import com.albares.clothes.utils.ResponseCodes;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import java.sql.SQLException;
import java.util.List;

@Path("/admin")
public class AdminService {
    
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createProduct(Product product){
        Response response = new Response();
        try{
            Db myDb = new Db();
            myDb.connect();
            product.insertProduct_DB(myDb);
            myDb.disconnect();
            response.setResponseCode(ResponseCodes.OK);
            return response;
        }catch(SQLException e){
            response.setResponseCode(ResponseCodes.ERROR);
            return response;
        }
    }    
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getProducts(){
        Response response = new Response();
        try{
            Db myDb = new Db();
            myDb.connect();
            List<Product> products = Product.selectAllProducts_DB(myDb);
            myDb.disconnect();
            response.setProducts(products);
            response.setResponseCode(ResponseCodes.OK);
            return response;
        }catch(SQLException e){
            response.setResponseCode(ResponseCodes.ERROR);
            return response;
        }
    }    
      
    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response editProduct(Product product){
        Response response = new Response();
        try{
            Db myDb = new Db();
            myDb.connect();
            product.editProduct_DB(myDb);
            myDb.disconnect();
            response.setResponseCode(ResponseCodes.OK);
            return response;
        }catch(SQLException e){
            response.setResponseCode(ResponseCodes.ERROR);
            return response;
        }
    }  
}







