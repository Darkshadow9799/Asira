package com.dshaw.asira.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.dshaw.asira.IntegrationTest;
import com.dshaw.asira.domain.Com;
import com.dshaw.asira.repository.ComRepository;
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
 * Integration tests for the {@link ComResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class ComResourceIT {

    private static final Long DEFAULT_C_CREATED_BY_ID = 1L;
    private static final Long UPDATED_C_CREATED_BY_ID = 2L;

    private static final String DEFAULT_C_CREATED_BY_NAME = "AAAAAAAAAA";
    private static final String UPDATED_C_CREATED_BY_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_C_DESC = "AAAAAAAAAA";
    private static final String UPDATED_C_DESC = "BBBBBBBBBB";

    private static final Instant DEFAULT_C_CREATED_DATE = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_C_CREATED_DATE = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final Instant DEFAULT_C_MODIFIED_DATE = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_C_MODIFIED_DATE = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final Long DEFAULT_SM_ID = 1L;
    private static final Long UPDATED_SM_ID = 2L;

    private static final String ENTITY_API_URL = "/api/coms";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private ComRepository comRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restComMockMvc;

    private Com com;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Com createEntity(EntityManager em) {
        Com com = new Com()
            .cCreatedById(DEFAULT_C_CREATED_BY_ID)
            .cCreatedByName(DEFAULT_C_CREATED_BY_NAME)
            .cDesc(DEFAULT_C_DESC)
            .cCreatedDate(DEFAULT_C_CREATED_DATE)
            .cModifiedDate(DEFAULT_C_MODIFIED_DATE)
            .smId(DEFAULT_SM_ID);
        return com;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
//    public static Com createUpdatedEntity(EntityManager em) {
//        Com com = new Com()
//            .cCreatedById(UPDATED_C_CREATED_BY_ID)
//            .cCreatedByName(UPDATED_C_CREATED_BY_NAME)
//            .cDesc(UPDATED_C_DESC)
//            .cCreatedDate(UPDATED_C_CREATED_DATE)
//            .cModifiedDate(UPDATED_C_MODIFIED_DATE)
//            .smId(UPDATED_SM_ID);
//        return com;
//    }
//
//    @BeforeEach
//    public void initTest() {
//        com = createEntity(em);
//    }
//
//    @Test
//    @Transactional
//    void createCom() throws Exception {
//        int databaseSizeBeforeCreate = comRepository.findAll().size();
//        // Create the Com
//        restComMockMvc
//            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(com)))
//            .andExpect(status().isCreated());
//
//        // Validate the Com in the database
//        List<Com> comList = comRepository.findAll();
//        assertThat(comList).hasSize(databaseSizeBeforeCreate + 1);
//        Com testCom = comList.get(comList.size() - 1);
//        assertThat(testCom.getcCreatedById()).isEqualTo(DEFAULT_C_CREATED_BY_ID);
//        assertThat(testCom.getcCreatedByName()).isEqualTo(DEFAULT_C_CREATED_BY_NAME);
//        assertThat(testCom.getcDesc()).isEqualTo(DEFAULT_C_DESC);
//        assertThat(testCom.getcCreatedDate()).isEqualTo(DEFAULT_C_CREATED_DATE);
//        assertThat(testCom.getcModifiedDate()).isEqualTo(DEFAULT_C_MODIFIED_DATE);
//        assertThat(testCom.getSmId()).isEqualTo(DEFAULT_SM_ID);
//    }
//
//    @Test
//    @Transactional
//    void createComWithExistingId() throws Exception {
//        // Create the Com with an existing ID
//        com.setId(1L);
//
//        int databaseSizeBeforeCreate = comRepository.findAll().size();
//
//        // An entity with an existing ID cannot be created, so this API call must fail
//        restComMockMvc
//            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(com)))
//            .andExpect(status().isBadRequest());
//
//        // Validate the Com in the database
//        List<Com> comList = comRepository.findAll();
//        assertThat(comList).hasSize(databaseSizeBeforeCreate);
//    }
//
//    @Test
//    @Transactional
//    void checkcCreatedByIdIsRequired() throws Exception {
//        int databaseSizeBeforeTest = comRepository.findAll().size();
//        // set the field null
//        com.setcCreatedById(null);
//
//        // Create the Com, which fails.
//
//        restComMockMvc
//            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(com)))
//            .andExpect(status().isBadRequest());
//
//        List<Com> comList = comRepository.findAll();
//        assertThat(comList).hasSize(databaseSizeBeforeTest);
//    }
//
//    @Test
//    @Transactional
//    void checkSmIdIsRequired() throws Exception {
//        int databaseSizeBeforeTest = comRepository.findAll().size();
//        // set the field null
//        com.setSmId(null);
//
//        // Create the Com, which fails.
//
//        restComMockMvc
//            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(com)))
//            .andExpect(status().isBadRequest());
//
//        List<Com> comList = comRepository.findAll();
//        assertThat(comList).hasSize(databaseSizeBeforeTest);
//    }
//
//    @Test
//    @Transactional
//    void getAllComs() throws Exception {
//        // Initialize the database
//        comRepository.saveAndFlush(com);
//
//        // Get all the comList
//        restComMockMvc
//            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
//            .andExpect(status().isOk())
//            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
//            .andExpect(jsonPath("$.[*].id").value(hasItem(com.getId().intValue())))
//            .andExpect(jsonPath("$.[*].cCreatedById").value(hasItem(DEFAULT_C_CREATED_BY_ID.intValue())))
//            .andExpect(jsonPath("$.[*].cCreatedByName").value(hasItem(DEFAULT_C_CREATED_BY_NAME)))
//            .andExpect(jsonPath("$.[*].cDesc").value(hasItem(DEFAULT_C_DESC)))
//            .andExpect(jsonPath("$.[*].cCreatedDate").value(hasItem(DEFAULT_C_CREATED_DATE.toString())))
//            .andExpect(jsonPath("$.[*].cModifiedDate").value(hasItem(DEFAULT_C_MODIFIED_DATE.toString())))
//            .andExpect(jsonPath("$.[*].smId").value(hasItem(DEFAULT_SM_ID.intValue())));
//    }
//
//    @Test
//    @Transactional
//    void getCom() throws Exception {
//        // Initialize the database
//        comRepository.saveAndFlush(com);
//
//        // Get the com
//        restComMockMvc
//            .perform(get(ENTITY_API_URL_ID, com.getId()))
//            .andExpect(status().isOk())
//            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
//            .andExpect(jsonPath("$.id").value(com.getId().intValue()))
//            .andExpect(jsonPath("$.cCreatedById").value(DEFAULT_C_CREATED_BY_ID.intValue()))
//            .andExpect(jsonPath("$.cCreatedByName").value(DEFAULT_C_CREATED_BY_NAME))
//            .andExpect(jsonPath("$.cDesc").value(DEFAULT_C_DESC))
//            .andExpect(jsonPath("$.cCreatedDate").value(DEFAULT_C_CREATED_DATE.toString()))
//            .andExpect(jsonPath("$.cModifiedDate").value(DEFAULT_C_MODIFIED_DATE.toString()))
//            .andExpect(jsonPath("$.smId").value(DEFAULT_SM_ID.intValue()));
//    }
//
//    @Test
//    @Transactional
//    void getNonExistingCom() throws Exception {
//        // Get the com
//        restComMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
//    }
//
//    @Test
//    @Transactional
//    void putNewCom() throws Exception {
//        // Initialize the database
//        comRepository.saveAndFlush(com);
//
//        int databaseSizeBeforeUpdate = comRepository.findAll().size();
//
//        // Update the com
//        Com updatedCom = comRepository.findById(com.getId()).get();
//        // Disconnect from session so that the updates on updatedCom are not directly saved in db
//        em.detach(updatedCom);
//        updatedCom
//            .cCreatedById(UPDATED_C_CREATED_BY_ID)
//            .cCreatedByName(UPDATED_C_CREATED_BY_NAME)
//            .cDesc(UPDATED_C_DESC)
//            .cCreatedDate(UPDATED_C_CREATED_DATE)
//            .cModifiedDate(UPDATED_C_MODIFIED_DATE)
//            .smId(UPDATED_SM_ID);
//
//        restComMockMvc
//            .perform(
//                put(ENTITY_API_URL_ID, updatedCom.getId())
//                    .contentType(MediaType.APPLICATION_JSON)
//                    .content(TestUtil.convertObjectToJsonBytes(updatedCom))
//            )
//            .andExpect(status().isOk());
//
//        // Validate the Com in the database
//        List<Com> comList = comRepository.findAll();
//        assertThat(comList).hasSize(databaseSizeBeforeUpdate);
//        Com testCom = comList.get(comList.size() - 1);
//        assertThat(testCom.getcCreatedById()).isEqualTo(UPDATED_C_CREATED_BY_ID);
//        assertThat(testCom.getcCreatedByName()).isEqualTo(UPDATED_C_CREATED_BY_NAME);
//        assertThat(testCom.getcDesc()).isEqualTo(UPDATED_C_DESC);
//        assertThat(testCom.getcCreatedDate()).isEqualTo(UPDATED_C_CREATED_DATE);
//        assertThat(testCom.getcModifiedDate()).isEqualTo(UPDATED_C_MODIFIED_DATE);
//        assertThat(testCom.getSmId()).isEqualTo(UPDATED_SM_ID);
//    }
//
//    @Test
//    @Transactional
//    void putNonExistingCom() throws Exception {
//        int databaseSizeBeforeUpdate = comRepository.findAll().size();
//        com.setId(count.incrementAndGet());
//
//        // If the entity doesn't have an ID, it will throw BadRequestAlertException
//        restComMockMvc
//            .perform(
//                put(ENTITY_API_URL_ID, com.getId()).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(com))
//            )
//            .andExpect(status().isBadRequest());
//
//        // Validate the Com in the database
//        List<Com> comList = comRepository.findAll();
//        assertThat(comList).hasSize(databaseSizeBeforeUpdate);
//    }
//
//    @Test
//    @Transactional
//    void putWithIdMismatchCom() throws Exception {
//        int databaseSizeBeforeUpdate = comRepository.findAll().size();
//        com.setId(count.incrementAndGet());
//
//        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
//        restComMockMvc
//            .perform(
//                put(ENTITY_API_URL_ID, count.incrementAndGet())
//                    .contentType(MediaType.APPLICATION_JSON)
//                    .content(TestUtil.convertObjectToJsonBytes(com))
//            )
//            .andExpect(status().isBadRequest());
//
//        // Validate the Com in the database
//        List<Com> comList = comRepository.findAll();
//        assertThat(comList).hasSize(databaseSizeBeforeUpdate);
//    }
//
//    @Test
//    @Transactional
//    void putWithMissingIdPathParamCom() throws Exception {
//        int databaseSizeBeforeUpdate = comRepository.findAll().size();
//        com.setId(count.incrementAndGet());
//
//        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
//        restComMockMvc
//            .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(com)))
//            .andExpect(status().isMethodNotAllowed());
//
//        // Validate the Com in the database
//        List<Com> comList = comRepository.findAll();
//        assertThat(comList).hasSize(databaseSizeBeforeUpdate);
//    }
//
//    @Test
//    @Transactional
//    void partialUpdateComWithPatch() throws Exception {
//        // Initialize the database
//        comRepository.saveAndFlush(com);
//
//        int databaseSizeBeforeUpdate = comRepository.findAll().size();
//
//        // Update the com using partial update
//        Com partialUpdatedCom = new Com();
//        partialUpdatedCom.setId(com.getId());
//
//        partialUpdatedCom.cDesc(UPDATED_C_DESC).cModifiedDate(UPDATED_C_MODIFIED_DATE).smId(UPDATED_SM_ID);
//
//        restComMockMvc
//            .perform(
//                patch(ENTITY_API_URL_ID, partialUpdatedCom.getId())
//                    .contentType("application/merge-patch+json")
//                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedCom))
//            )
//            .andExpect(status().isOk());
//
//        // Validate the Com in the database
//        List<Com> comList = comRepository.findAll();
//        assertThat(comList).hasSize(databaseSizeBeforeUpdate);
//        Com testCom = comList.get(comList.size() - 1);
//        assertThat(testCom.getcCreatedById()).isEqualTo(DEFAULT_C_CREATED_BY_ID);
//        assertThat(testCom.getcCreatedByName()).isEqualTo(DEFAULT_C_CREATED_BY_NAME);
//        assertThat(testCom.getcDesc()).isEqualTo(UPDATED_C_DESC);
//        assertThat(testCom.getcCreatedDate()).isEqualTo(DEFAULT_C_CREATED_DATE);
//        assertThat(testCom.getcModifiedDate()).isEqualTo(UPDATED_C_MODIFIED_DATE);
//        assertThat(testCom.getSmId()).isEqualTo(UPDATED_SM_ID);
//    }
//
//    @Test
//    @Transactional
//    void fullUpdateComWithPatch() throws Exception {
//        // Initialize the database
//        comRepository.saveAndFlush(com);
//
//        int databaseSizeBeforeUpdate = comRepository.findAll().size();
//
//        // Update the com using partial update
//        Com partialUpdatedCom = new Com();
//        partialUpdatedCom.setId(com.getId());
//
//        partialUpdatedCom
//            .cCreatedById(UPDATED_C_CREATED_BY_ID)
//            .cCreatedByName(UPDATED_C_CREATED_BY_NAME)
//            .cDesc(UPDATED_C_DESC)
//            .cCreatedDate(UPDATED_C_CREATED_DATE)
//            .cModifiedDate(UPDATED_C_MODIFIED_DATE)
//            .smId(UPDATED_SM_ID);
//
//        restComMockMvc
//            .perform(
//                patch(ENTITY_API_URL_ID, partialUpdatedCom.getId())
//                    .contentType("application/merge-patch+json")
//                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedCom))
//            )
//            .andExpect(status().isOk());
//
//        // Validate the Com in the database
//        List<Com> comList = comRepository.findAll();
//        assertThat(comList).hasSize(databaseSizeBeforeUpdate);
//        Com testCom = comList.get(comList.size() - 1);
//        assertThat(testCom.getcCreatedById()).isEqualTo(UPDATED_C_CREATED_BY_ID);
//        assertThat(testCom.getcCreatedByName()).isEqualTo(UPDATED_C_CREATED_BY_NAME);
//        assertThat(testCom.getcDesc()).isEqualTo(UPDATED_C_DESC);
//        assertThat(testCom.getcCreatedDate()).isEqualTo(UPDATED_C_CREATED_DATE);
//        assertThat(testCom.getcModifiedDate()).isEqualTo(UPDATED_C_MODIFIED_DATE);
//        assertThat(testCom.getSmId()).isEqualTo(UPDATED_SM_ID);
//    }
//
//    @Test
//    @Transactional
//    void patchNonExistingCom() throws Exception {
//        int databaseSizeBeforeUpdate = comRepository.findAll().size();
//        com.setId(count.incrementAndGet());
//
//        // If the entity doesn't have an ID, it will throw BadRequestAlertException
//        restComMockMvc
//            .perform(
//                patch(ENTITY_API_URL_ID, com.getId())
//                    .contentType("application/merge-patch+json")
//                    .content(TestUtil.convertObjectToJsonBytes(com))
//            )
//            .andExpect(status().isBadRequest());
//
//        // Validate the Com in the database
//        List<Com> comList = comRepository.findAll();
//        assertThat(comList).hasSize(databaseSizeBeforeUpdate);
//    }
//
//    @Test
//    @Transactional
//    void patchWithIdMismatchCom() throws Exception {
//        int databaseSizeBeforeUpdate = comRepository.findAll().size();
//        com.setId(count.incrementAndGet());
//
//        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
//        restComMockMvc
//            .perform(
//                patch(ENTITY_API_URL_ID, count.incrementAndGet())
//                    .contentType("application/merge-patch+json")
//                    .content(TestUtil.convertObjectToJsonBytes(com))
//            )
//            .andExpect(status().isBadRequest());
//
//        // Validate the Com in the database
//        List<Com> comList = comRepository.findAll();
//        assertThat(comList).hasSize(databaseSizeBeforeUpdate);
//    }
//
//    @Test
//    @Transactional
//    void patchWithMissingIdPathParamCom() throws Exception {
//        int databaseSizeBeforeUpdate = comRepository.findAll().size();
//        com.setId(count.incrementAndGet());
//
//        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
//        restComMockMvc
//            .perform(patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(TestUtil.convertObjectToJsonBytes(com)))
//            .andExpect(status().isMethodNotAllowed());
//
//        // Validate the Com in the database
//        List<Com> comList = comRepository.findAll();
//        assertThat(comList).hasSize(databaseSizeBeforeUpdate);
//    }
//
//    @Test
//    @Transactional
//    void deleteCom() throws Exception {
//        // Initialize the database
//        comRepository.saveAndFlush(com);
//
//        int databaseSizeBeforeDelete = comRepository.findAll().size();
//
//        // Delete the com
//        restComMockMvc.perform(delete(ENTITY_API_URL_ID, com.getId()).accept(MediaType.APPLICATION_JSON)).andExpect(status().isNoContent());
//
//        // Validate the database contains one less item
//        List<Com> comList = comRepository.findAll();
//        assertThat(comList).hasSize(databaseSizeBeforeDelete - 1);
//    }
}
