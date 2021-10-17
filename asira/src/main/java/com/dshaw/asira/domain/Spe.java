package com.dshaw.asira.domain;

import java.io.Serializable;
import java.time.Instant;
import javax.persistence.*;
import javax.validation.constraints.*;

/**
 * A Spe.
 */
@Entity
@Table(name = "spe")
public class Spe implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "spe_first_name")
    private String speFirstName;

    @Column(name = "spe_last_name")
    private String speLastName;

    @NotNull
    @Column(name = "spe_email_id", nullable = false)
    private String speEmailId;

    @Column(name = "spe_phone_number")
    private Long spePhoneNumber;

    @Column(name = "spe_org_id")
    private Long speOrgId;

    @Column(name = "spe_org_verified")
    private Boolean speOrgVerified;

    @Column(name = "created_date")
    private Instant createdDate;

    @Column(name = "modified_date")
    private Instant modifiedDate;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public Spe id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSpeFirstName() {
        return this.speFirstName;
    }

    public Spe speFirstName(String speFirstName) {
        this.setSpeFirstName(speFirstName);
        return this;
    }

    public void setSpeFirstName(String speFirstName) {
        this.speFirstName = speFirstName;
    }

    public String getSpeLastName() {
        return this.speLastName;
    }

    public Spe speLastName(String speLastName) {
        this.setSpeLastName(speLastName);
        return this;
    }

    public void setSpeLastName(String speLastName) {
        this.speLastName = speLastName;
    }

    public String getSpeEmailId() {
        return this.speEmailId;
    }

    public Spe speEmailId(String speEmailId) {
        this.setSpeEmailId(speEmailId);
        return this;
    }

    public void setSpeEmailId(String speEmailId) {
        this.speEmailId = speEmailId;
    }

    public Long getSpePhoneNumber() {
        return this.spePhoneNumber;
    }

    public Spe spePhoneNumber(Long spePhoneNumber) {
        this.setSpePhoneNumber(spePhoneNumber);
        return this;
    }

    public void setSpePhoneNumber(Long spePhoneNumber) {
        this.spePhoneNumber = spePhoneNumber;
    }

    public Long getSpeOrgId() {
        return this.speOrgId;
    }

    public Spe speOrgId(Long speOrgId) {
        this.setSpeOrgId(speOrgId);
        return this;
    }

    public void setSpeOrgId(Long speOrgId) {
        this.speOrgId = speOrgId;
    }

    public Boolean getSpeOrgVerified() {
        return this.speOrgVerified;
    }

    public Spe speOrgVerified(Boolean speOrgVerified) {
        this.setSpeOrgVerified(speOrgVerified);
        return this;
    }

    public void setSpeOrgVerified(Boolean speOrgVerified) {
        this.speOrgVerified = speOrgVerified;
    }

    public Instant getCreatedDate() {
        return this.createdDate;
    }

    public Spe createdDate(Instant createdDate) {
        this.setCreatedDate(createdDate);
        return this;
    }

    public void setCreatedDate(Instant createdDate) {
        this.createdDate = createdDate;
    }

    public Instant getModifiedDate() {
        return this.modifiedDate;
    }

    public Spe modifiedDate(Instant modifiedDate) {
        this.setModifiedDate(modifiedDate);
        return this;
    }

    public void setModifiedDate(Instant modifiedDate) {
        this.modifiedDate = modifiedDate;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Spe)) {
            return false;
        }
        return id != null && id.equals(((Spe) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Spe{" +
            "id=" + getId() +
            ", speFirstName='" + getSpeFirstName() + "'" +
            ", speLastName='" + getSpeLastName() + "'" +
            ", speEmailId='" + getSpeEmailId() + "'" +
            ", spePhoneNumber=" + getSpePhoneNumber() +
            ", speOrgId=" + getSpeOrgId() +
            ", speOrgVerified='" + getSpeOrgVerified() + "'" +
            ", createdDate='" + getCreatedDate() + "'" +
            ", modifiedDate='" + getModifiedDate() + "'" +
            "}";
    }
}
