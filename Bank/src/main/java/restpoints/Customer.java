/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package restpoints;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import dto.BankCustomerDTO;
import entity.BankCustomer_;
import entity.DB_create;
import facade.CustomerFacade;
import java.util.List;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;

/**
 * REST Web Service
 *
 * @author Martin
 */
@Path("cust")
public class Customer {

    @Context
    private UriInfo context;

    private static EntityManagerFactory emf
            = Persistence.createEntityManagerFactory("BANK");
    private static CustomerFacade CF = CustomerFacade.getCustomerFacade(emf);

    private final Gson gson = new GsonBuilder().setPrettyPrinting().create();

    /**
     * Creates a new instance of api
     */
    public Customer() {
    }

    /**
     * Retrieves representation of an instance of restpoints.api
     *
     * @return an instance of java.lang.String
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String demo() {
        return "{\"msg\":\"This is the Rest point to get customer info from.\"}";
    }

    @Path("all")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getJson() {
        List<entity.BankCustomer> L = CF.getAllCustomers();
        String out = gson.toJson(L);
        return out;
    }

    @Path("ID/{varX}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getByID(@PathParam("varX") String varX) {
        entity.BankCustomer B = CF.getCustomer(Integer.parseInt(varX));
        return gson.toJson(new BankCustomerDTO(B));
    }

    @Path("pub")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String pub(){
        DB_create.Create();
        return "{\"msg\":\"succes!\"}";
    }

    /**
     * PUT method for updating or creating an instance of api
     *
     * @param content representation for the resource
     */
    @PUT
    @Consumes(MediaType.APPLICATION_XML)
    public void putXml(String content) {
    }
}
