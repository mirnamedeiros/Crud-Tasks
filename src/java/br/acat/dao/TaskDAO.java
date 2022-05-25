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
 * @param <T>
 */
public class TaskDAO<T> extends GenericDAO<Task> implements Serializable {

    public TaskDAO() {
        super();
        persistenceClass = Task.class;
        order = "id";
    }
}
