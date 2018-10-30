package za.co.soma.solutions.smart.grocer.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import za.co.soma.solutions.smart.grocer.domain.CustomerHamper;

public interface CustomerHamperRepository extends JpaRepository<CustomerHamper, Long> {

    @Query("SELECT ch from CustomerHamper ch where ch.hamperReference =:ref ")
    CustomerHamper getByHamperReference(@Param("ref") String hamperReference);

    @Modifying
    @Query("UPDATE CustomerHamper ch set ch.paymentStatus = NULL")
    void updatePaymentStatusNull();

}
