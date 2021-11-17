package com.dshaw.asira.service.impl;

import com.dshaw.asira.domain.SubModules;
import com.dshaw.asira.repository.SubModulesRepository;
import com.dshaw.asira.service.SubModulesService;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link SubModules}.
 */
@Service
@Transactional
public class SubModulesServiceImpl implements SubModulesService {

    private final Logger log = LoggerFactory.getLogger(SubModulesServiceImpl.class);

    private final SubModulesRepository subModulesRepository;

    public SubModulesServiceImpl(SubModulesRepository subModulesRepository) {
        this.subModulesRepository = subModulesRepository;
    }

    @Override
    public SubModules save(SubModules subModules) {
        log.debug("Request to save SubModules : {}", subModules);
        return subModulesRepository.save(subModules);
    }

    @Override
    public Optional<SubModules> partialUpdate(SubModules subModules) {
        log.debug("Request to partially update SubModules : {}", subModules);

        return subModulesRepository
            .findById(subModules.getId())
            .map(existingSubModules -> {
                if (subModules.getSmName() != null) {
                    existingSubModules.setSmName(subModules.getSmName());
                }
                if (subModules.getSmDesc() != null) {
                    existingSubModules.setSmDesc(subModules.getSmDesc());
                }
                if (subModules.getSmCreatedDate() != null) {
                    existingSubModules.setSmCreatedDate(subModules.getSmCreatedDate());
                }
                if (subModules.getSmModifiedDate() != null) {
                    existingSubModules.setSmModifiedDate(subModules.getSmModifiedDate());
                }
                if (subModules.getSmDueDate() != null) {
                    existingSubModules.setSmDueDate(subModules.getSmDueDate());
                }
                if (subModules.getSmFinishedDate() != null) {
                    existingSubModules.setSmFinishedDate(subModules.getSmFinishedDate());
                }
                if (subModules.getSmCompleted() != null) {
                    existingSubModules.setSmCompleted(subModules.getSmCompleted());
                }
                if (subModules.getSpeNameSmTagId() != null) {
                    existingSubModules.setSpeNameSmTagId(subModules.getSpeNameSmTagId());
                }
                if (subModules.getSmLoggedTime() != null) {
                    existingSubModules.setSmLoggedTime(subModules.getSmLoggedTime());
                }

                return existingSubModules;
            })
            .map(subModulesRepository::save);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<SubModules> findAll(Pageable pageable) {
        log.debug("Request to get all SubModules");
        return subModulesRepository.findAll(pageable);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<SubModules> findOne(Long id) {
        log.debug("Request to get SubModules : {}", id);
        return subModulesRepository.findById(id);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete SubModules : {}", id);
        subModulesRepository.deleteById(id);
    }
}
