package com.kamil.dev_manager.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Lecture {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "lecture_id")
    private Long id;

    @Column(nullable = false, unique = true)
    private String tittle;

    @Column
    private String description;

    @Column
    private String teacher_info;

    @Column
    private LocalDate lecture_date;
    @JsonIgnore
    @ManyToMany(fetch = FetchType.LAZY,cascade = {CascadeType.PERSIST,CascadeType.MERGE},mappedBy = "lecturesSet")
    private Set<Student> studentSet = new HashSet<>();

    public Lecture() {
    }

    public Lecture(String tittle, String description, String teacher_info, LocalDate lecture_date) {
        this.tittle = tittle;
        this.description = description;
        this.teacher_info = teacher_info;
        this.lecture_date = lecture_date;
    }

    public Set<Student> getStudentSet() {
        return studentSet;
    }

    public void setStudentSet(Set<Student> studentSet) {
        this.studentSet = studentSet;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTittle() {
        return tittle;
    }

    public void setTittle(String tittle) {
        this.tittle = tittle;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTeacher_info() {
        return teacher_info;
    }

    public void setTeacher_info(String teacher_info) {
        this.teacher_info = teacher_info;
    }

    public LocalDate getLecture_date() {
        return lecture_date;
    }

    public void setLecture_date(LocalDate lecture_date) {
        this.lecture_date = lecture_date;
    }
}
