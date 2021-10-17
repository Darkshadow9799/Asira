package com.dshaw.asira.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.dshaw.asira.IntegrationTest;
import com.dshaw.asira.domain.Org;
import com.dshaw.asira.repository.OrgRepository;
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
 * Integration tests for the {@link OrgResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class OrgResourceIT {

    private static final String DEFAULT_ORG_NAME = "AAAAAAAAAA";
    private static final String UPDATED_ORG_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_ORG_EMAIL_ID = "AAAAAAAAAA";
    private static final String UPDATED_ORG_EMAIL_ID = "BBBBBBBBBB";

    private static final Long DEFAULT_ORG_PHONE_NUM = 1L;
    private static final Long UPDATED_ORG_PHONE_NUM = 2L;

    private static final Instant DEFAULT_CREATED_DATE = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_CREATED_DATE = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final Instant DEFAULT_MODIFIED_DATE = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_MODIFIED_DATE = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final Long DEFAULT_ORG_PROJ_NUM = 1L;
    private static final Long UPDATED_ORG_PROJ_NUM = 2L;

    private static final Long DEFAULT_ORG_MEMBERS_NUM = 1L;
    private static final Long UPDATED_ORG_MEMBERS_NUM = 2L;

    private static final String ENTITY_API_URL = "/api/orgs";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private OrgRepository orgRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restOrgMockMvc;

    private Org org;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
//    public static Org createEntity(EntityManager em) {
//        Org org = new Org()
//            .orgName(DEFAULT_ORG_NAME)
//            .orgEmailId(DEFAULT_ORG_EMAIL_ID)
//            .orgPhoneNum(DEFAULT_ORG_PHONE_NUM)
//            .createdDate(DEFAULT_CREATED_DATE)
//            .modifiedDate(DEFAULT_MODIFIED_DATE)
//            .orgProjNum(DEFAULT_ORG_PROJ_NUM)
//            .orgMembersNum(DEFAULT_ORG_MEMBERS_NUM);
//        return org;
//    }
//
//    /**
//     * Create an updated entity for this test.
//     *
//     * This is a static method, as tests for other entities might also need it,
//     * if they test an entity which requires the current entity.
//     */
//    public static Org createUpdatedEntity(EntityManager em) {
//        Org org = new Org()
//            .orgName(UPDATED_ORG_NAME)
//            .orgEmailId(UPDATED_ORG_EMAIL_ID)
//            .orgPhoneNum(UPDATED_ORG_PHONE_NUM)
//            .createdDate(UPDATED_CREATED_DATE)
//            .modifiedDate(UPDATED_MODIFIED_DATE)
//            .orgProjNum(UPDATED_ORG_PROJ_NUM)
//            .orgMembersNum(UPDATED_ORG_MEMBERS_NUM);
//        return org;
//    }
//
//    @BeforeEach
//    public void initTest() {
//        org = createEntity(em);
//    }
//
//    @Test
//    @Transactional
//    void createOrg() throws Exception {
//        int databaseSizeBeforeCreate = orgRepository.findAll().size();
//        // Create the Org
//        restOrgMockMvc
//            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(org)))
//            .andExpect(status().isCreated());
//
//        // Validate the Org in the database
//        List<Org> orgList = orgRepository.findAll();
//        assertThat(orgList).hasSize(databaseSizeBeforeCreate + 1);
//        Org testOrg = orgList.get(orgList.size() - 1);
//        assertThat(testOrg.getOrgName()).isEqualTo(DEFAULT_ORG_NAME);
//        assertThat(testOrg.getOrgEmailId()).isEqualTo(DEFAULT_ORG_EMAIL_ID);
//        assertThat(testOrg.getOrgPhoneNum()).isEqualTo(DEFAULT_ORG_PHONE_NUM);
//        assertThat(testOrg.getCreatedDate()).isEqualTo(DEFAULT_CREATED_DATE);
//        assertThat(testOrg.getModifiedDate()).isEqualTo(DEFAULT_MODIFIED_DATE);
//        assertThat(testOrg.getOrgProjNum()).isEqualTo(DEFAULT_ORG_PROJ_NUM);
//        assertThat(testOrg.getOrgMembersNum()).isEqualTo(DEFAULT_ORG_MEMBERS_NUM);
//    }
//
//    @Test
//    @Transactional
//    void createOrgWithExistingId() throws Exception {
//        // Create the Org with an existing ID
//        org.setId(1L);
//
//        int databaseSizeBeforeCreate = orgRepository.findAll().size();
//
//        // An entity with an existing ID cannot be created, so this API call must fail
//        restOrgMockMvc
//            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(org)))
//            .andExpect(status().isBadRequest());
//
//        // Validate the Org in the database
//        List<Org> orgList = orgRepository.findAll();
//        assertThat(orgList).hasSize(databaseSizeBeforeCreate);
//    }
//
//    @Test
//    @Transactional
//    void checkOrgNameIsRequired() throws Exception {
//        int databaseSizeBeforeTest = orgRepository.findAll().size();
//        // set the field null
//        org.setOrgName(null);
//
//        // Create the Org, which fails.
//
//        restOrgMockMvc
//            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(org)))
//            .andExpect(status().isBadRequest());
//
//        List<Org> orgList = orgRepository.findAll();
//        assertThat(orgList).hasSize(databaseSizeBeforeTest);
//    }
//
//    @Test
//    @Transactional
//    void getAllOrgs() throws Exception {
//        // Initialize the database
//        orgRepository.saveAndFlush(org);
//
//        // Get all the orgList
//        restOrgMockMvc
//            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
//            .andExpect(status().isOk())
//            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
//            .andExpect(jsonPath("$.[*].id").value(hasItem(org.getId().intValue())))
//            .andExpect(jsonPath("$.[*].orgName").value(hasItem(DEFAULT_ORG_NAME)))
//            .andExpect(jsonPath("$.[*].orgEmailId").value(hasItem(DEFAULT_ORG_EMAIL_ID)))
//            .andExpect(jsonPath("$.[*].orgPhoneNum").value(hasItem(DEFAULT_ORG_PHONE_NUM.intValue())))
//            .andExpect(jsonPath("$.[*].createdDate").value(hasItem(DEFAULT_CREATED_DATE.toString())))
//            .andExpect(jsonPath("$.[*].modifiedDate").value(hasItem(DEFAULT_MODIFIED_DATE.toString())))
//            .andExpect(jsonPath("$.[*].orgProjNum").value(hasItem(DEFAULT_ORG_PROJ_NUM.intValue())))
//            .andExpect(jsonPath("$.[*].orgMembersNum").value(hasItem(DEFAULT_ORG_MEMBERS_NUM.intValue())));
//    }
//
//    @Test
//    @Transactional
//    void getOrg() throws Exception {
//        // Initialize the database
//        orgRepository.saveAndFlush(org);
//
//        // Get the org
//        restOrgMockMvc
//            .perform(get(ENTITY_API_URL_ID, org.getId()))
//            .andExpect(status().isOk())
//            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
//            .andExpect(jsonPath("$.id").value(org.getId().intValue()))
//            .andExpect(jsonPath("$.orgName").value(DEFAULT_ORG_NAME))
//            .andExpect(jsonPath("$.orgEmailId").value(DEFAULT_ORG_EMAIL_ID))
//            .andExpect(jsonPath("$.orgPhoneNum").value(DEFAULT_ORG_PHONE_NUM.intValue()))
//            .andExpect(jsonPath("$.createdDate").value(DEFAULT_CREATED_DATE.toString()))
//            .andExpect(jsonPath("$.modifiedDate").value(DEFAULT_MODIFIED_DATE.toString()))
//            .andExpect(jsonPath("$.orgProjNum").value(DEFAULT_ORG_PROJ_NUM.intValue()))
//            .andExpect(jsonPath("$.orgMembersNum").value(DEFAULT_ORG_MEMBERS_NUM.intValue()));
//    }
//
//    @Test
//    @Transactional
//    void getNonExistingOrg() throws Exception {
//        // Get the org
//        restOrgMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
//    }
//
//    @Test
//    @Transactional
//    void putNewOrg() throws Exception {
//        // Initialize the database
//        orgRepository.saveAndFlush(org);
//
//        int databaseSizeBeforeUpdate = orgRepository.findAll().size();
//
//        // Update the org
//        Org updatedOrg = orgRepository.findById(org.getId()).get();
//        // Disconnect from session so that the updates on updatedOrg are not directly saved in db
//        em.detach(updatedOrg);
//        updatedOrg
//            .orgName(UPDATED_ORG_NAME)
//            .orgEmailId(UPDATED_ORG_EMAIL_ID)
//            .orgPhoneNum(UPDATED_ORG_PHONE_NUM)
//            .createdDate(UPDATED_CREATED_DATE)
//            .modifiedDate(UPDATED_MODIFIED_DATE)
//            .orgProjNum(UPDATED_ORG_PROJ_NUM)
//            .orgMembersNum(UPDATED_ORG_MEMBERS_NUM);
//
//        restOrgMockMvc
//            .perform(
//                put(ENTITY_API_URL_ID, updatedOrg.getId())
//                    .contentType(MediaType.APPLICATION_JSON)
//                    .content(TestUtil.convertObjectToJsonBytes(updatedOrg))
//            )
//            .andExpect(status().isOk());
//
//        // Validate the Org in the database
//        List<Org> orgList = orgRepository.findAll();
//        assertThat(orgList).hasSize(databaseSizeBeforeUpdate);
//        Org testOrg = orgList.get(orgList.size() - 1);
//        assertThat(testOrg.getOrgName()).isEqualTo(UPDATED_ORG_NAME);
//        assertThat(testOrg.getOrgEmailId()).isEqualTo(UPDATED_ORG_EMAIL_ID);
//        assertThat(testOrg.getOrgPhoneNum()).isEqualTo(UPDATED_ORG_PHONE_NUM);
//        assertThat(testOrg.getCreatedDate()).isEqualTo(UPDATED_CREATED_DATE);
//        assertThat(testOrg.getModifiedDate()).isEqualTo(UPDATED_MODIFIED_DATE);
//        assertThat(testOrg.getOrgProjNum()).isEqualTo(UPDATED_ORG_PROJ_NUM);
//        assertThat(testOrg.getOrgMembersNum()).isEqualTo(UPDATED_ORG_MEMBERS_NUM);
//    }
//
//    @Test
//    @Transactional
//    void putNonExistingOrg() throws Exception {
//        int databaseSizeBeforeUpdate = orgRepository.findAll().size();
//        org.setId(count.incrementAndGet());
//
//        // If the entity doesn't have an ID, it will throw BadRequestAlertException
//        restOrgMockMvc
//            .perform(
//                put(ENTITY_API_URL_ID, org.getId()).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(org))
//            )
//            .andExpect(status().isBadRequest());
//
//        // Validate the Org in the database
//        List<Org> orgList = orgRepository.findAll();
//        assertThat(orgList).hasSize(databaseSizeBeforeUpdate);
//    }
//
//    @Test
//    @Transactional
//    void putWithIdMismatchOrg() throws Exception {
//        int databaseSizeBeforeUpdate = orgRepository.findAll().size();
//        org.setId(count.incrementAndGet());
//
//        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
//        restOrgMockMvc
//            .perform(
//                put(ENTITY_API_URL_ID, count.incrementAndGet())
//                    .contentType(MediaType.APPLICATION_JSON)
//                    .content(TestUtil.convertObjectToJsonBytes(org))
//            )
//            .andExpect(status().isBadRequest());
//
//        // Validate the Org in the database
//        List<Org> orgList = orgRepository.findAll();
//        assertThat(orgList).hasSize(databaseSizeBeforeUpdate);
//    }
//
//    @Test
//    @Transactional
//    void putWithMissingIdPathParamOrg() throws Exception {
//        int databaseSizeBeforeUpdate = orgRepository.findAll().size();
//        org.setId(count.incrementAndGet());
//
//        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
//        restOrgMockMvc
//            .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(org)))
//            .andExpect(status().isMethodNotAllowed());
//
//        // Validate the Org in the database
//        List<Org> orgList = orgRepository.findAll();
//        assertThat(orgList).hasSize(databaseSizeBeforeUpdate);
//    }
//
//    @Test
//    @Transactional
//    void partialUpdateOrgWithPatch() throws Exception {
//        // Initialize the database
//        orgRepository.saveAndFlush(org);
//
//        int databaseSizeBeforeUpdate = orgRepository.findAll().size();
//
//        // Update the org using partial update
//        Org partialUpdatedOrg = new Org();
//        partialUpdatedOrg.setId(org.getId());
//
//        partialUpdatedOrg.orgName(UPDATED_ORG_NAME).orgPhoneNum(UPDATED_ORG_PHONE_NUM).createdDate(UPDATED_CREATED_DATE);
//
//        restOrgMockMvc
//            .perform(
//                patch(ENTITY_API_URL_ID, partialUpdatedOrg.getId())
//                    .contentType("application/merge-patch+json")
//                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedOrg))
//            )
//            .andExpect(status().isOk());
//
//        // Validate the Org in the database
//        List<Org> orgList = orgRepository.findAll();
//        assertThat(orgList).hasSize(databaseSizeBeforeUpdate);
//        Org testOrg = orgList.get(orgList.size() - 1);
//        assertThat(testOrg.getOrgName()).isEqualTo(UPDATED_ORG_NAME);
//        assertThat(testOrg.getOrgEmailId()).isEqualTo(DEFAULT_ORG_EMAIL_ID);
//        assertThat(testOrg.getOrgPhoneNum()).isEqualTo(UPDATED_ORG_PHONE_NUM);
//        assertThat(testOrg.getCreatedDate()).isEqualTo(UPDATED_CREATED_DATE);
//        assertThat(testOrg.getModifiedDate()).isEqualTo(DEFAULT_MODIFIED_DATE);
//        assertThat(testOrg.getOrgProjNum()).isEqualTo(DEFAULT_ORG_PROJ_NUM);
//        assertThat(testOrg.getOrgMembersNum()).isEqualTo(DEFAULT_ORG_MEMBERS_NUM);
//    }
//
//    @Test
//    @Transactional
//    void fullUpdateOrgWithPatch() throws Exception {
//        // Initialize the database
//        orgRepository.saveAndFlush(org);
//
//        int databaseSizeBeforeUpdate = orgRepository.findAll().size();
//
//        // Update the org using partial update
//        Org partialUpdatedOrg = new Org();
//        partialUpdatedOrg.setId(org.getId());
//
//        partialUpdatedOrg
//            .orgName(UPDATED_ORG_NAME)
//            .orgEmailId(UPDATED_ORG_EMAIL_ID)
//            .orgPhoneNum(UPDATED_ORG_PHONE_NUM)
//            .createdDate(UPDATED_CREATED_DATE)
//            .modifiedDate(UPDATED_MODIFIED_DATE)
//            .orgProjNum(UPDATED_ORG_PROJ_NUM)
//            .orgMembersNum(UPDATED_ORG_MEMBERS_NUM);
//
//        restOrgMockMvc
//            .perform(
//                patch(ENTITY_API_URL_ID, partialUpdatedOrg.getId())
//                    .contentType("application/merge-patch+json")
//                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedOrg))
//            )
//            .andExpect(status().isOk());
//
//        // Validate the Org in the database
//        List<Org> orgList = orgRepository.findAll();
//        assertThat(orgList).hasSize(databaseSizeBeforeUpdate);
//        Org testOrg = orgList.get(orgList.size() - 1);
//        assertThat(testOrg.getOrgName()).isEqualTo(UPDATED_ORG_NAME);
//        assertThat(testOrg.getOrgEmailId()).isEqualTo(UPDATED_ORG_EMAIL_ID);
//        assertThat(testOrg.getOrgPhoneNum()).isEqualTo(UPDATED_ORG_PHONE_NUM);
//        assertThat(testOrg.getCreatedDate()).isEqualTo(UPDATED_CREATED_DATE);
//        assertThat(testOrg.getModifiedDate()).isEqualTo(UPDATED_MODIFIED_DATE);
//        assertThat(testOrg.getOrgProjNum()).isEqualTo(UPDATED_ORG_PROJ_NUM);
//        assertThat(testOrg.getOrgMembersNum()).isEqualTo(UPDATED_ORG_MEMBERS_NUM);
//    }
//
//    @Test
//    @Transactional
//    void patchNonExistingOrg() throws Exception {
//        int databaseSizeBeforeUpdate = orgRepository.findAll().size();
//        org.setId(count.incrementAndGet());
//
//        // If the entity doesn't have an ID, it will throw BadRequestAlertException
//        restOrgMockMvc
//            .perform(
//                patch(ENTITY_API_URL_ID, org.getId())
//                    .contentType("application/merge-patch+json")
//                    .content(TestUtil.convertObjectToJsonBytes(org))
//            )
//            .andExpect(status().isBadRequest());
//
//        // Validate the Org in the database
//        List<Org> orgList = orgRepository.findAll();
//        assertThat(orgList).hasSize(databaseSizeBeforeUpdate);
//    }
//
//    @Test
//    @Transactional
//    void patchWithIdMismatchOrg() throws Exception {
//        int databaseSizeBeforeUpdate = orgRepository.findAll().size();
//        org.setId(count.incrementAndGet());
//
//        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
//        restOrgMockMvc
//            .perform(
//                patch(ENTITY_API_URL_ID, count.incrementAndGet())
//                    .contentType("application/merge-patch+json")
//                    .content(TestUtil.convertObjectToJsonBytes(org))
//            )
//            .andExpect(status().isBadRequest());
//
//        // Validate the Org in the database
//        List<Org> orgList = orgRepository.findAll();
//        assertThat(orgList).hasSize(databaseSizeBeforeUpdate);
//    }
//
//    @Test
//    @Transactional
//    void patchWithMissingIdPathParamOrg() throws Exception {
//        int databaseSizeBeforeUpdate = orgRepository.findAll().size();
//        org.setId(count.incrementAndGet());
//
//        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
//        restOrgMockMvc
//            .perform(patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(TestUtil.convertObjectToJsonBytes(org)))
//            .andExpect(status().isMethodNotAllowed());
//
//        // Validate the Org in the database
//        List<Org> orgList = orgRepository.findAll();
//        assertThat(orgList).hasSize(databaseSizeBeforeUpdate);
//    }
//
//    @Test
//    @Transactional
//    void deleteOrg() throws Exception {
//        // Initialize the database
//        orgRepository.saveAndFlush(org);
//
//        int databaseSizeBeforeDelete = orgRepository.findAll().size();
//
//        // Delete the org
//        restOrgMockMvc.perform(delete(ENTITY_API_URL_ID, org.getId()).accept(MediaType.APPLICATION_JSON)).andExpect(status().isNoContent());
//
//        // Validate the database contains one less item
//        List<Org> orgList = orgRepository.findAll();
//        assertThat(orgList).hasSize(databaseSizeBeforeDelete - 1);
//    }
}
