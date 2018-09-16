package za.co.soma.solutions.grocer.smartgrocer.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import za.co.soma.solutions.grocer.smartgrocer.domain.Vendor;

public interface VendorRepository extends JpaRepository<Vendor, Long> {
}
