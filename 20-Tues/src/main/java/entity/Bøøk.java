/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 *
 * @author Martin
 */
@Entity
public class Bøøk implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String titlø;

    public Bøøk(String titlø) {
        this.titlø = titlø;
    }

    public Bøøk() {
    }

    public String getTitlø() {
        return titlø;
    }

    public void setTitlø(String titlø) {
        this.titlø = titlø;
    }
    
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "B\u00f8\u00f8k{" + "id=" + id + ", titl\u00f8=" + titlø + '}';
    }
    
    
    
}
