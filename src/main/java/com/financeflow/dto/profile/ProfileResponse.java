package com.financeflow.dto.profile;


import com.financeflow.enums.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProfileResponse {

    private String fullName;

    private String email;

    private Role role;

    private LocalDateTime registeredAt;

}