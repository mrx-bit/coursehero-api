package kz.iitu.hackday.coursehero.repository;

import kz.iitu.hackday.coursehero.entity.Category;
import kz.iitu.hackday.coursehero.entity.enums.CategoryType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {

    List<Category> findAllByTypeAndIsActiveTrue(CategoryType type);

    @Query("select item from Category item where item.name like concat('%',?1,'%') " +
            "and item.type=?2 " +
            "and item.isActive=true")
    List<Category> search(String text, CategoryType type);
}
