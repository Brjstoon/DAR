package com.example.DAR.DTO.Out;

import lombok.Data;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
public class HomeItemDTOOut {

    private Integer id;
    private String category;
    private String brand;
    private LocalDate installDate;
    private Integer lifespanMonth;
    private LocalTime nextServiceDate;
    private String notes;
    private String homeAddress;  // from Home.address
}
