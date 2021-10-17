package com.dshaw.asira.domain;

import java.io.Serializable;
import java.time.Instant;
import javax.persistence.*;
import javax.validation.constraints.*;

/**
 * A Com.
 */
@Entity
@Table(name = "com")
public class Com implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @NotNull
    @Column(name = "c_created_by_id", nullable = false)
    private Long cCreatedById;

    @Column(name = "c_created_by_name")
    private String cCreatedByName;

    @Column(name = "c_desc")
    private String cDesc;

    @Column(name = "c_created_date")
    private Instant cCreatedDate;

    @Column(name = "c_modified_date")
    private Instant cModifiedDate;

    @NotNull
    @Column(name = "sm_id", nullable = false)
    private Long smId;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public Com id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getcCreatedById() {
        return this.cCreatedById;
    }

    public Com cCreatedById(Long cCreatedById) {
        this.setcCreatedById(cCreatedById);
        return this;
    }

    public void setcCreatedById(Long cCreatedById) {
        this.cCreatedById = cCreatedById;
    }

    public String getcCreatedByName() {
        return this.cCreatedByName;
    }

    public Com cCreatedByName(String cCreatedByName) {
        this.setcCreatedByName(cCreatedByName);
        return this;
    }

    public void setcCreatedByName(String cCreatedByName) {
        this.cCreatedByName = cCreatedByName;
    }

    public String getcDesc() {
        return this.cDesc;
    }

    public Com cDesc(String cDesc) {
        this.setcDesc(cDesc);
        return this;
    }

    public void setcDesc(String cDesc) {
        this.cDesc = cDesc;
    }

    public Instant getcCreatedDate() {
        return this.cCreatedDate;
    }

    public Com cCreatedDate(Instant cCreatedDate) {
        this.setcCreatedDate(cCreatedDate);
        return this;
    }

    public void setcCreatedDate(Instant cCreatedDate) {
        this.cCreatedDate = cCreatedDate;
    }

    public Instant getcModifiedDate() {
        return this.cModifiedDate;
    }

    public Com cModifiedDate(Instant cModifiedDate) {
        this.setcModifiedDate(cModifiedDate);
        return this;
    }

    public void setcModifiedDate(Instant cModifiedDate) {
        this.cModifiedDate = cModifiedDate;
    }

    public Long getSmId() {
        return this.smId;
    }

    public Com smId(Long smId) {
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
        if (!(o instanceof Com)) {
            return false;
        }
        return id != null && id.equals(((Com) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Com{" +
            "id=" + getId() +
            ", cCreatedById=" + getcCreatedById() +
            ", cCreatedByName='" + getcCreatedByName() + "'" +
            ", cDesc='" + getcDesc() + "'" +
            ", cCreatedDate='" + getcCreatedDate() + "'" +
            ", cModifiedDate='" + getcModifiedDate() + "'" +
            ", smId=" + getSmId() +
            "}";
    }
}
