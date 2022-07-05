package com.example.soap.model;

import com.example.models.User;
import java.util.List;

public class ExpiredUsers {

    private Long ownerId;

    private List<User> expiredUsers;

    public ExpiredUsers() {
    }

    public Long getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(Long ownerId) {
        this.ownerId = ownerId;
    }

    public List<User> getExpiredUsers() {
        return expiredUsers;
    }

    public void setExpiredUsers(List<User> expiredUsers) {
        this.expiredUsers = expiredUsers;
    }

    public ExpiredUsers(Long ownerId, List<User> expiredUsers) {
        this.ownerId = ownerId;
        this.expiredUsers = expiredUsers;
    }
}
