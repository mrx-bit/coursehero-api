package kz.iitu.hackday.coursehero.repository;

import kz.iitu.hackday.coursehero.entity.PollOption;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PollOptionRepository extends JpaRepository<PollOption, Long> {

    List<PollOption> findAllByPollIdAndIsActiveTrue(Long pollId);

}
