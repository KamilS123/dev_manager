package com.kamil.dev_manager.service;

import com.kamil.dev_manager.entity.Lecture;
import com.kamil.dev_manager.repository.LectureRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService {
    @Autowired
    private LectureRepository lectureRepository;

    public List<Lecture>getAllLectures() {
        return lectureRepository.findAll();
    }
}
