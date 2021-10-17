package com.dshaw.asira.service.impl;

import com.dshaw.asira.domain.Com;
import com.dshaw.asira.repository.ComRepository;
import com.dshaw.asira.service.ComService;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link Com}.
 */
@Service
@Transactional
public class ComServiceImpl implements ComService {

    private final Logger log = LoggerFactory.getLogger(ComServiceImpl.class);

    private final ComRepository comRepository;

    public ComServiceImpl(ComRepository comRepository) {
        this.comRepository = comRepository;
    }

    @Override
    public Com save(Com com) {
        log.debug("Request to save Com : {}", com);
        return comRepository.save(com);
    }

    @Override
    public Optional<Com> partialUpdate(Com com) {
        log.debug("Request to partially update Com : {}", com);

        return comRepository
            .findById(com.getId())
            .map(existingCom -> {
                if (com.getcCreatedById() != null) {
                    existingCom.setcCreatedById(com.getcCreatedById());
                }
                if (com.getcCreatedByName() != null) {
                    existingCom.setcCreatedByName(com.getcCreatedByName());
                }
                if (com.getcDesc() != null) {
                    existingCom.setcDesc(com.getcDesc());
                }
                if (com.getcCreatedDate() != null) {
                    existingCom.setcCreatedDate(com.getcCreatedDate());
                }
                if (com.getcModifiedDate() != null) {
                    existingCom.setcModifiedDate(com.getcModifiedDate());
                }
                if (com.getSmId() != null) {
                    existingCom.setSmId(com.getSmId());
                }

                return existingCom;
            })
            .map(comRepository::save);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<Com> findAll(Pageable pageable) {
        log.debug("Request to get all Coms");
        return comRepository.findAll(pageable);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Com> findOne(Long id) {
        log.debug("Request to get Com : {}", id);
        return comRepository.findById(id);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete Com : {}", id);
        comRepository.deleteById(id);
    }
}
