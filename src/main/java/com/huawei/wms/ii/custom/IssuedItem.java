/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.huawei.wms.ii.custom;

import com.huawei.wms.ii.entities.ItemStatus;
import com.huawei.wms.ii.entities.Items;
import com.huawei.wms.ii.entities.OrderLineItem;

/**
 *
 * @author Amr
 */
public class IssuedItem {
    private OrderLineItem issued;
    private Items returnable;
    private ItemStatus returnableStatus;

    public IssuedItem() {
        issued = new OrderLineItem();
    }

    public ItemStatus getReturnableStatus() {
        return returnableStatus;
    }

    public void setReturnableStatus(ItemStatus returnableStatus) {
        this.returnableStatus = returnableStatus;
    }

    
    
    public OrderLineItem getIssued() {
        return issued;
    }

    public void setIssued(OrderLineItem issued) {
        this.issued = issued;
    }

    public Items getReturnable() {
        return returnable;
    }

    public void setReturnable(Items returnable) {
        this.returnable = returnable;
    }
    
    
}
