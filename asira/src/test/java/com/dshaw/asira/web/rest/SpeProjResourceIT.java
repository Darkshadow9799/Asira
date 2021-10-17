package com.dshaw.asira.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.dshaw.asira.IntegrationTest;
import com.dshaw.asira.domain.SpeProj;
import com.dshaw.asira.repository.SpeProjRepository;
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
 * Integration tests for the {@link SpeProjResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class SpeProjResourceIT {

    private static final Long DEFAULT_PROJ_ID = 1L;
    private static final Long UPDATED_PROJ_ID = 2L;

    private static final Long DEFAULT_SPE_ID = 1L;
    private static final Long UPDATED_SPE_ID = 2L;

    private static final Long DEFAULT_ORG_ID = 1L;
    private static final Long UPDATED_ORG_ID = 2L;

    private static final String ENTITY_API_URL = "/api/spe-projs";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private SpeProjRepository speProjRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restSpeProjMockMvc;

    private SpeProj speProj;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
//    public static SpeProj createEntity(EntityManager em) {
//        SpeProj speProj = new SpeProj().projId(DEFAULT_PROJ_ID).speId(DEFAULT_SPE_ID).orgId(DEFAULT_ORG_ID);
//        return speProj;
//    }
//
//    /**
//     * Create an updated entity for this test.
//     *
//     * This is a static method, as tests for other entities might also need it,
//     * if they test an entity which requires the current entity.
//     */
//    public static SpeProj createUpdatedEntity(EntityManager em) {
//        SpeProj speProj = new SpeProj().projId(UPDATED_PROJ_ID).speId(UPDATED_SPE_ID).orgId(UPDATED_ORG_ID);
//        return speProj;
//    }
//
//    @BeforeEach
//    public void initTest() {
//        speProj = createEntity(em);
//    }
//
//    @Test
//    @Transactional
//    void createSpeProj() throws Exception {
//        int databaseSizeBeforeCreate = speProjRepository.findAll().size();
//        // Create the SpeProj
//        restSpeProjMockMvc
//            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(speProj)))
//            .andExpect(status().isCreated());
//
//        // Validate the SpeProj in the database
//        List<SpeProj> speProjList = speProjRepository.findAll();
//        assertThat(speProjList).hasSize(databaseSizeBeforeCreate + 1);
//        SpeProj testSpeProj = speProjList.get(speProjList.size() - 1);
//        assertThat(testSpeProj.getProjId()).isEqualTo(DEFAULT_PROJ_ID);
//        assertThat(testSpeProj.getSpeId()).isEqualTo(DEFAULT_SPE_ID);
//        assertThat(testSpeProj.getOrgId()).isEqualTo(DEFAULT_ORG_ID);
//    }
//
//    @Test
//    @Transactional
//    void createSpeProjWithExistingId() throws Exception {
//        // Create the SpeProj with an existing ID
//        speProj.setId(1L);
//
//        int databaseSizeBeforeCreate = speProjRepository.findAll().size();
//
//        // An entity with an existing ID cannot be created, so this API call must fail
//        restSpeProjMockMvc
//            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(speProj)))
//            .andExpect(status().isBadRequest());
//
//        // Validate the SpeProj in the database
//        List<SpeProj> speProjList = speProjRepository.findAll();
//        assertThat(speProjList).hasSize(databaseSizeBeforeCreate);
//    }
//
//    @Test
//    @Transactional
//    void checkProjIdIsRequired() throws Exception {
//        int databaseSizeBeforeTest = speProjRepository.findAll().size();
//        // set the field null
//        speProj.setProjId(null);
//
//        // Create the SpeProj, which fails.
//
//        restSpeProjMockMvc
//            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(speProj)))
//            .andExpect(status().isBadRequest());
//
//        List<SpeProj> speProjList = speProjRepository.findAll();
//        assertThat(speProjList).hasSize(databaseSizeBeforeTest);
//    }
//
//    @Test
//    @Transactional
//    void getAllSpeProjs() throws Exception {
//        // Initialize the database
//        speProjRepository.saveAndFlush(speProj);
//
//        // Get all the speProjList
//        restSpeProjMockMvc
//            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
//            .andExpect(status().isOk())
//            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
//            .andExpect(jsonPath("$.[*].id").value(hasItem(speProj.getId().intValue())))
//            .andExpect(jsonPath("$.[*].projId").value(hasItem(DEFAULT_PROJ_ID.intValue())))
//            .andExpect(jsonPath("$.[*].speId").value(hasItem(DEFAULT_SPE_ID.intValue())))
//            .andExpect(jsonPath("$.[*].orgId").value(hasItem(DEFAULT_ORG_ID.intValue())));
//    }
//
//    @Test
//    @Transactional
//    void getSpeProj() throws Exception {
//        // Initialize the database
//        speProjRepository.saveAndFlush(speProj);
//
//        // Get the speProj
//        restSpeProjMockMvc
//            .perform(get(ENTITY_API_URL_ID, speProj.getId()))
//            .andExpect(status().isOk())
//            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
//            .andExpect(jsonPath("$.id").value(speProj.getId().intValue()))
//            .andExpect(jsonPath("$.projId").value(DEFAULT_PROJ_ID.intValue()))
//            .andExpect(jsonPath("$.speId").value(DEFAULT_SPE_ID.intValue()))
//            .andExpect(jsonPath("$.orgId").value(DEFAULT_ORG_ID.intValue()));
//    }
//
//    @Test
//    @Transactional
//    void getNonExistingSpeProj() throws Exception {
//        // Get the speProj
//        restSpeProjMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
//    }
//
//    @Test
//    @Transactional
//    void putNewSpeProj() throws Exception {
//        // Initialize the database
//        speProjRepository.saveAndFlush(speProj);
//
//        int databaseSizeBeforeUpdate = speProjRepository.findAll().size();
//
//        // Update the speProj
//        SpeProj updatedSpeProj = speProjRepository.findById(speProj.getId()).get();
//        // Disconnect from session so that the updates on updatedSpeProj are not directly saved in db
//        em.detach(updatedSpeProj);
//        updatedSpeProj.projId(UPDATED_PROJ_ID).speId(UPDATED_SPE_ID).orgId(UPDATED_ORG_ID);
//
//        restSpeProjMockMvc
//            .perform(
//                put(ENTITY_API_URL_ID, updatedSpeProj.getId())
//                    .contentType(MediaType.APPLICATION_JSON)
//                    .content(TestUtil.convertObjectToJsonBytes(updatedSpeProj))
//            )
//            .andExpect(status().isOk());
//
//        // Validate the SpeProj in the database
//        List<SpeProj> speProjList = speProjRepository.findAll();
//        assertThat(speProjList).hasSize(databaseSizeBeforeUpdate);
//        SpeProj testSpeProj = speProjList.get(speProjList.size() - 1);
//        assertThat(testSpeProj.getProjId()).isEqualTo(UPDATED_PROJ_ID);
//        assertThat(testSpeProj.getSpeId()).isEqualTo(UPDATED_SPE_ID);
//        assertThat(testSpeProj.getOrgId()).isEqualTo(UPDATED_ORG_ID);
//    }
//
//    @Test
//    @Transactional
//    void putNonExistingSpeProj() throws Exception {
//        int databaseSizeBeforeUpdate = speProjRepository.findAll().size();
//        speProj.setId(count.incrementAndGet());
//
//        // If the entity doesn't have an ID, it will throw BadRequestAlertException
//        restSpeProjMockMvc
//            .perform(
//                put(ENTITY_API_URL_ID, speProj.getId())
//                    .contentType(MediaType.APPLICATION_JSON)
//                    .content(TestUtil.convertObjectToJsonBytes(speProj))
//            )
//            .andExpect(status().isBadRequest());
//
//        // Validate the SpeProj in the database
//        List<SpeProj> speProjList = speProjRepository.findAll();
//        assertThat(speProjList).hasSize(databaseSizeBeforeUpdate);
//    }
//
//    @Test
//    @Transactional
//    void putWithIdMismatchSpeProj() throws Exception {
//        int databaseSizeBeforeUpdate = speProjRepository.findAll().size();
//        speProj.setId(count.incrementAndGet());
//
//        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
//        restSpeProjMockMvc
//            .perform(
//                put(ENTITY_API_URL_ID, count.incrementAndGet())
//                    .contentType(MediaType.APPLICATION_JSON)
//                    .content(TestUtil.convertObjectToJsonBytes(speProj))
//            )
//            .andExpect(status().isBadRequest());
//
//        // Validate the SpeProj in the database
//        List<SpeProj> speProjList = speProjRepository.findAll();
//        assertThat(speProjList).hasSize(databaseSizeBeforeUpdate);
//    }
//
//    @Test
//    @Transactional
//    void putWithMissingIdPathParamSpeProj() throws Exception {
//        int databaseSizeBeforeUpdate = speProjRepository.findAll().size();
//        speProj.setId(count.incrementAndGet());
//
//        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
//        restSpeProjMockMvc
//            .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(speProj)))
//            .andExpect(status().isMethodNotAllowed());
//
//        // Validate the SpeProj in the database
//        List<SpeProj> speProjList = speProjRepository.findAll();
//        assertThat(speProjList).hasSize(databaseSizeBeforeUpdate);
//    }
//
//    @Test
//    @Transactional
//    void partialUpdateSpeProjWithPatch() throws Exception {
//        // Initialize the database
//        speProjRepository.saveAndFlush(speProj);
//
//        int databaseSizeBeforeUpdate = speProjRepository.findAll().size();
//
//        // Update the speProj using partial update
//        SpeProj partialUpdatedSpeProj = new SpeProj();
//        partialUpdatedSpeProj.setId(speProj.getId());
//
//        partialUpdatedSpeProj.orgId(UPDATED_ORG_ID);
//
//        restSpeProjMockMvc
//            .perform(
//                patch(ENTITY_API_URL_ID, partialUpdatedSpeProj.getId())
//                    .contentType("application/merge-patch+json")
//                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedSpeProj))
//            )
//            .andExpect(status().isOk());
//
//        // Validate the SpeProj in the database
//        List<SpeProj> speProjList = speProjRepository.findAll();
//        assertThat(speProjList).hasSize(databaseSizeBeforeUpdate);
//        SpeProj testSpeProj = speProjList.get(speProjList.size() - 1);
//        assertThat(testSpeProj.getProjId()).isEqualTo(DEFAULT_PROJ_ID);
//        assertThat(testSpeProj.getSpeId()).isEqualTo(DEFAULT_SPE_ID);
//        assertThat(testSpeProj.getOrgId()).isEqualTo(UPDATED_ORG_ID);
//    }
//
//    @Test
//    @Transactional
//    void fullUpdateSpeProjWithPatch() throws Exception {
//        // Initialize the database
//        speProjRepository.saveAndFlush(speProj);
//
//        int databaseSizeBeforeUpdate = speProjRepository.findAll().size();
//
//        // Update the speProj using partial update
//        SpeProj partialUpdatedSpeProj = new SpeProj();
//        partialUpdatedSpeProj.setId(speProj.getId());
//
//        partialUpdatedSpeProj.projId(UPDATED_PROJ_ID).speId(UPDATED_SPE_ID).orgId(UPDATED_ORG_ID);
//
//        restSpeProjMockMvc
//            .perform(
//                patch(ENTITY_API_URL_ID, partialUpdatedSpeProj.getId())
//                    .contentType("application/merge-patch+json")
//                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedSpeProj))
//            )
//            .andExpect(status().isOk());
//
//        // Validate the SpeProj in the database
//        List<SpeProj> speProjList = speProjRepository.findAll();
//        assertThat(speProjList).hasSize(databaseSizeBeforeUpdate);
//        SpeProj testSpeProj = speProjList.get(speProjList.size() - 1);
//        assertThat(testSpeProj.getProjId()).isEqualTo(UPDATED_PROJ_ID);
//        assertThat(testSpeProj.getSpeId()).isEqualTo(UPDATED_SPE_ID);
//        assertThat(testSpeProj.getOrgId()).isEqualTo(UPDATED_ORG_ID);
//    }
//
//    @Test
//    @Transactional
//    void patchNonExistingSpeProj() throws Exception {
//        int databaseSizeBeforeUpdate = speProjRepository.findAll().size();
//        speProj.setId(count.incrementAndGet());
//
//        // If the entity doesn't have an ID, it will throw BadRequestAlertException
//        restSpeProjMockMvc
//            .perform(
//                patch(ENTITY_API_URL_ID, speProj.getId())
//                    .contentType("application/merge-patch+json")
//                    .content(TestUtil.convertObjectToJsonBytes(speProj))
//            )
//            .andExpect(status().isBadRequest());
//
//        // Validate the SpeProj in the database
//        List<SpeProj> speProjList = speProjRepository.findAll();
//        assertThat(speProjList).hasSize(databaseSizeBeforeUpdate);
//    }
//
//    @Test
//    @Transactional
//    void patchWithIdMismatchSpeProj() throws Exception {
//        int databaseSizeBeforeUpdate = speProjRepository.findAll().size();
//        speProj.setId(count.incrementAndGet());
//
//        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
//        restSpeProjMockMvc
//            .perform(
//                patch(ENTITY_API_URL_ID, count.incrementAndGet())
//                    .contentType("application/merge-patch+json")
//                    .content(TestUtil.convertObjectToJsonBytes(speProj))
//            )
//            .andExpect(status().isBadRequest());
//
//        // Validate the SpeProj in the database
//        List<SpeProj> speProjList = speProjRepository.findAll();
//        assertThat(speProjList).hasSize(databaseSizeBeforeUpdate);
//    }
//
//    @Test
//    @Transactional
//    void patchWithMissingIdPathParamSpeProj() throws Exception {
//        int databaseSizeBeforeUpdate = speProjRepository.findAll().size();
//        speProj.setId(count.incrementAndGet());
//
//        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
//        restSpeProjMockMvc
//            .perform(patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(TestUtil.convertObjectToJsonBytes(speProj)))
//            .andExpect(status().isMethodNotAllowed());
//
//        // Validate the SpeProj in the database
//        List<SpeProj> speProjList = speProjRepository.findAll();
//        assertThat(speProjList).hasSize(databaseSizeBeforeUpdate);
//    }
//
//    @Test
//    @Transactional
//    void deleteSpeProj() throws Exception {
//        // Initialize the database
//        speProjRepository.saveAndFlush(speProj);
//
//        int databaseSizeBeforeDelete = speProjRepository.findAll().size();
//
//        // Delete the speProj
//        restSpeProjMockMvc
//            .perform(delete(ENTITY_API_URL_ID, speProj.getId()).accept(MediaType.APPLICATION_JSON))
//            .andExpect(status().isNoContent());
//
//        // Validate the database contains one less item
//        List<SpeProj> speProjList = speProjRepository.findAll();
//        assertThat(speProjList).hasSize(databaseSizeBeforeDelete - 1);
//    }
}
