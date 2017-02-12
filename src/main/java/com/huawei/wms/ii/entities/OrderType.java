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
@Table(name = "order_type")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "OrderType.findAll", query = "SELECT o FROM OrderType o")
    , @NamedQuery(name = "OrderType.findByTypeName", query = "SELECT o FROM OrderType o WHERE o.typeName = :typeName")
    , @NamedQuery(name = "OrderType.findByTypeDescription", query = "SELECT o FROM OrderType o WHERE o.typeDescription = :typeDescription")})
public class OrderType implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2147483647)
    @Column(name = "type_name")
    private String typeName;
    @Size(max = 2147483647)
    @Column(name = "type_description")
    private String typeDescription;
    @OneToMany(mappedBy = "actionType")
    private Collection<WarehouseLog> warehouseLogCollection;
    @OneToMany(mappedBy = "orderType")
    private Collection<Orders> ordersCollection;

    public OrderType() {
    }

    public OrderType(String typeName) {
        this.typeName = typeName;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public String getTypeDescription() {
        return typeDescription;
    }

    public void setTypeDescription(String typeDescription) {
        this.typeDescription = typeDescription;
    }

    @XmlTransient
    public Collection<WarehouseLog> getWarehouseLogCollection() {
        return warehouseLogCollection;
    }

    public void setWarehouseLogCollection(Collection<WarehouseLog> warehouseLogCollection) {
        this.warehouseLogCollection = warehouseLogCollection;
    }

    @XmlTransient
    public Collection<Orders> getOrdersCollection() {
        return ordersCollection;
    }

    public void setOrdersCollection(Collection<Orders> ordersCollection) {
        this.ordersCollection = ordersCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (typeName != null ? typeName.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof OrderType)) {
            return false;
        }
        OrderType other = (OrderType) object;
        if ((this.typeName == null && other.typeName != null) || (this.typeName != null && !this.typeName.equals(other.typeName))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.huawei.wms.ii.entities.OrderType[ typeName=" + typeName + " ]";
    }
    
}
