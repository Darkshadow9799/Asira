package com.dshaw.asira.service.impl;

import com.dshaw.asira.domain.Modules;
import com.dshaw.asira.domain.Project;
import com.dshaw.asira.repository.ModulesRepository;
import com.dshaw.asira.service.ModulesService;

import java.util.List;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link Modules}.
 */
@Service
@Transactional
public class ModulesServiceImpl implements ModulesService {

    private final Logger log = LoggerFactory.getLogger(ModulesServiceImpl.class);

    private final ModulesRepository modulesRepository;

    public ModulesServiceImpl(ModulesRepository modulesRepository) {
        this.modulesRepository = modulesRepository;
    }

    @Override
    public Modules save(Modules modules) {
        log.debug("Request to save Modules : {}", modules);
        return modulesRepository.save(modules);
    }

    @Override
    public List<Modules> getAllModulesByProject(Project project) {
        return modulesRepository.findByProject(project);
    }

    @Override
    public Optional<Modules> partialUpdate(Modules modules) {
        log.debug("Request to partially update Modules : {}", modules);

        return modulesRepository
            .findById(modules.getId())
            .map(existingModules -> {
                if (modules.getmName() != null) {
                    existingModules.setmName(modules.getmName());
                }
                if (modules.getmDesc() != null) {
                    existingModules.setmDesc(modules.getmDesc());
                }
                if (modules.getmCreatedDate() != null) {
                    existingModules.setmCreatedDate(modules.getmCreatedDate());
                }
                if (modules.getmModifiedDate() != null) {
                    existingModules.setmModifiedDate(modules.getmModifiedDate());
                }
                if (modules.getmSmNum() != null) {
                    existingModules.setmSmNum(modules.getmSmNum());
                }
                if (modules.getmDueDate() != null) {
                    existingModules.setmDueDate(modules.getmDueDate());
                }
                if (modules.getmFinishedDate() != null) {
                    existingModules.setmFinishedDate(modules.getmFinishedDate());
                }
                if (modules.getmCompleted() != null) {
                    existingModules.setmCompleted(modules.getmCompleted());
                }

                return existingModules;
            })
            .map(modulesRepository::save);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<Modules> findAll(Pageable pageable) {
        log.debug("Request to get all Modules");
        return modulesRepository.findAll(pageable);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Modules> findOne(Long id) {
        log.debug("Request to get Modules : {}", id);
        return modulesRepository.findById(id);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete Modules : {}", id);
        modulesRepository.deleteById(id);
    }
}
