/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.huawei.wms.ii.beans;

import com.huawei.wms.ii.entities.Engineers;
import com.huawei.wms.ii.entities.Project;
import com.huawei.wms.ii.entities.Region;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Amr
 */
@Stateless
public class EngineersFacade extends AbstractFacade<Engineers> {

    @PersistenceContext(unitName = "com.hw_WMS-II_war_1.0-SNAPSHOTPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public EngineersFacade() {
        super(Engineers.class);
    }

    public List<Engineers> findItemByProjectAndRegion(Project project, Region region) {
        return em.createNativeQuery("select * from engineers where project='"+project.getProjectName()+"' "
                + " and region='"+region.getRegionName()+"' ", Engineers.class).getResultList();
    }
    
}
