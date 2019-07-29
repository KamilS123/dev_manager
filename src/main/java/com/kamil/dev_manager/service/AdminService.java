package com.kamil.dev_manager.service;

import com.kamil.dev_manager.entity.Lecture;
import com.kamil.dev_manager.entity.Student;
import com.kamil.dev_manager.repository.LectureRepository;
import com.kamil.dev_manager.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
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
        return lectureRepository.save(lecture);
    }

    //adding new student to database
    public Student addStudent(Student student) {
        Optional<Student> studentInDatabase = studentRepository.findAll().stream()
                .filter(s -> s.getUsername().equals(student.getUsername()))
                .findFirst();
        if (studentInDatabase.isPresent()) {
            throw new IllegalArgumentException("There is passed username already in database");
        } else {
            //set up constant schema
            student.setRole("ROLE_STUDENT");
            student.setPermissions("permitUser");
            student.setPassword(passwordEncoder.encode(student.getPassword()));
        }
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
        if (studentToEdit.isPresent()) {
            editedStudent.setPassword(passwordEncoder.encode(student.getPassword()));
            editedStudent.setRole("ROLE_STUDENT");
            editedStudent.setEmail(student.getEmail());
            editedStudent.setIndexNumber(student.getIndexNumber());
            editedStudent.setSurname(student.getSurname());
            editedStudent.setNameOfStudies(student.getNameOfStudies());
            editedStudent.setYearOfStudies(student.getYearOfStudies());
            editedStudent.setUsername(student.getUsername());
        }else {
            throw new NullPointerException("Student is not present");
        }
        return studentRepository.save(editedStudent);
    }

    public void deleteLectureById(Long id) {
        //check if lecture with passed id exists
        Lecture lectureToDelete = lectureRepository.findAll().stream()
                .filter(s -> s.getId().equals(id))
                .findAny()
                .orElseThrow(() -> new NoSuchElementException("There is no choosen lecture!!!"));
        //compare lectures date
        LocalDate lectureDate = lectureToDelete.getlectureDate();
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
        lecture.setlectureDate(passedLecture.getlectureDate());
        lecture.setteacherInfo(passedLecture.getteacherInfo());
        lecture.setTittle(passedLecture.getTittle());
        return lectureRepository.save(lecture);
    }
}
