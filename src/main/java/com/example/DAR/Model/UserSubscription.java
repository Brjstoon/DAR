package com.example.DAR.Model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class UserSubscription {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Integer userId;
    private Integer planId;

    @Column(nullable = false)

    private LocalDate startDate;
    @Column(nullable = false)

    private LocalDate endDate;
    @Column(nullable = false)

    private String status;
    @Column(nullable = false)

    private String paymentStatus;

}
