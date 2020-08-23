package com.challenge.entity;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Entity
@Table ( name = "submission" )
@EntityListeners({AuditingEntityListener.class})
public class Submission {

    @EmbeddedId
    private SubmissionId submissionId;

    @NotNull
    private float score;

    @NotNull
    @CreatedDate
    @Column(name = "created_at")
    private Date created_at;
}
