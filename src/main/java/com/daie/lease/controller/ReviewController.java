package com.daie.lease.controller;

import com.daie.lease.common.result.Result;
import com.daie.lease.model.pojo.Review;
import com.daie.lease.model.vo.ReviewFormVo;
import com.daie.lease.service.ReviewService;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

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

    // 添加评价
    @PostMapping("add")
    public Result<Review> addReview(@RequestBody ReviewFormVo reviewFormVo) throws Exception {
        return Result.success(reviewService.addReview(reviewFormVo));
    }

    // 获取评价
    @GetMapping("get")
    public Result<Review> getReview(@RequestParam Long id) throws Exception {
        return Result.success(reviewService.getReviewById(id));
    }

    // 更新评价
    @PutMapping("update")
    public Result<Review> updateReview(@RequestParam Long id,
                                       @RequestBody ReviewFormVo reviewFormVo) throws Exception {
        return Result.success(reviewService.updateReview(id, reviewFormVo));
    }

    // 删除评价
    @DeleteMapping("delete")
    public Result<Boolean> deleteReview(@RequestParam Long id) {
        return Result.success(reviewService.deleteReview(id));
    }
}
