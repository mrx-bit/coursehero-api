package kz.iitu.hackday.coursehero.repository;

import kz.iitu.hackday.coursehero.entity.NewsFeedback;
import kz.iitu.hackday.coursehero.entity.enums.FeedbackType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NewsFeedbackRepository extends JpaRepository<NewsFeedback, Long> {

    Page<NewsFeedback> findAllByNewsIdAndTypeAndIsActiveTrue(Long newsId, FeedbackType type, Pageable pageRequest);

}
