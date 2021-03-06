package com.kamil.dev_manager.api;

import com.kamil.dev_manager.entity.Lecture;
import com.kamil.dev_manager.entity.Student;
import com.kamil.dev_manager.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Email;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@RestController
@RequestMapping("/admin")
public class AdminAPI {

    private final Logger LOGGER = Logger.getLogger(AdminAPI.class.getName());

    @Autowired
    private AdminService adminService;

    //find student by id
    @GetMapping("/student/{id}")
    public ResponseEntity<Student> getStudentById(@PathVariable("id") Long id) {
        LOGGER.log(Level.INFO, "getStudentById");
        return new ResponseEntity<>(adminService.getStudentById(id), HttpStatus.OK);
    }

    //find all students
    @GetMapping("/students")
    public ResponseEntity<List<Student>> getAllStudents() {
        LOGGER.log(Level.INFO, "getAllStudents");
        return new ResponseEntity<>(adminService.getAllStudents(), HttpStatus.OK);
    }

    //add new Student
    @PostMapping("/newStudent")
    public ResponseEntity<Student> addStudent(@RequestBody Student student) {
        LOGGER.log(Level.INFO, "addStudent");
        return new ResponseEntity<>(adminService.addStudent(student), HttpStatus.CREATED);
    }
    /*@PostMapping("/newStudent")
    public Student addStudent(@RequestBody @Valid Student student, BindingResult bindingResult) {
        *//*if (bindingResult.hasErrors()) {
            return "excepFile";
        }*//*
        LOGGER.log(Level.INFO, "addStudent");

        return adminService.addStudent(student);
    }*/

    //delete student by id
    @DeleteMapping("/student/{id}")
    public HttpStatus deleteStudentById(@PathVariable("id") Long id) {
        LOGGER.log(Level.INFO, "deleteStudent");
        adminService.deleteStudentById(id);
        return HttpStatus.NO_CONTENT;
    }

    //edit student details
    @PutMapping("/student/{id}")
    public ResponseEntity<Student> editStudent(@PathVariable("id") Long id, @RequestBody Student student) {
        LOGGER.log(Level.INFO, "editStudent");
        return new ResponseEntity<>(adminService.editStudentDetails(id, student), HttpStatus.OK);
    }

    //add new Lecture
    @PostMapping("/newLecture")
    public ResponseEntity<Lecture> addLecture(@Valid @RequestBody Lecture lecture) {
        LOGGER.log(Level.INFO, "addLecture");
        return new ResponseEntity<>(adminService.addNewLecture(lecture), HttpStatus.CREATED);
    }

    //delete lecture by id
    @DeleteMapping("/lecture/{id}")
    public HttpStatus deleteLectureById(@PathVariable("id") Long id) {
        LOGGER.log(Level.INFO, "deleteLecture");
        adminService.deleteLectureById(id);
        return HttpStatus.NO_CONTENT;
    }

    //edit lecture by id
    @PutMapping("/lecture/{id}")
    public ResponseEntity<Lecture> editLecture(@PathVariable("id") Long id, @RequestBody Lecture lecture) {
        LOGGER.log(Level.INFO, "editLecture");
        return new ResponseEntity<>(adminService.editLecture(id, lecture), HttpStatus.OK);
    }
}
