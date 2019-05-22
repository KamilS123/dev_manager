package com.kamil.dev_manager.api;

import com.kamil.dev_manager.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/common")
public class CommonAPI {
    @Autowired
    private AdminService adminService;
    @GetMapping("/loggedUser")
    public ResponseEntity<String>loggedUser(HttpServletRequest httpServletRequest) {
        return new ResponseEntity<>(adminService.getLoggedUser(httpServletRequest), HttpStatus.OK);
    }
}
