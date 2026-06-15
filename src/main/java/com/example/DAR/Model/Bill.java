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
public class Bill {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String type;

    private LocalDate billMonth;

    private Integer consumption;

    private String unit;

    private Boolean isInstallment;

    private Integer totalInstallment;

    private Integer paidInstallment;

    private String status;

    private Boolean isAnomaly;

    private String imageUrl;


//    @ManyToOne
//    @JoinColumn(name = "home_id")
//    private Home home;


}
