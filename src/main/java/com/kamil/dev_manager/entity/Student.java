package com.kamil.dev_manager.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.*;

@Entity
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "student_id")
    private Long id;

    @Column(nullable = false,unique = true)
    private String username;

    @Column(nullable = false)
    private String surname;

    @Column(unique = true)
    private String email;

    @Column(nullable = false)
    private String password;

    private String role = "";

    @Column
    private Integer year_of_studies;

    @Column
    private String name_of_studies;

    @Column
    private Integer index_number;

    private String permisions = "";

    @JsonIgnore
    @ManyToMany(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST,CascadeType.MERGE})
    @JoinTable(name = "attendance_list",
            joinColumns = @JoinColumn(name = "student_id", referencedColumnName = "student_id"),
            inverseJoinColumns = @JoinColumn(name = "lecture_id", referencedColumnName = "lecture_id"))
    private Set<Lecture>lecturesSet = new HashSet<>();

    public Student() {
    }

    public Student(String username, String surname, String email, String password, Integer year_of_studies, String name_of_studies, Integer index_number, String permisions) {
        this.username = username;
        this.surname = surname;
        this.email = email;
        this.password = password;
        this.year_of_studies = year_of_studies;
        this.name_of_studies = name_of_studies;
        this.index_number = index_number;
        this.permisions = permisions;
        this.role = "ROLE_STUDENT";
    }

    public List<String>getRolesList() {
        if (this.role.length()>0) {
            return Arrays.asList(this.role.split(","));
        }return new ArrayList<>();
    }

    public List<String>getPermisionsList() {
        if (this.permisions.length() > 0) {
            return Arrays.asList(this.permisions.split(","));
        }return new ArrayList<>();
    }
    public Set<Lecture> getLecturesSet() {
        return lecturesSet;
    }

    public void setLecturesSet(Set<Lecture> lecturesSet) {
        this.lecturesSet = lecturesSet;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public Integer getYear_of_studies() {
        return year_of_studies;
    }

    public void setYear_of_studies(Integer year_of_studies) {
        this.year_of_studies = year_of_studies;
    }

    public String getName_of_studies() {
        return name_of_studies;
    }

    public void setName_of_studies(String name_of_studies) {
        this.name_of_studies = name_of_studies;
    }

    public Integer getIndex_number() {
        return index_number;
    }

    public void setIndex_number(Integer index_number) {
        this.index_number = index_number;
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", surname='" + surname + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", role='" + role + '\'' +
                ", year_of_studies=" + year_of_studies +
                ", name_of_studies='" + name_of_studies + '\'' +
                ", index_number=" + index_number +
                ", permisions='" + permisions + '\'' +
                ", lecturesSet=" + lecturesSet +
                '}';
    }
}
