package com.dshaw.asira.repository;

import com.dshaw.asira.domain.SpeProjMSm;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data SQL repository for the SpeProjMSm entity.
 */
@SuppressWarnings("unused")
@Repository
public interface SpeProjMSmRepository extends JpaRepository<SpeProjMSm, Long> {}
