package com.huawei.wms.ii.controller;

import com.huawei.wms.ii.entities.WarehouseInventory;
import com.huawei.wms.ii.controller.util.JsfUtil;
import com.huawei.wms.ii.controller.util.JsfUtil.PersistAction;
import com.huawei.wms.ii.beans.WarehouseInventoryFacade;
import com.huawei.wms.ii.entities.ItemStatus;
import com.huawei.wms.ii.entities.Items;
import com.huawei.wms.ii.entities.Warehouse;

import java.io.Serializable;
import java.math.BigInteger;
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

@Named("warehouseInventoryController")
@SessionScoped
public class WarehouseInventoryController implements Serializable {

    @EJB
    private com.huawei.wms.ii.beans.WarehouseInventoryFacade ejbFacade;
    private List<WarehouseInventory> items = null;
    private WarehouseInventory selected;

    public WarehouseInventoryController() {
    }

    public WarehouseInventory getSelected() {
        return selected;
    }

    public void setSelected(WarehouseInventory selected) {
        this.selected = selected;
    }

    protected void setEmbeddableKeys() {
        selected.getWarehouseInventoryPK().setWarehouseId(selected.getWarehouse().getId());
        selected.getWarehouseInventoryPK().setItemId(selected.getItems().getHuaweiBom());
        selected.getWarehouseInventoryPK().setStatusId(selected.getItemStatus().getStatusName());
    }

    protected void initializeEmbeddableKey() {
        selected.setWarehouseInventoryPK(new com.huawei.wms.ii.entities.WarehouseInventoryPK());
    }

    private WarehouseInventoryFacade getFacade() {
        return ejbFacade;
    }

    public WarehouseInventory prepareCreate() {
        selected = new WarehouseInventory();
        initializeEmbeddableKey();
        return selected;
    }

    public void create() {
        persist(PersistAction.CREATE, ResourceBundle.getBundle("/Bundle").getString("WarehouseInventoryCreated"));
        if (!JsfUtil.isValidationFailed()) {
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public void update() {
        persist(PersistAction.UPDATE, ResourceBundle.getBundle("/Bundle").getString("WarehouseInventoryUpdated"));
    }

    public void destroy() {
        persist(PersistAction.DELETE, ResourceBundle.getBundle("/Bundle").getString("WarehouseInventoryDeleted"));
        if (!JsfUtil.isValidationFailed()) {
            selected = null; // Remove selection
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public List<WarehouseInventory> getItems() {
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

    public WarehouseInventory getWarehouseInventory(com.huawei.wms.ii.entities.WarehouseInventoryPK id) {
        return getFacade().find(id);
    }

    public List<WarehouseInventory> getItemsAvailableSelectMany() {
        return getFacade().findAll();
    }

    public List<WarehouseInventory> getItemsAvailableSelectOne() {
        return getFacade().findAll();
    }

    public BigInteger findGoodStockQty(Items itemId, Warehouse fromWarehouse) {
        return getFacade().findGoodQty(itemId,fromWarehouse);
    }

    public WarehouseInventory findGoodItem(Warehouse fromWarehouse, Items itemId) {
        return getFacade().findGoodItem(fromWarehouse,itemId);
    }

    public void deductFromSelectedGood(BigInteger qty) {
        if(selected!=null){
            selected.setQty(selected.getQty().subtract(qty));
            update();
        }
    }

    public WarehouseInventory findWarehouseItem(Warehouse toWarehouse, Items itemId, ItemStatus itemStatus) {
        return getFacade().findItem(toWarehouse,itemId,itemStatus);
    }

    public void addToSelected(BigInteger qty) {
        if(selected!=null){
            selected.setQty(selected.getQty().add(qty));
            update();
        }
    }

    public void subtractFromSelected(BigInteger qty) {
        if(selected!=null){
            selected.setQty(selected.getQty().subtract(qty));
            update();
        }
    }

    @FacesConverter(forClass = WarehouseInventory.class)
    public static class WarehouseInventoryControllerConverter implements Converter {

        private static final String SEPARATOR = "#";
        private static final String SEPARATOR_ESCAPED = "\\#";

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            WarehouseInventoryController controller = (WarehouseInventoryController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "warehouseInventoryController");
            return controller.getWarehouseInventory(getKey(value));
        }

        com.huawei.wms.ii.entities.WarehouseInventoryPK getKey(String value) {
            com.huawei.wms.ii.entities.WarehouseInventoryPK key;
            String values[] = value.split(SEPARATOR_ESCAPED);
            key = new com.huawei.wms.ii.entities.WarehouseInventoryPK();
            key.setWarehouseId(Long.parseLong(values[0]));
            key.setItemId(values[1]);
            key.setStatusId(values[2]);
            return key;
        }

        String getStringKey(com.huawei.wms.ii.entities.WarehouseInventoryPK value) {
            StringBuilder sb = new StringBuilder();
            sb.append(value.getWarehouseId());
            sb.append(SEPARATOR);
            sb.append(value.getItemId());
            sb.append(SEPARATOR);
            sb.append(value.getStatusId());
            return sb.toString();
        }

        @Override
        public String getAsString(FacesContext facesContext, UIComponent component, Object object) {
            if (object == null) {
                return null;
            }
            if (object instanceof WarehouseInventory) {
                WarehouseInventory o = (WarehouseInventory) object;
                return getStringKey(o.getWarehouseInventoryPK());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), WarehouseInventory.class.getName()});
                return null;
            }
        }

    }

}
