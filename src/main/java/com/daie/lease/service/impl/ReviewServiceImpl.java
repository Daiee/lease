package com.daie.lease.service.impl;

import com.daie.lease.model.pojo.Review;
import com.daie.lease.repository.ReviewRepository;
import com.daie.lease.service.ReviewService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
public class ReviewServiceImpl implements ReviewService {

    private final ReviewRepository reviewRepository;

    public ReviewServiceImpl(ReviewRepository reviewRepository) {
        this.reviewRepository = reviewRepository;
    }

    @Override
    public Page<Review> getReviewPageByUserId(int pageNo, int pageSize, Long userId) {
        PageRequest pageRequest = PageRequest.of(pageNo - 1, pageSize);
        return reviewRepository.findByUserId(userId, pageRequest);
    }

    @Override
    public Page<Review> getReviewPageByRoomId(int pageNo, int pageSize, Long roomId) {
        PageRequest pageRequest = PageRequest.of(pageNo - 1, pageSize);
        return reviewRepository.findByRoomId(roomId, pageRequest);

    }
}
