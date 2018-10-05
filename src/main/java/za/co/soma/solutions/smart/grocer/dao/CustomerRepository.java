package za.co.soma.solutions.smart.grocer.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import za.co.soma.solutions.smart.grocer.domain.Customer;

import java.util.List;

public interface CustomerRepository extends JpaRepository<Customer, Long> {


    @Query("select c from Customer c  where c.customerReferral.id =:id")
    List<Customer> getCustomersByCustomerRefereralId(Long id);
}
