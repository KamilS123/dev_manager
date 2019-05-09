package com.kamil.dev_manager.api;

import com.kamil.dev_manager.entity.Lecture;
import com.kamil.dev_manager.entity.Student;
import com.kamil.dev_manager.service.AdminService;
import com.kamil.dev_manager.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin")
public class AdminAPI {
    @Autowired
    private AdminService adminService;

    //find student by id
    @GetMapping("/getById/{id}")
    public ResponseEntity<Student> getStudentById(@PathVariable("id")Long id) {
        return new ResponseEntity<>(adminService.getStudentById(id), HttpStatus.OK);
    }

    //find all students
    @GetMapping("/getAll")
    public ResponseEntity<List<Student>>getAllStudents() {
        return new ResponseEntity<>(adminService.getAllStudents(),HttpStatus.OK);
    }

    //add new Lecture
    @PostMapping("/addLecture")
    public ResponseEntity<Lecture>addLecture(Lecture lecture) {
        return new ResponseEntity<>(adminService.addNewLecture(lecture),HttpStatus.CREATED);
    }
}
