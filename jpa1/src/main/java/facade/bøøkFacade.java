/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facade;

import entity.Bøøk;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;

/**
 *
 * @author Martin
 */
public class bøøkFacade {

    private static EntityManagerFactory emf;
    private static bøøkFacade instance;

    private bøøkFacade() {
    }

    public static bøøkFacade getBøøkFacade(EntityManagerFactory _emf) {
        if (instance == null) {
            emf = _emf;
            instance = new bøøkFacade();
        }
        return instance;
    }

    public Bøøk addBøøk(String author) {
        Bøøk book = new Bøøk(author);
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(book);
            em.getTransaction().commit();
            return book;
        } finally {
            em.close();
        }
    }

    public Bøøk findBook(int id) {
        EntityManager em = emf.createEntityManager();
        try {
            Bøøk book = em.find(Bøøk.class, id);
            return book;
        } finally {
            em.close();
        }
    }

    public List<Bøøk> getAllBøøks() {
        EntityManager em = emf.createEntityManager();
        try {
            TypedQuery<Bøøk> query
                    = em.createQuery("Select bøøk from Bøøk bøøk", Bøøk.class);
            return query.getResultList();
        } finally {
            em.close();
        }
    }
}
