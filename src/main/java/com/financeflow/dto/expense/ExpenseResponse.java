package com.financeflow.dto.expense;


import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ExpenseResponse {

    private Long id;


    private String category;
    private BigDecimal amount;
    private String title;
    private String description;
    private LocalDate date;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
