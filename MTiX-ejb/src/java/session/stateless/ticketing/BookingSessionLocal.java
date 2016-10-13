/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package session.stateless.ticketing;

import entity.SessionEntity;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author catherinexiong
 */
@Local
public interface BookingSessionLocal {
    
    public List<SessionEntity> getSessionsByEventId(Long id);
    public List<SessionEntity> getSessionsBySubeventId(Long id);
}
