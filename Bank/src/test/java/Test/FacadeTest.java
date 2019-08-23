/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Test;

import entity.BankCustomer;
import entity.DB_create;
import facade.CustomerFacade;
import java.util.List;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Martin
 */
public class FacadeTest {


    public FacadeTest() {
    }

    @BeforeClass
    public static void setUpClass() {
        DB_create.Create();
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    @Test
    public void testGetAll() {
        EntityManagerFactory emf
                = Persistence.createEntityManagerFactory("BANK");
        CustomerFacade CF = CustomerFacade.getCustomerFacade(emf);
        List<BankCustomer> L = CF.getAllCustomers();
        assertNotNull(L);
    }

    @Test
    public void testGetByID() {
        EntityManagerFactory emf
                = Persistence.createEntityManagerFactory("BANK");
        CustomerFacade CF = CustomerFacade.getCustomerFacade(emf);
        BankCustomer B = CF.getCustomer(1);
        assertNotNull(B);
        // Det er lort fordi jeg mass generater og den gir de forskellige 
        //customers random ID når man batch persister....
        
    }

    @Test
    public void testAddCustomer() {
        EntityManagerFactory emf
                = Persistence.createEntityManagerFactory("BANK");
        CustomerFacade CF = CustomerFacade.getCustomerFacade(emf);
        BankCustomer C = new BankCustomer("karl", "Karlsen", "han er en god dreng", 0.0, 0);
        CF.addCustomer(C);
        CF.addCustomer("KIM", "Kimesen");
        CF.addCustomer("KIM", "Kimesen");
        List<BankCustomer> L = CF.getAllCustomers();
        assertTrue(L.size() == 13);
    }

    @Test
    public void testGetByName() {
        EntityManagerFactory emf
                = Persistence.createEntityManagerFactory("BANK");
        CustomerFacade CF = CustomerFacade.getCustomerFacade(emf);
        List<BankCustomer> L = CF.getCustomersByName("KIM");

        assertTrue(L.size() == 2 && L.get(0).getFirstName().equals("KIM"));
    }
    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}
}
