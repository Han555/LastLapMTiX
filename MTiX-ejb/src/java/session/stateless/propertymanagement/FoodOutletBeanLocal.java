/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package session.stateless.propertymanagement;

import entity.FoodOutlet;
import entity.Manpower;
import entity.Property;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author catherinexiong
 */
@Local
public interface FoodOutletBeanLocal {

    public List<FoodOutlet> getFoodOutletInProperty(Long propertyId);

    public FoodOutlet getFoodOutletById(Long id);

    public Property getPropertyById(Long id);

    public FoodOutlet addFoodOutlet(String name, String type, String description, Long propertyId);

    public boolean editFoodOutlet(String name, String type, String description, Long outletId);

    public Boolean deleteFoodOutletById(Long outletId);

    public List<FoodOutlet> getAllFoodOutlet();

}
