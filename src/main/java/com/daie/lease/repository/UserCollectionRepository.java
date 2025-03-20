package com.daie.lease.repository;

import com.daie.lease.model.pojo.UserCollection;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserCollectionRepository extends JpaRepository<UserCollection, Long> {
    Optional<UserCollection> findByUserIdAndRoomId(Long userId, Long itemId);

    Page<Long> findRoomIdsByUserId(Long userId, Pageable pageable);
}
