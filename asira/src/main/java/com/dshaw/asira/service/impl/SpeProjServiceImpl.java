package com.dshaw.asira.service.impl;

import com.dshaw.asira.domain.SpeProj;
import com.dshaw.asira.repository.SpeProjRepository;
import com.dshaw.asira.service.SpeProjService;
import java.util.List;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link SpeProj}.
 */
@Service
@Transactional
public class SpeProjServiceImpl implements SpeProjService {

    private final Logger log = LoggerFactory.getLogger(SpeProjServiceImpl.class);

    private final SpeProjRepository speProjRepository;

    public SpeProjServiceImpl(SpeProjRepository speProjRepository) {
        this.speProjRepository = speProjRepository;
    }

    @Override
    public SpeProj save(SpeProj speProj) {
        log.debug("Request to save SpeProj : {}", speProj);
        return speProjRepository.save(speProj);
    }

    @Override
    public Optional<SpeProj> partialUpdate(SpeProj speProj) {
        log.debug("Request to partially update SpeProj : {}", speProj);

        return speProjRepository
            .findById(speProj.getId())
            .map(existingSpeProj -> {
                if (speProj.getProjId() != null) {
                    existingSpeProj.setProjId(speProj.getProjId());
                }
                if (speProj.getSpeId() != null) {
                    existingSpeProj.setSpeId(speProj.getSpeId());
                }
                if (speProj.getOrgId() != null) {
                    existingSpeProj.setOrgId(speProj.getOrgId());
                }

                return existingSpeProj;
            })
            .map(speProjRepository::save);
    }

    @Override
    @Transactional(readOnly = true)
    public List<SpeProj> findAll() {
        log.debug("Request to get all SpeProjs");
        return speProjRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<SpeProj> findOne(Long id) {
        log.debug("Request to get SpeProj : {}", id);
        return speProjRepository.findById(id);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete SpeProj : {}", id);
        speProjRepository.deleteById(id);
    }
}
