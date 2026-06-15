package com.example.DAR.Model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Maintenance {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String title;

    private String description;

    private LocalDate maintenanceDate;

    private Double cost;

    private String status;

    private String priority;

    private String notes;



//    @ManyToOne
//    @JoinColumn(name = "home_id")
//    private Home home;


//    @OneToOne
//    @JoinColumn(name = "homeItem_id")
//    private HomeItem homeItem;

}
