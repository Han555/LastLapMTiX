/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PropertyManagement;

import entity.Manpower;
import entity.Property;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author hyc528
 */
@Stateless
public class ManpowerBean implements ManpowerBeanLocal {

    @PersistenceContext(unitName = "MTiX-ejbPU")
    private EntityManager em;

    @EJB
    SeatingPlanManagementBeanLocal spm;

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    @Override
    public List<Manpower> getManpowerInProperty(Long propertyId) {

        Property property = spm.getPropertyById(propertyId);
        Query query = em.createQuery("SELECT m FROM Manpower m where m.property=:inProperty");
        query.setParameter("inProperty", property);
        return query.getResultList();

    }

    @Override
    public List<Manpower> getNonSManpowerInProperty(Long propertyId) {

        Property property = spm.getPropertyById(propertyId);
        Query query = em.createQuery("SELECT m FROM Manpower m where m.property=:inProperty");
        query.setParameter("inProperty", property);
        return query.getResultList();

    }

    public Property getPropertyById(Long id) {
        Property property = em.find(Property.class, id);
        return property;
    }

    @Override
    public Manpower getManpowerById(Long manpowerId) {
        Manpower manpower = em.find(Manpower.class, manpowerId);
        return manpower;

    }

    public Manpower addManpower(String role, Integer number, Boolean standard, Long propertyId) {

        //System.out.println("addManpower() called with manpower ID:" + manpowerId);
        try {
            Property property = getPropertyById(propertyId);
            //Manpower manpower = getManpowerById(manpowerId);
            Manpower manpower = new Manpower();
            manpower.setStaffRole(role);
            manpower.setNumber(number);
            manpower.setStandard(standard);

            if (standard.equals(Boolean.TRUE)) {
                manpower.setPrice(0);
            }
            manpower.setProperty(property);
            em.persist(manpower);
            em.flush();
            property.getManpower().add(manpower);
            em.merge(property);
            return manpower;
        } catch (Exception e) {
            return null;
        }
    }

    public Boolean editManpower(Long manpowerId, String role, Integer number) {
        System.out.println("editManpower() called with manpower ID:" + manpowerId);
        try {
//            Property property = getPropertyById(id);
            Manpower manpower = em.getReference(Manpower.class, manpowerId);
            if (manpower == null) {
                System.out.println("The staff" + manpowerId + "is not found");
                return false;
            }
            manpower.setStaffRole(role);
            manpower.setNumber(number);
//            manpower.setStandard(standard);
//            manpower.setProperty(property);
            em.merge(manpower);
//            property.getManpower().add(manpower);
            System.out.println("Manpower details have been updated successfully.");
            return true;
        } catch (Exception ex) {
            System.out.println("\nServer failed to update the Manpower\n" + ex);
            return false;
        }
    }
    
    

    public Boolean deleteManpowerById(Long manpowerId) {

        System.out.println("deleteManpowerById" + manpowerId);
        try {
//            Manpower manpower = em.find(Manpower.class, manpowerId);
            Manpower manpower = getManpowerById(manpowerId);
            em.remove(manpower);
            System.out.println("The Staff" + manpowerId + "is deleted successfully.");
            return true;
        } catch (Exception ex) {
            System.out.println("\nServer failed to remove the staff:\n" + ex);
            return false;
        }
    }

    public List<Manpower> getAllManpower() {
        Query q = em.createQuery("SELECT m FROM Manpower m");
        return q.getResultList();
    }

    @Override
    public Manpower mSetNoSPrice(Long mid, Integer price) {
        Manpower m = getManpowerById(mid);
        System.out.println("=======Bean" + price);
        m.setPrice(price);
        em.merge(m);
        em.flush();
        return m;
    }

    @Override
    public List<Manpower> getAllNonStandardManpowers() {
        Query q = em.createQuery("SELECT * FROM Manpower m WHERE m.standard = FALSE");
        return q.getResultList();
    }
}
