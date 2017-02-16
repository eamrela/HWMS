package com.huawei.wms.ii.entities;

import com.huawei.wms.ii.entities.OrderType;
import com.huawei.wms.ii.entities.Users;
import com.huawei.wms.ii.entities.Warehouse;
import java.math.BigInteger;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2017-02-16T12:32:16")
@StaticMetamodel(WarehouseLog.class)
public class WarehouseLog_ { 

    public static volatile SingularAttribute<WarehouseLog, OrderType> actionType;
    public static volatile SingularAttribute<WarehouseLog, String> logComment;
    public static volatile SingularAttribute<WarehouseLog, BigInteger> previousQty;
    public static volatile SingularAttribute<WarehouseLog, Users> actionBy;
    public static volatile SingularAttribute<WarehouseLog, Warehouse> warehouseId;
    public static volatile SingularAttribute<WarehouseLog, BigInteger> currentQty;
    public static volatile SingularAttribute<WarehouseLog, Long> id;

}