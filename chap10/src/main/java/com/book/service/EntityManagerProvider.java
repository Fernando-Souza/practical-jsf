
package com.book.service;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.context.RequestScoped;
import jakarta.enterprise.inject.Produces;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import java.io.Serializable;

@ApplicationScoped
public class EntityManagerProvider implements Serializable {
    
   
    private EntityManagerFactory emf=null;

    public EntityManagerProvider() {
        
        if(emf== null){
            
             emf = Persistence.createEntityManagerFactory("BooksPU");
        }
    }
    
    
    
    @Produces
    @RequestScoped
    public EntityManager getEntityManager(){
        
        return emf.createEntityManager();
    }
    
}
