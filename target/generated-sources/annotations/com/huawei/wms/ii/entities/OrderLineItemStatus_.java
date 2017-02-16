package com.huawei.wms.ii.entities;

import com.huawei.wms.ii.entities.OrderLineItem;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2017-02-16T12:32:16")
@StaticMetamodel(OrderLineItemStatus.class)
public class OrderLineItemStatus_ { 

    public static volatile SingularAttribute<OrderLineItemStatus, String> statusDescription;
    public static volatile SingularAttribute<OrderLineItemStatus, String> statusName;
    public static volatile CollectionAttribute<OrderLineItemStatus, OrderLineItem> orderLineItemCollection;

}