package com.daie.lease.model.pojo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.Comment;

import java.util.List;

@Entity
@Comment("房间表")
@Table
@Data
public class Room {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String roomName;

    private String roomDesc;

    private String roomPic;

    private String address;

    private String latitude;

    private String longitude;

    private String phone;

    @JsonIgnore
    @OneToMany(mappedBy = "room", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Review> reviewList;

}
