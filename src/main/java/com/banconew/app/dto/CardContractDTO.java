package com.banconew.app.dto;

import com.banconew.domain.model.StatusModel;
import lombok.*;
import lombok.extern.slf4j.Slf4j;

import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CardContractDTO {
    private String contractId;
    private String customerId;
    private StatusModel status;
    private LocalDateTime updateTime;

    private List<CardDTO> cards;
}
