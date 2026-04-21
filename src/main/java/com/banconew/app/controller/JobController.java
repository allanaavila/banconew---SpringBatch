package com.banconew.app.controller;

import com.banconew.app.service.EnrichmentJobService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/jobs")
public class JobController {

    @Autowired
    private EnrichmentJobService jobService;

    @PostMapping("/run-enrichment")
    public ResponseEntity<String> runEnrichment() {
        try {
            log.info("Recebida requisição para iniciar o Job de Enriquecimento...");
            jobService.runJob();
            return ResponseEntity.ok("Job disparado com sucesso! Acompanhe os logs no console.");
        } catch (Exception e) {
            log.error("Erro ao disparar o Job: ", e);
            return ResponseEntity.internalServerError().body("Falha ao iniciar o Job: " + e.getMessage());
        }
    }
}
