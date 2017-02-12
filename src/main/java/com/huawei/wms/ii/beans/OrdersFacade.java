/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.huawei.wms.ii.beans;

import com.huawei.wms.ii.entities.Orders;
import com.huawei.wms.ii.entities.Users;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Amr
 */
@Stateless
public class OrdersFacade extends AbstractFacade<Orders> {

    @PersistenceContext(unitName = "com.hw_WMS-II_war_1.0-SNAPSHOTPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public OrdersFacade() {
        super(Orders.class);
    }

    public List<Orders> findOrdersByProjectRegionOffice(Users loggedInuser) {
        return em.createNativeQuery("select * from orders where project='"+loggedInuser.getProject().getProjectName()+"' "
                + " and region='"+loggedInuser.getRegion().getRegionName()+"' and order_status != 'Accepted' ", Orders.class).getResultList();
    }
    
}
