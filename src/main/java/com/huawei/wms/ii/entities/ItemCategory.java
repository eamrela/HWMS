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
@Table(name = "item_category")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ItemCategory.findAll", query = "SELECT i FROM ItemCategory i")
    , @NamedQuery(name = "ItemCategory.findByCategoryName", query = "SELECT i FROM ItemCategory i WHERE i.categoryName = :categoryName")
    , @NamedQuery(name = "ItemCategory.findByCategoryDescription", query = "SELECT i FROM ItemCategory i WHERE i.categoryDescription = :categoryDescription")})
public class ItemCategory implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2147483647)
    @Column(name = "category_name")
    private String categoryName;
    @Size(max = 2147483647)
    @Column(name = "category_description")
    private String categoryDescription;
    @OneToMany(mappedBy = "itemCategory")
    private Collection<Items> itemsCollection;

    public ItemCategory() {
    }

    public ItemCategory(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getCategoryDescription() {
        return categoryDescription;
    }

    public void setCategoryDescription(String categoryDescription) {
        this.categoryDescription = categoryDescription;
    }

    @XmlTransient
    public Collection<Items> getItemsCollection() {
        return itemsCollection;
    }

    public void setItemsCollection(Collection<Items> itemsCollection) {
        this.itemsCollection = itemsCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (categoryName != null ? categoryName.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ItemCategory)) {
            return false;
        }
        ItemCategory other = (ItemCategory) object;
        if ((this.categoryName == null && other.categoryName != null) || (this.categoryName != null && !this.categoryName.equals(other.categoryName))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.huawei.wms.ii.entities.ItemCategory[ categoryName=" + categoryName + " ]";
    }
    
}
