package za.co.soma.solutions.smart.grocer.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import za.co.soma.solutions.smart.grocer.domain.BankDetail;

public interface BankDetailRepository extends JpaRepository<BankDetail, Long> {

    
}
