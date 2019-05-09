package com.kamil.dev_manager.repository;

import com.kamil.dev_manager.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentRepository extends JpaRepository<Student,Long> {

    @Query("from Student i where i.id=:id")
    Student getStudentById(@Param("id")Long id);
}
