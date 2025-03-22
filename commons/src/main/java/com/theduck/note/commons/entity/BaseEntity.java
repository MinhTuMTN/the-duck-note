package com.theduck.note.commons.entity;

import java.util.Date;

import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import lombok.Data;

@Data
@MappedSuperclass
public class BaseEntity {

    private Date createdAt;

    private Date updatedAt;

    private boolean deleted;

    @PrePersist
    public void prePersist() {
        this.createdAt = new Date();
        this.updatedAt = new Date();
        this.deleted = false;
    }

    @PreUpdate
    public void preUpdate() {
        this.updatedAt = new Date();
    }
}
