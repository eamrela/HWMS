package com.huawei.wms.ii.entities;

import com.huawei.wms.ii.entities.Orders;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2017-02-24T21:38:05")
@StaticMetamodel(OrderStatus.class)
public class OrderStatus_ { 

    public static volatile SingularAttribute<OrderStatus, String> statusDescription;
    public static volatile SingularAttribute<OrderStatus, String> statusName;
    public static volatile CollectionAttribute<OrderStatus, Orders> ordersCollection;

}