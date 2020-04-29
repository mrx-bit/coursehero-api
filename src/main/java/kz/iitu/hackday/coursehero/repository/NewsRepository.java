package kz.iitu.hackday.coursehero.repository;

import kz.iitu.hackday.coursehero.entity.News;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface NewsRepository extends JpaRepository<News, Long> {

    Page<News> findAllByIsActiveTrue(Pageable pageRequest);

    @Query("select n from News n where (n.title like concat('%',?1,'%') " +
            "or n.shortRawDescription like concat('%',?1,'%')" +
            "or n.fullRawDescription like concat('%',?1,'%')) and n.isActive=true")
    Page<News> search(String text, Pageable pageRequest);

}
