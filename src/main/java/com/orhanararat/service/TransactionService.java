package com.orhanararat.service;

import com.orhanararat.dto.TransactionHistoryResponseDto;
import com.orhanararat.mapper.TransactionMapper;
import com.orhanararat.model.BankAccount;
import com.orhanararat.model.Transaction;
import com.orhanararat.repository.transaction.TransactionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TransactionService {
    private final TransactionRepository transactionRepository;
    private final TransactionMapper transactionMapper;

    public Transaction save(Transaction transaction) {
        return transactionRepository.save(transaction);
    }

    public List<TransactionHistoryResponseDto> getTransaction(BankAccount bankAccount) {
        List<Transaction> allTransactionByAccountNumber = bankAccount.getTransactions();
        return allTransactionByAccountNumber.stream()
                .map(transactionMapper::transactionToTransactionHistoryDto).collect(Collectors.toList());
    }


}
