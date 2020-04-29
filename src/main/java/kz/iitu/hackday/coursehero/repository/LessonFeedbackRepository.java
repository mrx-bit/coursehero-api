package kz.iitu.hackday.coursehero.repository;

import kz.iitu.hackday.coursehero.entity.LessonFeedback;
import kz.iitu.hackday.coursehero.entity.enums.FeedbackType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LessonFeedbackRepository extends JpaRepository<LessonFeedback, Long> {

    Page<LessonFeedback> findAllByLessonIdAndTypeAndIsActiveTrue(Long lessonId, FeedbackType type, Pageable pageRequest);

}
