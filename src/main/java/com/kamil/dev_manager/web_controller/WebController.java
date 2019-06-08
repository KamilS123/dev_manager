package com.kamil.dev_manager.web_controller;

import com.kamil.dev_manager.entity.Student;
import com.kamil.dev_manager.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

@Controller
public class WebController {
    @Autowired
    private AdminService adminService;

    @GetMapping("/loggedUser")
    public ResponseEntity<String> loggedUser(HttpServletRequest httpServletRequest) {
        return new ResponseEntity<>(adminService.getLoggedUser(httpServletRequest), HttpStatus.OK);
    }
    @RequestMapping("/mainMenu")
    public String homePage() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Optional<Student> loggedUser = adminService.getAllStudents().stream()
                .filter(s->s.getUsername().equals(auth.getName()))
                .findFirst();
        Student student = loggedUser.get();
        String loggedRole = student.getRole();
        String returnedPlace = "";
       switch (loggedRole) {
           case "student": returnedPlace = "studentMenu";
           break;
           case "admin" : returnedPlace = "mainMenu";
           break;
       }
        return returnedPlace;
    }
    @RequestMapping("/addStudentForm")
    public String addStudentForm() {
        return "addStudentForm";
    }
    @RequestMapping("/addLectureForm")
    public String addLectureForm() {
        return "addLectureForm";
    }
}
