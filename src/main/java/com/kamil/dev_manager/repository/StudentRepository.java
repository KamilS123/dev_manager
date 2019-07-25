package com.kamil.dev_manager.repository;

import com.kamil.dev_manager.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentRepository extends JpaRepository<Student,Long> {

    @Query(value = "from Student i where i.id=:id")
    Student getStudentById(@Param("id")Long id);

    Student findByUsername(String s);

    @Query(value = "delete from Student s where s.id=:id")
    Student deleteStudentById(@Param("id")Long id);

     @Query(value = "select lecture_id from attendance_list where student_id = ?", nativeQuery = true)
     List<Long> allLecturesOnList(@Param("student_id")Long student_id);


}
