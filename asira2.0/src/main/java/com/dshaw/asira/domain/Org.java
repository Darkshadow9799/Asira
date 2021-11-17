package com.dshaw.asira.domain;

import java.io.Serializable;
import java.time.Instant;
import javax.persistence.*;
import javax.validation.constraints.*;

/**
 * A Org.
 */
@Entity
@Table(name = "org")
public class Org implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @NotNull
    @Column(name = "org_name", nullable = false)
    private String orgName;

    @Column(name = "org_email_id")
    private String orgEmailId;

    @Column(name = "org_phone_num")
    private Long orgPhoneNum;

    @Column(name = "created_date")
    private Instant createdDate;

    @Column(name = "modified_date")
    private Instant modifiedDate;

    @Column(name = "org_proj_num")
    private Long orgProjNum;

    @Column(name = "org_members_num")
    private Long orgMembersNum;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public Org id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getOrgName() {
        return this.orgName;
    }

    public Org orgName(String orgName) {
        this.setOrgName(orgName);
        return this;
    }

    public void setOrgName(String orgName) {
        this.orgName = orgName;
    }

    public String getOrgEmailId() {
        return this.orgEmailId;
    }

    public Org orgEmailId(String orgEmailId) {
        this.setOrgEmailId(orgEmailId);
        return this;
    }

    public void setOrgEmailId(String orgEmailId) {
        this.orgEmailId = orgEmailId;
    }

    public Long getOrgPhoneNum() {
        return this.orgPhoneNum;
    }

    public Org orgPhoneNum(Long orgPhoneNum) {
        this.setOrgPhoneNum(orgPhoneNum);
        return this;
    }

    public void setOrgPhoneNum(Long orgPhoneNum) {
        this.orgPhoneNum = orgPhoneNum;
    }

    public Instant getCreatedDate() {
        return this.createdDate;
    }

    public Org createdDate(Instant createdDate) {
        this.setCreatedDate(createdDate);
        return this;
    }

    public void setCreatedDate(Instant createdDate) {
        this.createdDate = createdDate;
    }

    public Instant getModifiedDate() {
        return this.modifiedDate;
    }

    public Org modifiedDate(Instant modifiedDate) {
        this.setModifiedDate(modifiedDate);
        return this;
    }

    public void setModifiedDate(Instant modifiedDate) {
        this.modifiedDate = modifiedDate;
    }

    public Long getOrgProjNum() {
        return this.orgProjNum;
    }

    public Org orgProjNum(Long orgProjNum) {
        this.setOrgProjNum(orgProjNum);
        return this;
    }

    public void setOrgProjNum(Long orgProjNum) {
        this.orgProjNum = orgProjNum;
    }

    public Long getOrgMembersNum() {
        return this.orgMembersNum;
    }

    public Org orgMembersNum(Long orgMembersNum) {
        this.setOrgMembersNum(orgMembersNum);
        return this;
    }

    public void setOrgMembersNum(Long orgMembersNum) {
        this.orgMembersNum = orgMembersNum;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Org)) {
            return false;
        }
        return id != null && id.equals(((Org) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Org{" +
            "id=" + getId() +
            ", orgName='" + getOrgName() + "'" +
            ", orgEmailId='" + getOrgEmailId() + "'" +
            ", orgPhoneNum=" + getOrgPhoneNum() +
            ", createdDate='" + getCreatedDate() + "'" +
            ", modifiedDate='" + getModifiedDate() + "'" +
            ", orgProjNum=" + getOrgProjNum() +
            ", orgMembersNum=" + getOrgMembersNum() +
            "}";
    }
}
