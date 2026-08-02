package com.financeflow.service.impl;

import com.financeflow.dto.expense.ExpenseRequest;
import com.financeflow.dto.expense.ExpenseResponse;
import com.financeflow.entity.Expense;
import com.financeflow.entity.User;
import com.financeflow.exception.custom.ResourceNotFoundException;
import com.financeflow.mapper.ExpenseMapper;
import com.financeflow.repository.ExpenseRepository;
import com.financeflow.service.ExpenseService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@RequiredArgsConstructor
@Service
public class ExpenseServiceImpl implements ExpenseService {

    private final ExpenseRepository expenseRepository;
    private final ExpenseMapper expenseMapper;

    @Override
    public ExpenseResponse addExpense(ExpenseRequest expenseRequest, User user) {

        Expense expense = expenseMapper.toEntity(expenseRequest);

        expense.setUser(user);

        LocalDateTime now = LocalDateTime.now();

        expense.setCreatedAt(now);
        expense.setUpdatedAt(now);

        Expense savedExpense = expenseRepository.save(expense);

        return expenseMapper.toResponse(savedExpense);

    }

    @Override
    public List<ExpenseResponse> getAllExpenses(User user) {
        List<Expense> expenses = expenseRepository.findByUser(user);

        return expenses.stream()
                .map(expenseMapper::toResponse)
                .toList();
    }

    @Override
    public ExpenseResponse getExpenseById(Long id, User user) {
        Expense expense = expenseRepository.findByIdAndUser(id,user)
                .orElseThrow(()-> new ResourceNotFoundException("Expense not found"));

        return expenseMapper.toResponse(expense);

    }

    @Override
    public ExpenseResponse updateExpense(Long id, ExpenseRequest expenseRequest, User user) {
        Expense expense = expenseRepository.findByIdAndUser(id,user)
                .orElseThrow(()-> new ResourceNotFoundException("Expense not found"));

        expense.setDate(expenseRequest.getDate());
        expense.setAmount(expenseRequest.getAmount());
        expense.setTitle(expenseRequest.getTitle());
        expense.setDescription(expenseRequest.getDescription());
        expense.setCategory(expenseRequest.getCategory());
        expense.setUpdatedAt(LocalDateTime.now());

        Expense savedExpense = expenseRepository.save(expense);

        return expenseMapper.toResponse(savedExpense);

    }

    @Override
    public void deleteExpense(Long id, User user) {

       Expense expense = expenseRepository.findByIdAndUser(id,user)
               .orElseThrow(()-> new ResourceNotFoundException("Expense not found"));

       expenseRepository.delete(expense);

    }
}
