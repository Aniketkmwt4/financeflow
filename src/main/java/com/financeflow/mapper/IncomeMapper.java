package com.financeflow.mapper;

import com.financeflow.dto.income.IncomeRequest;
import com.financeflow.dto.income.IncomeResponse;
import com.financeflow.entity.Income;
import org.springframework.stereotype.Component;

@Component
public class IncomeMapper {

    public Income toEntity(IncomeRequest request) {

        return Income.builder()
                .source(request.getSource())
                .amount(request.getAmount())
                .description(request.getDescription())
                .date(request.getDate())
                .build();
    }

    public IncomeResponse toResponse(Income income) {

        return IncomeResponse.builder()
                .id(income.getId())
                .source(income.getSource())
                .amount(income.getAmount())
                .description(income.getDescription())
                .date(income.getDate())
                .build();
    }
}