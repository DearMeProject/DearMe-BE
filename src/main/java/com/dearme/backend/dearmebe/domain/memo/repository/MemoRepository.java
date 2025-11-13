package com.dearme.backend.dearmebe.domain.memo.repository;

import com.dearme.backend.dearmebe.domain.memo.entity.Memo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MemoRepository extends JpaRepository<Memo, Long> {

    List<Memo> findByClientId(String clientId);
    List<Memo> findAllByClientIdOrderByDateDesc(String clientId);
}

