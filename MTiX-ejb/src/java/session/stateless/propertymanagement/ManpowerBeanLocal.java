/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package session.stateless.propertymanagement;

import entity.Manpower;
import entity.Property;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author hyc528
 */
@Local
public interface ManpowerBeanLocal {

    public Manpower getManpowerById(Long manpowerId);

    public Property getPropertyById(Long id);

    public Boolean deleteManpowerById(Long manpowerId);

    public List<Manpower> getAllManpower();

    public List<Manpower> getAllNonStandardManpowers();

    public List<Manpower> getManpowerInProperty(Long propertyId);

    public List<Manpower> getNonSManpowerInProperty(Long propertyId);

    public Manpower addManpower(String role, Integer number, Boolean standard, Long propertyId);

    public Boolean editManpower(Long manpowerId, String role, Integer number);

    public Manpower mSetNoSPrice(Long mid, Integer price);

}
