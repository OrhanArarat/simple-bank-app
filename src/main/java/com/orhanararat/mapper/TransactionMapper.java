package com.orhanararat.mapper;

import com.orhanararat.dto.TransactionHistoryResponseDto;
import com.orhanararat.model.Transaction;
import org.mapstruct.DecoratedWith;
import org.mapstruct.Mapper;

@Mapper
@DecoratedWith(TransactionMapperDecorator.class)
public interface TransactionMapper {
    TransactionHistoryResponseDto transactionToTransactionHistoryDto(Transaction transaction);
}
