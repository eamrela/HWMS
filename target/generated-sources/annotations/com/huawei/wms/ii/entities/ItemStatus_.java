package com.huawei.wms.ii.entities;

import com.huawei.wms.ii.entities.OrderLineItem;
import com.huawei.wms.ii.entities.WarehouseInventory;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2017-02-16T12:32:16")
@StaticMetamodel(ItemStatus.class)
public class ItemStatus_ { 

    public static volatile SingularAttribute<ItemStatus, String> statusDescription;
    public static volatile SingularAttribute<ItemStatus, String> statusName;
    public static volatile CollectionAttribute<ItemStatus, WarehouseInventory> warehouseInventoryCollection;
    public static volatile CollectionAttribute<ItemStatus, OrderLineItem> orderLineItemCollection;

}