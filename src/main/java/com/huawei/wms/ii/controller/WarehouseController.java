package com.huawei.wms.ii.controller;

import com.huawei.wms.ii.entities.Warehouse;
import com.huawei.wms.ii.controller.util.JsfUtil;
import com.huawei.wms.ii.controller.util.JsfUtil.PersistAction;
import com.huawei.wms.ii.beans.WarehouseFacade;

import java.io.Serializable;
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

@Named("warehouseController")
@SessionScoped
public class WarehouseController implements Serializable {

    @EJB
    private com.huawei.wms.ii.beans.WarehouseFacade ejbFacade;
    private List<Warehouse> items = null;
    private List<Warehouse> itemsForUser = null;
    private List<Warehouse> itemsForProject = null;
    private Warehouse selected;
    
    @Inject
    private UsersController userController;

    public WarehouseController() {
    }

    public Warehouse getSelected() {
        return selected;
    }

    public void setSelected(Warehouse selected) {
        this.selected = selected;
    }

    protected void setEmbeddableKeys() {
    }

    protected void initializeEmbeddableKey() {
    }

    private WarehouseFacade getFacade() {
        return ejbFacade;
    }

    public Warehouse prepareCreate() {
        selected = new Warehouse();
        selected.setWarehouseProject(userController.getLoggedInuser().getProject());
        selected.setWarehouseRegion(userController.getLoggedInuser().getRegion());
        initializeEmbeddableKey();
        return selected;
    }

    public void create() {
        persist(PersistAction.CREATE, ResourceBundle.getBundle("/Bundle").getString("WarehouseCreated"));
        if (!JsfUtil.isValidationFailed()) {
            items = null;    // Invalidate list of items to trigger re-query.
            itemsForUser = null;
            itemsForProject = null;
        }
    }

    public void update() {
        persist(PersistAction.UPDATE, ResourceBundle.getBundle("/Bundle").getString("WarehouseUpdated"));
    }

    public void destroy() {
        persist(PersistAction.DELETE, ResourceBundle.getBundle("/Bundle").getString("WarehouseDeleted"));
        if (!JsfUtil.isValidationFailed()) {
            selected = null; // Remove selection
            items = null;    // Invalidate list of items to trigger re-query.
            itemsForUser = null;
            itemsForProject = null;
        }
    }

    public List<Warehouse> getItems() {
        if (items == null) {
            items = getFacade().findAll();
        }
        return items;
    }

    public void setItemsForUser(List<Warehouse> itemsForUser) {
        this.itemsForUser = itemsForUser;
    }

    public void setItemsForProject(List<Warehouse> itemsForProject) {
        this.itemsForProject = itemsForProject;
    }

    public List<Warehouse> getItemsForProject() {
        if(itemsForProject==null){
            itemsForProject = getFacade().findbyProject(userController.getLoggedInuser());
        }
        return itemsForProject;
    }
    
    
    
    public List<Warehouse> getItemsForUser() {
        if(itemsForUser==null){
            itemsForUser = getFacade().findbyProjectAndRegion(userController.getLoggedInuser());
        }
        return itemsForUser;
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

    public Warehouse getWarehouse(java.lang.Long id) {
        return getFacade().find(id);
    }

    public List<Warehouse> getItemsAvailableSelectMany() {
        return getFacade().findAll();
    }

    public List<Warehouse> getItemsAvailableSelectOne() {
        return getFacade().findAll();
    }

    @FacesConverter("WarehouseControllerConverter")
    public static class WarehouseControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            WarehouseController controller = (WarehouseController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "warehouseController");
            return controller.getWarehouse(getKey(value));
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
            if (object instanceof Warehouse) {
                Warehouse o = (Warehouse) object;
                return getStringKey(o.getId());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), Warehouse.class.getName()});
                return null;
            }
        }

    }

}
