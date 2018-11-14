package za.co.soma.solutions.smart.grocer.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import za.co.soma.solutions.smart.grocer.domain.CustomerNo;
import za.co.soma.solutions.smart.grocer.domain.HamperNo;

public interface HamperNoRepository extends JpaRepository<HamperNo, Long> {
}
