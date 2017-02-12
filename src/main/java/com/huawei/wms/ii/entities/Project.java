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
@Table(name = "project")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Project.findAll", query = "SELECT p FROM Project p")
    , @NamedQuery(name = "Project.findByProjectName", query = "SELECT p FROM Project p WHERE p.projectName = :projectName")
    , @NamedQuery(name = "Project.findByProjectDescription", query = "SELECT p FROM Project p WHERE p.projectDescription = :projectDescription")})
public class Project implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2147483647)
    @Column(name = "project_name")
    private String projectName;
    @Size(max = 2147483647)
    @Column(name = "project_description")
    private String projectDescription;
    @OneToMany(mappedBy = "project")
    private Collection<Engineers> engineersCollection;
    @OneToMany(mappedBy = "warehouseProject")
    private Collection<Warehouse> warehouseCollection;
    @OneToMany(mappedBy = "project")
    private Collection<Users> usersCollection;
    @OneToMany(mappedBy = "project")
    private Collection<Orders> ordersCollection;
    @OneToMany(mappedBy = "project")
    private Collection<Items> itemsCollection;

    public Project() {
    }

    public Project(String projectName) {
        this.projectName = projectName;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getProjectDescription() {
        return projectDescription;
    }

    public void setProjectDescription(String projectDescription) {
        this.projectDescription = projectDescription;
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
        hash += (projectName != null ? projectName.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Project)) {
            return false;
        }
        Project other = (Project) object;
        if ((this.projectName == null && other.projectName != null) || (this.projectName != null && !this.projectName.equals(other.projectName))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.huawei.wms.ii.entities.Project[ projectName=" + projectName + " ]";
    }
    
}
