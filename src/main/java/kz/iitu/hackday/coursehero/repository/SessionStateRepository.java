package kz.iitu.hackday.coursehero.repository;

import kz.iitu.hackday.coursehero.entity.SessionState;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SessionStateRepository extends JpaRepository<SessionState, Long> {
    SessionState findByName(String name);
}
