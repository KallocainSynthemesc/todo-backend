/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kilian.todo.dao.jpa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author Kilian
 */
public class EntityManagerSingleton {
    
    private final static EntityManagerSingleton INSTANCE = new EntityManagerSingleton();

    public static EntityManagerSingleton getInstance() {
        return INSTANCE;
    }

    private EntityManager entityManager = null;
    
    public EntityManagerSingleton() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("todo");
        this.entityManager = emf.createEntityManager();
    }

    public EntityManager getEntityManager() {
        return this.entityManager;
    }

    public void closeEntityManager() {
        if (this.entityManager.isOpen()) {
            this.entityManager.close();            
        }
    }
}
