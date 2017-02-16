package com.huawei.wms.ii.entities;

import com.huawei.wms.ii.entities.ItemCategory;
import com.huawei.wms.ii.entities.OrderLineItem;
import com.huawei.wms.ii.entities.Project;
import com.huawei.wms.ii.entities.Users;
import com.huawei.wms.ii.entities.WarehouseInventory;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2017-02-16T12:32:16")
@StaticMetamodel(Items.class)
public class Items_ { 

    public static volatile SingularAttribute<Items, String> otherCode;
    public static volatile SingularAttribute<Items, String> englishDescription;
    public static volatile SingularAttribute<Items, ItemCategory> itemCategory;
    public static volatile SingularAttribute<Items, Users> addedBy;
    public static volatile SingularAttribute<Items, Project> project;
    public static volatile CollectionAttribute<Items, WarehouseInventory> warehouseInventoryCollection;
    public static volatile CollectionAttribute<Items, OrderLineItem> orderLineItemCollection;
    public static volatile SingularAttribute<Items, String> unit;
    public static volatile SingularAttribute<Items, String> customerOracleCode;
    public static volatile SingularAttribute<Items, String> price;
    public static volatile SingularAttribute<Items, String> supplier;
    public static volatile SingularAttribute<Items, String> huaweiBom;
    public static volatile SingularAttribute<Items, String> arabicDescription;
    public static volatile SingularAttribute<Items, String> brand;

}