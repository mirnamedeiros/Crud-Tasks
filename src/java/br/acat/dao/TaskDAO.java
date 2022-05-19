/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.acat.dao;

import br.acat.jpa.EntityManagerUtil;
import br.acat.model.Task;
import br.acat.util.Util;
import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;

/**
 *
 * @author Mirna
 */
public class TaskDAO implements Serializable {

    private String message = "";
    private EntityManager em;

    public TaskDAO() {
        em = EntityManagerUtil.getEntityManager();
    }

    public List<Task> getList() {
        return em.createQuery("from Task order by tittle").getResultList();
    }

    public boolean save(Task t) {
        try {
            em.getTransaction().begin();
            if (t.getId() == null) {
                em.persist(t);
            } else {
                em.merge(t);
            }
            em.getTransaction().commit();
            message = "task saved successfully";
            return true;
        } catch (Exception e) {
            if (em.getTransaction().isActive() == false) {
                em.getTransaction().begin();
            }
            em.getTransaction().rollback();
            message = "Error while persisting object: " + Util.getMessageError(e);
            return false;
        }
    }

    public boolean remove(Task t) {
        try {
            em.getTransaction().begin();
            em.remove(t);
            em.getTransaction().commit();
            message = "task removed successfully";
            return true;
        } catch (Exception e) {
            if (em.getTransaction().isActive() == false) {
                em.getTransaction().begin();
            }
            em.getTransaction().rollback();
            message = "Error while removing object: " + Util.getMessageError(e);
            return false;
        }
    }

    public Task find(Integer id) {
        return em.find(Task.class, id);
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

}
