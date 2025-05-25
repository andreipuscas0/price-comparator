package com.accesa.pricecomparator.service;

import com.accesa.pricecomparator.model.Discount;
import com.accesa.pricecomparator.model.Store;
import com.accesa.pricecomparator.util.CsvLoader;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class DiscountService {

    private final CsvLoader csvLoader;

    public DiscountService(CsvLoader csvLoader) {
        this.csvLoader = csvLoader;
    }

    public List<Discount> getBestDiscounts() {
        List<Discount> all = new ArrayList<>();
        all.addAll(csvLoader.loadDiscounts("data/lidl_discounts_2025-05-08.csv", Store.LIDL));
        all.addAll(csvLoader.loadDiscounts("data/kaufland_discounts_2025-05-08.csv", Store.KAUFLAND));
        all.addAll(csvLoader.loadDiscounts("data/profi_discounts_2025-05-08.csv", Store.PROFI));

        all.sort(Comparator.comparingDouble(Discount::getDiscountPercent).reversed());
        return all.subList(0, Math.min(10, all.size()));
    }

    public List<Discount> getNewDiscounts() {
        LocalDate now = LocalDate.now();
        LocalDate yesterday = now.minusDays(1);

        List<Discount> allDiscounts = new ArrayList<>();
        for (Store store : Store.values()) {
            String filename = "data/" + store.name().toLowerCase() + "_discounts_2025-05-08.csv";
            try {
                allDiscounts.addAll(csvLoader.loadDiscounts(filename, store));
            } catch (Exception e) {
                System.err.println("File missing or invalid: " + filename);
            }
        }

        return allDiscounts.stream()
                .filter(d -> !d.getFromDate().isBefore(yesterday))
                .collect(Collectors.toList());
    }

    public List<Discount> getDiscountsByStore(String storeName) {
        Store store;
        try {
            store = Store.valueOf(storeName);
        } catch (IllegalArgumentException e) {
            System.err.println("Invalid store name: " + storeName);
            return new ArrayList<>();
        }

        String filename = "data/" + store.name().toLowerCase() + "_discounts_2025-05-08.csv";
        try {
            return csvLoader.loadDiscounts(filename, store);
        } catch (Exception e) {
            System.err.println("Failed to load discounts for " + storeName + ": " + e.getMessage());
            return new ArrayList<>();
        }
    }

    public List<Discount> getDiscountsByDateRange(LocalDate from, LocalDate to) {
        List<Discount> allDiscounts = new ArrayList<>();
        for (Store store : Store.values()) {
            String filename = "data/" + store.name().toLowerCase() + "_discounts_2025-05-08.csv"; // sau altă logică
            try {
                allDiscounts.addAll(csvLoader.loadDiscounts(filename, store));
            } catch (Exception e) {
                System.err.println("Error loading file: " + filename);
            }
        }

        return allDiscounts.stream()
                .filter(d -> !(d.getToDate().isBefore(from) || d.getFromDate().isAfter(to)))
                .collect(Collectors.toList());
    }

    public List<Discount> getDiscountsAbove(double minDiscount) {
        List<Discount> all = new ArrayList<>();
        for (Store store : Store.values()) {
            String filename = "data/" + store.name().toLowerCase() + "_discounts_2025-05-08.csv";
            try {
                all.addAll(csvLoader.loadDiscounts(filename, store));
            } catch (Exception e) {
                System.err.println("Failed to load: " + filename);
            }
        }

        return all.stream()
                .filter(d -> d.getDiscountPercent() >= minDiscount)
                .collect(Collectors.toList());
    }

}
