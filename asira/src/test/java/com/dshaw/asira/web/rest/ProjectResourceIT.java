package com.dshaw.asira.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.dshaw.asira.IntegrationTest;
import com.dshaw.asira.domain.Project;
import com.dshaw.asira.repository.ProjectRepository;
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
 * Integration tests for the {@link ProjectResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class ProjectResourceIT {

    private static final String DEFAULT_PROJ_NAME = "AAAAAAAAAA";
    private static final String UPDATED_PROJ_NAME = "BBBBBBBBBB";

    private static final Instant DEFAULT_PROJ_CREATED_DATE = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_PROJ_CREATED_DATE = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final Instant DEFAULT_PROJ_MODIFIED_DATE = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_PROJ_MODIFIED_DATE = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final Long DEFAULT_PROJ_ADMIN_ID = 1L;
    private static final Long UPDATED_PROJ_ADMIN_ID = 2L;

    private static final Long DEFAULT_ORG_ID = 1L;
    private static final Long UPDATED_ORG_ID = 2L;

    private static final Long DEFAULT_PROJ_MEMBER_NUMBER = 1L;
    private static final Long UPDATED_PROJ_MEMBER_NUMBER = 2L;

    private static final String ENTITY_API_URL = "/api/projects";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private ProjectRepository projectRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restProjectMockMvc;

    private Project project;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
//    public static Project createEntity(EntityManager em) {
//        Project project = new Project()
//            .projName(DEFAULT_PROJ_NAME)
//            .projCreatedDate(DEFAULT_PROJ_CREATED_DATE)
//            .projModifiedDate(DEFAULT_PROJ_MODIFIED_DATE)
//            .projAdminId(DEFAULT_PROJ_ADMIN_ID)
//            .orgId(DEFAULT_ORG_ID)
//            .projMemberNumber(DEFAULT_PROJ_MEMBER_NUMBER);
//        return project;
//    }
//
//    /**
//     * Create an updated entity for this test.
//     *
//     * This is a static method, as tests for other entities might also need it,
//     * if they test an entity which requires the current entity.
//     */
//    public static Project createUpdatedEntity(EntityManager em) {
//        Project project = new Project()
//            .projName(UPDATED_PROJ_NAME)
//            .projCreatedDate(UPDATED_PROJ_CREATED_DATE)
//            .projModifiedDate(UPDATED_PROJ_MODIFIED_DATE)
//            .projAdminId(UPDATED_PROJ_ADMIN_ID)
//            .orgId(UPDATED_ORG_ID)
//            .projMemberNumber(UPDATED_PROJ_MEMBER_NUMBER);
//        return project;
//    }
//
//    @BeforeEach
//    public void initTest() {
//        project = createEntity(em);
//    }
//
//    @Test
//    @Transactional
//    void createProject() throws Exception {
//        int databaseSizeBeforeCreate = projectRepository.findAll().size();
//        // Create the Project
//        restProjectMockMvc
//            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(project)))
//            .andExpect(status().isCreated());
//
//        // Validate the Project in the database
//        List<Project> projectList = projectRepository.findAll();
//        assertThat(projectList).hasSize(databaseSizeBeforeCreate + 1);
//        Project testProject = projectList.get(projectList.size() - 1);
//        assertThat(testProject.getProjName()).isEqualTo(DEFAULT_PROJ_NAME);
//        assertThat(testProject.getProjCreatedDate()).isEqualTo(DEFAULT_PROJ_CREATED_DATE);
//        assertThat(testProject.getProjModifiedDate()).isEqualTo(DEFAULT_PROJ_MODIFIED_DATE);
//        assertThat(testProject.getProjAdminId()).isEqualTo(DEFAULT_PROJ_ADMIN_ID);
//        assertThat(testProject.getOrgId()).isEqualTo(DEFAULT_ORG_ID);
//        assertThat(testProject.getProjMemberNumber()).isEqualTo(DEFAULT_PROJ_MEMBER_NUMBER);
//    }
//
//    @Test
//    @Transactional
//    void createProjectWithExistingId() throws Exception {
//        // Create the Project with an existing ID
//        project.setId(1L);
//
//        int databaseSizeBeforeCreate = projectRepository.findAll().size();
//
//        // An entity with an existing ID cannot be created, so this API call must fail
//        restProjectMockMvc
//            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(project)))
//            .andExpect(status().isBadRequest());
//
//        // Validate the Project in the database
//        List<Project> projectList = projectRepository.findAll();
//        assertThat(projectList).hasSize(databaseSizeBeforeCreate);
//    }
//
//    @Test
//    @Transactional
//    void checkProjNameIsRequired() throws Exception {
//        int databaseSizeBeforeTest = projectRepository.findAll().size();
//        // set the field null
//        project.setProjName(null);
//
//        // Create the Project, which fails.
//
//        restProjectMockMvc
//            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(project)))
//            .andExpect(status().isBadRequest());
//
//        List<Project> projectList = projectRepository.findAll();
//        assertThat(projectList).hasSize(databaseSizeBeforeTest);
//    }
//
//    @Test
//    @Transactional
//    void getAllProjects() throws Exception {
//        // Initialize the database
//        projectRepository.saveAndFlush(project);
//
//        // Get all the projectList
//        restProjectMockMvc
//            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
//            .andExpect(status().isOk())
//            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
//            .andExpect(jsonPath("$.[*].id").value(hasItem(project.getId().intValue())))
//            .andExpect(jsonPath("$.[*].projName").value(hasItem(DEFAULT_PROJ_NAME)))
//            .andExpect(jsonPath("$.[*].projCreatedDate").value(hasItem(DEFAULT_PROJ_CREATED_DATE.toString())))
//            .andExpect(jsonPath("$.[*].projModifiedDate").value(hasItem(DEFAULT_PROJ_MODIFIED_DATE.toString())))
//            .andExpect(jsonPath("$.[*].projAdminId").value(hasItem(DEFAULT_PROJ_ADMIN_ID.intValue())))
//            .andExpect(jsonPath("$.[*].orgId").value(hasItem(DEFAULT_ORG_ID.intValue())))
//            .andExpect(jsonPath("$.[*].projMemberNumber").value(hasItem(DEFAULT_PROJ_MEMBER_NUMBER.intValue())));
//    }
//
//    @Test
//    @Transactional
//    void getProject() throws Exception {
//        // Initialize the database
//        projectRepository.saveAndFlush(project);
//
//        // Get the project
//        restProjectMockMvc
//            .perform(get(ENTITY_API_URL_ID, project.getId()))
//            .andExpect(status().isOk())
//            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
//            .andExpect(jsonPath("$.id").value(project.getId().intValue()))
//            .andExpect(jsonPath("$.projName").value(DEFAULT_PROJ_NAME))
//            .andExpect(jsonPath("$.projCreatedDate").value(DEFAULT_PROJ_CREATED_DATE.toString()))
//            .andExpect(jsonPath("$.projModifiedDate").value(DEFAULT_PROJ_MODIFIED_DATE.toString()))
//            .andExpect(jsonPath("$.projAdminId").value(DEFAULT_PROJ_ADMIN_ID.intValue()))
//            .andExpect(jsonPath("$.orgId").value(DEFAULT_ORG_ID.intValue()))
//            .andExpect(jsonPath("$.projMemberNumber").value(DEFAULT_PROJ_MEMBER_NUMBER.intValue()));
//    }
//
//    @Test
//    @Transactional
//    void getNonExistingProject() throws Exception {
//        // Get the project
//        restProjectMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
//    }
//
//    @Test
//    @Transactional
//    void putNewProject() throws Exception {
//        // Initialize the database
//        projectRepository.saveAndFlush(project);
//
//        int databaseSizeBeforeUpdate = projectRepository.findAll().size();
//
//        // Update the project
//        Project updatedProject = projectRepository.findById(project.getId()).get();
//        // Disconnect from session so that the updates on updatedProject are not directly saved in db
//        em.detach(updatedProject);
//        updatedProject
//            .projName(UPDATED_PROJ_NAME)
//            .projCreatedDate(UPDATED_PROJ_CREATED_DATE)
//            .projModifiedDate(UPDATED_PROJ_MODIFIED_DATE)
//            .projAdminId(UPDATED_PROJ_ADMIN_ID)
//            .orgId(UPDATED_ORG_ID)
//            .projMemberNumber(UPDATED_PROJ_MEMBER_NUMBER);
//
//        restProjectMockMvc
//            .perform(
//                put(ENTITY_API_URL_ID, updatedProject.getId())
//                    .contentType(MediaType.APPLICATION_JSON)
//                    .content(TestUtil.convertObjectToJsonBytes(updatedProject))
//            )
//            .andExpect(status().isOk());
//
//        // Validate the Project in the database
//        List<Project> projectList = projectRepository.findAll();
//        assertThat(projectList).hasSize(databaseSizeBeforeUpdate);
//        Project testProject = projectList.get(projectList.size() - 1);
//        assertThat(testProject.getProjName()).isEqualTo(UPDATED_PROJ_NAME);
//        assertThat(testProject.getProjCreatedDate()).isEqualTo(UPDATED_PROJ_CREATED_DATE);
//        assertThat(testProject.getProjModifiedDate()).isEqualTo(UPDATED_PROJ_MODIFIED_DATE);
//        assertThat(testProject.getProjAdminId()).isEqualTo(UPDATED_PROJ_ADMIN_ID);
//        assertThat(testProject.getOrgId()).isEqualTo(UPDATED_ORG_ID);
//        assertThat(testProject.getProjMemberNumber()).isEqualTo(UPDATED_PROJ_MEMBER_NUMBER);
//    }
//
//    @Test
//    @Transactional
//    void putNonExistingProject() throws Exception {
//        int databaseSizeBeforeUpdate = projectRepository.findAll().size();
//        project.setId(count.incrementAndGet());
//
//        // If the entity doesn't have an ID, it will throw BadRequestAlertException
//        restProjectMockMvc
//            .perform(
//                put(ENTITY_API_URL_ID, project.getId())
//                    .contentType(MediaType.APPLICATION_JSON)
//                    .content(TestUtil.convertObjectToJsonBytes(project))
//            )
//            .andExpect(status().isBadRequest());
//
//        // Validate the Project in the database
//        List<Project> projectList = projectRepository.findAll();
//        assertThat(projectList).hasSize(databaseSizeBeforeUpdate);
//    }
//
//    @Test
//    @Transactional
//    void putWithIdMismatchProject() throws Exception {
//        int databaseSizeBeforeUpdate = projectRepository.findAll().size();
//        project.setId(count.incrementAndGet());
//
//        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
//        restProjectMockMvc
//            .perform(
//                put(ENTITY_API_URL_ID, count.incrementAndGet())
//                    .contentType(MediaType.APPLICATION_JSON)
//                    .content(TestUtil.convertObjectToJsonBytes(project))
//            )
//            .andExpect(status().isBadRequest());
//
//        // Validate the Project in the database
//        List<Project> projectList = projectRepository.findAll();
//        assertThat(projectList).hasSize(databaseSizeBeforeUpdate);
//    }
//
//    @Test
//    @Transactional
//    void putWithMissingIdPathParamProject() throws Exception {
//        int databaseSizeBeforeUpdate = projectRepository.findAll().size();
//        project.setId(count.incrementAndGet());
//
//        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
//        restProjectMockMvc
//            .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(project)))
//            .andExpect(status().isMethodNotAllowed());
//
//        // Validate the Project in the database
//        List<Project> projectList = projectRepository.findAll();
//        assertThat(projectList).hasSize(databaseSizeBeforeUpdate);
//    }
//
//    @Test
//    @Transactional
//    void partialUpdateProjectWithPatch() throws Exception {
//        // Initialize the database
//        projectRepository.saveAndFlush(project);
//
//        int databaseSizeBeforeUpdate = projectRepository.findAll().size();
//
//        // Update the project using partial update
//        Project partialUpdatedProject = new Project();
//        partialUpdatedProject.setId(project.getId());
//
//        partialUpdatedProject.projName(UPDATED_PROJ_NAME).projAdminId(UPDATED_PROJ_ADMIN_ID).orgId(UPDATED_ORG_ID);
//
//        restProjectMockMvc
//            .perform(
//                patch(ENTITY_API_URL_ID, partialUpdatedProject.getId())
//                    .contentType("application/merge-patch+json")
//                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedProject))
//            )
//            .andExpect(status().isOk());
//
//        // Validate the Project in the database
//        List<Project> projectList = projectRepository.findAll();
//        assertThat(projectList).hasSize(databaseSizeBeforeUpdate);
//        Project testProject = projectList.get(projectList.size() - 1);
//        assertThat(testProject.getProjName()).isEqualTo(UPDATED_PROJ_NAME);
//        assertThat(testProject.getProjCreatedDate()).isEqualTo(DEFAULT_PROJ_CREATED_DATE);
//        assertThat(testProject.getProjModifiedDate()).isEqualTo(DEFAULT_PROJ_MODIFIED_DATE);
//        assertThat(testProject.getProjAdminId()).isEqualTo(UPDATED_PROJ_ADMIN_ID);
//        assertThat(testProject.getOrgId()).isEqualTo(UPDATED_ORG_ID);
//        assertThat(testProject.getProjMemberNumber()).isEqualTo(DEFAULT_PROJ_MEMBER_NUMBER);
//    }
//
//    @Test
//    @Transactional
//    void fullUpdateProjectWithPatch() throws Exception {
//        // Initialize the database
//        projectRepository.saveAndFlush(project);
//
//        int databaseSizeBeforeUpdate = projectRepository.findAll().size();
//
//        // Update the project using partial update
//        Project partialUpdatedProject = new Project();
//        partialUpdatedProject.setId(project.getId());
//
//        partialUpdatedProject
//            .projName(UPDATED_PROJ_NAME)
//            .projCreatedDate(UPDATED_PROJ_CREATED_DATE)
//            .projModifiedDate(UPDATED_PROJ_MODIFIED_DATE)
//            .projAdminId(UPDATED_PROJ_ADMIN_ID)
//            .orgId(UPDATED_ORG_ID)
//            .projMemberNumber(UPDATED_PROJ_MEMBER_NUMBER);
//
//        restProjectMockMvc
//            .perform(
//                patch(ENTITY_API_URL_ID, partialUpdatedProject.getId())
//                    .contentType("application/merge-patch+json")
//                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedProject))
//            )
//            .andExpect(status().isOk());
//
//        // Validate the Project in the database
//        List<Project> projectList = projectRepository.findAll();
//        assertThat(projectList).hasSize(databaseSizeBeforeUpdate);
//        Project testProject = projectList.get(projectList.size() - 1);
//        assertThat(testProject.getProjName()).isEqualTo(UPDATED_PROJ_NAME);
//        assertThat(testProject.getProjCreatedDate()).isEqualTo(UPDATED_PROJ_CREATED_DATE);
//        assertThat(testProject.getProjModifiedDate()).isEqualTo(UPDATED_PROJ_MODIFIED_DATE);
//        assertThat(testProject.getProjAdminId()).isEqualTo(UPDATED_PROJ_ADMIN_ID);
//        assertThat(testProject.getOrgId()).isEqualTo(UPDATED_ORG_ID);
//        assertThat(testProject.getProjMemberNumber()).isEqualTo(UPDATED_PROJ_MEMBER_NUMBER);
//    }
//
//    @Test
//    @Transactional
//    void patchNonExistingProject() throws Exception {
//        int databaseSizeBeforeUpdate = projectRepository.findAll().size();
//        project.setId(count.incrementAndGet());
//
//        // If the entity doesn't have an ID, it will throw BadRequestAlertException
//        restProjectMockMvc
//            .perform(
//                patch(ENTITY_API_URL_ID, project.getId())
//                    .contentType("application/merge-patch+json")
//                    .content(TestUtil.convertObjectToJsonBytes(project))
//            )
//            .andExpect(status().isBadRequest());
//
//        // Validate the Project in the database
//        List<Project> projectList = projectRepository.findAll();
//        assertThat(projectList).hasSize(databaseSizeBeforeUpdate);
//    }
//
//    @Test
//    @Transactional
//    void patchWithIdMismatchProject() throws Exception {
//        int databaseSizeBeforeUpdate = projectRepository.findAll().size();
//        project.setId(count.incrementAndGet());
//
//        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
//        restProjectMockMvc
//            .perform(
//                patch(ENTITY_API_URL_ID, count.incrementAndGet())
//                    .contentType("application/merge-patch+json")
//                    .content(TestUtil.convertObjectToJsonBytes(project))
//            )
//            .andExpect(status().isBadRequest());
//
//        // Validate the Project in the database
//        List<Project> projectList = projectRepository.findAll();
//        assertThat(projectList).hasSize(databaseSizeBeforeUpdate);
//    }
//
//    @Test
//    @Transactional
//    void patchWithMissingIdPathParamProject() throws Exception {
//        int databaseSizeBeforeUpdate = projectRepository.findAll().size();
//        project.setId(count.incrementAndGet());
//
//        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
//        restProjectMockMvc
//            .perform(patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(TestUtil.convertObjectToJsonBytes(project)))
//            .andExpect(status().isMethodNotAllowed());
//
//        // Validate the Project in the database
//        List<Project> projectList = projectRepository.findAll();
//        assertThat(projectList).hasSize(databaseSizeBeforeUpdate);
//    }
//
//    @Test
//    @Transactional
//    void deleteProject() throws Exception {
//        // Initialize the database
//        projectRepository.saveAndFlush(project);
//
//        int databaseSizeBeforeDelete = projectRepository.findAll().size();
//
//        // Delete the project
//        restProjectMockMvc
//            .perform(delete(ENTITY_API_URL_ID, project.getId()).accept(MediaType.APPLICATION_JSON))
//            .andExpect(status().isNoContent());
//
//        // Validate the database contains one less item
//        List<Project> projectList = projectRepository.findAll();
//        assertThat(projectList).hasSize(databaseSizeBeforeDelete - 1);
//    }
}
