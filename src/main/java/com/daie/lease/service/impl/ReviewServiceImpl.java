package com.daie.lease.service.impl;

import com.daie.lease.model.pojo.Review;
import com.daie.lease.model.pojo.Room;
import com.daie.lease.model.pojo.User;
import com.daie.lease.model.vo.ReviewFormVo;
import com.daie.lease.repository.ReviewRepository;
import com.daie.lease.repository.RoomRepository;
import com.daie.lease.repository.UserRepository;
import com.daie.lease.service.ReviewService;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ReviewServiceImpl implements ReviewService {

    private final ReviewRepository reviewRepository;
    private final UserRepository userRepository;
    private final RoomRepository roomRepository;

    public ReviewServiceImpl(ReviewRepository reviewRepository, UserRepository userRepository, RoomRepository roomRepository) {
        this.reviewRepository = reviewRepository;
        this.userRepository = userRepository;
        this.roomRepository = roomRepository;
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

    @Override
    public Review addReview(ReviewFormVo reviewFormVo) {
        Optional<Review> reviewOptional = calculateOverallRating(reviewFormVo);
        if (reviewOptional.isPresent()) {
            Review review = reviewOptional.get();

            Optional<User> userOptional = userRepository.findById(reviewFormVo.getUserId());
            if (userOptional.isPresent()) {
                review.setUser(userOptional.get());
            } else {
                throw new IllegalArgumentException("用户不存在，用户 ID: " + reviewFormVo.getUserId());
            }

            Optional<Room> roomOptional = roomRepository.findById(reviewFormVo.getRoomId());
            if (roomOptional.isPresent()) {
                review.setRoom(roomOptional.get());
            } else {
                throw new IllegalArgumentException("房间不存在，房间 ID: " + reviewFormVo.getRoomId());
            }
            return reviewRepository.save(review);
        }
        return null;
    }

    @Override
    public Review getReviewById(Long id) throws Exception {
        return reviewRepository.findById(id).orElseThrow(Exception::new);
    }

    @Override
    public Review updateReview(Long id, ReviewFormVo reviewFormVo) throws Exception {
        Review existingReview = reviewRepository.findById(id)
            .orElseThrow(() -> new Exception("Review with id " + id + " not found"));

        Optional<Review> newReviewOptional = calculateOverallRating(reviewFormVo);
        if (newReviewOptional.isPresent()) {
            Review newReview = newReviewOptional.get();
            newReview.setId(existingReview.getId());
            newReview.setUser(existingReview.getUser());
            newReview.setRoom(existingReview.getRoom());
            BeanUtils.copyProperties(newReview, existingReview, "id", "user", "room");
        }
        return reviewRepository.save(existingReview);
    }

    @Override
    public boolean deleteReview(Long id) {
        if (reviewRepository.existsById(id)) {
            reviewRepository.deleteById(id);
            return true;
        }
        return false;
    }

    private Optional<Review> calculateOverallRating(ReviewFormVo reviewFormVo) {
        int[] totalRatingHolder = {0};
        int[] validRatingCountHolder = {0};

        // 处理 rating1
        Optional.ofNullable(reviewFormVo.getRating1())
            .ifPresent(rating -> {
                totalRatingHolder[0] += rating;
                validRatingCountHolder[0]++;
            });

        // 处理 rating2
        Optional.ofNullable(reviewFormVo.getRating2())
            .ifPresent(rating -> {
                totalRatingHolder[0] += rating;
                validRatingCountHolder[0]++;
            });

        // 处理 rating3
        Optional.ofNullable(reviewFormVo.getRating3())
            .ifPresent(rating -> {
                totalRatingHolder[0] += rating;
                validRatingCountHolder[0]++;
            });

        // 处理 rating4
        Optional.ofNullable(reviewFormVo.getRating4())
            .ifPresent(rating -> {
                totalRatingHolder[0] += rating;
                validRatingCountHolder[0]++;
            });

        // 处理 rating5
        Optional.ofNullable(reviewFormVo.getRating5())
            .ifPresent(rating -> {
                totalRatingHolder[0] += rating;
                validRatingCountHolder[0]++;
            });

        // 处理 rating6
        Optional.ofNullable(reviewFormVo.getRating6())
            .ifPresent(rating -> {
                totalRatingHolder[0] += rating;
                validRatingCountHolder[0]++;
            });

        if (validRatingCountHolder[0] > 0) {
            float overallRating = (float) totalRatingHolder[0] / validRatingCountHolder[0];
            Review review = new Review();
            review.setOverallRating(overallRating);
            BeanUtils.copyProperties(reviewFormVo, review);
            return Optional.of(review);
        }
        return Optional.empty();
    }
}
