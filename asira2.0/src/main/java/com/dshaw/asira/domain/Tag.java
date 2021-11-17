package com.dshaw.asira.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serializable;
import java.time.Instant;
import javax.persistence.*;

/**
 * A Tag.
 */
@Entity
@Table(name = "tag")
public class Tag implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "tag_title")
    private String tagTitle;

    @Column(name = "tag_desc")
    private String tagDesc;

    @Column(name = "tag_created_by_id")
    private Long tagCreatedById;

    @Column(name = "tag_created_by_name")
    private String tagCreatedByName;

    @Column(name = "tag_created_date")
    private Instant tagCreatedDate;

    @Column(name = "tag_modified_date")
    private Instant tagModifiedDate;

    @JsonIgnoreProperties(value = { "org", "spe" }, allowSetters = true)
    @OneToOne
    @JoinColumn(unique = true)
    private Project project;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public Tag id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTagTitle() {
        return this.tagTitle;
    }

    public Tag tagTitle(String tagTitle) {
        this.setTagTitle(tagTitle);
        return this;
    }

    public void setTagTitle(String tagTitle) {
        this.tagTitle = tagTitle;
    }

    public String getTagDesc() {
        return this.tagDesc;
    }

    public Tag tagDesc(String tagDesc) {
        this.setTagDesc(tagDesc);
        return this;
    }

    public void setTagDesc(String tagDesc) {
        this.tagDesc = tagDesc;
    }

    public Long getTagCreatedById() {
        return this.tagCreatedById;
    }

    public Tag tagCreatedById(Long tagCreatedById) {
        this.setTagCreatedById(tagCreatedById);
        return this;
    }

    public void setTagCreatedById(Long tagCreatedById) {
        this.tagCreatedById = tagCreatedById;
    }

    public String getTagCreatedByName() {
        return this.tagCreatedByName;
    }

    public Tag tagCreatedByName(String tagCreatedByName) {
        this.setTagCreatedByName(tagCreatedByName);
        return this;
    }

    public void setTagCreatedByName(String tagCreatedByName) {
        this.tagCreatedByName = tagCreatedByName;
    }

    public Instant getTagCreatedDate() {
        return this.tagCreatedDate;
    }

    public Tag tagCreatedDate(Instant tagCreatedDate) {
        this.setTagCreatedDate(tagCreatedDate);
        return this;
    }

    public void setTagCreatedDate(Instant tagCreatedDate) {
        this.tagCreatedDate = tagCreatedDate;
    }

    public Instant getTagModifiedDate() {
        return this.tagModifiedDate;
    }

    public Tag tagModifiedDate(Instant tagModifiedDate) {
        this.setTagModifiedDate(tagModifiedDate);
        return this;
    }

    public void setTagModifiedDate(Instant tagModifiedDate) {
        this.tagModifiedDate = tagModifiedDate;
    }

    public Project getProject() {
        return this.project;
    }

    public void setProject(Project project) {
        this.project = project;
    }

    public Tag project(Project project) {
        this.setProject(project);
        return this;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Tag)) {
            return false;
        }
        return id != null && id.equals(((Tag) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Tag{" +
            "id=" + getId() +
            ", tagTitle='" + getTagTitle() + "'" +
            ", tagDesc='" + getTagDesc() + "'" +
            ", tagCreatedById=" + getTagCreatedById() +
            ", tagCreatedByName='" + getTagCreatedByName() + "'" +
            ", tagCreatedDate='" + getTagCreatedDate() + "'" +
            ", tagModifiedDate='" + getTagModifiedDate() + "'" +
            "}";
    }
}
