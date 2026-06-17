package com.example.DAR.DTO.In;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDate;

@Data
public class MaintenanceReminderDTOIn {

    @NotEmpty(message = "Title must not be empty")
    private String title;

    @NotEmpty(message = "Message must not be empty")
    private String message;

    @NotNull(message = "Reminder date must not be null")
    private LocalDate reminderDate;

    @NotEmpty(message = "Season must not be empty")
    private String season;

    @NotEmpty(message = "Weather condition must not be empty")
    private String weatherCondition;

}
