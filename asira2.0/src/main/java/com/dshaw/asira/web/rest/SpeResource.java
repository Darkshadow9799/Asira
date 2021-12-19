package com.dshaw.asira.web.rest;

import com.dshaw.asira.domain.Project;
import com.dshaw.asira.domain.Spe;
import com.dshaw.asira.repository.SpeRepository;
import com.dshaw.asira.service.SpeService;
import com.dshaw.asira.web.rest.errors.BadRequestAlertException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tech.jhipster.web.util.HeaderUtil;
import tech.jhipster.web.util.ResponseUtil;
import java.time.Instant;

/**
 * REST controller for managing {@link com.dshaw.asira.domain.Spe}.
 */
@RestController
@RequestMapping("/api")
public class SpeResource {

    private final Logger log = LoggerFactory.getLogger(SpeResource.class);

    private static final String ENTITY_NAME = "spe";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final SpeService speService;

    private final SpeRepository speRepository;

    public SpeResource(SpeService speService, SpeRepository speRepository) {
        this.speService = speService;
        this.speRepository = speRepository;
    }

    /**
     * {@code POST  /spes} : Create a new spe.
     *
     * @param spe the spe to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new spe, or with status {@code 400 (Bad Request)} if the spe has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/spes")
    public ResponseEntity<Spe> createSpe(@Valid @RequestBody Spe spe) throws URISyntaxException {
        log.debug("REST request to save Spe : {}", spe);
        if (spe.getId() != null) {
            throw new BadRequestAlertException("A new spe cannot already have an ID", ENTITY_NAME, "idexists");
        }
        spe.setCreatedDate(Instant.now());
        spe.setModifiedDate(Instant.now());
        Spe result = speService.save(spe);
        return ResponseEntity
            .created(new URI("/api/spes/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /spes/:id} : Updates an existing spe.
     *
     * @param id the id of the spe to save.
     * @param spe the spe to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated spe,
     * or with status {@code 400 (Bad Request)} if the spe is not valid,
     * or with status {@code 500 (Internal Server Error)} if the spe couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/spes/{id}")
    public ResponseEntity<Spe> updateSpe(@PathVariable(value = "id", required = false) final Long id, @Valid @RequestBody Spe spe)
        throws URISyntaxException {
        log.debug("REST request to update Spe : {}, {}", id, spe);
        if (spe.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, spe.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!speRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        spe.setModifiedDate(Instant.now());
        Spe result = speService.save(spe);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, spe.getId().toString()))
            .body(result);
    }

    /**
     * {@code PATCH  /spes/:id} : Partial updates given fields of an existing spe, field will ignore if it is null
     *
     * @param id the id of the spe to save.
     * @param spe the spe to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated spe,
     * or with status {@code 400 (Bad Request)} if the spe is not valid,
     * or with status {@code 404 (Not Found)} if the spe is not found,
     * or with status {@code 500 (Internal Server Error)} if the spe couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/spes/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<Spe> partialUpdateSpe(@PathVariable(value = "id", required = false) final Long id, @NotNull @RequestBody Spe spe)
        throws URISyntaxException {
        log.debug("REST request to partial update Spe partially : {}, {}", id, spe);
        if (spe.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, spe.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!speRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<Spe> result = speService.partialUpdate(spe);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, spe.getId().toString())
        );
    }

    /**
     * {@code GET  /spes} : get all the spes.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of spes in body.
     */
    @GetMapping("/spes")
    public List<Spe> getAllSpes() {
        log.debug("REST request to get all Spes");
        return speService.findAll();
    }

    /**
     * {@code GET  /spes/:id} : get the "id" spe.
     *
     * @param id the id of the spe to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the spe, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/spes/{id}")
    public ResponseEntity<Spe> getSpe(@PathVariable Long id) {
        log.debug("REST request to get Spe : {}", id);
        Optional<Spe> spe = speService.findOne(id);
        return ResponseUtil.wrapOrNotFound(spe);
    }

    @GetMapping("/spes/{email}")
    public ResponseEntity<Spe> getSpeByEmail(@PathVariable String email) {
        log.debug("REST request to get Spe : {}", email);
        Optional<Spe> spe = speService.findOneByEmailId(email);
        return ResponseUtil.wrapOrNotFound(spe);
    }

    @GetMapping("/specs/projects/{id}")
    public ResponseEntity<List<Project>> getAllProjectsBySpe(@PathVariable Long id){
        return ResponseEntity.ok().body(speService.findAllProjectsBySpe(id));
    }

    /**
     * {@code DELETE  /spes/:id} : delete the "id" spe.
     *
     * @param id the id of the spe to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/spes/{id}")
    public ResponseEntity<Void> deleteSpe(@PathVariable Long id) {
        log.debug("REST request to delete Spe : {}", id);
        speService.delete(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }
}
