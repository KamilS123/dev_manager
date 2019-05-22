package com.kamil.dev_manager.web_controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
public class WebController {
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
}
