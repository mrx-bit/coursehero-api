package kz.iitu.hackday.coursehero.repository;

import kz.iitu.hackday.coursehero.entity.Interest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InterestRepository extends JpaRepository<Interest, Long> {

    List<Interest> findAllByIsActiveTrue();

    @Query("select item from Interest item where item.name like concat('%',?1,'%') and item.isActive=true")
    List<Interest> search(String text);
}
