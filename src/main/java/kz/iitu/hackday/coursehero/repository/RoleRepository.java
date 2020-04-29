package kz.iitu.hackday.coursehero.repository;

import kz.iitu.hackday.coursehero.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {

}
