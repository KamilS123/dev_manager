package com.kamil.dev_manager.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Entity
public class Lecture {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String tittle;

    @Column
    private String description;

    @Column
    private String teacher_info;

    @Column
    private LocalDate lecture_date;

    public Lecture() {
    }

    public Lecture(String tittle, String description, String teacher_info, LocalDate lecture_date) {
        this.tittle = tittle;
        this.description = description;
        this.teacher_info = teacher_info;
        this.lecture_date = lecture_date;
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
