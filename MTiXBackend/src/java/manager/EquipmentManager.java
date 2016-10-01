/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package manager;

import PropertyManagement.EquipmentBeanLocal;
import entity.Equipment;
import java.util.List;

/**
 *
 * @author hyc528
 */
public class EquipmentManager {

    private final EquipmentBeanLocal equipmentBeanLocal;

    public EquipmentManager(EquipmentBeanLocal equipmentManagementBeanLocal) {
        this.equipmentBeanLocal = equipmentManagementBeanLocal;
    }

    public List<Equipment> getAllEquipments() {
        return equipmentBeanLocal.getAllEquipments();
    }

    public Equipment createNewEquipment(String ename, String location, Boolean standard, Long propertyId) {
        //Integer eprice = Integer.getInteger("price");
        return equipmentBeanLocal.addEquipment(ename, location, standard, propertyId);
    }
    
    public boolean editEquipment(Long id, String ename, String location) {
        //Integer eprice = Integer.getInteger("price");
        return equipmentBeanLocal.editEquipment(id, ename,location);
    }

    public Equipment setNoSPrice(Long eId, String price) {
        System.out.println("=======BeforeManager" + price);
        Integer eprice = Integer.parseInt(price);
        System.out.println("=======Manager" + price);
        return equipmentBeanLocal.setNoSPrice(eId, eprice);
    }

    public List<Equipment> getNonSEquipmentInProperty(Long propertyId) {
        return equipmentBeanLocal.getNonSEquipmentInProperty(propertyId);
    }
    
    public Boolean deleteEquipmentById(Long id){
        return equipmentBeanLocal.deleteEquipmentById(id);
    }

}
