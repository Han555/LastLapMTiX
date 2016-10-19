/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package session.stateless.propertymanagement;

import entity.Property;
import entity.Seat;
import entity.SectionCategoryEntity;
import entity.SectionEntity;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author catherinexiong
 */
@Local
public interface SeatingPlanManagementBeanLocal {

    /**
     *
     */
    public void CreateProperty();
    
    public List<Property> getAllProperties();
    
   // public List<SectionEntity> getAllSections(Long propertyId);

    public List<Seat> getAllSeatsInOneSection(Long sectionId);

    public Property getPropertyById(Long propertyId);

   

    public SectionEntity getSectionById(Long sectionId);

    public Boolean linkSeatsToSection();

    public List<Seat> getSeatsBySectionId(Long sectionId);

    public Boolean linkSectionsToProperty();

    public List<SectionEntity> getAllSectionsInOneProperty(Long propertyId);

    public List<SectionEntity> getSectionsByPropertyId(Long sectionId);

    public List<SectionCategoryEntity> getAllCategories();

    public SectionCategoryEntity getCategoryById(Long categoryId);

    public List<SectionEntity> getAllSectionsInOneCategory(Long categoryId);

   // public Boolean linkSectionsToCategory();

    public Boolean linkCategoryToProperty();

    public List<SectionCategoryEntity> getAllCategoryInOneProperty(Long propertyId);

    public Long getPropertyByName(String name);
}
