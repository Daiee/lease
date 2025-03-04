package com.daie.lease.model.pojo;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.Comment;

@Entity
@Comment("评价表")
@Table
@Data
public class Review {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Float overallRating;

    @Comment("干净卫生")
    private Integer rating1;

    @Comment("如实描述")
    private Integer rating2;

    @Comment("入住便捷")
    private Integer rating3;

    @Comment("沟通顺畅")
    private Integer rating4;

    @Comment("地段优越")
    private Integer rating5;

    @Comment("高性价比")
    private Integer rating6;

    private String comment;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "room_id")
    private Room room;
}
