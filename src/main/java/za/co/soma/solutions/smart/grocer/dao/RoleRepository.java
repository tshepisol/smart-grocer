package za.co.soma.solutions.smart.grocer.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import za.co.soma.solutions.smart.grocer.domain.Role;
import za.co.soma.solutions.smart.grocer.domain.RoleType;

public interface RoleRepository extends JpaRepository<Role, Long> {

    Role findByRoleType(RoleType roleType);
}
