package com.orhanararat.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;


@Getter
@Setter
@AllArgsConstructor
@Builder
public class HistoryRequestDto implements Serializable {
    private String accountNumber;
}
