package com.kamil.dev_manager.web_controller;

import com.kamil.dev_manager.entity.Lecture;
import com.kamil.dev_manager.entity.Student;
import com.kamil.dev_manager.service.AdminService;
import com.kamil.dev_manager.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.WebApplicationContext;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;

@Controller
public class WebController {

    private final Logger logger = Logger.getLogger(WebApplicationContext.class.getName());
    @Autowired
    private AdminService adminService;
    @Autowired
    private StudentService studentService;

    @GetMapping("/loggedUser")
    public ResponseEntity<String> loggedUser(HttpServletRequest httpServletRequest) {
        logger.log(Level.INFO, "loggedUser");
        return new ResponseEntity<>(adminService.getLoggedUser(httpServletRequest), HttpStatus.OK);
    }

    @RequestMapping("/mainMenu")
    public String homePage(Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Optional<Student> loggedUser = adminService.getAllStudents().stream()
                .filter(s -> s.getUsername().equals(auth.getName()))
                .findFirst();
        Student student = loggedUser.get();
        String loggedRole = student.getRole();
        String returnedPlace = "";
        switch (loggedRole) {
            case "ROLE_STUDENT":
                returnedPlace = "studentMenu";
                break;
            case "ROLE_ADMIN":
                returnedPlace = "mainMenu";
                break;
        }
        model.addAttribute("listWithLectures", studentService.getAllLectures());
        logger.log(Level.INFO, "mainMenu");
        return returnedPlace;
    }

    @RequestMapping("/addStudentForm")
    public String addStudentForm() {
        logger.log(Level.INFO, "addStudentForm");
        return "addStudentForm";
    }

    @RequestMapping("/addLectureForm")
    public String addLectureForm() {
        logger.log(Level.INFO, "addLectureForm");
        return "addLectureForm";
    }

    @RequestMapping("/selectSingleLecture")
    public String selectSingleLecture(@ModelAttribute Lecture description, Model model) throws ClassNotFoundException {
        Optional<Lecture> lectureList = Optional.of(studentService.getAllLectures().stream()
                .filter(s -> s.getDescription().equals(description.getDescription()))
                .findFirst()
                .orElseThrow(() -> new ClassNotFoundException("Lecture not found")));
        Lecture lecture = lectureList.get();
        model.addAttribute("lecture", lecture.getDescription());
        model.addAttribute("listWithLectures", studentService.getAllLectures());
        logger.log(Level.INFO, "selectSingleLecture");
        return "mainMenu";
    }
}
