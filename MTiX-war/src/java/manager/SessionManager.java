/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package manager;
import entity.SectionEntity;
import entity.SessionEntity;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import session.stateless.ticketing.BookingSessionLocal;

/**
 *
 * @author catherinexiong
 */
public class SessionManager {
    private BookingSessionLocal bsbl;
    
    public SessionManager(BookingSessionLocal bsbl) {
        this.bsbl= bsbl;
    }
    
    public List<SessionEntity> getSessionsByEventIdSorted(Long id) {
        return bsbl.getSessionsByEventIdSorted(id);
    }
    
    public List<SessionEntity> getSessionsBySubeventId(Long id) {
        return bsbl.getSessionsBySubeventId(id);
    }
    public Double getPriceBySessionAndSectionId(Long sessionId, Long sectionId){
        return bsbl.getPriceBySessionAndSectionId(sessionId, sectionId);
    }
    
    public List<SectionEntity> getReservedSectionsBySessionId (Long id){
        return bsbl.getReservedSectionsBySessionId(id);
    }
    public List<SectionEntity> getClosedSectionsBySessionId (Long id) {
    return bsbl.getClosedSectionsBySessionId(id);
}
    
    public HashMap<Long, List<Double>> getSessionsPricingByEventId(Long id) {
        return bsbl.getSessionsPricingByEventId(id);
        
    }
    
    public List<Double> getSessionsPricingBySessionId(Long id,String type) {
        return bsbl.getSessionsPricingBySessionId(id,type);
        
    }
    
}
