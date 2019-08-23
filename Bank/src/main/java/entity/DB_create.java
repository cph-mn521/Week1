package entity;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Martin
 */
public class DB_create {

    public static void Create() {
        EntityManagerFactory emf
                = Persistence.createEntityManagerFactory("BANK");
        EntityManager em
                = emf.createEntityManager();
        List<BankCustomer> L = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            L.add(new BankCustomer("navn" + i, "efternavn" + i, "customer info goes here:", 200.00, i));
        }
        try{
            em.getTransaction().begin();
            for (BankCustomer b : L) {
                em.persist(b);
            }
            em.getTransaction().commit();
        }finally{
            em.close();
        }
    }

}
