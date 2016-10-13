/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package session.stateless.propertymanagement;

import entity.Equipment;
import entity.Property;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author catherinexiong
 */
@Local
public interface EquipmentBeanLocal {

    public List<Equipment> getEquipmentInProperty(Long propertyId);

    public List<Equipment> getNonSEquipmentInProperty(Long propertyId);

    public Property getPropertyById(Long id);

    public Equipment getEquipmentById(Long equipmentId);

   

    public Boolean deleteEquipmentById(Long equipmentId);

    public List<Equipment> getAllEquipments();

 //   public Boolean addEquipment(String name, String location, Integer price, Boolean standard, Long propertyId);

    public boolean editEquipment(Long equipmentId, String name, String location);

    public Equipment addEquipment(String name, String location, Boolean standard, Long propertyId);

    public Equipment setNoSPrice(Long eid, Integer price);

    public List<Equipment> getAllNonStandardEquipments();

  //  public Equipment editEquipment(Long id, String name, String location);
    
}
