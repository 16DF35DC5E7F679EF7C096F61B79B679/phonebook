package com.harsha.phonebook.domain.model;

import jakarta.persistence.*;
import lombok.Getter;

import java.util.Date;

@Getter
@MappedSuperclass
public class BaseEntity {
    @Column(name = "id")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long id;
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "created_at")
    protected Date createdAt = new Date();
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "deleted_at")
    protected Date deletedAt;
}
