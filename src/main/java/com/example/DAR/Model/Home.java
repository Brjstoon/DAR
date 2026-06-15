package com.example.DAR.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Home {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "address", nullable = false)
    private String address;

    @Column(name = "latitude", nullable = false)
    private Double latitude;

    @Column(name = "longitude", nullable = false)
    private Double longitude;

    @Column(name = "buildYear", nullable = false)
    private Integer buildYear;



//    @OneToMany(mappedBy = "home", cascade = CascadeType.ALL)
//    @JsonIgnore
//    private Set<MaintenanceReminder> maintenanceReminders;
//
//
//    @OneToMany(mappedBy = "home", cascade = CascadeType.ALL)
//    @JsonIgnore
//    private Set<Maintenance> maintenances;
//
//
//    @OneToMany(mappedBy = "home", cascade = CascadeType.ALL)
//    @JsonIgnore
//    private Set<Bill> bills;
//
//
//    @OneToMany(mappedBy = "home", cascade = CascadeType.ALL)
//    @JsonIgnore
//    private Set<Sensor> sensors;
//
//
//    @OneToMany(mappedBy = "home", cascade = CascadeType.ALL)
//    @JsonIgnore
//    private Set<PurchaseInvoice> purchaseInvoices;

}
