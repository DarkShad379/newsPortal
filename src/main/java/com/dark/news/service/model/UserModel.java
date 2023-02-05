package com.dark.news.service.model;

import com.dark.news.database.entity.user.UserEntity;
import lombok.Data;

@Data
public class UserModel {
    private Integer id;
    private String username;
    private String password;

    public UserModel(UserEntity userEntity) {
        this.id = userEntity.getId();
        this.username = userEntity.getUsername();
        this.password = userEntity.getPassword();
    }
}
