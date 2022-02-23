
package com.albares.clothes.api;

import com.albares.clothes.db.Product;
import com.albares.clothes.utils.Db;
import com.albares.clothes.utils.Response;
import com.albares.clothes.utils.ResponseCodes;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import java.sql.SQLException;
import java.util.List;

@Path("/user")
public class UserService {
    
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response buyProduct(Product product){
        Response response = new Response();
        try{
            Db myDb = new Db();
            myDb.connect();
            response.setResponseCode(
                    product.buyProduct_DB(myDb)
                );
            myDb.disconnect();
            return response;
        }catch(SQLException e){
            response.setResponseCode(ResponseCodes.ERROR);
            return response;
        }
    }    
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/{gender}")
    public Response getProducts(@PathParam("gender") int gender){
        Response response = new Response();
        try{
            Db myDb = new Db();
            myDb.connect();
            List<Product> products = Product.selectAllProductsGender_DB(myDb,gender);
            myDb.disconnect();
            response.setProducts(products);
            response.setResponseCode(ResponseCodes.OK);
            return response;
        }catch(SQLException e){
            response.setResponseCode(ResponseCodes.ERROR);
            return response;
        }
    }    
    
    
    
    
    
    
    
    
    
    
    
  
}







