package com.kamil.dev_manager.repository;

import com.kamil.dev_manager.entity.Lecture;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface LectureRepository extends JpaRepository<Lecture,Long> {
    Optional<Lecture> findById(Long id);

   /* @Query("select d.lecture_id from attendance_list d where d.student_id=:student_id")
    List<Long> allLecturesOnList(@Param("student_id")Long student_id);*/
}
