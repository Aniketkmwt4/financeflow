package com.financeflow.service.impl;

import com.financeflow.dto.income.IncomeRequest;
import com.financeflow.dto.income.IncomeResponse;
import com.financeflow.entity.Income;
import com.financeflow.entity.User;
import com.financeflow.exception.custom.ResourceNotFoundException;
import com.financeflow.mapper.IncomeMapper;
import com.financeflow.repository.IncomeRepository;
import com.financeflow.service.IncomeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class IncomeServiceImpl implements IncomeService {


    private final IncomeRepository incomeRepository;
    private final IncomeMapper incomeMapper;

    @Override
    public void deleteIncome(Long id, User user) {

        Income income = incomeRepository.findByIdAndUser(id, user)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Income not found"));

        incomeRepository.delete(income);

    }

    @Override
    public IncomeResponse addIncome(IncomeRequest request, User user) {

        Income income = incomeMapper.toEntity(request);

        income.setUser(user);

        income.setCreatedAt(LocalDateTime.now());
        income.setUpdatedAt(LocalDateTime.now());

        Income savedIncome = incomeRepository.save(income);

        return incomeMapper.toResponse(savedIncome);
    }

    @Override
    public List<IncomeResponse> getAllIncomes(User user) {
        List<Income> incomes = incomeRepository.findByUser(user);

        return incomes.stream()
                .map(incomeMapper::toResponse)
                .toList();
    }

    @Override
    public IncomeResponse getIncomeById(Long id, User user) {
        Income income = incomeRepository.findByIdAndUser(id, user)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Income not found"));

        return incomeMapper.toResponse(income);
    }

    @Override
    public IncomeResponse updateIncome(Long id, IncomeRequest request, User user) {

        Income income = incomeRepository.findByIdAndUser(id, user)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Income not found"));

        income.setSource(request.getSource());
        income.setAmount(request.getAmount());
        income.setDescription(request.getDescription());
        income.setDate(request.getDate());
        income.setUpdatedAt(LocalDateTime.now());

        Income updatedIncome = incomeRepository.save(income);

        return incomeMapper.toResponse(updatedIncome);
    }
}