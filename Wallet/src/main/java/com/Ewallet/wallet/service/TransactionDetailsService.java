package com.Ewallet.wallet.service;

import com.Ewallet.wallet.dto.TransactionDTO;
import com.Ewallet.wallet.dto.WalletDTO;
import com.Ewallet.wallet.output.ApiOutput;

public interface TransactionDetailsService {

	public ApiOutput addMoney(WalletDTO walletDTO);

	public ApiOutput transferMoney(TransactionDTO transactionDTO);

	public ApiOutput viewPassBook(String mobileNumber);

	public ApiOutput checkStatus(Long txnId);

	public ApiOutput reversalTxn(Long txnId);

}
