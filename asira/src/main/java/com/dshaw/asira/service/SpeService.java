package com.dshaw.asira.service;

import com.dshaw.asira.domain.Spe;
import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link Spe}.
 */
public interface SpeService {
    /**
     * Save a spe.
     *
     * @param spe the entity to save.
     * @return the persisted entity.
     */
    Spe save(Spe spe);

    /**
     * Partially updates a spe.
     *
     * @param spe the entity to update partially.
     * @return the persisted entity.
     */
    Optional<Spe> partialUpdate(Spe spe);

    /**
     * Get all the spes.
     *
     * @return the list of entities.
     */
    List<Spe> findAll();

    /**
     * Get the "id" spe.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<Spe> findOne(Long id);

    /**
     * Delete the "id" spe.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
