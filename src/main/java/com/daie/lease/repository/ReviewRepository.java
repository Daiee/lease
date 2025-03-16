package com.daie.lease.repository;

import com.daie.lease.model.pojo.Review;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Long> {

    Page<Review> findByUserId(Long userId, Pageable pageable);

    Page<Review> findByRoomId(Long roomId, Pageable pageable);
}
