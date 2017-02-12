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
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Amr
 */
@Entity
@Table(name = "engineers")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Engineers.findAll", query = "SELECT e FROM Engineers e")
    , @NamedQuery(name = "Engineers.findById", query = "SELECT e FROM Engineers e WHERE e.id = :id")
    , @NamedQuery(name = "Engineers.findByFullName", query = "SELECT e FROM Engineers e WHERE e.fullName = :fullName")
    , @NamedQuery(name = "Engineers.findByCompany", query = "SELECT e FROM Engineers e WHERE e.company = :company")})
public class Engineers implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Long id;
    @Size(max = 2147483647)
    @Column(name = "full_name")
    private String fullName;
    @Size(max = 2147483647)
    @Column(name = "company")
    private String company;
    @JoinColumn(name = "project", referencedColumnName = "project_name")
    @ManyToOne
    private Project project;
    @JoinColumn(name = "region", referencedColumnName = "region_name")
    @ManyToOne
    private Region region;
    @OneToMany(mappedBy = "engineer")
    private Collection<Orders> ordersCollection;

    public Engineers() {
    }

    public Engineers(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }

    public Region getRegion() {
        return region;
    }

    public void setRegion(Region region) {
        this.region = region;
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
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Engineers)) {
            return false;
        }
        Engineers other = (Engineers) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.huawei.wms.ii.entities.Engineers[ id=" + id + " ]";
    }
    
}
