/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

/**
 *
 * @author Student-ID
 */
@Entity
public class UserEntity implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;
    private String username;
    private String password;
    private String salt;
    private String mobileNumber;
    private boolean firstLogin;
    private boolean resetPassword;
    private ArrayList<String> roles;
    
    @OneToMany(mappedBy="user")
    private Collection<SubEvent> subEvents = new ArrayList<SubEvent>();
    
    @OneToMany(mappedBy="user")
    private Collection<Event> events = new ArrayList<Event>();
    
    @OneToMany
    private Collection<MessageEntity> messages = new ArrayList<MessageEntity> ();
    
    @ManyToMany
    private Collection<BulletinEntity> bulletins = new ArrayList<BulletinEntity> (); 
    
    @ManyToMany
    private Collection<RightsEntity> rights = new ArrayList<RightsEntity> ();
    
    @OneToMany
    private Collection<PaymentRecord> payments = new ArrayList<PaymentRecord> ();
    
    @OneToMany
    private Collection<BookingFees> bookingfees = new ArrayList<BookingFees> ();
    
    public UserEntity() {
    }

    public Collection<BookingFees> getBookingfees() {
        return bookingfees;
    }

    public void setBookingfees(Collection<BookingFees> bookingfees) {
        this.bookingfees = bookingfees;
    }

    public Collection<PaymentRecord> getPayments() {
        return payments;
    }

    public void setPayments(Collection<PaymentRecord> payments) {
        this.payments = payments;
    }
    
    
    
    public Collection<RightsEntity> getRights() {
        return rights;
    }

    public void setRights(Collection<RightsEntity> rights) {
        this.rights = rights;
    }

    public Collection<MessageEntity> getMessages() {
        return messages;
    }

    public void setMessages(Collection<MessageEntity> messages) {
        this.messages = messages;
    }

    public Collection<BulletinEntity> getBulletins() {
        return bulletins;
    }

    public void setBulletins(Collection<BulletinEntity> bulletins) {
        this.bulletins = bulletins;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public boolean isResetPassword() {
        return resetPassword;
    }

    public void setResetPassword(boolean resetPassword) {
        this.resetPassword = resetPassword;
    }
    
    public void createAccount(String username, String password, String mobileNumber, String salt) {
        this.username = username;
        this.password = password;
        this.mobileNumber = mobileNumber;
        this.firstLogin = true;
        this.resetPassword = false;
        this.salt = salt;
        ArrayList<String> roles = new ArrayList();
        roles.add("customer");       
        this.roles = roles;
        
        ArrayList<String> dynamic= new ArrayList();
        dynamic.add("buy tickets");
        dynamic.add("finances");
        RightsEntity rights = new RightsEntity();
        rights.createRight("customer", dynamic);
        this.rights.add(rights);
    }

    public Collection<SubEvent> getSubEvents() {
        return subEvents;
    }

    public void setSubEvents(Collection<SubEvent> subEvents) {
        this.subEvents = subEvents;
    }

    public Collection<Event> getEvents() {
        return events;
    }

    public void setEvents(Collection<Event> events) {
        this.events = events;
    }

    public boolean isFirstLogin() {
        return firstLogin;
    }

    public void setFirstLogin(boolean firstLogin) {
        this.firstLogin = firstLogin;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public ArrayList<String> getRoles() {
        return roles;
    }

    public void setRoles(ArrayList<String> roles) {
        this.roles = roles;
    }
    
    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (userId != null ? userId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the userId fields are not set
        if (!(object instanceof UserEntity)) {
            return false;
        }
        UserEntity other = (UserEntity) object;
        if ((this.userId == null && other.userId != null) || (this.userId != null && !this.userId.equals(other.userId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.UserEntity[ id=" + userId + " ]";
    }
    
}
