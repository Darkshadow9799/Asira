package com.dshaw.asira.service.impl;

import com.dshaw.asira.domain.Spe;
import com.dshaw.asira.repository.SpeRepository;
import com.dshaw.asira.service.SpeService;
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

    public SpeServiceImpl(SpeRepository speRepository) {
        this.speRepository = speRepository;
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
                if (spe.getSpeOrgId() != null) {
                    existingSpe.setSpeOrgId(spe.getSpeOrgId());
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
    @Transactional(readOnly = true)
    public Optional<Spe> findOne(Long id) {
        log.debug("Request to get Spe : {}", id);
        return speRepository.findById(id);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete Spe : {}", id);
        speRepository.deleteById(id);
    }
}
