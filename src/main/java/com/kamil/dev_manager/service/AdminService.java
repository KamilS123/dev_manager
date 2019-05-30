package com.kamil.dev_manager.service;

import com.kamil.dev_manager.config.securityConfig.UserPrincipal;
import com.kamil.dev_manager.entity.Lecture;
import com.kamil.dev_manager.entity.Student;
import com.kamil.dev_manager.repository.LectureRepository;
import com.kamil.dev_manager.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.w3c.dom.UserDataHandler;

import javax.servlet.http.HttpServletRequest;
import java.rmi.NoSuchObjectException;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class AdminService {
    @Autowired
    private StudentRepository studentRepository;
    @Autowired
    private LectureRepository lectureRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    //return loggged user name
    public String getLoggedUser(HttpServletRequest request) {
        String loggedUser = "";
        try {
            loggedUser = request.getUserPrincipal().getName();
        } catch (Exception ex) {
            System.out.println("User is not logged in");
        }
        return loggedUser;
    }
    //fetch one student by passing id
    public Student getStudentById(Long id) {
        return studentRepository.getStudentById(id);
    }

    //fetch all students
    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    //adding new lecture to database
    public Lecture addNewLecture(Lecture lecture) {
        lectureRepository.save(lecture);
        return lecture;
    }

    //adding new student to database
    public Student addStudent(Student student) {
        //set up constatn schema
        student.setRole("student");
        student.setPassword(passwordEncoder.encode(student.getPassword()));
        return studentRepository.save(student);
    }

    //delete student from database by passing id
    public void deleteStudentById(Long id) {
        studentRepository.deleteById(id);
    }

    //edit student by pass id and student details
    public Student editStudentDetails(Long id, Student student) {
        Optional<Student> studentToEdit = studentRepository.findById(id);
        Student editedStudent = studentToEdit.get();
        editedStudent.setPassword(passwordEncoder.encode(student.getPassword()));
        editedStudent.setRole("student");
        editedStudent.setEmail(student.getEmail());
        editedStudent.setIndex_number(student.getIndex_number());
        editedStudent.setSurname(student.getSurname());
        editedStudent.setName_of_studies(student.getName_of_studies());
        editedStudent.setYear_of_studies(student.getYear_of_studies());
        editedStudent.setUsername(student.getUsername());
        return studentRepository.save(editedStudent);
    }

    //delete lecture from databese by passing id
    public void deleteLectureById(Long id) {
        //check if lecture with passed id exists
        Lecture lectureToDelete = lectureRepository.findAll().stream()
                .filter(s -> s.getId().equals(id))
                .findAny()
                .orElseThrow(() -> new NoSuchElementException("There is no choosen user!!!"));

        //compare lectures date
        LocalDate lectureDate = lectureToDelete.getLecture_date();
        LocalDate currentDate = LocalDate.now();
        if (lectureDate.isAfter(currentDate)) {
            lectureRepository.deleteById(id);
        } else {
            throw new DateTimeException("This lecture has been already, you can not delete it");
        }
    }

    //edit lecture in database by passing id and lecture details
    public Lecture editLecture(Long id, Lecture passedLecture) {
        Optional<Lecture> lectureToEdit = lectureRepository.findById(id);
        Lecture lecture = lectureToEdit.get();
        lecture.setDescription(passedLecture.getDescription());
        lecture.setLecture_date(passedLecture.getLecture_date());
        lecture.setTeacher_info(passedLecture.getTeacher_info());
        lecture.setTittle(passedLecture.getTittle());
        return lectureRepository.save(lecture);
    }
}
