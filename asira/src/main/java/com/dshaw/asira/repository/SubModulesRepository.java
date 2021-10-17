package com.dshaw.asira.repository;

import com.dshaw.asira.domain.SubModules;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data SQL repository for the SubModules entity.
 */
@SuppressWarnings("unused")
@Repository
public interface SubModulesRepository extends JpaRepository<SubModules, Long> {}
