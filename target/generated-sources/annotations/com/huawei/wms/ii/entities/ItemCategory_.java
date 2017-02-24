package com.huawei.wms.ii.entities;

import com.huawei.wms.ii.entities.Items;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2017-02-24T21:38:05")
@StaticMetamodel(ItemCategory.class)
public class ItemCategory_ { 

    public static volatile CollectionAttribute<ItemCategory, Items> itemsCollection;
    public static volatile SingularAttribute<ItemCategory, String> categoryName;
    public static volatile SingularAttribute<ItemCategory, String> categoryDescription;

}