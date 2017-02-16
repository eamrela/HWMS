package com.huawei.wms.ii.entities;

import com.huawei.wms.ii.entities.Orders;
import com.huawei.wms.ii.entities.Users;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2017-02-16T12:32:16")
@StaticMetamodel(OrderLog.class)
public class OrderLog_ { 

    public static volatile SingularAttribute<OrderLog, Orders> orderId;
    public static volatile SingularAttribute<OrderLog, Users> actionBy;
    public static volatile SingularAttribute<OrderLog, String> description;
    public static volatile SingularAttribute<OrderLog, Long> id;

}