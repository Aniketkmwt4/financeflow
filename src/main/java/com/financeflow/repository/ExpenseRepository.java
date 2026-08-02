package com.financeflow.repository;


import com.financeflow.entity.Expense;
import com.financeflow.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ExpenseRepository extends JpaRepository<Expense,Long> {

    List<Expense> findByUser(User user);

    Optional<Expense> findByIdAndUser(Long id, User user);

}
