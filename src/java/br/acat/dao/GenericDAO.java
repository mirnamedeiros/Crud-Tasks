/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.acat.dao;

import br.acat.jpa.EntityManagerUtil;
import br.acat.util.Util;
import java.util.List;
import javax.persistence.EntityManager;

/**
 *
 * @author Mirna
 */
public class GenericDAO<T> {

    private List<T> listObjs;
    private List<T> listAll;
    protected Class persistenceClass;
    protected String message = "";
    protected EntityManager em;
    protected String order = "id";
    protected String filter = "";
    protected Integer maxObjs = 8;
    protected Integer currentPosition = 0;
    protected Integer totalObjs = 0;

    public GenericDAO() {
        em = EntityManagerUtil.getEntityManager();
    }
    
    public List<T> getListObjs() {
        String jpql = "select t from " + persistenceClass.getSimpleName() +" t";
        String where = "";
        filter = filter.replaceAll("[';-]", "");
        if(filter.length() > 0) {
            if(order.equals("id")) {
                try {
                    Integer.parseInt(filter);
                    where += " where t." + order + " = '" + filter + "' ";
                }
                catch(Exception e) {}
            }
            else {
                where += " where t." + order + " like '%" + filter + "%' ";
            }
        }
        jpql += where;
        jpql += " order by t." + order;
        totalObjs = em.createQuery(jpql).getResultList().size();
        return em.createQuery(jpql).setFirstResult(currentPosition).setMaxResults(maxObjs).getResultList();
    }

    public List<T> getListAll() {
        String jpql = "select t from "+ persistenceClass.getSimpleName() +" t"+ " order by t." + order;
        return em.createQuery(jpql).getResultList();
    }
    
    public void first() {
        currentPosition = 0;
    }
    
    public void previous() {
        currentPosition -= maxObjs;
        if(currentPosition < 0) {
            currentPosition = 0;
        }
    }
    
    public void next() {
        if(currentPosition + maxObjs < totalObjs) {
            currentPosition += maxObjs;
        }
    }
    
    public void last() {
        int rest = totalObjs % maxObjs;
        if(rest > 0) {
            currentPosition = totalObjs - rest;
        }
        else {
            currentPosition = totalObjs - maxObjs;
        }
    }
    
    public String getNavegationMessage() {
        int to = currentPosition + maxObjs;
        if(to > totalObjs) {
            to = totalObjs;
        }
        return "["+to+" / "+ totalObjs + " tasks]"; 
    }
    
    public void rollback() {
        if(em.getTransaction().isActive() == false) {
            em.getTransaction().begin();
        }
        em.getTransaction().rollback();
    }
    
    public boolean persist(T obj) {
        try {
            em.getTransaction().begin();
            em.persist(obj);
            em.getTransaction().commit();
            message = "Object persited sucessfully";
            return true;
        }
        catch(Exception e) {
            rollback();
            message = "Error while persinting: " + Util.getMessageError(e);
            return false;
        }
    }
    
    public boolean merge(T obj) {
        try {
            em.getTransaction().begin();
            em.merge(obj);
            em.getTransaction().commit();
            message = "Object persited sucessfully";
            return true;
        }
        catch(Exception e) {
            rollback();
            message = "Error while persinting: " + Util.getMessageError(e);
            return false;
        }
    }
    
    public boolean remove(T obj) {
        try {
            em.getTransaction().begin();
            em.remove(obj);
            em.getTransaction().commit();
            message = "Object removed sucessfully";
            return true;
        }
        catch(Exception e) {
            rollback();
            message = "Error while removing: " + Util.getMessageError(e);
            return false;
        }
    }
    
    public T find(Integer id) {
        rollback();
        T task = (T) em.find(persistenceClass, id);
        return task;
    }
    
    public void setListObjs(List<T> listObjs) {
        this.listObjs = listObjs;
    }

    public void setListAll(List<T> listAll) {
        this.listAll = listAll;
    }

    public Class getPersistenceClass() {
        return persistenceClass;
    }

    public void setPersistenceClass(Class persistenceClass) {
        this.persistenceClass = persistenceClass;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public EntityManager getEm() {
        return em;
    }

    public void setEm(EntityManager em) {
        this.em = em;
    }

    public String getOrder() {
        return order;
    }

    public void setOrder(String order) {
        this.order = order;
    }

    public String getFilter() {
        return filter;
    }

    public void setFilter(String filter) {
        this.filter = filter;
    }

    public Integer getMaxObjs() {
        return maxObjs;
    }

    public void setMaxObjs(Integer maxObjs) {
        this.maxObjs = maxObjs;
    }

    public Integer getCurrentPosition() {
        return currentPosition;
    }

    public void setCurrentPosition(Integer currentPosition) {
        this.currentPosition = currentPosition;
    }

    public Integer getTotalObjs() {
        return totalObjs;
    }

    public void setTotalObjs(Integer totalObjs) {
        this.totalObjs = totalObjs;
    }
    
}
