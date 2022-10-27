/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author 2dam
 */
public class Client {
    private int id;
    private String login;
    private String email;
    private String fullname;
    private int status;
    private int privilegde;
    private String password;
    private Date lastPasswordChange;
    private static final Logger LOG = Logger.getLogger(Client.class.getName());
    
    public Client(){
    }

    public Client(int id, String login, String email, String fullname, int status, int privilegde, String password, Date lastPasswordChange) {
        this.id = id;
        this.login = login;
        this.email = email;
        this.fullname = fullname;
        this.status = status;
        this.privilegde = privilegde;
        this.password = password;
        this.lastPasswordChange = lastPasswordChange;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getPrivilegde() {
        return privilegde;
    }

    public void setPrivilegde(int privilegde) {
        this.privilegde = privilegde;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Date getLastPasswordChange() {
        return lastPasswordChange;
    }

    public void setLastPasswordChange(Date lastPasswordChange) {
        this.lastPasswordChange = lastPasswordChange;
    }
    
    public void GetData(){
        LOG.log(Level.INFO, "Id: " + this.id);
        LOG.log(Level.INFO, "Login: " + this.login);
        LOG.log(Level.INFO, "Email: " + this.email);
        LOG.log(Level.INFO, "Full name: " + this.fullname);
        LOG.log(Level.INFO, "Status: " + this.status);
        LOG.log(Level.INFO, "Priviledge: " + this.privilegde);
        LOG.log(Level.INFO, "Last paswword change: " + this.lastPasswordChange);
    }
    
    
}
