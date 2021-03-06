package za.co.soma.solutions.grocer.integration;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import za.co.soma.solutions.smart.grocer.domain.*;

import javax.annotation.Resource;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Random;
import java.util.ResourceBundle;

public class SmartGrocerCustomerIT {

 //  private String host = "http://35.242.165.19";
   private String host = "http://localhost:8080";

    @Test
    public void register() throws JsonProcessingException {

        Customer customer = createCustomer1();

        ObjectMapper mapper = new ObjectMapper();

        String jsonInString = mapper.writeValueAsString(customer);

        RestTemplate restTemplate = new RestTemplate();
        customer = restTemplate.postForObject(host +"/register", customer, Customer.class);



        Assert.assertNotNull(customer.getBankDetails().get(0).getPaymentHistoryList().size());

        System.out.println("Create Customer:"+customer.getBankDetails().get(0).getPaymentHistoryList().get(0).getId());


        customer = createCustomer2();
        customer = restTemplate.postForObject(host +"/register", customer, Customer.class);
        System.out.println("Create Customer:"+customer.getBankDetails().get(0).getPaymentHistoryList().get(0).getId());
    }


    @Test
    public void registerJSON() throws IOException {
        RestTemplate restTemplate = new RestTemplate();

        String payload = new String (Files.readAllBytes(Paths.get("target/test-classes/register/register-payload.json")));
      //  String url = "endpoint url";
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<String> entity = new HttpEntity<String>(payload,headers);
        String answer = restTemplate.postForObject(host+"/register", entity, String.class);
        System.out.println("response:"+answer);
    }

    @Test
    public void updateCustomer() throws IOException {
        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);



        String payload = new String (Files.readAllBytes(Paths.get("target/test-classes/register/register-payload.json")));
        HttpEntity<String> entity = new HttpEntity<String>(payload,headers);

         restTemplate.put(host+"/customer", entity);

    }

    @Test
    public void registerHamper(){

        RestTemplate restTemplate = new RestTemplate();
        Customer customer = createCustomer3();
        customer = restTemplate.postForObject(host +"/register", customer, Customer.class);
        Assert.assertNotNull(customer.getId());

        System.out.println("URL to retrieve Customer:"+customer);


        createCustomerHamper1(customer);


        restTemplate.put(host+"/register/hamper", customer);
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
    public void hampers(){

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> responseEntity = restTemplate.getForEntity(host+"/hamper", String.class);

        Assert.assertNotNull(responseEntity.getBody());

        System.out.println("URL to retrieve hamper:"+responseEntity.getBody());
    }


    @Test
    public void hamper(){

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> responseEntity = restTemplate.getForEntity(host+"/hamper/1", String.class);

        Assert.assertNotNull(responseEntity.getBody());

        System.out.println("URL to retrieve hamper:"+responseEntity.getBody());
    }

    @Test
    public void resetPartner(){

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> responseEntity = restTemplate.getForEntity(host+"/admin/partner/reset", String.class);

        Assert.assertEquals(responseEntity.getStatusCode().value(), 200);

        System.out.println("URL to retrieve hamper:"+responseEntity.getBody());
    }


    @Test
    public void customerNO(){

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> responseEntity = restTemplate.getForEntity(host+"/customer/ref/GR2", String.class);

        Assert.assertNotNull(responseEntity.getBody());

        System.out.println("URL to retrieve hamper:"+responseEntity.getBody());
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



    private void createCustomerHamper1(Customer customer){
        CustomerHamper customerHamper = new CustomerHamper();
        customerHamper.setCustomer(customer);
        Hamper hamper =  new Hamper();
        hamper.setId(1L);
        hamper.setVendor(new Vendor());
        hamper.getVendor().setId(1L);
        customerHamper.setHamper(hamper);
        customer.getCustomerHampers().add(customerHamper);
    }

    private Customer createCustomer3(){
        Customer customer = new Customer();//id=2
        customer.setLastName("MANDLA");
        customer.setFirstName("HAMPER");
        customer.setIdNumber("9003165495086");
        customer.setUser(new User());
        customer.getUser().setUsername("mylogin");
        customer.getUser().setPassword("123");

        Customer customer1 = new Customer();
        customer1.setId(1L);
        customer.setCustomerReferral(customer1);

        CustomerAddress customerAddress = new CustomerAddress();
        customerAddress.setNumber("13");
        customerAddress.setStreetName("draaibos Cresent");
        customerAddress.setTown("soshanguve North");
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
       /* PaymentHistory paymentHistory = new PaymentHistory();
        paymentHistory.setAmount(400);
        paymentHistory.setMonth(11);
        paymentHistory.setYear(2018);
        paymentHistory.setPaymentStatus(PaymentStatusType.PAID);

        paymentHistory.setBankDetail(bankDetail);
        bankDetail.getPaymentHistoryList().add(paymentHistory);*/

        CustomerContact customerContact = new CustomerContact();
        customerContact.setContactType(ContactType.MOBILE);
        customerContact.setNumber("0795262301");
        customer.getCustomerContacts().add(customerContact);




        return  customer;
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
        paymentHistory.setMonth(11);
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
        paymentHistory.setCustomerHamper(customerHamper);
        customerHamper.setHamperReference("HAMPER1");

        Hamper hamper =  new Hamper();
        hamper.setId(1L);
     //   hamper.setName("Makro special - Grocery hamper");
        hamper.setVendor(new Vendor());
        hamper.getVendor().setId(1L);
       // hamper.getVendor().setName("MAKRO");
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
        paymentHistory.setMonth(11);
        paymentHistory.setYear(2018);
        paymentHistory.setPaymentStatus(PaymentStatusType.PAID);
        paymentHistory.setBankDetail(bankDetail);
        bankDetail.getPaymentHistoryList().add(paymentHistory);

        customer.getBankDetails().add(bankDetail);
        CustomerContact customerContact = new CustomerContact();
        customerContact.setContactType(ContactType.MOBILE);
        customerContact.setNumber("0795262301");
        customer.getCustomerContacts().add(customerContact);

       // CustomerHamper customerHamper = new CustomerHamper();
       // customerHamper.setCustomer(customer);
       // paymentHistory.setCustomerHamper(customerHamper);
       // customerHamper.setHamperReference("HAMPER-1");

        //Hamper hamper =  new Hamper();
        //hamper.setId(1L);
      //  hamper.setName("Makro special - Grocery hamper");
        //hamper.setVendor(new Vendor());
       // hamper.getVendor().setId(1L);
       // hamper.getVendor().setName("MAKRO");
        //customerHamper.setHamper(hamper);
        //customer.getCustomerHampers().add(customerHamper);



        return  customer;
    }
}
