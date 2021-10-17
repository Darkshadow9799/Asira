package com.dshaw.asira.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.dshaw.asira.IntegrationTest;
import com.dshaw.asira.domain.SpeProjMSm;
import com.dshaw.asira.repository.SpeProjMSmRepository;
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
 * Integration tests for the {@link SpeProjMSmResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class SpeProjMSmResourceIT {

    private static final Long DEFAULT_SPE_ID = 1L;
    private static final Long UPDATED_SPE_ID = 2L;

    private static final Long DEFAULT_PROJ_ID = 1L;
    private static final Long UPDATED_PROJ_ID = 2L;

    private static final Long DEFAULT_M_ID = 1L;
    private static final Long UPDATED_M_ID = 2L;

    private static final Long DEFAULT_SM_ID = 1L;
    private static final Long UPDATED_SM_ID = 2L;

    private static final String ENTITY_API_URL = "/api/spe-proj-m-sms";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private SpeProjMSmRepository speProjMSmRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restSpeProjMSmMockMvc;

    private SpeProjMSm speProjMSm;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
//    public static SpeProjMSm createEntity(EntityManager em) {
//        SpeProjMSm speProjMSm = new SpeProjMSm().speId(DEFAULT_SPE_ID).projId(DEFAULT_PROJ_ID).mId(DEFAULT_M_ID).smId(DEFAULT_SM_ID);
//        return speProjMSm;
//    }
//
//    /**
//     * Create an updated entity for this test.
//     *
//     * This is a static method, as tests for other entities might also need it,
//     * if they test an entity which requires the current entity.
//     */
//    public static SpeProjMSm createUpdatedEntity(EntityManager em) {
//        SpeProjMSm speProjMSm = new SpeProjMSm().speId(UPDATED_SPE_ID).projId(UPDATED_PROJ_ID).mId(UPDATED_M_ID).smId(UPDATED_SM_ID);
//        return speProjMSm;
//    }
//
//    @BeforeEach
//    public void initTest() {
//        speProjMSm = createEntity(em);
//    }
//
//    @Test
//    @Transactional
//    void createSpeProjMSm() throws Exception {
//        int databaseSizeBeforeCreate = speProjMSmRepository.findAll().size();
//        // Create the SpeProjMSm
//        restSpeProjMSmMockMvc
//            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(speProjMSm)))
//            .andExpect(status().isCreated());
//
//        // Validate the SpeProjMSm in the database
//        List<SpeProjMSm> speProjMSmList = speProjMSmRepository.findAll();
//        assertThat(speProjMSmList).hasSize(databaseSizeBeforeCreate + 1);
//        SpeProjMSm testSpeProjMSm = speProjMSmList.get(speProjMSmList.size() - 1);
//        assertThat(testSpeProjMSm.getSpeId()).isEqualTo(DEFAULT_SPE_ID);
//        assertThat(testSpeProjMSm.getProjId()).isEqualTo(DEFAULT_PROJ_ID);
//        assertThat(testSpeProjMSm.getmId()).isEqualTo(DEFAULT_M_ID);
//        assertThat(testSpeProjMSm.getSmId()).isEqualTo(DEFAULT_SM_ID);
//    }
//
//    @Test
//    @Transactional
//    void createSpeProjMSmWithExistingId() throws Exception {
//        // Create the SpeProjMSm with an existing ID
//        speProjMSm.setId(1L);
//
//        int databaseSizeBeforeCreate = speProjMSmRepository.findAll().size();
//
//        // An entity with an existing ID cannot be created, so this API call must fail
//        restSpeProjMSmMockMvc
//            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(speProjMSm)))
//            .andExpect(status().isBadRequest());
//
//        // Validate the SpeProjMSm in the database
//        List<SpeProjMSm> speProjMSmList = speProjMSmRepository.findAll();
//        assertThat(speProjMSmList).hasSize(databaseSizeBeforeCreate);
//    }
//
//    @Test
//    @Transactional
//    void checkProjIdIsRequired() throws Exception {
//        int databaseSizeBeforeTest = speProjMSmRepository.findAll().size();
//        // set the field null
//        speProjMSm.setProjId(null);
//
//        // Create the SpeProjMSm, which fails.
//
//        restSpeProjMSmMockMvc
//            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(speProjMSm)))
//            .andExpect(status().isBadRequest());
//
//        List<SpeProjMSm> speProjMSmList = speProjMSmRepository.findAll();
//        assertThat(speProjMSmList).hasSize(databaseSizeBeforeTest);
//    }
//
//    @Test
//    @Transactional
//    void checkmIdIsRequired() throws Exception {
//        int databaseSizeBeforeTest = speProjMSmRepository.findAll().size();
//        // set the field null
//        speProjMSm.setmId(null);
//
//        // Create the SpeProjMSm, which fails.
//
//        restSpeProjMSmMockMvc
//            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(speProjMSm)))
//            .andExpect(status().isBadRequest());
//
//        List<SpeProjMSm> speProjMSmList = speProjMSmRepository.findAll();
//        assertThat(speProjMSmList).hasSize(databaseSizeBeforeTest);
//    }
//
//    @Test
//    @Transactional
//    void checkSmIdIsRequired() throws Exception {
//        int databaseSizeBeforeTest = speProjMSmRepository.findAll().size();
//        // set the field null
//        speProjMSm.setSmId(null);
//
//        // Create the SpeProjMSm, which fails.
//
//        restSpeProjMSmMockMvc
//            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(speProjMSm)))
//            .andExpect(status().isBadRequest());
//
//        List<SpeProjMSm> speProjMSmList = speProjMSmRepository.findAll();
//        assertThat(speProjMSmList).hasSize(databaseSizeBeforeTest);
//    }
//
//    @Test
//    @Transactional
//    void getAllSpeProjMSms() throws Exception {
//        // Initialize the database
//        speProjMSmRepository.saveAndFlush(speProjMSm);
//
//        // Get all the speProjMSmList
//        restSpeProjMSmMockMvc
//            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
//            .andExpect(status().isOk())
//            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
//            .andExpect(jsonPath("$.[*].id").value(hasItem(speProjMSm.getId().intValue())))
//            .andExpect(jsonPath("$.[*].speId").value(hasItem(DEFAULT_SPE_ID.intValue())))
//            .andExpect(jsonPath("$.[*].projId").value(hasItem(DEFAULT_PROJ_ID.intValue())))
//            .andExpect(jsonPath("$.[*].mId").value(hasItem(DEFAULT_M_ID.intValue())))
//            .andExpect(jsonPath("$.[*].smId").value(hasItem(DEFAULT_SM_ID.intValue())));
//    }
//
//    @Test
//    @Transactional
//    void getSpeProjMSm() throws Exception {
//        // Initialize the database
//        speProjMSmRepository.saveAndFlush(speProjMSm);
//
//        // Get the speProjMSm
//        restSpeProjMSmMockMvc
//            .perform(get(ENTITY_API_URL_ID, speProjMSm.getId()))
//            .andExpect(status().isOk())
//            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
//            .andExpect(jsonPath("$.id").value(speProjMSm.getId().intValue()))
//            .andExpect(jsonPath("$.speId").value(DEFAULT_SPE_ID.intValue()))
//            .andExpect(jsonPath("$.projId").value(DEFAULT_PROJ_ID.intValue()))
//            .andExpect(jsonPath("$.mId").value(DEFAULT_M_ID.intValue()))
//            .andExpect(jsonPath("$.smId").value(DEFAULT_SM_ID.intValue()));
//    }
//
//    @Test
//    @Transactional
//    void getNonExistingSpeProjMSm() throws Exception {
//        // Get the speProjMSm
//        restSpeProjMSmMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
//    }
//
//    @Test
//    @Transactional
//    void putNewSpeProjMSm() throws Exception {
//        // Initialize the database
//        speProjMSmRepository.saveAndFlush(speProjMSm);
//
//        int databaseSizeBeforeUpdate = speProjMSmRepository.findAll().size();
//
//        // Update the speProjMSm
//        SpeProjMSm updatedSpeProjMSm = speProjMSmRepository.findById(speProjMSm.getId()).get();
//        // Disconnect from session so that the updates on updatedSpeProjMSm are not directly saved in db
//        em.detach(updatedSpeProjMSm);
//        updatedSpeProjMSm.speId(UPDATED_SPE_ID).projId(UPDATED_PROJ_ID).mId(UPDATED_M_ID).smId(UPDATED_SM_ID);
//
//        restSpeProjMSmMockMvc
//            .perform(
//                put(ENTITY_API_URL_ID, updatedSpeProjMSm.getId())
//                    .contentType(MediaType.APPLICATION_JSON)
//                    .content(TestUtil.convertObjectToJsonBytes(updatedSpeProjMSm))
//            )
//            .andExpect(status().isOk());
//
//        // Validate the SpeProjMSm in the database
//        List<SpeProjMSm> speProjMSmList = speProjMSmRepository.findAll();
//        assertThat(speProjMSmList).hasSize(databaseSizeBeforeUpdate);
//        SpeProjMSm testSpeProjMSm = speProjMSmList.get(speProjMSmList.size() - 1);
//        assertThat(testSpeProjMSm.getSpeId()).isEqualTo(UPDATED_SPE_ID);
//        assertThat(testSpeProjMSm.getProjId()).isEqualTo(UPDATED_PROJ_ID);
//        assertThat(testSpeProjMSm.getmId()).isEqualTo(UPDATED_M_ID);
//        assertThat(testSpeProjMSm.getSmId()).isEqualTo(UPDATED_SM_ID);
//    }
//
//    @Test
//    @Transactional
//    void putNonExistingSpeProjMSm() throws Exception {
//        int databaseSizeBeforeUpdate = speProjMSmRepository.findAll().size();
//        speProjMSm.setId(count.incrementAndGet());
//
//        // If the entity doesn't have an ID, it will throw BadRequestAlertException
//        restSpeProjMSmMockMvc
//            .perform(
//                put(ENTITY_API_URL_ID, speProjMSm.getId())
//                    .contentType(MediaType.APPLICATION_JSON)
//                    .content(TestUtil.convertObjectToJsonBytes(speProjMSm))
//            )
//            .andExpect(status().isBadRequest());
//
//        // Validate the SpeProjMSm in the database
//        List<SpeProjMSm> speProjMSmList = speProjMSmRepository.findAll();
//        assertThat(speProjMSmList).hasSize(databaseSizeBeforeUpdate);
//    }
//
//    @Test
//    @Transactional
//    void putWithIdMismatchSpeProjMSm() throws Exception {
//        int databaseSizeBeforeUpdate = speProjMSmRepository.findAll().size();
//        speProjMSm.setId(count.incrementAndGet());
//
//        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
//        restSpeProjMSmMockMvc
//            .perform(
//                put(ENTITY_API_URL_ID, count.incrementAndGet())
//                    .contentType(MediaType.APPLICATION_JSON)
//                    .content(TestUtil.convertObjectToJsonBytes(speProjMSm))
//            )
//            .andExpect(status().isBadRequest());
//
//        // Validate the SpeProjMSm in the database
//        List<SpeProjMSm> speProjMSmList = speProjMSmRepository.findAll();
//        assertThat(speProjMSmList).hasSize(databaseSizeBeforeUpdate);
//    }
//
//    @Test
//    @Transactional
//    void putWithMissingIdPathParamSpeProjMSm() throws Exception {
//        int databaseSizeBeforeUpdate = speProjMSmRepository.findAll().size();
//        speProjMSm.setId(count.incrementAndGet());
//
//        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
//        restSpeProjMSmMockMvc
//            .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(speProjMSm)))
//            .andExpect(status().isMethodNotAllowed());
//
//        // Validate the SpeProjMSm in the database
//        List<SpeProjMSm> speProjMSmList = speProjMSmRepository.findAll();
//        assertThat(speProjMSmList).hasSize(databaseSizeBeforeUpdate);
//    }
//
//    @Test
//    @Transactional
//    void partialUpdateSpeProjMSmWithPatch() throws Exception {
//        // Initialize the database
//        speProjMSmRepository.saveAndFlush(speProjMSm);
//
//        int databaseSizeBeforeUpdate = speProjMSmRepository.findAll().size();
//
//        // Update the speProjMSm using partial update
//        SpeProjMSm partialUpdatedSpeProjMSm = new SpeProjMSm();
//        partialUpdatedSpeProjMSm.setId(speProjMSm.getId());
//
//        partialUpdatedSpeProjMSm.projId(UPDATED_PROJ_ID).mId(UPDATED_M_ID).smId(UPDATED_SM_ID);
//
//        restSpeProjMSmMockMvc
//            .perform(
//                patch(ENTITY_API_URL_ID, partialUpdatedSpeProjMSm.getId())
//                    .contentType("application/merge-patch+json")
//                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedSpeProjMSm))
//            )
//            .andExpect(status().isOk());
//
//        // Validate the SpeProjMSm in the database
//        List<SpeProjMSm> speProjMSmList = speProjMSmRepository.findAll();
//        assertThat(speProjMSmList).hasSize(databaseSizeBeforeUpdate);
//        SpeProjMSm testSpeProjMSm = speProjMSmList.get(speProjMSmList.size() - 1);
//        assertThat(testSpeProjMSm.getSpeId()).isEqualTo(DEFAULT_SPE_ID);
//        assertThat(testSpeProjMSm.getProjId()).isEqualTo(UPDATED_PROJ_ID);
//        assertThat(testSpeProjMSm.getmId()).isEqualTo(UPDATED_M_ID);
//        assertThat(testSpeProjMSm.getSmId()).isEqualTo(UPDATED_SM_ID);
//    }
//
//    @Test
//    @Transactional
//    void fullUpdateSpeProjMSmWithPatch() throws Exception {
//        // Initialize the database
//        speProjMSmRepository.saveAndFlush(speProjMSm);
//
//        int databaseSizeBeforeUpdate = speProjMSmRepository.findAll().size();
//
//        // Update the speProjMSm using partial update
//        SpeProjMSm partialUpdatedSpeProjMSm = new SpeProjMSm();
//        partialUpdatedSpeProjMSm.setId(speProjMSm.getId());
//
//        partialUpdatedSpeProjMSm.speId(UPDATED_SPE_ID).projId(UPDATED_PROJ_ID).mId(UPDATED_M_ID).smId(UPDATED_SM_ID);
//
//        restSpeProjMSmMockMvc
//            .perform(
//                patch(ENTITY_API_URL_ID, partialUpdatedSpeProjMSm.getId())
//                    .contentType("application/merge-patch+json")
//                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedSpeProjMSm))
//            )
//            .andExpect(status().isOk());
//
//        // Validate the SpeProjMSm in the database
//        List<SpeProjMSm> speProjMSmList = speProjMSmRepository.findAll();
//        assertThat(speProjMSmList).hasSize(databaseSizeBeforeUpdate);
//        SpeProjMSm testSpeProjMSm = speProjMSmList.get(speProjMSmList.size() - 1);
//        assertThat(testSpeProjMSm.getSpeId()).isEqualTo(UPDATED_SPE_ID);
//        assertThat(testSpeProjMSm.getProjId()).isEqualTo(UPDATED_PROJ_ID);
//        assertThat(testSpeProjMSm.getmId()).isEqualTo(UPDATED_M_ID);
//        assertThat(testSpeProjMSm.getSmId()).isEqualTo(UPDATED_SM_ID);
//    }
//
//    @Test
//    @Transactional
//    void patchNonExistingSpeProjMSm() throws Exception {
//        int databaseSizeBeforeUpdate = speProjMSmRepository.findAll().size();
//        speProjMSm.setId(count.incrementAndGet());
//
//        // If the entity doesn't have an ID, it will throw BadRequestAlertException
//        restSpeProjMSmMockMvc
//            .perform(
//                patch(ENTITY_API_URL_ID, speProjMSm.getId())
//                    .contentType("application/merge-patch+json")
//                    .content(TestUtil.convertObjectToJsonBytes(speProjMSm))
//            )
//            .andExpect(status().isBadRequest());
//
//        // Validate the SpeProjMSm in the database
//        List<SpeProjMSm> speProjMSmList = speProjMSmRepository.findAll();
//        assertThat(speProjMSmList).hasSize(databaseSizeBeforeUpdate);
//    }
//
//    @Test
//    @Transactional
//    void patchWithIdMismatchSpeProjMSm() throws Exception {
//        int databaseSizeBeforeUpdate = speProjMSmRepository.findAll().size();
//        speProjMSm.setId(count.incrementAndGet());
//
//        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
//        restSpeProjMSmMockMvc
//            .perform(
//                patch(ENTITY_API_URL_ID, count.incrementAndGet())
//                    .contentType("application/merge-patch+json")
//                    .content(TestUtil.convertObjectToJsonBytes(speProjMSm))
//            )
//            .andExpect(status().isBadRequest());
//
//        // Validate the SpeProjMSm in the database
//        List<SpeProjMSm> speProjMSmList = speProjMSmRepository.findAll();
//        assertThat(speProjMSmList).hasSize(databaseSizeBeforeUpdate);
//    }
//
//    @Test
//    @Transactional
//    void patchWithMissingIdPathParamSpeProjMSm() throws Exception {
//        int databaseSizeBeforeUpdate = speProjMSmRepository.findAll().size();
//        speProjMSm.setId(count.incrementAndGet());
//
//        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
//        restSpeProjMSmMockMvc
//            .perform(
//                patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(TestUtil.convertObjectToJsonBytes(speProjMSm))
//            )
//            .andExpect(status().isMethodNotAllowed());
//
//        // Validate the SpeProjMSm in the database
//        List<SpeProjMSm> speProjMSmList = speProjMSmRepository.findAll();
//        assertThat(speProjMSmList).hasSize(databaseSizeBeforeUpdate);
//    }
//
//    @Test
//    @Transactional
//    void deleteSpeProjMSm() throws Exception {
//        // Initialize the database
//        speProjMSmRepository.saveAndFlush(speProjMSm);
//
//        int databaseSizeBeforeDelete = speProjMSmRepository.findAll().size();
//
//        // Delete the speProjMSm
//        restSpeProjMSmMockMvc
//            .perform(delete(ENTITY_API_URL_ID, speProjMSm.getId()).accept(MediaType.APPLICATION_JSON))
//            .andExpect(status().isNoContent());
//
//        // Validate the database contains one less item
//        List<SpeProjMSm> speProjMSmList = speProjMSmRepository.findAll();
//        assertThat(speProjMSmList).hasSize(databaseSizeBeforeDelete - 1);
//    }
}
