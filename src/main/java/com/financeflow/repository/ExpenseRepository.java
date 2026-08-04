package com.financeflow.repository;


import com.financeflow.entity.Expense;
import com.financeflow.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

public interface ExpenseRepository extends JpaRepository<Expense,Long> {

    List<Expense> findByUser(User user);

    Optional<Expense> findByIdAndUser(Long id, User user);


    @Query("""
       SELECT SUM(e.amount)
       FROM Expense e
       WHERE e.user = :user
       """)
    BigDecimal getTotalExpense(@Param("user") User user);

    @Query("""
       SELECT SUM(e.amount)
       FROM Expense e
       WHERE e.user = :user
       AND MONTH(e.date) = MONTH(CURRENT_DATE)
       AND YEAR(e.date) = YEAR(CURRENT_DATE)
       """)
    BigDecimal getThisMonthExpense(@Param("user") User user);


}
