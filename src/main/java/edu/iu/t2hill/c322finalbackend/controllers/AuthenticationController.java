package edu.iu.t2hill.c322finalbackend.controllers;


import edu.iu.t2hill.c322finalbackend.model.Customer;
import edu.iu.t2hill.c322finalbackend.repository.CustomerRepository;
import edu.iu.t2hill.c322finalbackend.security.TokenService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
public class AuthenticationController {
    private final TokenService tokenService;
    CustomerRepository customerRepository;
    public AuthenticationController(TokenService tokenService,
                                    CustomerRepository
                                            customerRepository) {
        this.tokenService = tokenService;
        this.customerRepository = customerRepository;
    }
    @PostMapping("/signup")
    public void signup(@RequestBody Customer customer) {
        try {
            BCryptPasswordEncoder bc = new BCryptPasswordEncoder();
            String passwordEncoded = bc.encode(customer.getPassword());
            customer.setPassword(passwordEncoded);
            customerRepository.save(customer);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @PostMapping("/signin")
    public String login(@RequestBody Customer customer) {
        return tokenService.generateToken(customer.getUsername(), "ROLE_USER ROLE_ADMIN");
    }
}
