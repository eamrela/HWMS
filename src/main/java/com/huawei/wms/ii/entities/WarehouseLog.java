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
@Table(name = "warehouse_log")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "WarehouseLog.findAll", query = "SELECT w FROM WarehouseLog w")
    , @NamedQuery(name = "WarehouseLog.findById", query = "SELECT w FROM WarehouseLog w WHERE w.id = :id")
    , @NamedQuery(name = "WarehouseLog.findByLogComment", query = "SELECT w FROM WarehouseLog w WHERE w.logComment = :logComment")
    , @NamedQuery(name = "WarehouseLog.findByPreviousQty", query = "SELECT w FROM WarehouseLog w WHERE w.previousQty = :previousQty")
    , @NamedQuery(name = "WarehouseLog.findByCurrentQty", query = "SELECT w FROM WarehouseLog w WHERE w.currentQty = :currentQty")})
public class WarehouseLog implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Long id;
    @Size(max = 2147483647)
    @Column(name = "log_comment")
    private String logComment;
    @Column(name = "previous_qty")
    private BigInteger previousQty;
    @Column(name = "current_qty")
    private BigInteger currentQty;
    @JoinColumn(name = "action_type", referencedColumnName = "type_name")
    @ManyToOne
    private OrderType actionType;
    @JoinColumn(name = "action_by", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Users actionBy;
    @JoinColumn(name = "warehouse_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Warehouse warehouseId;

    public WarehouseLog() {
    }

    public WarehouseLog(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLogComment() {
        return logComment;
    }

    public void setLogComment(String logComment) {
        this.logComment = logComment;
    }

    public BigInteger getPreviousQty() {
        return previousQty;
    }

    public void setPreviousQty(BigInteger previousQty) {
        this.previousQty = previousQty;
    }

    public BigInteger getCurrentQty() {
        return currentQty;
    }

    public void setCurrentQty(BigInteger currentQty) {
        this.currentQty = currentQty;
    }

    public OrderType getActionType() {
        return actionType;
    }

    public void setActionType(OrderType actionType) {
        this.actionType = actionType;
    }

    public Users getActionBy() {
        return actionBy;
    }

    public void setActionBy(Users actionBy) {
        this.actionBy = actionBy;
    }

    public Warehouse getWarehouseId() {
        return warehouseId;
    }

    public void setWarehouseId(Warehouse warehouseId) {
        this.warehouseId = warehouseId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof WarehouseLog)) {
            return false;
        }
        WarehouseLog other = (WarehouseLog) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.huawei.wms.ii.entities.WarehouseLog[ id=" + id + " ]";
    }
    
}
