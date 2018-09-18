package za.co.soma.solutions.smart.grocer.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import za.co.soma.solutions.smart.grocer.domain.Partner;

public interface PartnerRepository extends JpaRepository<Partner, Long> {
}
