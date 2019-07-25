package com.kamil.dev_manager.service;

import com.kamil.dev_manager.entity.Lecture;
import com.kamil.dev_manager.entity.Student;
import com.kamil.dev_manager.repository.LectureRepository;
import com.kamil.dev_manager.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class StudentService {
    @Autowired
    private LectureRepository lectureRepository;

    @Autowired
    private StudentRepository studentRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    private Authentication authenticationPrincipal;

    //fetch all elements from lecture
    public List<Lecture> getAllLectures() {
        return lectureRepository.findAll();
    }

    //edit logged user password by passing twice the same details
    public Student editStudentPassword(String newPassword, String repeatedNewPassword) throws Exception {
        //authentication logged user
        authenticationPrincipal  = SecurityContextHolder.getContext().getAuthentication();
        Student student;
        String authenticatedName = authenticationPrincipal.getName();

        //compare passed password if not equals throw exception
        if (newPassword.equals(repeatedNewPassword)) {
            //check if user have logged details
            Optional<Long> checkLoggedStudent = Optional.of(studentRepository.findAll().stream()
                    .filter(s -> s.getUsername().equals(authenticatedName))
                    .map(Student::getId)
                    .findFirst()
                    .orElseThrow(() -> new UsernameNotFoundException("there is no logged user")));

                Long studentId = checkLoggedStudent.get();
                student = studentRepository.getStudentById(studentId);
                student.setPassword(passwordEncoder.encode(newPassword));
                studentRepository.save(student);
        }else {
            throw new Exception("passwords not equals");
        }
        return student;
    }
    public Lecture setAttendendenceToList(Long id) throws ClassNotFoundException {
        //check choosen Lecture if exist
        Optional<Lecture>listWithLectures = Optional.of(lectureRepository.findAll().stream()
                .filter(s -> s.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new ClassNotFoundException("There is no lecture with passed id!!!")));
        Lecture choosenLecture = listWithLectures.get();

        //check authenticated user if exist
        authenticationPrincipal = SecurityContextHolder.getContext().getAuthentication();
        String authenticatedName = authenticationPrincipal.getName();

        Optional<Student>listWithStudent = Optional.of(studentRepository.findAll().stream()
                .filter(s->s.getUsername().equals(authenticatedName))
                .findFirst()
                .orElseThrow(()->new ClassNotFoundException("There is no logged user!!!")));
        Student loggedStudent = listWithStudent.get();

        //save to database
        choosenLecture.getStudentSet().add(loggedStudent);
        loggedStudent.getLecturesSet().add(choosenLecture);
        studentRepository.save(loggedStudent);
        lectureRepository.save(choosenLecture);
        return choosenLecture;
    }
    public List<Lecture> showStudentAttendancies() throws ClassNotFoundException {
        authenticationPrincipal = SecurityContextHolder.getContext().getAuthentication();
        Optional<Student>loggedStudent = Optional.of(studentRepository.findAll().stream()
                .filter(s -> s.getUsername().equals(authenticationPrincipal.getName()))
                .findFirst()
                .orElseThrow(() -> new ClassNotFoundException("There is no logged user")));
        Student student = loggedStudent.get();
        Long studentId = student.getId();

        List<Long>lecturesOnStudentList = studentRepository.allLecturesOnList(studentId);

        List<Lecture>allLecturesList = lectureRepository.findAll();

        List<Lecture>personalStudentListWithAttendancies = new ArrayList<>();
        int indexing = 0;

        for(Lecture ids : allLecturesList) {
            for(Long studId : lecturesOnStudentList) {
                if (ids.getId()==studId) {
                    personalStudentListWithAttendancies.add(ids);
                    indexing++;
                }
            }
        }

        System.out.println("Student ajdi to" + studentId + "\n");

        for(Long l : lecturesOnStudentList) {
            System.out.println("lectures on student list " + l);

        }
        return personalStudentListWithAttendancies;
    }
}
