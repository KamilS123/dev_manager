package com.kamil.dev_manager.api;

import com.kamil.dev_manager.entity.Lecture;
import com.kamil.dev_manager.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/student")
public class StudentAPI {
    @Autowired
    private StudentService studentService;

    //get all lectures
    @GetMapping("/allLectures")
    public ResponseEntity<List<Lecture>>getAllLectures() {
        return new ResponseEntity<>(studentService.getAllLectures(),HttpStatus.OK);
    }
}
