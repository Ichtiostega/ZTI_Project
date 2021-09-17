package com.zti.bountyHunter.models;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
* Class that represetns the users entity
*/
@Entity
public class Users {
    @Id
    private String username;
    private String password;
    private Boolean enabled;
    
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
    public Boolean getEnabled() {
        return enabled;
    }
    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }
    public Users(String username, String password) {
        this.username = username;
        this.password = password;
        this.enabled = true;
    }
    public Users() {
    }
    
}
