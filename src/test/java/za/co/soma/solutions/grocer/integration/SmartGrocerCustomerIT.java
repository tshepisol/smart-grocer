package za.co.soma.solutions.grocer.integration;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.web.client.RestTemplate;
import za.co.soma.solutions.smart.grocer.domain.*;

import java.net.URI;
import java.util.Random;

public class SmartGrocerCustomerIT {

    private String postCustomerURL = "http://35.242.165.19/customer";
    private String postCustomerLocal = "http://localhost:8080/customer";

    @Test
    public void register(){

        Customer customer = createCustomer();
        RestTemplate restTemplate = new RestTemplate();
        URI uri = restTemplate.postForLocation(postCustomerLocal, customer, Customer.class);

        Assert.assertNotNull(uri.toASCIIString());

        System.out.println("URL to retrieve Customer:"+uri.toASCIIString());
    }

    @Test
    public void random(){
        System.out.println(new Random().nextInt(99999999));
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
        bankDetail.setAccountnumber(new Long (new Random().nextInt(99999999)));
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



        Hamper hamper =  new Hamper();
        hamper.setId(1L);
        hamper.setName("Makro special - Grocery hamper");
        hamper.setVendor(new Vendor());
        hamper.getVendor().setId(1L);
        hamper.getVendor().setName("MAKRO");
        customerHamper.setHamper(hamper);
        customer.getCustomerHampers().add(customerHamper);



        return  customer;
    }
}
