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
public class Sensor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;


    private String type;

    private String location;

    private Boolean isActive;

    private LocalDate lastPing;


//    @ManyToOne
//    @JoinColumn(name = "home_id")
//    private Home home;



}
