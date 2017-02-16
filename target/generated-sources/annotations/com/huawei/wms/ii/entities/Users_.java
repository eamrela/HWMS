package com.huawei.wms.ii.entities;

import com.huawei.wms.ii.entities.Items;
import com.huawei.wms.ii.entities.OrderLog;
import com.huawei.wms.ii.entities.Orders;
import com.huawei.wms.ii.entities.Project;
import com.huawei.wms.ii.entities.Region;
import com.huawei.wms.ii.entities.WarehouseLog;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2017-02-16T12:32:16")
@StaticMetamodel(Users.class)
public class Users_ { 

    public static volatile SingularAttribute<Users, String> passWord;
    public static volatile SingularAttribute<Users, String> role;
    public static volatile CollectionAttribute<Users, Items> itemsCollection;
    public static volatile SingularAttribute<Users, String> fullName;
    public static volatile SingularAttribute<Users, Project> project;
    public static volatile CollectionAttribute<Users, Orders> ordersCollection;
    public static volatile SingularAttribute<Users, String> userName;
    public static volatile SingularAttribute<Users, String> title;
    public static volatile SingularAttribute<Users, Boolean> enabled;
    public static volatile SingularAttribute<Users, String> emailAddress;
    public static volatile CollectionAttribute<Users, WarehouseLog> warehouseLogCollection;
    public static volatile CollectionAttribute<Users, OrderLog> orderLogCollection;
    public static volatile SingularAttribute<Users, Long> id;
    public static volatile SingularAttribute<Users, Region> region;

}