package com.huawei.wms.ii.controller;

import com.huawei.wms.ii.entities.Engineers;
import com.huawei.wms.ii.controller.util.JsfUtil;
import com.huawei.wms.ii.controller.util.JsfUtil.PersistAction;
import com.huawei.wms.ii.beans.EngineersFacade;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.ejb.EJBException;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.inject.Inject;

@Named("engineersController")
@SessionScoped
public class EngineersController implements Serializable {

    @EJB
    private com.huawei.wms.ii.beans.EngineersFacade ejbFacade;
    private List<Engineers> items = null;
    private Engineers selected;

    @Inject
    private OrdersController ordersController;
    
    public EngineersController() {
    }

    public Engineers getSelected() {
        return selected;
    }

    public void setSelected(Engineers selected) {
        this.selected = selected;
    }

    protected void setEmbeddableKeys() {
    }

    protected void initializeEmbeddableKey() {
    }

    private EngineersFacade getFacade() {
        return ejbFacade;
    }

    public Engineers prepareCreate() {
        selected = new Engineers();
        initializeEmbeddableKey();
        return selected;
    }

    public void create() {
        persist(PersistAction.CREATE, ResourceBundle.getBundle("/Bundle").getString("EngineersCreated"));
        if (!JsfUtil.isValidationFailed()) {
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public void update() {
        persist(PersistAction.UPDATE, ResourceBundle.getBundle("/Bundle").getString("EngineersUpdated"));
    }

    public void destroy() {
        persist(PersistAction.DELETE, ResourceBundle.getBundle("/Bundle").getString("EngineersDeleted"));
        if (!JsfUtil.isValidationFailed()) {
            selected = null; // Remove selection
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public List<Engineers> getItems() {
        if (items == null) {
            items = getFacade().findAll();
        }
        return items;
    }

    private void persist(PersistAction persistAction, String successMessage) {
        if (selected != null) {
            setEmbeddableKeys();
            try {
                if (persistAction != PersistAction.DELETE) {
                    getFacade().edit(selected);
                } else {
                    getFacade().remove(selected);
                }
                JsfUtil.addSuccessMessage(successMessage);
            } catch (EJBException ex) {
                String msg = "";
                Throwable cause = ex.getCause();
                if (cause != null) {
                    msg = cause.getLocalizedMessage();
                }
                if (msg.length() > 0) {
                    JsfUtil.addErrorMessage(msg);
                } else {
                    JsfUtil.addErrorMessage(ex, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
                }
            } catch (Exception ex) {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, ex);
                JsfUtil.addErrorMessage(ex, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
            }
        }
    }

    public Engineers getEngineers(java.lang.Long id) {
        return getFacade().find(id);
    }

    public List<Engineers> getItemsAvailableSelectMany() {
        return getFacade().findAll();
    }

    public List<Engineers> getItemsAvailableSelectOne() {
        return getFacade().findAll();
    }

    public List<Engineers> getItemsByProjectAndRegion(){
        if(ordersController.getSelected()!=null){
        return getFacade().findItemByProjectAndRegion(ordersController.getSelected().getProject()
                                                        , ordersController.getSelected().getRegion());
        }
        return new ArrayList<>();
    }
    
    
    @FacesConverter("EngineersControllerConverter")
    public static class EngineersControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            EngineersController controller = (EngineersController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "engineersController");
            return controller.getEngineers(getKey(value));
        }

        java.lang.Long getKey(String value) {
            java.lang.Long key;
            key = Long.valueOf(value);
            return key;
        }

        String getStringKey(java.lang.Long value) {
            StringBuilder sb = new StringBuilder();
            sb.append(value);
            return sb.toString();
        }

        @Override
        public String getAsString(FacesContext facesContext, UIComponent component, Object object) {
            if (object == null) {
                return null;
            }
            if (object instanceof Engineers) {
                Engineers o = (Engineers) object;
                return getStringKey(o.getId());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), Engineers.class.getName()});
                return null;
            }
        }

    }

}
