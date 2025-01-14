package com.maxi.pantrypos.dto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@Builder
public class ErrorDTO {
    private String code;
    private String message;
    private String detail;
    private LocalDate timestamp;
}
