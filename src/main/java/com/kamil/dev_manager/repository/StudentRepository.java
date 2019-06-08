package com.kamil.dev_manager.repository;

import com.kamil.dev_manager.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface StudentRepository extends JpaRepository<Student,Long> {

    @Query("from Student i where i.id=:id")
    Student getStudentById(@Param("id")Long id);

    Student findByUsername(String s);
   /* @Query("from Student s where s.username=:username")
    Optional<Student> findByUsername(@Param("username") String username);*/

    @Query("delete from Student s where s.id=:id")
    Student deleteStudentById(@Param("id")Long id);
/*
    @Query("from Student s where s.username=:username")
    Student findByUsername(@Param("username") String username);*/
}
