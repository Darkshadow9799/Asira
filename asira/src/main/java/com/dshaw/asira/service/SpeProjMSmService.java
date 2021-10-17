package com.dshaw.asira.service;

import com.dshaw.asira.domain.SpeProjMSm;
import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link SpeProjMSm}.
 */
public interface SpeProjMSmService {
    /**
     * Save a speProjMSm.
     *
     * @param speProjMSm the entity to save.
     * @return the persisted entity.
     */
    SpeProjMSm save(SpeProjMSm speProjMSm);

    /**
     * Partially updates a speProjMSm.
     *
     * @param speProjMSm the entity to update partially.
     * @return the persisted entity.
     */
    Optional<SpeProjMSm> partialUpdate(SpeProjMSm speProjMSm);

    /**
     * Get all the speProjMSms.
     *
     * @return the list of entities.
     */
    List<SpeProjMSm> findAll();

    /**
     * Get the "id" speProjMSm.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<SpeProjMSm> findOne(Long id);

    /**
     * Delete the "id" speProjMSm.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
