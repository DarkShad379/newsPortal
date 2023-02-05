package com.dark.news.database.entity.user;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "authusergroup")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AuthGroup {
    @Id
    @Column(name = "authgroupid")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @Column(name = "username")
    private String username;
    @Column(name = "authgroup")
    private String authGroupName;
}
