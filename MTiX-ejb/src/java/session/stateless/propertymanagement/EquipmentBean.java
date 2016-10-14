/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package session.stateless.propertymanagement;

import entity.Equipment;
import entity.Property;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author catherinexiong
 */
@Stateless
public class EquipmentBean implements EquipmentBeanLocal {

    @PersistenceContext
    private EntityManager em;

    @EJB
    private SeatingPlanManagementBeanLocal spm;

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    @Override
    public List<Equipment> getEquipmentInProperty(Long propertyId) {

        Property property = spm.getPropertyById(propertyId);
        Query query = em.createQuery("SELECT eq FROM Equipment eq where eq.property=:inProperty");
        query.setParameter("inProperty", property);
        return query.getResultList();

    }

    @Override
    public List<Equipment> getNonSEquipmentInProperty(Long propertyId) {

        Property property = spm.getPropertyById(propertyId);
        Query query = em.createQuery("SELECT eq FROM Equipment eq where eq.property=:inProperty AND eq.standard=:standard");
        query.setParameter("inProperty", property);
        query.setParameter("standard", Boolean.FALSE);
        return query.getResultList();

    }

    @Override
    public Property getPropertyById(Long id) {
        Property property = em.find(Property.class, id);
        return property;

    }

    @Override
    public Equipment getEquipmentById(Long equipmentId) {
        Equipment equipment = em.find(Equipment.class, equipmentId);
        return equipment;

    }
    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")

    @Override
    public Equipment addEquipment(String name, String location, Boolean standard, Long propertyId) {
        //  System.out.println("addEquipment() called with equipment ID:" + id);
        try {
            Property property = getPropertyById(propertyId);
            // Equipment equipment = getEquipmentById(id);
            Equipment equipment = new Equipment();
            equipment.setEquipmentName(name);
            equipment.setLocation(location);
            // equipment.setPrice(price);
            equipment.setStandard(standard);
            if (standard.equals(Boolean.TRUE)) {
                equipment.setPrice(0);
            }
            equipment.setProperty(property);
            em.persist(equipment);
            em.flush();
            property.getEquipments().add(equipment);
            em.merge(property);
            return equipment;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public boolean editEquipment(Long equipmentId, String name, String location) {
        System.out.println("editEquipment() called with equipment ID:" + equipmentId);
        try {
            Equipment equipment = em.getReference(Equipment.class, equipmentId);
            if (equipment == null) {
                System.out.println("Cannot find equipment");
                return false;
            }
            
            equipment.setEquipmentName(name);
            equipment.setLocation(location);
           
            em.merge(equipment);
            System.out.println("Equipment details have been updated successfully.");
            return true;
        } catch (Exception ex) {
            System.out.println("\nServer failed to update the Equipment\n" + ex);
            return false;
        }
    }

    @Override
    public Boolean deleteEquipmentById(Long equipmentId) {

        System.out.println("deleteEquipmentById" + equipmentId);
        try {
            Equipment equipment = getEquipmentById(equipmentId);
            em.remove(equipment);
            System.out.println("The equipment" + equipmentId + "is deleted successfully.");
            return true;
        } catch (Exception ex) {
            System.out.println("\nServer failed to remove the equipment:\n" + ex);
            return false;
        }
    }

    @Override
    public List<Equipment> getAllEquipments() {

        Query q = em.createQuery("SELECT e FROM Equipment e");
        return q.getResultList();

    }

    @Override
    public Equipment setNoSPrice(Long eid, Integer price) {
        Equipment e = getEquipmentById(eid);
        System.out.println("=======Bean" + price);
        e.setPrice(price);
        em.merge(e);
        em.flush();
        return e;

    }

    @Override
    public List<Equipment> getAllNonStandardEquipments() {
        Query q = em.createQuery("SELECT * FROM Equipment e WHERE e.standard = FALSE");
        return q.getResultList();
    }
    
   /* 
    @Override
    public Equipment editEquipment(Long id,String name,String location){
        Equipment e = getEquipmentById(id);
        System.out.println("=======Bean" + location);
        e.setEquipmentName(name);
        e.setLocation(location);
        em.merge(e);
      //  em.flush();
        return e;
        
    } */
}

//    public boolean setPrice(Long id,Integer price) {
//       try{
//        Equipment equipment = getEquipmentById(id);
//       if(equipment.getStandard()){
//           equipment.setPrice(price);
//        }
//    } cat
//}
