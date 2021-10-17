package com.dshaw.asira.service;

import com.dshaw.asira.domain.SpeProj;
import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link SpeProj}.
 */
public interface SpeProjService {
    /**
     * Save a speProj.
     *
     * @param speProj the entity to save.
     * @return the persisted entity.
     */
    SpeProj save(SpeProj speProj);

    /**
     * Partially updates a speProj.
     *
     * @param speProj the entity to update partially.
     * @return the persisted entity.
     */
    Optional<SpeProj> partialUpdate(SpeProj speProj);

    /**
     * Get all the speProjs.
     *
     * @return the list of entities.
     */
    List<SpeProj> findAll();

    /**
     * Get the "id" speProj.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<SpeProj> findOne(Long id);

    /**
     * Delete the "id" speProj.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
