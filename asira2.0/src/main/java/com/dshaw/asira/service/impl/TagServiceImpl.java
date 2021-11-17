package com.dshaw.asira.service.impl;

import com.dshaw.asira.domain.Tag;
import com.dshaw.asira.repository.TagRepository;
import com.dshaw.asira.service.TagService;
import java.util.List;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link Tag}.
 */
@Service
@Transactional
public class TagServiceImpl implements TagService {

    private final Logger log = LoggerFactory.getLogger(TagServiceImpl.class);

    private final TagRepository tagRepository;

    public TagServiceImpl(TagRepository tagRepository) {
        this.tagRepository = tagRepository;
    }

    @Override
    public Tag save(Tag tag) {
        log.debug("Request to save Tag : {}", tag);
        return tagRepository.save(tag);
    }

    @Override
    public Optional<Tag> partialUpdate(Tag tag) {
        log.debug("Request to partially update Tag : {}", tag);

        return tagRepository
            .findById(tag.getId())
            .map(existingTag -> {
                if (tag.getTagTitle() != null) {
                    existingTag.setTagTitle(tag.getTagTitle());
                }
                if (tag.getTagDesc() != null) {
                    existingTag.setTagDesc(tag.getTagDesc());
                }
                if (tag.getTagCreatedById() != null) {
                    existingTag.setTagCreatedById(tag.getTagCreatedById());
                }
                if (tag.getTagCreatedByName() != null) {
                    existingTag.setTagCreatedByName(tag.getTagCreatedByName());
                }
                if (tag.getTagCreatedDate() != null) {
                    existingTag.setTagCreatedDate(tag.getTagCreatedDate());
                }
                if (tag.getTagModifiedDate() != null) {
                    existingTag.setTagModifiedDate(tag.getTagModifiedDate());
                }

                return existingTag;
            })
            .map(tagRepository::save);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Tag> findAll() {
        log.debug("Request to get all Tags");
        return tagRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Tag> findOne(Long id) {
        log.debug("Request to get Tag : {}", id);
        return tagRepository.findById(id);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete Tag : {}", id);
        tagRepository.deleteById(id);
    }
}
