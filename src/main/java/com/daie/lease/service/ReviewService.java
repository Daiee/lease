package com.daie.lease.service;


import com.daie.lease.model.pojo.Review;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ReviewService {

    Page<Review> getReviewPageByUserId(int pageNo, int pageSize, Long userId);

    Page<Review> getReviewPageByRoomId(int pageNo, int pageSize, Long roomId);
}
