/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.huawei.wms.ii.beans;

import com.huawei.wms.ii.entities.OrderLineItem;
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
public class OrderLineItemFacade extends AbstractFacade<OrderLineItem> {

    @PersistenceContext(unitName = "com.hw_WMS-II_war_1.0-SNAPSHOTPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public OrderLineItemFacade() {
        super(OrderLineItem.class);
    }

    public List<OrderLineItem> findByUser(Users loggedInuser) {
        return em.createNativeQuery(" select *  " +
                                    " from order_line_item " +
                                    " where order_id in " +
                                    " (select order_id from orders where order_type = 'Issued Material (OTS)' and project = '"+loggedInuser.getProject().getProjectName()+"') " +
                                    " and line_item_status = 'Accepted' " +
                                    " and returnable is null ",OrderLineItem.class).getResultList();
    }

    public List<OrderLineItem> findByUserTransfer(Users loggedInuser) {
         return em.createNativeQuery(" select *  " +
                                    " from order_line_item " +
                                    " where order_id in " +
                                    " (select order_id from orders where order_type = 'Transfer' and project = '"+loggedInuser.getProject().getProjectName()+"') " +
                                    " and line_item_status = 'Accepted' " ,OrderLineItem.class).getResultList();
    }
    
}
