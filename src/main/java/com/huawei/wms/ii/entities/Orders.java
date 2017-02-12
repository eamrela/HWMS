/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.huawei.wms.ii.entities;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Amr
 */
@Entity
@Table(name = "orders")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Orders.findAll", query = "SELECT o FROM Orders o")
    , @NamedQuery(name = "Orders.findByOrderId", query = "SELECT o FROM Orders o WHERE o.orderId = :orderId")
    , @NamedQuery(name = "Orders.findByLoggingTime", query = "SELECT o FROM Orders o WHERE o.loggingTime = :loggingTime")
    , @NamedQuery(name = "Orders.findByOrderComment", query = "SELECT o FROM Orders o WHERE o.orderComment = :orderComment")
    , @NamedQuery(name = "Orders.findBySite", query = "SELECT o FROM Orders o WHERE o.site = :site")
    , @NamedQuery(name = "Orders.findBySiteLog", query = "SELECT o FROM Orders o WHERE o.siteLog = :siteLog")
    , @NamedQuery(name = "Orders.findByActionDate", query = "SELECT o FROM Orders o WHERE o.actionDate = :actionDate")})
public class Orders implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "order_id")
    private Long orderId;
    @Column(name = "logging_time")
    @Temporal(TemporalType.TIMESTAMP)
    private Date loggingTime;
    @Size(max = 2147483647)
    @Column(name = "order_comment")
    private String orderComment;
    @Size(max = 2147483647)
    @Column(name = "site")
    private String site;
    @Size(max = 2147483647)
    @Column(name = "site_log")
    private String siteLog;
    @Column(name = "action_date")
    @Temporal(TemporalType.DATE)
    private Date actionDate;
    @JoinColumn(name = "engineer", referencedColumnName = "id")
    @ManyToOne
    private Engineers engineer;
    @JoinColumn(name = "order_status", referencedColumnName = "status_name")
    @ManyToOne
    private OrderStatus orderStatus;
    @JoinColumn(name = "order_type", referencedColumnName = "type_name")
    @ManyToOne
    private OrderType orderType;
    @JoinColumn(name = "project", referencedColumnName = "project_name")
    @ManyToOne
    private Project project;
    @JoinColumn(name = "region", referencedColumnName = "region_name")
    @ManyToOne
    private Region region;
    @JoinColumn(name = "created_by", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Users createdBy;
    @JoinColumn(name = "from_warehouse", referencedColumnName = "id")
    @ManyToOne
    private Warehouse fromWarehouse;
    @JoinColumn(name = "to_warehouse", referencedColumnName = "id")
    @ManyToOne
    private Warehouse toWarehouse;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "orderId")
    private Collection<OrderLineItem> orderLineItemCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "orderId")
    private Collection<OrderLog> orderLogCollection;

    public Orders() {
    }

    public Orders(Long orderId) {
        this.orderId = orderId;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public Date getLoggingTime() {
        return loggingTime;
    }

    public void setLoggingTime(Date loggingTime) {
        this.loggingTime = loggingTime;
    }

    public String getOrderComment() {
        return orderComment;
    }

    public void setOrderComment(String orderComment) {
        this.orderComment = orderComment;
    }

    public String getSite() {
        return site;
    }

    public void setSite(String site) {
        this.site = site;
    }

    public String getSiteLog() {
        return siteLog;
    }

    public void setSiteLog(String siteLog) {
        this.siteLog = siteLog;
    }

    public Date getActionDate() {
        return actionDate;
    }

    public void setActionDate(Date actionDate) {
        this.actionDate = actionDate;
    }

    public Engineers getEngineer() {
        return engineer;
    }

    public void setEngineer(Engineers engineer) {
        this.engineer = engineer;
    }

    public OrderStatus getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(OrderStatus orderStatus) {
        this.orderStatus = orderStatus;
    }

    public OrderType getOrderType() {
        return orderType;
    }

    public void setOrderType(OrderType orderType) {
        this.orderType = orderType;
    }

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }

    public Region getRegion() {
        return region;
    }

    public void setRegion(Region region) {
        this.region = region;
    }

    public Users getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(Users createdBy) {
        this.createdBy = createdBy;
    }

    public Warehouse getFromWarehouse() {
        return fromWarehouse;
    }

    public void setFromWarehouse(Warehouse fromWarehouse) {
        this.fromWarehouse = fromWarehouse;
    }

    public Warehouse getToWarehouse() {
        return toWarehouse;
    }

    public void setToWarehouse(Warehouse toWarehouse) {
        this.toWarehouse = toWarehouse;
    }

    @XmlTransient
    public Collection<OrderLineItem> getOrderLineItemCollection() {
        return orderLineItemCollection;
    }

    public void setOrderLineItemCollection(Collection<OrderLineItem> orderLineItemCollection) {
        this.orderLineItemCollection = orderLineItemCollection;
    }

    @XmlTransient
    public Collection<OrderLog> getOrderLogCollection() {
        return orderLogCollection;
    }

    public void setOrderLogCollection(Collection<OrderLog> orderLogCollection) {
        this.orderLogCollection = orderLogCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (orderId != null ? orderId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Orders)) {
            return false;
        }
        Orders other = (Orders) object;
        if ((this.orderId == null && other.orderId != null) || (this.orderId != null && !this.orderId.equals(other.orderId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.huawei.wms.ii.entities.Orders[ orderId=" + orderId + " ]";
    }
    
}
