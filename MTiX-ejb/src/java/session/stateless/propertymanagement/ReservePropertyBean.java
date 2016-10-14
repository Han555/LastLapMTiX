/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package session.stateless.propertymanagement;

import entity.Equipment;
import entity.Event;
import entity.MaintenanceSchedule;
import entity.Manpower;
import entity.Property;
import entity.SubEvent;
import entity.UserEntity;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import util.exception.ReservationConflictException;

/**
 *
 * @author catherinexiong
 */
@Stateless
public class ReservePropertyBean implements ReservePropertyBeanLocal {

    @PersistenceContext
    private EntityManager em;

    @EJB
    private SeatingPlanManagementBeanLocal spm;
    
    @EJB
    private EquipmentBeanLocal ebl;
    
    @EJB
    private ManpowerBeanLocal mbl;

    /**
     * ****
     *
     * private methods
     */
    private UserEntity getUserEntityById(Long userId) {
        UserEntity user = em.find(UserEntity.class, userId);
        return user;
    }
    
    

    private Boolean checkPropertyConflict(Date startDate, Date endDate, Long propertyId) {
        Query query = em.createQuery("SELECT e FROM Event e WHERE e.property = :property AND e.start <= :endDate AND e.end >= :startDate");
        query.setParameter("property", spm.getPropertyById(propertyId));
        query.setParameter("startDate", startDate);
        query.setParameter("endDate", endDate);
        List resultList = query.getResultList();
        Query query2 = em.createQuery("SELECT e FROM SubEvent e WHERE e.property = :property AND e.start <= :endDate AND e.end >= :startDate");
        query2.setParameter("property", spm.getPropertyById(propertyId));
        query2.setParameter("startDate", startDate);
        query2.setParameter("endDate", endDate);
        List resultList2 = query2.getResultList();
        if (resultList.isEmpty() && resultList2.isEmpty()) {
            return false;
        } else {
            return true;
        }
    }

    private Boolean checkMaintenanceConflict(Date startDate, Date endDate, Long propertyId) {
        List<MaintenanceSchedule> ms = spm.getPropertyById(propertyId).getMaintenanceSchedule();
        for (MaintenanceSchedule m : ms) {
            if (endDate.getTime() >= m.getStartDate().getTime()) {
                if (startDate.getTime() <= m.getEndDate().getTime()) {
                    return true;
                }
            }
        }
        return false;

    }

    @Override
    public List<Property> checkRecommendation(List<Property> properties, String visual) {
        Boolean vEvent = false;
        if (visual.equalsIgnoreCase("yes")) {
            vEvent = true;
        }
        List<Property> pRListFinal = new ArrayList();
        if (vEvent) {

            for (Property p : properties) {
                if (p.getPropertyName().equals("Merlion Star Theater")) {
                    pRListFinal.add(spm.getPropertyById(Long.valueOf(2)));

                } 

            }
        } else{
            for (Property p : properties) {
                if (p.getPropertyName().equals("Merlion Concert Hall")) {
                    pRListFinal.add(spm.getPropertyById(Long.valueOf(1)));

                } 

            
        }
        }
        return pRListFinal;

    }

    @Override
    public Event getEventById(Long id) {
        return (Event) em.find(Event.class, id);
    }
    
    @Override
    public SubEvent getSubEventById(Long id) {
        return (SubEvent) em.find(SubEvent.class, id);
    }

    @Override
    public UserEntity getUserByEmail(String email) {
        Query query = em.createQuery("SELECT u FROM UserEntity u where u.username=:inEmail");
        query.setParameter("inEmail", email);

        return (UserEntity) query.getSingleResult();

    }

    @Override
    public Boolean checkUser(String username) {
        Query q1 = em.createQuery("SELECT u FROM UserEntity u WHERE u.username = " + "'" + username + "'");

        if (q1.getResultList().isEmpty()) {
            return false;
        } else {
            return true;
        }
    }

    @Override
    public Event addNewEvent(String eventName, String eventDescription, Date startDateTime, Date endDateTime, Long propertyId, String email)  {
        if (!checkPropertyConflict(startDateTime, endDateTime, propertyId) && !checkMaintenanceConflict(startDateTime, endDateTime, propertyId)) {
            UserEntity user = getUserByEmail(email);
            Event event = new Event();
            event.setName(eventName);
            event.setDescription(eventDescription);
            event.setStart(startDateTime);
            event.setEnd(endDateTime);
            event.setHasSubEvent(false);
            event.setProperty(spm.getPropertyById(propertyId));
            event.setUser(user);
            //  event.setStatus("Pending");
            em.persist(event);
            em.flush();
            user.getEvents().add(event);
            em.merge(user);
            return event;
        } else
        return null;
    }
    @Override
    public SubEvent addNewSubEvent(String eventName,  Date start, Date end, Long propertyId, Long eId, String email){
    if (!checkPropertyConflict(start, end,propertyId) && !checkMaintenanceConflict(start, end, propertyId)) {
            UserEntity user = getUserByEmail(email);
            Event event = getEventById(eId);
            System.out.println(eId+event.getName());
            SubEvent subevent = new SubEvent();
            subevent.setName(eventName);
           // event.setDescription(eventDescription);
            subevent.setStart(start);
            subevent.setEnd(end);
            //event.setHasSubEvent(false);
            subevent.setProperty(spm.getPropertyById(propertyId));
            subevent.setUser(user);
            subevent.setEvent(event);
            //  event.setStatus("Pending");
            em.persist(subevent);
            em.flush();
            event.getSubEvents().add(subevent);
            user.getSubEvents().add(subevent);
            em.merge(event);
            em.merge(user);
            return subevent;
        } else
        return null;
}
    @Override
    public Event addNewEventWithSub(String eventName, String eventDescription, String eoEmail) {

        UserEntity user = getUserByEmail(eoEmail);
        Event event = new Event();
        event.setName(eventName);
        event.setDescription(eventDescription);
        event.setHasSubEvent(true);
       

        event.setUser(user);
        //  event.setStatus("Pending");
        em.persist(event);
        em.flush();
        user.getEvents().add(event);
        em.merge(user);
        return event;
    }

    @Override
    public List<Property> getReservationSearchResult(String visual, String eventScale, String daterange) throws ParseException {
        Boolean vEvent = false;
        if (visual.equalsIgnoreCase("yes")) {
            vEvent = true;
        }
        String[] parts = daterange.split("-");
        DateFormat df = new SimpleDateFormat("MM/dd/yyyy");
        Date startDate = df.parse(parts[0]);
        Date endDate = df.parse(parts[1]);

        List<Property> pList = spm.getAllProperties();
        List<Property> aList = new ArrayList();
        List<Property> aListFinal = new ArrayList();
        for (Property p : pList) {
            if ((!checkPropertyConflict(startDate, endDate, p.getId())) && (!checkMaintenanceConflict(startDate, endDate, p.getId()))) {
                aList.add(p);

            }
        }
        if (eventScale.equalsIgnoreCase("SS")) {
            for (Property pa : aList) {
                if (pa.getCapacity() <= 3000) {
                    aListFinal.add(pa);
                }
            }

        } else if (eventScale.equalsIgnoreCase("MS")) {
            for (Property pa : aList) {
                if (pa.getCapacity() <= 8000) {
                    aListFinal.add(pa);
                }
            }
        } else {
            for (Property pa : aList) {
                if (pa.getCapacity() > 8000) {
                    aListFinal.add(pa);
                }
            }
        }

        return aListFinal;

    }
    
    @Override
    public List<Event> getEventReservationByProperty(Long propertyId){
        Property property = spm.getPropertyById(propertyId);
        Query query = em.createQuery("SELECT e FROM Event e WHERE e.property = :property");
        query.setParameter("property", property);
        return query.getResultList();
        
        
    }
    
    @Override
    public List<SubEvent> getSubEventReservationByProperty(Long propertyId){
        Property property = spm.getPropertyById(propertyId);
        System.out.println("====getSubRES"+property.getPropertyName());
        Query query = em.createQuery("SELECT e FROM SubEvent e WHERE e.property = :property");
        query.setParameter("property", property);
        return query.getResultList();
    }
    
    @Override
    public List<Equipment> saveEquipmentSub(String[] evalues,Long pid, Long seid){
        if(evalues.length != 0){
            List <Equipment> eList = new ArrayList();
        for(int i=0;i<evalues.length;i++){
            Long eid= Long.valueOf(evalues[i]);
            Equipment eq = ebl.getEquipmentById(eid);
            SubEvent se = getSubEventById(seid);
            Property p = spm.getPropertyById(pid);
            se.getEquipments().add(eq);
            em.merge(se);
            
//            eq.getSubEvents().add(se);
//            em.merge(eq);
            eList.add(eq);
        } return eList;
        } else return null;
        
    }
    
    @Override
    public List<Equipment> saveEquipmentEvent(String[] evalues,Long pid, Long eventid){
        if(evalues.length != 0){
            List <Equipment> eList = new ArrayList();
        for(int i=0;i<evalues.length;i++){
            Long eid= Long.valueOf(evalues[i]);
            Equipment eq = ebl.getEquipmentById(eid);
            Event e = getEventById(eventid);
            Property p = spm.getPropertyById(pid);
            e.getEquipments().add(eq);
            em.merge(e);
//            eq.getEvents().add(e);
//            em.merge(eq);
            eList.add(eq);
        } return eList;
        } else return null;
        
    }
    
    @Override
    public List<Manpower> saveManpowerSub(String[] evalues,Long pid, Long seid){
        if(evalues.length != 0){
            List <Manpower> mList = new ArrayList();
        for(int i=0;i<evalues.length;i++){
            Long eid= Long.valueOf(evalues[i]);
            Manpower m = mbl.getManpowerById(eid);
            SubEvent se = getSubEventById(seid);
            Property p = spm.getPropertyById(pid);
            se.getManpower().add(m);
            em.merge(se);
//            m.getSubEvents().add(se);
//            em.merge(m);
            mList.add(m);
        } return mList;
        } else return null;
        
    }
    
    @Override
    public List<Manpower> saveManpowerEvent(String[] evalues,Long pid, Long eventid){
        if(evalues.length != 0){
            List <Manpower> mList = new ArrayList();
        for(int i=0;i<evalues.length;i++){
            Long eid= Long.valueOf(evalues[i]);
            Manpower m = mbl.getManpowerById(eid);
            Event e = getEventById(eid);
            Property p = spm.getPropertyById(pid);
            e.getManpower().add(m);
            em.merge(e);
//            m.getEvents().add(e);
//            em.merge(m);
            mList.add(m);
        } return mList;
        } else return null;
        
    }
    
    @Override
    public Boolean deleteEventReservationById(Long eId) {

        System.out.println("deleteEventRervationById" + eId);
        try {
            Event event = getEventById(eId);
            em.remove(event);
            System.out.println("The event" + eId + "is deleted successfully.");
            return true;
        } catch (Exception ex) {
            System.out.println("\nServer failed to remove the event:\n" + ex);
            return false;
        }
    }
    
    @Override
    public Boolean deleteSubEventReservationById(Long eId) {

        System.out.println("deleteSubEventRervationById" + eId);
        try {
            SubEvent subevent = getSubEventById(eId);
            em.remove(subevent);
            System.out.println("The sub event" + eId + "is deleted successfully.");
            return true;
        } catch (Exception ex) {
            System.out.println("\nServer failed to remove the sub event:\n" + ex);
            return false;
        }
    }
    
    @Override
    public Boolean editEventReservation(Long eId, String name, String des){
        System.out.println("editEvent() called with Event ID:" + eId);
        try {
            Event event = getEventById(eId);
            if (event == null) {
                System.out.println("Cannot find event");
                return false;
            }
            
            event.setName(name);
            event.setDescription(des);
           
            em.merge(event);
            System.out.println("event details have been updated successfully.");
            return true;
        } catch (Exception ex) {
            System.out.println("\nServer failed to update the event\n" + ex);
            return false;
        }
    }
    
     @Override
    public Boolean editSubEventReservation(Long eId, String name){
        System.out.println("editSubEvent() called with Sub Event ID:" + eId);
        try {
            SubEvent subevent = getSubEventById(eId);
            if (subevent == null) {
                System.out.println("Cannot find subevent");
                return false;
            }
            
            subevent.setName(name);
           // event.setDescription(des);
           
            em.merge(subevent);
            System.out.println("subevent details have been updated successfully.");
            return true;
        } catch (Exception ex) {
            System.out.println("\nServer failed to update the subevent\n" + ex);
            return false;
        }
    }
    
    
    public List<SubEvent> getListOfSubEvent(Event event){
        Query query = em.createQuery("SELECT e FROM SubEvent e WHERE e.event=:event");
        query.setParameter("event", event);
       
        List resultList = query.getResultList();
        if(resultList.isEmpty()){
            return null;
        }
        return resultList;
        
        
    }


    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
}
