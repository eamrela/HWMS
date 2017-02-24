package com.huawei.wms.ii.controller;

import com.huawei.wms.ii.entities.OrderLineItem;
import com.huawei.wms.ii.controller.util.JsfUtil;
import com.huawei.wms.ii.controller.util.JsfUtil.PersistAction;
import com.huawei.wms.ii.beans.OrderLineItemFacade;

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

@Named("orderLineItemController")
@SessionScoped
public class OrderLineItemController implements Serializable {

    @EJB
    private com.huawei.wms.ii.beans.OrderLineItemFacade ejbFacade;
    private List<OrderLineItem> items = null;
    private List<OrderLineItem> itemsPerUser = null;
    private List<OrderLineItem> itemsPerUserTransfer = null;
    private OrderLineItem selected;

    @Inject 
    private UsersController userController;
    public OrderLineItemController() {
    }

    public OrderLineItem getSelected() {
        return selected;
    }

    public void setSelected(OrderLineItem selected) {
        this.selected = selected;
    }

    protected void setEmbeddableKeys() {
    }

    protected void initializeEmbeddableKey() {
    }

    private OrderLineItemFacade getFacade() {
        return ejbFacade;
    }

    public OrderLineItem prepareCreate() {
        selected = new OrderLineItem();
        initializeEmbeddableKey();
        return selected;
    }

    public void create() {
        persist(PersistAction.CREATE, ResourceBundle.getBundle("/Bundle").getString("OrderLineItemCreated"));
        if (!JsfUtil.isValidationFailed()) {
            items = null;    // Invalidate list of items to trigger re-query.
            itemsPerUser = null;
            itemsPerUserTransfer = null;
        }
    }

    public void update() {
        persist(PersistAction.UPDATE, ResourceBundle.getBundle("/Bundle").getString("OrderLineItemUpdated"));
    }

    public void destroy() {
        persist(PersistAction.DELETE, ResourceBundle.getBundle("/Bundle").getString("OrderLineItemDeleted"));
        if (!JsfUtil.isValidationFailed()) {
            selected = null; // Remove selection
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public List<OrderLineItem> getItems() {
        if (items == null) {
            items = getFacade().findAll();
        }
        return items;
    }

    public List<OrderLineItem> getItemsPerUser() {
        if(itemsPerUser == null){
            itemsPerUser = getFacade().findByUser(userController.getLoggedInuser());
        }
        return itemsPerUser;
    }

    public List<OrderLineItem> getItemsPerUserTransfer() {
        if(itemsPerUserTransfer == null){
            itemsPerUserTransfer = getFacade().findByUserTransfer(userController.getLoggedInuser());
        }
        return itemsPerUserTransfer;
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

    public OrderLineItem getOrderLineItem(java.lang.Long id) {
        return getFacade().find(id);
    }

    public List<OrderLineItem> getItemsAvailableSelectMany() {
        return getFacade().findAll();
    }

    public List<OrderLineItem> getItemsAvailableSelectOne() {
        return getFacade().findAll();
    }

    @FacesConverter(forClass = OrderLineItem.class)
    public static class OrderLineItemControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            OrderLineItemController controller = (OrderLineItemController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "orderLineItemController");
            return controller.getOrderLineItem(getKey(value));
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
            if (object instanceof OrderLineItem) {
                OrderLineItem o = (OrderLineItem) object;
                return getStringKey(o.getLineItemId());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), OrderLineItem.class.getName()});
                return null;
            }
        }

    }

}
