package com.banconew.app.dto;

import com.banconew.domain.model.StatusModel;
import lombok.*;
import lombok.extern.slf4j.Slf4j;

import java.time.LocalDateTime;

@Slf4j
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CardContractDTO {
    private String contractId;
    private Long customerId;
    private String cardNumber;
    private StatusModel status;
    private LocalDateTime updateTime;
}
