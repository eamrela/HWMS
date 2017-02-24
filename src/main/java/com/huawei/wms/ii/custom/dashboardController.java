/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.huawei.wms.ii.custom;

import com.huawei.wms.ii.controller.UsersController;
import java.io.Serializable;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Random;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.joda.time.DateTime;

/**
 *
 * @author Amr
 */
@Named("dashboardController")
@SessionScoped
public class dashboardController implements Serializable {
    
    @PersistenceContext(unitName = "com.hw_WMS-II_war_1.0-SNAPSHOTPU")
    private EntityManager em;
    
    private Date startDate = DateTime.now().dayOfWeek().withMinimumValue().toDate();
    private Date endDate = DateTime.now().dayOfWeek().withMaximumValue().toDate();
    private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd 00:00:00");
    
    //<editor-fold defaultstate="collapsed" desc="Top Tiles">
    private Long processedRequests;
    private Long pendingRequests;
    private Long issuedItems;
    private Long transferItems;
    private Long inBoundItems;
    private BigDecimal totalItems;
//</editor-fold>
    //<editor-fold defaultstate="collapsed" desc="Master Graph">
    private String masterGraphData;
//</editor-fold>
    //<editor-fold defaultstate="collapsed" desc="Colors">
    private  String[] mColors = {
            "#39add1", // light blue
            "#3079ab", // dark blue
            "#c25975", // mauve
            "#e15258", // red
            "#f9845b", // orange
            "#838cc7", // lavender
            "#7d669e", // purple
            "#53bbb4", // aqua
            "#51b46d", // green
            "#e0ab18", // mustard
            "#637a91", // dark gray
            "#f092b0", // pink
            "#b7c0c7"  // light gray
    };
//</editor-fold>
    
    @Inject
    private UsersController userController;

    public EntityManager getEm() {
        return em;
    }

    public void setEm(EntityManager em) {
        this.em = em;
    }

    public Long getProcessedRequests() {
        processedRequests = (Long) em.createNativeQuery(
                "select count(*) from orders "
                        + " where logging_time between '"+sdf.format(startDate)+"' and '"+sdf.format(endDate)+"' "
                                + " and project ='"+userController.getLoggedInuser().getProject().getProjectName()+"' ").getSingleResult();
        return processedRequests;
    }

    public void setProcessedRequests(Long processedRequests) {
        this.processedRequests = processedRequests;
    }

    public Long getPendingRequests() {
        pendingRequests = (Long) em.createNativeQuery(
                "select count(*) from orders "
                        + " where logging_time between '"+sdf.format(startDate)+"' and '"+sdf.format(endDate)+"' "
                                + " and order_status != 'Accepted' "
                                + " and project ='"+userController.getLoggedInuser().getProject().getProjectName()+"' ").getSingleResult();
        return pendingRequests;
    }

    public void setPendingRequests(Long pendingRequests) {
        this.pendingRequests = pendingRequests;
    }

    public Long getIssuedItems() {
        issuedItems = (Long) em.createNativeQuery(
                "select count(*) from orders "
                        + " where logging_time between '"+sdf.format(startDate)+"' and '"+sdf.format(endDate)+"' "
                                + " and order_type = 'Issued Material (OTS)' "
                                + " and project ='"+userController.getLoggedInuser().getProject().getProjectName()+"'").getSingleResult();
        return issuedItems;
    }

    public void setIssuedItems(Long issuedItems) {
        this.issuedItems = issuedItems;
    }

    public Long getTransferItems() {
        transferItems = (Long) em.createNativeQuery(
                "select count(*) from orders "
                        + " where logging_time between '"+sdf.format(startDate)+"' and '"+sdf.format(endDate)+"' "
                                + " and order_type = 'Transfer' "
                                + " and project ='"+userController.getLoggedInuser().getProject().getProjectName()+"' ").getSingleResult();
        return transferItems;
    }

    public void setTransferItems(Long transferItems) {
        this.transferItems = transferItems;
    }

    public Long getInBoundItems() {
        inBoundItems = (Long) em.createNativeQuery(
                "select count(*) from orders "
                        + " where logging_time between '"+sdf.format(startDate)+"' and '"+sdf.format(endDate)+"' "
                                + " and order_type = 'In-Bound' "
                                + " and project ='"+userController.getLoggedInuser().getProject().getProjectName()+"' ").getSingleResult();
        return inBoundItems;
    }

    public void setInBoundItems(Long inBoundItems) {
        this.inBoundItems = inBoundItems;
    }

    public BigDecimal getTotalItems() {
        totalItems = (BigDecimal) em.createNativeQuery(
                " select sum(qty) from warehouse_inventory  " +
                " where warehouse_id in (select id from warehouse where "
                + " warehouse_project='"+userController.getLoggedInuser().getProject().getProjectName()+"')").getSingleResult();
        return totalItems;
    }

    public void setTotalItems(BigDecimal totalItems) {
        this.totalItems = totalItems;
    }

    public UsersController getUserController() {
        return userController;
    }

    public void setUserController(UsersController userController) {
        this.userController = userController;
    }

    public Date getStartDate() {
        return startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public String getMasterGraphData() {
        //<editor-fold defaultstate="collapsed" desc="StartString">
        masterGraphData = "{ " +
                            "    \"theme\": \"light\", " +
                            "    \"type\": \"serial\", " +
                            "	\"startDuration\": 1, " +
                            "    \"dataProvider\": [";
//</editor-fold>
        //<editor-fold defaultstate="collapsed" desc="Data Access">
       String query = "select warehouse_name , " +
"(select COALESCE(sum(qty),0)  " +
" from order_line_item " +
" where order_id in (select order_id " +
"		   from orders " +
"		   where order_type = 'Issued Material (OTS)' " +
"		   and from_warehouse = w.id " +
"		   and project='"+userController.getLoggedInuser().getProject().getProjectName()+"'))" +
"from warehouse w";
       
       List<Object[]> result = em.createNativeQuery(query).getResultList();
//</editor-fold>
        for (int i = 0; i < result.size(); i++) {
            if(i==result.size()-1){
                masterGraphData+="{ " +
                                "        \"warehouse\": \""+result.get(i)[0]+"\", " +
                                "        \"items\": "+result.get(i)[1]+", " +
                                "        \"color\": \""+getColor()+"\" " +
                                "    }";
            }else{
                masterGraphData+="{ " +
                                "        \"warehouse\": \""+result.get(i)[0]+"\", " +
                                "        \"items\": "+result.get(i)[1]+", " +
                                "        \"color\": \""+getColor()+"\" " +
                                "    },";
            }
        }
        //<editor-fold defaultstate="collapsed" desc="EndString">
      masterGraphData += "],\n" +
"    \"valueAxes\": [{\n" +
"        \"position\": \"left\",\n" +
"        \"title\": \"Items Consumed\"\n" +
"    }],\n" +
"    \"graphs\": [{\n" +
"        \"balloonText\": \"[[warehouse]]: <b>[[items]]</b>\",\n" +
"        \"fillColorsField\": \"color\",\n" +
"        \"fillAlphas\": 1,\n" +
"        \"lineAlpha\": 0.1,\n" +
"        \"type\": \"column\",\n" +
"        \"valueField\": \"items\"\n" +
"    }],\n" +
"    \"depth3D\": 20,\n" +
"	\"angle\": 30,\n" +
"    \"chartCursor\": {\n" +
"        \"categoryBalloonEnabled\": false,\n" +
"        \"cursorAlpha\": 0,\n" +
"        \"zoomable\": false\n" +
"    },\n" +
"    \"categoryField\": \"warehouse\",\n" +
"    \"categoryAxis\": {\n" +
"        \"gridPosition\": \"start\",\n" +
"        \"labelRotation\": 90\n" +
"    },\n" +
"    \"export\": {\n" +
"    	\"enabled\": true\n" +
"     }\n" +
"\n" +
"}";
//</editor-fold>
        return masterGraphData;
    }

    public String getColor() {
        Random randomGenerator = new Random(); // Construct a new Random number generator
        int randomNumber = randomGenerator.nextInt(mColors.length);
        return mColors[randomNumber];
    }
    
    public List<Object[]> getTopIssuedItems(){
        List<Object[]> result =  em.createNativeQuery("select '('||i.huawei_bom||')'||' | '||i.english_description item,sum(qty)\n" +
                                                    "from order_line_item li , items i\n" +
                                                    "where li.item_id = i.huawei_bom\n" +
                                                    "and order_id in (select order_id from orders where project='"+userController.getLoggedInuser().getProject().getProjectName()+"' and order_type='Issued Material (OTS)')\n" +
                                                    "group by '('||i.huawei_bom||')'||' | '||i.english_description\n" +
                                                    "order by sum(qty) desc\n" +
                                                    "limit 4").getResultList();
        BigDecimal sum = BigDecimal.ZERO;
        for (int i = 0; i < result.size(); i++) {
            sum = sum.add((BigDecimal) result.get(i)[1]);
        }
        for (int i = 0; i < result.size(); i++) {
            result.get(i)[1] = (((BigDecimal)result.get(i)[1]).divide(sum)).multiply(BigDecimal.valueOf(100.0));
        }
        return result;
    }
}
