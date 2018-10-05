package za.co.soma.solutions.smart.grocer.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import za.co.soma.solutions.smart.grocer.Service.SomaValidation;

@CrossOrigin(maxAge = 3600)
@RestController
@RequestMapping("/user")
public class UserController implements SomaValidation {
}
