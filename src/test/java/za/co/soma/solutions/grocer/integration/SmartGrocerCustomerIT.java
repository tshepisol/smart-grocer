package za.co.soma.solutions.grocer.integration;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import za.co.soma.solutions.smart.grocer.domain.*;

import java.util.Random;

public class SmartGrocerCustomerIT {

   // private String host = "http://35.242.165.19";
   private String host = "http://localhost:8080";

    @Test
    public void register(){

        Customer customer = createCustomer1();
        RestTemplate restTemplate = new RestTemplate();
        customer = restTemplate.postForObject(host +"/register", customer, Customer.class);

        Assert.assertNotNull(customer.getBankDetails().get(0).getPaymentHistoryList().size());

        System.out.println("Create Customer:"+customer.getBankDetails().get(0).getPaymentHistoryList().get(0).getId());


        customer = createCustomer2();
        customer = restTemplate.postForObject(host +"/register", customer, Customer.class);
        System.out.println("Create Customer:"+customer.getBankDetails().get(0).getPaymentHistoryList().get(0).getId());
    }


    @Test
    public void customerAll(){

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> paymentHistories = restTemplate.getForEntity(host +"/customer", String.class);

        Assert.assertNotNull(paymentHistories.getBody());

        System.out.println("URL to retrieve Customer:"+paymentHistories.getBody());
    }

    @Test
    public void customer(){

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> paymentHistories = restTemplate.getForEntity(host+"/customer/1", String.class);

        Assert.assertNotNull(paymentHistories.getBody());

        System.out.println("URL to retrieve Customer:"+paymentHistories.getBody());
    }


    @Test
    public void paymentHistory(){

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> paymentHistories = restTemplate.getForEntity(host +"/customer/payment/2", String.class);

        Assert.assertNotNull(paymentHistories.getBody());

        System.out.println("URL to retrieve Customer:"+paymentHistories.getBody());
    }

    @Test
    public void referral(){

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> customerReferrals = restTemplate.getForEntity(host +"/customer/referrals/1", String.class);

        Assert.assertNotNull(customerReferrals.getBody());

        System.out.println("URL to retrieve Customer:"+customerReferrals.getBody());
    }

    @Test
    public void partnerLevel(){

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> customerReferrals = restTemplate.getForEntity(host +"/admin/partner", String.class);

        Assert.assertEquals(customerReferrals.getStatusCode().value(), 200);

        System.out.println("URL to retrieve Customer:"+customerReferrals.getBody());
    }

    @Test
    public void random(){
        System.out.println(new Random().nextInt(99999999));
    }



    private Customer createCustomer2(){
        Customer customer = new Customer();//id=2
        customer.setLastName("Letswalo");
        customer.setFirstName("Tshepiso");
        customer.setIdNumber("0103165495086");
        customer.setUser(new User());
        customer.getUser().setUsername("linx");
        customer.getUser().setPassword("123");

        Customer customer1 = new Customer();
        customer1.setId(1L);
        customer.setCustomerReferral(customer1);

        CustomerAddress customerAddress = new CustomerAddress();
        customerAddress.setNumber("13");
        customerAddress.setStreetName("Tiemie Cresent");
        customerAddress.setTown("Rooihuiskraal North");
        customerAddress.setAddressType(AddressType.POSTAL);
        customerAddress.setPostalCode("0182");
        customer.getCustomerAddresses().add(customerAddress);
        customerAddress.setCustomer(customer);

        BankDetail bankDetail = new BankDetail();
        bankDetail.setAccountNumber(new Long (new Random().nextInt(99999999)));
        bankDetail.setBranchCode(00002);
        bankDetail.setDebitDate(20);
        bankDetail.setAccountType(AccountType.CHEQUE);
        bankDetail.setBank(BankType.FNB);
        customer.getBankDetails().add(bankDetail);
        PaymentHistory paymentHistory = new PaymentHistory();
        paymentHistory.setAmount(400);
        paymentHistory.setMonth(10);
        paymentHistory.setYear(2018);
        paymentHistory.setPaymentStatus(PaymentStatusType.PAID);
        paymentHistory.setBankDetail(bankDetail);
        bankDetail.getPaymentHistoryList().add(paymentHistory);

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


    private Customer createCustomer1(){
        Customer customer = new Customer();//ID=1
        customer.setLastName("lebo");
        customer.setFirstName("lebo");
        customer.setIdNumber("0103165495086");
        customer.setUser(new User());
        customer.getUser().setUsername("lebo");
        customer.getUser().setPassword("123");

        CustomerAddress customerAddress = new CustomerAddress();
        customerAddress.setNumber("13");
        customerAddress.setStreetName("Tiemie Cresent");
        customerAddress.setTown("Rooihuiskraal North");
        customerAddress.setAddressType(AddressType.POSTAL);
        customerAddress.setPostalCode("0182");
        customer.getCustomerAddresses().add(customerAddress);
        customerAddress.setCustomer(customer);

        BankDetail bankDetail = new BankDetail();
        bankDetail.setAccountNumber(new Long (new Random().nextInt(99999999)));
        bankDetail.setBranchCode(00002);
        bankDetail.setDebitDate(1);
        bankDetail.setAccountType(AccountType.CHEQUE);
        bankDetail.setBank(BankType.ABSA);
        PaymentHistory paymentHistory = new PaymentHistory();
        paymentHistory.setAmount(200);
        paymentHistory.setMonth(10);
        paymentHistory.setYear(2018);
        paymentHistory.setPaymentStatus(PaymentStatusType.PAID);
        paymentHistory.setBankDetail(bankDetail);
        bankDetail.getPaymentHistoryList().add(paymentHistory);

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
