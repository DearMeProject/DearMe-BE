package com.dearme.backend.dearmebe.domain.memo.repository;

import com.dearme.backend.dearmebe.domain.memo.entity.Memo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface MemoRepository extends JpaRepository<Memo, Long> {

    List<Memo> findByClientId(String clientId);

    List<Memo> findAllByClientIdOrderByDateAsc(String clientId);

    Optional<Memo> findById(Long id);
}

