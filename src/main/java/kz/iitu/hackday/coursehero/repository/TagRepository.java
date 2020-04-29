package kz.iitu.hackday.coursehero.repository;

import kz.iitu.hackday.coursehero.entity.Tag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TagRepository extends JpaRepository<Tag, Long> {

    List<Tag> findAllByIsActiveTrue();

    @Query("select item from Tag item where item.name like concat('%',?1,'%') and item.isActive=true")
    List<Tag> search(String text);
}
