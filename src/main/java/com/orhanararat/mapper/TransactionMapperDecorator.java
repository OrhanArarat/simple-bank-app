package com.orhanararat.mapper;

import com.orhanararat.dto.TransactionHistoryResponseDto;
import com.orhanararat.model.Transaction;
import org.springframework.beans.factory.annotation.Autowired;

public abstract class TransactionMapperDecorator implements TransactionMapper {

    private TransactionMapper mapper;

    @Autowired
    public void setMapper(TransactionMapper mapper) {
        this.mapper = mapper;
    }

    @Override
    public TransactionHistoryResponseDto transactionToTransactionHistoryDto(Transaction transaction) {
        TransactionHistoryResponseDto transactionHistoryResponseDto = mapper.transactionToTransactionHistoryDto(transaction);
        transactionHistoryResponseDto.setType(transaction.getClass().getSimpleName());
        return transactionHistoryResponseDto;
    }
}
