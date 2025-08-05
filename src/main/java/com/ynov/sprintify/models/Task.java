package com.ynov.sprintify.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ynov.sprintify.enums.StatusEnum;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@Table(name = "tasks")
@Entity
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false)
    private String title;

    private String description;

    private StatusEnum status;

    @Column(name = "due_date", nullable = false)
    private LocalDate dueDate;

    @OneToOne
    @JoinColumn(name = "assignee_id")
    private User assignee;

    @ManyToOne
    @JoinColumn(name = "sprint_id", nullable = false)
    @JsonIgnore
    private Sprint sprint;
}
