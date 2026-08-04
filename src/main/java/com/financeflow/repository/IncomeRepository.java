package com.financeflow.repository;

import com.financeflow.entity.Income;
import com.financeflow.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

public interface IncomeRepository extends JpaRepository<Income, Long> {

    List<Income> findByUser(User user);

    Optional<Income> findByIdAndUser(Long id, User user);

    @Query("SELECT SUM(i.amount) FROM Income i WHERE i.user = :user")
    BigDecimal getTotalIncome(@Param("user") User user);

    @Query("""
       SELECT SUM(i.amount)
       FROM Income i
       WHERE i.user = :user
       AND MONTH(i.date) = MONTH(CURRENT_DATE)
       AND YEAR(i.date) = YEAR(CURRENT_DATE)
       """)
    BigDecimal getThisMonthIncome(@Param("user") User user);

}