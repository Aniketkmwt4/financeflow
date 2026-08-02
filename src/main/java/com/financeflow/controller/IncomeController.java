package com.financeflow.controller;

import com.financeflow.dto.income.IncomeRequest;
import com.financeflow.dto.income.IncomeResponse;
import com.financeflow.entity.User;
import com.financeflow.response.ApiResponse;
import com.financeflow.service.IncomeService;
import com.financeflow.util.ApiResponseUtil;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/incomes")
@RequiredArgsConstructor
public class IncomeController {

    private final IncomeService incomeService;

    @PostMapping
    public ResponseEntity<ApiResponse<IncomeResponse>> addIncome(
            @Valid @RequestBody IncomeRequest request,
            @AuthenticationPrincipal User user) {

        System.out.println("Income Controller Called");


        IncomeResponse response = incomeService.addIncome(request, user);

        return ResponseEntity.ok(
                ApiResponseUtil.success(
                        "Income added successfully",
                        response
                )
        );
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<IncomeResponse>>> getAllIncomes(
            @AuthenticationPrincipal User user) {

        List<IncomeResponse> response = incomeService.getAllIncomes(user);

        System.out.println("GET Income Controller Called");

        return ResponseEntity.ok(
                ApiResponseUtil.success(
                        "Income fetched successfully",
                        response
                )
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<IncomeResponse>> getIncomeById(
            @PathVariable Long id,
            @AuthenticationPrincipal User user) {

        IncomeResponse response = incomeService.getIncomeById(id, user);

        return ResponseEntity.ok(
                ApiResponseUtil.success(
                        "Income fetched successfully",
                        response
                )
        );
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<IncomeResponse>> updateIncome(
            @PathVariable Long id,
            @Valid @RequestBody IncomeRequest request,
            @AuthenticationPrincipal User user) {

        IncomeResponse response = incomeService.updateIncome(id, request, user);

        return ResponseEntity.ok(
                ApiResponseUtil.success(
                        "Income updated successfully",
                        response
                )
        );
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> deleteIncome(
            @PathVariable Long id,
            @AuthenticationPrincipal User user) {

        incomeService.deleteIncome(id, user);

        return ResponseEntity.ok(
                ApiResponseUtil.success(
                        "Income deleted successfully",
                        null
                )
        );
    }
}