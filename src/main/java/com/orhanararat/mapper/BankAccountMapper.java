package com.orhanararat.mapper;

import com.orhanararat.dto.response.BankAccountHistoryResponseDto;
import com.orhanararat.model.BankAccount;
import org.mapstruct.Mapper;

@Mapper
public interface BankAccountMapper {
    BankAccountHistoryResponseDto bankAccountToBankAccountHistoryDto(BankAccount bankAccount);
}
