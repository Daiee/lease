package com.daie.lease.model.pojo;

import com.daie.lease.common.enumeration.Gender;
import com.daie.lease.common.enumeration.Role;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table
@Data
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;

    private String password;

    private String avatarUrl;

    private String nickname;

    @Enumerated(EnumType.STRING)
    private Gender gender;

    @Enumerated(EnumType.STRING)
    private Role role;
}
