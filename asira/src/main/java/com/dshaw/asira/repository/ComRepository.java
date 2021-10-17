package com.dshaw.asira.repository;

import com.dshaw.asira.domain.Com;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data SQL repository for the Com entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ComRepository extends JpaRepository<Com, Long> {}
