/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

/**
 *
 * @author catherinexiong
 */
@Entity
public class FoodOutlet implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String outletId;
    private String outletName;
    private String outletType;
    private String outletDescription;
    @ManyToOne
    private Property property;
    
    public void createFoodOutlets(String outletId, String outletName, String outletType,String outletDescription){
        this.setOutletId(outletId);
        this.setOutletName(outletName);
        this.setOutletType(outletType);
        this.setOutletDescription(outletDescription);
    }
    
    public void createOutlet(String name, String type, String description) {
        this.outletName = name;
        this.outletType = type;
        this.outletDescription = description;      
    }
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
        if (!(object instanceof FoodOutlet)) {
            return false;
        }
        FoodOutlet other = (FoodOutlet) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.FoodOutlet[ id=" + id + " ]";
    }

    /**
     * @return the outletName
     */
    public String getOutletName() {
        return outletName;
    }

    /**
     * @param outletName the outletName to set
     */
    public void setOutletName(String outletName) {
        this.outletName = outletName;
    }

    /**
     * @return the outletType
     */
    public String getOutletType() {
        return outletType;
    }

    /**
     * @param outletType the outletType to set
     */
    public void setOutletType(String outletType) {
        this.outletType = outletType;
    }

    /**
     * @return the outletDescription
     */
    public String getOutletDescription() {
        return outletDescription;
    }

    /**
     * @param outletDescription the outletDescription to set
     */
    public void setOutletDescription(String outletDescription) {
        this.outletDescription = outletDescription;
    }

    /**
     * @return the property
     */
    public Property getProperty() {
        return property;
    }

    /**
     * @param property the property to set
     */
    public void setProperty(Property property) {
        this.property = property;
    }

    /**
     * @return the outletId
     */
    public String getOutletId() {
        return outletId;
    }

    /**
     * @param outletId the outletId to set
     */
    public void setOutletId(String outletId) {
        this.outletId = outletId;
    }
    
}
