package com.huawei.wms.ii.entities;

import com.huawei.wms.ii.entities.ItemStatus;
import com.huawei.wms.ii.entities.Items;
import com.huawei.wms.ii.entities.OrderLineItemStatus;
import com.huawei.wms.ii.entities.Orders;
import java.math.BigInteger;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2017-02-16T12:32:16")
@StaticMetamodel(OrderLineItem.class)
public class OrderLineItem_ { 

    public static volatile SingularAttribute<OrderLineItem, String> lineItemComment;
    public static volatile SingularAttribute<OrderLineItem, Items> itemId;
    public static volatile SingularAttribute<OrderLineItem, Boolean> returnable;
    public static volatile SingularAttribute<OrderLineItem, Long> lineItemId;
    public static volatile SingularAttribute<OrderLineItem, Orders> orderId;
    public static volatile SingularAttribute<OrderLineItem, BigInteger> qty;
    public static volatile SingularAttribute<OrderLineItem, ItemStatus> returnableStatus;
    public static volatile SingularAttribute<OrderLineItem, OrderLineItemStatus> lineItemStatus;

}