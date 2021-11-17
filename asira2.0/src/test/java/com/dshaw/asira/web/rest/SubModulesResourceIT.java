package com.dshaw.asira.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.dshaw.asira.IntegrationTest;
import com.dshaw.asira.domain.SubModules;
import com.dshaw.asira.repository.SubModulesRepository;
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
 * Integration tests for the {@link SubModulesResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class SubModulesResourceIT {

    private static final String DEFAULT_SM_NAME = "AAAAAAAAAA";
    private static final String UPDATED_SM_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_SM_DESC = "AAAAAAAAAA";
    private static final String UPDATED_SM_DESC = "BBBBBBBBBB";

    private static final Instant DEFAULT_SM_CREATED_DATE = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_SM_CREATED_DATE = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final Instant DEFAULT_SM_MODIFIED_DATE = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_SM_MODIFIED_DATE = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final Instant DEFAULT_SM_DUE_DATE = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_SM_DUE_DATE = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final Instant DEFAULT_SM_FINISHED_DATE = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_SM_FINISHED_DATE = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final Boolean DEFAULT_SM_COMPLETED = false;
    private static final Boolean UPDATED_SM_COMPLETED = true;

    private static final String DEFAULT_SPE_NAME_SM_TAG_ID = "AAAAAAAAAA";
    private static final String UPDATED_SPE_NAME_SM_TAG_ID = "BBBBBBBBBB";

    private static final Long DEFAULT_SM_LOGGED_TIME = 1L;
    private static final Long UPDATED_SM_LOGGED_TIME = 2L;

    private static final String ENTITY_API_URL = "/api/sub-modules";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private SubModulesRepository subModulesRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restSubModulesMockMvc;

    private SubModules subModules;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static SubModules createEntity(EntityManager em) {
        SubModules subModules = new SubModules()
            .smName(DEFAULT_SM_NAME)
            .smDesc(DEFAULT_SM_DESC)
            .smCreatedDate(DEFAULT_SM_CREATED_DATE)
            .smModifiedDate(DEFAULT_SM_MODIFIED_DATE)
            .smDueDate(DEFAULT_SM_DUE_DATE)
            .smFinishedDate(DEFAULT_SM_FINISHED_DATE)
            .smCompleted(DEFAULT_SM_COMPLETED)
            .speNameSmTagId(DEFAULT_SPE_NAME_SM_TAG_ID)
            .smLoggedTime(DEFAULT_SM_LOGGED_TIME);
        return subModules;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static SubModules createUpdatedEntity(EntityManager em) {
        SubModules subModules = new SubModules()
            .smName(UPDATED_SM_NAME)
            .smDesc(UPDATED_SM_DESC)
            .smCreatedDate(UPDATED_SM_CREATED_DATE)
            .smModifiedDate(UPDATED_SM_MODIFIED_DATE)
            .smDueDate(UPDATED_SM_DUE_DATE)
            .smFinishedDate(UPDATED_SM_FINISHED_DATE)
            .smCompleted(UPDATED_SM_COMPLETED)
            .speNameSmTagId(UPDATED_SPE_NAME_SM_TAG_ID)
            .smLoggedTime(UPDATED_SM_LOGGED_TIME);
        return subModules;
    }

    @BeforeEach
    public void initTest() {
        subModules = createEntity(em);
    }

    @Test
    @Transactional
    void createSubModules() throws Exception {
        int databaseSizeBeforeCreate = subModulesRepository.findAll().size();
        // Create the SubModules
        restSubModulesMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(subModules)))
            .andExpect(status().isCreated());

        // Validate the SubModules in the database
        List<SubModules> subModulesList = subModulesRepository.findAll();
        assertThat(subModulesList).hasSize(databaseSizeBeforeCreate + 1);
        SubModules testSubModules = subModulesList.get(subModulesList.size() - 1);
        assertThat(testSubModules.getSmName()).isEqualTo(DEFAULT_SM_NAME);
        assertThat(testSubModules.getSmDesc()).isEqualTo(DEFAULT_SM_DESC);
        assertThat(testSubModules.getSmCreatedDate()).isEqualTo(DEFAULT_SM_CREATED_DATE);
        assertThat(testSubModules.getSmModifiedDate()).isEqualTo(DEFAULT_SM_MODIFIED_DATE);
        assertThat(testSubModules.getSmDueDate()).isEqualTo(DEFAULT_SM_DUE_DATE);
        assertThat(testSubModules.getSmFinishedDate()).isEqualTo(DEFAULT_SM_FINISHED_DATE);
        assertThat(testSubModules.getSmCompleted()).isEqualTo(DEFAULT_SM_COMPLETED);
        assertThat(testSubModules.getSpeNameSmTagId()).isEqualTo(DEFAULT_SPE_NAME_SM_TAG_ID);
        assertThat(testSubModules.getSmLoggedTime()).isEqualTo(DEFAULT_SM_LOGGED_TIME);
    }

    @Test
    @Transactional
    void createSubModulesWithExistingId() throws Exception {
        // Create the SubModules with an existing ID
        subModules.setId(1L);

        int databaseSizeBeforeCreate = subModulesRepository.findAll().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        restSubModulesMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(subModules)))
            .andExpect(status().isBadRequest());

        // Validate the SubModules in the database
        List<SubModules> subModulesList = subModulesRepository.findAll();
        assertThat(subModulesList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void getAllSubModules() throws Exception {
        // Initialize the database
        subModulesRepository.saveAndFlush(subModules);

        // Get all the subModulesList
        restSubModulesMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(subModules.getId().intValue())))
            .andExpect(jsonPath("$.[*].smName").value(hasItem(DEFAULT_SM_NAME)))
            .andExpect(jsonPath("$.[*].smDesc").value(hasItem(DEFAULT_SM_DESC)))
            .andExpect(jsonPath("$.[*].smCreatedDate").value(hasItem(DEFAULT_SM_CREATED_DATE.toString())))
            .andExpect(jsonPath("$.[*].smModifiedDate").value(hasItem(DEFAULT_SM_MODIFIED_DATE.toString())))
            .andExpect(jsonPath("$.[*].smDueDate").value(hasItem(DEFAULT_SM_DUE_DATE.toString())))
            .andExpect(jsonPath("$.[*].smFinishedDate").value(hasItem(DEFAULT_SM_FINISHED_DATE.toString())))
            .andExpect(jsonPath("$.[*].smCompleted").value(hasItem(DEFAULT_SM_COMPLETED.booleanValue())))
            .andExpect(jsonPath("$.[*].speNameSmTagId").value(hasItem(DEFAULT_SPE_NAME_SM_TAG_ID)))
            .andExpect(jsonPath("$.[*].smLoggedTime").value(hasItem(DEFAULT_SM_LOGGED_TIME.intValue())));
    }

    @Test
    @Transactional
    void getSubModules() throws Exception {
        // Initialize the database
        subModulesRepository.saveAndFlush(subModules);

        // Get the subModules
        restSubModulesMockMvc
            .perform(get(ENTITY_API_URL_ID, subModules.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(subModules.getId().intValue()))
            .andExpect(jsonPath("$.smName").value(DEFAULT_SM_NAME))
            .andExpect(jsonPath("$.smDesc").value(DEFAULT_SM_DESC))
            .andExpect(jsonPath("$.smCreatedDate").value(DEFAULT_SM_CREATED_DATE.toString()))
            .andExpect(jsonPath("$.smModifiedDate").value(DEFAULT_SM_MODIFIED_DATE.toString()))
            .andExpect(jsonPath("$.smDueDate").value(DEFAULT_SM_DUE_DATE.toString()))
            .andExpect(jsonPath("$.smFinishedDate").value(DEFAULT_SM_FINISHED_DATE.toString()))
            .andExpect(jsonPath("$.smCompleted").value(DEFAULT_SM_COMPLETED.booleanValue()))
            .andExpect(jsonPath("$.speNameSmTagId").value(DEFAULT_SPE_NAME_SM_TAG_ID))
            .andExpect(jsonPath("$.smLoggedTime").value(DEFAULT_SM_LOGGED_TIME.intValue()));
    }

    @Test
    @Transactional
    void getNonExistingSubModules() throws Exception {
        // Get the subModules
        restSubModulesMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putNewSubModules() throws Exception {
        // Initialize the database
        subModulesRepository.saveAndFlush(subModules);

        int databaseSizeBeforeUpdate = subModulesRepository.findAll().size();

        // Update the subModules
        SubModules updatedSubModules = subModulesRepository.findById(subModules.getId()).get();
        // Disconnect from session so that the updates on updatedSubModules are not directly saved in db
        em.detach(updatedSubModules);
        updatedSubModules
            .smName(UPDATED_SM_NAME)
            .smDesc(UPDATED_SM_DESC)
            .smCreatedDate(UPDATED_SM_CREATED_DATE)
            .smModifiedDate(UPDATED_SM_MODIFIED_DATE)
            .smDueDate(UPDATED_SM_DUE_DATE)
            .smFinishedDate(UPDATED_SM_FINISHED_DATE)
            .smCompleted(UPDATED_SM_COMPLETED)
            .speNameSmTagId(UPDATED_SPE_NAME_SM_TAG_ID)
            .smLoggedTime(UPDATED_SM_LOGGED_TIME);

        restSubModulesMockMvc
            .perform(
                put(ENTITY_API_URL_ID, updatedSubModules.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(updatedSubModules))
            )
            .andExpect(status().isOk());

        // Validate the SubModules in the database
        List<SubModules> subModulesList = subModulesRepository.findAll();
        assertThat(subModulesList).hasSize(databaseSizeBeforeUpdate);
        SubModules testSubModules = subModulesList.get(subModulesList.size() - 1);
        assertThat(testSubModules.getSmName()).isEqualTo(UPDATED_SM_NAME);
        assertThat(testSubModules.getSmDesc()).isEqualTo(UPDATED_SM_DESC);
        assertThat(testSubModules.getSmCreatedDate()).isEqualTo(UPDATED_SM_CREATED_DATE);
        assertThat(testSubModules.getSmModifiedDate()).isEqualTo(UPDATED_SM_MODIFIED_DATE);
        assertThat(testSubModules.getSmDueDate()).isEqualTo(UPDATED_SM_DUE_DATE);
        assertThat(testSubModules.getSmFinishedDate()).isEqualTo(UPDATED_SM_FINISHED_DATE);
        assertThat(testSubModules.getSmCompleted()).isEqualTo(UPDATED_SM_COMPLETED);
        assertThat(testSubModules.getSpeNameSmTagId()).isEqualTo(UPDATED_SPE_NAME_SM_TAG_ID);
        assertThat(testSubModules.getSmLoggedTime()).isEqualTo(UPDATED_SM_LOGGED_TIME);
    }

    @Test
    @Transactional
    void putNonExistingSubModules() throws Exception {
        int databaseSizeBeforeUpdate = subModulesRepository.findAll().size();
        subModules.setId(count.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restSubModulesMockMvc
            .perform(
                put(ENTITY_API_URL_ID, subModules.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(subModules))
            )
            .andExpect(status().isBadRequest());

        // Validate the SubModules in the database
        List<SubModules> subModulesList = subModulesRepository.findAll();
        assertThat(subModulesList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchSubModules() throws Exception {
        int databaseSizeBeforeUpdate = subModulesRepository.findAll().size();
        subModules.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restSubModulesMockMvc
            .perform(
                put(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(subModules))
            )
            .andExpect(status().isBadRequest());

        // Validate the SubModules in the database
        List<SubModules> subModulesList = subModulesRepository.findAll();
        assertThat(subModulesList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamSubModules() throws Exception {
        int databaseSizeBeforeUpdate = subModulesRepository.findAll().size();
        subModules.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restSubModulesMockMvc
            .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(subModules)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the SubModules in the database
        List<SubModules> subModulesList = subModulesRepository.findAll();
        assertThat(subModulesList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateSubModulesWithPatch() throws Exception {
        // Initialize the database
        subModulesRepository.saveAndFlush(subModules);

        int databaseSizeBeforeUpdate = subModulesRepository.findAll().size();

        // Update the subModules using partial update
        SubModules partialUpdatedSubModules = new SubModules();
        partialUpdatedSubModules.setId(subModules.getId());

        partialUpdatedSubModules
            .smCreatedDate(UPDATED_SM_CREATED_DATE)
            .smDueDate(UPDATED_SM_DUE_DATE)
            .smFinishedDate(UPDATED_SM_FINISHED_DATE)
            .smCompleted(UPDATED_SM_COMPLETED);

        restSubModulesMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedSubModules.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedSubModules))
            )
            .andExpect(status().isOk());

        // Validate the SubModules in the database
        List<SubModules> subModulesList = subModulesRepository.findAll();
        assertThat(subModulesList).hasSize(databaseSizeBeforeUpdate);
        SubModules testSubModules = subModulesList.get(subModulesList.size() - 1);
        assertThat(testSubModules.getSmName()).isEqualTo(DEFAULT_SM_NAME);
        assertThat(testSubModules.getSmDesc()).isEqualTo(DEFAULT_SM_DESC);
        assertThat(testSubModules.getSmCreatedDate()).isEqualTo(UPDATED_SM_CREATED_DATE);
        assertThat(testSubModules.getSmModifiedDate()).isEqualTo(DEFAULT_SM_MODIFIED_DATE);
        assertThat(testSubModules.getSmDueDate()).isEqualTo(UPDATED_SM_DUE_DATE);
        assertThat(testSubModules.getSmFinishedDate()).isEqualTo(UPDATED_SM_FINISHED_DATE);
        assertThat(testSubModules.getSmCompleted()).isEqualTo(UPDATED_SM_COMPLETED);
        assertThat(testSubModules.getSpeNameSmTagId()).isEqualTo(DEFAULT_SPE_NAME_SM_TAG_ID);
        assertThat(testSubModules.getSmLoggedTime()).isEqualTo(DEFAULT_SM_LOGGED_TIME);
    }

    @Test
    @Transactional
    void fullUpdateSubModulesWithPatch() throws Exception {
        // Initialize the database
        subModulesRepository.saveAndFlush(subModules);

        int databaseSizeBeforeUpdate = subModulesRepository.findAll().size();

        // Update the subModules using partial update
        SubModules partialUpdatedSubModules = new SubModules();
        partialUpdatedSubModules.setId(subModules.getId());

        partialUpdatedSubModules
            .smName(UPDATED_SM_NAME)
            .smDesc(UPDATED_SM_DESC)
            .smCreatedDate(UPDATED_SM_CREATED_DATE)
            .smModifiedDate(UPDATED_SM_MODIFIED_DATE)
            .smDueDate(UPDATED_SM_DUE_DATE)
            .smFinishedDate(UPDATED_SM_FINISHED_DATE)
            .smCompleted(UPDATED_SM_COMPLETED)
            .speNameSmTagId(UPDATED_SPE_NAME_SM_TAG_ID)
            .smLoggedTime(UPDATED_SM_LOGGED_TIME);

        restSubModulesMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedSubModules.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedSubModules))
            )
            .andExpect(status().isOk());

        // Validate the SubModules in the database
        List<SubModules> subModulesList = subModulesRepository.findAll();
        assertThat(subModulesList).hasSize(databaseSizeBeforeUpdate);
        SubModules testSubModules = subModulesList.get(subModulesList.size() - 1);
        assertThat(testSubModules.getSmName()).isEqualTo(UPDATED_SM_NAME);
        assertThat(testSubModules.getSmDesc()).isEqualTo(UPDATED_SM_DESC);
        assertThat(testSubModules.getSmCreatedDate()).isEqualTo(UPDATED_SM_CREATED_DATE);
        assertThat(testSubModules.getSmModifiedDate()).isEqualTo(UPDATED_SM_MODIFIED_DATE);
        assertThat(testSubModules.getSmDueDate()).isEqualTo(UPDATED_SM_DUE_DATE);
        assertThat(testSubModules.getSmFinishedDate()).isEqualTo(UPDATED_SM_FINISHED_DATE);
        assertThat(testSubModules.getSmCompleted()).isEqualTo(UPDATED_SM_COMPLETED);
        assertThat(testSubModules.getSpeNameSmTagId()).isEqualTo(UPDATED_SPE_NAME_SM_TAG_ID);
        assertThat(testSubModules.getSmLoggedTime()).isEqualTo(UPDATED_SM_LOGGED_TIME);
    }

    @Test
    @Transactional
    void patchNonExistingSubModules() throws Exception {
        int databaseSizeBeforeUpdate = subModulesRepository.findAll().size();
        subModules.setId(count.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restSubModulesMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, subModules.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(subModules))
            )
            .andExpect(status().isBadRequest());

        // Validate the SubModules in the database
        List<SubModules> subModulesList = subModulesRepository.findAll();
        assertThat(subModulesList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchSubModules() throws Exception {
        int databaseSizeBeforeUpdate = subModulesRepository.findAll().size();
        subModules.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restSubModulesMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(subModules))
            )
            .andExpect(status().isBadRequest());

        // Validate the SubModules in the database
        List<SubModules> subModulesList = subModulesRepository.findAll();
        assertThat(subModulesList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamSubModules() throws Exception {
        int databaseSizeBeforeUpdate = subModulesRepository.findAll().size();
        subModules.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restSubModulesMockMvc
            .perform(
                patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(TestUtil.convertObjectToJsonBytes(subModules))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the SubModules in the database
        List<SubModules> subModulesList = subModulesRepository.findAll();
        assertThat(subModulesList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteSubModules() throws Exception {
        // Initialize the database
        subModulesRepository.saveAndFlush(subModules);

        int databaseSizeBeforeDelete = subModulesRepository.findAll().size();

        // Delete the subModules
        restSubModulesMockMvc
            .perform(delete(ENTITY_API_URL_ID, subModules.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<SubModules> subModulesList = subModulesRepository.findAll();
        assertThat(subModulesList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
