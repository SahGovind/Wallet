package com.Ewallet.wallet.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.Ewallet.wallet.model.ApplicationUser;

public interface ApplicationUserRepository extends JpaRepository<ApplicationUser, Long> {
	    ApplicationUser findByUsername(String username);

}
