package com.example.DAR.DTO.Out;

import lombok.Data;

@Data
public class HomeDTOOut {

    private Integer id;
    private String address;
    private Double latitude;
    private Double longitude;
    private Integer buildYear;
    private String ownerName;  // from User.fullName
}
