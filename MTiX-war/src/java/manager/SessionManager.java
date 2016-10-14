/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package manager;
import entity.SessionEntity;
import java.util.ArrayList;
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
    
    public List<SessionEntity> getSessionsByEventId(Long id) {
        return bsbl.getSessionsByEventId(id);
    }
    
    public List<SessionEntity> getSessionsBySubeventId(Long id) {
        return bsbl.getSessionsBySubeventId(id);
    }
    
}
