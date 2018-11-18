package za.co.soma.solutions.smart.grocer.Service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import za.co.soma.solutions.smart.grocer.dao.HamperNoRepository;
import za.co.soma.solutions.smart.grocer.domain.CustomerHamper;
import za.co.soma.solutions.smart.grocer.domain.Hamper;
import za.co.soma.solutions.smart.grocer.domain.HamperNo;

import java.util.List;
import java.util.Optional;

@Service
public class HamperGeneratorService {

    @Autowired
    HamperNoRepository hamperNoRepository;

    @Autowired
    HamperService hamperService;

    public int getHamperNo(){

        List<HamperNo>  hamperNos = hamperNoRepository.findAll();
        hamperNos.get(0).increment();
        HamperNo hamperNo = hamperNoRepository.save(hamperNos.get(0));
        hamperNoRepository.flush();

        return hamperNo.getHamperNumber();
    }

    public void createCustomerHamperReference(List<CustomerHamper> customerHampers){

        customerHampers.stream().forEach(customerHamper -> {
            if(customerHamper.getHamperReference() == null )
                if(customerHamper.getHamper() != null && customerHamper.getHamper().getId() != null)
                    generateHamperReference(customerHamper);

            System.out.println("CUSTOMER_HAMPER_REFERENCE:"+customerHamper.getHamperReference());
        });

    }

    private void generateHamperReference(CustomerHamper customerHamper){
        Optional<Hamper> hamperOptional = hamperService.retrieve(customerHamper.getHamper().getId());
        customerHamper.setHamperReference(hamperOptional.get().getProductCode()+"-"+ getHamperNo());
    }
}
