/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.huawei.wms.ii.entities;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Amr
 */
@Entity
@Table(name = "order_line_item_status")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "OrderLineItemStatus.findAll", query = "SELECT o FROM OrderLineItemStatus o")
    , @NamedQuery(name = "OrderLineItemStatus.findByStatusName", query = "SELECT o FROM OrderLineItemStatus o WHERE o.statusName = :statusName")
    , @NamedQuery(name = "OrderLineItemStatus.findByStatusDescription", query = "SELECT o FROM OrderLineItemStatus o WHERE o.statusDescription = :statusDescription")})
public class OrderLineItemStatus implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2147483647)
    @Column(name = "status_name")
    private String statusName;
    @Size(max = 2147483647)
    @Column(name = "status_description")
    private String statusDescription;
    @OneToMany(mappedBy = "lineItemStatus")
    private Collection<OrderLineItem> orderLineItemCollection;

    public OrderLineItemStatus() {
    }

    public OrderLineItemStatus(String statusName) {
        this.statusName = statusName;
    }

    public String getStatusName() {
        return statusName;
    }

    public void setStatusName(String statusName) {
        this.statusName = statusName;
    }

    public String getStatusDescription() {
        return statusDescription;
    }

    public void setStatusDescription(String statusDescription) {
        this.statusDescription = statusDescription;
    }

    @XmlTransient
    public Collection<OrderLineItem> getOrderLineItemCollection() {
        return orderLineItemCollection;
    }

    public void setOrderLineItemCollection(Collection<OrderLineItem> orderLineItemCollection) {
        this.orderLineItemCollection = orderLineItemCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (statusName != null ? statusName.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof OrderLineItemStatus)) {
            return false;
        }
        OrderLineItemStatus other = (OrderLineItemStatus) object;
        if ((this.statusName == null && other.statusName != null) || (this.statusName != null && !this.statusName.equals(other.statusName))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.huawei.wms.ii.entities.OrderLineItemStatus[ statusName=" + statusName + " ]";
    }
    
}
