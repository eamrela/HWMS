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
@Table(name = "warehouse")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Warehouse.findAll", query = "SELECT w FROM Warehouse w")
    , @NamedQuery(name = "Warehouse.findById", query = "SELECT w FROM Warehouse w WHERE w.id = :id")
    , @NamedQuery(name = "Warehouse.findByWarehouseName", query = "SELECT w FROM Warehouse w WHERE w.warehouseName = :warehouseName")
    , @NamedQuery(name = "Warehouse.findByMainOffice", query = "SELECT w FROM Warehouse w WHERE w.mainOffice = :mainOffice")
    , @NamedQuery(name = "Warehouse.findByMain", query = "SELECT w FROM Warehouse w WHERE w.main = :main")
    , @NamedQuery(name = "Warehouse.findByActionDateValidation", query = "SELECT w FROM Warehouse w WHERE w.actionDateValidation = :actionDateValidation")})
public class Warehouse implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Long id;
    @Size(max = 2147483647)
    @Column(name = "warehouse_name")
    private String warehouseName;
    @Size(max = 2147483647)
    @Column(name = "main_office")
    private String mainOffice;
    @Column(name = "main")
    private Boolean main;
    @Column(name = "action_date_validation")
    @Temporal(TemporalType.DATE)
    private Date actionDateValidation;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "warehouseId")
    private Collection<WarehouseLog> warehouseLogCollection;
    @JoinColumn(name = "warehouse_project", referencedColumnName = "project_name")
    @ManyToOne
    private Project warehouseProject;
    @JoinColumn(name = "warehouse_region", referencedColumnName = "region_name")
    @ManyToOne
    private Region warehouseRegion;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "warehouse")
    private Collection<WarehouseInventory> warehouseInventoryCollection;
    @OneToMany(mappedBy = "fromWarehouse")
    private Collection<Orders> ordersCollection;
    @OneToMany(mappedBy = "toWarehouse")
    private Collection<Orders> ordersCollection1;

    public Warehouse() {
    }

    public Warehouse(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getWarehouseName() {
        return warehouseName;
    }

    public void setWarehouseName(String warehouseName) {
        this.warehouseName = warehouseName;
    }

    public String getMainOffice() {
        return mainOffice;
    }

    public void setMainOffice(String mainOffice) {
        this.mainOffice = mainOffice;
    }

    public Boolean getMain() {
        return main;
    }

    public void setMain(Boolean main) {
        this.main = main;
    }

    public Date getActionDateValidation() {
        return actionDateValidation;
    }

    public void setActionDateValidation(Date actionDateValidation) {
        this.actionDateValidation = actionDateValidation;
    }

    @XmlTransient
    public Collection<WarehouseLog> getWarehouseLogCollection() {
        return warehouseLogCollection;
    }

    public void setWarehouseLogCollection(Collection<WarehouseLog> warehouseLogCollection) {
        this.warehouseLogCollection = warehouseLogCollection;
    }

    public Project getWarehouseProject() {
        return warehouseProject;
    }

    public void setWarehouseProject(Project warehouseProject) {
        this.warehouseProject = warehouseProject;
    }

    public Region getWarehouseRegion() {
        return warehouseRegion;
    }

    public void setWarehouseRegion(Region warehouseRegion) {
        this.warehouseRegion = warehouseRegion;
    }

    @XmlTransient
    public Collection<WarehouseInventory> getWarehouseInventoryCollection() {
        return warehouseInventoryCollection;
    }

    public void setWarehouseInventoryCollection(Collection<WarehouseInventory> warehouseInventoryCollection) {
        this.warehouseInventoryCollection = warehouseInventoryCollection;
    }

    @XmlTransient
    public Collection<Orders> getOrdersCollection() {
        return ordersCollection;
    }

    public void setOrdersCollection(Collection<Orders> ordersCollection) {
        this.ordersCollection = ordersCollection;
    }

    @XmlTransient
    public Collection<Orders> getOrdersCollection1() {
        return ordersCollection1;
    }

    public void setOrdersCollection1(Collection<Orders> ordersCollection1) {
        this.ordersCollection1 = ordersCollection1;
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
        if (!(object instanceof Warehouse)) {
            return false;
        }
        Warehouse other = (Warehouse) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.huawei.wms.ii.entities.Warehouse[ id=" + id + " ]";
    }
    
}
