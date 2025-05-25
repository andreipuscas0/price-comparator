package com.accesa.pricecomparator.controller;

import com.accesa.pricecomparator.model.Discount;
import com.accesa.pricecomparator.service.DiscountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/discounts")
public class DiscountController {

    @Autowired
    private DiscountService discountService;

    @GetMapping("/best")
    public List<Discount> getBestDiscounts() {
        return discountService.getBestDiscounts();
    }

    @GetMapping("/new")
    public List<Discount> getNewDiscounts() {
        return discountService.getNewDiscounts();
    }

    @GetMapping("/store/{store}")
    public List<Discount> getByStore(@PathVariable("store") String store) {
        return discountService.getDiscountsByStore(store);
    }

    @GetMapping("/date")
    public List<Discount> getDiscountsByDateRange(@RequestParam("from") String fromDateStr,
                                                  @RequestParam("to") String toDateStr) {
        LocalDate from = LocalDate.parse(fromDateStr);
        LocalDate to = LocalDate.parse(toDateStr);
        return discountService.getDiscountsByDateRange(from, to);
    }

}


