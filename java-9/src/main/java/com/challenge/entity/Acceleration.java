package com.challenge.entity;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "acceleration")
@EntityListeners({AuditingEntityListener.class})
public class Acceleration {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotBlank
    @NotNull
    @Column(length = 100)
    @Size(max = 100)
    private String name;

    @NotBlank
    @NotNull
    @Column(length = 50)
    @Size(max = 50)
    private String slug;

    @ManyToOne
    private Challenge challenge;

    @NotNull
    @CreatedDate
    @Column(name = "created_at")
    private Date created_at;

    @OneToMany
    private List<Candidate> candidates;
}
