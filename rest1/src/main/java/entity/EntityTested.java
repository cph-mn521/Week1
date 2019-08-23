/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author Martin
 */
public class EntityTested {

    public static void main(String[] args) {
        EntityManagerFactory emf
                = Persistence.createEntityManagerFactory("pu");
        EntityManager em
                = emf.createEntityManager();
        customer c1 = new customer("1", "2");
        customer c2 = new customer("lars", "2");
        customer c3 = new customer("bo", "2");
        customer c4 = new customer("ko", "2");
        customer c5 = new customer("rsdfjkl", "2");
        try {
            em.getTransaction().begin();
            em.persist(c1);
            em.persist(c2);
            em.persist(c3);
            em.persist(c4);
            em.persist(c5);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }
}
