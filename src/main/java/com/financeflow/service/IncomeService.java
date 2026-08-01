package com.financeflow.service;

import com.financeflow.dto.income.IncomeRequest;
import com.financeflow.dto.income.IncomeResponse;
import com.financeflow.entity.User;

import java.util.List;

public interface IncomeService {

    IncomeResponse addIncome(IncomeRequest request, User user);

    List<IncomeResponse> getAllIncomes(User user);

    IncomeResponse getIncomeById(Long id, User user);

    IncomeResponse updateIncome(Long id, IncomeRequest request, User user);

    void deleteIncome(Long id, User user);
}