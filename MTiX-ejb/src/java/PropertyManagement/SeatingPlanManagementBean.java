package PropertyManagement;

import entity.Property;
import entity.Seat;
import entity.SectionCategory;
import entity.SectionEntity;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author catherinexiong
 */
@Stateless
public class SeatingPlanManagementBean implements SeatingPlanManagementBeanLocal {

    @PersistenceContext
    private EntityManager em;

    public SeatingPlanManagementBean() {

    }

    @Override
    public void CreateProperty() {
        String name;
        Integer num;
        Integer capacity;
        try {
            Property property = new Property();
            property.createProperty("Merlion Concert Hall", 1, 2000);
            em.persist(property);
            em.flush();
            name = property.getPropertyName();
            capacity = property.getCapacity();
            Long propertyID = property.getId();
            System.out.println("Property Name \"" + name + "\" registered successfully as id:" + propertyID + "\" with capcity of:" + capacity);

            property.createProperty("Merlion Theater", 2, 2000);
            em.persist(property);
            em.flush();
            name = property.getPropertyName();
            capacity = property.getCapacity();
            propertyID = property.getId();
            System.out.println("Property Name \"" + name + "\" registered successfully as id:" + propertyID + "\" with capcity of:" + capacity);

            property.createProperty("Merlion Outdoor Stadium", 3, 80000);
            em.persist(property);
            em.flush();
            name = property.getPropertyName();
            capacity = property.getCapacity();
            propertyID = property.getId();
            System.out.println("Property Name \"" + name + "\" registered successfully as id:" + propertyID + "\" with capcity of:" + capacity);

            em.detach(property);

        } catch (Exception ex) {
            System.out.println("\nServer failed to add new property:\n" + ex);

        }

    }

    /**
     * *************************************************************
     * Get Property, Section, Seat By Id
     * *************************************************************
     */
    @Override
    public Property getPropertyById(Long propertyId) {
        Property property = em.find(Property.class, propertyId);
        return property;
    }

    @Override
    public SectionEntity getSectionById(Long sectionId) {
        SectionEntity section = em.find(SectionEntity.class, sectionId);
        return section;
    }
    
    @Override
    public SectionCategory getCategoryById(Long categoryId) {
        SectionCategory category = em.find(SectionCategory.class, categoryId);
        return category;
    }

     /*************************************************************
     Get All Property, Section, Seat (List)
      *************************************************************/
    
    @Override
    public List<Property> getAllProperties() {

        Query query = em.createQuery("SELECT p FROM Property p");
        return query.getResultList();
    }
    
    @Override
    public List<SectionCategory> getAllCategories() {

        Query query = em.createQuery("SELECT sc FROM SectionCategory sc");
        return query.getResultList();
    }
    /*
     @Override
     public List<SectionEntity> getAllSections(Long propertyId) {
     Property property = getPropertyById(propertyId);
     Query query = em.createQuery("SELECT se FROM SectionEntity se WHERE se.property = :inProperty");
     query.setParameter("inProperty", property);
     return query.getResultList();
     }
     */

    @Override
    public List<Seat> getAllSeatsInOneSection(Long sectionId) {
        SectionEntity section = getSectionById(sectionId);
        Query query = em.createQuery("SELECT s FROM Seat s WHERE s.section = :inSection");
        query.setParameter("inSection", section);
        return query.getResultList();
    }

    @Override
    public List<SectionEntity> getAllSectionsInOneProperty(Long propertyId) {
        Property property = getPropertyById(propertyId);
        Query query = em.createQuery("SELECT se FROM SectionEntity se WHERE se.property = :inProperty");
        query.setParameter("inProperty", property);
        return query.getResultList();
    }
    
    @Override
    public List<SectionCategory> getAllCategoryInOneProperty(Long propertyId) {
        Property property = getPropertyById(propertyId);
        Query query = em.createQuery("SELECT sc FROM SectionCategory sc WHERE sc.property = :inProperty");
        query.setParameter("inProperty", property);
        return query.getResultList();
    }
    
    @Override
    public List<SectionEntity> getAllSectionsInOneCategory(Long categoryId) {
        SectionCategory category = getCategoryById(categoryId);
        Query query = em.createQuery("SELECT se FROM SectionEntity se WHERE se.category = :inCategory");
        query.setParameter("inCategory", category);
        return query.getResultList();
    }

    /**
     * *************************************************************
     * Link Property, Section, Seat
     * *************************************************************
     * @return 
     */
    
    @Override
    public Boolean linkSeatsToSection() {
        try {
            for (int i = 0; i <= 26; i++) {
                List<Seat> seats = getAllSeatsInOneSection(Long.valueOf(i + 1));

                SectionEntity section = getSectionById(Long.valueOf(i + 1));
                section.setSeats(seats);
                em.merge(section);

            }

            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public List<Seat> getSeatsBySectionId(Long sectionId) {
        SectionEntity section = getSectionById(sectionId);
        return section.getSeats();

    }

    @Override
    public Boolean linkSectionsToProperty() {
        try {

            List<SectionEntity> sections = getAllSectionsInOneProperty(Long.valueOf(1));

            Property property = getPropertyById(Long.valueOf(1));
            property.setSections(sections);
            em.merge(property);

            return true;
        } catch (Exception e) {
            return false;
        }
       
    }
    
    @Override
    public Boolean linkCategoryToProperty() {
        try {

            List<SectionCategory> category = getAllCategoryInOneProperty(Long.valueOf(1));

            Property property = getPropertyById(Long.valueOf(1));
            property.setCategory(category);
            em.merge(property);

            return true;
        } catch (Exception e) {
            return false;
        }
       
    }
    
//    @Override
//    public Boolean linkSectionsToCategory() {
//        try {
//            List<SectionCategory> category = new ArrayList();
//            category = getAllCategories();
//            
//            for(SectionCategory sc:category){
//                List<SectionEntity> sections = getAllSectionsInOneCategory(sc.getId());
//                sc.setSection(sections);
//                em.merge(sc);
//                
//            }
//
//            return true;
//        } catch (Exception e) {
//            return false;
//        }
//       
//    }
//    
    @Override
    public List<SectionEntity> getSectionsByPropertyId(Long propertyId) {
        Property property = getPropertyById(propertyId);
        return property.getSections();

    }
    
    @Override
    public Long getPropertyByName(String name){
        Query query = em.createQuery("SELECT p FROM Property p WHERE p.propertyName = :inName");
        query.setParameter("inName", name);
        if(query.getSingleResult() != null){
        Property property = (Property) query.getSingleResult();
        return property.getId();
        } else return null;
    }

    /**
     * *************************************************************
     *
     * *************************************************************
     */
}
