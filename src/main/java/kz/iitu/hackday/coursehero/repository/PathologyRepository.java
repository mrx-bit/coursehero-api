package kz.iitu.hackday.coursehero.repository;

import kz.iitu.hackday.coursehero.entity.Pathology;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PathologyRepository extends JpaRepository<Pathology, Long> {

    List<Pathology> findAllByIsActiveTrue();

    @Query("select item from Pathology item where item.name like concat('%',?1,'%') and item.isActive=true")
    List<Pathology> search(String text);
}
