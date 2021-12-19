package com.dshaw.asira.service;

import com.dshaw.asira.domain.Modules;

import java.util.List;
import java.util.Optional;

import com.dshaw.asira.domain.Project;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Service Interface for managing {@link Modules}.
 */
public interface ModulesService {
    /**
     * Save a modules.
     *
     * @param modules the entity to save.
     * @return the persisted entity.
     */
    Modules save(Modules modules);

    List<Modules> getAllModulesByProject(Project project);

    /**
     * Partially updates a modules.
     *
     * @param modules the entity to update partially.
     * @return the persisted entity.
     */
    Optional<Modules> partialUpdate(Modules modules);

    /**
     * Get all the modules.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<Modules> findAll(Pageable pageable);

    /**
     * Get the "id" modules.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<Modules> findOne(Long id);

    /**
     * Delete the "id" modules.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
