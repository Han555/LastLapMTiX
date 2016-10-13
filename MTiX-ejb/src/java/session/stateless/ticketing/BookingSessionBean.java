/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package session.stateless.ticketing;

import PropertyManagement.ReservePropertyBeanLocal;
import entity.Event;
import entity.SessionEntity;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author catherinexiong
 */
@Stateless
public class BookingSessionBean implements BookingSessionBeanLocal {

    @PersistenceContext
    private EntityManager em;
    
    @EJB
    private ReservePropertyBeanLocal rpm;
    
    public List<SessionEntity> getSessionsByEventId(Long id) {
        Event e = rpm.getEventById(id);
        Query q = em.createQuery("SELECT s FROM SessionEntity s WHERE s.event=:event");
        q.setParameter("event", e);
        return q.getResultList();
    }
    
    public List<SessionEntity> getSessionsBySubeventId(Long id) {
        Event e = rpm.getEventById(id);
        Query q = em.createQuery("SELECT s FROM SessionEntity s WHERE s.subEvent=:subevent");
        q.setParameter("subevent", e);
        return q.getResultList();
    }
    
}
