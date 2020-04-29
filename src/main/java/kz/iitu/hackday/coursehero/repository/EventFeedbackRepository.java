package kz.iitu.hackday.coursehero.repository;

import kz.iitu.hackday.coursehero.entity.EventFeedback;
import kz.iitu.hackday.coursehero.entity.enums.FeedbackType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EventFeedbackRepository extends JpaRepository<EventFeedback, Long> {

    Page<EventFeedback> findAllByEventIdAndTypeAndIsActiveTrue(Long eventId, FeedbackType type, Pageable pageRequest);

}
