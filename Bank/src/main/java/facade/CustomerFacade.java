/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facade;

import entity.BankCustomer;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;

/**
 *
 * @author Martin
 */
public class CustomerFacade {

    private static EntityManagerFactory emf;
    private static CustomerFacade instance;

    public static CustomerFacade getCustomerFacade(EntityManagerFactory _emf) {
        if (instance == null) {
            emf = _emf;
            instance = new CustomerFacade();
        }
        return instance;
    }

    public BankCustomer addCustomer(String name, String lastname) {
        BankCustomer newCustomer = new BankCustomer(name, lastname, "", 0.0, 0);
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(newCustomer);
            em.getTransaction().commit();
            return newCustomer;
        } finally {
            em.close();
        }
    }

    public BankCustomer addCustomer(BankCustomer b) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(b);
            em.getTransaction().commit();
            return b;
        } finally {
            em.close();
        }
    }

    public BankCustomer getCustomer(int ID) {
        EntityManager em = emf.createEntityManager();
        try {
            return em.find(BankCustomer.class, ID);
        } finally {
            em.close();
        }
    }

    public List<BankCustomer> getAllCustomers() {
        EntityManager em = emf.createEntityManager();
        try {
            TypedQuery<BankCustomer> query
                    = em.createQuery("select bankcustomer from BankCustomer bankcustomer",
                            BankCustomer.class);
            return query.getResultList();
        } finally {
            em.close();
        }
    }

    public List<BankCustomer> getCustomersByName(String Name) {
        EntityManager em = emf.createEntityManager();
        try {
            TypedQuery<BankCustomer> query
                    = em.createQuery("SELECT bankcustomer FROM BankCustomer bankcustomer "
                            + "WHERE bankcustomer.firstName = :name",BankCustomer.class).setParameter("name", Name);
            return query.getResultList();
        } finally {
            em.close();
        }
    }

}
