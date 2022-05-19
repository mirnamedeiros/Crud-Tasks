 /*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.acat.util;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

/**
 *
 * @author Mirna
 */
public class Util {
    
    public static String getMessageError(Exception e) {
        while(e.getCause() != null) {
            e = (Exception)e.getCause();
        }
        String return_ = e.getMessage();
        if(return_.contains("foreign key")) {
            return_ = "Registro não pode ser excluído por possuir referências no sistema";
        }
        return return_;
    }
    
    public static void messageInfo(String msg) {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        facesContext.getExternalContext().getFlash().setKeepMessages(true);
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO,msg,"");
        facesContext.addMessage(null, message);
    }
    
    public static void messageError(String msg) {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        facesContext.getExternalContext().getFlash().setKeepMessages(true);
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR,msg,"");
        facesContext.addMessage(null, message);
    }
}
