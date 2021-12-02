package com.dshaw.asira.repository;

import com.dshaw.asira.domain.Org;
import com.dshaw.asira.domain.Spe;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Spring Data SQL repository for the Spe entity.
 */
@SuppressWarnings("unused")
@Repository
public interface SpeRepository extends JpaRepository<Spe, Long> {
    List<Spe> findByOrg(Org org);

    Optional<Spe> findBySpeEmailId(String emailId);
}
