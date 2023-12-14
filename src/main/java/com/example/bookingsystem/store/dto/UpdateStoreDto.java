package com.example.bookingsystem.store.dto;

import java.sql.Time;
import javax.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UpdateStoreDto {

    // name, description, phone, zip, location, operation hours can be updated

    private String name;

    private String description;

    private String phone;

    private String location;

    @Size(min = 6 , max = 6, message = "Zipcode must be 6 characters")
    private String zipcode;

    // operation hours
    private Time openTime;
    private Time closeTime;


}
