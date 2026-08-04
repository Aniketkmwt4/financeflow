package com.financeflow.service;

import com.financeflow.dto.dashboard.DashboardResponse;
import com.financeflow.entity.User;


public interface DashboardService {


    DashboardResponse getDashboard(User user);

}