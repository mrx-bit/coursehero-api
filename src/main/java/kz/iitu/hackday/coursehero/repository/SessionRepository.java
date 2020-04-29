package kz.iitu.hackday.coursehero.repository;

import kz.iitu.hackday.coursehero.entity.Session;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

@Repository
public interface SessionRepository extends JpaRepository<Session, Long> {

    @Modifying
    @Query("update Session s set s.closed=?3, s.stateId=?2 where s.closed is null and s.email=?1")
    @Transactional
    void deactivateAllUserSessions(String email, Long closedStateId, Date closedDate);

    Session getSessionByTokenAndAndStateId(String token, Long stateId);
}
