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
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;

import java.time.LocalDate;
import java.util.ArrayList;

import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class AdminServiceTest {
    @Mock
    private StudentRepository studentRepository;
    @Mock
    private LectureRepository lectureRepository;
    @InjectMocks
    private AdminService adminService;

    @Test
    public void getLoggedUser() {

    }

    @Test
    public void return_student_equal_to_passed_id() {
        //given
        Student student = new Student("Jan", "Kowalski", "win@wp.pl", "admin", 2, "Fizyka", 258256, "permitAdmin");
        student.setId(5L);
        //when
        when(studentRepository.getStudentById(5L)).thenReturn(mockSingleStudent());
        //then
        String stud = new Gson().toJson(student);
        String stud2 = new Gson().toJson(adminService.getStudentById(5L));
        assertEquals(stud, stud2);
    }

    @Test
    public void return_size_of_allStudents_list() {
        when(studentRepository.findAll()).thenReturn(mockStudentList());
        List<Student> students = adminService.getAllStudents();
        Assert.assertThat(students, Matchers.hasSize(2));
    }

    @Test
    public void should_return_all_students_list() {
        when(studentRepository.findAll()).thenReturn(mockStudentList());
        String s1 = new Gson().toJson(mockStudentList());
        String s2 = new Gson().toJson(adminService.getAllStudents());
        assertEquals(s1, s2);
    }

    @Test
    public void should_accept_adding_new_lecture_by_status_ok() {
        Lecture lecture = mockSingleLecture();
        when(adminService.addNewLecture(any(Lecture.class))).thenReturn(lecture);
        assertEquals(adminService.addNewLecture(lecture), lecture);
    }

    @Test
    public void addStudent() {
        Student student = mockSingleStudent();
        when(adminService.addStudent(any(Student.class))).thenReturn(student);
        Student create = adminService.addStudent(student);
        Student created = adminService.getStudentById(create.getId());
        assertTrue(adminService.addStudent(student).getUsername().contains("Jan"));
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

    private List<Student> mockStudentList() {
        List<Student> students = new ArrayList<>();
        Student student1 = new Student("Jan", "Kowalski", "win@wp.pl", "admin", 2, "English", 258256, "permitAdmin");
        student1.setId(3L);
        students.add(student1);
        Student student2 = new Student("Mike", "Kowalski", "kon@wp.pl", "student", 2, "Biology", 258256, "permitStudent");
        student2.setId(4L);
        students.add(student2);
        return students;
    }

    private Student mockSingleStudent() {
        Student student = new Student("Jan", "Kowalski", "win@wp.pl", "admin", 2, "Fizyka", 258256, "permitAdmin");
        student.setId(5L);
        return student;
    }

    private Lecture mockSingleLecture() {
        return new Lecture("Biologia", "Anatomia czlowieka", "Marek Maciejczuk", LocalDate.of(2019, 05, 13));
    }
}