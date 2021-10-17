package com.dshaw.asira.web.rest;

import com.dshaw.asira.domain.SpeProjMSm;
import com.dshaw.asira.domain.SubModules;
import com.dshaw.asira.repository.SubModulesRepository;
import com.dshaw.asira.service.ModulesService;
import com.dshaw.asira.service.SpeProjMSmService;
import com.dshaw.asira.service.SubModulesService;
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
 * REST controller for managing {@link com.dshaw.asira.domain.SubModules}.
 */
@RestController
@RequestMapping("/api")
public class SubModulesResource {

    private final Logger log = LoggerFactory.getLogger(SubModulesResource.class);

    private static final String ENTITY_NAME = "subModules";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final SubModulesService subModulesService;

    private final SubModulesRepository subModulesRepository;

    private final SpeProjMSmService speProjMSmService;

    private final ModulesService modulesService;

    public SubModulesResource(SubModulesService subModulesService, SubModulesRepository subModulesRepository,
                              SpeProjMSmService speProjMSmService, ModulesService modulesService) {
        this.subModulesService = subModulesService;
        this.subModulesRepository = subModulesRepository;
        this.speProjMSmService = speProjMSmService;
        this.modulesService = modulesService;
    }

    /**
     * {@code POST  /sub-modules} : Create a new subModules.
     *
     * @param subModules the subModules to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new subModules, or with status {@code 400 (Bad Request)} if the subModules has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/sub-modules")
    public ResponseEntity<SubModules> createSubModules(@Valid @RequestBody SubModules subModules) throws URISyntaxException {
        log.debug("REST request to save SubModules : {}", subModules);
        if (subModules.getId() != null) {
            throw new BadRequestAlertException("A new subModules cannot already have an ID", ENTITY_NAME, "idexists");
        }
        subModules.setSmCreatedDate(Instant.now());
        subModules.setSmModifiedDate(Instant.now());
        SpeProjMSm speProjMSm = new SpeProjMSm();
        SubModules result = subModulesService.save(subModules);
        createSpeProjMSm(speProjMSm, result);
        return ResponseEntity
            .created(new URI("/api/sub-modules/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    private void createSpeProjMSm(SpeProjMSm speProjMSm, SubModules result) {
        speProjMSm.setProjId(modulesService.findOne(result.getSmMId()).get().getmProjId());
        speProjMSm.setSpeId(result.getSmSpeId());
        speProjMSm.setmId(result.getSmMId());
        speProjMSm.setSmId(result.getId());
        speProjMSmService.save(speProjMSm);
    }

    /**
     * {@code PUT  /sub-modules/:id} : Updates an existing subModules.
     *
     * @param id the id of the subModules to save.
     * @param subModules the subModules to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated subModules,
     * or with status {@code 400 (Bad Request)} if the subModules is not valid,
     * or with status {@code 500 (Internal Server Error)} if the subModules couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/sub-modules/{id}")
    public ResponseEntity<SubModules> updateSubModules(
        @PathVariable(value = "id", required = false) final Long id,
        @Valid @RequestBody SubModules subModules
    ) throws URISyntaxException {
        log.debug("REST request to update SubModules : {}, {}", id, subModules);
        if (subModules.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, subModules.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!subModulesRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        SpeProjMSm speProjMSm = new SpeProjMSm();
        subModules.setSmModifiedDate(Instant.now());
        SubModules result = subModulesService.save(subModules);
        createSpeProjMSm(speProjMSm, result);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, subModules.getId().toString()))
            .body(result);
    }

    /**
     * {@code PATCH  /sub-modules/:id} : Partial updates given fields of an existing subModules, field will ignore if it is null
     *
     * @param id the id of the subModules to save.
     * @param subModules the subModules to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated subModules,
     * or with status {@code 400 (Bad Request)} if the subModules is not valid,
     * or with status {@code 404 (Not Found)} if the subModules is not found,
     * or with status {@code 500 (Internal Server Error)} if the subModules couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/sub-modules/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<SubModules> partialUpdateSubModules(
        @PathVariable(value = "id", required = false) final Long id,
        @NotNull @RequestBody SubModules subModules
    ) throws URISyntaxException {
        log.debug("REST request to partial update SubModules partially : {}, {}", id, subModules);
        if (subModules.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, subModules.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!subModulesRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<SubModules> result = subModulesService.partialUpdate(subModules);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, subModules.getId().toString())
        );
    }

    /**
     * {@code GET  /sub-modules} : get all the subModules.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of subModules in body.
     */
    @GetMapping("/sub-modules")
    public ResponseEntity<List<SubModules>> getAllSubModules(Pageable pageable) {
        log.debug("REST request to get a page of SubModules");
        Page<SubModules> page = subModulesService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /sub-modules/:id} : get the "id" subModules.
     *
     * @param id the id of the subModules to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the subModules, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/sub-modules/{id}")
    public ResponseEntity<SubModules> getSubModules(@PathVariable Long id) {
        log.debug("REST request to get SubModules : {}", id);
        Optional<SubModules> subModules = subModulesService.findOne(id);
        return ResponseUtil.wrapOrNotFound(subModules);
    }

    /**
     * {@code DELETE  /sub-modules/:id} : delete the "id" subModules.
     *
     * @param id the id of the subModules to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/sub-modules/{id}")
    public ResponseEntity<Void> deleteSubModules(@PathVariable Long id) {
        log.debug("REST request to delete SubModules : {}", id);
        subModulesService.delete(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }
}
