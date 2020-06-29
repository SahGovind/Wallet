package com.Ewallet.wallet.restcontroller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.Ewallet.wallet.model.ApplicationUser;
import com.Ewallet.wallet.repository.ApplicationUserRepository;
import com.Ewallet.wallet.service.TransactionDetailsService;



@RestController
@RequestMapping("/users")
public class UserController {
	@Autowired
	private TransactionDetailsService transactionDetailsService;
	@Autowired
    private ApplicationUserRepository applicationUserRepository;
	@Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    public UserController(ApplicationUserRepository applicationUserRepository,
                          BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.applicationUserRepository = applicationUserRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @PostMapping("/sign-up")
    public void signUp(@RequestBody ApplicationUser user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        applicationUserRepository.save(user);
    }
 
}