package com.kamil.dev_manager.service;

import com.kamil.dev_manager.entity.Student;
import com.kamil.dev_manager.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class DbInitService implements CommandLineRunner {
    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) {
        Optional<Student>isAdmin = studentRepository.findAll().stream()
                .filter(s->s.getRole().equals("admin"))
                .findFirst();
        if (!isAdmin.isPresent()) {
            Student admin = new Student("Jan", "Kowalski", "win@wp.pl", passwordEncoder.encode("admin"), 2, "Polski", 258256);
            admin.setRole("admin");
            studentRepository.save(admin);
        }
    }
}
