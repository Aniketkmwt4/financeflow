package com.financeflow.service;

import com.financeflow.dto.expense.ExpenseRequest;
import com.financeflow.dto.expense.ExpenseResponse;
import com.financeflow.entity.User;

import java.util.List;

public interface ExpenseService {

     ExpenseResponse addExpense(ExpenseRequest expenseRequest, User user);

     List<ExpenseResponse> getAllExpenses(User user);

     ExpenseResponse getExpenseById(Long id,User user);

     ExpenseResponse updateExpense(Long id, ExpenseRequest expenseRequest,User user);

     void deleteExpense(Long id, User user);
}
