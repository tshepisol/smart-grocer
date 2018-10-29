package za.co.soma.solutions.smart.grocer.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import za.co.soma.solutions.smart.grocer.domain.PaymentHistory;

import java.util.List;

public interface PaymentHistoryRepository extends JpaRepository<PaymentHistory, Long> {

    @Query("SELECT ph from PaymentHistory ph where ph.year =:year and ph.month =:month ")
    List<PaymentHistory> findByYearMonth(@Param("year") int year, @Param("month") int month);
}
