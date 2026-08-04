package com.financeflow.service.impl;

import com.financeflow.dto.dashboard.DashboardResponse;
import com.financeflow.entity.User;
import com.financeflow.repository.ExpenseRepository;
import com.financeflow.repository.IncomeRepository;
import com.financeflow.service.DashboardService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Optional;


@RequiredArgsConstructor
@Service
public class DashboardServiceImpl implements DashboardService {

    private final IncomeRepository incomeRepository;
    private final ExpenseRepository expenseRepository;


    private static BigDecimal getOrZero(BigDecimal value) {
        return Optional.ofNullable(value).orElse(BigDecimal.ZERO);
    }



    @Override
    public DashboardResponse getDashboard(User user) {
        BigDecimal totalIncome = getOrZero(incomeRepository.getTotalIncome(user));
        BigDecimal totalExpense = getOrZero(expenseRepository.getTotalExpense(user));

        BigDecimal thisMonthIncome = getOrZero(incomeRepository.getThisMonthIncome(user));
        BigDecimal thisMonthExpense = getOrZero(expenseRepository.getThisMonthExpense(user));


        BigDecimal balance = totalIncome.subtract(totalExpense);

        return DashboardResponse.builder()
                .totalIncome(totalIncome)
                .totalExpense(totalExpense)
                .balance(balance)
                .thisMonthIncome(thisMonthIncome)
                .thisMonthExpense(thisMonthExpense)
                .build();
    }


}
