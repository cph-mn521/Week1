/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fac;

import entity.customer;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;

/**
 *
 * @author Martin
 */
public class facade {

    private static EntityManagerFactory emf;
    private static facade instance;

    private facade() {
    }

    public static facade getfacade(EntityManagerFactory _emf) {
        if (instance == null) {
            emf = _emf;
            instance = new facade();
        }
        return instance;
    }

    public customer findById(int ID) {
        EntityManager em = emf.createEntityManager();
        try {
            customer c = em.find(customer.class, ID);
            return c;
        } finally {
            em.close();
        }
    }

    public List<customer> getAll() {
        EntityManager em = emf.createEntityManager();
        try {
            TypedQuery<customer> query
                    = em.createQuery("Select c from customer c", customer.class);
            return query.getResultList();
        } finally {
            em.close();
        }
    }

    public int getNumberOfCustomers() {
        List<customer> l = getAll();
        return l.size();
    }

    public customer add(String fn, String ln) {
        customer nc = new customer(fn, ln);
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(nc);
            em.getTransaction().commit();
            return nc;
        } finally {
            em.close();
        }
    }
    
    public List<customer> findByLastName(String name){
        EntityManager em = emf.createEntityManager();
        try {
            TypedQuery<customer> query
                    = em.createQuery("SELECT bankcustomer FROM customer bankcustomer "
                            + "WHERE bankcustomer.firstname = :name",customer.class).setParameter("name", name);
            return query.getResultList();
        } finally {
            em.close();
        }
    }
}
