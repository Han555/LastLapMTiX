/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package manager;

import java.util.ArrayList;
import session.stateless.PaymentSessionLocal;

/**
 *
 * @author Student-ID
 */
public class PaymentManager {
    private PaymentSessionLocal paymentSessionLocal;
    
    public PaymentManager(PaymentSessionLocal paymentSessionLocal) {
        this.paymentSessionLocal = paymentSessionLocal;
    }
    
    public void createRecord(String payer, String receiver, String eventName, String ticketQuantity, String amount, String promotion) {
        paymentSessionLocal.createPayment(payer, receiver, eventName, ticketQuantity, amount, promotion);
    }
    
    public ArrayList<ArrayList<String>> getRecords(String username) {
        ArrayList<ArrayList<String>> records = paymentSessionLocal.retrievePayments(username);
        ArrayList<ArrayList<String>> arrangedRecords = new ArrayList();
        int size = records.size();
        size--;
        
        for(int i=size; i>=0; i--) {
            arrangedRecords.add(records.get(i));
        }
        
        return arrangedRecords;
    }
    
    public ArrayList<ArrayList<String>> recordPage(ArrayList<ArrayList<String>> records, int offset, int noOfRecords) {
        ArrayList<ArrayList<String>> recordPage = new ArrayList();
        int size = records.size();
        int finalRecord = offset + noOfRecords;
        if(finalRecord >= size) {
            finalRecord = size;
        }
        for(int i=offset; i<finalRecord; i++) {
            ArrayList<String> record = new ArrayList();
            record.add(records.get(i).get(0));
            record.add(records.get(i).get(1));
            record.add(records.get(i).get(2));
            record.add(records.get(i).get(3));
            record.add(records.get(i).get(4));
            recordPage.add(record);
        }
        
       
        return recordPage;
    }
    
    public String getEventName(String paymentId) {
        return paymentSessionLocal.retrieveEventName(paymentId);
    }
    
    public void addAddress(String paymentId, String address, String country, String city, String zip) {
        paymentSessionLocal.updateRecord(paymentId, address, country, city, zip);
    }
    
    public void updatePaymentStatus(String paymentId) {
        paymentSessionLocal.updateStatus(paymentId);
    }
    
    public void sendEmail(String to, String from, String message, String subject, String smtpServ) {
        paymentSessionLocal.sendMail(to, from, message, subject, smtpServ);
    }
    
    public ArrayList<String> getRecordDetails(String paymentId) {
        return paymentSessionLocal.retrieveAddress(paymentId);
    }
    
    
    public ArrayList<String> getEvents(String receiver) {
        return paymentSessionLocal.retrieveEvents(receiver);
    }
    
    
}
