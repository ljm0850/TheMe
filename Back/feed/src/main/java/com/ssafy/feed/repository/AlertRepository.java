package com.ssafy.feed.repository;

import com.ssafy.feed.entity.Alert;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AlertRepository extends JpaRepository<Alert,Integer> {
    Optional<Alert> findByReferenceIdxAndTypeAndReportUserIdx(int referenceIdx, int type, int reportUserIdx);

    List<Alert> findByReferenceIdxAndType(int referenceIdx, int type);
}
