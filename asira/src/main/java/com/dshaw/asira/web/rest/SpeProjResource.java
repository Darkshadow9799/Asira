package com.dshaw.asira.web.rest;

import com.dshaw.asira.domain.SpeProj;
import com.dshaw.asira.repository.SpeProjRepository;
import com.dshaw.asira.service.SpeProjService;
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
 * REST controller for managing {@link com.dshaw.asira.domain.SpeProj}.
 */
@RestController
@RequestMapping("/api")
public class SpeProjResource {

    private final Logger log = LoggerFactory.getLogger(SpeProjResource.class);

    private static final String ENTITY_NAME = "speProj";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final SpeProjService speProjService;

    private final SpeProjRepository speProjRepository;

    public SpeProjResource(SpeProjService speProjService, SpeProjRepository speProjRepository) {
        this.speProjService = speProjService;
        this.speProjRepository = speProjRepository;
    }

    /**
     * {@code POST  /spe-projs} : Create a new speProj.
     *
     * @param speProj the speProj to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new speProj, or with status {@code 400 (Bad Request)} if the speProj has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/spe-projs")
    public ResponseEntity<SpeProj> createSpeProj(@Valid @RequestBody SpeProj speProj) throws URISyntaxException {
        log.debug("REST request to save SpeProj : {}", speProj);
        if (speProj.getId() != null) {
            throw new BadRequestAlertException("A new speProj cannot already have an ID", ENTITY_NAME, "idexists");
        }
        SpeProj result = speProjService.save(speProj);
        return ResponseEntity
            .created(new URI("/api/spe-projs/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /spe-projs/:id} : Updates an existing speProj.
     *
     * @param id the id of the speProj to save.
     * @param speProj the speProj to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated speProj,
     * or with status {@code 400 (Bad Request)} if the speProj is not valid,
     * or with status {@code 500 (Internal Server Error)} if the speProj couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/spe-projs/{id}")
    public ResponseEntity<SpeProj> updateSpeProj(
        @PathVariable(value = "id", required = false) final Long id,
        @Valid @RequestBody SpeProj speProj
    ) throws URISyntaxException {
        log.debug("REST request to update SpeProj : {}, {}", id, speProj);
        if (speProj.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, speProj.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!speProjRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        SpeProj result = speProjService.save(speProj);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, speProj.getId().toString()))
            .body(result);
    }

    /**
     * {@code PATCH  /spe-projs/:id} : Partial updates given fields of an existing speProj, field will ignore if it is null
     *
     * @param id the id of the speProj to save.
     * @param speProj the speProj to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated speProj,
     * or with status {@code 400 (Bad Request)} if the speProj is not valid,
     * or with status {@code 404 (Not Found)} if the speProj is not found,
     * or with status {@code 500 (Internal Server Error)} if the speProj couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/spe-projs/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<SpeProj> partialUpdateSpeProj(
        @PathVariable(value = "id", required = false) final Long id,
        @NotNull @RequestBody SpeProj speProj
    ) throws URISyntaxException {
        log.debug("REST request to partial update SpeProj partially : {}, {}", id, speProj);
        if (speProj.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, speProj.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!speProjRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<SpeProj> result = speProjService.partialUpdate(speProj);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, speProj.getId().toString())
        );
    }

    /**
     * {@code GET  /spe-projs} : get all the speProjs.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of speProjs in body.
     */
    @GetMapping("/spe-projs")
    public List<SpeProj> getAllSpeProjs() {
        log.debug("REST request to get all SpeProjs");
        return speProjService.findAll();
    }

    /**
     * {@code GET  /spe-projs/:id} : get the "id" speProj.
     *
     * @param id the id of the speProj to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the speProj, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/spe-projs/{id}")
    public ResponseEntity<SpeProj> getSpeProj(@PathVariable Long id) {
        log.debug("REST request to get SpeProj : {}", id);
        Optional<SpeProj> speProj = speProjService.findOne(id);
        return ResponseUtil.wrapOrNotFound(speProj);
    }

    /**
     * {@code DELETE  /spe-projs/:id} : delete the "id" speProj.
     *
     * @param id the id of the speProj to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/spe-projs/{id}")
    public ResponseEntity<Void> deleteSpeProj(@PathVariable Long id) {
        log.debug("REST request to delete SpeProj : {}", id);
        speProjService.delete(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }
}
