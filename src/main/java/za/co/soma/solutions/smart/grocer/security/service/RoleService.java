package za.co.soma.solutions.smart.grocer.security.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import za.co.soma.solutions.smart.grocer.dao.RoleRepository;
import za.co.soma.solutions.smart.grocer.domain.Role;

import java.util.List;
import java.util.Optional;

@Service
public class RoleService {

    @Autowired
    RoleRepository roleRepository;




    public Optional<Role> retrieve(Long roleId){

        return  roleRepository.findById(roleId);
    }


    public List<Role> getAll(){
        return  roleRepository.findAll();
    }


    public Role create(Role role){

        return  roleRepository.save(role);
    }

    public Role update(Role role){
        return roleRepository.save(role);
    }


    public void delete(Long roleId){

        roleRepository.deleteById(roleId);
    }



}
