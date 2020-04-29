package kz.iitu.hackday.coursehero.repository;

import kz.iitu.hackday.coursehero.entity.Disability;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DisabilityRepository extends JpaRepository<Disability, Long> {

    List<Disability> findAllByIsActiveTrue();

    @Query("select item from Disability item where item.name like concat('%',?1,'%') and item.isActive=true")
    List<Disability> search(String text);
}
