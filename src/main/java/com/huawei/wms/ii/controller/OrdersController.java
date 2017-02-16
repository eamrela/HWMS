package com.huawei.wms.ii.controller;

import com.huawei.wms.ii.entities.Orders;
import com.huawei.wms.ii.controller.util.JsfUtil;
import com.huawei.wms.ii.controller.util.JsfUtil.PersistAction;
import com.huawei.wms.ii.beans.OrdersFacade;
import com.huawei.wms.ii.custom.IssuedItem;
import com.huawei.wms.ii.entities.Items;
import com.huawei.wms.ii.entities.OrderLineItem;
import java.io.IOException;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Date;
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
import org.apache.commons.io.IOUtils;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.event.SelectEvent;
import org.primefaces.model.UploadedFile;


@Named("ordersController")
@SessionScoped
public class OrdersController implements Serializable {

    private ArrayList<IssuedItem> issuedItems;
    private IssuedItem selectIssueItem;
    @EJB
    private com.huawei.wms.ii.beans.OrdersFacade ejbFacade;
    private List<Orders> items = null;
    private List<Orders> warehouseOrders = null;
    private Orders selected;
    private List<OrderLineItem> selectedItems;

    @Inject
    private UsersController userController;
    @Inject
    private OrderTypeController orderTypeController;
    @Inject
    private OrderLineItemController orderLineItemController;
    @Inject
    private OrderLineItemStatusController orderLineItemStatusController;
    @Inject
    private ItemsController itemsController;
    @Inject
    private WarehouseInventoryController warehouseInventoryController;
    @Inject
    private OrderStatusController orderStatusController;
    @Inject
    private WarehouseController warehouseController;
    @Inject
    private ItemStatusController itemStatusController;
    
    public OrdersController() {
    }

    public Orders getSelected() {
        return selected;
    }

    public void setSelected(Orders selected) {
        this.selected = selected;
        this.selectedItems = null;
    }

    
    protected void setEmbeddableKeys() {
    }

    protected void initializeEmbeddableKey() {
    }

    private OrdersFacade getFacade() {
        return ejbFacade;
    }

    public Orders prepareCreate() {
        selectedItems = null;
        selected = new Orders();
        initializeEmbeddableKey();
        return selected;
    }

    public Orders prepareCreateInBound(){
        selectedItems = null;
        selected = new Orders();
        selected.setCreatedBy(userController.getLoggedInuser());
        selected.setLoggingTime(new Date());
        selected.setProject(userController.getLoggedInuser().getProject());
        selected.setRegion(userController.getLoggedInuser().getRegion());
        selected.setOrderType(orderTypeController.getOrderType("In-Bound"));
        selected.setOrderStatus(orderStatusController.getOrderStatus("Pending Approval"));
        initializeEmbeddableKey();
        return selected; 
    }
    
    public Orders prepareCreateTransfer(){
        selectedItems = null;
        selected = new Orders();
        selected.setCreatedBy(userController.getLoggedInuser());
        selected.setLoggingTime(new Date());
        selected.setProject(userController.getLoggedInuser().getProject());
        selected.setRegion(userController.getLoggedInuser().getRegion());
        selected.setOrderType(orderTypeController.getOrderType("Transfer"));
        selected.setOrderStatus(orderStatusController.getOrderStatus("Pending Approval"));
        initializeEmbeddableKey();
        return selected; 
    }
    
    public Orders prepareCreateIssue(){
        selectedItems = null;
     selected = new Orders();
        selected.setCreatedBy(userController.getLoggedInuser());
        selected.setLoggingTime(new Date());
        selected.setProject(userController.getLoggedInuser().getProject());
        selected.setRegion(userController.getLoggedInuser().getRegion());
        selected.setOrderType(orderTypeController.getOrderType("Issued Material (OTS)"));
        selected.setOrderStatus(orderStatusController.getOrderStatus("Pending Approval"));
        initializeEmbeddableKey();
        return selected;    
    }
    
    public void create() {
        persist(PersistAction.CREATE, ResourceBundle.getBundle("/Bundle").getString("OrdersCreated"));
        if (!JsfUtil.isValidationFailed()) {
            items = null;    // Invalidate list of items to trigger re-query.
            warehouseOrders = null;
            selectedItems = null;
        }
    }

    public void createInBoundOrder(){
        if(selected!=null){
            if(selected.getOrderLineItemCollection()!=null){
                Object[] lineItems = selected.getOrderLineItemCollection().toArray();
                boolean allGood = true;
                for (Object lineItem : lineItems) {
                    if(((OrderLineItem)lineItem).getQty()!=null){
                        if(((OrderLineItem)lineItem).getQty().compareTo(BigInteger.ZERO)==0){
                            JsfUtil.addErrorMessage("You need to add QTY to the Order Line Item");
                            allGood=false;
                        }
                    }else{
                        allGood = false;
                    }
                }
                if(allGood){
                    persist(PersistAction.CREATE, ResourceBundle.getBundle("/Bundle").getString("OrdersCreated"));
                    clearValues();
                    redirect();
                }
            }else{
                JsfUtil.addErrorMessage("You need to add In-Bound Items");
            }
        }
    }
    
    public void createTransferOrder(){
        if(selected!=null){
            if(selected.getOrderLineItemCollection()!=null){
                Object[] lineItems = selected.getOrderLineItemCollection().toArray();
                boolean allGood = true;
                for (Object lineItem : lineItems) {
                    if(((OrderLineItem)lineItem).getQty()!=null){
                        if(((OrderLineItem)lineItem).getQty().compareTo(BigInteger.ZERO)==0){
                            JsfUtil.addErrorMessage("You need to add QTY to the Order Line Item");
                            allGood=false;
                            break;
                        }
                        if(((OrderLineItem)lineItem).getQty().compareTo(getGoodStockQty((OrderLineItem)lineItem))>0){
                            JsfUtil.addErrorMessage("The Qty is more than what's available");
                            allGood=false;
                            break;
                        }
                    }else{
                        allGood = false;
                    }
                }
                if(allGood){
                    persist(PersistAction.CREATE, ResourceBundle.getBundle("/Bundle").getString("OrdersCreated"));
                    updateGoodWarehouseInventory(lineItems);
                    clearValues();
                    redirect();
                }
            }else{
                JsfUtil.addErrorMessage("You need to add Transfer Items");
            }
        }
    }
    
    public void createIssueOrder(){
        if(selected!=null){
            if(selected.getOrderLineItemCollection()==null){
                selected.setOrderLineItemCollection(new ArrayList<>());
            }
            for (IssuedItem issuedItem : issuedItems) {
                OrderLineItem lineItemReturnable = new OrderLineItem();
                lineItemReturnable.setItemId(issuedItem.getReturnable());
                lineItemReturnable.setReturnable(Boolean.TRUE);
                lineItemReturnable.setReturnableStatus(issuedItem.getReturnableStatus());
                lineItemReturnable.setQty(issuedItem.getIssued().getQty());
                lineItemReturnable.setOrderId(selected);
                lineItemReturnable.setLineItemStatus(orderLineItemStatusController.getOrderLineItemStatus("Pending Approval"));
                selected.getOrderLineItemCollection().add(issuedItem.getIssued());
                selected.getOrderLineItemCollection().add(lineItemReturnable);
            }
            persist(PersistAction.CREATE, ResourceBundle.getBundle("/Bundle").getString("OrdersCreated"));
            clearValues();
            redirect();
        }
    }
    
    public void update() {
        persist(PersistAction.UPDATE, ResourceBundle.getBundle("/Bundle").getString("OrdersUpdated"));
    }

    public void destroy() {
        persist(PersistAction.DELETE, ResourceBundle.getBundle("/Bundle").getString("OrdersDeleted"));
        if (!JsfUtil.isValidationFailed()) {
            selected = null; // Remove selection
            items = null;    // Invalidate list of items to trigger re-query.
            warehouseOrders = null;
            selectedItems = null;
        }
    }

    public List<Orders> getItems() {
        if (items == null) {
            items = getFacade().findAll();
        }
        return items;
    }

    public List<Orders> getWarehouseOrders() {
        if(warehouseOrders==null){
            warehouseOrders = getFacade().findOrdersByProjectRegionOffice(userController.getLoggedInuser());
            selectedItems = null;
        }
        return warehouseOrders;
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

    public Orders getOrders(java.lang.Long id) {
        return getFacade().find(id);
    }

    public List<Orders> getItemsAvailableSelectMany() {
        return getFacade().findAll();
    }

    public List<Orders> getItemsAvailableSelectOne() {
        return getFacade().findAll();
    }

    private void updateGoodWarehouseInventory(Object[] lineItems) {
        for (Object lineItem : lineItems) {
            OrderLineItem orderLineItem = (OrderLineItem) lineItem;
                warehouseInventoryController.setSelected(warehouseInventoryController.findGoodItem(
                        orderLineItem.getOrderId().getFromWarehouse(),
                        orderLineItem.getItemId()));
                warehouseInventoryController.deductFromSelectedGood(orderLineItem.getQty());
        }
    }

    @FacesConverter(forClass = Orders.class)
    public static class OrdersControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            OrdersController controller = (OrdersController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "ordersController");
            return controller.getOrders(getKey(value));
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
            if (object instanceof Orders) {
                Orders o = (Orders) object;
                return getStringKey(o.getOrderId());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), Orders.class.getName()});
                return null;
            }
        }

    }

    public void addSelectedLineItem(SelectEvent event){
        if(selected!=null){
        Items item = (Items) event.getObject();
        if(selected.getOrderLineItemCollection()==null){
            selected.setOrderLineItemCollection(new ArrayList());
        }
        OrderLineItem lineItem = new OrderLineItem();
        lineItem.setItemId(item);
        lineItem.setOrderId(selected);
        lineItem.setLineItemStatus(orderLineItemStatusController.getOrderLineItemStatus("Pending Approval"));
        
        selected.getOrderLineItemCollection().add(lineItem);
        itemsController.setSelected(null);
        } 
    }
    
    public void addSelectedIssueLineItem(SelectEvent event){
        if(selected!=null){
        Items item = (Items) event.getObject();
        if(issuedItems==null){
            issuedItems = new ArrayList<>();
        }
        OrderLineItem lineItem = new OrderLineItem();
        
        lineItem.setItemId(item);
        lineItem.setOrderId(selected);
        lineItem.setLineItemStatus(orderLineItemStatusController.getOrderLineItemStatus("Pending Approval"));
        
        IssuedItem issuedItem = new IssuedItem();
        issuedItem.setIssued(lineItem);
        issuedItem.setReturnable(issuedItem.getIssued().getItemId());
        
        issuedItems.add(issuedItem);
        itemsController.setSelected(null);
        }  
    }
    
    
    
    public void removeFromLineItems(final SelectEvent event){
        OrderLineItem obj = (OrderLineItem) event.getObject();
        if(selected!=null){
          if(selected.getOrderLineItemCollection()!=null){
            selected.getOrderLineItemCollection().remove(obj);
            }  
        }
    }
    
    public void removeFromIssuedLineItems(final SelectEvent event){
         IssuedItem obj = (IssuedItem) event.getObject();
        if(selected!=null){
          if(issuedItems!=null){
            issuedItems.remove(obj);
            }  
        }
    }
    
    public BigInteger getGoodStockQty(OrderLineItem lineItem){
        if(selected!=null){
            if(selected.getFromWarehouse()!=null){
                return warehouseInventoryController.findGoodStockQty(lineItem.getItemId(),selected.getFromWarehouse());
            }else{
                JsfUtil.addErrorMessage("You need to select a warehouse");
            }
        }
        return BigInteger.ZERO;
    }
    
    public void validateActionDate(SelectEvent event){
        if(selected!=null){
            selected.setActionDate((Date) event.getObject());
            if(selected.getFromWarehouse()!=null){
                if(selected.getActionDate().after(selected.getLoggingTime())){
                    selected.setActionDate(null);
                    JsfUtil.addErrorMessage("Action date can't be after the order creation date");
                }
                else if(selected.getActionDate().before(selected.getFromWarehouse().getActionDateValidation())){
                    selected.setActionDate(null);
                    JsfUtil.addErrorMessage("Action date can't be before warehouse validation date");
                }
                
            }else{
                selected.setActionDate(null);
                JsfUtil.addErrorMessage("You need to select a warehouse first");
            }
        }
    }

    public ArrayList<IssuedItem> getIssuedItems() {
        return issuedItems;
    }

    public void setItems(List<Orders> items) {
        this.items = items;
    }
    
       
    public void redirect(){
        try {
            FacesContext.getCurrentInstance().getExternalContext().redirect("/WMS-II/app/orders/order_queue.xhtml");
        } catch (IOException ex) {
            Logger.getLogger(OrdersController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void clearValues(){
        selected = null;
        items = null;
        itemsController.setSelected(null);
        warehouseController.setItemsForProject(null);
        warehouseController.setItemsForUser(null);
        issuedItems = null;
        selectIssueItem = null;
        warehouseOrders = null;
        selectedItems = null;
        
    }

    public IssuedItem getSelectIssueItem() {
        return selectIssueItem;
    }

    public void setSelectIssueItem(IssuedItem selectIssueItem) {
        this.selectIssueItem = selectIssueItem;
    }
 
    
    public void setIssuedItems(ArrayList<IssuedItem> issuedItems) {
        this.issuedItems = issuedItems;
    }

    public List<OrderLineItem> getSelectedItems() {
        return selectedItems;
    }

    public void setSelectedItems(List<OrderLineItem> selectedItems) {
        this.selectedItems = selectedItems;
    }

    public void handleApprove(){
        if(selectedItems!=null){
            for (OrderLineItem selectedItem : selectedItems) {
            if(selectedItem.getLineItemStatus().getStatusName().equals("Pending Approval")){
                if(selectedItem.getOrderId().getOrderType().getTypeName().equals("In-Bound")){
                    selectedItem.setLineItemStatus(orderLineItemStatusController.getOrderLineItemStatus("Accepted"));
                    
                    selected.getOrderLineItemCollection().remove(selectedItem);
                    
                    orderLineItemController.setSelected(selectedItem);
                    orderLineItemStatusController.update();
                   
                    warehouseInventoryController.setSelected(warehouseInventoryController.findWarehouseItem(
                            selectedItem.getOrderId().getToWarehouse(),
                            selectedItem.getItemId(),
                            itemStatusController.getItemStatus("Good")));
                    warehouseInventoryController.addToSelected(selectedItem.getQty());
                    selected.getOrderLineItemCollection().add(selectedItem);
                    updateSelectedStatus();
                    update();
                }else if(selectedItem.getOrderId().getOrderType().getTypeName().equals("Transfer")){
                    selectedItem.setLineItemStatus(orderLineItemStatusController.getOrderLineItemStatus("Accepted"));
                    selected.getOrderLineItemCollection().remove(selectedItem);
                    
                    warehouseInventoryController.setSelected(warehouseInventoryController.findWarehouseItem(
                            selectedItem.getOrderId().getToWarehouse(),
                            selectedItem.getItemId(),
                            itemStatusController.getItemStatus("Good")));
                    warehouseInventoryController.addToSelected(selectedItem.getQty());
                    
                    orderLineItemController.setSelected(selectedItem);
                    orderLineItemStatusController.update();
                    selected.getOrderLineItemCollection().add(selectedItem);
                    updateSelectedStatus();
                    update();
                }else if(selectedItem.getOrderId().getOrderType().getTypeName().equals("Issued Material (OTS)")){
                    selectedItem.setLineItemStatus(orderLineItemStatusController.getOrderLineItemStatus("Accepted"));
                    selected.getOrderLineItemCollection().remove(selectedItem);
                    if(selectedItem.getReturnable()!=null){
                        if(selectedItem.getReturnable()){
                            warehouseInventoryController.setSelected(warehouseInventoryController.findWarehouseItem(
                            selectedItem.getOrderId().getFromWarehouse(),
                            selectedItem.getItemId(),
                            selectedItem.getReturnableStatus()));
                            warehouseInventoryController.addToSelected(selectedItem.getQty());
                        }
                    }else{
                            warehouseInventoryController.setSelected(warehouseInventoryController.findWarehouseItem(
                            selectedItem.getOrderId().getFromWarehouse(),
                            selectedItem.getItemId(),
                            itemStatusController.getItemStatus("Good")));
                            warehouseInventoryController.subtractFromSelected(selectedItem.getQty());
                    }
                    
                    orderLineItemController.setSelected(selectedItem);
                    orderLineItemStatusController.update();
                    
                    selected.getOrderLineItemCollection().add(selectedItem);
                    updateSelectedStatus();
                    update();
                }
            }
            }
            warehouseOrders=null;
            selectedItems=null;
            selected=null;
        }
    }
    
    public void handleReject(){
        if(selectedItems!=null){
            for (OrderLineItem selectedItem : selectedItems) {
                    if(selectedItem.getLineItemStatus().getStatusName().equals("Pending Approval")){
                if(selectedItem.getOrderId().getOrderType().getTypeName().equals("In-Bound")){
                        
                    selectedItem.setLineItemStatus(orderLineItemStatusController.getOrderLineItemStatus("Rejected"));
                    selected.getOrderLineItemCollection().remove(selectedItem);
                    
                    orderLineItemController.setSelected(selectedItem);
                    orderLineItemStatusController.update();
                    
                    selected.getOrderLineItemCollection().add(selectedItem);
                    updateSelectedStatus();
                    update();
                }else if(selectedItem.getOrderId().getOrderType().getTypeName().equals("Transfer")){
                    selectedItem.setLineItemStatus(orderLineItemStatusController.getOrderLineItemStatus("Rejected"));
                    selected.getOrderLineItemCollection().remove(selectedItem);
                    
                    warehouseInventoryController.setSelected(warehouseInventoryController.findWarehouseItem(
                            selectedItem.getOrderId().getFromWarehouse(),
                            selectedItem.getItemId(),
                            itemStatusController.getItemStatus("Good")));
                    warehouseInventoryController.addToSelected(selectedItem.getQty());
                    orderLineItemController.setSelected(selectedItem);
                    orderLineItemStatusController.update();
                    
                    selected.getOrderLineItemCollection().add(selectedItem);
                    updateSelectedStatus();
                    update();
                }else if(selectedItem.getOrderId().getOrderType().getTypeName().equals("Issued Material (OTS)")){
                    selectedItem.setLineItemStatus(orderLineItemStatusController.getOrderLineItemStatus("Rejected"));
                    
                    selected.getOrderLineItemCollection().remove(selectedItem);
                    orderLineItemController.setSelected(selectedItem);
                    orderLineItemStatusController.update();
                    selected.getOrderLineItemCollection().add(selectedItem);
                    updateSelectedStatus();
                    update();
                    }
                }
            }
            warehouseOrders=null;
            selectedItems=null;
            selected=null;
        }
    }
    
    public void updateSelectedStatus(){
        if(selected!=null){
            if(selected.getOrderLineItemCollection()!=null){
                Object[] lineitems = selected.getOrderLineItemCollection().toArray();
                int accepted=0;
                int rejected=0;
                for (Object lineitem : lineitems) {
                    if(((OrderLineItem)lineitem).getLineItemStatus().getStatusName().equals("Accepted")){
                        accepted++;
                    }
                    else if(((OrderLineItem)lineitem).getLineItemStatus().getStatusName().equals("Rejected")){
                        rejected++;
                    }
                }
                if(accepted==lineitems.length){
                    selected.setOrderStatus(orderStatusController.getOrderStatus("Accepted"));
                }
                else if(rejected==lineitems.length){
                    selected.setOrderStatus(orderStatusController.getOrderStatus("Rejected"));
                }else if(accepted>0){
                    selected.setOrderStatus(orderStatusController.getOrderStatus("Partially Accepted"));
                }
            }
        }
    }
    
    
    public void handleFileUpload(FileUploadEvent event) {
        UploadedFile file = event.getFile();
        try {
            String myString = IOUtils.toString(file.getInputstream(), "UTF-8");
            myString = myString.replaceAll("\"", "").replaceAll("\\r", "");
            String[] lines = myString.split("\n");
            for (int i = 0; i < lines.length; i++) {
                String[] vals = lines[i].split(",");
                Items item = null;
                if((item=itemsController.getItems(vals[0]))!=null){
                    if(selected!=null){
                        if(selected.getOrderLineItemCollection()==null){
                            selected.setOrderLineItemCollection(new ArrayList());
                        }
                    OrderLineItem lineItem = new OrderLineItem();
                    lineItem.setItemId(item);
                    lineItem.setOrderId(selected);
                    lineItem.setQty(new BigInteger(vals[1]));
                   lineItem.setLineItemStatus(orderLineItemStatusController.getOrderLineItemStatus("Pending Approval"));
                    selected.getOrderLineItemCollection().add(lineItem);
                    itemsController.setSelected(null);
                    } 
                }
            }
            
            JsfUtil.addSuccessMessage(file.getFileName()+ " is now uploaded");
        } catch (IOException ex) {
            Logger.getLogger(OrdersController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
}
