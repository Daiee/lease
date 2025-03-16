package com.daie.lease.controller;

import com.daie.lease.common.result.Result;
import com.daie.lease.model.pojo.Review;
import com.daie.lease.service.ReviewService;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("review")
public class ReviewController {

    private final ReviewService reviewService;

    public ReviewController(ReviewService reviewService) {
        this.reviewService = reviewService;
    }

    // 根据 user_id 查询评价列表
    @GetMapping("user")
    public Result<Page<Review>> getReviewPageByUserId(@RequestParam(defaultValue = "1") int pageNo,
                                                      @RequestParam(defaultValue = "10") int pageSize,
                                                      @RequestParam(value = "id") Long userId) {
        Page<Review> reviewPage = reviewService.getReviewPageByUserId(pageNo, pageSize, userId );
        return Result.success(reviewPage);
    }

    // 根据 room_id 查询评价列表
    @GetMapping("room")
    public Result<Page<Review>> getReviewPageByRoomId(@RequestParam(defaultValue = "1") int pageNo,
                                                      @RequestParam(defaultValue = "10") int pageSize,
                                                      @RequestParam(value = "id") Long roomId) {
       Page<Review> reviewPage = reviewService.getReviewPageByRoomId(pageNo, pageSize, roomId);
       return Result.success(reviewPage);
    }
}
