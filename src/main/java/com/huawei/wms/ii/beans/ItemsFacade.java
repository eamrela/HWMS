/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.huawei.wms.ii.beans;

import com.huawei.wms.ii.entities.Items;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Amr
 */
@Stateless
public class ItemsFacade extends AbstractFacade<Items> {

    @PersistenceContext(unitName = "com.hw_WMS-II_war_1.0-SNAPSHOTPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ItemsFacade() {
        super(Items.class);
    }
    
}
