package com.challenge.entity;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Entity
@Table(name = "candidate")
@EntityListeners({AuditingEntityListener.class})
public class Candidate {

    @EmbeddedId
    private CandidateId candidateId;

    @NotNull
    private int status;

    @NotNull
    @CreatedDate
    @Column(name = "created_at")
    private Date created_at;
}
