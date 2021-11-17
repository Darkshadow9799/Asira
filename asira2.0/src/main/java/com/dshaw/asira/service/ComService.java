package com.dshaw.asira.service;

import com.dshaw.asira.domain.Com;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Service Interface for managing {@link Com}.
 */
public interface ComService {
    /**
     * Save a com.
     *
     * @param com the entity to save.
     * @return the persisted entity.
     */
    Com save(Com com);

    /**
     * Partially updates a com.
     *
     * @param com the entity to update partially.
     * @return the persisted entity.
     */
    Optional<Com> partialUpdate(Com com);

    /**
     * Get all the coms.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<Com> findAll(Pageable pageable);

    /**
     * Get the "id" com.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<Com> findOne(Long id);

    /**
     * Delete the "id" com.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
