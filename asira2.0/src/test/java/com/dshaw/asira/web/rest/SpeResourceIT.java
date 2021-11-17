package com.dshaw.asira.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.dshaw.asira.IntegrationTest;
import com.dshaw.asira.domain.Spe;
import com.dshaw.asira.repository.SpeRepository;
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
 * Integration tests for the {@link SpeResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class SpeResourceIT {

    private static final String DEFAULT_SPE_FIRST_NAME = "AAAAAAAAAA";
    private static final String UPDATED_SPE_FIRST_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_SPE_LAST_NAME = "AAAAAAAAAA";
    private static final String UPDATED_SPE_LAST_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_SPE_EMAIL_ID = "AAAAAAAAAA";
    private static final String UPDATED_SPE_EMAIL_ID = "BBBBBBBBBB";

    private static final Long DEFAULT_SPE_PHONE_NUMBER = 1L;
    private static final Long UPDATED_SPE_PHONE_NUMBER = 2L;

    private static final Boolean DEFAULT_SPE_ORG_VERIFIED = false;
    private static final Boolean UPDATED_SPE_ORG_VERIFIED = true;

    private static final Instant DEFAULT_CREATED_DATE = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_CREATED_DATE = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final Instant DEFAULT_MODIFIED_DATE = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_MODIFIED_DATE = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final String ENTITY_API_URL = "/api/spes";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private SpeRepository speRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restSpeMockMvc;

    private Spe spe;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Spe createEntity(EntityManager em) {
        Spe spe = new Spe()
            .speFirstName(DEFAULT_SPE_FIRST_NAME)
            .speLastName(DEFAULT_SPE_LAST_NAME)
            .speEmailId(DEFAULT_SPE_EMAIL_ID)
            .spePhoneNumber(DEFAULT_SPE_PHONE_NUMBER)
            .speOrgVerified(DEFAULT_SPE_ORG_VERIFIED)
            .createdDate(DEFAULT_CREATED_DATE)
            .modifiedDate(DEFAULT_MODIFIED_DATE);
        return spe;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Spe createUpdatedEntity(EntityManager em) {
        Spe spe = new Spe()
            .speFirstName(UPDATED_SPE_FIRST_NAME)
            .speLastName(UPDATED_SPE_LAST_NAME)
            .speEmailId(UPDATED_SPE_EMAIL_ID)
            .spePhoneNumber(UPDATED_SPE_PHONE_NUMBER)
            .speOrgVerified(UPDATED_SPE_ORG_VERIFIED)
            .createdDate(UPDATED_CREATED_DATE)
            .modifiedDate(UPDATED_MODIFIED_DATE);
        return spe;
    }

    @BeforeEach
    public void initTest() {
        spe = createEntity(em);
    }

    @Test
    @Transactional
    void createSpe() throws Exception {
        int databaseSizeBeforeCreate = speRepository.findAll().size();
        // Create the Spe
        restSpeMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(spe)))
            .andExpect(status().isCreated());

        // Validate the Spe in the database
        List<Spe> speList = speRepository.findAll();
        assertThat(speList).hasSize(databaseSizeBeforeCreate + 1);
        Spe testSpe = speList.get(speList.size() - 1);
        assertThat(testSpe.getSpeFirstName()).isEqualTo(DEFAULT_SPE_FIRST_NAME);
        assertThat(testSpe.getSpeLastName()).isEqualTo(DEFAULT_SPE_LAST_NAME);
        assertThat(testSpe.getSpeEmailId()).isEqualTo(DEFAULT_SPE_EMAIL_ID);
        assertThat(testSpe.getSpePhoneNumber()).isEqualTo(DEFAULT_SPE_PHONE_NUMBER);
        assertThat(testSpe.getSpeOrgVerified()).isEqualTo(DEFAULT_SPE_ORG_VERIFIED);
        assertThat(testSpe.getCreatedDate()).isEqualTo(DEFAULT_CREATED_DATE);
        assertThat(testSpe.getModifiedDate()).isEqualTo(DEFAULT_MODIFIED_DATE);
    }

    @Test
    @Transactional
    void createSpeWithExistingId() throws Exception {
        // Create the Spe with an existing ID
        spe.setId(1L);

        int databaseSizeBeforeCreate = speRepository.findAll().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        restSpeMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(spe)))
            .andExpect(status().isBadRequest());

        // Validate the Spe in the database
        List<Spe> speList = speRepository.findAll();
        assertThat(speList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void checkSpeEmailIdIsRequired() throws Exception {
        int databaseSizeBeforeTest = speRepository.findAll().size();
        // set the field null
        spe.setSpeEmailId(null);

        // Create the Spe, which fails.

        restSpeMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(spe)))
            .andExpect(status().isBadRequest());

        List<Spe> speList = speRepository.findAll();
        assertThat(speList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void getAllSpes() throws Exception {
        // Initialize the database
        speRepository.saveAndFlush(spe);

        // Get all the speList
        restSpeMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(spe.getId().intValue())))
            .andExpect(jsonPath("$.[*].speFirstName").value(hasItem(DEFAULT_SPE_FIRST_NAME)))
            .andExpect(jsonPath("$.[*].speLastName").value(hasItem(DEFAULT_SPE_LAST_NAME)))
            .andExpect(jsonPath("$.[*].speEmailId").value(hasItem(DEFAULT_SPE_EMAIL_ID)))
            .andExpect(jsonPath("$.[*].spePhoneNumber").value(hasItem(DEFAULT_SPE_PHONE_NUMBER.intValue())))
            .andExpect(jsonPath("$.[*].speOrgVerified").value(hasItem(DEFAULT_SPE_ORG_VERIFIED.booleanValue())))
            .andExpect(jsonPath("$.[*].createdDate").value(hasItem(DEFAULT_CREATED_DATE.toString())))
            .andExpect(jsonPath("$.[*].modifiedDate").value(hasItem(DEFAULT_MODIFIED_DATE.toString())));
    }

    @Test
    @Transactional
    void getSpe() throws Exception {
        // Initialize the database
        speRepository.saveAndFlush(spe);

        // Get the spe
        restSpeMockMvc
            .perform(get(ENTITY_API_URL_ID, spe.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(spe.getId().intValue()))
            .andExpect(jsonPath("$.speFirstName").value(DEFAULT_SPE_FIRST_NAME))
            .andExpect(jsonPath("$.speLastName").value(DEFAULT_SPE_LAST_NAME))
            .andExpect(jsonPath("$.speEmailId").value(DEFAULT_SPE_EMAIL_ID))
            .andExpect(jsonPath("$.spePhoneNumber").value(DEFAULT_SPE_PHONE_NUMBER.intValue()))
            .andExpect(jsonPath("$.speOrgVerified").value(DEFAULT_SPE_ORG_VERIFIED.booleanValue()))
            .andExpect(jsonPath("$.createdDate").value(DEFAULT_CREATED_DATE.toString()))
            .andExpect(jsonPath("$.modifiedDate").value(DEFAULT_MODIFIED_DATE.toString()));
    }

    @Test
    @Transactional
    void getNonExistingSpe() throws Exception {
        // Get the spe
        restSpeMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putNewSpe() throws Exception {
        // Initialize the database
        speRepository.saveAndFlush(spe);

        int databaseSizeBeforeUpdate = speRepository.findAll().size();

        // Update the spe
        Spe updatedSpe = speRepository.findById(spe.getId()).get();
        // Disconnect from session so that the updates on updatedSpe are not directly saved in db
        em.detach(updatedSpe);
        updatedSpe
            .speFirstName(UPDATED_SPE_FIRST_NAME)
            .speLastName(UPDATED_SPE_LAST_NAME)
            .speEmailId(UPDATED_SPE_EMAIL_ID)
            .spePhoneNumber(UPDATED_SPE_PHONE_NUMBER)
            .speOrgVerified(UPDATED_SPE_ORG_VERIFIED)
            .createdDate(UPDATED_CREATED_DATE)
            .modifiedDate(UPDATED_MODIFIED_DATE);

        restSpeMockMvc
            .perform(
                put(ENTITY_API_URL_ID, updatedSpe.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(updatedSpe))
            )
            .andExpect(status().isOk());

        // Validate the Spe in the database
        List<Spe> speList = speRepository.findAll();
        assertThat(speList).hasSize(databaseSizeBeforeUpdate);
        Spe testSpe = speList.get(speList.size() - 1);
        assertThat(testSpe.getSpeFirstName()).isEqualTo(UPDATED_SPE_FIRST_NAME);
        assertThat(testSpe.getSpeLastName()).isEqualTo(UPDATED_SPE_LAST_NAME);
        assertThat(testSpe.getSpeEmailId()).isEqualTo(UPDATED_SPE_EMAIL_ID);
        assertThat(testSpe.getSpePhoneNumber()).isEqualTo(UPDATED_SPE_PHONE_NUMBER);
        assertThat(testSpe.getSpeOrgVerified()).isEqualTo(UPDATED_SPE_ORG_VERIFIED);
        assertThat(testSpe.getCreatedDate()).isEqualTo(UPDATED_CREATED_DATE);
        assertThat(testSpe.getModifiedDate()).isEqualTo(UPDATED_MODIFIED_DATE);
    }

    @Test
    @Transactional
    void putNonExistingSpe() throws Exception {
        int databaseSizeBeforeUpdate = speRepository.findAll().size();
        spe.setId(count.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restSpeMockMvc
            .perform(
                put(ENTITY_API_URL_ID, spe.getId()).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(spe))
            )
            .andExpect(status().isBadRequest());

        // Validate the Spe in the database
        List<Spe> speList = speRepository.findAll();
        assertThat(speList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchSpe() throws Exception {
        int databaseSizeBeforeUpdate = speRepository.findAll().size();
        spe.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restSpeMockMvc
            .perform(
                put(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(spe))
            )
            .andExpect(status().isBadRequest());

        // Validate the Spe in the database
        List<Spe> speList = speRepository.findAll();
        assertThat(speList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamSpe() throws Exception {
        int databaseSizeBeforeUpdate = speRepository.findAll().size();
        spe.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restSpeMockMvc
            .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(spe)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the Spe in the database
        List<Spe> speList = speRepository.findAll();
        assertThat(speList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateSpeWithPatch() throws Exception {
        // Initialize the database
        speRepository.saveAndFlush(spe);

        int databaseSizeBeforeUpdate = speRepository.findAll().size();

        // Update the spe using partial update
        Spe partialUpdatedSpe = new Spe();
        partialUpdatedSpe.setId(spe.getId());

        partialUpdatedSpe.createdDate(UPDATED_CREATED_DATE);

        restSpeMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedSpe.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedSpe))
            )
            .andExpect(status().isOk());

        // Validate the Spe in the database
        List<Spe> speList = speRepository.findAll();
        assertThat(speList).hasSize(databaseSizeBeforeUpdate);
        Spe testSpe = speList.get(speList.size() - 1);
        assertThat(testSpe.getSpeFirstName()).isEqualTo(DEFAULT_SPE_FIRST_NAME);
        assertThat(testSpe.getSpeLastName()).isEqualTo(DEFAULT_SPE_LAST_NAME);
        assertThat(testSpe.getSpeEmailId()).isEqualTo(DEFAULT_SPE_EMAIL_ID);
        assertThat(testSpe.getSpePhoneNumber()).isEqualTo(DEFAULT_SPE_PHONE_NUMBER);
        assertThat(testSpe.getSpeOrgVerified()).isEqualTo(DEFAULT_SPE_ORG_VERIFIED);
        assertThat(testSpe.getCreatedDate()).isEqualTo(UPDATED_CREATED_DATE);
        assertThat(testSpe.getModifiedDate()).isEqualTo(DEFAULT_MODIFIED_DATE);
    }

    @Test
    @Transactional
    void fullUpdateSpeWithPatch() throws Exception {
        // Initialize the database
        speRepository.saveAndFlush(spe);

        int databaseSizeBeforeUpdate = speRepository.findAll().size();

        // Update the spe using partial update
        Spe partialUpdatedSpe = new Spe();
        partialUpdatedSpe.setId(spe.getId());

        partialUpdatedSpe
            .speFirstName(UPDATED_SPE_FIRST_NAME)
            .speLastName(UPDATED_SPE_LAST_NAME)
            .speEmailId(UPDATED_SPE_EMAIL_ID)
            .spePhoneNumber(UPDATED_SPE_PHONE_NUMBER)
            .speOrgVerified(UPDATED_SPE_ORG_VERIFIED)
            .createdDate(UPDATED_CREATED_DATE)
            .modifiedDate(UPDATED_MODIFIED_DATE);

        restSpeMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedSpe.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedSpe))
            )
            .andExpect(status().isOk());

        // Validate the Spe in the database
        List<Spe> speList = speRepository.findAll();
        assertThat(speList).hasSize(databaseSizeBeforeUpdate);
        Spe testSpe = speList.get(speList.size() - 1);
        assertThat(testSpe.getSpeFirstName()).isEqualTo(UPDATED_SPE_FIRST_NAME);
        assertThat(testSpe.getSpeLastName()).isEqualTo(UPDATED_SPE_LAST_NAME);
        assertThat(testSpe.getSpeEmailId()).isEqualTo(UPDATED_SPE_EMAIL_ID);
        assertThat(testSpe.getSpePhoneNumber()).isEqualTo(UPDATED_SPE_PHONE_NUMBER);
        assertThat(testSpe.getSpeOrgVerified()).isEqualTo(UPDATED_SPE_ORG_VERIFIED);
        assertThat(testSpe.getCreatedDate()).isEqualTo(UPDATED_CREATED_DATE);
        assertThat(testSpe.getModifiedDate()).isEqualTo(UPDATED_MODIFIED_DATE);
    }

    @Test
    @Transactional
    void patchNonExistingSpe() throws Exception {
        int databaseSizeBeforeUpdate = speRepository.findAll().size();
        spe.setId(count.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restSpeMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, spe.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(spe))
            )
            .andExpect(status().isBadRequest());

        // Validate the Spe in the database
        List<Spe> speList = speRepository.findAll();
        assertThat(speList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchSpe() throws Exception {
        int databaseSizeBeforeUpdate = speRepository.findAll().size();
        spe.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restSpeMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(spe))
            )
            .andExpect(status().isBadRequest());

        // Validate the Spe in the database
        List<Spe> speList = speRepository.findAll();
        assertThat(speList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamSpe() throws Exception {
        int databaseSizeBeforeUpdate = speRepository.findAll().size();
        spe.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restSpeMockMvc
            .perform(patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(TestUtil.convertObjectToJsonBytes(spe)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the Spe in the database
        List<Spe> speList = speRepository.findAll();
        assertThat(speList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteSpe() throws Exception {
        // Initialize the database
        speRepository.saveAndFlush(spe);

        int databaseSizeBeforeDelete = speRepository.findAll().size();

        // Delete the spe
        restSpeMockMvc.perform(delete(ENTITY_API_URL_ID, spe.getId()).accept(MediaType.APPLICATION_JSON)).andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Spe> speList = speRepository.findAll();
        assertThat(speList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
