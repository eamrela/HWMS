/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.huawei.wms.ii.entities;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@Table(name = "items")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Items.findAll", query = "SELECT i FROM Items i")
    , @NamedQuery(name = "Items.findByHuaweiBom", query = "SELECT i FROM Items i WHERE i.huaweiBom = :huaweiBom")
    , @NamedQuery(name = "Items.findByEnglishDescription", query = "SELECT i FROM Items i WHERE i.englishDescription = :englishDescription")
    , @NamedQuery(name = "Items.findByArabicDescription", query = "SELECT i FROM Items i WHERE i.arabicDescription = :arabicDescription")
    , @NamedQuery(name = "Items.findByCustomerOracleCode", query = "SELECT i FROM Items i WHERE i.customerOracleCode = :customerOracleCode")
    , @NamedQuery(name = "Items.findByOtherCode", query = "SELECT i FROM Items i WHERE i.otherCode = :otherCode")
    , @NamedQuery(name = "Items.findByBrand", query = "SELECT i FROM Items i WHERE i.brand = :brand")
    , @NamedQuery(name = "Items.findBySupplier", query = "SELECT i FROM Items i WHERE i.supplier = :supplier")
    , @NamedQuery(name = "Items.findByUnit", query = "SELECT i FROM Items i WHERE i.unit = :unit")
    , @NamedQuery(name = "Items.findByPrice", query = "SELECT i FROM Items i WHERE i.price = :price")})
public class Items implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2147483647)
    @Column(name = "huawei_bom")
    private String huaweiBom;
    @Size(max = 2147483647)
    @Column(name = "english_description")
    private String englishDescription;
    @Size(max = 2147483647)
    @Column(name = "arabic_description")
    private String arabicDescription;
    @Size(max = 2147483647)
    @Column(name = "customer_oracle_code")
    private String customerOracleCode;
    @Size(max = 2147483647)
    @Column(name = "other_code")
    private String otherCode;
    @Size(max = 2147483647)
    @Column(name = "brand")
    private String brand;
    @Size(max = 2147483647)
    @Column(name = "supplier")
    private String supplier;
    @Size(max = 2147483647)
    @Column(name = "unit")
    private String unit;
    @Size(max = 2147483647)
    @Column(name = "price")
    private String price;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "items")
    private Collection<WarehouseInventory> warehouseInventoryCollection;
    @OneToMany(mappedBy = "itemId")
    private Collection<OrderLineItem> orderLineItemCollection;
    @JoinColumn(name = "item_category", referencedColumnName = "category_name")
    @ManyToOne
    private ItemCategory itemCategory;
    @JoinColumn(name = "project", referencedColumnName = "project_name")
    @ManyToOne
    private Project project;
    @JoinColumn(name = "added_by", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Users addedBy;

    public Items() {
    }

    public Items(String huaweiBom) {
        this.huaweiBom = huaweiBom;
    }

    public String getHuaweiBom() {
        return huaweiBom;
    }

    public void setHuaweiBom(String huaweiBom) {
        this.huaweiBom = huaweiBom;
    }

    public String getEnglishDescription() {
        return englishDescription;
    }

    public void setEnglishDescription(String englishDescription) {
        this.englishDescription = englishDescription;
    }

    public String getArabicDescription() {
        return arabicDescription;
    }

    public void setArabicDescription(String arabicDescription) {
        this.arabicDescription = arabicDescription;
    }

    public String getCustomerOracleCode() {
        return customerOracleCode;
    }

    public void setCustomerOracleCode(String customerOracleCode) {
        this.customerOracleCode = customerOracleCode;
    }

    public String getOtherCode() {
        return otherCode;
    }

    public void setOtherCode(String otherCode) {
        this.otherCode = otherCode;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getSupplier() {
        return supplier;
    }

    public void setSupplier(String supplier) {
        this.supplier = supplier;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    @XmlTransient
    public Collection<WarehouseInventory> getWarehouseInventoryCollection() {
        return warehouseInventoryCollection;
    }

    public void setWarehouseInventoryCollection(Collection<WarehouseInventory> warehouseInventoryCollection) {
        this.warehouseInventoryCollection = warehouseInventoryCollection;
    }

    @XmlTransient
    public Collection<OrderLineItem> getOrderLineItemCollection() {
        return orderLineItemCollection;
    }

    public void setOrderLineItemCollection(Collection<OrderLineItem> orderLineItemCollection) {
        this.orderLineItemCollection = orderLineItemCollection;
    }

    public ItemCategory getItemCategory() {
        return itemCategory;
    }

    public void setItemCategory(ItemCategory itemCategory) {
        this.itemCategory = itemCategory;
    }

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }

    public Users getAddedBy() {
        return addedBy;
    }

    public void setAddedBy(Users addedBy) {
        this.addedBy = addedBy;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (huaweiBom != null ? huaweiBom.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Items)) {
            return false;
        }
        Items other = (Items) object;
        if ((this.huaweiBom == null && other.huaweiBom != null) || (this.huaweiBom != null && !this.huaweiBom.equals(other.huaweiBom))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.huawei.wms.ii.entities.Items[ huaweiBom=" + huaweiBom + " ]";
    }
    
}
