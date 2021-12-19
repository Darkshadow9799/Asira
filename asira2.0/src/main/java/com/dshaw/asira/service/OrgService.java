package com.dshaw.asira.service;

import com.dshaw.asira.domain.Org;
import java.util.List;
import java.util.Optional;

import com.dshaw.asira.domain.Project;
import com.dshaw.asira.domain.Spe;

/**
 * Service Interface for managing {@link Org}.
 */
public interface OrgService {
    /**
     * Save a org.
     *
     * @param org the entity to save.
     * @return the persisted entity.
     */
    Org save(Org org);

    /**
     * Partially updates a org.
     *
     * @param org the entity to update partially.
     * @return the persisted entity.
     */
    Optional<Org> partialUpdate(Org org);

    /**
     * Get all the orgs.
     *
     * @return the list of entities.
     */
    List<Org> findAll();

    List<Spe> findAllSpesOfOrg(Long id);

    List<Project> findAllProjectsOfOrg(Long id);

    /**
     * Get the "id" org.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<Org> findOne(Long id);

    /**
     * Delete the "id" org.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
