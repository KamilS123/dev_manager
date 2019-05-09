package com.kamil.dev_manager.service;

import com.kamil.dev_manager.entity.Lecture;
import com.kamil.dev_manager.entity.Student;
import com.kamil.dev_manager.repository.LectureRepository;
import com.kamil.dev_manager.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminService {
    @Autowired
    private StudentRepository studentRepository;
    @Autowired
    private LectureRepository lectureRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    public Student getStudentById(Long id) {
        return studentRepository.getStudentById(id);
    }
    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }
    public Lecture addNewLecture(Lecture lecture) {
        lectureRepository.save(lecture);
        return lecture;
    }
}
