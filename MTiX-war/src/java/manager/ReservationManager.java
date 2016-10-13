/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package manager;

import PropertyManagement.ReservePropertyBeanLocal;
import PropertyManagement.SeatingPlanManagementBeanLocal;
import entity.Equipment;
import entity.Event;
import entity.Manpower;
import entity.Property;
import entity.Seat;
import entity.SectionEntity;
import entity.SubEvent;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;
import util.exception.ReservationConflictException;

/**
 *
 * @author catherinexiong
 */
public class ReservationManager {

    private final ReservePropertyBeanLocal rpb;

    public ReservationManager(ReservePropertyBeanLocal rpb) {
        this.rpb = rpb;
    }

    public Event addNewEvent(String eventName, String eventDescription, String daterange, Long propertyId, String email) throws ParseException {

        String[] parts = daterange.split(" - ");
        DateFormat df = new SimpleDateFormat("MM/dd/yyyy");
        Date startDate = df.parse(parts[0].trim());
        Date endDate = df.parse(parts[1].trim());

        return rpb.addNewEvent(eventName, eventDescription, startDate, endDate, propertyId,email);

    }
    
    public SubEvent addNewSubEvent(String name,  String daterange, Long propertyId, Long eId, String email) throws ParseException {

        String[] parts = daterange.split(" - ");
        DateFormat df = new SimpleDateFormat("MM/dd/yyyy");
        Date startDate = df.parse(parts[0].trim());
        Date endDate = df.parse(parts[1].trim());
        System.out.println("=====Manager "+eId);
        return rpb.addNewSubEvent(name,startDate, endDate, propertyId,eId,email);

    }
    
    public Event addNewEventWithSub(String name, String eventDes, String eoEmail){
        return rpb.addNewEventWithSub(name,eventDes,eoEmail);
    }
    
    public boolean checkUser(String email){
        return rpb.checkUser(email);
    }

    public List<Property> getReservationSearchResult(HttpServletRequest request) throws ParseException {
        String visual = request.getParameter("radioV");
        String eventScale = request.getParameter("eventscale");
        String daterange = request.getParameter("daterange");
        
        return rpb.getReservationSearchResult(visual,eventScale,daterange);
    }
    
    public List<Property> checkRecommendation(List<Property> properties,HttpServletRequest request){
        String visual = request.getParameter("radioV");
        return rpb.checkRecommendation(properties,visual);
    }
    
    public List<Event> getEventReservationByProperty(Long propertyId){
        return rpb.getEventReservationByProperty(propertyId);
    }
    public List<SubEvent> getSubEventReservationByProperty(Long propertyId){
        return rpb.getSubEventReservationByProperty(propertyId);
    }
    
    public List<Equipment> saveEquipmentSub(String[] evalues,String pidStr, String seidStr){
        Long pid = Long.valueOf(pidStr);
        Long seid = Long.valueOf(seidStr);
        return rpb.saveEquipmentSub(evalues,pid,seid);
    }
    
    public List<Manpower> saveManpowerSub(String[] evalues,String pidStr, String seidStr){
        Long pid = Long.valueOf(pidStr);
        Long seid = Long.valueOf(seidStr);
        return rpb.saveManpowerSub(evalues,pid,seid);
    }
    
    public List<Equipment> saveEquipmentEvent(String[] evalues,String pidStr, String eidStr){
        Long pid = Long.valueOf(pidStr);
        Long eid = Long.valueOf(eidStr);
        return rpb.saveEquipmentEvent(evalues,pid,eid);
    }
    
    public List<Manpower> saveManpowerEvent(String[] evalues,String pidStr, String eidStr){
        Long pid = Long.valueOf(pidStr);
        Long eid = Long.valueOf(eidStr);
        return rpb.saveManpowerEvent(evalues,pid,eid);
    }
    
  
    public Boolean deleteEventReservationById(Long eId) {

        return rpb.deleteEventReservationById(eId);
    }
    
    public Boolean deleteSubEventReservationById(Long eId) {

        return rpb.deleteSubEventReservationById(eId);
    }
    
    public Boolean editEventReservation(Long eId, String name, String des){
        return rpb.editEventReservation(eId,name,des);
    }
    
    public Boolean editSubEventReservation(Long eId, String name){
        return rpb.editSubEventReservation(eId,name);
    }
    
    public SubEvent getSubEventById(Long id){
        return rpb.getSubEventById(id);
    }
    
    public Event getEventById(Long id){
        return rpb.getEventById(id);
    }
    
    public List<SubEvent> getListOfSubEvent(Event event){
        return rpb.getListOfSubEvent(event);
    }

}