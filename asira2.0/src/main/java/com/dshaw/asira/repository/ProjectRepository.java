package com.dshaw.asira.repository;

import com.dshaw.asira.domain.Org;
import com.dshaw.asira.domain.Project;
import liquibase.pro.packaged.P;
import org.springframework.data.domain.Example;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Spring Data SQL repository for the Project entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ProjectRepository extends JpaRepository<Project, Long> {
    List<Project> findByOrg(Org org);
}
