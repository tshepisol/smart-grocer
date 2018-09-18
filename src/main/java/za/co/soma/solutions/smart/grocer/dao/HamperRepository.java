package za.co.soma.solutions.smart.grocer.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import za.co.soma.solutions.smart.grocer.domain.Hamper;

public interface HamperRepository extends JpaRepository<Hamper, Long> {
}
