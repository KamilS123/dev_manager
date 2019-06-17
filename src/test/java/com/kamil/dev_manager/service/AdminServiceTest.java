package com.kamil.dev_manager.service;

import com.google.gson.Gson;
import com.kamil.dev_manager.entity.Lecture;
import com.kamil.dev_manager.entity.Student;
import com.kamil.dev_manager.repository.LectureRepository;
import com.kamil.dev_manager.repository.StudentRepository;
import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.time.LocalDate;
import java.util.ArrayList;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class AdminServiceTest {
    @Mock
    StudentRepository studentRepository;
    @Mock
    LectureRepository lectureRepository;
    @InjectMocks
    AdminService adminService;

    @Before
    public void init() {
        when(studentRepository.findAll()).thenReturn(mockStudentList());
        when(studentRepository.getStudentById(5L)).thenReturn(mockSingleStudent());
//        when(lectureRepository.save(any(Lecture.class).thenReturn(mockSingleLecture());
    }

    @Test
    public void getLoggedUser() {

    }

    @Test
    public void return_student_equal_to_passed_id() {
        Student student = new Student("Jan", "Kowalski", "win@wp.pl", "admin", 2, "Fizyka", 258256, "permitAdmin");
        student.setId(5L);
        String stud = new Gson().toJson(student);
        String stud2 = new Gson().toJson(adminService.getStudentById(5L));
        Assert.assertEquals(stud, stud2);
    }

    @Test
    public void return_size_of_allStudents_list() {
        List<Student> students = adminService.getAllStudents();
        Assert.assertThat(students, Matchers.hasSize(2));
    }

    @Test
    public void get_all_students() {
        String s1 = new Gson().toJson(mockStudentList());
        String s2 = new Gson().toJson(adminService.getAllStudents());
        Assert.assertEquals(s1, s2);
    }

    @Test
    public void addNewLecture() {
        String s1 = new Gson().toJson(adminService.addNewLecture(new Lecture("Biologia", "Anatomia czlowieka", "Marek Maciejczuk", LocalDate.of(2019, 05, 13))));
        String s2 = new Gson().toJson(mockSingleLecture());
        Assert.assertEquals(s1, s2);
    }

    @Test
    public void addStudent() {
//        Student student = mockSingleStudent();
//        when(studentRepository.save(any(Student.class))).thenReturn(new Student());
//        Student created = adminService.addStudent(student);
//        Assert.assertThat(created.getUsername()).isSameAs(student.getUsername());
    }

    @Test
    public void deleteStudentById() {
    }

    @Test
    public void editStudentDetails() {
    }

    @Test
    public void deleteLectureById() {
    }

    @Test
    public void editLecture() {
    }

    public List<Student> mockStudentList() {
        List<Student> students = new ArrayList<>();
        Student student1 = new Student("Jan", "Kowalski", "win@wp.pl", "admin", 2, "English", 258256, "permitAdmin");
        student1.setId(3L);
        students.add(student1);
        Student student2 = new Student("Mike", "Kowalski", "kon@wp.pl", "student", 2, "Biology", 258256, "permitStudent");
        student2.setId(4L);
        students.add(student2);
        return students;
    }

    public Student mockSingleStudent() {
        Student student = new Student("Jan", "Kowalski", "win@wp.pl", "admin", 2, "Fizyka", 258256, "permitAdmin");
        student.setId(5L);
        return student;
    }

    public Lecture mockSingleLecture() {
        return new Lecture("Biologia", "Anatomia czlowieka", "Marek Maciejczuk", LocalDate.of(2019, 05, 13));
    }
}