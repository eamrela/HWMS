/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.huawei.wms.ii.entities;

import java.io.Serializable;
import java.math.BigInteger;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Amr
 */
@Entity
@Table(name = "order_line_item")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "OrderLineItem.findAll", query = "SELECT o FROM OrderLineItem o")
    , @NamedQuery(name = "OrderLineItem.findByLineItemId", query = "SELECT o FROM OrderLineItem o WHERE o.lineItemId = :lineItemId")
    , @NamedQuery(name = "OrderLineItem.findByQty", query = "SELECT o FROM OrderLineItem o WHERE o.qty = :qty")
    , @NamedQuery(name = "OrderLineItem.findByLineItemComment", query = "SELECT o FROM OrderLineItem o WHERE o.lineItemComment = :lineItemComment")
    , @NamedQuery(name = "OrderLineItem.findByReturnable", query = "SELECT o FROM OrderLineItem o WHERE o.returnable = :returnable")})
public class OrderLineItem implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "line_item_id")
    private Long lineItemId;
    @Column(name = "qty")
    private BigInteger qty;
    @Size(max = 2147483647)
    @Column(name = "line_item_comment")
    private String lineItemComment;
    @Column(name = "returnable")
    private Boolean returnable;
    @JoinColumn(name = "returnable_status", referencedColumnName = "status_name")
    @ManyToOne
    private ItemStatus returnableStatus;
    @JoinColumn(name = "item_id", referencedColumnName = "huawei_bom")
    @ManyToOne
    private Items itemId;
    @JoinColumn(name = "line_item_status", referencedColumnName = "status_name")
    @ManyToOne
    private OrderLineItemStatus lineItemStatus;
    @JoinColumn(name = "order_id", referencedColumnName = "order_id")
    @ManyToOne(optional = false)
    private Orders orderId;

    public OrderLineItem() {
    }

    public OrderLineItem(Long lineItemId) {
        this.lineItemId = lineItemId;
    }

    public Long getLineItemId() {
        return lineItemId;
    }

    public void setLineItemId(Long lineItemId) {
        this.lineItemId = lineItemId;
    }

    public BigInteger getQty() {
        return qty;
    }

    public void setQty(BigInteger qty) {
        this.qty = qty;
    }

    public String getLineItemComment() {
        return lineItemComment;
    }

    public void setLineItemComment(String lineItemComment) {
        this.lineItemComment = lineItemComment;
    }

    public Boolean getReturnable() {
        return returnable;
    }

    public void setReturnable(Boolean returnable) {
        this.returnable = returnable;
    }

    public ItemStatus getReturnableStatus() {
        return returnableStatus;
    }

    public void setReturnableStatus(ItemStatus returnableStatus) {
        this.returnableStatus = returnableStatus;
    }

    public Items getItemId() {
        return itemId;
    }

    public void setItemId(Items itemId) {
        this.itemId = itemId;
    }

    public OrderLineItemStatus getLineItemStatus() {
        return lineItemStatus;
    }

    public void setLineItemStatus(OrderLineItemStatus lineItemStatus) {
        this.lineItemStatus = lineItemStatus;
    }

    public Orders getOrderId() {
        return orderId;
    }

    public void setOrderId(Orders orderId) {
        this.orderId = orderId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (lineItemId != null ? lineItemId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof OrderLineItem)) {
            return false;
        }
        OrderLineItem other = (OrderLineItem) object;
        if ((this.lineItemId == null && other.lineItemId != null) || (this.lineItemId != null && !this.lineItemId.equals(other.lineItemId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.huawei.wms.ii.entities.OrderLineItem[ lineItemId=" + lineItemId + " ]";
    }
    
}
