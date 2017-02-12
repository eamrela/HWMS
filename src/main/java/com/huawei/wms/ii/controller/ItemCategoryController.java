package com.huawei.wms.ii.controller;

import com.huawei.wms.ii.entities.ItemCategory;
import com.huawei.wms.ii.controller.util.JsfUtil;
import com.huawei.wms.ii.controller.util.JsfUtil.PersistAction;
import com.huawei.wms.ii.beans.ItemCategoryFacade;

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

@Named("itemCategoryController")
@SessionScoped
public class ItemCategoryController implements Serializable {

    @EJB
    private com.huawei.wms.ii.beans.ItemCategoryFacade ejbFacade;
    private List<ItemCategory> items = null;
    private ItemCategory selected;

    public ItemCategoryController() {
    }

    public ItemCategory getSelected() {
        return selected;
    }

    public void setSelected(ItemCategory selected) {
        this.selected = selected;
    }

    protected void setEmbeddableKeys() {
    }

    protected void initializeEmbeddableKey() {
    }

    private ItemCategoryFacade getFacade() {
        return ejbFacade;
    }

    public ItemCategory prepareCreate() {
        selected = new ItemCategory();
        initializeEmbeddableKey();
        return selected;
    }

    public void create() {
        persist(PersistAction.CREATE, ResourceBundle.getBundle("/Bundle").getString("ItemCategoryCreated"));
        if (!JsfUtil.isValidationFailed()) {
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public void update() {
        persist(PersistAction.UPDATE, ResourceBundle.getBundle("/Bundle").getString("ItemCategoryUpdated"));
    }

    public void destroy() {
        persist(PersistAction.DELETE, ResourceBundle.getBundle("/Bundle").getString("ItemCategoryDeleted"));
        if (!JsfUtil.isValidationFailed()) {
            selected = null; // Remove selection
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public List<ItemCategory> getItems() {
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

    public ItemCategory getItemCategory(java.lang.String id) {
        return getFacade().find(id);
    }

    public List<ItemCategory> getItemsAvailableSelectMany() {
        return getFacade().findAll();
    }

    public List<ItemCategory> getItemsAvailableSelectOne() {
        return getFacade().findAll();
    }

    @FacesConverter("ItemCategoryControllerConverter")
    public static class ItemCategoryControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            ItemCategoryController controller = (ItemCategoryController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "itemCategoryController");
            return controller.getItemCategory(getKey(value));
        }

        java.lang.String getKey(String value) {
            java.lang.String key;
            key = value;
            return key;
        }

        String getStringKey(java.lang.String value) {
            StringBuilder sb = new StringBuilder();
            sb.append(value);
            return sb.toString();
        }

        @Override
        public String getAsString(FacesContext facesContext, UIComponent component, Object object) {
            if (object == null) {
                return null;
            }
            if (object instanceof ItemCategory) {
                ItemCategory o = (ItemCategory) object;
                return getStringKey(o.getCategoryName());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), ItemCategory.class.getName()});
                return null;
            }
        }

    }

}
