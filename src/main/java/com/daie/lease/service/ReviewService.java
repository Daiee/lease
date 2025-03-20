package com.daie.lease.service;


import com.daie.lease.model.pojo.Review;
import com.daie.lease.model.vo.ReviewFormVo;
import org.springframework.data.domain.Page;

public interface ReviewService {

    Page<Review> getReviewPageByUserId(int pageNo, int pageSize, Long userId);

    Page<Review> getReviewPageByRoomId(int pageNo, int pageSize, Long roomId);

    Review addReview(ReviewFormVo reviewFormVo) throws Exception;

    Review getReviewById(Long id) throws Exception;

    Review updateReview(Long id,ReviewFormVo reviewFormVo) throws Exception;

    boolean deleteReview(Long id);
}
