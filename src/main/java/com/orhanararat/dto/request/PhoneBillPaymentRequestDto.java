package com.orhanararat.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PhoneBillPaymentRequestDto implements Serializable {

    private String accountNumber;
    private String payee;
    private String subscriberNumber;
    private BigDecimal amount;
}
