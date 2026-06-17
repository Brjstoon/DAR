package com.example.DAR.DTO.Out;

import lombok.Data;

import java.time.LocalDate;

@Data
public class MaintenanceDTOOut {

    private Integer id;
    private String title;
    private String description;
    private LocalDate maintenanceDate;
    private Double cost;
    private String status;
    private String priority;
    private String notes;
    private String homeAddress;      // from Home.address
    private String homeItemCategory; // from HomeItem.category
    private String homeItemBrand;    // from HomeItem.brand
}
