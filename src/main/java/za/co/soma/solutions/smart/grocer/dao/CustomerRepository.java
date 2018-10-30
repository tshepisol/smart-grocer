package za.co.soma.solutions.smart.grocer.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import za.co.soma.solutions.smart.grocer.domain.Customer;

import java.util.List;

public interface CustomerRepository extends JpaRepository<Customer, Long> {


    @Query("SELECT c FROM Customer c JOIN FETCH c.customerReferral cr  WHERE cr.id =:id")
    List<Customer> getCustomersByCustomerReferralId(@Param("id") Long id);

    @Query("SELECT c FROM Customer c JOIN FETCH c.customerReferral cr")
    List<Customer> getAllReferredCustomers();

    @Query("SELECT c FROM Customer c LEFT JOIN FETCH c.customerReferral cr  WHERE c.id =:id")
    Customer getCustomerByIdJoinFetchCustomerReferral(@Param("id") Long id);

    @Query("SELECT c FROM Customer c  LEFT JOIN FETCH c.customerReferral cr where c.customerNo =:customerNo")
    Customer getByCustomerNo(@Param("customerNo") String customerNo);
}
