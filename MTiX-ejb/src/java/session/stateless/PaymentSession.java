/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package session.stateless;

import entity.PaymentRecord;
import entity.UserEntity;
import java.util.ArrayList;
import java.util.Date;
import java.util.Properties;
import javax.ejb.Stateless;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author Student-ID
 */
@Stateless
public class PaymentSession implements PaymentSessionLocal {

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void createPayment(String payer, String receiver, String eventName, String ticketQuantity, String amount, String promotion) {
        PaymentRecord paymentRecord = new PaymentRecord();
        paymentRecord.createRecord(payer, receiver, eventName, ticketQuantity, amount, promotion);

        Query q = entityManager.createQuery("SELECT u FROM UserEntity u WHERE u.username = " + "'" + payer + "'");
        for (Object o : q.getResultList()) {
            UserEntity u = new UserEntity();
            u = (UserEntity) o;
            u.getPayments().add(paymentRecord);
            entityManager.merge(u);
        }
    }

    @Override
    public ArrayList<ArrayList<String>> retrievePayments(String username) {
        Query q = entityManager.createQuery("SELECT i FROM PaymentRecord i WHERE i.payer = " + "'" + username + "'");
        ArrayList<ArrayList<String>> records = new ArrayList();

        if (q.getResultList().isEmpty()) {
            return records;
        } else {
            for (Object o : q.getResultList()) {
                PaymentRecord p = (PaymentRecord) o;

                ArrayList<String> record = new ArrayList();
                record.add(Long.toString(p.getId()));
                record.add(p.getEventName());
                record.add(p.getTicketQuantity());
                record.add(p.getAmount());
                record.add(p.getPaymentStatus());
                records.add(record);
            }
            return records;
        }

    }

    @Override
    public String retrieveEventName(String paymentId) {
        Query q = entityManager.createQuery("SELECT i FROM PaymentRecord i WHERE i.id = " + paymentId);
        String name = "";
        
        for(Object o: q.getResultList()) {
            PaymentRecord record = new PaymentRecord();
            record = (PaymentRecord) o;
            name = record.getEventName();
        }
        return name;
    }

    @Override
    public void updateRecord(String paymentId, String address, String country, String city, String zip) {
        Query query = entityManager.createQuery("UPDATE PaymentRecord u SET u.address = "+"'"+address+"'" + " WHERE u.id = " + paymentId);
        query.executeUpdate();
        Query query2 = entityManager.createQuery("UPDATE PaymentRecord u SET u.country = "+"'"+country+"'" + " WHERE u.id = " + paymentId);
        query2.executeUpdate();
        Query query3 = entityManager.createQuery("UPDATE PaymentRecord u SET u.city = "+"'"+city+"'" + " WHERE u.id = " + paymentId);
        query3.executeUpdate();
        Query query4 = entityManager.createQuery("UPDATE PaymentRecord u SET u.zip = "+"'"+zip+"'" + " WHERE u.id = " + paymentId);
        query4.executeUpdate();
    }

    @Override
    public void updateStatus(String paymentId) {
        Query query = entityManager.createQuery("UPDATE PaymentRecord u SET u.paymentStatus = 'paid'" + " WHERE u.id = " + paymentId);
        query.executeUpdate();
    }

    @Override
    public int sendMail(String to, String from, String message, String subject, String smtpServ) {
        try {
            Properties props = System.getProperties();
            /*
             Properties props = new Properties();
             props.put("mail.transport.protocol", "smtp");
             props.put("mail.smtp.host", "smtp.gmail.com");
             props.put("mail.smtp.port", "587");
             props.put("mail.smtp.auth", "true");
             props.put("mail.smtp.starttls.enable", "true");
             props.put("mail.smtp.debug", "true");           
             javax.mail.Authenticator auth = new SMTPAuthenticator();
             Session session = Session.getInstance(props, auth);
             session.setDebug(true);           
             Message msg = new MimeMessage(session);
             msg.setFrom(InternetAddress.parse("xxx <xxx@gmail.com>", false)[0]);*/
            // -- Attaching to default Session, or we could start a new one --
            props.put("mail.transport.protocol", "smtp");
            props.put("mail.smtp.starttls.enable", "true");
            props.put("mail.smtp.host", smtpServ);
            props.put("mail.smtp.auth", "true");
            props.put("mail.smtp.port", "587");
            //new
            props.put("mail.smtp.debug", "true");
            Authenticator auth = new PaymentSession.SMTPAuthenticator();
            Session session = Session.getInstance(props, auth);
            //new
            session.setDebug(true);
            // -- Create a new message --
            Message msg = new MimeMessage(session);
            // -- Set the FROM and TO fields --
            msg.setFrom(new InternetAddress(from));
            msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to, false));
            msg.setSubject(subject);
            msg.setText(message);
            // -- Set some other header information --
            msg.setHeader("MyMail", "Mr. XYZ");
            msg.setSentDate(new Date());
            // -- Send the message --
            Transport.send(msg);
            System.out.println("Message sent to" + to + " OK.");
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            System.out.println("Exception " + ex);
            return -1;
        }
    }
    
     private class SMTPAuthenticator extends javax.mail.Authenticator {

        @Override
        public PasswordAuthentication getPasswordAuthentication() {
            String username = "is3102mtix@gmail.com";           // specify your email id here (sender's email id)
            String password = "integrated555";                                      // specify your password here
            return new PasswordAuthentication(username, password);
        }
    }

    @Override
    public ArrayList<String> retrieveAddress(String paymentId) {
        ArrayList<String> address = new ArrayList();
        Query q = entityManager.createQuery("SELECT i FROM PaymentRecord i WHERE i.id = " + paymentId);
        
        for(Object o: q.getResultList()) {
            PaymentRecord p = new PaymentRecord();
            p = (PaymentRecord) o;
            address.add(p.getAddress());
            address.add(p.getCountry());
            address.add(p.getCity());
            address.add(p.getZip());
            address.add(p.getEventName());
            address.add(p.getTicketQuantity());
            address.add(p.getAmount());
        }
        return address;
    }

    @Override
    public ArrayList<String> retrieveEvents(String receiver) {
        Query q = entityManager.createQuery("SELECT i FROM PaymentRecord i WHERE i.receiver = " + "'"+receiver+"'");
        ArrayList<String> events = new ArrayList();
        System.out.println("receiver: "+receiver);
        for(Object o: q.getResultList()) {
            PaymentRecord p = new PaymentRecord();
            p = (PaymentRecord) o;
            System.out.println("event name: "+p.getEventName());
            events.add(p.getEventName());
        }
        
        for(int i=0; i<events.size(); i++) {
            for(int u = i +1; u<events.size(); u++) {
                if(events.get(i).equals(events.get(u))) {
                    events.remove(u);
                }
            }
        }
        return events;
    }

    @Override
    public ArrayList<ArrayList<String>> retrieveRecords(String event, String receiver) {
        Query q = entityManager.createQuery("SELECT i FROM PaymentRecord i WHERE i.receiver = " + "'"+receiver+"'"+ " AND i.eventName = "+"'"+event+"'");
        ArrayList<ArrayList<String>> records = new ArrayList();
        
        for(Object o: q.getResultList()) {
            PaymentRecord p = new PaymentRecord();
            p = (PaymentRecord) o;
            ArrayList<String> record = new ArrayList();
            record.add(p.getPayer());
            record.add(p.getTicketQuantity());
            record.add(p.getAmount());
            record.add(p.getPaymentStatus());
            records.add(record);
        }
        
        return records;
    }

    
    
}
