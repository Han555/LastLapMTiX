/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PropertyManagement;

import entity.FoodOutlet;
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
public class FoodOutletBean implements FoodOutletBeanLocal {

    @PersistenceContext
    private EntityManager em;

    @EJB
    SeatingPlanManagementBeanLocal spm;

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    @Override
    public List<FoodOutlet> getFoodOutletInProperty(Long propertyId) {

        Property property = spm.getPropertyById(propertyId);
        Query query = em.createQuery("SELECT fo FROM FoodOutlet fo where fo.property=:inProperty");
        query.setParameter("inProperty", property);
        return query.getResultList();

    }

    @Override
    public Property getPropertyById(Long id) {
        Property property = em.find(Property.class, id);
        return property;

    }

    @Override
    public FoodOutlet getFoodOutletById(Long id) {
        FoodOutlet foodOutlet = em.find(FoodOutlet.class, id);
        return foodOutlet;

    }


    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")

    @Override
    public FoodOutlet addFoodOutlet(String name, String type, String description, Long propertyId) {
        //  System.out.println("addEquipment() called with equipment ID:" + id);
        try {
            Property property = getPropertyById(propertyId);
            // Equipment equipment = getEquipmentById(id);
            FoodOutlet foodOutlet = new FoodOutlet();
            foodOutlet.setOutletName(name);
            foodOutlet.setOutletType(type);
            // equipment.setPrice(price);
            foodOutlet.setOutletDescription(description);
            foodOutlet.setProperty(property);
            em.persist(foodOutlet);
            em.flush();
//            foodOutlet.getOutlet().add(foodOutlet);
            em.merge(property);
            return foodOutlet;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public boolean editFoodOutlet(String name, String type, String description, Long outletId) {
        //System.out.println("editEquipment() called with equipment ID:" + fId);
        try {
            FoodOutlet foodoutlet = em.getReference(FoodOutlet.class, outletId);
            if (foodoutlet == null) {
                System.out.println("Cannot find equipment");
                return false;
            }

            foodoutlet.setOutletName(name);
            foodoutlet.setOutletType(type);
            foodoutlet.setOutletDescription(description);

            em.merge(foodoutlet);
            System.out.println("Equipment details have been updated successfully.");
            return true;
        } catch (Exception ex) {
            System.out.println("\nServer failed to update the Equipment\n" + ex);
            return false;
        }
    }

    @Override
    public Boolean deleteFoodOutletById(Long outletId) {

        System.out.println("deleteEquipmentById" + outletId);
        try {
            FoodOutlet foodoutlet = getFoodOutletById(outletId);
            em.remove(foodoutlet);
            System.out.println("The equipment" + outletId + "is deleted successfully.");
            return true;
        } catch (Exception ex) {
            System.out.println("\nServer failed to remove the equipment:\n" + ex);
            return false;
        }
    }

    @Override
    public List<FoodOutlet> getAllFoodOutlet() {

        Query q = em.createQuery("SELECT fo FROM FoodOutlet fo");
        return q.getResultList();

    }
}


