package za.co.soma.solutions.smart.grocer.controller;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.springframework.web.util.UriComponentsBuilder;
import za.co.soma.solutions.smart.grocer.Application;
import za.co.soma.solutions.smart.grocer.dao.HamperRepository;
import za.co.soma.solutions.smart.grocer.domain.*;
import za.co.soma.solutions.smart.grocer.exception.GrocerErrorType;

import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyObject;
import static org.mockito.Matchers.eq;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
public class CustomerControllerTest {

    @Autowired
    CustomerController customerController;

    UriComponentsBuilder uriComponentsBuilder;

    @Autowired
    HamperRepository hamperRepository;

    @Before
    public void init(){

    }

    @Test
    public void create() {

        Customer customer = createCustomer();
        uriComponentsBuilder = ServletUriComponentsBuilder.fromCurrentRequestUri();
        ResponseEntity responseEntity = customerController.create(customer, uriComponentsBuilder);

        Object payload  = responseEntity.getBody();

        if( payload instanceof  Customer){
            customer = (Customer) payload;

            Assert.assertNotNull(customer);
            Assert.assertNotNull(customer.getId());

        }else{
            GrocerErrorType grocerErrorType = (GrocerErrorType) payload;
            System.out.println("\n\n"+grocerErrorType);
            Assert.assertNull(grocerErrorType);
        }

    }


    private Customer createCustomer(){
        Customer customer = new Customer();
        customer.setLastName("Letswalo");
        customer.setFirstName("Tshepiso");
        customer.setIdNumber("8303165495086");
        customer.setUser(new User());
        customer.getUser().setUsername("linx");
        customer.getUser().setPassword("123");

        CustomerAddress customerAddress = new CustomerAddress();
        customerAddress.setNumber("13");
        customerAddress.setStreetName("Tiemie Cresent");
        customerAddress.setTown("Rooihuiskraal North");
        customerAddress.setAddressType(AddressType.POSTAL);
        customerAddress.setPostalCode("0182");
        customer.getCustomerAddresses().add(customerAddress);

        BankDetail bankDetail = new BankDetail();
        bankDetail.setAccountnumber(1223344445L);
        bankDetail.setBranchCode(00002);
        bankDetail.setDebitDate(1);
        bankDetail.setAccountType(AccountType.CHEQUE);
        bankDetail.setBank(BankType.ABSA);
        customer.getBankDetails().add(bankDetail);
        CustomerContact customerContact = new CustomerContact();
        customerContact.setContactType(ContactType.MOBILE);
        customerContact.setNumber("0795262301");
        customer.getCustomerContacts().add(customerContact);

        CustomerHamper customerHamper = new CustomerHamper();
        customerHamper.setCustomer(customer);

        Hamper hamper = hamperRepository.findByName("Makro special - Grocery hamper");
        customerHamper.setHamper(hamper);
        customer.getCustomerHampers().add(customerHamper);



        return  customer;
    }
}