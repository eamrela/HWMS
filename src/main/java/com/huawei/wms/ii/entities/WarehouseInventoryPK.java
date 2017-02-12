/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.huawei.wms.ii.entities;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author Amr
 */
@Embeddable
public class WarehouseInventoryPK implements Serializable {

    @Basic(optional = false)
    @Column(name = "warehouse_id")
    private long warehouseId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2147483647)
    @Column(name = "item_id")
    private String itemId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2147483647)
    @Column(name = "status_id")
    private String statusId;

    public WarehouseInventoryPK() {
    }

    public WarehouseInventoryPK(long warehouseId, String itemId, String statusId) {
        this.warehouseId = warehouseId;
        this.itemId = itemId;
        this.statusId = statusId;
    }

    public long getWarehouseId() {
        return warehouseId;
    }

    public void setWarehouseId(long warehouseId) {
        this.warehouseId = warehouseId;
    }

    public String getItemId() {
        return itemId;
    }

    public void setItemId(String itemId) {
        this.itemId = itemId;
    }

    public String getStatusId() {
        return statusId;
    }

    public void setStatusId(String statusId) {
        this.statusId = statusId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) warehouseId;
        hash += (itemId != null ? itemId.hashCode() : 0);
        hash += (statusId != null ? statusId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof WarehouseInventoryPK)) {
            return false;
        }
        WarehouseInventoryPK other = (WarehouseInventoryPK) object;
        if (this.warehouseId != other.warehouseId) {
            return false;
        }
        if ((this.itemId == null && other.itemId != null) || (this.itemId != null && !this.itemId.equals(other.itemId))) {
            return false;
        }
        if ((this.statusId == null && other.statusId != null) || (this.statusId != null && !this.statusId.equals(other.statusId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.huawei.wms.ii.entities.WarehouseInventoryPK[ warehouseId=" + warehouseId + ", itemId=" + itemId + ", statusId=" + statusId + " ]";
    }
    
}
