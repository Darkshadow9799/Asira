package com.dshaw.asira.web.rest;

import com.dshaw.asira.domain.Modules;
import com.dshaw.asira.repository.ModulesRepository;
import com.dshaw.asira.service.ModulesService;
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
 * REST controller for managing {@link com.dshaw.asira.domain.Modules}.
 */
@RestController
@RequestMapping("/api")
public class ModulesResource {

    private final Logger log = LoggerFactory.getLogger(ModulesResource.class);

    private static final String ENTITY_NAME = "modules";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final ModulesService modulesService;

    private final ModulesRepository modulesRepository;

    public ModulesResource(ModulesService modulesService, ModulesRepository modulesRepository) {
        this.modulesService = modulesService;
        this.modulesRepository = modulesRepository;
    }

    /**
     * {@code POST  /modules} : Create a new modules.
     *
     * @param modules the modules to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new modules, or with status {@code 400 (Bad Request)} if the modules has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/modules")
    public ResponseEntity<Modules> createModules(@Valid @RequestBody Modules modules) throws URISyntaxException {
        log.debug("REST request to save Modules : {}", modules);
        if (modules.getId() != null) {
            throw new BadRequestAlertException("A new modules cannot already have an ID", ENTITY_NAME, "idexists");
        }
        modules.setmCreatedDate(Instant.now());
        modules.setmModifiedDate(Instant.now());
        Modules result = modulesService.save(modules);
        return ResponseEntity
            .created(new URI("/api/modules/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /modules/:id} : Updates an existing modules.
     *
     * @param id the id of the modules to save.
     * @param modules the modules to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated modules,
     * or with status {@code 400 (Bad Request)} if the modules is not valid,
     * or with status {@code 500 (Internal Server Error)} if the modules couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/modules/{id}")
    public ResponseEntity<Modules> updateModules(
        @PathVariable(value = "id", required = false) final Long id,
        @Valid @RequestBody Modules modules
    ) throws URISyntaxException {
        log.debug("REST request to update Modules : {}, {}", id, modules);
        if (modules.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, modules.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!modulesRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        modules.setmModifiedDate(Instant.now());
        Modules result = modulesService.save(modules);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, modules.getId().toString()))
            .body(result);
    }

    /**
     * {@code PATCH  /modules/:id} : Partial updates given fields of an existing modules, field will ignore if it is null
     *
     * @param id the id of the modules to save.
     * @param modules the modules to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated modules,
     * or with status {@code 400 (Bad Request)} if the modules is not valid,
     * or with status {@code 404 (Not Found)} if the modules is not found,
     * or with status {@code 500 (Internal Server Error)} if the modules couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/modules/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<Modules> partialUpdateModules(
        @PathVariable(value = "id", required = false) final Long id,
        @NotNull @RequestBody Modules modules
    ) throws URISyntaxException {
        log.debug("REST request to partial update Modules partially : {}, {}", id, modules);
        if (modules.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, modules.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!modulesRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<Modules> result = modulesService.partialUpdate(modules);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, modules.getId().toString())
        );
    }

    /**
     * {@code GET  /modules} : get all the modules.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of modules in body.
     */
    @GetMapping("/modules")
    public ResponseEntity<List<Modules>> getAllModules(Pageable pageable) {
        log.debug("REST request to get a page of Modules");
        Page<Modules> page = modulesService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /modules/:id} : get the "id" modules.
     *
     * @param id the id of the modules to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the modules, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/modules/{id}")
    public ResponseEntity<Modules> getModules(@PathVariable Long id) {
        log.debug("REST request to get Modules : {}", id);
        Optional<Modules> modules = modulesService.findOne(id);
        return ResponseUtil.wrapOrNotFound(modules);
    }

    /**
     * {@code DELETE  /modules/:id} : delete the "id" modules.
     *
     * @param id the id of the modules to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/modules/{id}")
    public ResponseEntity<Void> deleteModules(@PathVariable Long id) {
        log.debug("REST request to delete Modules : {}", id);
        modulesService.delete(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }
}
