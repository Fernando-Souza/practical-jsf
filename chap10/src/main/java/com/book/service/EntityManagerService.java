
package com.book.service;

import jakarta.enterprise.context.Dependent;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.criteria.CriteriaQuery;
import java.io.Serializable;
import java.util.List;

@Named
@Dependent
public class EntityManagerService<T> implements Serializable {
    
   @Inject
    private EntityManager manager;
    
    public EntityManager getEntityManager() {
        return manager;
    }

    public T create(T entity) {
        
        EntityTransaction transaction= getEntityManager().getTransaction();
        transaction.begin();        
        getEntityManager().persist(entity); 
        transaction.commit();
        
        return entity;
        
    }
    
    public T read(Object id, Class<T> tipo){
        
                
        return getEntityManager().find(tipo, id);
        
    }
    
    public T update(T entity){
        
        return getEntityManager().merge(entity);
        
    }
    
    public void delete(T entity){
        EntityTransaction transaction= getEntityManager().getTransaction();
        transaction.begin();
        getEntityManager().remove(getEntityManager().merge(entity));
        transaction.commit();
    }    
     
    
    public List<T> findAll(Class<T> entityClass){
        
        CriteriaQuery cq = getEntityManager().getCriteriaBuilder().createQuery();
        
        cq.select(cq.from(entityClass));      
        
        return getEntityManager().createQuery(cq).getResultList();
        
    }
    
    public T salvar(T entity){
        
        return getEntityManager().merge(entity);
    }
    
    
    
}
