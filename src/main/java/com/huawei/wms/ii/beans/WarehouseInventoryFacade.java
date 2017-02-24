/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.huawei.wms.ii.beans;

import com.huawei.wms.ii.entities.ItemStatus;
import com.huawei.wms.ii.entities.Items;
import com.huawei.wms.ii.entities.Users;
import com.huawei.wms.ii.entities.Warehouse;
import com.huawei.wms.ii.entities.WarehouseInventory;
import java.math.BigInteger;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Amr
 */
@Stateless
public class WarehouseInventoryFacade extends AbstractFacade<WarehouseInventory> {

    @PersistenceContext(unitName = "com.hw_WMS-II_war_1.0-SNAPSHOTPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public WarehouseInventoryFacade() {
        super(WarehouseInventory.class);
    }

    public BigInteger findGoodorUsedQty(Items itemId, Warehouse fromWarehouse) {
        List<WarehouseInventory> result = em.createNativeQuery(
               "select * from warehouse_inventory " +
                "where status_id in ('Good','Used') " +
                "and warehouse_id= "+fromWarehouse.getId() +
                "and item_id = '"+itemId.getHuaweiBom()+"' ", 
                WarehouseInventory.class).getResultList();
        BigInteger qty = BigInteger.ZERO;
        for (int i = 0; i < result.size(); i++) {
            qty = qty.add(result.get(i).getQty());
        }
        
        return qty;
    }

    public WarehouseInventory findGoodOrUsedItem(Warehouse fromWarehouse, Items itemId) {
         List<WarehouseInventory> result = em.createNativeQuery(
               "select * from warehouse_inventory " +
                "where status_id in ('Good','Used') " +
                " and warehouse_id= "+fromWarehouse.getId() +
                " and item_id = '"+itemId.getHuaweiBom()+"' ", 
                WarehouseInventory.class).getResultList();
        if(result.size()>0){
            return result.get(0);
        }
        
        return null;
    }

    public WarehouseInventory findItem(Warehouse toWarehouse, Items itemId, ItemStatus itemStatus) {
        List<WarehouseInventory> result = em.createNativeQuery(
               "select * from warehouse_inventory " +
                "where status_id ='"+itemStatus.getStatusName()+"' " +
                "and warehouse_id= "+toWarehouse.getId() +
                "and item_id = '"+itemId.getHuaweiBom()+"' ", 
                WarehouseInventory.class).getResultList();
        if(result.size()>0){
            return result.get(0);
        }
        return null;
    }

    public List<WarehouseInventory> findByUser(Users loggedInuser) {
        return em.createNativeQuery(" select * from warehouse_inventory  " +
                                    " where warehouse_id in  " +
                                    " (select id from warehouse where warehouse_project = '"+loggedInuser.getProject().getProjectName()+"') " +
                                    " order by warehouse_id ",WarehouseInventory.class).getResultList();
    }
    
}
