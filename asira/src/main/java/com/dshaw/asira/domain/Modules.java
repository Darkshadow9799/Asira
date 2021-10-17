package com.dshaw.asira.domain;

import java.io.Serializable;
import java.time.Instant;
import javax.persistence.*;
import javax.validation.constraints.*;

/**
 * A Modules.
 */
@Entity
@Table(name = "modules")
public class Modules implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "m_name")
    private String mName;

    @Column(name = "m_desc")
    private String mDesc;

    @NotNull
    @Column(name = "m_spe_id", nullable = false)
    private Long mSpeId;

    @NotNull
    @Column(name = "m_proj_id", nullable = false)
    private Long mProjId;

    @Column(name = "m_created_date")
    private Instant mCreatedDate;

    @Column(name = "m_modified_date")
    private Instant mModifiedDate;

    @Column(name = "m_sm_num")
    private Long mSmNum;

    @Column(name = "m_due_date")
    private Instant mDueDate;

    @Column(name = "m_finished_date")
    private Instant mFinishedDate;

    @Column(name = "m_completed")
    private Boolean mCompleted;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public Modules id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getmName() {
        return this.mName;
    }

    public Modules mName(String mName) {
        this.setmName(mName);
        return this;
    }

    public void setmName(String mName) {
        this.mName = mName;
    }

    public String getmDesc() {
        return this.mDesc;
    }

    public Modules mDesc(String mDesc) {
        this.setmDesc(mDesc);
        return this;
    }

    public void setmDesc(String mDesc) {
        this.mDesc = mDesc;
    }

    public Long getmSpeId() {
        return this.mSpeId;
    }

    public Modules mSpeId(Long mSpeId) {
        this.setmSpeId(mSpeId);
        return this;
    }

    public void setmSpeId(Long mSpeId) {
        this.mSpeId = mSpeId;
    }

    public Long getmProjId() {
        return this.mProjId;
    }

    public Modules mProjId(Long mProjId) {
        this.setmProjId(mProjId);
        return this;
    }

    public void setmProjId(Long mProjId) {
        this.mProjId = mProjId;
    }

    public Instant getmCreatedDate() {
        return this.mCreatedDate;
    }

    public Modules mCreatedDate(Instant mCreatedDate) {
        this.setmCreatedDate(mCreatedDate);
        return this;
    }

    public void setmCreatedDate(Instant mCreatedDate) {
        this.mCreatedDate = mCreatedDate;
    }

    public Instant getmModifiedDate() {
        return this.mModifiedDate;
    }

    public Modules mModifiedDate(Instant mModifiedDate) {
        this.setmModifiedDate(mModifiedDate);
        return this;
    }

    public void setmModifiedDate(Instant mModifiedDate) {
        this.mModifiedDate = mModifiedDate;
    }

    public Long getmSmNum() {
        return this.mSmNum;
    }

    public Modules mSmNum(Long mSmNum) {
        this.setmSmNum(mSmNum);
        return this;
    }

    public void setmSmNum(Long mSmNum) {
        this.mSmNum = mSmNum;
    }

    public Instant getmDueDate() {
        return this.mDueDate;
    }

    public Modules mDueDate(Instant mDueDate) {
        this.setmDueDate(mDueDate);
        return this;
    }

    public void setmDueDate(Instant mDueDate) {
        this.mDueDate = mDueDate;
    }

    public Instant getmFinishedDate() {
        return this.mFinishedDate;
    }

    public Modules mFinishedDate(Instant mFinishedDate) {
        this.setmFinishedDate(mFinishedDate);
        return this;
    }

    public void setmFinishedDate(Instant mFinishedDate) {
        this.mFinishedDate = mFinishedDate;
    }

    public Boolean getmCompleted() {
        return this.mCompleted;
    }

    public Modules mCompleted(Boolean mCompleted) {
        this.setmCompleted(mCompleted);
        return this;
    }

    public void setmCompleted(Boolean mCompleted) {
        this.mCompleted = mCompleted;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Modules)) {
            return false;
        }
        return id != null && id.equals(((Modules) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Modules{" +
            "id=" + getId() +
            ", mName='" + getmName() + "'" +
            ", mDesc='" + getmDesc() + "'" +
            ", mSpeId=" + getmSpeId() +
            ", mProjId=" + getmProjId() +
            ", mCreatedDate='" + getmCreatedDate() + "'" +
            ", mModifiedDate='" + getmModifiedDate() + "'" +
            ", mSmNum=" + getmSmNum() +
            ", mDueDate='" + getmDueDate() + "'" +
            ", mFinishedDate='" + getmFinishedDate() + "'" +
            ", mCompleted='" + getmCompleted() + "'" +
            "}";
    }
}
