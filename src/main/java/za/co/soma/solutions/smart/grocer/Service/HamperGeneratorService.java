package za.co.soma.solutions.smart.grocer.Service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import za.co.soma.solutions.smart.grocer.dao.HamperNoRepository;
import za.co.soma.solutions.smart.grocer.domain.CustomerHamper;
import za.co.soma.solutions.smart.grocer.domain.HamperNo;

import java.util.List;

@Service
public class HamperGeneratorService {

    @Autowired
    HamperNoRepository hamperNoRepository;

    public int getHamperNo(){

        List<HamperNo>  hamperNos = hamperNoRepository.findAll();
        hamperNos.get(0).increment();
        HamperNo hamperNo = hamperNoRepository.save(hamperNos.get(0));
        hamperNoRepository.flush();

        return hamperNo.getHamperNumber();
    }

    public void createCustomerHamper(List<CustomerHamper> customerHampers){

        customerHampers.stream().forEach(customerHamper -> {
            customerHamper.setHamperReference(customerHamper.getHamper().getProductCode()+"-"+ getHamperNo());
        });

    }
}
