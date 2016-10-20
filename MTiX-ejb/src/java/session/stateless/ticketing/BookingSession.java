/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package session.stateless.ticketing;

import session.stateless.propertymanagement.ReservePropertyBeanLocal;
import entity.Event;
import entity.SectionCategoryEntity;
import entity.SectionEntity;
import entity.SessionEntity;
import entity.SessionSeatsInventory;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import session.stateless.propertymanagement.SeatingPlanManagementBeanLocal;

/**
 *
 * @author catherinexiong
 */
@Stateless
public class BookingSession implements BookingSessionLocal {

    @PersistenceContext
    private EntityManager em;

    @EJB
    private ReservePropertyBeanLocal rpm;
    @EJB
    private SeatingPlanManagementBeanLocal spm;

    @Override
    public List<SessionEntity> getSessionsByEventIdSorted(Long id) {
        Event e = rpm.getEventById(id);

        Query q = em.createQuery("SELECT s FROM SessionEntity s WHERE s.event=:event ORDER BY s.timeStart");
        q.setParameter("event", e);
        return q.getResultList();
    }

    @Override
    public List<SessionEntity> getSessionsBySubeventId(Long id) {
        Event e = rpm.getEventById(id);
        Query q = em.createQuery("SELECT s FROM SessionEntity s WHERE s.subEvent=:subevent");
        q.setParameter("subevent", e);
        return q.getResultList();
    }

    @Override
    public Double getPriceBySessionAndSectionId(Long sessionId, Long sectionId) {
        SectionEntity s = spm.getSectionById(sectionId);
        SessionEntity session = em.find(SessionEntity.class, sessionId);
        SectionCategoryEntity sc = s.getCategory();
        Query q = em.createQuery("SELECT scp.price FROM SessionCategoryPrice scp WHERE scp.category=:category AND scp.session=:session");
        q.setParameter("category", sc);
        q.setParameter("session", session);
        return (Double) q.getSingleResult();
    }

    @Override
    public List<SectionEntity> getReservedSectionsBySessionId(Long id) {
        SessionEntity sessionE = em.find(SessionEntity.class, id);
        Date currentD = new Date();

        Query q = em.createQuery("SELECT s FROM SessionSeatsInventory s WHERE s.session=:session AND s.reserveTickets=TRUE");
        q.setParameter("session", sessionE);
        long Hour = 3600 * 1000;
        List<SectionEntity> result = new ArrayList<SectionEntity>();
        for (SessionSeatsInventory s : (List<SessionSeatsInventory>) q.getResultList()) {
            if (s.getReservationEndDate().after(new Date(currentD.getTime() - 24 * Hour))) {
                result.add(s.getSectionEntity());
            }
        }
        return result;
    }

    @Override
    public List<SectionEntity> getClosedSectionsBySessionId(Long id) {
        SessionEntity sessionE = em.find(SessionEntity.class, id);

        Query q = em.createQuery("SELECT s.sectionEntity FROM SessionSeatsInventory s WHERE s.session=:session AND s.stopTicketsSales=TRUE");
        q.setParameter("session", sessionE);
        return q.getResultList();
    }

    public HashMap<Long, List<Double>> getSessionsPricingByEventId(Long id) {
        Event event = em.find(Event.class, id);
        HashMap<Long, List<Double>> map = new HashMap<Long, List<Double>>();
        List<SessionEntity> sessions;
        sessions = getSessionsByEventIdSorted(id);
        for (SessionEntity s : sessions) {
            Long sid = s.getId();
            List<SectionEntity> sections = spm.getAllSectionsInOneProperty(event.getProperty().getId());
            List<Double> prices = new ArrayList<Double>();
            for (SectionEntity sec : sections) {
                prices.add(getPriceBySessionAndSectionId(sid, sec.getId()));
            }
            map.put(sid, prices);
        }
        return map;
    }

    public List<Double> getSessionsPricingBySessionId(Long id, String type) {
        SessionEntity s = em.find(SessionEntity.class, id);
        List<Double> prices = new ArrayList<Double>();
        if (type.equals("event")) {
            List<SectionEntity> sections = spm.getAllSectionsInOneProperty(s.getEvent().getProperty().getId());
           
            for (SectionEntity sec : sections) {
                prices.add(getPriceBySessionAndSectionId(id, sec.getId()));
            }
        } else {
             List<SectionEntity> sections = spm.getAllSectionsInOneProperty(s.getSubEvent().getProperty().getId());
            
            for (SectionEntity sec : sections) {
                prices.add(getPriceBySessionAndSectionId(id, sec.getId()));
            }
        }
        return prices;
    }

}
