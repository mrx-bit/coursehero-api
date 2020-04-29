package kz.iitu.hackday.coursehero.repository;

import kz.iitu.hackday.coursehero.entity.Course;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface CourseRepository extends JpaRepository<Course, Long> {

    Page<Course> findAllByIsActiveTrue(Pageable pageRequest);

    @Query("select c from Course c where (c.title like concat('%',?1,'%') " +
            "or c.shortRawDescription like concat('%',?1,'%')" +
            "or c.fullRawDescription like concat('%',?1,'%')) and c.isActive=true")
    Page<Course> search(String text, Pageable pageRequest);

}
