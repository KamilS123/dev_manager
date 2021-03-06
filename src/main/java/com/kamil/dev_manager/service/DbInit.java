package com.kamil.dev_manager.service;

import com.kamil.dev_manager.entity.Lecture;
import com.kamil.dev_manager.entity.Student;
import com.kamil.dev_manager.repository.LectureRepository;
import com.kamil.dev_manager.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.util.Optional;

@Service
public class DbInit implements CommandLineRunner {
    @Autowired
    private StudentRepository studentRepository;
    @Autowired
    private LectureRepository lectureRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) {
        //chacking if admin exists. If no than create new admin
        Optional<Student>isAdmin = studentRepository.findAll().stream()
                .filter(s->s.getRole().equals("ROLE_ADMIN"))
                .findFirst();
        if (!isAdmin.isPresent()) {
            Student admin = new Student("Jan", "Kowalski", "win@wp.pl", passwordEncoder.encode("admin"), 2, "Fizyka", 258256,"permitAdmin");
            admin.setRole("ROLE_ADMIN");
            studentRepository.save(admin);
        }
        Optional<Student>isStudent = studentRepository.findAll().stream()
                .filter(s->s.getRole().equals("ROLE_STUDENT"))
                .findFirst();
        if (!isStudent.isPresent()) {
            Student student = new Student("Kamil", "Superson", "win@wp.pl", passwordEncoder.encode("student"), 2, "Fizyka", 258256,"permitUser");
            student.setRole("ROLE_STUDENT");
            studentRepository.save(student);
        }
        //checking if lecture exists. If not than create new lecture
        int lectureSize = lectureRepository.findAll().size();
        if (lectureSize==0) {
            Lecture firstLecture = new Lecture("Biologia","Anatomia czlowieka","Marek Maciejczuk", LocalDate.of(2019,05,13));
            lectureRepository.save(firstLecture);
        }
    }
}
