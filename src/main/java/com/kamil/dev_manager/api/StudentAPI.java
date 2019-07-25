package com.kamil.dev_manager.api;

import com.kamil.dev_manager.entity.Lecture;
import com.kamil.dev_manager.entity.Student;
import com.kamil.dev_manager.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@RestController
@RequestMapping("/user")
public class StudentAPI {
    private final Logger LOGGER = Logger.getLogger(AdminAPI.class.getName());
    @Autowired
    private StudentService studentService;

    //get all lectures
    @GetMapping("/lectures")
    private ResponseEntity<List<Lecture>> getAllLectures() {
        LOGGER.log(Level.INFO, "allLectures");
        return new ResponseEntity<>(studentService.getAllLectures(), HttpStatus.OK);
    }

    //set password
    @PutMapping("/password/{newPassword}/{repeatedPassword}")
    private ResponseEntity<Student> setPassword(@PathVariable("newPassword") String newPassword, @PathVariable("repeatedPassword") String repeatedPassword) throws Exception {
        LOGGER.log(Level.INFO, "setPassword");
        return new ResponseEntity<>(studentService.editStudentPassword(newPassword, repeatedPassword), HttpStatus.OK);
    }

    //add to attendanceList logged user
    @PostMapping("/toList/{id}")
    private ResponseEntity<Lecture> addToAttendanceList(@PathVariable("id") Long id) throws ClassNotFoundException {
        LOGGER.log(Level.INFO, "addToList");
        return new ResponseEntity<>(studentService.setAttendendenceToList(id), HttpStatus.CREATED);
    }

    @GetMapping("/attendance")
    private ResponseEntity<List<Lecture>> showPersonalAttendance() throws ClassNotFoundException {
        return new ResponseEntity<>(studentService.showStudentAttendancies(), HttpStatus.OK);
    }
}
