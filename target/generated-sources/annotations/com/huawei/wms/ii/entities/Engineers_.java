package com.huawei.wms.ii.entities;

import com.huawei.wms.ii.entities.Orders;
import com.huawei.wms.ii.entities.Project;
import com.huawei.wms.ii.entities.Region;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2017-02-24T21:38:05")
@StaticMetamodel(Engineers.class)
public class Engineers_ { 

    public static volatile SingularAttribute<Engineers, String> fullName;
    public static volatile SingularAttribute<Engineers, Project> project;
    public static volatile SingularAttribute<Engineers, String> company;
    public static volatile SingularAttribute<Engineers, Long> id;
    public static volatile CollectionAttribute<Engineers, Orders> ordersCollection;
    public static volatile SingularAttribute<Engineers, Region> region;

}