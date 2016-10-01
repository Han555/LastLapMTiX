/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PropertyManagement;

import entity.Equipment;
import entity.Event;
import entity.Manpower;
import entity.Property;
import entity.SubEvent;
import entity.UserEntity;
import java.text.ParseException;
import java.util.Date;
import java.util.List;

import javax.ejb.Local;
import util.exception.ReservationConflictException;

/**
 *
 * @author catherinexiong
 */
@Local
public interface ReservePropertyBeanLocal {

   

    

  
    public List<Property> getReservationSearchResult(String visual, String eventScale, String daterange) throws ParseException;

    public List<Property> checkRecommendation(List<Property> properties, String visual);

    public UserEntity getUserByEmail(String email);

    public Event addNewEventWithSub(String eventName, String eventDescription, String eoEmail);

    public Boolean checkUser(String username);

    public List<Event> getEventReservationByProperty(Long propertyId);

    public List<SubEvent> getSubEventReservationByProperty(Long propertyId);

    public Event addNewEvent(String eventName, String eventDescription, Date startDateTime, Date endDateTime, Long propertyId, String email);

    public SubEvent addNewSubEvent(String eventName, Date start, Date end, Long propertyId, Long eId, String email);

    public Event getEventById(Long id);

    public List<Equipment> saveEquipmentSub(String[] evalues, Long pid, Long seid);

    public List<Equipment> saveEquipmentEvent(String[] evalues, Long pid, Long seid);

    public List<Manpower> saveManpowerSub(String[] evalues, Long pid, Long seid);

    public List<Manpower> saveManpowerEvent(String[] evalues, Long pid, Long seid);

    public Boolean deleteEventReservationById(Long eId);

    public Boolean deleteSubEventReservationById(Long eId);

    public Boolean editEventReservation(Long eId, String name, String des);

    public Boolean editSubEventReservation(Long eId, String name);

    public SubEvent getSubEventById(Long id);


    
}
