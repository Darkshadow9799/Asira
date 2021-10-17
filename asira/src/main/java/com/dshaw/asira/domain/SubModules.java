package com.dshaw.asira.domain;

import java.io.Serializable;
import java.time.Instant;
import javax.persistence.*;
import javax.validation.constraints.*;

/**
 * A SubModules.
 */
@Entity
@Table(name = "sub_modules")
public class SubModules implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "sm_name")
    private String smName;

    @Column(name = "sm_desc")
    private String smDesc;

    @NotNull
    @Column(name = "sm_m_id", nullable = false)
    private Long smMId;

    @NotNull
    @Column(name = "sm_spe_id", nullable = false)
    private Long smSpeId;

    @Column(name = "sm_created_date")
    private Instant smCreatedDate;

    @Column(name = "sm_modified_date")
    private Instant smModifiedDate;

    @Column(name = "sm_due_date")
    private Instant smDueDate;

    @Column(name = "sm_finished_date")
    private Instant smFinishedDate;

    @Column(name = "sm_completed")
    private Boolean smCompleted;

    @Column(name = "sm_tag_id")
    private Long smTagID;

    @Column(name = "spe_id_sm_tag_id")
    private Long speIdSmTagId;

    @Column(name = "sm_logged_time")
    private Long smLoggedTime;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public SubModules id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSmName() {
        return this.smName;
    }

    public SubModules smName(String smName) {
        this.setSmName(smName);
        return this;
    }

    public void setSmName(String smName) {
        this.smName = smName;
    }

    public String getSmDesc() {
        return this.smDesc;
    }

    public SubModules smDesc(String smDesc) {
        this.setSmDesc(smDesc);
        return this;
    }

    public void setSmDesc(String smDesc) {
        this.smDesc = smDesc;
    }

    public Long getSmMId() {
        return this.smMId;
    }

    public SubModules smMId(Long smMId) {
        this.setSmMId(smMId);
        return this;
    }

    public void setSmMId(Long smMId) {
        this.smMId = smMId;
    }

    public Long getSmSpeId() {
        return this.smSpeId;
    }

    public SubModules smSpeId(Long smSpeId) {
        this.setSmSpeId(smSpeId);
        return this;
    }

    public void setSmSpeId(Long smSpeId) {
        this.smSpeId = smSpeId;
    }

    public Instant getSmCreatedDate() {
        return this.smCreatedDate;
    }

    public SubModules smCreatedDate(Instant smCreatedDate) {
        this.setSmCreatedDate(smCreatedDate);
        return this;
    }

    public void setSmCreatedDate(Instant smCreatedDate) {
        this.smCreatedDate = smCreatedDate;
    }

    public Instant getSmModifiedDate() {
        return this.smModifiedDate;
    }

    public SubModules smModifiedDate(Instant smModifiedDate) {
        this.setSmModifiedDate(smModifiedDate);
        return this;
    }

    public void setSmModifiedDate(Instant smModifiedDate) {
        this.smModifiedDate = smModifiedDate;
    }

    public Instant getSmDueDate() {
        return this.smDueDate;
    }

    public SubModules smDueDate(Instant smDueDate) {
        this.setSmDueDate(smDueDate);
        return this;
    }

    public void setSmDueDate(Instant smDueDate) {
        this.smDueDate = smDueDate;
    }

    public Instant getSmFinishedDate() {
        return this.smFinishedDate;
    }

    public SubModules smFinishedDate(Instant smFinishedDate) {
        this.setSmFinishedDate(smFinishedDate);
        return this;
    }

    public void setSmFinishedDate(Instant smFinishedDate) {
        this.smFinishedDate = smFinishedDate;
    }

    public Boolean getSmCompleted() {
        return this.smCompleted;
    }

    public SubModules smCompleted(Boolean smCompleted) {
        this.setSmCompleted(smCompleted);
        return this;
    }

    public void setSmCompleted(Boolean smCompleted) {
        this.smCompleted = smCompleted;
    }

    public Long getSmTagID() {
        return this.smTagID;
    }

    public SubModules smTagID(Long smTagID) {
        this.setSmTagID(smTagID);
        return this;
    }

    public void setSmTagID(Long smTagID) {
        this.smTagID = smTagID;
    }

    public Long getSpeIdSmTagId() {
        return this.speIdSmTagId;
    }

    public SubModules speIdSmTagId(Long speIdSmTagId) {
        this.setSpeIdSmTagId(speIdSmTagId);
        return this;
    }

    public void setSpeIdSmTagId(Long speIdSmTagId) {
        this.speIdSmTagId = speIdSmTagId;
    }

    public Long getSmLoggedTime() {
        return this.smLoggedTime;
    }

    public SubModules smLoggedTime(Long smLoggedTime) {
        this.setSmLoggedTime(smLoggedTime);
        return this;
    }

    public void setSmLoggedTime(Long smLoggedTime) {
        this.smLoggedTime = smLoggedTime;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof SubModules)) {
            return false;
        }
        return id != null && id.equals(((SubModules) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "SubModules{" +
            "id=" + getId() +
            ", smName='" + getSmName() + "'" +
            ", smDesc='" + getSmDesc() + "'" +
            ", smMId=" + getSmMId() +
            ", smSpeId=" + getSmSpeId() +
            ", smCreatedDate='" + getSmCreatedDate() + "'" +
            ", smModifiedDate='" + getSmModifiedDate() + "'" +
            ", smDueDate='" + getSmDueDate() + "'" +
            ", smFinishedDate='" + getSmFinishedDate() + "'" +
            ", smCompleted='" + getSmCompleted() + "'" +
            ", smTagID=" + getSmTagID() +
            ", speIdSmTagId=" + getSpeIdSmTagId() +
            ", smLoggedTime=" + getSmLoggedTime() +
            "}";
    }
}
