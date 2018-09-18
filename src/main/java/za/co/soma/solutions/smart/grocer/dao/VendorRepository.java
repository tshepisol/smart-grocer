package za.co.soma.solutions.smart.grocer.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import za.co.soma.solutions.smart.grocer.domain.Vendor;

public interface VendorRepository extends JpaRepository<Vendor, Long> {
}
