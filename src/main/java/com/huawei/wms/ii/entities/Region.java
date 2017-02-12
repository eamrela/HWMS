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
@Table(name = "region")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Region.findAll", query = "SELECT r FROM Region r")
    , @NamedQuery(name = "Region.findByRegionName", query = "SELECT r FROM Region r WHERE r.regionName = :regionName")
    , @NamedQuery(name = "Region.findByRegionDescription", query = "SELECT r FROM Region r WHERE r.regionDescription = :regionDescription")})
public class Region implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2147483647)
    @Column(name = "region_name")
    private String regionName;
    @Size(max = 2147483647)
    @Column(name = "region_description")
    private String regionDescription;
    @OneToMany(mappedBy = "region")
    private Collection<Engineers> engineersCollection;
    @OneToMany(mappedBy = "warehouseRegion")
    private Collection<Warehouse> warehouseCollection;
    @OneToMany(mappedBy = "region")
    private Collection<Users> usersCollection;
    @OneToMany(mappedBy = "region")
    private Collection<Orders> ordersCollection;

    public Region() {
    }

    public Region(String regionName) {
        this.regionName = regionName;
    }

    public String getRegionName() {
        return regionName;
    }

    public void setRegionName(String regionName) {
        this.regionName = regionName;
    }

    public String getRegionDescription() {
        return regionDescription;
    }

    public void setRegionDescription(String regionDescription) {
        this.regionDescription = regionDescription;
    }

    @XmlTransient
    public Collection<Engineers> getEngineersCollection() {
        return engineersCollection;
    }

    public void setEngineersCollection(Collection<Engineers> engineersCollection) {
        this.engineersCollection = engineersCollection;
    }

    @XmlTransient
    public Collection<Warehouse> getWarehouseCollection() {
        return warehouseCollection;
    }

    public void setWarehouseCollection(Collection<Warehouse> warehouseCollection) {
        this.warehouseCollection = warehouseCollection;
    }

    @XmlTransient
    public Collection<Users> getUsersCollection() {
        return usersCollection;
    }

    public void setUsersCollection(Collection<Users> usersCollection) {
        this.usersCollection = usersCollection;
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
        hash += (regionName != null ? regionName.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Region)) {
            return false;
        }
        Region other = (Region) object;
        if ((this.regionName == null && other.regionName != null) || (this.regionName != null && !this.regionName.equals(other.regionName))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.huawei.wms.ii.entities.Region[ regionName=" + regionName + " ]";
    }
    
}
