package com.dshaw.asira.repository;

import com.dshaw.asira.domain.SpeProj;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data SQL repository for the SpeProj entity.
 */
@SuppressWarnings("unused")
@Repository
public interface SpeProjRepository extends JpaRepository<SpeProj, Long> {}
