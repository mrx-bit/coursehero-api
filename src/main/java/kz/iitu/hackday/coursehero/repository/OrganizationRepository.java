package kz.iitu.hackday.coursehero.repository;

import kz.iitu.hackday.coursehero.entity.Organization;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrganizationRepository extends JpaRepository<Organization, Long> {

    List<Organization> findAllByIsActiveTrue();

    @Query("select item from Organization item where item.name like concat('%',?1,'%') and item.isActive=true")
    List<Organization> search(String text);
}
