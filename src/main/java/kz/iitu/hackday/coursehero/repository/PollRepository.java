package kz.iitu.hackday.coursehero.repository;

import kz.iitu.hackday.coursehero.entity.Poll;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PollRepository extends JpaRepository<Poll, Long> {

    List<Poll> findAllByIsActiveTrueOrderByOrderVal();

}
