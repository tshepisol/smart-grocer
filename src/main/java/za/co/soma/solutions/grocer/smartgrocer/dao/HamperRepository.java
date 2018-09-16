package za.co.soma.solutions.grocer.smartgrocer.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import za.co.soma.solutions.grocer.smartgrocer.domain.Hamper;

public interface HamperRepository extends JpaRepository<Hamper, Long> {
}
