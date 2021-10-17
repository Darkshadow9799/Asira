package com.dshaw.asira.web.rest;

import com.dshaw.asira.domain.SpeProjMSm;
import com.dshaw.asira.repository.SpeProjMSmRepository;
import com.dshaw.asira.service.SpeProjMSmService;
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

/**
 * REST controller for managing {@link com.dshaw.asira.domain.SpeProjMSm}.
 */
@RestController
@RequestMapping("/api")
public class SpeProjMSmResource {

    private final Logger log = LoggerFactory.getLogger(SpeProjMSmResource.class);

    private static final String ENTITY_NAME = "speProjMSm";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final SpeProjMSmService speProjMSmService;

    private final SpeProjMSmRepository speProjMSmRepository;

    public SpeProjMSmResource(SpeProjMSmService speProjMSmService, SpeProjMSmRepository speProjMSmRepository) {
        this.speProjMSmService = speProjMSmService;
        this.speProjMSmRepository = speProjMSmRepository;
    }

    /**
     * {@code POST  /spe-proj-m-sms} : Create a new speProjMSm.
     *
     * @param speProjMSm the speProjMSm to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new speProjMSm, or with status {@code 400 (Bad Request)} if the speProjMSm has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/spe-proj-m-sms")
    public ResponseEntity<SpeProjMSm> createSpeProjMSm(@Valid @RequestBody SpeProjMSm speProjMSm) throws URISyntaxException {
        log.debug("REST request to save SpeProjMSm : {}", speProjMSm);
        if (speProjMSm.getId() != null) {
            throw new BadRequestAlertException("A new speProjMSm cannot already have an ID", ENTITY_NAME, "idexists");
        }
        SpeProjMSm result = speProjMSmService.save(speProjMSm);
        return ResponseEntity
            .created(new URI("/api/spe-proj-m-sms/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /spe-proj-m-sms/:id} : Updates an existing speProjMSm.
     *
     * @param id the id of the speProjMSm to save.
     * @param speProjMSm the speProjMSm to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated speProjMSm,
     * or with status {@code 400 (Bad Request)} if the speProjMSm is not valid,
     * or with status {@code 500 (Internal Server Error)} if the speProjMSm couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/spe-proj-m-sms/{id}")
    public ResponseEntity<SpeProjMSm> updateSpeProjMSm(
        @PathVariable(value = "id", required = false) final Long id,
        @Valid @RequestBody SpeProjMSm speProjMSm
    ) throws URISyntaxException {
        log.debug("REST request to update SpeProjMSm : {}, {}", id, speProjMSm);
        if (speProjMSm.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, speProjMSm.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!speProjMSmRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        SpeProjMSm result = speProjMSmService.save(speProjMSm);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, speProjMSm.getId().toString()))
            .body(result);
    }

    /**
     * {@code PATCH  /spe-proj-m-sms/:id} : Partial updates given fields of an existing speProjMSm, field will ignore if it is null
     *
     * @param id the id of the speProjMSm to save.
     * @param speProjMSm the speProjMSm to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated speProjMSm,
     * or with status {@code 400 (Bad Request)} if the speProjMSm is not valid,
     * or with status {@code 404 (Not Found)} if the speProjMSm is not found,
     * or with status {@code 500 (Internal Server Error)} if the speProjMSm couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/spe-proj-m-sms/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<SpeProjMSm> partialUpdateSpeProjMSm(
        @PathVariable(value = "id", required = false) final Long id,
        @NotNull @RequestBody SpeProjMSm speProjMSm
    ) throws URISyntaxException {
        log.debug("REST request to partial update SpeProjMSm partially : {}, {}", id, speProjMSm);
        if (speProjMSm.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, speProjMSm.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!speProjMSmRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<SpeProjMSm> result = speProjMSmService.partialUpdate(speProjMSm);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, speProjMSm.getId().toString())
        );
    }

    /**
     * {@code GET  /spe-proj-m-sms} : get all the speProjMSms.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of speProjMSms in body.
     */
    @GetMapping("/spe-proj-m-sms")
    public List<SpeProjMSm> getAllSpeProjMSms() {
        log.debug("REST request to get all SpeProjMSms");
        return speProjMSmService.findAll();
    }

    /**
     * {@code GET  /spe-proj-m-sms/:id} : get the "id" speProjMSm.
     *
     * @param id the id of the speProjMSm to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the speProjMSm, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/spe-proj-m-sms/{id}")
    public ResponseEntity<SpeProjMSm> getSpeProjMSm(@PathVariable Long id) {
        log.debug("REST request to get SpeProjMSm : {}", id);
        Optional<SpeProjMSm> speProjMSm = speProjMSmService.findOne(id);
        return ResponseUtil.wrapOrNotFound(speProjMSm);
    }

    /**
     * {@code DELETE  /spe-proj-m-sms/:id} : delete the "id" speProjMSm.
     *
     * @param id the id of the speProjMSm to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/spe-proj-m-sms/{id}")
    public ResponseEntity<Void> deleteSpeProjMSm(@PathVariable Long id) {
        log.debug("REST request to delete SpeProjMSm : {}", id);
        speProjMSmService.delete(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }
}
