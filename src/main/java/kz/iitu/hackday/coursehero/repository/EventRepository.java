package kz.iitu.hackday.coursehero.repository;

import kz.iitu.hackday.coursehero.entity.Event;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface EventRepository extends JpaRepository<Event, Long> {

    Page<Event> findAllByIsActiveTrue(Pageable pageRequest);

    @Query("select e from Event e where (e.title like concat('%',?1,'%') " +
            "or e.shortRawDescription like concat('%',?1,'%')" +
            "or e.fullRawDescription like concat('%',?1,'%')) and e.isActive=true")
    Page<Event> search(String text, Pageable pageRequest);
}
