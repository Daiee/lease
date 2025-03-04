package com.daie.lease.model.pojo;

import com.daie.lease.common.enumeration.Gender;
import com.daie.lease.common.enumeration.Role;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.Comment;

import java.util.List;

@Entity
@Comment("用户表")
@Table
@Data
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String username;

    @Column(nullable = false)
    private String password;

    private String avatarUrl;

    private String nickname;

    @Enumerated(EnumType.STRING)
    private Gender gender;

    @Enumerated(EnumType.STRING)
    private Role role;

    @OneToMany(mappedBy = "user")
    private List<Review> reviewList;
}
