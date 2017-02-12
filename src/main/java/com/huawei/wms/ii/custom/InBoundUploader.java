/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.huawei.wms.ii.custom;

import java.io.IOException;
import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.enterprise.context.SessionScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Named;

/**
 *
 * @author Amr
 */
@Named("InBoundUploader")
@SessionScoped
public class InBoundUploader implements Serializable{
    
    
    public void exportExcelData() {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        ExternalContext externalContext = facesContext.getExternalContext();
        externalContext.setResponseContentType("text/plain");
        externalContext.setResponseHeader("Content-Disposition", "attachment; filename=\"bulk_upload.csv\"");

        try {
            externalContext.getResponseOutputStream().write("huawei_bom,qty".getBytes());
            externalContext.getResponseOutputStream().flush();
            externalContext.getResponseOutputStream().close();
        } catch (IOException ex) {
            Logger.getLogger(InBoundUploader.class.getName()).log(Level.SEVERE, null, ex);
        }
        facesContext.responseComplete();
}


}
