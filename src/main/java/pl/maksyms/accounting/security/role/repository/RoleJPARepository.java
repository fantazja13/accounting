package pl.maksyms.accounting.security.role.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.maksyms.accounting.security.role.Role;

import java.util.Optional;

public interface RoleJPARepository extends JpaRepository<Role, Long> {

    Optional<Role> findByName(String name);
}
