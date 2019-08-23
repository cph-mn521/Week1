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
public class entityrunner {

    public static void main(String[] args) {
        EntityManagerFactory emf
                = Persistence.createEntityManagerFactory("pu");
        EntityManager em
                = emf.createEntityManager();
        Bøøk b1 = new Bøøk("Excel og dig, vejen til fyring.");
        Bøøk b2 = new Bøøk("Niels har ret, og andre classiske eventyr");
        Bøøk b3 = new Bøøk("lør at tøl svønsk med dønne bøøk, møøse");
        System.out.println(b1.toString());
        System.out.println(b2.toString());
        System.out.println(b3.toString());
        try {
            em.getTransaction().begin();
            em.persist(b1);
            em.persist(b2);
            em.persist(b3);
            em.getTransaction().commit();

        } catch (Exception e) {
            em.getTransaction().rollback();
            em.close();
        } finally {
            System.out.println(b1.toString());
            System.out.println(b2.toString());
            System.out.println(b3.toString());
            em.close();
            System.out.println("wooo");
        }

    }
}
