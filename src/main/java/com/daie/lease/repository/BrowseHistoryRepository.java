package com.daie.lease.repository;

import com.daie.lease.model.pojo.BrowseHistory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BrowseHistoryRepository extends JpaRepository<BrowseHistory, Long> {
}