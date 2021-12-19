package com.dshaw.asira.service;

import com.dshaw.asira.domain.Modules;
import com.dshaw.asira.domain.Org;
import com.dshaw.asira.domain.Project;

import java.util.List;
import java.util.Optional;

import com.dshaw.asira.domain.Spe;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Service Interface for managing {@link Project}.
 */
public interface ProjectService {
    /**
     * Save a project.
     *
     * @param project the entity to save.
     * @return the persisted entity.
     */
    Project save(Project project);

    /**
     * Partially updates a project.
     *
     * @param project the entity to update partially.
     * @return the persisted entity.
     */
    Optional<Project> partialUpdate(Project project);

    /**
     * Get all the projects.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<Project> findAll(Pageable pageable);

    List<Project> findAllProjectsByOrg(Org org);

    List<Project> findAllProjectsBySpe(Spe spe);

    List<Modules> findAllModulesByProject(Long id);
    /**
     * Get the "id" project.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<Project> findOne(Long id);

    /**
     * Delete the "id" project.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
