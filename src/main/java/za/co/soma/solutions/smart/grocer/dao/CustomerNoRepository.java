package za.co.soma.solutions.smart.grocer.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import za.co.soma.solutions.smart.grocer.domain.CustomerNo;

public interface CustomerNoRepository extends JpaRepository<CustomerNo, Long> {
}
