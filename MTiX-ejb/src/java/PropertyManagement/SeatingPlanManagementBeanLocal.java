/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PropertyManagement;

import entity.Property;
import entity.Seat;
import entity.SectionCategory;
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

    public List<SectionCategory> getAllCategories();

    public SectionCategory getCategoryById(Long categoryId);

    public List<SectionEntity> getAllSectionsInOneCategory(Long categoryId);

   // public Boolean linkSectionsToCategory();

    public Boolean linkCategoryToProperty();

    public List<SectionCategory> getAllCategoryInOneProperty(Long propertyId);
}
