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
public class DbInitService implements CommandLineRunner {
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
                .filter(s->s.getRole().equals("admin"))
                .findFirst();
        if (!isAdmin.isPresent()) {
            Student admin = new Student("Jan", "Kowalski", "win@wp.pl", passwordEncoder.encode("admin"), 2, "Polski", 258256);
            admin.setRole("admin");
            studentRepository.save(admin);
        }

        //checking if lecture exists. If not than create new lecture
        int lectureSize = lectureRepository.findAll().size();
        if (lectureSize==0) {
            Lecture firstLecture = new Lecture("Biologia","Anatomia czlowieka","Marek Maciejczuk", LocalDate.of(2019,05,13));
            lectureRepository.save(firstLecture);
        }
    }
}
