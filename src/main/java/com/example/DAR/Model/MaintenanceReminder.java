package com.example.DAR.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class MaintenanceReminder {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String title;

    private String message;

    private LocalDate reminderDate;

    private String season;

    private String weatherCondition;

    private Boolean isSent;

    private LocalDate createdAt;


//    @ManyToOne
//    @JoinColumn(name = "home_id")
//    private Home home;


//    @OneToOne
//    @JoinColumn(name = "homeItem_id")
//    private HomeItem homeItem;



}
