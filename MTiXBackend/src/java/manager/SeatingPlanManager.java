/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package manager;

import session.stateless.propertymanagement.SeatingPlanManagementBeanLocal;
import entity.Property;
import entity.Seat;
import entity.SectionEntity;
import java.util.List;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author catherinexiong
 */
public class SeatingPlanManager {

    private final SeatingPlanManagementBeanLocal seatingPlanManagementBeanLocal;

    public SeatingPlanManager(SeatingPlanManagementBeanLocal seatingPlanManagementBeanLocal) {
        this.seatingPlanManagementBeanLocal = seatingPlanManagementBeanLocal;
    }

    public List<Property> getAllProperties() {
        return seatingPlanManagementBeanLocal.getAllProperties();
    }

    public List<SectionEntity> getAllSectionsInOneProperty(Long propertyId) {
        return seatingPlanManagementBeanLocal.getAllSectionsInOneProperty(propertyId);
    }

    public List<Seat> getAllSeatsInOneSection(Long sectionId) {
        return seatingPlanManagementBeanLocal.getAllSeatsInOneSection(sectionId);
    }

    public Boolean linkSeatsToSection() {

        return seatingPlanManagementBeanLocal.linkSeatsToSection();
    }

    public Boolean linkSectionsToProperty() {

        return seatingPlanManagementBeanLocal.linkSectionsToProperty();
    }

//    public Boolean linkSectionToCategory() {
//
//        return seatingPlanManagementBeanLocal.linkSectionsToCategory();
//    }

    public Boolean linkCategoryToProperty() {

        return seatingPlanManagementBeanLocal.linkCategoryToProperty();
    }

    public List<SectionEntity> getSectionsByPropertyId(Long propertyId) {
        return seatingPlanManagementBeanLocal.getSectionsByPropertyId(propertyId);
    }

    public List<Seat> getSeatsBySectionId(Long sectionId) {
        return seatingPlanManagementBeanLocal.getSeatsBySectionId(sectionId);
    }
    
    public Property getPropertyById(Long propertyId) {
        return seatingPlanManagementBeanLocal.getPropertyById(propertyId);
    }
    
    public Long getPropertyByName(String name){
        return seatingPlanManagementBeanLocal.getPropertyByName(name);
    }

}
