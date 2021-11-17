package com.dshaw.asira.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
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

    @Column(name = "proj_member_number")
    private Long projMemberNumber;

    @ManyToOne
    private Org org;

    @ManyToOne
    @JsonIgnoreProperties(value = { "org" }, allowSetters = true)
    private Spe spe;

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

    public Org getOrg() {
        return this.org;
    }

    public void setOrg(Org org) {
        this.org = org;
    }

    public Project org(Org org) {
        this.setOrg(org);
        return this;
    }

    public Spe getSpe() {
        return this.spe;
    }

    public void setSpe(Spe spe) {
        this.spe = spe;
    }

    public Project spe(Spe spe) {
        this.setSpe(spe);
        return this;
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
            ", projMemberNumber=" + getProjMemberNumber() +
            "}";
    }
}
