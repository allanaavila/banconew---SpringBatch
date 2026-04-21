package com.banconew.app.dto;

import lombok.*;
import lombok.extern.slf4j.Slf4j;

import java.time.LocalDate;

@Slf4j
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RestrictiveDTO {
    private String id;
    private String name;
    private String documentNumber;
    private String email;
    private String phoneNumber;
    private String debtType;
    private String fraudType;
    private LocalDate occurrenceDate;
    private Long statusId;
    private String customerId;
}
