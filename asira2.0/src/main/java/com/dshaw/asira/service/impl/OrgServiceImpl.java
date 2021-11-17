package com.dshaw.asira.service.impl;

import com.dshaw.asira.domain.Org;
import com.dshaw.asira.repository.OrgRepository;
import com.dshaw.asira.service.OrgService;
import java.util.List;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link Org}.
 */
@Service
@Transactional
public class OrgServiceImpl implements OrgService {

    private final Logger log = LoggerFactory.getLogger(OrgServiceImpl.class);

    private final OrgRepository orgRepository;

    public OrgServiceImpl(OrgRepository orgRepository) {
        this.orgRepository = orgRepository;
    }

    @Override
    public Org save(Org org) {
        log.debug("Request to save Org : {}", org);
        return orgRepository.save(org);
    }

    @Override
    public Optional<Org> partialUpdate(Org org) {
        log.debug("Request to partially update Org : {}", org);

        return orgRepository
            .findById(org.getId())
            .map(existingOrg -> {
                if (org.getOrgName() != null) {
                    existingOrg.setOrgName(org.getOrgName());
                }
                if (org.getOrgEmailId() != null) {
                    existingOrg.setOrgEmailId(org.getOrgEmailId());
                }
                if (org.getOrgPhoneNum() != null) {
                    existingOrg.setOrgPhoneNum(org.getOrgPhoneNum());
                }
                if (org.getCreatedDate() != null) {
                    existingOrg.setCreatedDate(org.getCreatedDate());
                }
                if (org.getModifiedDate() != null) {
                    existingOrg.setModifiedDate(org.getModifiedDate());
                }
                if (org.getOrgProjNum() != null) {
                    existingOrg.setOrgProjNum(org.getOrgProjNum());
                }
                if (org.getOrgMembersNum() != null) {
                    existingOrg.setOrgMembersNum(org.getOrgMembersNum());
                }

                return existingOrg;
            })
            .map(orgRepository::save);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Org> findAll() {
        log.debug("Request to get all Orgs");
        return orgRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Org> findOne(Long id) {
        log.debug("Request to get Org : {}", id);
        return orgRepository.findById(id);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete Org : {}", id);
        orgRepository.deleteById(id);
    }
}
