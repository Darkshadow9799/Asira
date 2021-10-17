package com.dshaw.asira.domain;

import java.io.Serializable;
import java.time.Instant;
import javax.persistence.*;
import javax.validation.constraints.*;

/**
 * A Project.
 */
@Entity
@Table(name = "project")
public class Project implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @NotNull
    @Column(name = "proj_name", nullable = false)
    private String projName;

    @Column(name = "proj_created_date")
    private Instant projCreatedDate;

    @Column(name = "proj_modified_date")
    private Instant projModifiedDate;

    @Column(name = "proj_admin_id")
    private Long projAdminId;

    @Column(name = "org_id")
    private Long orgId;

    @Column(name = "proj_member_number")
    private Long projMemberNumber;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public Project id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getProjName() {
        return this.projName;
    }

    public Project projName(String projName) {
        this.setProjName(projName);
        return this;
    }

    public void setProjName(String projName) {
        this.projName = projName;
    }

    public Instant getProjCreatedDate() {
        return this.projCreatedDate;
    }

    public Project projCreatedDate(Instant projCreatedDate) {
        this.setProjCreatedDate(projCreatedDate);
        return this;
    }

    public void setProjCreatedDate(Instant projCreatedDate) {
        this.projCreatedDate = projCreatedDate;
    }

    public Instant getProjModifiedDate() {
        return this.projModifiedDate;
    }

    public Project projModifiedDate(Instant projModifiedDate) {
        this.setProjModifiedDate(projModifiedDate);
        return this;
    }

    public void setProjModifiedDate(Instant projModifiedDate) {
        this.projModifiedDate = projModifiedDate;
    }

    public Long getProjAdminId() {
        return this.projAdminId;
    }

    public Project projAdminId(Long projAdminId) {
        this.setProjAdminId(projAdminId);
        return this;
    }

    public void setProjAdminId(Long projAdminId) {
        this.projAdminId = projAdminId;
    }

    public Long getOrgId() {
        return this.orgId;
    }

    public Project orgId(Long orgId) {
        this.setOrgId(orgId);
        return this;
    }

    public void setOrgId(Long orgId) {
        this.orgId = orgId;
    }

    public Long getProjMemberNumber() {
        return this.projMemberNumber;
    }

    public Project projMemberNumber(Long projMemberNumber) {
        this.setProjMemberNumber(projMemberNumber);
        return this;
    }

    public void setProjMemberNumber(Long projMemberNumber) {
        this.projMemberNumber = projMemberNumber;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Project)) {
            return false;
        }
        return id != null && id.equals(((Project) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Project{" +
            "id=" + getId() +
            ", projName='" + getProjName() + "'" +
            ", projCreatedDate='" + getProjCreatedDate() + "'" +
            ", projModifiedDate='" + getProjModifiedDate() + "'" +
            ", projAdminId=" + getProjAdminId() +
            ", orgId=" + getOrgId() +
            ", projMemberNumber=" + getProjMemberNumber() +
            "}";
    }
}
