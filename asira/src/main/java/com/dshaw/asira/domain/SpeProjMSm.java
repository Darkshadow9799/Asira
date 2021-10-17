package com.dshaw.asira.domain;

import java.io.Serializable;
import javax.persistence.*;
import javax.validation.constraints.*;

/**
 * A SpeProjMSm.
 */
@Entity
@Table(name = "spe_proj_m_sm")
public class SpeProjMSm implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "spe_id")
    private Long speId;

    @NotNull
    @Column(name = "proj_id", nullable = false)
    private Long projId;

    @NotNull
    @Column(name = "m_id", nullable = false)
    private Long mId;

    @NotNull
    @Column(name = "sm_id", nullable = false)
    private Long smId;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public SpeProjMSm id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getSpeId() {
        return this.speId;
    }

    public SpeProjMSm speId(Long speId) {
        this.setSpeId(speId);
        return this;
    }

    public void setSpeId(Long speId) {
        this.speId = speId;
    }

    public Long getProjId() {
        return this.projId;
    }

    public SpeProjMSm projId(Long projId) {
        this.setProjId(projId);
        return this;
    }

    public void setProjId(Long projId) {
        this.projId = projId;
    }

    public Long getmId() {
        return this.mId;
    }

    public SpeProjMSm mId(Long mId) {
        this.setmId(mId);
        return this;
    }

    public void setmId(Long mId) {
        this.mId = mId;
    }

    public Long getSmId() {
        return this.smId;
    }

    public SpeProjMSm smId(Long smId) {
        this.setSmId(smId);
        return this;
    }

    public void setSmId(Long smId) {
        this.smId = smId;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof SpeProjMSm)) {
            return false;
        }
        return id != null && id.equals(((SpeProjMSm) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "SpeProjMSm{" +
            "id=" + getId() +
            ", speId=" + getSpeId() +
            ", projId=" + getProjId() +
            ", mId=" + getmId() +
            ", smId=" + getSmId() +
            "}";
    }
}
