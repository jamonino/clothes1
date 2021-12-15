
package com.albares.clothes.api;

import com.albares.clothes.db.Product;
import com.albares.clothes.utils.Db;
import com.albares.clothes.utils.Response;
import com.albares.clothes.utils.ResponseCodes;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

@Path("/admin")
public class Admin {
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addProduct(Product product){  
        try{
            Db myDb = new Db();

            myDb.connect();
            
            product.insertAndGetId_DB(myDb);
            
            myDb.disconnect();
            
            Response r = new Response();
            r.setResponseCode(ResponseCodes.OK);
            return r;   
        }catch(Exception e){
            Response r = new Response();
            r.setResponseCode(ResponseCodes.ERROR);
            return r;   
        }
    }    
}

