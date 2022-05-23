/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.acat.controller;

import br.acat.dao.TaskDAO;
import br.acat.model.Task;
import br.acat.util.Util;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author Mirna
 */
@ManagedBean(name = "taskController")
@SessionScoped
public class TaskController {
    
    private TaskDAO dao;
    private Task task;

    public TaskController() {
        dao = new TaskDAO();
    }
    
    public String list() {
        return "/private/task/list?faces-redirect=true";
    }
    
    public String newTask() {
        task = new Task();
        return "edit?faces-redirect=true";
    }
    
    public String save() {
        if(dao.save(task)) {
            Util.messageInfo(dao.getMessage());
            return "list?faces-redirect=true";
        }
        else {
            Util.messageError(dao.getMessage());
            return "edit?faces-redirect=true";
        }
    }
    
    public String cancel() {
        return "list?faces-redirect=true";
    }
    
    public String edit(Integer id) {
        task = dao.find(id);
        return "edit?faces-redirect=true";
    }
    
    public void remove(Integer id) {
        task = dao.find(id);
        if(dao.remove(task)) {
            Util.messageInfo(dao.getMessage());
        }
        else {
            Util.messageError(dao.getMessage());
        }
    }
    
    public TaskDAO getDao() {
        return dao;
    }

    public void setDao(TaskDAO dao) {
        this.dao = dao;
    }

    public Task getTask() {
        return task;
    }

    public void setTask(Task task) {
        this.task = task;
    }
    
}
