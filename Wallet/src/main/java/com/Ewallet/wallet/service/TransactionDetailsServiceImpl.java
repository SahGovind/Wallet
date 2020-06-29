package com.Ewallet.wallet.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Ewallet.wallet.dto.TransactionDTO;
import com.Ewallet.wallet.dto.TransactionStatus;
import com.Ewallet.wallet.dto.WalletDTO;
import com.Ewallet.wallet.model.Transaction;
import com.Ewallet.wallet.model.Wallet;
import com.Ewallet.wallet.output.ApiOutput;
import com.Ewallet.wallet.repository.TransactionDetailsRepository;
import com.Ewallet.wallet.repository.WalletRepository;

@Service
public class TransactionDetailsServiceImpl implements TransactionDetailsService {

	@Autowired
	public TransactionDetailsRepository transactionDetailsRepository;

	@Autowired
	public WalletRepository walletRepository;

	@Override
	public ApiOutput addMoney(WalletDTO walletDTO) {

		ApiOutput apiOutput = new ApiOutput();

		Wallet wallet = walletRepository.findByMobileNo(walletDTO.getMobile_no());
		if (walletDTO.getMobile_no() != "") {
			wallet.setCurrentBalance(wallet.getCurrentBalance() + walletDTO.getAmountToAdd());
			apiOutput.setResponse(wallet);
			apiOutput.setSuccess(true);
		} else {

			apiOutput.setResponse("Wallet Account does not exist");
			apiOutput.setSuccess(true);
		}

		return apiOutput;

	}

	@Override
	public ApiOutput transferMoney(TransactionDTO transactionDTO) {

		ApiOutput apiOutput = new ApiOutput();
		Transaction transaction = new Transaction();

		Wallet wallet = walletRepository.findByMobileNo(transactionDTO.getMobileNumber());

		if (wallet.getCurrentBalance() < transactionDTO.getTxnAmount()) {
			apiOutput.setResponse("Insufficient Balance");
			apiOutput.setResponse(wallet);
			apiOutput.setSuccess(true);

			return apiOutput;

		} else {
			Double finalAmount = charges(transactionDTO);
			if (wallet.getCurrentBalance() < finalAmount) {

				apiOutput.setResponse("Insufficient Balance");
				apiOutput.setResponse(wallet);
				apiOutput.setSuccess(true);
				return apiOutput;

			} else {
				wallet.setCurrentBalance(wallet.getCurrentBalance() - finalAmount);
				transaction.setTransactionStatus(TransactionStatus.AUTHORIZED_TRANSACTION);
				transaction.setMobileNumber(transactionDTO.getMobileNumber());
				transaction.setRecieverMobNo(transactionDTO.getRecieverMobNo());
				transaction.setTransactionAmount(transactionDTO.getTxnAmount());
			}
		}

		Wallet recieversWallet = walletRepository.findByMobileNo(transactionDTO.getRecieverMobNo());

		if (transactionDTO.getRecieverMobNo() != "") {
			recieversWallet.setCurrentBalance(recieversWallet.getCurrentBalance() + transactionDTO.getTxnAmount());

			transaction.setTransactionStatus(TransactionStatus.AUTHORIZED_TRANSACTION);
			try {
				walletRepository.save(wallet);

			} catch (Exception e) {
				transaction.setTransactionStatus(TransactionStatus.FAILED_TRANSACTION);

			}
			try {
				walletRepository.save(recieversWallet);
			} catch (Exception e) {
				transaction.setTransactionStatus(TransactionStatus.PENDING_TRANSACTION);

			}

			transactionDetailsRepository.save(transaction);
			apiOutput.setResponse(wallet);
			apiOutput.setSuccess(true);
		} else {
			apiOutput.setResponse("Reciever Doesnot exist");
			apiOutput.setSuccess(true);

		}
		return apiOutput;

	}

	public Double charges(TransactionDTO transactionDTO) {

		Double chargedAmount = transactionDTO.getTxnAmount() * 0.002;

		Double commissionAmount = transactionDTO.getTxnAmount() * 0.0005;

		Double finalAmount = chargedAmount + commissionAmount + transactionDTO.getTxnAmount();

		return finalAmount;

	}

	@Override
	public ApiOutput viewPassBook(String mobileNumber) {

		ApiOutput apiOutput = new ApiOutput();
		List<Transaction> transactionList = transactionDetailsRepository.findByMobileNumber(mobileNumber);

		apiOutput.setResponse(transactionList);
		apiOutput.setSuccess(true);

		return apiOutput;

	}

	@Override
	public ApiOutput checkStatus(Long txnId) {

		ApiOutput apiOutput = new ApiOutput();
		Transaction transaction = transactionDetailsRepository.findByTxnId(txnId);

		if (transaction.getTransactionStatus().equals(TransactionStatus.AUTHORIZED_TRANSACTION)) {
			apiOutput.setResponse("Transaction is successfull");
			apiOutput.setSuccess(true);

		} else if (transaction.getTransactionStatus().equals(TransactionStatus.PENDING_TRANSACTION)) {
			apiOutput.setResponse("Transaction is pending");
			apiOutput.setSuccess(true);

		} else if (transaction.getTransactionStatus().equals(TransactionStatus.FAILED_TRANSACTION)) {
			apiOutput.setResponse("Transaction is Failed");
			apiOutput.setSuccess(true);

		} else if (transaction.getTransactionStatus().equals(TransactionStatus.REVERSE_TRANSACTION)) {
			apiOutput.setResponse("Transaction is Reversed");
			apiOutput.setSuccess(true);
		}

		return apiOutput;
	}

	@Override
	public ApiOutput reversalTxn(Long txnId) {

		ApiOutput apiOutput = new ApiOutput();
		Transaction transaction = transactionDetailsRepository.findByTxnId(txnId);
		TransactionDTO transactionDTO = new TransactionDTO();

		if (transaction.getTransactionStatus().equals(TransactionStatus.AUTHORIZED_TRANSACTION)) {

			transaction.setTransactionStatus(TransactionStatus.REVERSE_TRANSACTION);
			Wallet wallet = walletRepository.findByMobileNo(transaction.getMobileNumber());
			wallet.setCurrentBalance(wallet.getCurrentBalance() + transaction.getTransactionAmount());
			walletRepository.save(wallet);

			apiOutput.setResponse("Transaction is Reversed");
			apiOutput.setSuccess(true);
		}
		return apiOutput;
	}

}
