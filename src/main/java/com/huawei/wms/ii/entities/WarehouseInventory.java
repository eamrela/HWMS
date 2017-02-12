/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.huawei.wms.ii.entities;

import java.io.Serializable;
import java.math.BigInteger;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Amr
 */
@Entity
@Table(name = "warehouse_inventory")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "WarehouseInventory.findAll", query = "SELECT w FROM WarehouseInventory w")
    , @NamedQuery(name = "WarehouseInventory.findByWarehouseId", query = "SELECT w FROM WarehouseInventory w WHERE w.warehouseInventoryPK.warehouseId = :warehouseId")
    , @NamedQuery(name = "WarehouseInventory.findByItemId", query = "SELECT w FROM WarehouseInventory w WHERE w.warehouseInventoryPK.itemId = :itemId")
    , @NamedQuery(name = "WarehouseInventory.findByStatusId", query = "SELECT w FROM WarehouseInventory w WHERE w.warehouseInventoryPK.statusId = :statusId")
    , @NamedQuery(name = "WarehouseInventory.findByQty", query = "SELECT w FROM WarehouseInventory w WHERE w.qty = :qty")})
public class WarehouseInventory implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected WarehouseInventoryPK warehouseInventoryPK;
    @Column(name = "qty")
    private BigInteger qty;
    @JoinColumn(name = "status_id", referencedColumnName = "status_name", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private ItemStatus itemStatus;
    @JoinColumn(name = "item_id", referencedColumnName = "huawei_bom", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Items items;
    @JoinColumn(name = "warehouse_id", referencedColumnName = "id", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Warehouse warehouse;

    public WarehouseInventory() {
    }

    public WarehouseInventory(WarehouseInventoryPK warehouseInventoryPK) {
        this.warehouseInventoryPK = warehouseInventoryPK;
    }

    public WarehouseInventory(long warehouseId, String itemId, String statusId) {
        this.warehouseInventoryPK = new WarehouseInventoryPK(warehouseId, itemId, statusId);
    }

    public WarehouseInventoryPK getWarehouseInventoryPK() {
        return warehouseInventoryPK;
    }

    public void setWarehouseInventoryPK(WarehouseInventoryPK warehouseInventoryPK) {
        this.warehouseInventoryPK = warehouseInventoryPK;
    }

    public BigInteger getQty() {
        return qty;
    }

    public void setQty(BigInteger qty) {
        this.qty = qty;
    }

    public ItemStatus getItemStatus() {
        return itemStatus;
    }

    public void setItemStatus(ItemStatus itemStatus) {
        this.itemStatus = itemStatus;
    }

    public Items getItems() {
        return items;
    }

    public void setItems(Items items) {
        this.items = items;
    }

    public Warehouse getWarehouse() {
        return warehouse;
    }

    public void setWarehouse(Warehouse warehouse) {
        this.warehouse = warehouse;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (warehouseInventoryPK != null ? warehouseInventoryPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof WarehouseInventory)) {
            return false;
        }
        WarehouseInventory other = (WarehouseInventory) object;
        if ((this.warehouseInventoryPK == null && other.warehouseInventoryPK != null) || (this.warehouseInventoryPK != null && !this.warehouseInventoryPK.equals(other.warehouseInventoryPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.huawei.wms.ii.entities.WarehouseInventory[ warehouseInventoryPK=" + warehouseInventoryPK + " ]";
    }
    
}
