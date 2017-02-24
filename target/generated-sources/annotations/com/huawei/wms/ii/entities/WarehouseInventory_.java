package com.huawei.wms.ii.entities;

import com.huawei.wms.ii.entities.ItemStatus;
import com.huawei.wms.ii.entities.Items;
import com.huawei.wms.ii.entities.Warehouse;
import com.huawei.wms.ii.entities.WarehouseInventoryPK;
import java.math.BigInteger;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2017-02-24T21:38:05")
@StaticMetamodel(WarehouseInventory.class)
public class WarehouseInventory_ { 

    public static volatile SingularAttribute<WarehouseInventory, WarehouseInventoryPK> warehouseInventoryPK;
    public static volatile SingularAttribute<WarehouseInventory, BigInteger> qty;
    public static volatile SingularAttribute<WarehouseInventory, ItemStatus> itemStatus;
    public static volatile SingularAttribute<WarehouseInventory, Warehouse> warehouse;
    public static volatile SingularAttribute<WarehouseInventory, Items> items;

}