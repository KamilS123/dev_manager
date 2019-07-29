package com.kamil.dev_manager.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
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
    @NotBlank(message = "tittle can`t be blank")
    private String tittle;

    @Column
    @NotBlank(message = "description can`t be blank")
    private String description;

    @Column
    @NotBlank(message = "info about can`t be blank")
    private String teacherInfo;

    @Column
    private LocalDate lectureDate;
    
    @JsonIgnore
    @ManyToMany(fetch = FetchType.LAZY,cascade = {CascadeType.PERSIST,CascadeType.MERGE},mappedBy = "lecturesSet")
    private Set<Student> studentSet = new HashSet<>();

    public Lecture() {
    }

    public Lecture(String tittle, String description, String teacherInfo, LocalDate lectureDate) {
        this.tittle = tittle;
        this.description = description;
        this.teacherInfo = teacherInfo;
        this.lectureDate = lectureDate;
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

    public String getteacherInfo() {
        return teacherInfo;
    }

    public void setteacherInfo(String teacherInfo) {
        this.teacherInfo = teacherInfo;
    }

    public LocalDate getlectureDate() {
        return lectureDate;
    }

    public void setlectureDate(LocalDate lectureDate) {
        this.lectureDate = lectureDate;
    }

    @Override
    public String toString() {
        return "Lecture{" +
                "id=" + id +
                ", tittle='" + tittle + '\'' +
                ", description='" + description + '\'' +
                ", teacherInfo='" + teacherInfo + '\'' +
                ", lectureDate=" + lectureDate +
                ", studentSet=" + studentSet +
                '}';
    }
}
