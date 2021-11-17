package com.dshaw.asira.service;

import com.dshaw.asira.domain.Request;
import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link Request}.
 */
public interface RequestService {
    /**
     * Save a request.
     *
     * @param request the entity to save.
     * @return the persisted entity.
     */
    Request save(Request request);

    /**
     * Partially updates a request.
     *
     * @param request the entity to update partially.
     * @return the persisted entity.
     */
    Optional<Request> partialUpdate(Request request);

    /**
     * Get all the requests.
     *
     * @return the list of entities.
     */
    List<Request> findAll();

    /**
     * Get the "id" request.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<Request> findOne(Long id);

    /**
     * Delete the "id" request.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
