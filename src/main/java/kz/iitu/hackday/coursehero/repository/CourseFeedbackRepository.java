package kz.iitu.hackday.coursehero.repository;

import kz.iitu.hackday.coursehero.entity.CourseFeedback;
import kz.iitu.hackday.coursehero.entity.enums.FeedbackType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CourseFeedbackRepository extends JpaRepository<CourseFeedback, Long> {

    Page<CourseFeedback> findAllByCourseIdAndTypeAndIsActiveTrue(Long courseId, FeedbackType type, Pageable pageRequest);

}
