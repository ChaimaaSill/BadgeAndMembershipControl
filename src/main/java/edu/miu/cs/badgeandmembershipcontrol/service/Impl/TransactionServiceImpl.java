package edu.miu.cs.badgeandmembershipcontrol.service.Impl;

import edu.miu.cs.badgeandmembershipcontrol.domain.Transaction;
import edu.miu.cs.badgeandmembershipcontrol.repository.TransactionRepository;
import edu.miu.cs.badgeandmembershipcontrol.service.TransactionService;

import java.util.List;
import java.util.Optional;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class TransactionServiceImpl implements TransactionService {

	private final TransactionRepository transactionRepository;
	
	@Override public List<Transaction> getAllTransactions() {
		return transactionRepository.findAll();
	}

	@Override public Transaction getTransaction(Long transactionId) {
	Optional<Transaction> transactionOptional = transactionRepository.findById(transactionId);
		return transactionOptional.orElse(null);
	}
	
	@Override public List<Transaction> getBadgeTransactions(Long badgeId) {
		Optional<List<Transaction>> badgeTransactionsOptional = transactionRepository.findTransactionsByBadge_Id(badgeId);
		return badgeTransactionsOptional.orElse(null);
	}

	@Override public Transaction createTransaction(Transaction transaction) {
		return transactionRepository.save(transaction);
	}
	
	@Override public boolean removeTransaction(Long transactionId) {
		Optional<Transaction> transactionOptional = transactionRepository.findById(transactionId);
		if(transactionOptional.isPresent()){
			transactionRepository.deleteById(transactionId);
			return true;
		}
		return false;
	}

}