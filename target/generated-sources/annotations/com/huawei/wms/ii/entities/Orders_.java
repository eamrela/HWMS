package com.huawei.wms.ii.entities;

import com.huawei.wms.ii.entities.Engineers;
import com.huawei.wms.ii.entities.OrderLineItem;
import com.huawei.wms.ii.entities.OrderLog;
import com.huawei.wms.ii.entities.OrderStatus;
import com.huawei.wms.ii.entities.OrderType;
import com.huawei.wms.ii.entities.Project;
import com.huawei.wms.ii.entities.Region;
import com.huawei.wms.ii.entities.Users;
import com.huawei.wms.ii.entities.Warehouse;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2017-02-16T12:32:16")
@StaticMetamodel(Orders.class)
public class Orders_ { 

    public static volatile SingularAttribute<Orders, OrderType> orderType;
    public static volatile SingularAttribute<Orders, String> orderComment;
    public static volatile SingularAttribute<Orders, Long> orderId;
    public static volatile SingularAttribute<Orders, Date> loggingTime;
    public static volatile SingularAttribute<Orders, OrderStatus> orderStatus;
    public static volatile SingularAttribute<Orders, Project> project;
    public static volatile SingularAttribute<Orders, Engineers> engineer;
    public static volatile CollectionAttribute<Orders, OrderLineItem> orderLineItemCollection;
    public static volatile SingularAttribute<Orders, String> site;
    public static volatile SingularAttribute<Orders, Warehouse> toWarehouse;
    public static volatile SingularAttribute<Orders, Users> createdBy;
    public static volatile SingularAttribute<Orders, String> siteLog;
    public static volatile SingularAttribute<Orders, Warehouse> fromWarehouse;
    public static volatile CollectionAttribute<Orders, OrderLog> orderLogCollection;
    public static volatile SingularAttribute<Orders, Region> region;
    public static volatile SingularAttribute<Orders, Date> actionDate;

}