package com.dshaw.asira.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serializable;
import java.time.Instant;
import javax.persistence.*;

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

    @Column(name = "spe_name_sm_tag_id")
    private String speNameSmTagId;

    @Column(name = "sm_logged_time")
    private Long smLoggedTime;

    @JsonIgnoreProperties(value = { "project" }, allowSetters = true)
    @OneToOne
    @JoinColumn(unique = true)
    private Tag tag;

    @ManyToOne
    @JsonIgnoreProperties(value = { "org" }, allowSetters = true)
    private Spe spe;

    @ManyToOne
    @JsonIgnoreProperties(value = { "spe", "project" }, allowSetters = true)
    private Modules module;

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

    public String getSpeNameSmTagId() {
        return this.speNameSmTagId;
    }

    public SubModules speNameSmTagId(String speNameSmTagId) {
        this.setSpeNameSmTagId(speNameSmTagId);
        return this;
    }

    public void setSpeNameSmTagId(String speNameSmTagId) {
        this.speNameSmTagId = speNameSmTagId;
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

    public Tag getTag() {
        return this.tag;
    }

    public void setTag(Tag tag) {
        this.tag = tag;
    }

    public SubModules tag(Tag tag) {
        this.setTag(tag);
        return this;
    }

    public Spe getSpe() {
        return this.spe;
    }

    public void setSpe(Spe spe) {
        this.spe = spe;
    }

    public SubModules spe(Spe spe) {
        this.setSpe(spe);
        return this;
    }

    public Modules getModule() {
        return this.module;
    }

    public void setModule(Modules modules) {
        this.module = modules;
    }

    public SubModules module(Modules modules) {
        this.setModule(modules);
        return this;
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
            ", smCreatedDate='" + getSmCreatedDate() + "'" +
            ", smModifiedDate='" + getSmModifiedDate() + "'" +
            ", smDueDate='" + getSmDueDate() + "'" +
            ", smFinishedDate='" + getSmFinishedDate() + "'" +
            ", smCompleted='" + getSmCompleted() + "'" +
            ", speNameSmTagId='" + getSpeNameSmTagId() + "'" +
            ", smLoggedTime=" + getSmLoggedTime() +
            "}";
    }
}
