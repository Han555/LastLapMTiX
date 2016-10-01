/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package session.stateless;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Student-ID
 */
@Local
public interface ProductSessionLocal {
     public void createEvent(String name, String equipment, Integer manpower, Date start, Date end);

    public int createSession(String name, ArrayList<Date> start, ArrayList<Date> end, String description, String type, Long id);

    public List<ArrayList> searchEventSessions(Long id, String type);
    
    public ArrayList editSessions(Long id, String type);

    public int writeSession(ArrayList data);

    public void deleteSessions(String[] id);

    public void createSubEvent(String name, String equipment, Integer manpower, Date start, Date end);

    public ArrayList getSessionEvent(String type, Long id, int no);

    public int setPricing(Long id, ArrayList<Double> cat, int no, String apply);

    public ArrayList getPricing(Long id);

    public void generateUser();

    public boolean signIn(String name);

    public List<ArrayList> getEventList();

    public List<ArrayList> getSessionsPricing(long id, String type);

    public void setPromotion_1(String[] info, String type, String name, double discount, String requirement, String desc);

    public int getCategory(Long id, String type);
    
     public void setPromotion_3(String[] type, String[] info, String name, double discount, String requirement, String desc);

    public List<ArrayList> searchEventPromotion(Long id, String type);

    public ArrayList editPromotion(Long id, String type);

    public void writePromotion(ArrayList data);

    public void deletePromotion(String[] id);

    ArrayList<ArrayList<String>> getPromotion();

    void addTickets(String promotionId, String numOfTics);

    void deleteTickets(String promotionId, String numOfTics);

    boolean checkTicketAmount(String promotionId, String numOfTics);

    void editTicketAmount(String promotionId, String ticketAmount);

    ArrayList<ArrayList<String>> retrieveEvents();

    void addAlert(String percentage, String alertType, String username, String date, String eventId);

    void addSubEvent();

    boolean checkSubEvent(String eventId);

    void addSubEventAlert(String percentage, String alertType, String username, String date, String eventId);

    void editAlert(String percentage, String alertType, String username, String date, String eventId);

    void editSubEventAlert(String percentage, String alertType, String username, String date, String eventId);



    
}
