package com.financeflow.mapper;

import com.financeflow.dto.expense.ExpenseRequest;
import com.financeflow.dto.expense.ExpenseResponse;
import com.financeflow.entity.Expense;
import com.financeflow.entity.User;
import org.springframework.stereotype.Component;

@Component
public class ExpenseMapper {

    public Expense toEntity(ExpenseRequest request){

        return Expense.builder()
                .amount(request.getAmount())
                .title(request.getTitle())
                .category(request.getCategory())
                .description(request.getDescription())
                .date(request.getDate())
                .build();
    }

    public ExpenseResponse toResponse(Expense expense){

        return ExpenseResponse.builder()
                .id(expense.getId())
                .category(expense.getCategory())
                .amount(expense.getAmount())
                .title(expense.getTitle())
                .description(expense.getDescription())
                .date(expense.getDate())
                .createdAt(expense.getCreatedAt())
                .updatedAt(expense.getUpdatedAt())
                .build();
    }
}
