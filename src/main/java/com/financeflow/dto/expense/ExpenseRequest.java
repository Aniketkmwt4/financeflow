package com.financeflow.dto.expense;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.*;
import org.springframework.security.core.parameters.P;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ExpenseRequest {

    @NotBlank
    private String title;


    @NotNull
    @Positive
    private BigDecimal amount;

    @NotBlank
    private String category;


    private String description;

    @NotNull
    private LocalDate date;

}
