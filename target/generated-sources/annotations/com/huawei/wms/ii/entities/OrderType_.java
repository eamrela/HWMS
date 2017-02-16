package com.huawei.wms.ii.entities;

import com.huawei.wms.ii.entities.Orders;
import com.huawei.wms.ii.entities.WarehouseLog;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2017-02-16T12:32:16")
@StaticMetamodel(OrderType.class)
public class OrderType_ { 

    public static volatile SingularAttribute<OrderType, String> typeName;
    public static volatile CollectionAttribute<OrderType, WarehouseLog> warehouseLogCollection;
    public static volatile SingularAttribute<OrderType, String> typeDescription;
    public static volatile CollectionAttribute<OrderType, Orders> ordersCollection;

}