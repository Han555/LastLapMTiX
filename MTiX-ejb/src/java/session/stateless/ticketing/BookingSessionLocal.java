/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package session.stateless.ticketing;

import entity.SectionEntity;
import entity.SessionEntity;
import java.util.HashMap;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author catherinexiong
 */
@Local
public interface BookingSessionLocal {
    
  
    public List<SessionEntity> getSessionsBySubeventId(Long id);

    public Double getPriceBySessionAndSectionId(Long sessionId, Long sectionId);

    public List<SessionEntity> getSessionsByEventIdSorted(Long id);

    public List<SectionEntity> getReservedSectionsBySessionId(Long id);

    public List<SectionEntity> getClosedSectionsBySessionId(Long id);
    
    HashMap<Long, List<Double>> getSessionsPricingByEventId(Long id);

  

    public List<Double> getSessionsPricingBySessionId(Long id, String type);

   
}
