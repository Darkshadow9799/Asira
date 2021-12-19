package com.dshaw.asira.service.impl;

import com.dshaw.asira.domain.Modules;
import com.dshaw.asira.domain.Org;
import com.dshaw.asira.domain.Project;
import com.dshaw.asira.domain.Spe;
import com.dshaw.asira.repository.ProjectRepository;
import com.dshaw.asira.service.ModulesService;
import com.dshaw.asira.service.ProjectService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link Project}.
 */
@Service
@Transactional
public class ProjectServiceImpl implements ProjectService {

    private final Logger log = LoggerFactory.getLogger(ProjectServiceImpl.class);

    private final ProjectRepository projectRepository;

    private final ModulesService modulesService;

    public ProjectServiceImpl(ProjectRepository projectRepository,
                              ModulesService modulesService) {
        this.projectRepository = projectRepository;
        this.modulesService = modulesService;
    }

    @Override
    public Project save(Project project) {
        log.debug("Request to save Project : {}", project);
        return projectRepository.save(project);
    }

    @Override
    public Optional<Project> partialUpdate(Project project) {
        log.debug("Request to partially update Project : {}", project);

        return projectRepository
            .findById(project.getId())
            .map(existingProject -> {
                if (project.getProjName() != null) {
                    existingProject.setProjName(project.getProjName());
                }
                if (project.getProjCreatedDate() != null) {
                    existingProject.setProjCreatedDate(project.getProjCreatedDate());
                }
                if (project.getProjModifiedDate() != null) {
                    existingProject.setProjModifiedDate(project.getProjModifiedDate());
                }
                if (project.getProjMemberNumber() != null) {
                    existingProject.setProjMemberNumber(project.getProjMemberNumber());
                }

                return existingProject;
            })
            .map(projectRepository::save);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<Project> findAll(Pageable pageable) {
        log.debug("Request to get all Projects");
        return projectRepository.findAll(pageable);
    }

    @Override
    public List<Project> findAllProjectsByOrg(Org org) {
        log.debug("Request to get all Projects By Org: {}", org);
        return projectRepository.findByOrg(org);
    }

    @Override
    public List<Project> findAllProjectsBySpe(Spe spe) {
        log.debug("Request to get all projects by Spe: {}", spe);
        return projectRepository.findBySpe(spe);
    }

    @Override
    public List<Modules> findAllModulesByProject(Long id) {
        Optional<Project> project = projectRepository.findById(id);
        if(project.isPresent()){
            return modulesService.getAllModulesByProject(project.get());
        }
        return new ArrayList<Modules>();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Project> findOne(Long id) {
        log.debug("Request to get Project : {}", id);
        return projectRepository.findById(id);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete Project : {}", id);
        projectRepository.deleteById(id);
    }
}
