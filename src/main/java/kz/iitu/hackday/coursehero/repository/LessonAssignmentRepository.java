package kz.iitu.hackday.coursehero.repository;

import kz.iitu.hackday.coursehero.entity.LessonAssignment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LessonAssignmentRepository extends JpaRepository<LessonAssignment, Long> {

    List<LessonAssignment> findAllByLessonIdAndIsActiveTrueOrderByOrderVal(Long lessonId);

}
