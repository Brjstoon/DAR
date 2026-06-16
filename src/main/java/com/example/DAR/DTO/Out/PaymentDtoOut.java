package com.example.DAR.DTO.Out;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PaymentDtoOut {
    private Integer id;
    private Integer userSubscriptionId;
    private Double amount;
    private String paymentMethod;
    private LocalDate paymentDate;
    private String status;
    private String transactionReference;
}
