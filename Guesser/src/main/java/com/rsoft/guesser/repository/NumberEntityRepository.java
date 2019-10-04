package com.rsoft.guesser.repository;

import com.rsoft.guesser.entity.NumberEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NumberEntityRepository extends JpaRepository<NumberEntity, String> {
}
