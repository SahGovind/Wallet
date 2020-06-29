package com.Ewallet.wallet.restcontroller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.Ewallet.wallet.dto.TransactionDTO;
import com.Ewallet.wallet.dto.WalletDTO;
import com.Ewallet.wallet.service.TransactionDetailsService;

@RestController
public class TransactionDetails {
	@Autowired
	private TransactionDetailsService transactionDetailsService;

	@SuppressWarnings("rawtypes")
	@PostMapping("/addMoney")
	public ResponseEntity addMoney(@RequestBody WalletDTO walletDTO) {
		return new ResponseEntity<>(transactionDetailsService.addMoney( walletDTO), HttpStatus.OK);
	}

	@SuppressWarnings("rawtypes")
	@PostMapping("/transferMoney") 
	public ResponseEntity transferMoney(@RequestBody TransactionDTO transactionDTO) {

		return new ResponseEntity<>(transactionDetailsService.transferMoney(transactionDTO), HttpStatus.OK);

	}

	@SuppressWarnings("rawtypes")
	@GetMapping("/checkStatus/{txnId}")
	public ResponseEntity checkstatus(@PathVariable Long txnId) {
		return new ResponseEntity<>(transactionDetailsService.checkStatus(txnId), HttpStatus.OK);

	}
	@SuppressWarnings("rawtypes")
	@GetMapping("/reversalTxn/{txnId}")
	public ResponseEntity reversalTxn(@PathVariable Long txnId) {
		return new ResponseEntity<>(transactionDetailsService.reversalTxn(txnId), HttpStatus.OK);

	}

	@SuppressWarnings("rawtypes")
	@GetMapping("/viewPassbook/{mobileNumber}")
	public ResponseEntity viewPassbook(@PathVariable String mobileNumber) {
		return new ResponseEntity<>(transactionDetailsService.viewPassBook(mobileNumber), HttpStatus.OK);

	}

}
