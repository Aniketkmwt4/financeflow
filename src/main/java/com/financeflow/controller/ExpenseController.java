package com.financeflow.controller;

import com.financeflow.dto.expense.ExpenseRequest;
import com.financeflow.dto.expense.ExpenseResponse;
import com.financeflow.entity.User;
import com.financeflow.response.ApiResponse;
import com.financeflow.service.ExpenseService;
import com.financeflow.util.ApiResponseUtil;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/expenses")
@RequiredArgsConstructor
public class ExpenseController {

    private final ExpenseService expenseService;

    @PostMapping
    public ResponseEntity<ApiResponse<ExpenseResponse>> addExpense(
            @Valid @RequestBody ExpenseRequest request,
            @AuthenticationPrincipal User user
            ){

        ExpenseResponse response = expenseService.addExpense(request,user);

        return ResponseEntity.ok(
                ApiResponseUtil.success(
                        "Expense added successfully",
                        response
                )
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<ExpenseResponse>> getExpenseById(
             @PathVariable Long id,
             @AuthenticationPrincipal User user
    ){
        ExpenseResponse response = expenseService.getExpenseById(id,user);

        return ResponseEntity.ok(
                ApiResponseUtil.success(
                        "Expense fetched successfully",
                        response
                )
        );
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<ExpenseResponse>>> getAllExpenses(
            @AuthenticationPrincipal User user
    ){
        List<ExpenseResponse> expenses= expenseService.getAllExpenses(user);

        return ResponseEntity.ok(
                ApiResponseUtil.success(
                        "All expenses fetched successfully",
                        expenses
                )
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> deleteExpense(
            @PathVariable Long id,
            @AuthenticationPrincipal User user
    ){
        expenseService.deleteExpense(id,user);
        return ResponseEntity.ok(
                ApiResponseUtil.success(
                        "Expense deleted successfully",
                        null
                )
        );
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<ExpenseResponse>> updateExpense(
            @PathVariable Long id,
            @Valid @RequestBody ExpenseRequest request,
            @AuthenticationPrincipal User user
    ){
        ExpenseResponse response = expenseService.updateExpense(id,request,user);

        return ResponseEntity.ok(
                ApiResponseUtil.success(
                        "Expense updated successfully",
                        response
                )
        );
    }

}
