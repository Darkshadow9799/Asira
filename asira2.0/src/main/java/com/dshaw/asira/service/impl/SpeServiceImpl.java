package com.dshaw.asira.service.impl;

import com.dshaw.asira.domain.Org;
import com.dshaw.asira.domain.Project;
import com.dshaw.asira.domain.Spe;
import com.dshaw.asira.repository.SpeRepository;
import com.dshaw.asira.service.ProjectService;
import com.dshaw.asira.service.SpeService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link Spe}.
 */
@Service
@Transactional
public class SpeServiceImpl implements SpeService {

    private final Logger log = LoggerFactory.getLogger(SpeServiceImpl.class);

    private final SpeRepository speRepository;

    private final ProjectService projectService;

    public SpeServiceImpl(SpeRepository speRepository,
                          ProjectService projectService) {
        this.speRepository = speRepository;
        this.projectService = projectService;
    }

    @Override
    public Spe save(Spe spe) {
        log.debug("Request to save Spe : {}", spe);
        return speRepository.save(spe);
    }

    @Override
    public Optional<Spe> partialUpdate(Spe spe) {
        log.debug("Request to partially update Spe : {}", spe);

        return speRepository
            .findById(spe.getId())
            .map(existingSpe -> {
                if (spe.getSpeFirstName() != null) {
                    existingSpe.setSpeFirstName(spe.getSpeFirstName());
                }
                if (spe.getSpeLastName() != null) {
                    existingSpe.setSpeLastName(spe.getSpeLastName());
                }
                if (spe.getSpeEmailId() != null) {
                    existingSpe.setSpeEmailId(spe.getSpeEmailId());
                }
                if (spe.getSpePhoneNumber() != null) {
                    existingSpe.setSpePhoneNumber(spe.getSpePhoneNumber());
                }
                if (spe.getSpeOrgVerified() != null) {
                    existingSpe.setSpeOrgVerified(spe.getSpeOrgVerified());
                }
                if (spe.getCreatedDate() != null) {
                    existingSpe.setCreatedDate(spe.getCreatedDate());
                }
                if (spe.getModifiedDate() != null) {
                    existingSpe.setModifiedDate(spe.getModifiedDate());
                }

                return existingSpe;
            })
            .map(speRepository::save);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Spe> findAll() {
        log.debug("Request to get all Spes");
        return speRepository.findAll();
    }

    @Override
    public List<Spe> findAllSpesByOrg(Org org) {
        return speRepository.findByOrg(org);
    }

    @Override
    public List<Project> findAllProjectsBySpe(Long id) {
        Optional<Spe> spe = speRepository.findById(id);
        if(spe.isPresent()){
            return projectService.findAllProjectsBySpe(spe.get());
        }
        return new ArrayList<Project>();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Spe> findOne(Long id) {
        log.debug("Request to get Spe : {}", id);
        return speRepository.findById(id);
    }

    @Override
    public Optional<Spe> findOneByEmailId(String email) {
        return speRepository.findBySpeEmailId(email);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete Spe : {}", id);
        speRepository.deleteById(id);
    }
}
