package com.dshaw.asira.service;

import com.dshaw.asira.domain.SubModules;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Service Interface for managing {@link SubModules}.
 */
public interface SubModulesService {
    /**
     * Save a subModules.
     *
     * @param subModules the entity to save.
     * @return the persisted entity.
     */
    SubModules save(SubModules subModules);

    /**
     * Partially updates a subModules.
     *
     * @param subModules the entity to update partially.
     * @return the persisted entity.
     */
    Optional<SubModules> partialUpdate(SubModules subModules);

    /**
     * Get all the subModules.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<SubModules> findAll(Pageable pageable);

    /**
     * Get the "id" subModules.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<SubModules> findOne(Long id);

    /**
     * Delete the "id" subModules.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
