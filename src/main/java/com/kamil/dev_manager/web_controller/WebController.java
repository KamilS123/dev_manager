package com.kamil.dev_manager.web_controller;

import com.kamil.dev_manager.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
public class WebController {
    @Autowired
    private AdminService adminService;
    @GetMapping("/loggedUser")
    public ResponseEntity<String> loggedUser(HttpServletRequest httpServletRequest) {
        return new ResponseEntity<>(adminService.getLoggedUser(httpServletRequest), HttpStatus.OK);
    }
    @RequestMapping("/mainMenu")
    public String homePage(HttpServletRequest request, Model model) {
//        String ok = request.getParameter("name");
//        String ok2 = request.getParameter("name2");
//        model.addAttribute("okno",message(ok));
//        model.addAttribute("okno2","czesc2 ");
        return "mainMenu";
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
