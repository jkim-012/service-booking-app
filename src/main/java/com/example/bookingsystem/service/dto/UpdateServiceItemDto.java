package com.example.bookingsystem.service.dto;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UpdateServiceItemDto {

    private String name;
    private String description;
    private Double price;
    private Integer duration;

}
