package com.huawei.wms.ii.entities;

import com.huawei.wms.ii.entities.Orders;
import com.huawei.wms.ii.entities.Project;
import com.huawei.wms.ii.entities.Region;
import com.huawei.wms.ii.entities.WarehouseInventory;
import com.huawei.wms.ii.entities.WarehouseLog;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2017-02-16T12:32:16")
@StaticMetamodel(Warehouse.class)
public class Warehouse_ { 

    public static volatile SingularAttribute<Warehouse, Region> warehouseRegion;
    public static volatile SingularAttribute<Warehouse, Date> actionDateValidation;
    public static volatile CollectionAttribute<Warehouse, WarehouseLog> warehouseLogCollection;
    public static volatile SingularAttribute<Warehouse, Boolean> main;
    public static volatile SingularAttribute<Warehouse, Long> id;
    public static volatile CollectionAttribute<Warehouse, Orders> ordersCollection;
    public static volatile CollectionAttribute<Warehouse, Orders> ordersCollection1;
    public static volatile SingularAttribute<Warehouse, String> warehouseName;
    public static volatile SingularAttribute<Warehouse, String> mainOffice;
    public static volatile CollectionAttribute<Warehouse, WarehouseInventory> warehouseInventoryCollection;
    public static volatile SingularAttribute<Warehouse, Project> warehouseProject;

}