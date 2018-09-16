package za.co.soma.solutions.grocer.smartgrocer.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import za.co.soma.solutions.grocer.smartgrocer.domain.Partner;

public interface PartnerRepository extends JpaRepository<Partner, Long> {
}
