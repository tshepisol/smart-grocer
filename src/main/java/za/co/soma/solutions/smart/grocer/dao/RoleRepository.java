package za.co.soma.solutions.smart.grocer.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import za.co.soma.solutions.smart.grocer.domain.Role;
import za.co.soma.solutions.smart.grocer.domain.RoleName;

public interface RoleRepository extends JpaRepository<Role, Long> {

    Role findByRoleName(RoleName roleName);
}
