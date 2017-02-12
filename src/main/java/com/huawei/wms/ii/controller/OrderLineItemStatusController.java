package com.huawei.wms.ii.controller;

import com.huawei.wms.ii.entities.OrderLineItemStatus;
import com.huawei.wms.ii.controller.util.JsfUtil;
import com.huawei.wms.ii.controller.util.JsfUtil.PersistAction;
import com.huawei.wms.ii.beans.OrderLineItemStatusFacade;

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

@Named("orderLineItemStatusController")
@SessionScoped
public class OrderLineItemStatusController implements Serializable {

    @EJB
    private com.huawei.wms.ii.beans.OrderLineItemStatusFacade ejbFacade;
    private List<OrderLineItemStatus> items = null;
    private OrderLineItemStatus selected;

    public OrderLineItemStatusController() {
    }

    public OrderLineItemStatus getSelected() {
        return selected;
    }

    public void setSelected(OrderLineItemStatus selected) {
        this.selected = selected;
    }

    protected void setEmbeddableKeys() {
    }

    protected void initializeEmbeddableKey() {
    }

    private OrderLineItemStatusFacade getFacade() {
        return ejbFacade;
    }

    public OrderLineItemStatus prepareCreate() {
        selected = new OrderLineItemStatus();
        initializeEmbeddableKey();
        return selected;
    }

    public void create() {
        persist(PersistAction.CREATE, ResourceBundle.getBundle("/Bundle").getString("OrderLineItemStatusCreated"));
        if (!JsfUtil.isValidationFailed()) {
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public void update() {
        persist(PersistAction.UPDATE, ResourceBundle.getBundle("/Bundle").getString("OrderLineItemStatusUpdated"));
    }

    public void destroy() {
        persist(PersistAction.DELETE, ResourceBundle.getBundle("/Bundle").getString("OrderLineItemStatusDeleted"));
        if (!JsfUtil.isValidationFailed()) {
            selected = null; // Remove selection
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public List<OrderLineItemStatus> getItems() {
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

    public OrderLineItemStatus getOrderLineItemStatus(java.lang.String id) {
        return getFacade().find(id);
    }

    public List<OrderLineItemStatus> getItemsAvailableSelectMany() {
        return getFacade().findAll();
    }

    public List<OrderLineItemStatus> getItemsAvailableSelectOne() {
        return getFacade().findAll();
    }

    @FacesConverter(forClass = OrderLineItemStatus.class)
    public static class OrderLineItemStatusControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            OrderLineItemStatusController controller = (OrderLineItemStatusController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "orderLineItemStatusController");
            return controller.getOrderLineItemStatus(getKey(value));
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
            if (object instanceof OrderLineItemStatus) {
                OrderLineItemStatus o = (OrderLineItemStatus) object;
                return getStringKey(o.getStatusName());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), OrderLineItemStatus.class.getName()});
                return null;
            }
        }

    }

}
