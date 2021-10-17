package com.dshaw.asira.service.impl;

import com.dshaw.asira.domain.SpeProjMSm;
import com.dshaw.asira.repository.SpeProjMSmRepository;
import com.dshaw.asira.service.SpeProjMSmService;
import java.util.List;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link SpeProjMSm}.
 */
@Service
@Transactional
public class SpeProjMSmServiceImpl implements SpeProjMSmService {

    private final Logger log = LoggerFactory.getLogger(SpeProjMSmServiceImpl.class);

    private final SpeProjMSmRepository speProjMSmRepository;

    public SpeProjMSmServiceImpl(SpeProjMSmRepository speProjMSmRepository) {
        this.speProjMSmRepository = speProjMSmRepository;
    }

    @Override
    public SpeProjMSm save(SpeProjMSm speProjMSm) {
        log.debug("Request to save SpeProjMSm : {}", speProjMSm);
        return speProjMSmRepository.save(speProjMSm);
    }

    @Override
    public Optional<SpeProjMSm> partialUpdate(SpeProjMSm speProjMSm) {
        log.debug("Request to partially update SpeProjMSm : {}", speProjMSm);

        return speProjMSmRepository
            .findById(speProjMSm.getId())
            .map(existingSpeProjMSm -> {
                if (speProjMSm.getSpeId() != null) {
                    existingSpeProjMSm.setSpeId(speProjMSm.getSpeId());
                }
                if (speProjMSm.getProjId() != null) {
                    existingSpeProjMSm.setProjId(speProjMSm.getProjId());
                }
                if (speProjMSm.getmId() != null) {
                    existingSpeProjMSm.setmId(speProjMSm.getmId());
                }
                if (speProjMSm.getSmId() != null) {
                    existingSpeProjMSm.setSmId(speProjMSm.getSmId());
                }

                return existingSpeProjMSm;
            })
            .map(speProjMSmRepository::save);
    }

    @Override
    @Transactional(readOnly = true)
    public List<SpeProjMSm> findAll() {
        log.debug("Request to get all SpeProjMSms");
        return speProjMSmRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<SpeProjMSm> findOne(Long id) {
        log.debug("Request to get SpeProjMSm : {}", id);
        return speProjMSmRepository.findById(id);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete SpeProjMSm : {}", id);
        speProjMSmRepository.deleteById(id);
    }
}
