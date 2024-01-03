package com.example.bookingsystem.global;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PageInfo {

  private int page;
  private int size;
  private int totalPage;
  private long totalElements;

}
