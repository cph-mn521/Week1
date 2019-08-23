/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package evil.inc.rest1;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import entity.animal;
import entity.customer;
import fac.facade;
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
import javax.ws.rs.core.MediaType;

/**
 * REST Web Service
 *
 * @author Martin
 */
@Path("animal")
public class GenericResource {

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of GenericResource
     */
    public GenericResource() {
    }
    private static EntityManagerFactory emf
            = Persistence.createEntityManagerFactory("pu");
    private static facade CF = facade.getfacade(emf);

    private final Gson gson = new GsonBuilder().setPrettyPrinting().create();

    /**
     * Retrieves representation of an instance of evil.inc.rest1.GenericResource
     *
     * @return an instance of java.lang.String
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getXml() {
        return "{\"msg\":\"This is the Rest point to get info from.\"}";
    }

    @Path("get4")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String get4() {
        animal[] A = new animal[4];
        A[0] = new animal("Duck", "Quack", 0);
        A[1] = new animal("Dog", "Bork", 0);
        A[2] = new animal("Dragon", "RAAAAAAAAAUUWUWURURRRRRF ild ild farligt farligt.", -1000000000);
        A[3] = new animal("Snake", "Hisssss", 0);
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        return gson.toJson(A);
    }

    @Path("getData")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String get() {
        List<customer> L = CF.getAll();
        return gson.toJson(L);
    }

    /**
     * PUT method for updating or creating an instance of GenericResource
     *
     * @param content representation for the resource
     */
    @PUT
    @Consumes(MediaType.APPLICATION_XML)
    public void putXml(String content) {
    }
}
