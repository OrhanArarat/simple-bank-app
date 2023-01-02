package com.orhanararat.dto.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.orhanararat.dto.TransactionHistoryResponseDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BankAccountHistoryResponseDto implements Serializable {
    private String accountNumber;
    private Long id;
    private String owner;
    private BigDecimal balance;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime createDate;

    public BankAccountHistoryResponseDto(String accountNumber, Long id, String owner, BigDecimal balance, LocalDateTime createDate) {
        this.accountNumber = accountNumber;
        this.id = id;
        this.owner = owner;
        this.balance = balance;
        this.createDate = createDate;
    }

    private List<TransactionHistoryResponseDto> transactions;

}
