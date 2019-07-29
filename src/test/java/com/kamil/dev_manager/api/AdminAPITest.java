package com.kamil.dev_manager.api;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.kamil.dev_manager.entity.Lecture;
import com.kamil.dev_manager.entity.Student;
import com.kamil.dev_manager.repository.LectureRepository;
import com.kamil.dev_manager.repository.StudentRepository;
import com.kamil.dev_manager.service.AdminService;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;
import static org.mockito.internal.verification.VerificationModeFactory.times;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@RunWith(SpringRunner.class)
//@WebMvcTest(AdminAPI.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc(secure = false)
class AdminAPITest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private StudentRepository studentRepository;

    @MockBean
    private LectureRepository lectureRepository;

    @MockBean
    private AdminService adminService;

    @Test
    void should_return_student_by_passed_id() throws Exception {
        Student student = mockSingleStudent();
        when(adminService.getStudentById(6L)).thenReturn(student);
        this.mockMvc.perform(get("/admin/student/6"))
                .andDo(print())
//                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(6)))
                .andExpect(jsonPath("$.username", is("Jan")))
                .andExpect(jsonPath("$.surname", is("Kowalski")))
                .andExpect(jsonPath("$.email", is("win@wp.pl")))
                .andExpect(jsonPath("$.password", is("admin")))
                .andExpect(jsonPath("$.yearOfStudies", is(2)))
                .andExpect(jsonPath("$.nameOfStudies", is("English")))
                .andExpect(jsonPath("$.indexNumber", is(258256)));
        verify(adminService, times(1)).getStudentById(6L);
    }

    @Test
    void should_return_size_of_users_list() throws Exception {
        List<Student> students = mockStudentList();
        when(adminService.getAllStudents()).thenReturn(students);
        mockMvc.perform(get("/admin/students"))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$", hasSize(2)));
        verify(adminService, times(1)).getAllStudents();
    }

    @Test
    void should_return_all_students_list() throws Exception {
        //given
        List<Student> students = mockStudentList();
        given(adminService.getAllStudents()).willReturn(students);
        //when+then
        this.mockMvc.perform(get("/admin/students"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$[0].id").value(3))
                .andExpect(jsonPath("$[0].username").value("Jan"))
                .andExpect(jsonPath("$[0].surname").value("Kowalski"))
                .andExpect(jsonPath("$[0].email").value("win@wp.pl"))
                .andExpect(jsonPath("$[0].password").value("admin"))
                .andExpect(jsonPath("$[0].yearOfStudies").value("2"))
                .andExpect(jsonPath("$[0].nameOfStudies").value("English"))
                .andExpect(jsonPath("$[0].indexNumber").value(258256))
                .andExpect(jsonPath("$[1].id").value(4))
                .andExpect(jsonPath("$[1].username").value("Mike"))
                .andExpect(jsonPath("$[1].surname").value("Nowicki"))
                .andExpect(jsonPath("$[1].email").value("kon@wp.pl"))
                .andExpect(jsonPath("$[1].password").value("student"))
                .andExpect(jsonPath("$[1].yearOfStudies").value("4"))
                .andExpect(jsonPath("$[1].nameOfStudies").value("Biology"))
                .andExpect(jsonPath("$[1].indexNumber").value(123456));
        verify(adminService, times(1)).getAllStudents();
    }

    @Test
    void should_check_if_student_is_added_correctly() throws Exception {
        Student student = mockSingleStudent();
        when(studentRepository.save(any(Student.class))).thenReturn(student);
        mockMvc.perform(post("/admin/newStudent")
                .content(String.valueOf(new Gson().toJson(student)))
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().isCreated());
    }

    @Test
    void should_check_if_student_may_be_deleted_by_id() throws Exception {
        this.mockMvc.perform(delete("/admin/student/6"))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk());
    }

    @Test
    void should_check_if_student_can_be_edited_with_passed_details() throws Exception {
        Student student = mockSingleStudent();
        when(studentRepository.save(any(Student.class))).thenReturn(student);
        mockMvc.perform(put("/admin/student/6")
                .content(String.valueOf(new Gson().toJson(student)))
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk());
    }

    @Test
    void should_check_if_lecture_is_added_correctly() throws Exception {
        Lecture lecture = mockSingleLecture();
        when(lectureRepository.save(any(Lecture.class))).thenReturn(lecture);
        mockMvc.perform(post("/admin/newLecture")
                .content(String.valueOf(new Gson().toJson(lecture)))
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().isCreated());
    }

    @Test
    void deleteLectureById() throws Exception {
        this.mockMvc.perform(delete("/admin/lecture/5"))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk());
    }

    @Test
    void should_check_if_lecture_can_be_edited_with_passed_details() throws Exception {
        Lecture lecture = mockSingleLecture();
        when(lectureRepository.save(any(Lecture.class))).thenReturn(lecture);
        mockMvc.perform(put("/admin/lecture/5")
                .content(String.valueOf(new Gson().toJson(lecture)))
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk());
    }

    private List<Student> mockStudentList() {
        List<Student> students = new ArrayList<>();
        Student student1 = new Student("Jan", "Kowalski", "win@wp.pl", "admin", 2, "English", 258256, "permitAdmin");
        student1.setId(3L);
        students.add(student1);
        Student student2 = new Student("Mike", "Nowicki", "kon@wp.pl", "student", 4, "Biology", 123456, "permitStudent");
        student2.setId(4L);
        students.add(student2);
        return students;
    }

    private Student mockSingleStudent() {
        Student student = new Student("Jan", "Kowalski", "win@wp.pl", "admin", 2, "English", 258256, "permitAdmin");
        student.setId(6L);
        return student;
    }

    private Lecture mockSingleLecture() {
        Lecture lecture = new Lecture("Biology", "Anathomy", "Marek Maciejczuk", LocalDate.of(2019, 05, 13));
        lecture.setId(5L);
        return lecture;
    }
}