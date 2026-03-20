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
    private Long id;
    private String type;
    private String description;

    // Referência simplificada ao cliente para o enriquecimento
    private Long customerId;
    private String customerDocument;
    private LocalDate occurrenceDate;
}
