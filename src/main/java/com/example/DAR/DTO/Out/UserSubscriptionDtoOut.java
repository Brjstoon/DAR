package com.example.DAR.DTO.Out;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserSubscriptionDtoOut {
    private Integer id;
    private Integer userId;
    private String username;
    private Integer planId;
    private String planName;
    private LocalDate startDate;
    private LocalDate endDate;
    private String status;
    private String paymentStatus;
}
