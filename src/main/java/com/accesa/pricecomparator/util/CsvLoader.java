package com.accesa.pricecomparator.util;

import com.accesa.pricecomparator.model.Discount;
import com.accesa.pricecomparator.model.Store;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Component
public class CsvLoader {

    public List<Discount> loadDiscounts(String filename, Store store) {
        List<Discount> discounts = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(
                getClass().getClassLoader().getResourceAsStream(filename)))) {

            String line;
            reader.readLine(); // skip header

            while ((line = reader.readLine()) != null) {
                String[] tokens = line.split(",");

                Discount discount = new Discount(
                        Long.parseLong(tokens[0]),                    // productId
                        tokens[1],                                    // productName
                        tokens[2],                                    // brand
                        Double.parseDouble(tokens[3]),               // quantity
                        tokens[4],                                    // unit
                        tokens[5],                                    // category
                        LocalDate.parse(tokens[6]),                  // fromDate
                        LocalDate.parse(tokens[7]),                  // toDate
                        Double.parseDouble(tokens[8]),               // discountPercent
                        store                                         // store (enum)
                );

                discounts.add(discount);
            }

        } catch (IOException | NullPointerException e) {
            System.err.println("Failed to load CSV: " + filename + " | " + e.getMessage());
        }

        return discounts;
    }
}