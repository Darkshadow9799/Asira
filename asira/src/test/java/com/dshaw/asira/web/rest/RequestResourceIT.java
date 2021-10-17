package com.dshaw.asira.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.dshaw.asira.IntegrationTest;
import com.dshaw.asira.domain.Request;
import com.dshaw.asira.repository.RequestRepository;
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
 * Integration tests for the {@link RequestResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class RequestResourceIT {

    private static final Long DEFAULT_R_FROM = 1L;
    private static final Long UPDATED_R_FROM = 2L;

    private static final Long DEFAULT_R_TO = 1L;
    private static final Long UPDATED_R_TO = 2L;

    private static final Boolean DEFAULT_R_PENDING_STATE = false;
    private static final Boolean UPDATED_R_PENDING_STATE = true;

    private static final Boolean DEFAULT_R_ACCEPTED = false;
    private static final Boolean UPDATED_R_ACCEPTED = true;

    private static final Boolean DEFAULT_R_REJECTED = false;
    private static final Boolean UPDATED_R_REJECTED = true;

    private static final Boolean DEFAULT_SPE_NOTIFIED = false;
    private static final Boolean UPDATED_SPE_NOTIFIED = true;

    private static final Boolean DEFAULT_ORG_NOTIFIED = false;
    private static final Boolean UPDATED_ORG_NOTIFIED = true;

    private static final Instant DEFAULT_CREATED_DATE = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_CREATED_DATE = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final Instant DEFAULT_MODIFIED_DATE = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_MODIFIED_DATE = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final String ENTITY_API_URL = "/api/requests";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private RequestRepository requestRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restRequestMockMvc;

    private Request request;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
//    public static Request createEntity(EntityManager em) {
//        Request request = new Request()
//            .rFrom(DEFAULT_R_FROM)
//            .rTo(DEFAULT_R_TO)
//            .rPendingState(DEFAULT_R_PENDING_STATE)
//            .rAccepted(DEFAULT_R_ACCEPTED)
//            .rRejected(DEFAULT_R_REJECTED)
//            .speNotified(DEFAULT_SPE_NOTIFIED)
//            .orgNotified(DEFAULT_ORG_NOTIFIED)
//            .createdDate(DEFAULT_CREATED_DATE)
//            .modifiedDate(DEFAULT_MODIFIED_DATE);
//        return request;
//    }
//
//    /**
//     * Create an updated entity for this test.
//     *
//     * This is a static method, as tests for other entities might also need it,
//     * if they test an entity which requires the current entity.
//     */
//    public static Request createUpdatedEntity(EntityManager em) {
//        Request request = new Request()
//            .rFrom(UPDATED_R_FROM)
//            .rTo(UPDATED_R_TO)
//            .rPendingState(UPDATED_R_PENDING_STATE)
//            .rAccepted(UPDATED_R_ACCEPTED)
//            .rRejected(UPDATED_R_REJECTED)
//            .speNotified(UPDATED_SPE_NOTIFIED)
//            .orgNotified(UPDATED_ORG_NOTIFIED)
//            .createdDate(UPDATED_CREATED_DATE)
//            .modifiedDate(UPDATED_MODIFIED_DATE);
//        return request;
//    }
//
//    @BeforeEach
//    public void initTest() {
//        request = createEntity(em);
//    }
//
//    @Test
//    @Transactional
//    void createRequest() throws Exception {
//        int databaseSizeBeforeCreate = requestRepository.findAll().size();
//        // Create the Request
//        restRequestMockMvc
//            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(request)))
//            .andExpect(status().isCreated());
//
//        // Validate the Request in the database
//        List<Request> requestList = requestRepository.findAll();
//        assertThat(requestList).hasSize(databaseSizeBeforeCreate + 1);
//        Request testRequest = requestList.get(requestList.size() - 1);
//        assertThat(testRequest.getrFrom()).isEqualTo(DEFAULT_R_FROM);
//        assertThat(testRequest.getrTo()).isEqualTo(DEFAULT_R_TO);
//        assertThat(testRequest.getrPendingState()).isEqualTo(DEFAULT_R_PENDING_STATE);
//        assertThat(testRequest.getrAccepted()).isEqualTo(DEFAULT_R_ACCEPTED);
//        assertThat(testRequest.getrRejected()).isEqualTo(DEFAULT_R_REJECTED);
//        assertThat(testRequest.getSpeNotified()).isEqualTo(DEFAULT_SPE_NOTIFIED);
//        assertThat(testRequest.getOrgNotified()).isEqualTo(DEFAULT_ORG_NOTIFIED);
//        assertThat(testRequest.getCreatedDate()).isEqualTo(DEFAULT_CREATED_DATE);
//        assertThat(testRequest.getModifiedDate()).isEqualTo(DEFAULT_MODIFIED_DATE);
//    }
//
//    @Test
//    @Transactional
//    void createRequestWithExistingId() throws Exception {
//        // Create the Request with an existing ID
//        request.setId(1L);
//
//        int databaseSizeBeforeCreate = requestRepository.findAll().size();
//
//        // An entity with an existing ID cannot be created, so this API call must fail
//        restRequestMockMvc
//            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(request)))
//            .andExpect(status().isBadRequest());
//
//        // Validate the Request in the database
//        List<Request> requestList = requestRepository.findAll();
//        assertThat(requestList).hasSize(databaseSizeBeforeCreate);
//    }
//
//    @Test
//    @Transactional
//    void getAllRequests() throws Exception {
//        // Initialize the database
//        requestRepository.saveAndFlush(request);
//
//        // Get all the requestList
//        restRequestMockMvc
//            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
//            .andExpect(status().isOk())
//            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
//            .andExpect(jsonPath("$.[*].id").value(hasItem(request.getId().intValue())))
//            .andExpect(jsonPath("$.[*].rFrom").value(hasItem(DEFAULT_R_FROM.intValue())))
//            .andExpect(jsonPath("$.[*].rTo").value(hasItem(DEFAULT_R_TO.intValue())))
//            .andExpect(jsonPath("$.[*].rPendingState").value(hasItem(DEFAULT_R_PENDING_STATE.booleanValue())))
//            .andExpect(jsonPath("$.[*].rAccepted").value(hasItem(DEFAULT_R_ACCEPTED.booleanValue())))
//            .andExpect(jsonPath("$.[*].rRejected").value(hasItem(DEFAULT_R_REJECTED.booleanValue())))
//            .andExpect(jsonPath("$.[*].speNotified").value(hasItem(DEFAULT_SPE_NOTIFIED.booleanValue())))
//            .andExpect(jsonPath("$.[*].orgNotified").value(hasItem(DEFAULT_ORG_NOTIFIED.booleanValue())))
//            .andExpect(jsonPath("$.[*].createdDate").value(hasItem(DEFAULT_CREATED_DATE.toString())))
//            .andExpect(jsonPath("$.[*].modifiedDate").value(hasItem(DEFAULT_MODIFIED_DATE.toString())));
//    }
//
//    @Test
//    @Transactional
//    void getRequest() throws Exception {
//        // Initialize the database
//        requestRepository.saveAndFlush(request);
//
//        // Get the request
//        restRequestMockMvc
//            .perform(get(ENTITY_API_URL_ID, request.getId()))
//            .andExpect(status().isOk())
//            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
//            .andExpect(jsonPath("$.id").value(request.getId().intValue()))
//            .andExpect(jsonPath("$.rFrom").value(DEFAULT_R_FROM.intValue()))
//            .andExpect(jsonPath("$.rTo").value(DEFAULT_R_TO.intValue()))
//            .andExpect(jsonPath("$.rPendingState").value(DEFAULT_R_PENDING_STATE.booleanValue()))
//            .andExpect(jsonPath("$.rAccepted").value(DEFAULT_R_ACCEPTED.booleanValue()))
//            .andExpect(jsonPath("$.rRejected").value(DEFAULT_R_REJECTED.booleanValue()))
//            .andExpect(jsonPath("$.speNotified").value(DEFAULT_SPE_NOTIFIED.booleanValue()))
//            .andExpect(jsonPath("$.orgNotified").value(DEFAULT_ORG_NOTIFIED.booleanValue()))
//            .andExpect(jsonPath("$.createdDate").value(DEFAULT_CREATED_DATE.toString()))
//            .andExpect(jsonPath("$.modifiedDate").value(DEFAULT_MODIFIED_DATE.toString()));
//    }
//
//    @Test
//    @Transactional
//    void getNonExistingRequest() throws Exception {
//        // Get the request
//        restRequestMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
//    }
//
//    @Test
//    @Transactional
//    void putNewRequest() throws Exception {
//        // Initialize the database
//        requestRepository.saveAndFlush(request);
//
//        int databaseSizeBeforeUpdate = requestRepository.findAll().size();
//
//        // Update the request
//        Request updatedRequest = requestRepository.findById(request.getId()).get();
//        // Disconnect from session so that the updates on updatedRequest are not directly saved in db
//        em.detach(updatedRequest);
//        updatedRequest
//            .rFrom(UPDATED_R_FROM)
//            .rTo(UPDATED_R_TO)
//            .rPendingState(UPDATED_R_PENDING_STATE)
//            .rAccepted(UPDATED_R_ACCEPTED)
//            .rRejected(UPDATED_R_REJECTED)
//            .speNotified(UPDATED_SPE_NOTIFIED)
//            .orgNotified(UPDATED_ORG_NOTIFIED)
//            .createdDate(UPDATED_CREATED_DATE)
//            .modifiedDate(UPDATED_MODIFIED_DATE);
//
//        restRequestMockMvc
//            .perform(
//                put(ENTITY_API_URL_ID, updatedRequest.getId())
//                    .contentType(MediaType.APPLICATION_JSON)
//                    .content(TestUtil.convertObjectToJsonBytes(updatedRequest))
//            )
//            .andExpect(status().isOk());
//
//        // Validate the Request in the database
//        List<Request> requestList = requestRepository.findAll();
//        assertThat(requestList).hasSize(databaseSizeBeforeUpdate);
//        Request testRequest = requestList.get(requestList.size() - 1);
//        assertThat(testRequest.getrFrom()).isEqualTo(UPDATED_R_FROM);
//        assertThat(testRequest.getrTo()).isEqualTo(UPDATED_R_TO);
//        assertThat(testRequest.getrPendingState()).isEqualTo(UPDATED_R_PENDING_STATE);
//        assertThat(testRequest.getrAccepted()).isEqualTo(UPDATED_R_ACCEPTED);
//        assertThat(testRequest.getrRejected()).isEqualTo(UPDATED_R_REJECTED);
//        assertThat(testRequest.getSpeNotified()).isEqualTo(UPDATED_SPE_NOTIFIED);
//        assertThat(testRequest.getOrgNotified()).isEqualTo(UPDATED_ORG_NOTIFIED);
//        assertThat(testRequest.getCreatedDate()).isEqualTo(UPDATED_CREATED_DATE);
//        assertThat(testRequest.getModifiedDate()).isEqualTo(UPDATED_MODIFIED_DATE);
//    }
//
//    @Test
//    @Transactional
//    void putNonExistingRequest() throws Exception {
//        int databaseSizeBeforeUpdate = requestRepository.findAll().size();
//        request.setId(count.incrementAndGet());
//
//        // If the entity doesn't have an ID, it will throw BadRequestAlertException
//        restRequestMockMvc
//            .perform(
//                put(ENTITY_API_URL_ID, request.getId())
//                    .contentType(MediaType.APPLICATION_JSON)
//                    .content(TestUtil.convertObjectToJsonBytes(request))
//            )
//            .andExpect(status().isBadRequest());
//
//        // Validate the Request in the database
//        List<Request> requestList = requestRepository.findAll();
//        assertThat(requestList).hasSize(databaseSizeBeforeUpdate);
//    }
//
//    @Test
//    @Transactional
//    void putWithIdMismatchRequest() throws Exception {
//        int databaseSizeBeforeUpdate = requestRepository.findAll().size();
//        request.setId(count.incrementAndGet());
//
//        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
//        restRequestMockMvc
//            .perform(
//                put(ENTITY_API_URL_ID, count.incrementAndGet())
//                    .contentType(MediaType.APPLICATION_JSON)
//                    .content(TestUtil.convertObjectToJsonBytes(request))
//            )
//            .andExpect(status().isBadRequest());
//
//        // Validate the Request in the database
//        List<Request> requestList = requestRepository.findAll();
//        assertThat(requestList).hasSize(databaseSizeBeforeUpdate);
//    }
//
//    @Test
//    @Transactional
//    void putWithMissingIdPathParamRequest() throws Exception {
//        int databaseSizeBeforeUpdate = requestRepository.findAll().size();
//        request.setId(count.incrementAndGet());
//
//        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
//        restRequestMockMvc
//            .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(request)))
//            .andExpect(status().isMethodNotAllowed());
//
//        // Validate the Request in the database
//        List<Request> requestList = requestRepository.findAll();
//        assertThat(requestList).hasSize(databaseSizeBeforeUpdate);
//    }
//
//    @Test
//    @Transactional
//    void partialUpdateRequestWithPatch() throws Exception {
//        // Initialize the database
//        requestRepository.saveAndFlush(request);
//
//        int databaseSizeBeforeUpdate = requestRepository.findAll().size();
//
//        // Update the request using partial update
//        Request partialUpdatedRequest = new Request();
//        partialUpdatedRequest.setId(request.getId());
//
//        partialUpdatedRequest
//            .rFrom(UPDATED_R_FROM)
//            .rTo(UPDATED_R_TO)
//            .rAccepted(UPDATED_R_ACCEPTED)
//            .rRejected(UPDATED_R_REJECTED)
//            .speNotified(UPDATED_SPE_NOTIFIED)
//            .createdDate(UPDATED_CREATED_DATE)
//            .modifiedDate(UPDATED_MODIFIED_DATE);
//
//        restRequestMockMvc
//            .perform(
//                patch(ENTITY_API_URL_ID, partialUpdatedRequest.getId())
//                    .contentType("application/merge-patch+json")
//                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedRequest))
//            )
//            .andExpect(status().isOk());
//
//        // Validate the Request in the database
//        List<Request> requestList = requestRepository.findAll();
//        assertThat(requestList).hasSize(databaseSizeBeforeUpdate);
//        Request testRequest = requestList.get(requestList.size() - 1);
//        assertThat(testRequest.getrFrom()).isEqualTo(UPDATED_R_FROM);
//        assertThat(testRequest.getrTo()).isEqualTo(UPDATED_R_TO);
//        assertThat(testRequest.getrPendingState()).isEqualTo(DEFAULT_R_PENDING_STATE);
//        assertThat(testRequest.getrAccepted()).isEqualTo(UPDATED_R_ACCEPTED);
//        assertThat(testRequest.getrRejected()).isEqualTo(UPDATED_R_REJECTED);
//        assertThat(testRequest.getSpeNotified()).isEqualTo(UPDATED_SPE_NOTIFIED);
//        assertThat(testRequest.getOrgNotified()).isEqualTo(DEFAULT_ORG_NOTIFIED);
//        assertThat(testRequest.getCreatedDate()).isEqualTo(UPDATED_CREATED_DATE);
//        assertThat(testRequest.getModifiedDate()).isEqualTo(UPDATED_MODIFIED_DATE);
//    }
//
//    @Test
//    @Transactional
//    void fullUpdateRequestWithPatch() throws Exception {
//        // Initialize the database
//        requestRepository.saveAndFlush(request);
//
//        int databaseSizeBeforeUpdate = requestRepository.findAll().size();
//
//        // Update the request using partial update
//        Request partialUpdatedRequest = new Request();
//        partialUpdatedRequest.setId(request.getId());
//
//        partialUpdatedRequest
//            .rFrom(UPDATED_R_FROM)
//            .rTo(UPDATED_R_TO)
//            .rPendingState(UPDATED_R_PENDING_STATE)
//            .rAccepted(UPDATED_R_ACCEPTED)
//            .rRejected(UPDATED_R_REJECTED)
//            .speNotified(UPDATED_SPE_NOTIFIED)
//            .orgNotified(UPDATED_ORG_NOTIFIED)
//            .createdDate(UPDATED_CREATED_DATE)
//            .modifiedDate(UPDATED_MODIFIED_DATE);
//
//        restRequestMockMvc
//            .perform(
//                patch(ENTITY_API_URL_ID, partialUpdatedRequest.getId())
//                    .contentType("application/merge-patch+json")
//                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedRequest))
//            )
//            .andExpect(status().isOk());
//
//        // Validate the Request in the database
//        List<Request> requestList = requestRepository.findAll();
//        assertThat(requestList).hasSize(databaseSizeBeforeUpdate);
//        Request testRequest = requestList.get(requestList.size() - 1);
//        assertThat(testRequest.getrFrom()).isEqualTo(UPDATED_R_FROM);
//        assertThat(testRequest.getrTo()).isEqualTo(UPDATED_R_TO);
//        assertThat(testRequest.getrPendingState()).isEqualTo(UPDATED_R_PENDING_STATE);
//        assertThat(testRequest.getrAccepted()).isEqualTo(UPDATED_R_ACCEPTED);
//        assertThat(testRequest.getrRejected()).isEqualTo(UPDATED_R_REJECTED);
//        assertThat(testRequest.getSpeNotified()).isEqualTo(UPDATED_SPE_NOTIFIED);
//        assertThat(testRequest.getOrgNotified()).isEqualTo(UPDATED_ORG_NOTIFIED);
//        assertThat(testRequest.getCreatedDate()).isEqualTo(UPDATED_CREATED_DATE);
//        assertThat(testRequest.getModifiedDate()).isEqualTo(UPDATED_MODIFIED_DATE);
//    }
//
//    @Test
//    @Transactional
//    void patchNonExistingRequest() throws Exception {
//        int databaseSizeBeforeUpdate = requestRepository.findAll().size();
//        request.setId(count.incrementAndGet());
//
//        // If the entity doesn't have an ID, it will throw BadRequestAlertException
//        restRequestMockMvc
//            .perform(
//                patch(ENTITY_API_URL_ID, request.getId())
//                    .contentType("application/merge-patch+json")
//                    .content(TestUtil.convertObjectToJsonBytes(request))
//            )
//            .andExpect(status().isBadRequest());
//
//        // Validate the Request in the database
//        List<Request> requestList = requestRepository.findAll();
//        assertThat(requestList).hasSize(databaseSizeBeforeUpdate);
//    }
//
//    @Test
//    @Transactional
//    void patchWithIdMismatchRequest() throws Exception {
//        int databaseSizeBeforeUpdate = requestRepository.findAll().size();
//        request.setId(count.incrementAndGet());
//
//        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
//        restRequestMockMvc
//            .perform(
//                patch(ENTITY_API_URL_ID, count.incrementAndGet())
//                    .contentType("application/merge-patch+json")
//                    .content(TestUtil.convertObjectToJsonBytes(request))
//            )
//            .andExpect(status().isBadRequest());
//
//        // Validate the Request in the database
//        List<Request> requestList = requestRepository.findAll();
//        assertThat(requestList).hasSize(databaseSizeBeforeUpdate);
//    }
//
//    @Test
//    @Transactional
//    void patchWithMissingIdPathParamRequest() throws Exception {
//        int databaseSizeBeforeUpdate = requestRepository.findAll().size();
//        request.setId(count.incrementAndGet());
//
//        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
//        restRequestMockMvc
//            .perform(patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(TestUtil.convertObjectToJsonBytes(request)))
//            .andExpect(status().isMethodNotAllowed());
//
//        // Validate the Request in the database
//        List<Request> requestList = requestRepository.findAll();
//        assertThat(requestList).hasSize(databaseSizeBeforeUpdate);
//    }
//
//    @Test
//    @Transactional
//    void deleteRequest() throws Exception {
//        // Initialize the database
//        requestRepository.saveAndFlush(request);
//
//        int databaseSizeBeforeDelete = requestRepository.findAll().size();
//
//        // Delete the request
//        restRequestMockMvc
//            .perform(delete(ENTITY_API_URL_ID, request.getId()).accept(MediaType.APPLICATION_JSON))
//            .andExpect(status().isNoContent());
//
//        // Validate the database contains one less item
//        List<Request> requestList = requestRepository.findAll();
//        assertThat(requestList).hasSize(databaseSizeBeforeDelete - 1);
//    }
}
