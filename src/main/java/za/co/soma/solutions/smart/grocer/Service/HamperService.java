package za.co.soma.solutions.smart.grocer.Service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import za.co.soma.solutions.smart.grocer.dao.HamperRepository;
import za.co.soma.solutions.smart.grocer.domain.Hamper;

import java.util.List;
import java.util.Optional;

@Service
public class HamperService {

    @Autowired
    HamperRepository hamperRepository;


    public List<Hamper> getAll(){
        return hamperRepository.findAll();
    }

    public Optional<Hamper> retrieve(Long hamperId) {
        return hamperRepository.findById(hamperId);
    }
}
