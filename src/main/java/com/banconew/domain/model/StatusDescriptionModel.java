package com.banconew.domain.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "status_description")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class StatusDescriptionModel {
    @Id
    private Long id;
    private String description;
}