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
public class PurchaseInvoice {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;


    private String productName;

    private String store;

    private Double amount;

    private LocalDate purchaseDate;

    private String category;

    private String imageUrl;

    private String warrantyNote;

    private LocalDate warrantyExpiry;


//    @ManyToOne
//    @JoinColumn(name = "home_id")
//    private Home home;
}
