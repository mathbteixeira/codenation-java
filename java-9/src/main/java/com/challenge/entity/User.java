package com.challenge.entity;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "user")
@EntityListeners({AuditingEntityListener.class})
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "full_name", length = 100)
    @NotNull
    @NotBlank
    private String fullName;

    @Column(length = 100)
    @NotNull
    @NotBlank
    @Email
    private String email;

    @Column(length = 50)
    @NotNull
    @NotBlank
    private String nickname;

    @NotNull
    @NotBlank
    private String password;

    @NotNull
    @CreatedDate
    @Column(name = "created_at")
    private Date created_at;

    @OneToMany
    private List<Candidate> candidates;

    @OneToMany
    private List<Submission> submissions;
}
