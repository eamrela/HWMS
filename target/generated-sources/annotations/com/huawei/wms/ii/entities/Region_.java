package com.huawei.wms.ii.entities;

import com.huawei.wms.ii.entities.Engineers;
import com.huawei.wms.ii.entities.Orders;
import com.huawei.wms.ii.entities.Users;
import com.huawei.wms.ii.entities.Warehouse;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2017-02-24T21:38:05")
@StaticMetamodel(Region.class)
public class Region_ { 

    public static volatile SingularAttribute<Region, String> regionName;
    public static volatile CollectionAttribute<Region, Warehouse> warehouseCollection;
    public static volatile CollectionAttribute<Region, Orders> ordersCollection;
    public static volatile SingularAttribute<Region, String> regionDescription;
    public static volatile CollectionAttribute<Region, Users> usersCollection;
    public static volatile CollectionAttribute<Region, Engineers> engineersCollection;

}