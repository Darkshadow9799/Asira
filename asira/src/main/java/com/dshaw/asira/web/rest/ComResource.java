package com.dshaw.asira.web.rest;

import com.dshaw.asira.domain.Com;
import com.dshaw.asira.repository.ComRepository;
import com.dshaw.asira.service.ComService;
import com.dshaw.asira.web.rest.errors.BadRequestAlertException;
import java.net.URI;
import java.net.URISyntaxException;
import java.time.Instant;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import tech.jhipster.web.util.HeaderUtil;
import tech.jhipster.web.util.PaginationUtil;
import tech.jhipster.web.util.ResponseUtil;

/**
 * REST controller for managing {@link com.dshaw.asira.domain.Com}.
 */
@RestController
@RequestMapping("/api")
public class ComResource {

    private final Logger log = LoggerFactory.getLogger(ComResource.class);

    private static final String ENTITY_NAME = "com";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final ComService comService;

    private final ComRepository comRepository;

    public ComResource(ComService comService, ComRepository comRepository) {
        this.comService = comService;
        this.comRepository = comRepository;
    }

    /**
     * {@code POST  /coms} : Create a new com.
     *
     * @param com the com to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new com, or with status {@code 400 (Bad Request)} if the com has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/coms")
    public ResponseEntity<Com> createCom(@Valid @RequestBody Com com) throws URISyntaxException {
        log.debug("REST request to save Com : {}", com);
        if (com.getId() != null) {
            throw new BadRequestAlertException("A new com cannot already have an ID", ENTITY_NAME, "idexists");
        }
        com.setcCreatedDate(Instant.now());
        com.setcModifiedDate(Instant.now());
        Com result = comService.save(com);
        return ResponseEntity
            .created(new URI("/api/coms/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /coms/:id} : Updates an existing com.
     *
     * @param id the id of the com to save.
     * @param com the com to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated com,
     * or with status {@code 400 (Bad Request)} if the com is not valid,
     * or with status {@code 500 (Internal Server Error)} if the com couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/coms/{id}")
    public ResponseEntity<Com> updateCom(@PathVariable(value = "id", required = false) final Long id, @Valid @RequestBody Com com)
        throws URISyntaxException {
        log.debug("REST request to update Com : {}, {}", id, com);
        if (com.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, com.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!comRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        com.setcModifiedDate(Instant.now());
        Com result = comService.save(com);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, com.getId().toString()))
            .body(result);
    }

    /**
     * {@code PATCH  /coms/:id} : Partial updates given fields of an existing com, field will ignore if it is null
     *
     * @param id the id of the com to save.
     * @param com the com to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated com,
     * or with status {@code 400 (Bad Request)} if the com is not valid,
     * or with status {@code 404 (Not Found)} if the com is not found,
     * or with status {@code 500 (Internal Server Error)} if the com couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/coms/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<Com> partialUpdateCom(@PathVariable(value = "id", required = false) final Long id, @NotNull @RequestBody Com com)
        throws URISyntaxException {
        log.debug("REST request to partial update Com partially : {}, {}", id, com);
        if (com.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, com.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!comRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<Com> result = comService.partialUpdate(com);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, com.getId().toString())
        );
    }

    /**
     * {@code GET  /coms} : get all the coms.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of coms in body.
     */
    @GetMapping("/coms")
    public ResponseEntity<List<Com>> getAllComs(Pageable pageable) {
        log.debug("REST request to get a page of Coms");
        Page<Com> page = comService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /coms/:id} : get the "id" com.
     *
     * @param id the id of the com to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the com, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/coms/{id}")
    public ResponseEntity<Com> getCom(@PathVariable Long id) {
        log.debug("REST request to get Com : {}", id);
        Optional<Com> com = comService.findOne(id);
        return ResponseUtil.wrapOrNotFound(com);
    }

    /**
     * {@code DELETE  /coms/:id} : delete the "id" com.
     *
     * @param id the id of the com to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/coms/{id}")
    public ResponseEntity<Void> deleteCom(@PathVariable Long id) {
        log.debug("REST request to delete Com : {}", id);
        comService.delete(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }
}
