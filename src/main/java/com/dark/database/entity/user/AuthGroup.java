package com.dark.database.entity.user;

import javax.persistence.*;

@Entity
@Table(name = "authusergroup")
public class AuthGroup {
    @Id
    @Column(name = "authgroupid")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @Column(name = "username")
    private String username;
    @Column(name = "authgroup")
    private String authGroupName;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getAuthGroupName() {
        return authGroupName;
    }

    public void setAuthGroupName(String authGroupName) {
        this.authGroupName = authGroupName;
    }
}
