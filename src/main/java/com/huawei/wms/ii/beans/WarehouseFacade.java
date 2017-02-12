/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.huawei.wms.ii.beans;

import com.huawei.wms.ii.entities.Users;
import com.huawei.wms.ii.entities.Warehouse;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Amr
 */
@Stateless
public class WarehouseFacade extends AbstractFacade<Warehouse> {

    @PersistenceContext(unitName = "com.hw_WMS-II_war_1.0-SNAPSHOTPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public WarehouseFacade() {
        super(Warehouse.class);
    }

    public List<Warehouse> findbyProjectAndRegion(Users loggedInuser) {
        return em.createNativeQuery("select * from warehouse "
                + " where warehouse_project = '"+loggedInuser.getProject().getProjectName()+"'  "
                        + " and warehouse_region='"+loggedInuser.getRegion().getRegionName()+"' ", Warehouse.class).getResultList();
    }

    public List<Warehouse> findbyProject(Users loggedInuser) {
        return em.createNativeQuery("select * from warehouse "
                + " where warehouse_project = '"+loggedInuser.getProject().getProjectName()+"'  ", Warehouse.class).getResultList();
    }
    
}
