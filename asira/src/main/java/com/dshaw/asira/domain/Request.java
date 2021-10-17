package com.dshaw.asira.domain;

import java.io.Serializable;
import java.time.Instant;
import javax.persistence.*;

/**
 * A Request.
 */
@Entity
@Table(name = "request")
public class Request implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "r_from")
    private Long rFrom;

    @Column(name = "r_to")
    private Long rTo;

    @Column(name = "r_pending_state")
    private Boolean rPendingState;

    @Column(name = "r_accepted")
    private Boolean rAccepted;

    @Column(name = "r_rejected")
    private Boolean rRejected;

    @Column(name = "spe_notified")
    private Boolean speNotified;

    @Column(name = "org_notified")
    private Boolean orgNotified;

    @Column(name = "created_date")
    private Instant createdDate;

    @Column(name = "modified_date")
    private Instant modifiedDate;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public Request id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getrFrom() {
        return this.rFrom;
    }

    public Request rFrom(Long rFrom) {
        this.setrFrom(rFrom);
        return this;
    }

    public void setrFrom(Long rFrom) {
        this.rFrom = rFrom;
    }

    public Long getrTo() {
        return this.rTo;
    }

    public Request rTo(Long rTo) {
        this.setrTo(rTo);
        return this;
    }

    public void setrTo(Long rTo) {
        this.rTo = rTo;
    }

    public Boolean getrPendingState() {
        return this.rPendingState;
    }

    public Request rPendingState(Boolean rPendingState) {
        this.setrPendingState(rPendingState);
        return this;
    }

    public void setrPendingState(Boolean rPendingState) {
        this.rPendingState = rPendingState;
    }

    public Boolean getrAccepted() {
        return this.rAccepted;
    }

    public Request rAccepted(Boolean rAccepted) {
        this.setrAccepted(rAccepted);
        return this;
    }

    public void setrAccepted(Boolean rAccepted) {
        this.rAccepted = rAccepted;
    }

    public Boolean getrRejected() {
        return this.rRejected;
    }

    public Request rRejected(Boolean rRejected) {
        this.setrRejected(rRejected);
        return this;
    }

    public void setrRejected(Boolean rRejected) {
        this.rRejected = rRejected;
    }

    public Boolean getSpeNotified() {
        return this.speNotified;
    }

    public Request speNotified(Boolean speNotified) {
        this.setSpeNotified(speNotified);
        return this;
    }

    public void setSpeNotified(Boolean speNotified) {
        this.speNotified = speNotified;
    }

    public Boolean getOrgNotified() {
        return this.orgNotified;
    }

    public Request orgNotified(Boolean orgNotified) {
        this.setOrgNotified(orgNotified);
        return this;
    }

    public void setOrgNotified(Boolean orgNotified) {
        this.orgNotified = orgNotified;
    }

    public Instant getCreatedDate() {
        return this.createdDate;
    }

    public Request createdDate(Instant createdDate) {
        this.setCreatedDate(createdDate);
        return this;
    }

    public void setCreatedDate(Instant createdDate) {
        this.createdDate = createdDate;
    }

    public Instant getModifiedDate() {
        return this.modifiedDate;
    }

    public Request modifiedDate(Instant modifiedDate) {
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
        if (!(o instanceof Request)) {
            return false;
        }
        return id != null && id.equals(((Request) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Request{" +
            "id=" + getId() +
            ", rFrom=" + getrFrom() +
            ", rTo=" + getrTo() +
            ", rPendingState='" + getrPendingState() + "'" +
            ", rAccepted='" + getrAccepted() + "'" +
            ", rRejected='" + getrRejected() + "'" +
            ", speNotified='" + getSpeNotified() + "'" +
            ", orgNotified='" + getOrgNotified() + "'" +
            ", createdDate='" + getCreatedDate() + "'" +
            ", modifiedDate='" + getModifiedDate() + "'" +
            "}";
    }
}
