package com.kamil.dev_manager.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.util.*;

@Entity
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "student_id")
    private Long id;

    @Column(nullable = false,unique = true)
    @NotBlank(message = "username can not be blank")
    private String username;

    @Column(nullable = false)
    @NotBlank(message = "surname can not be blank")
    private String surname;

    @Column(unique = true)
    private String email;

    @Column(nullable = false)
    private String password;

    private String role = "";

    @Column
    private Integer yearOfStudies;

    @Column
    private String nameOfStudies;

    @Column
    private Integer indexNumber;

    private String permissions = "";

    @JsonIgnore
    @ManyToMany(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST,CascadeType.MERGE})
    @JoinTable(name = "attendance_list",
            joinColumns = @JoinColumn(name = "student_id", referencedColumnName = "student_id"),
            inverseJoinColumns = @JoinColumn(name = "lecture_id", referencedColumnName = "lecture_id"))
    private Set<Lecture>lecturesSet = new HashSet<>();

    public Student() {
    }

    public Student(String username, String surname, String email, String password, Integer yearOfStudies, String nameOfStudies, Integer indexNumber, String permissions) {
        this.username = username;
        this.surname = surname;
        this.email = email;
        this.password = password;
        this.yearOfStudies = yearOfStudies;
        this.nameOfStudies = nameOfStudies;
        this.indexNumber = indexNumber;
        this.permissions = permissions;
    }

    public List<String>getRolesList() {
        if (this.role.length()>0) {
            return Arrays.asList(this.role.split(","));
        }return new ArrayList<>();
    }

    public List<String>getPermisionsList() {
        if (this.permissions.length() > 0) {
            return Arrays.asList(this.permissions.split(","));
        }return new ArrayList<>();
    }

    public String getPermissions() {
        return permissions;
    }

    public void setPermissions(String permissions) {
        this.permissions = permissions;
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

    public Integer getYearOfStudies() {
        return yearOfStudies;
    }

    public void setYearOfStudies(Integer yearOfStudies) {
        this.yearOfStudies = yearOfStudies;
    }

    public String getNameOfStudies() {
        return nameOfStudies;
    }

    public void setNameOfStudies(String nameOfStudies) {
        this.nameOfStudies = nameOfStudies;
    }

    public Integer getIndexNumber() {
        return indexNumber;
    }

    public void setIndexNumber(Integer indexNumber) {
        this.indexNumber = indexNumber;
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
                ", yearOfStudies=" + yearOfStudies +
                ", nameOfStudies='" + nameOfStudies + '\'' +
                ", indexNumber=" + indexNumber +
                ", permisions='" + permissions + '\'' +
                ", lecturesSet=" + lecturesSet +
                '}';
    }
}
