package com.Ewallet.wallet.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.Ewallet.wallet.model.Transaction;
import com.Ewallet.wallet.model.Wallet;

public interface TransactionDetailsRepository extends JpaRepository<Transaction,String>{
	
	public Transaction findByTxnId(Long txnId);
	public List<Transaction> findByMobileNumber(String mobileNumber);
	
	
	

}
