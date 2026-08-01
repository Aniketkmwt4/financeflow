package com.financeflow.repository;

import com.financeflow.entity.Income;
import com.financeflow.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface IncomeRepository extends JpaRepository<Income, Long> {

    List<Income> findByUser(User user);

    Optional<Income> findByIdAndUser(Long id, User user);

}