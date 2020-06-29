package com.Ewallet.wallet.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.Ewallet.wallet.model.Wallet;


@Repository
public interface WalletRepository extends JpaRepository<Wallet,String>		{
	
	
 public Wallet findByMobileNo(String mobileNo); 

}
