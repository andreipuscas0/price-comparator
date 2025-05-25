package com.accesa.pricecomparator.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Discount {
    private long productId;
    private String productName;
    private String brand;
    private double quantity;
    private String unit;
    private String category;
    private LocalDate fromDate;
    private LocalDate toDate;
    private double discountPercent;
    private Store store;
}


