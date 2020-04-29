package kz.iitu.hackday.coursehero.repository;

import kz.iitu.hackday.coursehero.entity.Lesson;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LessonRepository extends JpaRepository<Lesson, Long> {

    List<Lesson> findAllByCourseIdAndIsActiveTrueOrderByOrderVal(Long courseId);

}
