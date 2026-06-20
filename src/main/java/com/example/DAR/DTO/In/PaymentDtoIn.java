package com.example.DAR.DTO.In;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;

@Data
@AllArgsConstructor
public class PaymentDtoIn {


    @NotEmpty(message = "Payment method is required")
    private String paymentMethod;


    @NotEmpty(message = "Transaction reference is required")
    private String transactionReference;
}
