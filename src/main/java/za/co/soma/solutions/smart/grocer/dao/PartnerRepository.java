package za.co.soma.solutions.smart.grocer.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import za.co.soma.solutions.smart.grocer.domain.Partner;


public interface PartnerRepository extends JpaRepository<Partner, Long> {

    @Modifying
    @Query("DELETE FROM Partner")
    void deleteAllPartnerLevel();

}
