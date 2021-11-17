package com.dshaw.asira.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.dshaw.asira.IntegrationTest;
import com.dshaw.asira.domain.Modules;
import com.dshaw.asira.repository.ModulesRepository;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Random;
import java.util.concurrent.atomic.AtomicLong;
import javax.persistence.EntityManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

/**
 * Integration tests for the {@link ModulesResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class ModulesResourceIT {

    private static final String DEFAULT_M_NAME = "AAAAAAAAAA";
    private static final String UPDATED_M_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_M_DESC = "AAAAAAAAAA";
    private static final String UPDATED_M_DESC = "BBBBBBBBBB";

    private static final Instant DEFAULT_M_CREATED_DATE = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_M_CREATED_DATE = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final Instant DEFAULT_M_MODIFIED_DATE = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_M_MODIFIED_DATE = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final Long DEFAULT_M_SM_NUM = 1L;
    private static final Long UPDATED_M_SM_NUM = 2L;

    private static final Instant DEFAULT_M_DUE_DATE = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_M_DUE_DATE = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final Instant DEFAULT_M_FINISHED_DATE = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_M_FINISHED_DATE = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final Boolean DEFAULT_M_COMPLETED = false;
    private static final Boolean UPDATED_M_COMPLETED = true;

    private static final String ENTITY_API_URL = "/api/modules";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private ModulesRepository modulesRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restModulesMockMvc;

    private Modules modules;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Modules createEntity(EntityManager em) {
        Modules modules = new Modules()
            .mName(DEFAULT_M_NAME)
            .mDesc(DEFAULT_M_DESC)
            .mCreatedDate(DEFAULT_M_CREATED_DATE)
            .mModifiedDate(DEFAULT_M_MODIFIED_DATE)
            .mSmNum(DEFAULT_M_SM_NUM)
            .mDueDate(DEFAULT_M_DUE_DATE)
            .mFinishedDate(DEFAULT_M_FINISHED_DATE)
            .mCompleted(DEFAULT_M_COMPLETED);
        return modules;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Modules createUpdatedEntity(EntityManager em) {
        Modules modules = new Modules()
            .mName(UPDATED_M_NAME)
            .mDesc(UPDATED_M_DESC)
            .mCreatedDate(UPDATED_M_CREATED_DATE)
            .mModifiedDate(UPDATED_M_MODIFIED_DATE)
            .mSmNum(UPDATED_M_SM_NUM)
            .mDueDate(UPDATED_M_DUE_DATE)
            .mFinishedDate(UPDATED_M_FINISHED_DATE)
            .mCompleted(UPDATED_M_COMPLETED);
        return modules;
    }

    @BeforeEach
    public void initTest() {
        modules = createEntity(em);
    }

    @Test
    @Transactional
    void createModules() throws Exception {
        int databaseSizeBeforeCreate = modulesRepository.findAll().size();
        // Create the Modules
        restModulesMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(modules)))
            .andExpect(status().isCreated());

        // Validate the Modules in the database
        List<Modules> modulesList = modulesRepository.findAll();
        assertThat(modulesList).hasSize(databaseSizeBeforeCreate + 1);
        Modules testModules = modulesList.get(modulesList.size() - 1);
        assertThat(testModules.getmName()).isEqualTo(DEFAULT_M_NAME);
        assertThat(testModules.getmDesc()).isEqualTo(DEFAULT_M_DESC);
        assertThat(testModules.getmCreatedDate()).isEqualTo(DEFAULT_M_CREATED_DATE);
        assertThat(testModules.getmModifiedDate()).isEqualTo(DEFAULT_M_MODIFIED_DATE);
        assertThat(testModules.getmSmNum()).isEqualTo(DEFAULT_M_SM_NUM);
        assertThat(testModules.getmDueDate()).isEqualTo(DEFAULT_M_DUE_DATE);
        assertThat(testModules.getmFinishedDate()).isEqualTo(DEFAULT_M_FINISHED_DATE);
        assertThat(testModules.getmCompleted()).isEqualTo(DEFAULT_M_COMPLETED);
    }

    @Test
    @Transactional
    void createModulesWithExistingId() throws Exception {
        // Create the Modules with an existing ID
        modules.setId(1L);

        int databaseSizeBeforeCreate = modulesRepository.findAll().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        restModulesMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(modules)))
            .andExpect(status().isBadRequest());

        // Validate the Modules in the database
        List<Modules> modulesList = modulesRepository.findAll();
        assertThat(modulesList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void getAllModules() throws Exception {
        // Initialize the database
        modulesRepository.saveAndFlush(modules);

        // Get all the modulesList
        restModulesMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(modules.getId().intValue())))
            .andExpect(jsonPath("$.[*].mName").value(hasItem(DEFAULT_M_NAME)))
            .andExpect(jsonPath("$.[*].mDesc").value(hasItem(DEFAULT_M_DESC)))
            .andExpect(jsonPath("$.[*].mCreatedDate").value(hasItem(DEFAULT_M_CREATED_DATE.toString())))
            .andExpect(jsonPath("$.[*].mModifiedDate").value(hasItem(DEFAULT_M_MODIFIED_DATE.toString())))
            .andExpect(jsonPath("$.[*].mSmNum").value(hasItem(DEFAULT_M_SM_NUM.intValue())))
            .andExpect(jsonPath("$.[*].mDueDate").value(hasItem(DEFAULT_M_DUE_DATE.toString())))
            .andExpect(jsonPath("$.[*].mFinishedDate").value(hasItem(DEFAULT_M_FINISHED_DATE.toString())))
            .andExpect(jsonPath("$.[*].mCompleted").value(hasItem(DEFAULT_M_COMPLETED.booleanValue())));
    }

    @Test
    @Transactional
    void getModules() throws Exception {
        // Initialize the database
        modulesRepository.saveAndFlush(modules);

        // Get the modules
        restModulesMockMvc
            .perform(get(ENTITY_API_URL_ID, modules.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(modules.getId().intValue()))
            .andExpect(jsonPath("$.mName").value(DEFAULT_M_NAME))
            .andExpect(jsonPath("$.mDesc").value(DEFAULT_M_DESC))
            .andExpect(jsonPath("$.mCreatedDate").value(DEFAULT_M_CREATED_DATE.toString()))
            .andExpect(jsonPath("$.mModifiedDate").value(DEFAULT_M_MODIFIED_DATE.toString()))
            .andExpect(jsonPath("$.mSmNum").value(DEFAULT_M_SM_NUM.intValue()))
            .andExpect(jsonPath("$.mDueDate").value(DEFAULT_M_DUE_DATE.toString()))
            .andExpect(jsonPath("$.mFinishedDate").value(DEFAULT_M_FINISHED_DATE.toString()))
            .andExpect(jsonPath("$.mCompleted").value(DEFAULT_M_COMPLETED.booleanValue()));
    }

    @Test
    @Transactional
    void getNonExistingModules() throws Exception {
        // Get the modules
        restModulesMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putNewModules() throws Exception {
        // Initialize the database
        modulesRepository.saveAndFlush(modules);

        int databaseSizeBeforeUpdate = modulesRepository.findAll().size();

        // Update the modules
        Modules updatedModules = modulesRepository.findById(modules.getId()).get();
        // Disconnect from session so that the updates on updatedModules are not directly saved in db
        em.detach(updatedModules);
        updatedModules
            .mName(UPDATED_M_NAME)
            .mDesc(UPDATED_M_DESC)
            .mCreatedDate(UPDATED_M_CREATED_DATE)
            .mModifiedDate(UPDATED_M_MODIFIED_DATE)
            .mSmNum(UPDATED_M_SM_NUM)
            .mDueDate(UPDATED_M_DUE_DATE)
            .mFinishedDate(UPDATED_M_FINISHED_DATE)
            .mCompleted(UPDATED_M_COMPLETED);

        restModulesMockMvc
            .perform(
                put(ENTITY_API_URL_ID, updatedModules.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(updatedModules))
            )
            .andExpect(status().isOk());

        // Validate the Modules in the database
        List<Modules> modulesList = modulesRepository.findAll();
        assertThat(modulesList).hasSize(databaseSizeBeforeUpdate);
        Modules testModules = modulesList.get(modulesList.size() - 1);
        assertThat(testModules.getmName()).isEqualTo(UPDATED_M_NAME);
        assertThat(testModules.getmDesc()).isEqualTo(UPDATED_M_DESC);
        assertThat(testModules.getmCreatedDate()).isEqualTo(UPDATED_M_CREATED_DATE);
        assertThat(testModules.getmModifiedDate()).isEqualTo(UPDATED_M_MODIFIED_DATE);
        assertThat(testModules.getmSmNum()).isEqualTo(UPDATED_M_SM_NUM);
        assertThat(testModules.getmDueDate()).isEqualTo(UPDATED_M_DUE_DATE);
        assertThat(testModules.getmFinishedDate()).isEqualTo(UPDATED_M_FINISHED_DATE);
        assertThat(testModules.getmCompleted()).isEqualTo(UPDATED_M_COMPLETED);
    }

    @Test
    @Transactional
    void putNonExistingModules() throws Exception {
        int databaseSizeBeforeUpdate = modulesRepository.findAll().size();
        modules.setId(count.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restModulesMockMvc
            .perform(
                put(ENTITY_API_URL_ID, modules.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(modules))
            )
            .andExpect(status().isBadRequest());

        // Validate the Modules in the database
        List<Modules> modulesList = modulesRepository.findAll();
        assertThat(modulesList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchModules() throws Exception {
        int databaseSizeBeforeUpdate = modulesRepository.findAll().size();
        modules.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restModulesMockMvc
            .perform(
                put(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(modules))
            )
            .andExpect(status().isBadRequest());

        // Validate the Modules in the database
        List<Modules> modulesList = modulesRepository.findAll();
        assertThat(modulesList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamModules() throws Exception {
        int databaseSizeBeforeUpdate = modulesRepository.findAll().size();
        modules.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restModulesMockMvc
            .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(modules)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the Modules in the database
        List<Modules> modulesList = modulesRepository.findAll();
        assertThat(modulesList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateModulesWithPatch() throws Exception {
        // Initialize the database
        modulesRepository.saveAndFlush(modules);

        int databaseSizeBeforeUpdate = modulesRepository.findAll().size();

        // Update the modules using partial update
        Modules partialUpdatedModules = new Modules();
        partialUpdatedModules.setId(modules.getId());

        partialUpdatedModules.mName(UPDATED_M_NAME).mSmNum(UPDATED_M_SM_NUM).mDueDate(UPDATED_M_DUE_DATE);

        restModulesMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedModules.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedModules))
            )
            .andExpect(status().isOk());

        // Validate the Modules in the database
        List<Modules> modulesList = modulesRepository.findAll();
        assertThat(modulesList).hasSize(databaseSizeBeforeUpdate);
        Modules testModules = modulesList.get(modulesList.size() - 1);
        assertThat(testModules.getmName()).isEqualTo(UPDATED_M_NAME);
        assertThat(testModules.getmDesc()).isEqualTo(DEFAULT_M_DESC);
        assertThat(testModules.getmCreatedDate()).isEqualTo(DEFAULT_M_CREATED_DATE);
        assertThat(testModules.getmModifiedDate()).isEqualTo(DEFAULT_M_MODIFIED_DATE);
        assertThat(testModules.getmSmNum()).isEqualTo(UPDATED_M_SM_NUM);
        assertThat(testModules.getmDueDate()).isEqualTo(UPDATED_M_DUE_DATE);
        assertThat(testModules.getmFinishedDate()).isEqualTo(DEFAULT_M_FINISHED_DATE);
        assertThat(testModules.getmCompleted()).isEqualTo(DEFAULT_M_COMPLETED);
    }

    @Test
    @Transactional
    void fullUpdateModulesWithPatch() throws Exception {
        // Initialize the database
        modulesRepository.saveAndFlush(modules);

        int databaseSizeBeforeUpdate = modulesRepository.findAll().size();

        // Update the modules using partial update
        Modules partialUpdatedModules = new Modules();
        partialUpdatedModules.setId(modules.getId());

        partialUpdatedModules
            .mName(UPDATED_M_NAME)
            .mDesc(UPDATED_M_DESC)
            .mCreatedDate(UPDATED_M_CREATED_DATE)
            .mModifiedDate(UPDATED_M_MODIFIED_DATE)
            .mSmNum(UPDATED_M_SM_NUM)
            .mDueDate(UPDATED_M_DUE_DATE)
            .mFinishedDate(UPDATED_M_FINISHED_DATE)
            .mCompleted(UPDATED_M_COMPLETED);

        restModulesMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedModules.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedModules))
            )
            .andExpect(status().isOk());

        // Validate the Modules in the database
        List<Modules> modulesList = modulesRepository.findAll();
        assertThat(modulesList).hasSize(databaseSizeBeforeUpdate);
        Modules testModules = modulesList.get(modulesList.size() - 1);
        assertThat(testModules.getmName()).isEqualTo(UPDATED_M_NAME);
        assertThat(testModules.getmDesc()).isEqualTo(UPDATED_M_DESC);
        assertThat(testModules.getmCreatedDate()).isEqualTo(UPDATED_M_CREATED_DATE);
        assertThat(testModules.getmModifiedDate()).isEqualTo(UPDATED_M_MODIFIED_DATE);
        assertThat(testModules.getmSmNum()).isEqualTo(UPDATED_M_SM_NUM);
        assertThat(testModules.getmDueDate()).isEqualTo(UPDATED_M_DUE_DATE);
        assertThat(testModules.getmFinishedDate()).isEqualTo(UPDATED_M_FINISHED_DATE);
        assertThat(testModules.getmCompleted()).isEqualTo(UPDATED_M_COMPLETED);
    }

    @Test
    @Transactional
    void patchNonExistingModules() throws Exception {
        int databaseSizeBeforeUpdate = modulesRepository.findAll().size();
        modules.setId(count.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restModulesMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, modules.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(modules))
            )
            .andExpect(status().isBadRequest());

        // Validate the Modules in the database
        List<Modules> modulesList = modulesRepository.findAll();
        assertThat(modulesList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchModules() throws Exception {
        int databaseSizeBeforeUpdate = modulesRepository.findAll().size();
        modules.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restModulesMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(modules))
            )
            .andExpect(status().isBadRequest());

        // Validate the Modules in the database
        List<Modules> modulesList = modulesRepository.findAll();
        assertThat(modulesList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamModules() throws Exception {
        int databaseSizeBeforeUpdate = modulesRepository.findAll().size();
        modules.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restModulesMockMvc
            .perform(patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(TestUtil.convertObjectToJsonBytes(modules)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the Modules in the database
        List<Modules> modulesList = modulesRepository.findAll();
        assertThat(modulesList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteModules() throws Exception {
        // Initialize the database
        modulesRepository.saveAndFlush(modules);

        int databaseSizeBeforeDelete = modulesRepository.findAll().size();

        // Delete the modules
        restModulesMockMvc
            .perform(delete(ENTITY_API_URL_ID, modules.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Modules> modulesList = modulesRepository.findAll();
        assertThat(modulesList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
