package com.banconew.domain.model;

import lombok.Getter;

@Getter
public enum StatusModel {
    IMPORTED(1L, "IMPORTED"),
    ENRICHED(2L, "ENRICHED"),
    PROCESSED(3L, "PROCESSED"),
    ERROR(4L, "ERROR"),
    ACTIVED(null, "ACTIVED"),
    BLOCKED(null, "BLOCKED");

    private final Long id;
    private final String description;

    StatusModel(Long id, String description) {
        this.id = id;
        this.description = description;
    }
    public static StatusModel fromId(Long id) {
        for (StatusModel status : values()) {
            if (status.getId() != null && status.getId().equals(id)) {
                return status;
            }
        }
        return ERROR;
    }
}