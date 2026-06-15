package com.example.DAR.DTO.In;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
public class HomeItemDTOIn {

    @NotEmpty(message = "Category must not be empty")
    private String category;

    @NotEmpty(message = "Brand must not be empty")
    private String brand;

    @NotNull(message = "Install date must not be null")
    private LocalDate installDate;

    @NotNull(message = "Lifespan in months must not be null")
    @Positive(message = "Lifespan must be a positive number")
    private Integer lifespanMonth;

    @NotNull(message = "Next service date must not be null")
    private LocalTime nextServiceDate;

    @NotEmpty(message = "Notes must not be empty")
    private String notes;

}
