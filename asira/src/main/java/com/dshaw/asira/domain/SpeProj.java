package com.dshaw.asira.domain;

import java.io.Serializable;
import javax.persistence.*;
import javax.validation.constraints.*;

/**
 * A SpeProj.
 */
@Entity
@Table(name = "spe_proj")
public class SpeProj implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @NotNull
    @Column(name = "proj_id", nullable = false)
    private Long projId;

    @Column(name = "spe_id")
    private Long speId;

    @Column(name = "org_id")
    private Long orgId;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public SpeProj id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getProjId() {
        return this.projId;
    }

    public SpeProj projId(Long projId) {
        this.setProjId(projId);
        return this;
    }

    public void setProjId(Long projId) {
        this.projId = projId;
    }

    public Long getSpeId() {
        return this.speId;
    }

    public SpeProj speId(Long speId) {
        this.setSpeId(speId);
        return this;
    }

    public void setSpeId(Long speId) {
        this.speId = speId;
    }

    public Long getOrgId() {
        return this.orgId;
    }

    public SpeProj orgId(Long orgId) {
        this.setOrgId(orgId);
        return this;
    }

    public void setOrgId(Long orgId) {
        this.orgId = orgId;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof SpeProj)) {
            return false;
        }
        return id != null && id.equals(((SpeProj) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "SpeProj{" +
            "id=" + getId() +
            ", projId=" + getProjId() +
            ", speId=" + getSpeId() +
            ", orgId=" + getOrgId() +
            "}";
    }
}
