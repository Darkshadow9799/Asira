package com.dshaw.asira.service.impl;

import com.dshaw.asira.domain.Request;
import com.dshaw.asira.repository.RequestRepository;
import com.dshaw.asira.service.RequestService;
import java.util.List;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link Request}.
 */
@Service
@Transactional
public class RequestServiceImpl implements RequestService {

    private final Logger log = LoggerFactory.getLogger(RequestServiceImpl.class);

    private final RequestRepository requestRepository;

    public RequestServiceImpl(RequestRepository requestRepository) {
        this.requestRepository = requestRepository;
    }

    @Override
    public Request save(Request request) {
        log.debug("Request to save Request : {}", request);
        return requestRepository.save(request);
    }

    @Override
    public Optional<Request> partialUpdate(Request request) {
        log.debug("Request to partially update Request : {}", request);

        return requestRepository
            .findById(request.getId())
            .map(existingRequest -> {
                if (request.getrFrom() != null) {
                    existingRequest.setrFrom(request.getrFrom());
                }
                if (request.getrTo() != null) {
                    existingRequest.setrTo(request.getrTo());
                }
                if (request.getrPendingState() != null) {
                    existingRequest.setrPendingState(request.getrPendingState());
                }
                if (request.getrAccepted() != null) {
                    existingRequest.setrAccepted(request.getrAccepted());
                }
                if (request.getrRejected() != null) {
                    existingRequest.setrRejected(request.getrRejected());
                }
                if (request.getSpeNotified() != null) {
                    existingRequest.setSpeNotified(request.getSpeNotified());
                }
                if (request.getOrgNotified() != null) {
                    existingRequest.setOrgNotified(request.getOrgNotified());
                }
                if (request.getCreatedDate() != null) {
                    existingRequest.setCreatedDate(request.getCreatedDate());
                }
                if (request.getModifiedDate() != null) {
                    existingRequest.setModifiedDate(request.getModifiedDate());
                }

                return existingRequest;
            })
            .map(requestRepository::save);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Request> findAll() {
        log.debug("Request to get all Requests");
        return requestRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Request> findOne(Long id) {
        log.debug("Request to get Request : {}", id);
        return requestRepository.findById(id);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete Request : {}", id);
        requestRepository.deleteById(id);
    }
}
