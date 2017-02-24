package com.huawei.wms.ii.entities;

import com.huawei.wms.ii.entities.Engineers;
import com.huawei.wms.ii.entities.Items;
import com.huawei.wms.ii.entities.Orders;
import com.huawei.wms.ii.entities.Users;
import com.huawei.wms.ii.entities.Warehouse;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2017-02-24T21:38:05")
@StaticMetamodel(Project.class)
public class Project_ { 

    public static volatile SingularAttribute<Project, String> projectDescription;
    public static volatile CollectionAttribute<Project, Items> itemsCollection;
    public static volatile CollectionAttribute<Project, Warehouse> warehouseCollection;
    public static volatile CollectionAttribute<Project, Orders> ordersCollection;
    public static volatile SingularAttribute<Project, String> projectName;
    public static volatile CollectionAttribute<Project, Users> usersCollection;
    public static volatile CollectionAttribute<Project, Engineers> engineersCollection;

}