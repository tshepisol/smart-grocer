package za.co.soma.solutions.smart.grocer.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import za.co.soma.solutions.smart.grocer.Service.CustomerPartnerLevelService;
import za.co.soma.solutions.smart.grocer.domain.PaymentHistory;

import java.util.List;

@CrossOrigin(maxAge = 3600)
@RestController
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    CustomerPartnerLevelService customerPartnerLevelService;


    @GetMapping("/partner")
    public  void determine(){
        customerPartnerLevelService.determinePartnerLevel();
    }


    @GetMapping("/history")
    public List<PaymentHistory> historyList(){
       return customerPartnerLevelService.history();
    }
}
